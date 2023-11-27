import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import OrderService from "../services/OrderService";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "./AddOrders.css";

function AddOrders() {
  const navigate = useNavigate();

  const [order, setOrder] = useState({
    orderId: "",
    siteName: "",
    supplierName: "",
    reqDate: "",
    siteAddress: "",
    orderDescription: "",
    totalPrice: "",
  });

  const [validationError, setValidationError] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setOrder({
      ...order,
      [name]: value,
    });
  };

  const validateInput = () => {
    if (
      !order.orderId ||
      !order.siteName ||
      !order.supplierName ||
      !order.reqDate ||
      !order.siteAddress ||
      !order.orderDescription ||
      !order.totalPrice
    ) {
      setValidationError("Please fill in all fields.");
      return false;
    } else if (isNaN(order.totalPrice)) {
      setValidationError("Total Price must be a valid number.");
      return false;
    }
    return true;
  };

  const saveOrder = (e) => {
    e.preventDefault();
    setValidationError("");

    if (validateInput()) {
      const orderWithStatus = { ...order, status: "Pending" };

      OrderService.saveOrder(orderWithStatus)
        .then((response) => {
          console.log(response);
          navigate("/");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <div>
      <Navbar />
      <div className="add-orders-containerB">
        <form onSubmit={saveOrder} className="order-formB">
          <span className="add-orders-headingB">Add Orders</span>

          <label className="form-labelB">Order Id</label>
          <input
            type="text"
            name="orderId"
            value={order.orderId}
            onChange={handleChange}
            className="form-inputB"
            required
          />
          <label className="form-labelB">Site Name</label>
          <input
            type="text"
            name="siteName"
            value={order.siteName}
            onChange={handleChange}
            className="form-inputB"
            required
          />
          <label className="form-labelB">Supplier Name</label>
          <input
            type="text"
            name="supplierName"
            value={order.supplierName}
            onChange={handleChange}
            className="form-inputB"
            required
          />
          <label className="form-labelB">Requesting Date</label>
          <input
            type="Date"
            name="reqDate"
            value={order.reqDate}
            onChange={handleChange}
            className="form-inputB"
            required
          />
          <label className="form-labelB">Site Address</label>
          <input
            type="text"
            name="siteAddress"
            value={order.siteAddress}
            onChange={handleChange}
            className="form-inputB"
            required
          />
          <label className="form-labelB">Order Description</label>
          <textarea
            name="orderDescription"
            value={order.orderDescription}
            onChange={handleChange}
            className="form-inputB"
            required
          ></textarea>
          <label className="form-labelB">Total Price</label>
          <input
            type="text"
            name="totalPrice"
            value={order.totalPrice}
            onChange={handleChange}
            className="form-inputB"
            required
          />
          {validationError && (
            <div className="error-message">{validationError}</div>
          )}
          <br />
          <button type="submit" className="place-order-buttonB">
            Place Order
          </button>
        </form>
      </div>
      <Footer />
    </div>
  );
}

export default AddOrders;
