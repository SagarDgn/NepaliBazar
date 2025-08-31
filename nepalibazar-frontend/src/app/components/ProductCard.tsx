// components/ProductCard.tsx
import React from "react";
import { Product } from "../types/Product";
import { Heart } from "lucide-react";
interface Props {
  product: Product;
}

const ProductCard: React.FC<Props> = ({ product }) => {
  return (
    <div className="p-4  rounded-2xl shadow hover:shadow-lg transition bg-blue-50/25">
      <img
        src={product.image}
        alt={product.productName}
        className="w-full h-40 object-cover rounded-xl"
      />
      <h2 className="text-lg font-semibold mt-3">{product.productName}</h2>
      <p className="text-sm text-gray-600 line-clamp-2">{product.aboutProduct}</p>

      <div className="mt-2 flex items-center justify-between">
        <span className="text-xl font-bold ">MRP {product.price}/-</span>
        {product.discount > 0 && (
          <span className="text-sm ">-{product.discount}%</span>
        )}
      </div>

      <p className="text-sm text-gray-500 mt-1">
        Quantity: {product.quantity}
      </p>

      <p className="text-xs text-gray-500 mt-1">
        Seller: {product.sellerEntity.sellerName}
      </p>

      <button className="w-full mt-3 py-2 bg-green-500 text-white rounded-xl hover:bg-green-600 transition flex flex-row items-center">
        <p className="mx-14">Add to Wishlist</p>
        <Heart />
      </button>
      <button className="w-full mt-3 py-2 bg-red-500 text-white rounded-xl hover:bg-red-600 transition">
        Add to cart
      </button>
    </div>
  );
};

export default ProductCard;
