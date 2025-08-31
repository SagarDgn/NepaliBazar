// components/Footer.tsx
"use client";

import React from "react";
import Link from "next/link";
import { FaFacebook, FaTwitter, FaInstagram } from "react-icons/fa";

const Footer: React.FC = () => {
  return (
    <footer className="bg-gray-900 text-gray-300 py-8 mt-12">
      <div className="container mx-auto px-6 flex flex-col md:flex-row justify-between items-center">
        
        {/* Logo / Brand */}
        <div className="text-2xl font-bold text-white mb-4 md:mb-0">
          <h1>NepaliBazar</h1>
        </div>

        {/* Navigation */}
        <div className="flex space-x-6 mb-4 md:mb-0">
  
          <Link href="/about" className="hover:text-white">About</Link>
          <Link href="/contact" className="hover:text-white">Contact</Link>
        </div>

        {/* Social icons */}
        <div className="flex space-x-4">
          <a href="#" className="hover:text-white"><FaFacebook size={20} /></a>
          <a href="#" className="hover:text-white"><FaTwitter size={20} /></a>
          <a href="#" className="hover:text-white"><FaInstagram size={20} /></a>
        </div>
      </div>

      {/* Divider */}
      <div className="border-t border-gray-700 mt-6 pt-4 text-center text-sm text-gray-500">
        © {new Date().getFullYear()} MyShop. All rights reserved.
      </div>
    </footer>
  );
};

export default Footer;
