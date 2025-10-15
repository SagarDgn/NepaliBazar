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
            <div class="mt-10 py-5 flex flex-col">
                <form class="mt-10 w-96 gap-4" @submit.prevent="handleSignup">
                    <h2 class="text-2xl font-medium mb-4 border-b border-gray-300">
                        Enter your details
                    </h2>

                    <!-- USERNAME -->
                    <input
                      v-model="userName"
                      type="text"
                      placeholder="Username"
                      class="border-b border-gray-500 outline-none w-full my-3 p-2"
                    />
                    <p v-if="userNameError" class="text-red-600 text-sm">{{ userNameError }}</p>

                    <!-- PASSWORD -->
                    <input
                      v-model="password"
                      type="password"
                      placeholder="Password"
                      class="border-b border-gray-500 outline-none w-full my-3 p-2"
                    />
                    <p v-if="passwordError" class="text-red-600 text-sm">{{ passwordError }}</p>

                    <!-- ADDRESS -->
                    <input
                      v-model="address"
                      type="text"
                      placeholder="Address"
                      class="border-b border-gray-500 outline-none w-full my-3 p-2"
                    />
                    <p v-if="addressError" class="text-red-600 text-sm">{{ addressError }}</p>

                    <!-- OTP -->
                    <input
                      v-model="otp"
                      type="text"
                      placeholder="Enter 6-digit OTP"
                      class="border-b border-gray-500 outline-none w-full my-3 p-2"
                    />
                    <p v-if="otpError" class="text-red-600 text-sm">{{ otpError }}</p>

                    <button
                      type="submit"
                      :disabled="loading"
                      class="bg-red-700/95 text-white py-2 rounded hover:bg-red-700 disabled:opacity-60 w-full"
                    >
                      {{ loading ? "Verifying..." : "Verify and Register" }}
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
import { signupUser } from '../services/UsersignupService';

export default {
  name: "CustomerVerification",
  components: { Navbar, Hero },

  data() {
    return {
      email: sessionStorage.getItem("signupEmail") || "",
      userName: "",
      password: "",
      address: "",
      otp: "",
      loading: false,

      userNameError: "",
      passwordError: "",
      addressError: "",
      otpError: ""
    };
  },

  methods: {
    validateForm() {
      let valid = true;

      if (!this.userName) {
        this.userNameError = "Username is required";
        valid = false;
      } else {
        this.userNameError = "";
      }

      if (!this.password || this.password.length < 8) {
        this.passwordError = "Password must be at least 8 characters";
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

      if (!this.otp) {
        this.otpError = "OTP is required";
        valid = false;
      } else {
        this.otpError = "";
      }

      return valid;
    },

    async handleSignup() {
      if (!this.validateForm()) return;
      this.loading = true;

      try {
        const userData = {
          emailPhone: this.email,
          userName: this.userName,
          password: this.password,
          address: this.address,
          otp: this.otp,
        };

        const response = await signupUser(userData);

        if (response.data.code === "0") {
          toast.success("Signup success");
          this.$router.push({ name: "UserLoginPage" });
        } else {
          toast.error(response.data.message || "Signup failed");
        }
      } catch (error) {
        console.error("Signup failed", error);
        toast.error(error.response?.data?.message || "Internal server error. Please try again later.");
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>
