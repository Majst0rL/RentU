import React from "react";
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const NavContainer = styled.nav`
  background-color: #333;
  padding: 15px;
  display: flex;
  justify-content: space-around;
`;

const NavLink = styled(Link)`
  text-decoration: none;
  color: #fff;
  padding: 10px;
  border-radius: 5px;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #555;
  }
`;

export default function Navigation() {
  return (
    <NavContainer>
      <NavLink to="/">Home</NavLink>
      <NavLink to="/vehicles">Vehicles</NavLink>
      <NavLink to="/agencies">Agencies</NavLink>
      <NavLink to="/users">Users</NavLink>
        <NavLink to="/posts">Posts</NavLink>
      <NavLink to="/emailsender">Newsletter</NavLink>
    </NavContainer>
  );
};
