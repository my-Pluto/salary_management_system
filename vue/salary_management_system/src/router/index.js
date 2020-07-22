import Vue from 'vue'
import Router from 'vue-router'

import Login from '../views/Login.vue'
import Index from '../views/Index.vue'
import Users from '../views/User/Users.vue'
import UsersData from '../views/User/UsersData.vue'
import NewUser from '../views/User/NewUser.vue'
import Department from '../views/Department/Department.vue'
import DepartmentData from '../views/Department/DepartmentData.vue'
import NewDepartment from '../views/Department/NewDepartment.vue'
import FixedItem from '../views/Salary/FixedItem/FixedItem.vue'
import FixedItemData from '../views/Salary/FixedItem/FixedItemData.vue'
import NewFixedItem from '../views/Salary/FixedItem/NewFixedItem.vue'
import AllFixedItemData from '../views/Salary/FixedItem/AllFixedItemData.vue'
import DeptFixedItemData from '../views/Salary/FixedItem/DeptFixedItemData.vue'
import CountItem from '../views/Salary/CountItem/CountItem.vue'
import CountItemData from '../views/Salary/CountItem/CountItemData.vue'
import NewCountItem from '../views/Salary/CountItem/NewCountItem.vue'

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
	},
	{
		path: '/department',
		name:'department',
		component: Department,
		meta: {
			requireAuth: true
		},
		children: [{
			path: 'departmentdata',
			name: 'departmentdata',
			component: DepartmentData,
			meta: {
				requireAuth: true
			}
		},
		{
			path: 'newdepartment',
			name:'newdepartment',
			component: NewDepartment,
			meta: {
				requireAuth: true
			}
		}]
	},
	{
		path: '/fixeditem',
		name: 'fixeditem',
		component: FixedItem,
		meta: {
			requireAuth: true
		},
		children: [
			{
				path: 'fixeditemdata',
				name: 'fixeditemdata',
				component: FixedItemData,
				meta: {
					requireAuth: true
				}
			},
			{
				path: 'allfixeditemdata',
				name: 'allfixeditemdata',
				component: AllFixedItemData,
				meta: {
					requireAuth: true
				}
			},
			{
				path: 'newfixeditem',
				name: 'newfixeditem',
				component: NewFixedItem,
				meta: {
					requireAuth: true
				}
			},
			{
				path:'deptfixeditemdata',
				name: 'deptfixeditemdata',
				component: DeptFixedItemData,
				meta: {
					requireAuth: true
				}
			}
		]
	},
	{
		path: '/countitem',
		name:'countitem',
		component: CountItem,
		meta: {
			requireAuth: true
		},
		children: [{
			path: 'countitemdata',
			name: 'countitemdata',
			component: CountItemData,
			meta: {
				requireAuth: true
			}
		},
		{
			path: 'newcountitem',
			name:'newcountitem',
			component: NewCountItem,
			meta: {
				requireAuth: true
			}
		}]
	},
]

const router = new Router({
	mode: 'history',
	routes
})

export default router
