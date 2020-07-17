import Vue from 'vue'
import App from './App.vue'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import axios from 'axios'
import Router from 'vue-router'
import store from './store'

import Login from './views/Login.vue'


Vue.config.productionTip = false
Vue.use(Element)
// axios.defaults.baseURL = '/api'
Vue.prototype.$axios = axios
Vue.use(Router)

const routes = [
	{
		path: '/',
		component: Login
	},
	{
		path: '/login',
		component: Login
	}
]

const router = new Router({
	routes
})

new Vue({
	router,
	store: store,
  render: h => h(App),
}).$mount('#app')
