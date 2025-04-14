import React from "react";
import { Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import HomePage from "../Pages/HomePage";
import CategoriesPage from "../Pages/CategoriesPage"; // Import the new page
import "../App.css";
import CustomNavbar from "./CustomNavbar";
import Footer from "./Footer";
import AccountPage from "../Pages/AccountPage";

function App() {
  return (
    <div>
      <CustomNavbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/categories" element={<CategoriesPage />} />
        <Route path="/Account" element={<AccountPage/>}/>
      </Routes>
      <Footer />
  </div>
  );
}

export default App;