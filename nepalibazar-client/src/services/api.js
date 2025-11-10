import axios from "axios";

const api= axios.create({
    baseURL: "http://localhost:8080/api/v1",
    headers:{
        "Content-Type": "application/json"
    }
});

api.interceptors.request.use(config => {
    const buyerToken = localStorage.getItem('buyer_jwt');
    const sellerToken = sessionStorage.getItem('seller_jwt');
    const role = localStorage.getItem('buyer_role') || sessionStorage.getItem('seller_role');

    // Only set the correct token based on role
    if (role === 'BUYER' && buyerToken) {
        config.headers.Authorization = `Bearer ${buyerToken}`;
    } else if (role === 'SELLER' && sellerToken) {
        config.headers.Authorization = `Bearer ${sellerToken}`;
    }

    return config;
});

export default api;