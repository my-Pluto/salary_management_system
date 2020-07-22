<template>
	<div>
		<el-container>
			<el-aside width="180px">
				<el-row class="tac">
					<el-col>
						<el-menu default-active="/importitem/importitemdata" class="el-menu-vertical-demo" @select="handleOpen">
							<el-menu-item index="/importitem/importitemdata">
								<i class="el-icon-user"></i>
								<span slot="title">导入项目查询</span>
							</el-menu-item>
							<el-menu-item index="/importitem/newimportitem">
								<i class="el-icon-circle-plus-outline"></i>
								<span slot="title">新增导入项目</span>
							</el-menu-item>
							<el-menu-item index="/importitem/allimportitemdata">
								<i class="el-icon-search"></i>
								<span slot="title">项目数据查询</span>
							</el-menu-item>
							<el-menu-item index="/importitem/deptimportitem">
								<i class="el-icon-setting"></i>
								<span slot="title">数据批量维护</span>
							</el-menu-item>
							<el-menu-item index="/importitem/employeeimportitem">
								<i class="el-icon-edit"></i>
								<span slot="title">个人数据维护</span>
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
		name: 'ImportItem',
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
		 * @description 组件创建时触发，导向usersdata组件
		 */
		created() {
			this.activeIndex = '/importitem/importitemdata';
			this.$router.push("/importitem/importitemdata");
			this.$parent.activeIndex = '/importitem';
		}
	}
</script>

<style>
</style>
