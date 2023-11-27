import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import StaffOrderServices from "../services/StaffOrderServices";
import SMOrderService from "../services/SMOrderService";
import "./StaffOrderList.css";

function StaffOrderList() {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [smorders, setSMOrders] = useState(null);
  const [selectedStatus, setSelectedStatus] = useState(
    "Pending Admin Approval"
  );

  useEffect(() => {
    setLoading(true);

    // Fetch a list of orders
    SMOrderService.getSMOrders()
      .then((response) => {
        // Filter orders based on the selected status
        const filteredOrders = response.data.filter(
          (order) => order.status === selectedStatus
        );
        setSMOrders(filteredOrders);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching orders: ", error);
        setLoading(false);
      });
  }, [selectedStatus]); // Add selectedStatus as a dependency

  return (
    <div>
      <Navbar />
      <div className="main-containerC">
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
              {smorders.map((smorder) => (
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
                      onClick={() => navigate(`/stafforder/${smorder.id}`)}
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

export default StaffOrderList;
