"use client";

import React, { useState } from "react";
import { signupSeller, SignupRequest } from "../services/SellerSignupService";
import toast from "react-hot-toast";
import { useRouter } from "next/navigation";

interface VerifyOtpProps {
  email: string;
  onVerified: () => void;
}

const Verifyotp: React.FC<VerifyOtpProps> = ({ email, onVerified }) => {
  const router = useRouter();

  const [formData, setFormData] = useState<Omit<SignupRequest, "otp">>({
    sellerName: "",
    emailPhone: email,
    password: "",
    location: "",
    productCategory: "",
    role: "SELLER"

  });

  const [otp, setOtp] = useState("");
  const [errors, setErrors] = useState<{ [key: string]: string }>({});
  const [loading, setLoading] = useState(false);

  const validate = () => {
    const errs: { [key: string]: string } = {};
    if (!formData.sellerName.trim()) errs.sellerName = "Name is required";
    if (!formData.password.trim()) {
      errs.password = "Password is required";
    } else {
      const passwordRegex =
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?#&_.])[A-Za-z\d@$!%*?#&_.]{8,}$/;
      if (!passwordRegex.test(formData.password)) {
        errs.password =
          "Must contain 8+ chars with uppercase, lowercase, digit & special char";
      }
    }
    if (!formData.location.trim()) errs.location = "Location is required";
    if (!otp.trim() || otp.trim().length !== 6) errs.otp = "Enter 6-digit OTP";
    if(!formData.productCategory.trim())errs.productCategory= "Product category is required";

    setErrors(errs);
    return Object.keys(errs).length === 0;
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
    setErrors((prev) => ({ ...prev, [name]: "" }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
  e.preventDefault();
  if (!validate()) return;

  try {
    setLoading(true);
    const fullRequest: SignupRequest = {
      ...formData,
      emailPhone: email,
      otp,
    };

    const res = await signupSeller(fullRequest);
    console.log("Raw response:", res);
    console.log("Code:", res.code, "Type:", typeof res.code);

    if (  res.code === "0") {
      toast.success(res.message);
      router.push("/seller-login");
    } else {
      toast.error(res.message || "Signup failed");
    }
  } catch (error) {
    console.error("Signup error:", error);
    toast.error("An error occurred during signup");
  } finally {
    setLoading(false);
  }
};


  return (
    <div className="flex flex-row w-full min-h-screen px-10  py-16 bg-blue-50/25 gap-16 border-t border-gray-300 rounded">
      <div className="w-[500px] h-[600px]">
        <img className="rounded shadow-blue-200 w-full object-cover" src="signupPic.png" />
      </div>
      <div className="my-12 mx-14">
        <form onSubmit={handleSubmit} className="flex flex-col gap-4 w-96">
          <h2 className="text-2xl font-semibold mb-4 border-b border-gray-500 rounded">
            Verify OTP and Complete Signup
          </h2>

          <input
            name="sellerName"
            placeholder="Name"
            value={formData.sellerName}
            onChange={handleChange}
            className="border-b border-gray-500 p-2 outline-none"
          />
          {errors.userName && <p className="text-red-600 text-sm">{errors.userName}</p>}

          <input
            name="password"
            type="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            className="border-b border-gray-500 p-2  outline-none"
          />
          {errors.password && <p className="text-red-600 text-sm">{errors.password}</p>}

          <input
            name="location"
            placeholder="Location"
            value={formData.location}
            onChange={handleChange}
            className="border-b border-gray-500 p-2  outline-none"
          />
          {errors.location && <p className="text-red-600 text-sm">{errors.location}</p>}

           <input
            name="productCategory"
            placeholder="Product category"
            value={formData.productCategory}
            onChange={handleChange}
            className="border-b border-gray-500 p-2  outline-none"
          />
          {errors.productCategory && <p className="text-red-600 text-sm"></p>}

          <input
            name="otp"
            placeholder="Enter 6-digit OTP"
            value={otp}
            onChange={(e) => {
              setOtp(e.target.value);
              setErrors((prev) => ({ ...prev, otp: "" }));
            }}
            className="border-b border-gray-500 p-2 rounded outline-none"
          />
          {errors.otp && <p className="text-red-600 text-sm">{errors.otp}</p>}

          <button
            type="submit"
            disabled={loading}
            className="bg-red-600/95 text-white py-2 rounded hover:bg-red-700 disabled:opacity-60"
          >
            {loading ? "Verifying..." : "Verify & Register"}
          </button>
        </form>
      </div>
    </div>
  );
};

export default Verifyotp;
