import React, { useState, useEffect, useRef } from "react";
import { Navbar, Nav, Container, Form } from "react-bootstrap";
import { Link } from "react-router-dom";
import { FaSearch, FaUser, FaShoppingCart } from "react-icons/fa";
import Login from "./login";
import LocationModal from "./LocationModel"; // Import the Location Modal
import "../Css/Navbar.css";
import { Dropdown } from "react-bootstrap";

function CustomNavbar() {
  const [showLoginModal, setShowLoginModal] = useState(false);
  const [showLocationModal, setShowLocationModal] = useState(false);
  const [searchQuery, setSearchQuery] = useState("");

  const searchSuggestions = useRef([
    "Laptops",
    "Mobile Phones",
    "Headphones",
    "Smart Watches",
    "Gaming Consoles",
    "Home Appliances",
    "Cameras",
  ]);

  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIndex(
        (prevIndex) => (prevIndex + 1) % searchSuggestions.current.length
      );
    }, 3000); // Change suggestion every 3 seconds

    return () => clearInterval(interval);
  }, []);

  const handleSearch = (e) => {
    e.preventDefault();
    alert(`Searching for: ${searchQuery}`);
  };

  return (
    <>
      <Navbar expand="lg" className="navbar-custom">
        <Container fluid>
          {/* Brand Section */}
          <Navbar.Brand className="brand-name d-flex justify-content-start">
            <Link to="/" className="brand-info d-flex justify-content-start">
              <span className="brand-text">Brand</span>
              <div className="delivery-info d-flex flex-column align-items-start">
                <span className="delivery-time">Delivery in 30 min</span>
                {/* Select Location Trigger */}
                <span
                  className="select-location"
                  onClick={() => setShowLocationModal(true)}
                  style={{ cursor: "pointer", color: "blue" }}
                >
                  Select Location
                </span>
              </div>
            </Link>
          </Navbar.Brand>

          {/* Search Bar */}
          <Form className="search-bar" onSubmit={handleSearch}>
            <div className="search-container">
              <div className="search-icon-wrapper">
                <FaSearch className="search-icon" onClick={handleSearch} />
              </div>

              {/* Rolling Placeholder */}
              {!searchQuery && (
                <div className="search-placeholder rolling-text">
                  {`Search for ${searchSuggestions.current[currentIndex]}`}
                </div>
              )}

              <Form.Control
                type="text"
                className="search-input"
                placeholder=""
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
              />
            </div>
          </Form>

          <Dropdown className="account-dropdown">
            <Dropdown.Toggle variant="secondary" id="dropdown-basic">
              Account
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item as={Link} to="/my-orders">
                My Orders
              </Dropdown.Item>
              <Dropdown.Item as={Link} to="/saved-addresses">
                Saved Addresses
              </Dropdown.Item>
              <Dropdown.Item as={Link} to="/e-gift-cards">
                E-Gift Cards
              </Dropdown.Item>
              <Dropdown.Item as={Link} to="/faq">
                FAQ
              </Dropdown.Item>
              <Dropdown.Divider />
              <Dropdown.Item as={Link} to="/logout">
                Log Out
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
         
          <Nav className="ms-auto navbar-icons">
            {/* Login Icon */}
            <div className="navbar-icon-item">
              <FaUser
                onClick={() => setShowLoginModal(true)}
                className="navbar-icon"
              />
              <span>Login</span>
            </div>

            {/* Cart Icon */}
            <div className="navbar-icon-item">
              <FaShoppingCart className="navbar-icon" />
              <span>Cart</span>
            </div>
          </Nav>
        </Container>
      </Navbar>

      {/* Modals */}
      <Login showModal={showLoginModal} setShowModal={setShowLoginModal} />
      <LocationModal
        show={showLocationModal}
        handleClose={() => setShowLocationModal(false)}
      />
    </>
  );
}

export default CustomNavbar;
