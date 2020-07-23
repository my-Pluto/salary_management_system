<template>
	<div>
		<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" background-color="#545c64"
		 text-color="#fff" active-text-color="#ffd04b" v-if="show">
			<el-menu-item index="/index">主页</el-menu-item>
			<el-submenu index="/personnel">
							<template slot="title">人事管理</template>
							<el-menu-item index="/department" >部门管理</el-menu-item>
							<el-menu-item index="/employee">员工管理</el-menu-item>
							<el-menu-item index="/position">岗位管理</el-menu-item>
						</el-submenu>
			<el-submenu index="/salary">
				<template slot="title">工资管理</template>
				<el-menu-item index="/fixeditem">固定项目管理</el-menu-item>
				<el-menu-item index="/countitem">计算项目管理</el-menu-item>
				<el-menu-item index="/importitem">导入项目管理</el-menu-item>
				<el-menu-item index="/statement">结算管理</el-menu-item>
			</el-submenu>
			<!-- <el-menu-item index="/statement">报表管理</el-menu-item> -->
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
				<el-button @click="notExit">取 消</el-button>
				<el-button type="primary" @click="exitSystem" v-loading.fullscreen.lock="fullscreenLoading">确 定</el-button>
			</span>
		</el-dialog>
		<router-view />
	</div>
</template>

<script>
	import md5 from 'js-md5';
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
				if (key == '/user/password') {
					this.updatePassword();
					return;
				}
				this.$router.push(key);
			},

			updatePassword() {
				this.$prompt('请输入新密码（仅包含数字和字母）', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					inputPattern: /^[a-zA-Z0-9]+$/,
					inputErrorMessage: '密码格式不正确'
				}).then(({
					value
				}) => {
					console.log(md5(value));
					
					this.$axios.post("http://localhost:8081/user/" + this.$store.state.userInfo.id, {
						id: this.$store.state.userInfo.id,
						name: this.$store.state.userInfo.name,
						password: md5(value)
					}).then(res => {
						if (res.data.code == '200') {
							this.$message({
								type: 'success',
								message: '修改密码成功！'
							});
							location.reload();
						}
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '取消输入'
					});
					location.reload();
				});
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
			notExit() {
				this.dialogVisible = false
									location.reload();
			}
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
