import React, { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import "../Css/Login.css";
import googlePlay from "../Images/google-play.png";
import appStore from "../Images/app-store.png";
import { apiService } from "../APIs/ApiService";

function Login({ showModal, setShowModal }) {
  const [mobile, setMobile] = useState("");
  const [otp, setOtp] = useState("");
  const [name, setName] = useState("");
  const [step, setStep] = useState(1);

  const sendOtp = async () => {
    try {
        if (mobile.length === 10) {
            const response = await apiService.login(mobile); // This should be awaited
            console.log(response); // Process the response as needed (for example, show success message)

            alert("OTP sent! (Use 1234)");
            setStep(2);
        } else {
            alert("Enter a valid 10-digit mobile number");
        }
    } catch (error) {
        alert("Something went wrong while sending OTP. Please try again.");
    }
};

  const verifyOtp = () => {
    if (otp === "1234") {
      setStep(3);
    } else {
      alert("Invalid OTP. Try again!");
    }
  };

  const handleSubmit = () => {
    if (name.trim() === "") {
      alert("Please enter your name");
    } else {
      alert(`Welcome, ${name}!`);
      clearForm();
      setShowModal(false);
    }
  };

  const clearForm = () => {
    setMobile("");
    setOtp("");
    setName("");
    setStep(1);
  };

  const handleMobileChange = (e) => {
    const value = e.target.value;
    const numericValue = value.replace(/[^0-9]/g, "");
    if (numericValue.length <= 10) {
      setMobile(numericValue);
    }
  };

  return (
    <Modal show={showModal} onHide={() => { clearForm(); setShowModal(false); }} centered>

      <Modal.Header closeButton>
        <Modal.Title>Login/Sign Up</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        {step === 1 && (
          <>
            <Form.Group controlId="mobile">
              <div className="input-group-custom">
                <span className="country-code">+91</span>
                <Form.Control
                  type="text"
                  placeholder="Enter Mobile Number"
                  value={mobile}
                  onChange={handleMobileChange}
                  maxLength={10}
                  className="input-field"
                />
              </div>
            </Form.Group>
            <Button
              variant="primary"
              className="mt-3"
              onClick={sendOtp}
              disabled={mobile.length !== 10}
            >
              Send OTP
            </Button>
          </>
        )}

        {step === 2 && (
          <>
            <Form.Group controlId="otp">
              <Form.Control
                type="text"
                placeholder="Enter OTP"
                value={otp}
                onChange={(e) => setOtp(e.target.value)}
              />
            </Form.Group>
            <Button variant="success" className="mt-3" onClick={verifyOtp}>
              Verify OTP
            </Button>
          </>
        )}

        {step === 3 && (
          <>
            <Form.Group controlId="name">
              <Form.Control
                type="text"
                placeholder="Your Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </Form.Group>
            <Button variant="success" className="mt-3" onClick={handleSubmit}>
              Submit
            </Button>
          </>
        )}

        {/* Download Our App Section */}
        <div className="app-download mt-4 text-center">
          <p className="mb-2">Get our app for a better shopping experience:</p>
          <div className="d-flex justify-content-center">
            <a
              href="https://play.google.com/store/apps/details?id=your.android.app"
              target="_blank"
              rel="noopener noreferrer"
            >
              <img
                src={googlePlay}
                alt="Download on Google Play"
                className="app-store-btn"
              />
            </a>
            <a
              href="https://apps.apple.com/app/your-ios-app"
              target="_blank"
              rel="noopener noreferrer"
            >
              <img
                src={appStore}
                alt="Download on App Store"
                className="app-store-btn ms-2"
              />
            </a>
          </div>
        </div>
      </Modal.Body>
    </Modal>
  );
}

export default Login;
