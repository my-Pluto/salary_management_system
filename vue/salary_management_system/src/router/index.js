import Vue from 'vue'
import Router from 'vue-router'

import Login from '../views/Login.vue'
import Index from '../views/Index.vue'
import Users from '../views/Users.vue'
import UsersData from '../views/UsersData.vue'

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
		component: Index,
		meta: {
			requireAuth: true
		}
	},
	{
		path: '/users',
		name: 'users',
		component: Users,
		children: [
			{
				path: 'usersdata',
				name: 'usersdata',
				component: UsersData
			},
		]
	}
]

const router = new Router({
	mode:'history',
	routes
})

export default router