const BASE_URL = "http://localhost:8087"; // Your Spring Boot backend URL

export const apiService = {
    // Login API
    login: async (mobile) => {
        try {
            const response = await fetch(`${BASE_URL}/api/user/generate`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ mobile }),
            });
            
            if (!response.ok) {
                throw new Error(`Failed to login: ${response.statusText}`);
            }

            return response.json();
        } catch (error) {
            console.error("Login error:", error);
            throw error;
        }
    },

    // Verify OTP API
    verifyOtp: async (mobile, otp) => {
        const response = await fetch(`${BASE_URL}/api/verify`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ mobile, otp }),
        });
        return response.json();
    },

    // Get Products API
    getProducts: async () => {
        const response = await fetch(`${BASE_URL}/api/products`, {
            method: "GET",
        });
        return response.json();
    },

    // Add to Cart API
    addToCart: async (productId) => {
        const response = await fetch(`${BASE_URL}/api/cart`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ productId }),
        });
        return response.json();
    },
};