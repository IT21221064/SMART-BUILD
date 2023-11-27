import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"; // Import useNavigate
import OrderService from "../services/OrderService";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "./OrderDetails.css";

function OrderDetails() {
  const navigate = useNavigate(); // Get the navigate function
  const { id } = useParams(); // Extract the orderId from the URL
  const [orderDetails, setOrderDetails] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);

    // Fetch order details using the orderId
    OrderService.getOrderById(id)
      .then((response) => {
        setOrderDetails(response.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err);
        setLoading(false);
      });
  }, [id]);

  const editOrderStatus = (newStatus) => {
    // Create a copy of the order details with the new status
    const updatedOrder = { ...orderDetails, status: newStatus };

    // Make an API request to update the order status
    OrderService.updateOrderStatus(updatedOrder, id)
      .then((updatedOrder) => {
        // Order status updated successfully
        setOrderDetails(updatedOrder);
        // Navigate to the OrderList page
        navigate("/");
      })
      .catch((err) => {
        // Handle errors here
        console.error("Error updating order status: ", err);
      });
  };

  return (
    <div>
      <Navbar />
      <div className="order-details-containerE">
        <h2 className="order-details-titleE">Order Details</h2>
        {loading ? (
          <p className="loading-text">Loading order details...</p>
        ) : error ? (
          <p className="error-text">Error: {error.message}</p>
        ) : (
          <div className="order-details-contentE">
            <p className="order-infoE">Order ID: {orderDetails.orderId}</p>
            <p className="order-infoE">Site Name: {orderDetails.siteName}</p>
            <p className="order-infoE">
              Supplier Name: {orderDetails.supplierName}
            </p>
            <p className="order-infoE">
              Requesting Date: {orderDetails.reqDate}
            </p>
            <p className="order-infoE">
              Site Address: {orderDetails.siteAddress}
            </p>
            <p className="order-infoE">
              Order Description: {orderDetails.orderDescription}
            </p>
            <p className="order-infoE">
              Total Price: {orderDetails.totalPrice}
            </p>
            <p className="order-infoE">Status: {orderDetails.status}</p>
          </div>
        )}
      </div>
      <Footer />
    </div>
  );
}

export default OrderDetails;
