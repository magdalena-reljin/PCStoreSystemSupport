import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import RecommendingComponents from '../views/RecommendingComponents.vue'

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
  
   
]

const router = createRouter({
  
  history: createWebHistory(),
  routes,
})

export default router





