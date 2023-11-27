import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import SMOrderService from "../services/SMOrderService";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "./SMOrderList.css";

function SMOrderList() {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [smorders, setSMOrders] = useState(null);
  const [selectedStatus, setSelectedStatus] = useState("All"); // Initialize with "All" status

  useEffect(() => {
    setLoading(true);

    // Fetch a list of orders
    SMOrderService.getSMOrders()
      .then((response) => {
        setSMOrders(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching orders: ", error);
        setLoading(false);
      });
  }, []);

  // Filter orders based on the selected status
  const filteredOrders =
    selectedStatus === "All"
      ? smorders
      : smorders && smorders.filter((order) => order.status === selectedStatus);

  return (
    <div>
      <Navbar />
      <div className="main-containerC">
        <div className="filter-containerC">
          <label>Filter by Status: </label>
          <select
            value={selectedStatus}
            onChange={(e) => setSelectedStatus(e.target.value)}
          >
            <option value="All">All</option>
            <option value="Accepted">Accepted</option>
            <option value="Rejected">Rejected</option>
            <option value="Placed">Placed</option>
            <option value="Pending Admin Approval">
              Pending Admin Approval
            </option>
          </select>
        </div>

        {loading ? (
          <p className="loading-text">Loading...</p>
        ) : (
          <table className="data-tableC">
            <thead>
              <tr>
                <th className="header-cellC">Order Id</th>
                <th className="header-cellC">Site Manager</th>
                <th className="header-cellC">Site Name</th>
                <th className="header-cellC">Status</th>
                <th className="header-cellC">Request Date</th>
              </tr>
            </thead>
            <tbody>
              {filteredOrders.map((smorder) => (
                <tr key={smorder.id} className="data-rowC">
                  <td className="data-cellC">{smorder.orderId}</td>
                  <td className="data-cellC">{smorder.siteManager}</td>
                  <td className="data-cellC">{smorder.siteName}</td>
                  <td className="data-cellC">{smorder.status}</td>
                  <td className="data-cellC">
                    {new Date(smorder.reqDate).toLocaleDateString()}
                  </td>
                  <td className="data-cellC">
                    <button
                      className="view-buttonC"
                      onClick={() => navigate(`/smorder/${smorder.id}`)}
                    >
                      View
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
      <Footer />
    </div>
  );
}

export default SMOrderList;
