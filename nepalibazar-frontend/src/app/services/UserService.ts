import axios from "axios";

export type UserRole = "BUYER";

export interface SignupRequest {
  userName: string;
  emailPhone: string;
  address: string;
  userRole: UserRole;
  password: string;
  otp?: string;
}

export interface RestResponse<T> {
  code: string;
  message: string;
  data: T;
}

const API_BASE_URL = "http://localhost:8080/api";

// Call to send OTP
export const sendOtp = async (email: string): Promise<RestResponse<any>> => {
  const response = await axios.post<RestResponse<any>>(`${API_BASE_URL}/user/sent-otp`, {
    email,
  });
  return response.data;
};

// Call to signup (with OTP verification)
export const signupUser = async (
  requestData: SignupRequest
): Promise<RestResponse<any>> => {
  const response = await axios.post<RestResponse<any>>(
    `${API_BASE_URL}/user/signup`,
    requestData
  );
  return response.data;
};
