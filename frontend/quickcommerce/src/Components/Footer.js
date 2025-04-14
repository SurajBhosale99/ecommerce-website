import React from "react";
import { Link } from "react-router-dom";
import "../Css/Footer.css";
import { FaFacebook, FaInstagram, FaTwitter ,FaLinkedin} from "react-icons/fa";
import googlePlay from "../Images/google-play.png";
import appStore from "../Images/app-store.png";

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-container">
        {/* Logo & About */}
        <div className="footer-section about">
          <h2>QuickCommerce</h2>
          <p>Your one-stop destination for fresh products and sustainable goods.</p>
        </div>

        {/* Quick Links */}
        <div className="footer-section links">
          <h3>Quick Links</h3>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/account">My Account</Link></li>
            <li><Link to="/faq">FAQs</Link></li>
            <li><Link to="/contact">Contact Us</Link></li>
          </ul>
        </div>
      </div>

      {/* Copyright & Download App & Social Media */}
      <div className="footer-bottom">
      <p className="footer-copy">&copy; {new Date().getFullYear()} QuickCommerce. All Rights Reserved.</p>


        {/* Download App Section */}
        <div className="download-app">
          <p>Download App</p>
          <div className="app-store-links">
            <a href="https://play.google.com" target="_blank" rel="noopener noreferrer">
              <img src={googlePlay} alt="Download on Google Play" className="app-store-icon" />
            </a>
            <a href="https://apps.apple.com" target="_blank" rel="noopener noreferrer">
              <img src={appStore} alt="Download on the App Store" className="app-store-icon" />
            </a>
          </div>
        </div>

        {/* Social Media */}
        <div className="social-icons">
          <a href="https://facebook.com" target="_blank" rel="noopener noreferrer"><FaFacebook /></a>
          <a href="https://instagram.com" target="_blank" rel="noopener noreferrer"><FaInstagram /></a>
          <a href="https://twitter.com" target="_blank" rel="noopener noreferrer"><FaTwitter /></a>
          <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer"><FaLinkedin /></a>

        </div>
      </div>
    </footer>
  );
}

export default Footer;