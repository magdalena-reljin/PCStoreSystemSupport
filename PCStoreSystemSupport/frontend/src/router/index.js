import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import RecommendingComponents from '../views/RecommendingComponents.vue'
import EstimatingValue from '../views/EstimatingValue.vue'
import FaultProbability from '../views/FaultProbability'
import SimilarPCs from '../views/SimilarPCs'
const routes = [
  
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/recommendingComponents',
    name: 'RecommendingComponents',
    component: RecommendingComponents
  },
  {
    path: '/estimatingValue',
    name: 'EstimatingValue',
    component: EstimatingValue
  },
  {
    path: '/faultProbability',
    name: 'FaultProbability',
    component: FaultProbability
  },
  {
    path: '/similarpcs',
    name: 'SimilarPCs',
    component: SimilarPCs
  },
  
   
]

const router = createRouter({
  
  history: createWebHistory(),
  routes,
})

export default router





