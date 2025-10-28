import { createRouter, createWebHistory } from "vue-router";
import Home from "../pages/Home.vue";
import CustomerSignup from "../pages/CustomerSignup.vue";
import CustomerVerification from "../pages/CustomerVerification.vue";
import Sellersignup from "../pages/Sellersignup.vue";
import LoginSeller from "../pages/loginSeller.vue";
import UserAuthService from "../services/UserAuthService";
import SellerVerification from "../pages/SellerVerification.vue";
import SellerDashbord from "../pages/SellerDashbord.vue";
import SellerAuthService from "../services/SellerAuthService";
import ProductList from "../pages/ProductList.vue";
import LoginUser from "../pages/loginUser.vue";
import WishlistPage from "../pages/WishlistPage.vue";
import CartItemPage from "../pages/CartItemPage.vue";


const routes = [
  { path: "/", name: "Home", component: Home },
  { path: "/sellersignup", name: "Sellersignup", component: Sellersignup, meta: { guestOnly: true } },
  { path: "/usersignup", name: "CustomerSignup", component: CustomerSignup, meta: { guestOnly: true } },
  { path: "/userverification", name: "CustomerVerification", component: CustomerVerification, meta: { guestOnly: true } },
  {path: '/loginuser',name:'LoginUser',component: LoginUser},
  { path: "/sellerverification", name: "SellerVerification", component: SellerVerification, meta: { guestOnly: true } },
  { path: "/sellerlogin", name: "LoginSeller", component: LoginSeller },
  {
    path: "/sellerdashbord",
    name: "SellerDashbord",
    component: SellerDashbord,
    meta: { requiresSellerAuth: true },
  },
  { path: "/products", name: "ProductPage", component: ProductList },
  {
    path: "/wishlist",
    name: "WishlistPage",
    component: WishlistPage,
    meta: { requiresUserAuth: true }, //  only logged-in users can view wishlist
  },
  {path: "/mycart",name:"CartItemPage",component: CartItemPage}


];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const userLoggedIn = UserAuthService.isAuthenticated();
  const sellerLoggedIn = SellerAuthService.isAuthenticated();
  const seller_role = localStorage.getItem("seller_role"); // "BUYER" or "SELLER"
   const buyer_role = localStorage.getItem("buyer_role");

  // Prevent infinite redirect
  if (to.name === from.name) return next();

  // 🔹 Guest-only routes (login/signup)
  if (to.meta.guestOnly) {
    if (userLoggedIn && buyer_role === "BUYER") return next({ name: "Home" });
    if (sellerLoggedIn && seller_role === "SELLER") return next({ name: "SellerDashbord" });
  }

  // 🔹 Seller-only routes
  if (to.meta.requiresSellerAuth) {
    if (!sellerLoggedIn || seller_role !== "SELLER") return next({ name: "LoginSeller" });
  }

  // 🔹 User-only routes
  if (to.meta.requiresUserAuth) {
    if (!userLoggedIn || buyer_role !== "BUYER") return next({ name: "LoginUser" });
  }

  // Default: allow navigation
  next();
});


export default router;
