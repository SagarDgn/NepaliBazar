"use client";
import React, { useState } from 'react'
import { authSeller } from '../services/auth/SellerAuthService';
import toast from 'react-hot-toast';

const Login: React.FC = () => {

  const[emailPhone, setEmailPhone]= useState("");
  const[password, setPassword]= useState("");
  const[loading, setLoading]= useState(false);

  const handleLogin = async (e: React.FormEvent) => {
  e.preventDefault();
  if (loading) return;
  setLoading(true);

  try {
    debugger
    const res = await authSeller.login({ emailPhone, password });
    
 if (res && res.token) {
  localStorage.setItem("token", res.token);
  localStorage.setItem("sellerName", res.sellername);
  localStorage.setItem("role", res.permission.join(","));
  toast.success(res.message || "Login successful");

  window.location.href = "/seller-dashbord";
} 


 else {
      toast.error(res.message || "Invalid email or password");
    }
  } catch (err: any) {
    if (err.response) {
      toast.error(err.response.data?.message || "Login failed");
    } else if (err.request) {
      toast.error("No response from server. Try later.");
    } else {
      toast.error("Login error: " + err.message);
    }
  } finally {
    setLoading(false);
  }
};
  return (
    <div className="flex flex-row w-full min-h-screen px-10 py-16  bg-blue-50/25 gap-16 border-t border-gray-300 rounded">
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
          onSubmit={handleLogin}
          
        >
          <h2 className="text-2xl font-medium mb-4 border-b border-gray-300 rounded">
            Login to your account
          </h2>

          <input
            type="text"
            placeholder="Email or Phone"
            value={emailPhone}
            onChange={(e)=>setEmailPhone(e.target.value)}
            className="border-b border-gray-500 p-2 outline-none"
            required
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e)=> setPassword(e.target.value)}
            className="border-b border-gray-500 p-2 outline-none"
            required
          />

          <button
            type="submit"
            disabled={loading}
            className={`bg-red-700/95 text-white py-2 rounded hover:bg-red-700 ${loading ? 'opacity-70 cursor-not-allowed' : ''}`}
          >
            {loading? 'Logging in...': 'Login'}
          </button>
        </form>
      </div>
    </div>
  )
}

export default Login