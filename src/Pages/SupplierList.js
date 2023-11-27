import React, { useState, useEffect } from "react";
import SupplierService from "../services/SupplierService";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "./SupplierList.css";

function SupplierList() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [suppliers, setSuppliers] = useState(null);
  useEffect(() => {
    setLoading(true);

    // Fetch a list of orders
    SupplierService.getSuppliers()
      .then((response) => {
        setSuppliers(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching orders: ", error);
        setLoading(false);
      });
  }, []);

  return (
    <div>
      <Navbar />
      <div className="supplier-list-containerF">
        <br />
        <button
          className="add-supplier-buttonF"
          onClick={() => navigate("/addsupplier")}
        >
          Add Supplier
        </button>
        {loading ? (
          <p className="loading-text">Loading...</p>
        ) : (
          <table className="supplier-tableF">
            <thead>
              <tr>
                <th className="header-cellF">Supplier Name</th>
                <th className="header-cellF">Supplier Phone</th>
                <th className="header-cellF">Supplier Address</th>
                <th className="header-cellF">Actions</th>
              </tr>
            </thead>
            <tbody>
              {suppliers &&
                suppliers.map((supplier) => (
                  <tr key={supplier.id} className="data-rowF">
                    <td className="data-cellF">{supplier.supName}</td>
                    <td className="data-cellF">{supplier.supPhone}</td>
                    <td className="data-cellF">{supplier.supAddress}</td>
                    <td className="data-cellF">
                      <button
                        className="action-buttonF"
                        onClick={() => navigate(`/supplier/${supplier.id}`)}
                      >
                        Update
                      </button>
                      <button
                        className="action-buttonDF"
                        onClick={() => navigate(`/supplier/${supplier.id}`)}
                      >
                        Delete
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

export default SupplierList;
