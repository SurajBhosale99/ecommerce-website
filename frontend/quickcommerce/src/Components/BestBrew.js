import React, { useRef } from "react";
import { FaChevronLeft, FaChevronRight } from "react-icons/fa";
import "../Css/BestBrew.css"; // Import the CSS file

const BestBrew = () => {
  const scrollRef = useRef(null);

  const scrollLeft = () => {
    if (scrollRef.current) {
      scrollRef.current.scrollBy({ left: -200, behavior: "smooth" });
    }
  };

  const scrollRight = () => {
    if (scrollRef.current) {
      scrollRef.current.scrollBy({ left: 200, behavior: "smooth" });
    }
  };

  const coffeeOptions = [
    { id: 1, name: "Espresso", image: "/images/espresso.jpg" },
    { id: 2, name: "Latte", image: "/images/latte.jpg" },
    { id: 3, name: "Cappuccino", image: "/images/cappuccino.jpg" },
    { id: 4, name: "Americano", image: "/images/americano.jpg" },
    { id: 5, name: "Mocha", image: "/images/mocha.jpg" },
    { id: 6, name: "Mocha", image: "/images/mocha.jpg" },
    { id: 7, name: "Mocha", image: "/images/mocha.jpg" },
    { id: 8, name: "Mocha", image: "/images/mocha.jpg" },
  ];

  return (
    <div className="best-brew-container">
      {/* Left Side - Best Brew */}
      <div className="best-brew">
        <h2>Best Brew</h2>
        <img src="/images/best-brew.jpg" alt="Best Brew" />
        <p>Discover our best-selling brew, crafted for a rich aroma and smooth taste.</p>
      </div>

      {/* Right Side - Coffee Scroll */}
      <div className="coffee-scroll-container">
        <h2 className="coffee-scroll-title">Our Coffees</h2>

        <button className="scroll-button left-scroll" onClick={scrollLeft}>
          <FaChevronLeft />
        </button>

        <div ref={scrollRef} className="coffee-list">
          {coffeeOptions.map((coffee) => (
            <div key={coffee.id} className="coffee-item">
              <img src={coffee.image} alt={coffee.name} />
              <h3>{coffee.name}</h3>
            </div>
          ))}
        </div>

        <button className="scroll-button right-scroll" onClick={scrollRight}>
          <FaChevronRight />
        </button>
      </div>
    </div>
  );
};

export default BestBrew;