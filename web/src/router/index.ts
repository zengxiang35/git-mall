import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import AdminBrand from '../views/admin/admin-brand.vue'
import AdminSpecification from '../views/admin/admin-specification.vue'
import AdminGoods from '../views/admin/admin-goods.vue'
import AdminCategory from '../views/admin/amdin-category.vue'
import AdminTemplates from '../views/admin/admin-templates.vue'
import AdminContent from '../views/content/admin-content.vue'


const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/admin/brand',
    name: 'AdminBrand',
    component: AdminBrand
  },
  {
    path: '/admin/specification',
    name: 'AdminSpecification',
    component: AdminSpecification
  },
  {
    path: '/admin/templates',
    name: 'AdminTemplates',
    component: AdminTemplates
  },
  {
    path: '/admin/goods',
    name: 'AdminGoods',
    component: AdminGoods
  },
  {
    path: '/admin/category',
    name: 'AdminCategory',
    component: AdminCategory
  },
  {
    path: '/content/content',
    name: 'AdminContent',
    component: AdminContent
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
