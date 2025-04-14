import React from "react";
import Carousel from "react-bootstrap/Carousel";
import "../Css/Carousel.css"; // Make sure to import the Carousel styles
import freshFruit from "../Images/freshfruit.png";
import freshVeg from "../Images/freshVeg.jpeg";
import bambooProduct from "../Images/bambooProduct.webp";


function Slids() {
  return (
    <Carousel data-bs-theme="dark">
      <Carousel.Item>
        <img
          className="d-block w-100"
          src={freshFruit}
          alt="First slide"
        />
        
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src={freshVeg}
          alt="Second slide"
        />
        
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src={bambooProduct}
          alt="Third slide"
        />
        
      </Carousel.Item>
    </Carousel>
  );
}

export default Slids;