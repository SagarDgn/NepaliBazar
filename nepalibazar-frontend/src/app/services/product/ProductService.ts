import axios from "axios";
import { Product ,ApiResponse} from "@/app/types/Product";

const API_BASE_URL= "http://localhost:8080";

export const getAllProducts= async(): Promise<Product[]>=>{
    const response= await axios.get<ApiResponse<Product[]>>(`${API_BASE_URL}/api/product/get`);
    return response.data.data || [];
};