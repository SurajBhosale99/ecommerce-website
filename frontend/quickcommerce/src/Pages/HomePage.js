import React from "react";

import { ScrollMenu } from "react-horizontal-scrolling-menu";
import "../Css/HomePage.css"; // Import HomePage CSS
import BestBrew from "../Components/BestBrew";

const categories = [
  "Atta,Dals,Rice & Oils", "Dry Fruits", "Sweet", "Packaged Food", "Bakery Biscuits", 
  "Books", "Beauty", "Tea,Coffee and Health Drink", "Groceries", "Health",
  "Furniture", "Gaming", "Jewelry", "Music", "Pet Supplies",
  "Stationery", "Tools", "Garden", "Watches", "Baby Products"
];

// Example product data with more products per category
const productsData = {
  "Atta,Dals,Rice & Oils": [
    { id: 1, name: "Basmati Rice", image: "https://via.placeholder.com/150", price: "$12" },
    { id: 2, name: "Organic Dal", image: "https://via.placeholder.com/150", price: "$8" },
    { id: 3, name: "Sunflower Oil", image: "https://via.placeholder.com/150", price: "$10" },
    { id: 4, name: "Whole Wheat Flour", image: "https://via.placeholder.com/150", price: "$7" },
    { id: 5, name: "Olive Oil", image: "https://via.placeholder.com/150", price: "$15" },
    { id: 6, name: "Sugar", image: "https://via.placeholder.com/150", price: "$5" },
    { id: 7, name: "Salt", image: "https://via.placeholder.com/150", price: "$3" },
    { id: 8, name: "Spices", image: "https://via.placeholder.com/150", price: "$10" },
    { id: 9, name: "Rice Flour", image: "https://via.placeholder.com/150", price: "$4" },
    { id: 10, name: "Pulses", image: "https://via.placeholder.com/150", price: "$6" },
    { id: 8, name: "Spices", image: "https://via.placeholder.com/150", price: "$10" },
    { id: 9, name: "Rice Flour", image: "https://via.placeholder.com/150", price: "$4" },
    { id: 10, name: "Pulses", image: "https://via.placeholder.com/150", price: "$6" }   
  ],
  "Dry Fruits": [
    { id: 1, name: "Almonds", image: "https://via.placeholder.com/150", price: "$20" },
    { id: 2, name: "Cashews", image: "https://via.placeholder.com/150", price: "$22" },
    { id: 3, name: "Pistachios", image: "https://via.placeholder.com/150", price: "$25" },
    { id: 4, name: "Dates", image: "https://via.placeholder.com/150", price: "$12" },
    { id: 5, name: "Raisins", image: "https://via.placeholder.com/150", price: "$8" },
    { id: 6, name: "Walnuts", image: "https://via.placeholder.com/150", price: "$18" }
  ],
  // Continue adding more products in each category as needed...
};


const howItWorksSteps = [
  { id: 1, title: "Browse Products", description: "Explore a wide range of categories and select your favorite products.", icon: "🛍️" },
  { id: 2, title: "Add to Cart", description: "Easily add items to your cart and proceed to checkout.", icon: "🛒" },
  { id: 3, title: "Secure Payment", description: "Use secure payment options to complete your purchase.", icon: "💳" },
  { id: 4, title: "Fast Delivery", description: "Get your items delivered quickly to your doorstep.", icon: "🚚" },
];

const HomePage = () => {
  return (
    <div className="homepage">
     
      <div className="home-content">
        <h1>Welcome to Our E-Commerce Store!</h1>
        <p>Shop the latest products at great prices!</p>
        
        {/* Categories Section */}
        <div className="categories-container">
          {categories.map((category, index) => (
            <div key={index} className="category-item">
              {category}
            </div>
          ))}
        </div>

        {/* Iterate through categories and render products */}
        {Object.keys(productsData).map((category) => (
          <div className="products-section" key={category}>
            <h2>{category}</h2>

            {/* Horizontal Scroll Menu for Products */}
            <ScrollMenu>
              <div className="scroll-menu-container">
                {productsData[category].map((product) => (
                  <div key={product.id} className="product-item">
                    <img src={product.image} alt={product.name} />
                    <p>{product.name}</p>
                    <span>{product.price}</span>
                  </div>
                ))}
              </div>
            </ScrollMenu>
          </div>
        ))}

    <BestBrew /> 
        <div className="how-it-works">
          <h2>How It Works</h2>
          <div className="how-it-works-grid">
            {howItWorksSteps.map((step) => (
              <div key={step.id} className="how-it-works-item">
                <span className="icon">{step.icon}</span>
                <h3>{step.title}</h3>
                <p>{step.description}</p>
              </div>
            ))}
          </div>
        </div>
        
      </div>

      
     
    </div>
  );
};

export default HomePage;