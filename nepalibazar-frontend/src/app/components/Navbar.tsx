'use client'

import { Search, Heart, ShoppingCart } from 'lucide-react'
import Link from 'next/link'
import React from 'react'

const Navbar = () => {
  return (
    <nav className="border-b border-zinc-500 py-3 px-4 md:px-12 h-24 ">
      <div className="flex flex-col md:flex-row items-center justify-between gap-4  text-black my-5">
        
        <div className=" md:text-2xl mt-1 mx-6 font-bold ">
          <Link href="/home" className='text-2xl'>NepaliBazar</Link>
        </div>

        <ul className="hidden md:flex gap-6 mt-1 font-normal text-base text-black">
            <Link href="/home" className="hover:underline decoration-gray-400 hover:text-gray-800">
            Home</Link>
           <Link href="/about" className="hover:underline decoration-gray-400 hover:text-gray-800">
           About</Link>
           <Link href="/contact" className="hover:underline decoration-gray-400 hover:text-gray-800">
           Contact</Link>
          <Link href="/signup" className="hover:underline decoration-gray-400 hover:text-gray-800">
          Sign Up</Link>
          <Link href="/seller" className="hover:underline decoration-gray-400 hover:text-gray-800">
          Become a Seller</Link>
        </ul>


        <div className="relative w-full max-w-xs mt-1 ">
          <input
            type="search"
            name="navSearch"
            placeholder="What are you looking for?"
            className="w-full border border-zinc-300 rounded-full py-2 pl-10 pr-4 focus:outline-none focus:ring-2 focus:ring-zinc-400 text-sm"
          />
          <Search className="absolute left-3 top-2.5 h-5 w-5 text-zinc-700" />
        </div>

        <div className="flex items-center gap-5 mt-1">
          <Link href="/wishlist" className="hover:text-gray-900">
            <Heart className="w-5 h-5" />
          </Link>
          <Link href="/cart" className="hover:text-gray-900">
            <ShoppingCart className="w-5 h-5" />
          </Link>
        </div>
      </div>
    </nav>
  )
}

export default Navbar

