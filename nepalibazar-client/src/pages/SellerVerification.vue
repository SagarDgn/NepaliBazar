<template>
  <div>
  <Hero />
  <Navbar />
  <div class="my-15">
    <div class="flex flex-row gap-20">
      <div class="mr-10">
        <img
          src="../assets/signupPic.PNG"
          alt="Basket image"
          width="550"
          class="rounded shadow-blue-200"
        />
      </div>
      <div class="mt-10 py-5 flex flex-col">
        <form class="mt-10 w-96 gap-4" @submit.prevent="handleSignup">
          <h2 className="text-2xl font-medium mb-4 border-b border-gray-300 ">
            Enter your details
          </h2>

          <input
          v-model="sellerName"
            type="text"
            placeholder="Sellername"
            class="outline-none border-b border-gray-500 w-full my-3 p-2"
          />
          <p v-if="sellerNameError" class="text-red-600 text-sm">{{ sellerNameError }}</p>
          <input
          v-model="password"
            type="password"
            placeholder="Password"
            class="outline-none border-b border-gray-500 w-full my-3 p-2"
          />
          <p v-if="passwordError" class="text-red-600 text-sm">{{ passwordError }}</p>
          <input
          v-model="address"
            type="text"
            placeholder="Address"
            class="outline-none border-b border-gray-500 w-full my-3 p-2"
          />
          <p v-if="addressError" class="text-red-600 text-sm">{{ addressError }}</p>
          <input
          v-model="productCategory"
            type="text"
            placeholder="Product category"
            class="outline-none border-b border-gray-500 w-full my-3 p-2"
          />
          <p v-if="categoryError" class="text-red-600 text-sm">{{ categoryError }}</p>

           <input
          v-model="otp"
            type="text"
            placeholder="Enter 6-digit OTP"
            class="outline-none border-b border-gray-500 w-full my-3 p-2"
          />
          <p v-if="otpError" class="text-red-600 text-sm">{{ otpError }}</p>

          <button
            type="submit"
            class="bg-red-700/95 text-white py-2 rounded hover:bg-red-700 disabled:opacity-60 w-full"
            :disabled="loading"
          >
            {{ loading? "Verifying...": "Verify and register" }}
          </button>
        </form>
        <div className="py-1.5">
          <router-link to="/login" className="flex flex-row">
            <p className="font-extralight">Already have account?</p>
            <p className="font-light hover:underline">Login</p>
          </router-link>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import { toast } from "vue3-toastify";
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";
import { signUpSeller } from "../services/SellersignupService";

export default {
  components: { Hero, Navbar },
  name: "SellerVerification",

  data() {
    return {
      email: sessionStorage.getItem("signupEmail") || "",
      sellerName: "",
      password: "",
      address: "",
      productCategory: "",
      otp: "",
      loading: false,

      sellerNameError: "",
      passwordError: "",
      addressError: "",
      categoryError: "",
      otpError: "",
    };
  },

  methods: {
    validateForm() {
      let valid = true;

      if (!this.sellerName) {
        this.sellerNameError = "Sellername is required";
        valid = false;
      } else {
        this.sellerNameNameError = "";
      }

      if (!this.password || this.password.length < 8) {
        this.passwordError = "Password must be at least 8 characters and required";
        valid = false;
      } else {
        this.passwordError = "";
      }

      if (!this.address) {
        this.addressError = "Address is required";
        valid = false;
      } else {
        this.addressError = "";
      }

      if (!this.productCategory) {
        this.categoryError = "Address is required";
        valid = false;
      } else {
        this.categoryError = "";
      }

      if (!this.otp) {
        this.otpError = "OTP is required";
        valid = false;
      } else {
        this.otpError = "";
      }

      return valid;
    },

    async handleSignup() {
        if(!this.validateForm()) return;
        this.loading =true;

        try{
            const sellerData={
                emailPhone: this.email,
                sellerName: this.sellerName,
                passowrd: this.password,
                location: this.address,
                productCategory: this.productCategory,
                otp: this.otp
            };

            const response= await signUpSeller(sellerData);
            if(response.data.code === "0"){
                toast.success("Signup success");
                this.$router.push({name: "LoginSeller"});
            }else{
                toast.error(response.data.message || "Signup failed");
            }
        }catch(error){
            console.log(error);
            toast.error(error.response?.data?.message ||"Network error. Please try again later.")
        }finally{
            this.loading=false;
        }
    },
  },
};
</script>

<style></style>
