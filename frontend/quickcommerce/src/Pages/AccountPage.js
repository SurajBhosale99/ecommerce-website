import React from 'react';
import '../Css/AccountPage.css'; // Optional: Create a separate CSS file for styling if needed

const AccountPage = () => {
    const orders = ['Order #1', 'Order #2', 'Order #3'];
    const addresses = ['Address #1', 'Address #2', 'Address #3'];
    const faq = [
        'What is the return policy?',
        'How can I track my order?',
        'How do I update my address?'
    ];

    return (
        <div className="account-page">
            {/* Sidebar */}
            <div className="sidebar">
                <h4>Account</h4>
                <ul>
                    <li className="active">Orders</li>
                    <li>Saved Addresses</li>
                    <li>FAQ</li>
                    <li><button className="logout-btn">Logout</button></li>
                </ul>
            </div>

            {/* Main Content */}
            <div className="main-content">
                {/* Orders Section */}
                <div className="account-section">
                    <h3>Orders</h3>
                    <ul>
                        {orders.map((order, index) => (
                            <li key={index}>{order}</li>
                        ))}
                    </ul>
                </div>

                {/* Saved Addresses Section */}
                <div className="account-section">
                    <h3>Saved Addresses</h3>
                    <ul>
                        {addresses.map((address, index) => (
                            <li key={index}>{address}</li>
                        ))}
                    </ul>
                </div>

                {/* FAQ Section */}
                <div className="account-section">
                    <h3>FAQ</h3>
                    <ul>
                        {faq.map((item, index) => (
                            <li key={index}>{item}</li>
                        ))}
                    </ul>
                </div>
            </div>

            {/* Footer */}
            <div className="footer">
                &copy; 2025 Company Name. All rights reserved.
            </div>
        </div>
    );
};

export default AccountPage;