import Vue from 'vue'
import App from './App.vue'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import axios from 'axios'
import Router from 'vue-router'
import store from './store'

import Login from './views/Login.vue'
import Index from './views/Index.vue'

Vue.config.productionTip = false
Vue.use(Element)
Vue.prototype.$axios = axios
Vue.use(Router)

const routes = [
	{
		path: '/',
		redirect: 'login',
		},
	{
		path: '/login',
		name: 'login',
		component: Login
	},
	{
		path: '/index',
		name: 'index',
		component: Index
	}
]

const router = new Router({
	mode:'history',
	routes
})

new Vue({
	router,
	store: store,
  render: h => h(App),
}).$mount('#app')
