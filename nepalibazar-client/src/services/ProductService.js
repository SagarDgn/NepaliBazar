import api from "./api";

export default {
  // fetch products with optional pagination
  async getProducts(page = 0, size = 10) {
    try {
      const response = await api.get(`/product/get?page=${page}&size=${size}`);

      if (response.data.code !== "0") {
        console.error("Error fetching products:", response.data.message);
        return [];
      }

      const products = response.data.data || [];

      // Normalize fields for frontend
      return products.map(p => ({
        id: p.id,
        name: p.productName,
        description: p.aboutProduct,
        price: p.price,
        discount: p.discount,
        quantity: p.quantity,
        imageUrl: p.image,
        sellerName: p.sellerEntity?.sellerName || "",
         createdAt: p.createdAt ,
      }));
    } catch (error) {
      console.error("API error:", error);
      return [];
    }
  },
};
