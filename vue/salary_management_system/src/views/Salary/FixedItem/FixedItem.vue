<template>
	<div>
		<el-container>
			<el-aside width="180px">
				<el-row class="tac">
					<el-col>
						<el-menu default-active="/fixeditem/fixeditemdata" class="el-menu-vertical-demo" @select="handleOpen">
							<el-menu-item index="/fixeditem/fixeditemdata">
								<i class="el-icon-user"></i>
								<span slot="title">固定项目查询</span>
							</el-menu-item>
							<el-menu-item index="/fixeditem/newfixeditem">
								<i class="el-icon-circle-plus-outline"></i>
								<span slot="title">新增固定项目</span>
							</el-menu-item>
							<el-menu-item index="/fixeditem/deptfixeditemdata">
								<i class="el-icon-setting"></i>
								<span slot="title">数据批量维护</span>
							</el-menu-item>
							<el-menu-item index="/fixeditem/allfixeditemdata">
								<i class="el-icon-edit"></i>
								<span slot="title">项目数据查询</span>
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
		name: 'FixedItem',
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
			this.activeIndex = '/fixeditem/fixeditemdata';
			this.$router.push("/fixeditem/fixeditemdata");
			this.$parent.activeIndex = '/fixeditem';
		}
	}
</script>

<style>
</style>
