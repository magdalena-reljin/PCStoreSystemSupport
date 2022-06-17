<template>

<div  >

   <nav  class="navbar navbar-fixed-top navbar-expand" style="background-color: white; list-style: none;">
      <div class="container-fluid justify-content-center" style="background-color: white; ">
      <a class="navbar-brand"  href="http://localhost:8080/" >
      <img src="../assets/magicSolutionsLOGO.jpg" alt="" width="500" height="190" >
      </a>
      </div>
    </nav>
    &nbsp;  &nbsp;
   <br>
 
  <div class="row" id="slikadiv" >

    <div class=" justify-content-center" style="padding: 5%; padding-left: 20%; padding-right: 20%; ">
       <div class="row" style="height: 20%; padding-bottom: 5%;" >
         <button class="btn" @click="recommending()" style=" background-color: black; color: white; font-size: 200%">RECOMMENDING COMPONENTS</button>
       </div>
       <div class="row" style="height: 20%; padding-bottom: 5%;">
         <button class="btn" @click="estimateValue()" style=" background-color: black; color: white; font-size: 200%">COMPONENT VALUE ESTIMATION FOR PURPOSE</button>
       </div>
       <div class="row" style="height: 20%; padding-bottom: 5%;">
         <button class="btn" @click="faultProbability()" style=" background-color: black; color: white; font-size: 200%">COMPONENT FAULT PROBABILITY</button>
       </div>
       <div class="row" style="height: 20%; padding-bottom: 5%;">
        <button class="btn" @click="similarPc()" style=" background-color: black; color: white; font-size: 200%">SIMILAR PC's</button>
       </div>

    </div>

     <div class="col-sm-6" style="padding: 5%;">
      
       

    </div>

  </div>
  <div class="row"  >


  </div>
<div style="background-color: #b0638e; color: white;" class="footer" >
   <br><br>
   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart-fill" viewBox="0 0 16 16">
  <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"/>
</svg>

    Made by Dajana, Kristina and Magdalena

    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart-fill" viewBox="0 0 16 16">
  <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"/>
</svg>
   <br><br> <br><br>
</div>

</div>


</template>

<script>
   
  import axios from 'axios'
export default {
 

  data() {
    return {
      
    
       
    };
  },
  mounted() {
    
  },
  methods: {
       recommending: function(){
         this.$router.push("/recommendingComponents")
       },
       estimateValue: function(){
         this.$router.push("/estimatingValue")
       },
       faultProbability: function(){
         this.$router.push("/faultProbability")
       },
       similarPc: function () {
            axios
           .get("http://localhost:8081/cbr/proba")
           .then((response) => {
            this.results=response.data
            })
       }

    }
   
 

  
  
};
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#slikadiv{
  background-image: url('../assets/collage.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
  height: 700px
  

     
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
