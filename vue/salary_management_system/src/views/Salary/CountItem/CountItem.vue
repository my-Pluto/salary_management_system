<template>
	<el-container>
		<el-aside width="180px">
			<el-row class="tac">
				<el-col>
					<el-menu default-active="/countitem/countitemdata" class="el-menu-vertical-demo" @select="handleOpen">
						<el-menu-item index="/countitem/countitemdata">
							<i class="el-icon-user"></i>
							<span slot="title">计算项目查询</span>
						</el-menu-item>
						<el-menu-item index="/countitem/newcountitem">
							<i class="el-icon-circle-plus-outline"></i>
							<span slot="title">新增计算项目</span>
						</el-menu-item>
					</el-menu>
				</el-col>
			</el-row>
		</el-aside>
		<el-main>
			<router-view />
		</el-main>
	</el-container>
</template>

<script>
	export default {
		name: 'CountItem',
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
			this.activeIndex = '/countitem/countitemdata';
			this.$router.push("/countitem/countitemdata");
			this.$parent.activeIndex = '/countitem';
		}
	}
</script>

<style>
</style>
