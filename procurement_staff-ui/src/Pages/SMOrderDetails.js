import React, { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom"; // Import Link from react-router-dom
import SMOrderService from "../services/SMOrderService";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "./SMOrderDe.css";

function SMOrderDetails() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [smOrderDetails, setSMOrderDetails] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);

    SMOrderService.getSMOrderById(id)
      .then((response) => {
        // Convert the reqDate to a Date object
        response.data.reqDate = new Date(response.data.reqDate);

        setSMOrderDetails(response.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err);
        setLoading(false);
      });
  }, [id]);

  const editOrderStatus = (newStatus) => {
    if (newStatus === "Placed") {
      const updatedOrder = { ...smOrderDetails, status: newStatus };

      SMOrderService.updateSMOrderStatus(updatedOrder, id)
        .then((updatedOrder) => {
          // Update the order status in the state
          setSMOrderDetails(updatedOrder);
          navigate("/addorder");
        })
        .catch((err) => {
          console.error("Error updating order status: ", err);
        });
    }
    if (newStatus === "Pending Admin Approval") {
      const updatedOrder = { ...smOrderDetails, status: newStatus };

      SMOrderService.updateSMOrderStatus(updatedOrder, id)
        .then((updatedOrder) => {
          // Update the order status in the state
          setSMOrderDetails(updatedOrder);
          navigate("/stafforders");
        })
        .catch((err) => {
          console.error("Error updating order status: ", err);
        });
    } else {
      const updatedOrder = { ...smOrderDetails, status: newStatus };

      SMOrderService.updateSMOrderStatus(updatedOrder, id)
        .then((updatedOrder) => {
          // Update the order status in the state
          setSMOrderDetails(updatedOrder);
          navigate("/smorderlist");
        })
        .catch((err) => {
          console.error("Error updating order status: ", err);
        });
    }
  };

  // Function to format date to display only the date part (yyyy-mm-dd)
  const formatDate = (date) => {
    const options = { year: "numeric", month: "2-digit", day: "2-digit" };
    return date.toLocaleDateString(undefined, options);
  };

  return (
    <div>
      <Navbar />
      <div className="order-details-containerD">
        <span className="order-details-titleD">Order Details</span>
        <Link to={`/smorderrep/${id}`}>
          <button className="generate-report-buttonD">Generate Report</button>
        </Link>
        {loading ? (
          <p className="loading-text">Loading order details...</p>
        ) : error ? (
          <p className="error-text">Error: {error.message}</p>
        ) : (
          <div className="order-details-contentD">
            <p className="order-infoD">Order ID: {smOrderDetails.orderId}</p>
            <p className="order-infoD">
              Site Manager: {smOrderDetails.siteManager}
            </p>
            <p className="order-infoD">Site Name: {smOrderDetails.siteName}</p>
            <p className="order-infoD">Status: {smOrderDetails.status}</p>
            <p className="order-infoD">
              Require Date: {formatDate(smOrderDetails.reqDate)}
            </p>
            <h3 className="items-titleD">Items:</h3>
            <ul className="item-listD">
              {smOrderDetails.items.map((item) => (
                <li key={item.id} className="itemD">
                  {item.itemName} (Price: {item.price}, Quantity:{" "}
                  {item.quantity}, Seller: {item.seller}, Total Price:{" "}
                  {item.totPrice})
                </li>
              ))}
            </ul>

            <button
              className="action-button accept-buttonD"
              onClick={() => editOrderStatus("Accepted")}
            >
              Accept
            </button>
            <button
              className="action-button reject-buttonD"
              onClick={() => editOrderStatus("Rejected")}
            >
              Reject
            </button>
            <button
              className="action-button place-buttonD"
              onClick={() => editOrderStatus("Placed")}
            >
              Place
            </button>
            <button
              className="action-button admin-buttonD"
              onClick={() => editOrderStatus("Pending Admin Approval")}
            >
              Admin Approval
            </button>
          </div>
        )}
      </div>
      <Footer />
    </div>
  );
}

export default SMOrderDetails;
