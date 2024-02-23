import axios from "axios";

export const backendBaseURL = "http://localhost:8180/api/v1";

export const dataService = {
  //Vehicles
  getVehicles: () => axios.get(`${backendBaseURL}/vehicles`),
  getVehicleById: (id) => axios.get(`${backendBaseURL}/vehicles/${id}`),
  createVehicle: (data) => axios.post(`${backendBaseURL}/vehicles`, data),
  updateVehicle: (id, data) => axios.put(`${backendBaseURL}/vehicles/${id}`, data),
  deleteVehicle: (id) => axios.delete(`${backendBaseURL}/vehicles/${id}`),
  getFilteredVehicles: (powerMoreThan, pricePerDayLessThan, builtYearLessThan) => axios.get(`${backendBaseURL}/vehicles/powerMoreThan/${powerMoreThan}/pricePerDayLessThan/${pricePerDayLessThan}/builtYearLessThan/${builtYearLessThan}`),

  //Agencies
  getAgencies: () => axios.get(`${backendBaseURL}/agencies`),
  getAgencyById: (id) => axios.get(`${backendBaseURL}/agencies/${id}`),
  createAgency: (data) => axios.post(`${backendBaseURL}/agencies`, data),
  updateAgency: (id, data) => axios.put(`${backendBaseURL}/agencies/${id}`, data),
  deleteAgency: (id) => axios.delete(`${backendBaseURL}/agencies/${id}`),
  getVehiclesByAgency: (agencyId) => axios.get(`${backendBaseURL}/agencies/${agencyId}/vehicles`),


  //Users
  getUsers: () => axios.get(`${backendBaseURL}/users`),
  loginUser: (username, password) => axios.get(`${backendBaseURL}/users/login/${username}/${password}`),
  getUserById: (id) => axios.get(`${backendBaseURL}/users/${id}`),
  createUser: (data) => axios.post(`${backendBaseURL}/users`, data),
  registerUser: (data) => axios.post(`${backendBaseURL}/users/register`, data),
  updateUser: (id, data) => axios.put(`${backendBaseURL}/users/updateUser/${id}`, data),
  deleteUser: (id) => axios.delete(`${backendBaseURL}/users/deleteUser/${id}`),

  //Posts
  getAllPosts: () => axios.get(`${backendBaseURL}/posts/`),
  getPostById: (id) => axios.get(`${backendBaseURL}/posts/${id}`),
  createPost: (data) => axios.post(`${backendBaseURL}/posts/createPost`, data),
  updatePost: (id, postDetails) => axios.put(`${backendBaseURL}/posts/updatePost/${id}`, postDetails),
  deletePost: (id) => axios.delete(`${backendBaseURL}/posts/${id}`),
  findPostsByModelPriceCityAndAgency: (model, pricePerDay, city) => axios.get(`${backendBaseURL}/posts/findPostsByModelPriceCityAndAgency`, { params: { model, pricePerDay, city } }),
  findPostsByParameters: (params) => axios.get(`${backendBaseURL}/posts/findPostsByParameters`, { params }),
  findPostsByManufacturer: (manufacturer) => axios.get(`${backendBaseURL}/posts/findPostsByManufacturer`, { params: { manufacturer } }),
  findPostsByAgencyLocation: (city) => axios.get(`${backendBaseURL}/posts/findPostsByAgencyLocation`, { params: { city } }),
  findPostsByVehicleParameters: (params) => axios.get(`${backendBaseURL}/posts/findPostsByVehicleParameters`, { params }),
  subscribeToNewsletter: (email) => axios.post(`${backendBaseURL}/emailsender/subscribe?email=${email}`),
};
