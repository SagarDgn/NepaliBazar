
import { createRouter,createWebHistory } from "vue-router";
import Home from "../pages/Home.vue";
import CustomerSignup from "../pages/CustomerSignup.vue";
import CustomerVerification from "../pages/CustomerVerification.vue";
import Sellersignup from "../pages/Sellersignup.vue";
import LoginSeller from '../pages/loginSeller.vue';
import UserAuthService from "../services/UserAuthService";
import SellerVerification from "../pages/SellerVerification.vue";
import SellerDashbord from "../pages/SellerDashbord.vue";
import Loginuser from "../pages/loginUser.vue";
import SellerAuthService from "../services/SellerAuthService";


const routes=[
    {path: '/', name:'Home',component: Home},
    {path: '/sellersignup', name: 'Sellersignup', component: Sellersignup,meta: { guestOnly: true }},
    {path: '/usersignup', name: 'CustomerSignup',component:CustomerSignup,meta:{guestOnly:true}},
    {path: '/userverification', name: 'CustomerVerification', component: CustomerVerification,meta: { guestOnly: true }},
    {path:'/userlogin' ,name: 'UserLoginPage',component:Loginuser},
    {path: '/sellerverification', name: 'SellerVerification',component: SellerVerification,meta: { guestOnly: true }},
    {path: '/sellerlogin', name: 'LoginSeller', component: LoginSeller},    
    {
        path:'/sellerdashbord',
        name:'SellerDashbord',
        component: SellerDashbord,
        meta:{requiresSellerAuth:true}
    }
    

];

const router= createRouter({
    history:  createWebHistory(),
    routes,
})

router.beforeEach((to, from, next) => {
  const userLoggedIn = UserAuthService.isAuthenticated();
  const sellerLoggedIn = SellerAuthService.isAuthenticated();
  const sellerRole = localStorage.getItem("role");

  
  if (to.meta.guestOnly && (userLoggedIn || sellerLoggedIn)) {
    if (sellerRole === "SELLER") {
      return next({ name: "SellerDashbord" });
    }
    return next({ name: "Home" });
  }

 
  if (to.meta.requiresSellerAuth) {
    if (!sellerLoggedIn || sellerRole !== "SELLER") {
      return next({ name: "LoginSeller" });
    }
  }

  next();
});

export default router