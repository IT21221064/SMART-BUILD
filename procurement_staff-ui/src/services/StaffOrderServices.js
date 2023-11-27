import axios from "axios";
const Stafforder_API_BASE_URL = "http://localhost:8080/api/v1/approvals";

class StaffService {
  saveOrder(approvals) {
    return axios.post(Stafforder_API_BASE_URL, approvals);
  }
  getAllOrders() {
    return axios.get(Stafforder_API_BASE_URL);
  }
  getStaffOrderById(id) {
    const url = `http://localhost:8080/api/v1/approvals/${id}`;
    return axios.get(url);
  }
  updateStaffOrder(stafforders, id) {
    const url = `http://localhost:8080/api/v1/approvals/${id}`;
    return axios.put(url, stafforders); // Include the 'order' parameter here
  }
}

export default new StaffService();
