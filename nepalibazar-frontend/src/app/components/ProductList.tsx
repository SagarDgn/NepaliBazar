
"use client";

import React, { useEffect, useState } from "react";
import Link from "next/link"; // ✅ for navigation
import { Product } from "../types/Product";
import { getAllProducts } from "../services/product/ProductService";
import ProductCard from "./ProductCard";

interface Props {
  limit?: number;
}

const ProductList: React.FC<Props> = ({ limit }) => {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getAllProducts()
      .then(setProducts)
      .catch((err) => {
        console.error("Error fetching products:", err);
        setProducts([]); // fallback to empty array
      })
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p className="text-center text-lg">Loading...</p>;

  const displayedProducts = limit ? products.slice(0, limit) : products;

  return (
    <div className="p-6">
      {/* product cards grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 xl:grid-cols-4 gap-6">
        {displayedProducts.length === 0 ? (
          <p>No products found</p>
        ) : (
          displayedProducts.map((product) => (
            <ProductCard key={product.id} product={product} />
          ))
        )}
      </div>

        {limit && (
  <div className="flex justify-center mt-6">
    <Link
      href="/products"
      className="px-6 py-2 bg-red-500 text-white rounded-lg hover:bg-red-600 transition"
    >
      View All Products
    </Link>
        </div>
      )}
    </div>
  );
};

export default ProductList;
