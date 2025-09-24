
import { createRouter,createWebHistory } from "vue-router";
import Home from "../pages/Home.vue";
import CustomerSignup from "../pages/CustomerSignup.vue";
import CustomerVerification from "../pages/CustomerVerification.vue";
import Loginuser from "../pages/loginuser.vue";
import Sellersignup from "../pages/Sellersignup.vue";


const routes=[
    {path: '/', name:'Home',component: Home},
    {path: '/sellersignup', name: 'Sellersignup', component: Sellersignup},
    {path: '/usersignup', name: 'CustomerSignup',component:CustomerSignup},
    { path: '/userverification', name: 'CustomerVerification', component: CustomerVerification},
    {path:'/userlogin' ,name: 'UserLoginPage',component: Loginuser}

]

const router= createRouter({
    history:  createWebHistory(),
    routes,
});
export default router