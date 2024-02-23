import axios from "axios";

export const backendBaseURL = "http://localhost:8180/api/v1";

export const dataService = {
  getVehicles: () => axios.get(`${backendBaseURL}/vehicles`),
  getVehicleById: (id) => axios.get(`${backendBaseURL}/vehicles/${id}`),
  createVehicle: (data) => axios.post(`${backendBaseURL}/vehicles`, data),
  updateVehicle: (id, data) => axios.put(`${backendBaseURL}/vehicles/${id}`, data),
  deleteVehicle: (id) => axios.delete(`${backendBaseURL}/vehicles/${id}`),

  getAgencies: () => axios.get(`${backendBaseURL}/agencies`),
  getAgencyById: (id) => axios.get(`${backendBaseURL}/agencies/${id}`),
  createAgency: (data) => axios.post(`${backendBaseURL}/agencies`, data),
  updateAgency: (id, data) => axios.put(`${backendBaseURL}/agencies/${id}`, data),
  deleteAgency: (id) => axios.delete(`${backendBaseURL}/agencies/${id}`),

  getUsers: () => axios.get(`${backendBaseURL}/users`),
  loginUser: (username, password) => axios.get(`${backendBaseURL}/users/login/${username}/${password}`),
  getUserById: (id) => axios.get(`${backendBaseURL}/users/${id}`),
  createUser: (data) => axios.post(`${backendBaseURL}/users`, data),
  registerUser: (data) => axios.post(`${backendBaseURL}/users/register`, data),
  updateUser: (id, data) => axios.put(`${backendBaseURL}/users/updateUser/${id}`, data),
  deleteUser: (id) => axios.delete(`${backendBaseURL}/users/deleteUser/${id}`),

  subscribeToNewsletter: (email) => axios.post(`${backendBaseURL}/emailsender/subscribe?email=${email}`),
  
};
