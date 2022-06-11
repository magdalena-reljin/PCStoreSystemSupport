import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import RecommendingComponents from '../views/RecommendingComponents.vue'
import EstimatingValue from '../views/EstimatingValue.vue'
import FaultProbability from '../views/FaultProbability'
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
  
   
]

const router = createRouter({
  
  history: createWebHistory(),
  routes,
})

export default router





