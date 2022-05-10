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
               <h3 style="text-align:left;" class="mb-0">My computer components</h3>
   </div>
   <div  class="card-body"  style="text-align: left;">
    <form  @submit="sendRequest" method='post' >
        <div class="form-group">
            <label><b>Motherboard:</b></label>
            <select v-model="selected" class="form-select">
            <option v-for="(motherboard,index) in motherboards" :key="index">
                    {{motherboard}}
            </option>
        </select>
          
        </div>
          <br>

        <div class="form-group">
          <label><b>Processor:</b></label>
          <select v-model="selected3" class="form-select">
            <option v-for="(processor,index) in processors" :key="index">
                    {{processor}}
            </option>
        </select>
          
        </div>
  <br>
        <div class="form-group">
          <label><b>I want to upgrade:</b></label>
          <select v-model="selected2" class="form-select">
            <option>
                PROCESSOR
            </option>
            <option>
                RAM 
            </option>

        </select>
        </div>
       

       
        

        <br>
        <div class="row">
             <div class="col">
             <div class="form-group" style="text-align: right;">
             <button  type="submit" class="btn" style="background-color: #b0638e; color: white">Find components</button>
             </div>
             </div>
            
        </div>
    </form>
</div>
</div>
</div>
</div>
<br>

<hr>
<div class="list-group" style="padding-left: 5%; padding-right: 5%; text-align: left;" >
    <h3 >Compatible components</h3>
    <hr>
    <a v-for="(foundComponent,index) in foundComponents" :key="index"  href="#" class="list-group-item list-group-item-action" style=" font-size: 125%;">
    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-boxes" viewBox="0 0 16 16">
  <path d="M7.752.066a.5.5 0 0 1 .496 0l3.75 2.143a.5.5 0 0 1 .252.434v3.995l3.498 2A.5.5 0 0 1 16 9.07v4.286a.5.5 0 0 1-.252.434l-3.75 2.143a.5.5 0 0 1-.496 0l-3.502-2-3.502 2.001a.5.5 0 0 1-.496 0l-3.75-2.143A.5.5 0 0 1 0 13.357V9.071a.5.5 0 0 1 .252-.434L3.75 6.638V2.643a.5.5 0 0 1 .252-.434L7.752.066ZM4.25 7.504 1.508 9.071l2.742 1.567 2.742-1.567L4.25 7.504ZM7.5 9.933l-2.75 1.571v3.134l2.75-1.571V9.933Zm1 3.134 2.75 1.571v-3.134L8.5 9.933v3.134Zm.508-3.996 2.742 1.567 2.742-1.567-2.742-1.567-2.742 1.567Zm2.242-2.433V3.504L8.5 5.076V8.21l2.75-1.572ZM7.5 8.21V5.076L4.75 3.504v3.134L7.5 8.21ZM5.258 2.643 8 4.21l2.742-1.567L8 1.076 5.258 2.643ZM15 9.933l-2.75 1.571v3.134L15 13.067V9.933ZM3.75 14.638v-3.134L1 9.933v3.134l2.75 1.571Z"/>
</svg>
<b> &nbsp;&nbsp;{{foundComponent}}</b>
    </a>
</div>

</template>

<script>
   import axios from "axios"
  
export default {
 

  data() {
    return {
       selected: '',
       selected2: '',
       selected3: '',
       motherboards: [],
       processors: [],
       foundComponents: []
       
    };
  },
  mounted() {
     this.getMotherBoards()
  },
  methods: {
       recommending: function(){
         this.$router.push("/recommendingComponents")
       },
       getMotherBoards: function(){
           axios
               .get("http://localhost:8081/recommendingComponents/motherboards")
               .then((response) => {
                  this.motherboards=response.data
                  this.getProcessors()

               })

       },
       getProcessors: function(){
                
                     axios
                    .get("http://localhost:8081/recommendingComponents/processors")
                    .then((response) => {
                        this.processors=response.data
                    })
       },
       sendRequest: function(event){
           event.preventDefault()
           if(this.selected2==""){
                this.$swal.fire({
                 position: 'top-end',
                  icon: 'error',
                 title: 'Please choose component you want to upgrade',
               showConfirmButton: false,
               timer: 2500
           })
           return;
           }
           
           if(this.selected2=="PROCESSOR"){
               this.findProcessors()
           }

          
       },
       findProcessors: function(){
           if(this.selected=="") {
                this.$swal.fire({
                 position: 'top-end',
                  icon: 'error',
                 title: 'Please choose motherboard',
               showConfirmButton: false,
               timer: 1500
           })
           return;
           }
           console.log("izabran je "+this.selected)
              axios
               .post("http://localhost:8081/recommendingComponents/findProcessors/"+this.selected)
               .then((response) => {
                   console.log("rez "+response.data)
                   this.foundComponents=response.data
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
