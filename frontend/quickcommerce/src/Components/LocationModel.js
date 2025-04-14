import React, { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import "../Css/LocationModal.css";

const LocationModal = ({ show, handleClose }) => {
  const [searchLocation, setSearchLocation] = useState("");
  const [currentLocation, setCurrentLocation] = useState(null);
  const [locationError, setLocationError] = useState("");
  const [locationEnabled, setLocationEnabled] = useState(false); // New state to track if location was enabled

  // Function to get user's current location
  const getCurrentLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const { latitude, longitude } = position.coords;
          setCurrentLocation(`Lat: ${latitude}, Lon: ${longitude}`);
          setLocationError(""); // Clear previous errors
          setLocationEnabled(true); // Mark location as enabled
        },
        () => {
          setLocationError("Unable to fetch location. Please enable location access.");
          setCurrentLocation(null);
          setLocationEnabled(false); // Reset location enabled state on error
        }
      );
    } else {
      setLocationError("Geolocation is not supported by this browser.");
      setLocationEnabled(false); // Mark location as disabled if geolocation is not supported
    }
  };

  return (
    <Modal
      className="location-modal"
      show={show}
      onHide={handleClose}
      centered
      size="lg"
      keyboard={true} // Close on ESC key
    >
      <Modal.Body className="location-modal-body">
        {/* Title */}
        <h1 className="location-modal-title">Your Location</h1>
        <hr />

        {/* Search Bar */}
        <Form>
          <Form.Group>
            <Form.Control
              type="text"
              className="location-search"
              placeholder="Search for a location..."
              value={searchLocation}
              onChange={(e) => setSearchLocation(e.target.value)}
              aria-label="Search location"
            />
          </Form.Group>
        </Form>

        {/* Enable Location Section */}
        <div className="enable-location-container">
          <p className="enable-location-text">
            Enable your current location for better service
          </p>
          <Button className="enable-btn" onClick={getCurrentLocation}>
            Enable
          </Button>
        </div>

        {/* Display Current Location or Error Message */}
        {locationEnabled && currentLocation && <p className="text-success">{currentLocation}</p>}
        {locationError && <p className="text-danger">{locationError}</p>}
      </Modal.Body>
    </Modal>
  );
};

export default LocationModal;