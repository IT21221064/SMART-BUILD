import axios from "axios";
const SMOrder_API_BASE_URL = "http://localhost:8080/api/v1/smorders";

class SMOrderService {
  saveSMOrder(smorders) {
    return axios.post(SMOrder_API_BASE_URL, smorders);
  }
  getSMOrders() {
    return axios.get(SMOrder_API_BASE_URL);
  }
  getSMOrderById(id) {
    const url = `http://localhost:8080/api/v1/smorders/${id}`;
    return axios.get(url);
  }
  updateSMOrderStatus(smorders, id) {
    const url = `http://localhost:8080/api/v1/smorders/${id}`;
    return axios.put(url, smorders); // Include the 'order' parameter here
  }
}

export default new SMOrderService();
