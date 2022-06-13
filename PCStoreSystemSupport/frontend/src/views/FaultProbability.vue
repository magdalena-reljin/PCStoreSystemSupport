<template>
  <nav  class="navbar navbar-fixed-top navbar-expand" style="background-color: white; list-style: none;">
      <div class="container-fluid justify-content-center" style="background-color: white;">
      <a class="navbar-brand"  href="http://localhost:8080/" >
      <img src="../assets/magicSolutionsLOGO.jpg" alt="" width="500" height="190" >
      </a>
      </div>
    </nav>
    &nbsp;  &nbsp;
   <br>
   <div id="zaSliku" class="row justify-content-center" style=" height: 350%;">
   <div class="col-md-6" style=" padding-top: 5%; padding-bottom: 5%;">
   <div class="card card-outline-secondary">
   <div class="card-header" style="background-color: white; color: black;">
               <h3 style="text-align:left;" class="mb-0">PC damage detection</h3>
   </div>
   <div  class="card-body"  style="text-align: left;">
    <form  @submit="sendRequest" method='post' >
        <div class="form-group">
            <label><b>Failure:</b></label>
              <Multiselect   style="color: gray; padding-bottom: 5%; " 
                           v-model="value"
                          mode="tags"
                          :close-on-select="false"
                          :searchable="true"
                          :create-option="false"
                          :options="options"
                          
                        />
          
        </div>
          <br>

        
       
        <br>
      
        <div class="row">
             <div class="col">
             <div class="form-group" style="text-align: right;">
             <button  type="submit" class="btn" style="background-color: #b0638e; color: white">Detect damage</button>
             </div>
             </div>
            
        </div>
    </form>
   </div>
   </div>
    </div>
   </div>

<hr>
<div class="list-group" style="padding-left: 5%; padding-right: 5%; text-align: left;" >
    <h3> Possible damage</h3>
    <hr>
    <a v-for="(result,index) in sort(results)" :key="index"  href="#" class="list-group-item list-group-item-action" style=" font-size: 125%;">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-wrench-adjustable" viewBox="0 0 16 16">
  <path d="M16 4.5a4.492 4.492 0 0 1-1.703 3.526L13 5l2.959-1.11c.027.2.041.403.041.61Z"/>
  <path d="M11.5 9c.653 0 1.273-.139 1.833-.39L12 5.5 11 3l3.826-1.53A4.5 4.5 0 0 0 7.29 6.092l-6.116 5.096a2.583 2.583 0 1 0 3.638 3.638L9.908 8.71A4.49 4.49 0 0 0 11.5 9Zm-1.292-4.361-.596.893.809-.27a.25.25 0 0 1 .287.377l-.596.893.809-.27.158.475-1.5.5a.25.25 0 0 1-.287-.376l.596-.893-.809.27a.25.25 0 0 1-.287-.377l.596-.893-.809.27-.158-.475 1.5-.5a.25.25 0 0 1 .287.376ZM3 14a1 1 0 1 1 0-2 1 1 0 0 1 0 2Z"/>
</svg> 
<b> &nbsp;&nbsp;{{result.failure}} {{result.percentage.toFixed(1)}}%</b>
    </a>
</div>


<br><br>
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
</template>

<script>
import axios from "axios"
import Multiselect from '@vueform/multiselect'
import "@vueform/multiselect/themes/default.css"
export default {
 components: {
           Multiselect
   },

  data() {
    return {
       selectedFailures: [],
       results: [],
       value: null,
       options: [
         "pc_wont_turn_on", 
         "high_temperature_of_device", 
         "array_of_short_sound_signals",
         "high_surge_voltages",
         "plugged_in_during_thunderstorm",
         "freeze",
         "slow_computer",
         "blue_screen",
         "lost_data",
         "antivirus_failure",
         "periodically_restarting",
         "cant_connect_to_internet",
         "os_loading",
         "it_takes_long_time_to_save_files",
         "dropped_computer",
         "foreign_artifacts",
         "input_devices_are_not_working",
         "dropped_input_devices",
         "black_display",
         "no_recognize_storage_device",
         "no_sound",
         "slow_internet"
        ],
        requestDtos: []
    };
  },
  mounted() {
  },
  methods: {
       sendRequest: function(event){
           event.preventDefault()
           this.requestDtos=[]
           if(this.value != null){
                    for(let i=0; i < this.value.length ; i++){
                          console.log(this.value[i])
                          this.requestDtos.push({ "failure" : this.value[i]})
                    }
           }
           
           axios
           .post("http://localhost:8081/faultProbability/findDamage",this.requestDtos)
           .then((response) => {
                  this.results=response.data
            })

       },
       sort: function(resultList){
           return resultList.sort((a,b)=>{if(a.percentage < b.percentage) return 1; else return -1})
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
#zaSliku{
     background-image: url('../assets/background.jpg') ;
     background-repeat: no-repeat;
      background-size: 100% 100%;
      width: 100.5%; 
      height: 100%;
}




#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
