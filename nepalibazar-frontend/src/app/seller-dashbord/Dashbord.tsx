"use client";
import { useRouter } from 'next/navigation';
import React, { useEffect, useState } from 'react'
import { authSeller } from '../services/auth/SellerAuthService';
import toast from 'react-hot-toast';
import Button from '../components/Button';

const Dashbord :React.FC= () => {
  const [sellername, setSellername]= useState<string | null>(null); 
  const router = useRouter();

  useEffect(() => {
    const token = localStorage.getItem("token");
    const storedName = localStorage.getItem("sellerName");

    if (!token) {
      // no token → force login
      router.push("/seller-login");
    } else if (storedName) {
      setSellername(storedName);
    }
  }, [router]);

  const handleLogout = async () => {
    const token = localStorage.getItem("token");
    if (token) {
      try {
        const successlogout = await authSeller.logout(token); 
        if (!successlogout) {
          toast.error("Logout failed");
        } else {
          localStorage.removeItem("token");
          localStorage.removeItem("sellerName");
          localStorage.removeItem("role");
          toast.success("Logout success");
          router.push("/seller-login"); 
        }
      } catch (error) {
        toast.error("Something went wrong during logout");
      }
    }
  };



  return (
     <div className="    flex flex-col items-center border-t border-gray-300">
      
      <div className="  rounded-2xl p-6 w-[700px] text-center ">
        <h1 className="text-2xl font-medium mb-4 ">
          {sellername ? `Welcome, ${sellername}! to NepaliBazar` : "Loading... "}
        </h1>
        <p className="text-gray-600 mb-6">
          This is your seller dashboard. You can manage your products and orders here.
        </p>
        
      </div>
      <div>
        <h1>Manage products</h1>
      </div>
      <div>
       <Button className='' label="Logout" onClick={handleLogout} />
       </div>
    </div>
  );
};

export default Dashbord