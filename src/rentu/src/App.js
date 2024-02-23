import React, { useState } from "react";
import {BrowserRouter as Router, Link, Route, Routes} from "react-router-dom";
import Navigation from "./components/Navigation/Navigation.jsx";
import Home from "./components/Home/Home.jsx";
import Agencies from "./components/Agencies/Agencies.jsx";
import Vehicles from "./components/Vehicles/Vehicles.jsx";
import Users from "./components/Users/Users.jsx";
import EmailSender from "./components/EmailSender/EmailSender.jsx";

const App = () => {
  const [loggedInUser, setLoggedInUser] = useState(undefined);

  return (
    <div>
      <Router>
        <Navigation />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/vehicles" element={<Vehicles />} />
          <Route path="/agencies" element={<Agencies />} />
          <Route path="/users" element={<Users loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />} />
          <Route path="/emailsender" element={<EmailSender />} />
        </Routes>
      </Router>
    </div>
  );
};

export default App;
