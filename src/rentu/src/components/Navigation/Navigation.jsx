
import React from "react";
import { Link } from 'react-router-dom';

export default function Navigation() {
  return (
    <nav>
        <Link to="/">Home</Link>
        <Link to="/vehicles">Vehicles</Link>
        <Link to="/agencies">Agencies</Link>
        <Link to="/users">Users</Link>
        <Link to="/emailsender">Subscribe</Link>
    </nav>
  );
};
