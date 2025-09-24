import api from "./api";

//service to get otp from backend
export const sentOtp = (payload) => {
  return api.post("/user/sent-otp", payload, {
    headers: { "Content-Type": "application/json" },
  });
};


//service to signup user
export const signupUser= (userData)=>{
    return api.post("/user/signup",userData);

};

