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

   async getMyProducts() {
    try {
      const token = localStorage.getItem('seller_jwt');
      const cleanToken= token.trim();
      console.log("sending token",cleanToken)
      const response = await api.get('/my/products', {
        headers: {
          'Authorization': `Bearer ${cleanToken}`
        }
      });

      if (response.data.code !== "0") {
        console.error("Error fetching seller products:", response.data.message);
        return [];
      }

      const products = response.data.data || [];
      
      // Normalize product data for frontend
      return products.map(p => ({
        id: p.id,
        name: p.productName,
        description: p.aboutProduct,
        price: p.price,
        discount: p.discount,
        quantity: p.quantity,
        imageUrl: p.image,
        status: p.status || 'active',
        createdAt: p.createdAt,
        category: p.category || 'General'
      }));
    } catch (error) {
      console.error("API error fetching seller products:", error);
      throw new Error('Failed to fetch products');
    }
  },

   // Add new product
  async addProduct(productData) {
    try {
      const token = localStorage.getItem('seller_jwt');
      const response = await api.post('/product/add', productData, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      });

      if (response.data.code !== "0") {
        throw new Error(response.data.message);
      }

      return response.data.data;
    } catch (error) {
      console.error("API error adding product:", error);
      throw new Error('Failed to add product');
    }
  },

   // Update product
  async updateProduct(id, productData) {
    try {
      const token = localStorage.getItem('seller_jwt');
      const response = await api.put(`/product/update/${id}`, productData, {
        headers: {
          // 'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      });

      if (response.data.code !== "0") {
        throw new Error(response.data.message);
      }

      return response.data.data;
    } catch (error) {
      console.error("API error updating product:", error);
      throw new Error('Failed to update product');
    }
  },

    // Delete product
  async deleteProduct(id) {
    try {
      const token = localStorage.getItem('seller_jwt');
      const response = await api.delete(`/product/delete/${id}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      if (response.data.code !== "0") {
        throw new Error(response.data.message);
      }

      return response.data.data;
    } catch (error) {
      console.error("API error deleting product:", error);
      throw new Error('Failed to delete product');
    }
  }
};
