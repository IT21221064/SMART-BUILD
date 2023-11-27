import React, { useState, useEffect, useRef } from "react"; // Import useRef
import { useNavigate, useParams } from "react-router-dom";
import SMOrderService from "../services/SMOrderService";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "./SMOrderReport.css";

import { useReactToPrint } from "react-to-print";

function SMOrderReport() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [smOrderDetails, setSMOrderDetails] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Define the componentPDF ref
  const componentPDF = useRef();

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

  // Function to format date to display only the date part (yyyy-mm-dd)
  const formatDate = (date) => {
    const options = { year: "numeric", month: "2-digit", day: "2-digit" };
    return date.toLocaleDateString(undefined, options);
  };
  const generatePDF = useReactToPrint({
    content: () => componentPDF.current,
    documentTitle: "Stock Report",
    onAfterPrint: () => alert("Data saved in PDF"),
  });
  const getCurrentDate = () => {
    const currentDate = new Date();
    return `${currentDate.toDateString()} ${currentDate.toLocaleTimeString()}`;
  };

  return (
    <div>
      <Navbar />
      <React.Fragment>
        <button className="btn-success" onClick={generatePDF}>
          PDF/Download
        </button>
        <div className="rporder-details-containerD">
          <div ref={componentPDF}>
            {loading ? (
              <p className="loading-text">Loading order details...</p>
            ) : error ? (
              <p className="error-text">Error: {error.message}</p>
            ) : (
              <div className="rporder-details-contentD">
                <h4>Smart BuilD Pvt Limited</h4>
                <p>Build Easy With Us</p>
                <p>No. 551, Mihindu Mawatha, Malabe, Sri Lanka</p>
                <p>Email: SmartBuildinternational@gmail.com</p>
                <p className="report-date">Report Date: {getCurrentDate()}</p>
                <hr />
                <p className="rporder-infoD">
                  Order ID: {smOrderDetails.orderId}
                </p>
                <p className="rporder-infoD">
                  Site Manager: {smOrderDetails.siteManager}
                </p>
                <p className="rporder-infoD">
                  Site Name: {smOrderDetails.siteName}
                </p>
                <p className="rporder-infoD">Status: {smOrderDetails.status}</p>
                <p className="rporder-infoD">
                  Require Date: {formatDate(smOrderDetails.reqDate)}
                </p>
                <h3 className="rpitems-titleD">Items:</h3>
                <ul className="rpitem-listD">
                  {smOrderDetails.items.map((item) => (
                    <li key={item.id} className="rpitemD">
                      {item.itemName} (Price: {item.price}, Quantity:{" "}
                      {item.quantity}, Seller: {item.seller}, Total Price:{" "}
                      {item.totPrice})
                    </li>
                  ))}
                </ul>
              </div>
            )}
          </div>
        </div>
      </React.Fragment>
      <Footer />
    </div>
  );
}

export default SMOrderReport;
