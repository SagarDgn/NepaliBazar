'use client'

import { Search, Heart, ShoppingCart, User } from 'lucide-react'
import { useRouter } from 'next/navigation'
import React from 'react'

const Navbar = () => {
  const router = useRouter()

  const navItems = [
    { label: "Home", path: "/home" },
    { label: "About", path: "/about" },
    { label: "Contact", path: "/contact" },
    { label: "Sign Up", path: "/signup-user" },
    { label: "Become a Seller", path: "/signup-seller" },
  ]

  return (
    <nav className="border-b border-zinc-500 py-3 px-4 md:px-12 h-24">
      <div className="flex flex-col md:flex-row items-center justify-between text-black my-5">

        {/* Logo */}
        <div
          className="md:text-2xl mt-1 mx-6 font-bold cursor-pointer"
          onClick={() => router.push('/home')}
        >
          <img src="logo.png" height={100} width={200} alt="Logo" />
        </div>

        

        <ul className="hidden md:flex gap-6 mt-1 font-normal text-base text-black">
          {navItems.map((item, index) => (
            <li
              key={index}
              onClick={() => router.push(item.path)}
              className="cursor-pointer hover:underline decoration-gray-400 hover:text-gray-800"
            >
              {item.label}
            </li>
          ))}
        </ul>

        
        <div className="relative w-full max-w-xs mt-1">
          <input
            type="search"
            name="navSearch"
            placeholder="What are you looking for?"
            className="w-full border border-zinc-300 rounded-full py-2 pl-10 pr-4 focus:outline-none focus:ring-2 focus:ring-zinc-400 text-sm"
          />
          <Search className="absolute left-3 top-2.5 h-5 w-5 text-zinc-700" />
        </div>

        
        <div className="flex items-center gap-5 mt-1">
          <Heart
            className="w-5 h-5 cursor-pointer hover:text-gray-900"
            onClick={() => router.push('/wishlist')}
          />
          <ShoppingCart
            className="w-5 h-5 cursor-pointer hover:text-gray-900"
            onClick={() => router.push('/cart')}
          />
          <User
           className="w-5 h-5 cursor-pointer hover:text-gray-900"
            onClick={() => router.push('/user-profile')}
            />

        </div>
        
      </div>
    </nav>
  )
}

export default Navbar
