<template>
	<div>
		<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" background-color="#545c64"
		 text-color="#fff" active-text-color="#ffd04b" v-if="show">
			<el-menu-item index="/index">主页</el-menu-item>
			<el-submenu index="/personnel">
				<template slot="title">人事管理</template>
				<el-menu-item index="/personnel/department">部门管理</el-menu-item>
				<el-menu-item index="/personnel/employee">员工管理</el-menu-item>
				<el-menu-item index="/personnel/position">岗位管理</el-menu-item>
			</el-submenu>
			<el-submenu index="/salary">
				<template slot="title">工资管理</template>
				<el-menu-item index="/salary/fixed_item">固定项目管理</el-menu-item>
				<el-menu-item index="/salary/count_item">计算项目管理</el-menu-item>
				<el-menu-item index="/salary/import_item">导入项目管理</el-menu-item>
				<el-menu-item index="3-3">结算管理</el-menu-item>
			</el-submenu>
			<el-menu-item index="/statement">报表管理</el-menu-item>
			<el-menu-item index="/users" v-if="userManagement">用户管理</el-menu-item>

			<el-submenu index="user" style="float:right;">
				<template slot="title">
					<el-avatar>{{username}}</el-avatar>
				</template>
				<el-menu-item index="/user/password">修改密码</el-menu-item>
				<el-menu-item index="/user/exit">注销</el-menu-item>
			</el-submenu>
			</el-menu-item>
		</el-menu>
		<el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
			<span>是否退出系统？</span>
			<span slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="exitSystem" v-loading.fullscreen.lock="fullscreenLoading">确 定</el-button>
			</span>
		</el-dialog>
		<router-view />
	</div>
</template>

<script>
	export default {
		name: 'app',
		data() {
			return {
				show: false,
				activeIndex: this.$route.path,
				userManagement: false,
				username: '',
				dialogVisible: false,
				fullscreenLoading: false
			}
		},
		methods: {
			handleSelect(key, keyPath) {
				if (key == '/user/exit') {
					this.dialogVisible = true;
					return;
				}
				this.$router.push(key);
			},
			exitSystem() {
				this.fullscreenLoading = true;
				setTimeout(() => {
					this.fullscreenLoading = false;
				}, 1000);


				this.$axios.get('http://localhost:8081/loginout', {
					headers: {
						"Authorization": localStorage.getItem("token")
					}
				}).then(res => {
					if (res.data.code == '200') {
						this.$message({
							message: res.data.msg,
							type: 'success'
						});
						this.$store.commit("REMOVE_INFO");
						this.dialogVisible = false;
						this.username = '';
						this.activeIndex = '/index';
						this.$router.push('/login');
					} else {
						this.$message.error(res.data.msg);
					}
				})
			},
		},

		created() {
			if (this.$store.state.token == null || this.$store.state.token == '') {
				this.show = false;
			} else {
				this.show = true;
				if (this.$store.state.userInfo.name == 'admin') {
					this.userManagement = true;
					this.username = 'admin';
				} else {
					this.userManagement = false;
					this.username = this.$store.state.userInfo.name;
				}
			}
		}
	}
</script>

<style>
</style>
