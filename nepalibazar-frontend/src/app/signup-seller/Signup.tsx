"use client";
import React, { useState } from "react";
import Link from "next/link";
import { sendOtp } from "../services/UserService";
import toast from "react-hot-toast";


interface SignupProps{
  onOtpSent: (email:string)=>void;
}

const Signup: React.FC<SignupProps> = ({onOtpSent}) => {

  const[emailPhone, setEmailPhone]= useState("");
  const[error, setError]= useState("");
  const[loading, seteLoading]= useState(false);

  const validateEmail= (email: string)=>{
    const emailRegex= /^[^\s@]+@gmail\.com$/;
    return emailRegex.test(email);
  };

  const handleSubmit= async (e: React.FormEvent)=>{
    e.preventDefault();
    setError("");

    if(!emailPhone.trim){
      setError("Email is required");
      return;
    }
    if(!validateEmail(emailPhone)){
      setError("Only valid Gmail address is accepted");
      return;
    }
    try{
      seteLoading(true);
      const res= await sendOtp(emailPhone);
      const code= Number(res.code);

      if(code === 0){
        toast.success(res.message);
        onOtpSent(emailPhone);
      }else{
        toast.error(res.message);
      }

    }catch{
      toast.error("Failed to send otp");
    }finally{
      seteLoading(false);
    }
  };


  return (
    <div className="flex flex-row w-full min-h-screen px-10 py-16 gap-16 border-t border-gray-300 rounded bg-blue-50/25 ">
      
      <div className="w-[500px] h-[600px]">
        <img
          className="rounded shadow-blue-200 w-full"
          src="signupPic.png"
          alt="Signup Illustration"
        />
      </div>

      
      <div className="my-32 mx-14">
        <form
         onSubmit={handleSubmit}
           className="flex flex-col gap-4 w-96 border-gray-300">
          <h2 className="text-2xl font-medium mb-4 border-b border-gray-300 rounded">
            Enter Gmail to become partner
          </h2>

          <input
            name="emailPhone"
            placeholder="Gmail"
            value={emailPhone}
            onChange={(e)=>setEmailPhone(e.target.value)}
            className="border-b border-gray-500 p-2 outline-none"
          />

          <button
            type="submit"
            disabled={loading}
            className="bg-red-700/95 text-white py-2 rounded hover:bg-red-700 disabled:opacity-60"
          >
            {loading ? "Sending OTP...": "Request OTP"}
          </button>
        </form>

        
        <div className="py-1.5">
          <Link href="/seller-login" className="flex flex-row gap-1">
            <p className="font-extralight">Already have account?</p>
            <p className="font-light hover:underline">Login</p>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Signup;
