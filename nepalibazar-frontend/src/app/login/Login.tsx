'use client';

import React, { useState } from "react";
import { useRouter } from "next/navigation";
import { loginUser } from "../services/UserLoginService";
import toast from "react-hot-toast";

const Login: React.FC = () => {
  const [emailPhone, setEmailPhone] = useState('');
  const [password, setPassword] = useState('');
  const router = useRouter();

  const handlelogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res = await loginUser.login({ emailPhone, password });
      localStorage.setItem('token', res.token);
      toast.success('Login successful');
      router.push('/');
    } catch (err: any) {
      toast.error(err.response?.data?.message || 'Login failed');
    }
  };

  return (
    <div className="flex flex-row w-full min-h-screen px-10 py-16 mt-6 ml-6 bg-blue-50/25 gap-16 border-t border-gray-300 rounded">
      <div className="w-[500px] h-[600px]">
        <img
          className="rounded shadow-blue-200 w-full"
          src="signupPic.png"
          alt="Login illustration"
        />
      </div>

      <div className="my-32 mx-14">
        <form 
          className="flex flex-col gap-4 w-96 border-gray-300"
          onSubmit={handlelogin} 
        >
          <h2 className="text-2xl font-medium mb-4 border-b border-gray-300 rounded">
            Login to your account
          </h2>

          <input
            type="email"
            placeholder="Gmail"
            value={emailPhone}
            onChange={(e) => setEmailPhone(e.target.value)}
            className="border-b border-gray-500 p-2 outline-none"
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
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
