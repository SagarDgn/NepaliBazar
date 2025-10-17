<template>
  <div>
    <Hero/>
    <Navbar/>

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
        <div class="mt-30 py-10 flex flex-col">
          <form class="mt-10 w-96 gap-4" @submit.prevent="handleLogin">
            <h2 className="text-2xl font-medium mb-4 border-b border-gray-300 ">
              Enter Crenditals to login
            </h2>

            <input
              type="email"
              v-model="email"
              placeholder="Gmail"
              class="outline-none border-b border-gray-500 w-full my-3 p-2"
            />
            <p v-if="emailError" class="text-sm text-red-600 ">{{ emailError }}</p>

            <input
              type="password"
              v-model="password"
              placeholder="Password"
              class="outline-none border-b border-gray-500 w-full my-3 p-2"
            />
            <p v-if="passwordError" class="text-red-600 text-sm">{{ passwordError }}</p>
            <button
              type="submit"
              class="bg-red-700/95 text-white py-2 rounded hover:bg-red-700 disabled:opacity-60 w-full"
              :disabled="loading"
            >
              {{ loading ? "Logging in.." : "Login" }}
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { toast } from 'vue3-toastify';
import Hero from '../components/ui/Hero.vue';
import Navbar from '../components/ui/Navbar.vue';
import SellerAuthService from '../services/SellerAuthService';

export default {
    components: {Hero, Navbar},
    name: "LoginSeller",

     data() {
    return {
      email: "",
      password: "",
      loading: false,

      emailError: "",
      passwordError: "",
    };
  },

   mounted() {
    if (SellerAuthService.isAuthenticated()) {
      this.$router.push({ name: "SellerDashbord" });
    }
  },
   methods: {
    isValidate() {
      let valid = true;

      if (!this.email) {
        this.emailError = "Email is required for login";
        valid = false;
      } else {
        this.emailError = "";
      }

      if (!this.password) {
        this.passwordError = "Password is required for login";
        valid = false;
      } else {
        this.passwordError = "";
      }
      return valid;
    },

     async handleLogin() {
      if (!this.isValidate()) return;

      this.loading = true;

      try {
        const userData = {
          emailPhone: this.email,
          password: this.password,
        };

        const response = await SellerAuthService.login(userData);
        if (response.data.code === "0") {
          toast.success("Login successful");
          this.$router.push({ name: "SellerDashbord" });
        } else {
          toast.error(response.data.message || "Login  failed");
        }
      } catch (error) {
        console.log(error);
        toast.error(
          error.response?.data?.message ||
            "Internal server error. Please try again later."
        );
      } finally {
        this.loading = false;
      }
    },
  },


};
</script>

<style>

</style>