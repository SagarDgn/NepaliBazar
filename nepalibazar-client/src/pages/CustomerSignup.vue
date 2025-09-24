<template>
  <div class="">
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
        <div class="mt-30 py-10 flex flex-col">
          <form class="mt-10 w-96 gap-4" @submit.prevent="handleSentOtp">
            <h2 class="text-2xl font-medium mb-4 border-b border-gray-300">
              Enter Gmail to signup
            </h2>

            <input
              v-model="email"
              type="email"
              placeholder="Gmail"
              class="outline-none border-b border-gray-500 w-full my-3 p-2"
            />
            <p v-if="emailError" class="text-red-600 text-sm mt-1">
              {{ emailError }}
            </p> 
            <button
              type="submit"
              class="bg-red-700/95 text-white py-2 rounded hover:bg-red-700 disabled:opacity-60 w-full"
              :disabled="loading"
            >
              {{ loading? "Sending OTP..." : "Request OTP" }}
            </button>
          </form>
        
        <div class="py-1.5">
          <router-link to="/login" class="flex flex-row">
            <p class="font-extralight">Already have account?</p>
            <p class="font-light hover:underline">Login</p>
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
import { sentOtp } from "../services/UsersignupService";

export default {
  components: { Hero, Navbar },
  name: "CustomerSignup",

  data() {
    return {
      email: "",
      emailError: "",
      loading: false,
    };
  },

  methods: {
    isValidGmail(email) {
      const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
      return gmailRegex.test(email);
    },

    async handleSentOtp() {
      if (!this.email) {
        this.emailError = "Email is required";
        toast.error("Please enter your Gmail");
        return;
      }

      if (!this.isValidGmail(this.email)) {
        this.emailError = "Enter a valid Gmail address";
        toast.error("Invalid Gmail format");
        return;
      }

      this.emailError = ""; 
      this.loading = true;

      try {
        const response= await sentOtp({emailPhone: this.email})
        if(response.data.code== "0"){
          toast.success("OTP sent successfully",{autoClose:3000});
          sessionStorage.setItem("signupEmail", this.email);
          this.$router.push({ name: "CustomerVerification" });
        }else{
           toast.error(response.data.message || "Gmail is already in use!!");
        }
       
      } catch (error) {
        console.error("Some error occurred: ", error);
        toast.error(error.response?.data?.message || "Network error. Please try again.");
      } finally{
        this.loading=false;
      }
    },
  },
};
</script>
<style></style>
