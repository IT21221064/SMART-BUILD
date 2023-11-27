import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import SupplierService from "../services/SupplierService";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "./AddSupplier.css";

function AddSupplier() {
  const navigate = useNavigate();
  const [supplier, setSupplier] = useState({
    supName: "",
    supPhone: "",
    supAddress: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setSupplier({
      ...supplier,
      [name]: value,
    });
  };

  const saveSupplier = (e) => {
    e.preventDefault();

    // Assuming your SupplierService.saveSupplier method takes a parameter which is an object
    SupplierService.saveSupplier(supplier)
      .then((response) => {
        console.log(response);
        navigate("/"); // Navigate to the home page after adding the supplier
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div>
      <Navbar />
      <div className="add-supplier-containerG">
        <form onSubmit={saveSupplier} className="supplier-formG">
          <div className="add-supplier-titleG">Add Supplier</div>
          <br />
          <label className="form-labelG">Supplier Name</label>
          <input
            type="text"
            name="supName"
            value={supplier.supName}
            onChange={handleChange}
            className="form-inputG"
          />
          <br />
          <br />
          <label className="form-labelG">Supplier Phone</label>
          <input
            type="text"
            name="supPhone"
            value={supplier.supPhone}
            onChange={handleChange}
            className="form-inputG"
          />
          <br />
          <br />
          <label className="form-labelG">Supplier Address</label>
          <input
            type="text"
            name="supAddress"
            value={supplier.supAddress}
            onChange={handleChange}
            className="form-inputG"
          />
          <br />
          <br />
          <button type="submit" className="add-buttonG">
            Add Supplier
          </button>
        </form>
      </div>
      <Footer />
    </div>
  );
}

export default AddSupplier;
