<template>
  <div class="">
    <Hero />
    <Navbar />
    <div class="my-15">
      <!-- <SignupForm/> -->
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
          <form class="mt-10 w-96 gap-4">
            <h2 className="text-2xl font-medium mb-4 border-b border-gray-300 ">
              Enter Gmail to signup
            </h2>

            <input
              type="email"
              placeholder="Gmail"
              class="outline-none border-b border-gray-500 w-full my-3 p-2"
            />
            <button
              type="submit"
              class="bg-red-700/95 text-white py-2 rounded hover:bg-red-700 disabled:opacity-60 w-full"
            >
              Request OTP
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
import SignupForm from "../components/SignupForm.vue";
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";

export default {
  components: { SignupForm, Hero, Navbar },
  name: "Sellersignup",
};
</script>

<style></style>
