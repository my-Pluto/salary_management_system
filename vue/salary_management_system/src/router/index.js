import Vue from 'vue'
import Router from 'vue-router'

import Login from '../views/Login.vue'
import Index from '../views/Index.vue'
import Users from '../views/User/Users.vue'
import UsersData from '../views/User/UsersData.vue'
import NewUser from '../views/User/NewUser.vue'

Vue.use(Router)

const routes = [{
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
		meta: {
			requireAuth: true
		},
		children: [{
				path: 'usersdata',
				name: 'usersdata',
				component: UsersData,
				meta: {
					requireAuth: true
				}
			},
			{
				path: 'newuser',
				name: 'newuser',
				component: NewUser,
				meta: {
					requireAuth: true
				}
			}
		]
	}
]

const router = new Router({
	mode: 'history',
	routes
})

export default router
