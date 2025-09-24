<template>
  <nav class="flex flex-row  h-20  items-center border-b border-gray-500">
    <!-- navbar image -->
    <div class="mx-15 mt-4">
        <router-link to="/">
        <img src="../../assets/logo.PNG" alt="company logo " width="200">
        </router-link>
     </div> 
    <!-- navbar items -->
    <div class="gap-5 flex flex-row mx-10 mt-4">
       <div class="hover hover:underline font-normal" 
       v-for="(items, index) in navitems" 
       :key="index">
            <router-link :to="items.link">{{ items.name }}</router-link>   
        </div>
    </div>
 <!-- search bar -->
    <div>

    </div>
         
    
    </nav>
</template>

<script>
export default {
    name: "Navbar",
    data(){
        return{
            navitems: [
                {name: 'Home', link: '/'},
                {name: 'About Us', link:'/aboutus'},
                {name: 'Contact Us', link: '/contactus'},
                {name: 'Sign Up', link: '/usersignup'},
                {name: 'Become a seller', link:'/sellersignup'}

            ]
        }
    }
}

</script>

