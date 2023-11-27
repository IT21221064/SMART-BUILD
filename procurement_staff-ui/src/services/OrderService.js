import axios from "axios";
const Order_API_BASE_URL = "http://localhost:8080/api/v1/orders";

class OrderService {
  saveOrder(order) {
    return axios.post(Order_API_BASE_URL, order);
  }

  getOrders() {
    return axios.get(Order_API_BASE_URL);
  }

  getOrderById(id) {
    const url = `http://localhost:8080/api/v1/order/${id}`;
    return axios.get(url);
  }
  updateOrderStatus(order, id) {
    const url = `http://localhost:8080/api/v1/order/${id}`;
    return axios.put(url, order); // Include the 'order' parameter here
  }
}

export default new OrderService();
