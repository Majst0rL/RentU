import React from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Navigation from "./components/Navigation/Navigation.jsx";
import Home from "./components/Home/Home.jsx";
import Agencies from "./components/Agencies/Agencies.jsx";
import Vehicles from "./components/Vehicles/Vehicles.jsx";

const App = () => {
  return (
    <div>
      <Navigation />
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/vehicles" element={<Vehicles />} />
          <Route path="/agencies" element={<Agencies />} />

          {/* Add other routes as needed */}
        </Routes>
      </Router>
    </div>
  );
};

export default App;
