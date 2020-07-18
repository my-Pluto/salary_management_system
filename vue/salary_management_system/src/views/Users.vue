<template>
	<div>
		<el-container>
			<el-aside width="180px">
				<el-row class="tac">
					<el-col>
						<el-menu default-active="/users/usersdata" class="el-menu-vertical-demo" @select="handleOpen">
							<el-menu-item index="/users/usersdata">
								<i class="el-icon-s-custom"></i>
								<span slot="title">用户查询</span>
							</el-menu-item>
							<el-menu-item index="/users/adduser">
								<i class="el-icon-circle-plus-outline"></i>
								<span slot="title">新增用户</span>
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
		name: 'Users',
		data() {
			return {
				activeIndex: this.$route.path
			}
		},
		methods: {
			handleOpen(key, keyPath) {
				this.$router.push(key);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			}
		},
		created() {
			this.activeIndex = '/users/usersdata';
			this.$router.push("/users/usersdata");
			this.$parent.activeIndex = '/users';
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
