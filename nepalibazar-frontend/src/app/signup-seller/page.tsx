"use client";
import React, { useState } from 'react'
import Signup from './Signup'
import Verifyotp from './Verifyotp';

const page = () => {

  const [otpSent, setOtpSent]= useState(false);
  const[email, setEmail]= useState("");
  return (
    <div className='min-h-screen flex items-center justify-center p-6 bg-gray-50'>
        {!otpSent ? (
        <Signup
          onOtpSent={(emailInput) => {
            setEmail(emailInput);
            setOtpSent(true);
          }}
        />
      ) : (
        <Verifyotp
          email={email}
          onVerified={() => {
            setOtpSent(false);
            setEmail("");
          }}
        />
      )}
    </div>
  );
};

export default page