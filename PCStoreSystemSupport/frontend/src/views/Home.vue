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
         <button class="btn" @click="recommending()" style=" background-color: black; color: white; font-size: 200%">COMPONENT FAULT PROBABILITY</button>
       </div>
       <div class="row" style="height: 20%; padding-bottom: 5%;">
         <button class="btn" @click="recommending()" style=" background-color: black; color: white; font-size: 200%">COMPONENT VALUE ESTIMATION FOR PURPOSE</button>
       </div>
       <div class="row" style="height: 20%; padding-bottom: 5%;">
        <button class="btn" @click="recommending()" style=" background-color: black; color: white; font-size: 200%">SIMILAR PC's</button>
       </div>

    </div>

     <div class="col-sm-6" style="padding: 5%;">
      
       

    </div>

  </div>
  <div class="row"  >


  </div>


</div>


</template>

<script>
   
  
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
