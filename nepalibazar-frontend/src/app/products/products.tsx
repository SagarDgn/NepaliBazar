// pages/products.tsx
"use client";

import React from "react";
import ProductList from "../components/ProductList";
import { Link } from "lucide-react";

const ProductsPage: React.FC = () => {
  return (
    <div className="max-w-7xl mx-auto p-6 ">
      <h1 className="text-2xl font-semibold mx-6 mb-6">All Products from NepaliBazar</h1>
      {/* No limit, show all products */}
      <ProductList />
    </div>
  );
};

export default ProductsPage;
