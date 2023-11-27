import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import OrderService from "../services/OrderService";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "./OrderList.css";

function formatRequestDate(date) {
  const requestDate = new Date(date);
  return requestDate.toISOString().split("T")[0]; // Extract and return the date part
}

function OrderList() {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [orders, setOrders] = useState(null);

  useEffect(() => {
    setLoading(true);

    // Fetch a list of orders
    OrderService.getOrders()
      .then((response) => {
        setOrders(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching orders: ", error);
        setLoading(false);
      });
  }, []); // The empty dependency array means this effect runs once when the component mounts

  return (
    <div>
      <Navbar />
      <div className="order-list-containerA">
        <button
          className="add-order-buttonA"
          onClick={() => navigate("/addorder")}
        >
          Add Orders
        </button>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <table className="order-list-tableA">
            <thead>
              <tr>
                <th className="order-id">Order Id</th>
                <th className="seller-name">Seller Name</th>
                <th className="site-name">Site Name</th>
                <th className="total-price">Total Price</th>
                <th className="request-date">Request Date</th>
                <th className="status">Status</th>
                <th className="view-order">View Order</th>
              </tr>
            </thead>
            <tbody>
              {orders &&
                orders.map((order) => (
                  <tr key={order.id} className="order-row">
                    <td>{order.orderId}</td>
                    <td>{order.supplierName}</td>
                    <td>{order.siteName}</td>
                    <td>{order.totalPrice}</td>
                    <td>{formatRequestDate(order.reqDate)}</td>{" "}
                    {/* Format the date */}
                    <td>{order.status}</td>
                    <td>
                      <button
                        className="view-button"
                        onClick={() => navigate(`/order/${order.id}`)}
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

export default OrderList;
