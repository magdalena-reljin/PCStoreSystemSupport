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
            <select v-model="selectedMotherboard"  class="form-select">
            <option v-for="(motherboard, key) in motherboards" :key="key" :value="key">
                   {{motherboard.producer}} {{motherboard.name}} 
            </option>
        </select>
          
        </div>
          <br>

        <div class="form-group">
          <label><b>Processor:</b></label>
          <select v-model="selectedProcessor" class="form-select">
            <option  v-for="(processor,key) in processors" :key="key" :value="key" >
                    {{processor.name}}[{{processor.numOfCores}},{{processor.motherboardSocket}},{{processor.frequency}}MHz,TDP:{{processor.tdp}},{{processor.operatingMode}}]
            </option>
        </select>
          
        </div>
        <br>

         <div class="form-group">
          <label><b>Graphic card:</b></label>
          <select v-model="selectedGPU" class="form-select" >
            <option v-for="(gpu,key) in gpus" :key="key" :value="key">
                    {{gpu.name}}[{{gpu.memoryType}} {{gpu.memorySize}}GB, minPsu:{{gpu.minPSU}}]
            </option>
        </select>
          
        </div>
        <br>

         <div class="form-group">
          <label><b>RAM:</b></label>
          <select v-model="selectedRAM" class="form-select" >
            <option v-for="(ram,key) in rams" :key="key" :value="key">
                    {{ram.producer}} {{ram.name}} [{{ram.type}} {{ram.capacity}}GB, {{ram.frequency}}MHz]
            </option>
        </select>
          
        </div>
        
        <br>

         <div class="form-group">
          <label><b>Cooler:</b></label>
          <select v-model="selectedCooler" class="form-select" >
            <option v-for="(cooler,key) in coolers" :key="key" :value="key">
                    {{cooler.name}} [TDP: {{cooler.tdp}}, {{cooler.maxFanSpeed}}, {{cooler.noiseLevel}}]
            </option>
        </select>
          
        </div>
        
        <br>
      
        <div class="row">
             <div class="col">
             <div class="form-group" style="text-align: right;">
             <button  type="submit" class="btn" style="background-color: #b0638e; color: white">Find similar PC</button>
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
    <h3 >Similar PCs</h3>
    <hr>
    <a v-for="(result,index) in results" :key="index"  href="#" class="list-group-item list-group-item-action" style=" font-size: 125%;">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-reception-4" viewBox="0 0 16 16">
  <path d="M0 11.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2zm4-3a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5v5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-5zm4-3a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5v8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-8zm4-3a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5v11a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-11z"/>
</svg>
<b> &nbsp;&nbsp;{{format(result)}}</b>
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
  
export default {
 

  data() {
    return {
       selectedMotherboard: '',
       selectedProcessor: '',
       selectedGPU: '',
       selectedRAM: '',
       selectedCooler: '',
       mPar: '',
       pPar: '',
       gPar: '',
       rPar: '',
       cPar: '',
       motherboards: [],
       processors: [],
       gpus: [],
       rams: [],
       coolers: [],
       results: []
    };
  },
  mounted() {
     this.getCompontents()
  },
  methods: {
       getCompontents: function(){
                
                    axios
                    .get("http://localhost:8081/recommendingComponents/motherboardsForCbr")
                    .then((response) => {
                         this.motherboards = response.data;

                         axios
                        .get("http://localhost:8081/recommendingComponents/processorsForCbr")
                        .then((response) => {
                            this.processors=response.data

                                axios
                                .get("http://localhost:8081/recommendingComponents/gpusForCbr")
                                .then((response) => {
                                    this.gpus=response.data

                                        axios
                                        .get("http://localhost:8081/recommendingComponents/ramsForCbr")
                                        .then((response) => {
                                            this.rams=response.data
                                            axios
                                                        .get("http://localhost:8081/recommendingComponents/coolersForCbr")
                                                        .then((response) => {
                                                            this.coolers=response.data
                                                        })
                                        })
                                })
                        })
                    })
       },
       sendRequest: function(event){
           event.preventDefault()
          
           this.mPar=this.motherboards[this.selectedMotherboard]
           this.pPar=this.processors[this.selectedProcessor]
           this.gPar=this.gpus[this.selectedGPU]
           this.rPar=this.rams[this.selectedRAM]
           this.cPar=this.coolers[this.selectedCooler]
           this.results=[]
           axios
           .post("http://localhost:8081/cbr/findSimilarPCs",
           {
              motherboard: this.mPar,
              processor: this.pPar,
              gpu: this.gPar,
              cooler: this.cPar,
              ram: this.rPar
           })
           .then((response) => {
            this.results=response.data
            })

       },
       format: function(value){
         return value.replaceAll('*', '\n')
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
