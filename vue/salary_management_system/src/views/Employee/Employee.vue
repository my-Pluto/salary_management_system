<!--
 * @FileDescription: 员工侧边导航组件
 * @Author: 陈辰
 * @Date: 
 * @LastEditors: 陈辰
 * @LastEditTime:
-->
<template>
	<div>
		<el-container>
			<el-aside width="180px">
				<el-row class="tac">
					<el-col>
						<el-menu default-active="/employee/employeedata" class="el-menu-vertical-demo" @select="handleOpen">
							<el-menu-item index="/employee/employeedata">
								<i class="el-icon-s-custom"></i>
								<span slot="title">员工查询</span>
							</el-menu-item>
							<el-menu-item index="/employee/newemployee">
								<i class="el-icon-circle-plus-outline"></i>
								<span slot="title">新增员工</span>
							</el-menu-item>
						</el-menu>
					</el-col>
				</el-row>
			</el-aside>
			<el-main>
				<router-view />
			</el-main>
		</el-container>
	</div>
</template>

<script>
	export default {
		name: 'Employee',
		data() {
			return {
				activeIndex: this.$route.path //激活的菜单项的值
			}
		},
		methods: {
			/**
			 * @description 点击侧边菜单触发，导向key值对应的路由的组件进行显示
			 * @param {Object} key
			 * @param {Object} keyPath
			 * @return {void}
			 */
			handleOpen(key, keyPath) {
				this.$router.push(key);
			},
		},
		/**
		 * @description 组件创建时触发，导向employeedata组件
		 */
		created() {
			this.activeIndex = '/employee';
			this.$router.push("/employee/employeedata");
			this.$parent.activeIndex = '/employee';
		}
	}
</script>

<style>
	html,
	body,
	#app,
	.el-container {
		/*设置内部填充为0，几个布局元素之间没有间距*/
		padding: 0px;
		/*外部间距也是如此设置*/
		margin: 0px;
		/*统一设置高度为100%*/
		height: 100%;
	}

	.el-menu-item {
		margin-left: 20px;
	}
</style>
