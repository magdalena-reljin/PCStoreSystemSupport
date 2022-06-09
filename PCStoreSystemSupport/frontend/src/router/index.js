import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import RecommendingComponents from '../views/RecommendingComponents.vue'
import EstimatingValue from '../views/EstimatingValue.vue'
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
  
   
]

const router = createRouter({
  
  history: createWebHistory(),
  routes,
})

export default router





