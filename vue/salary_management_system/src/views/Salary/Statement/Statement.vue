<template>
	<div>
		<el-container>
			<el-aside width="180px">
				<el-row class="tac">
					<el-col>
						<el-menu default-active="/statement/statementdata" class="el-menu-vertical-demo" @select="handleOpen">
							<el-menu-item index="/statement/statementdata">
								<i class="el-icon-user"></i>
								<span slot="title">工资查询</span>
							</el-menu-item>
							<el-menu-item index="/statement/countstatement">
								<i class="el-icon-circle-plus-outline"></i>
								<span slot="title">工资计算</span>
							</el-menu-item>
							<el-menu-item index="/statement/salary">
								<i class="el-icon-setting"></i>
								<span slot="title">工资发放</span>
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
		name:'Statement',
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
			this.activeIndex = '/statement/statementdata';
			this.$router.push("/statement/statementdata");
			this.$parent.activeIndex = '/statement';
		}
	}
</script>

<style>
</style>
