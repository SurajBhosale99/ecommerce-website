import React, { useState } from "react";
import "../Css/Categories.css";


const categories = {
  "Dairy & Bread": ["aslk", "Oats", "Butter", "Curd","jaskjd", "Oats", "bus", "Curd","ajshjska", "Oats", "yakul", "Curd","Suaskdh", "Oats", "cool cool", "Curd","ajksa", "Oats", "tea", "Curd","Chai","beer","Thandai"]
};

const productsData = {
  Milk: [
    {
      id: 1,
      name: "Fresh Milk",
      price: "$3",
      
    },
    {
      id: 2,
      name: "Fresh Milk",
      price: "$3",
     
    },
    {
      id: 3,
      name: "Fresh Milk",
      price: "$3",
      
    },
    {
      id: 4,
      name: "Fresh Milk",
      price: "$3",
      
    },
    {
      id: 5,
      name: "Fresh Milk",
      price: "$3",
      
    },
    {
      id: 6,
      name: "Fresh Milk",
      price: "$3",
      
    },
    {
      id: 7,
      name: "Fresh Milk",
      price: "$3",
     
    },
    {
      id: 8,
      name: "Fresh Milk",
      price: "$3",
      
    },
  ],
  Oats: [
    {
      id: 9,
      name: "Organic Oats",
      price: "$5",
      
    },
  ],
  Butter: [
    {
      id: 10,
      name: "Pure Butter",
      price: "$6",
     
    },
  ],
  Curd: [
    {
      id: 11,
      name: "Natural Curd",
      price: "$4",
     
    },
  ],
  Ored: [
    {
      id: 19,
      name: "Organic Oats",
      price: "$5",
    
    },
  ],
  Buddstter: [
    {
      id: 100,
      name: "Pure Butter",
      price: "$6",
      
    },
  ],
  Cuddsrd: [
    {
      id: 111,
      name: "Natural Curd",
      price: "$4",
     
    },
  ],
  dddsdf: [
    {
      id: 91,
      name: "Organic Oats",
      price: "$5",
      
    },
  ],
  sdf: [
    {
      id: 101,
      name: "Pure Butter",
      price: "$6",
      
    },
  ],
  Cudsdfrd: [
    {
      id: 211,
      name: "Natural Curd",
      price: "$4",
      
    },
  ],
  dsds: [
    {
      id: 92,
      name: "Organic Oats",
      price: "$5",
     
    },
  ],
  dsdsdsds: [
    {
      id: 103,
      name: "Pure Butter",
      price: "$6",
      
    },
  ],
  sddssd: [
    {
      id: 114,
      name: "Natural Curd",
      price: "$4",
      
    },
  ],
};


const CategoriesPage = () => {
  const [selectedCategory, setSelectedCategory] = useState("Dairy & Bread");
  const [selectedSubCategory, setSelectedSubCategory] = useState("Milk");
  
  return (
    <div className="categories-page">
      {/* Sidebar */}
      <div className="sidebar">
         {Object.keys(categories).map((category) => (
          <div key={category}>
            <h4 onClick={() => setSelectedCategory(category)}>{category}</h4>
            {selectedCategory === category && (
              <ul>
                {categories[category].map((sub) => (
                  <li key={sub} onClick={() => setSelectedSubCategory(sub)}  className={selectedSubCategory === sub ? "active-subcategory" : ""}>
                    {sub}
                  </li>
                ))}
              </ul>
            )}
          </div>
        ))}
      </div>

      {/* Main Content */}
<div className="main-content">
  <h2>{selectedSubCategory} Products</h2>
  <div className="product-list">
  {productsData[selectedSubCategory].map((product) => (
    <div key={product.id} className="product-card">
      <img src={product.image} alt={product.name} className="product-image" />
      <div className="product-details">
        <p className="product-name">{product.name}</p>
        <div className="product-info">
          <span className="product-quantity">Quantity: 1</span> {/* Assuming quantity is 1 */}
          <span className="product-price">{product.price}</span>
        </div>
      </div>
      <button className="add-to-cart">Add to Cart</button>
    </div>
  ))}
</div>
</div>
    </div>
  );
};

export default CategoriesPage;
