"use client";

import React from "react";
import Link from "next/link";

const Login: React.FC = () => {
  return (
    <div className="flex flex-row w-full min-h-screen px-10 py-16 mt-6 ml-6 bg-blue-50/25 gap-16 border-t border-gray-300 rounded">
      {/* Left side image */}
      <div className="w-[500px] h-[600px]">
        <img
          className="rounded shadow-blue-200 w-full"
          src="signupPic.png"
          alt="Login illustration"
        />
      </div>

      {/* Right side form */}
      <div className="my-32 mx-14">
        <form className="flex flex-col gap-4 w-96 border-gray-300">
          <h2 className="text-2xl font-medium mb-4 border-b border-gray-300 rounded">
            Login to your account
          </h2>

          <input
            name="email"
            placeholder="Gmail"
            className="border-b border-gray-500 p-2 outline-none"
          />

          <input
            name="password"
            type="password"
            placeholder="Password"
            className="border-b border-gray-500 p-2 outline-none"
          />

          <button
            type="submit"
            className="bg-red-700/95 text-white py-2 rounded hover:bg-red-700"
          >
            Login
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;
