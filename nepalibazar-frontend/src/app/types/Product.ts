// types/Product.ts
export interface SellerEntity {
  id: number;
  sellerName: string;
  emailPhone: string;
  location: string;
  role: string;
  productCategory: string;
  loggedIn: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface Product {
  id: number;
  productName: string;
  aboutProduct: string;
  image: string;
  price: number;
  discount: number;
  quantity: number;
  sellerEntity: SellerEntity;
}

export interface ApiResponse<T> {
  code: string;
  message: string;
  data: T;
}
