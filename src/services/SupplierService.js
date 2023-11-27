import axios from "axios";
const Supplier_API_BASE_URL = "http://localhost:8080/api/v1/suppliers";


class SupplierService {
  saveSupplier(supplier) {
    return axios.post(Supplier_API_BASE_URL, supplier);
  }
  getSuppliers() {
    return axios.get(Supplier_API_BASE_URL);
  }
  updateSupplier(supplier, id) {
    const url = `http://localhost:8080/api/v1/supplier/${id}`;
    return axios.put(url, supplier); // Include the 'order' parameter here
  }
}

export default new SupplierService();
