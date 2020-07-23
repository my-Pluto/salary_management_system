<!--
 * @FileDescription: 部门侧边导航栏组件
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
						<el-menu default-active="/department/departmentdata" class="el-menu-vertical-demo" @select="handleOpen">
							<el-menu-item index="/department/departmentdata">
								<i class="el-icon-s-custom"></i>
								<span slot="title">部门查询</span>
							</el-menu-item>
							<el-menu-item index="/department/newdepartment">
								<i class="el-icon-circle-plus-outline"></i>
								<span slot="title">新增部门</span>
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
		name: 'Department',
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
		 * @description 组件创建时触发，导向departmentdata组件
		 */
		created() {
			this.activeIndex = '/department/departmentdata';
			this.$router.push("/department/departmentdata");
			this.$parent.activeIndex = '/department';
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
