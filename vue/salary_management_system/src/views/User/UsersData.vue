<template>
	<div>
		<el-table :data="tableData" border style="width: 100%">
			<el-table-column fixed prop="id" label="工号" width="300">
			</el-table-column>
			<el-table-column prop="name" label="姓名" width="250">
			</el-table-column>
			<el-table-column prop="department" label="部门" width="250">
			</el-table-column>
			<el-table-column prop="gmtCreate" label="创建时间" width="250">
			</el-table-column>
			<el-table-column label="操作" width="210">
				<template slot-scope="scope">
					<!-- <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button> -->
					<el-button type="text" size="small" @click='updateUser(scope.row)'>重置密码</el-button>
					<el-button type="text" size="small" @click='deleteUser(scope.row)'>删除用户</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-row>
			<el-col :span="10" :offset='8'>
				<el-pagination layout="prev, pager, next, jumper, total, sizes" style="margin-top: 20px; margin-bottom: 20px; margin-right: 0px; margin-left: 0px;"
				 @current-change="findPage" @size-change="findSize" :page-size='size' :current-page="pageNow" :total='total'>
				</el-pagination>
			</el-col>
		</el-row>

	</div>
</template>

<script>
	export default {
		name: 'UsersData',
		data() {
			return {
				tableData: [],
				pageNow: 1,
				size: 10,
				total: 0,
			}
		},
		
		methods: {

			deleteUser(row) {
				this.$confirm('您正在删除用户, 是否继续?', '警告', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('http://localhost:8081/user/' + row.id, {
						headers: {
							"Authorization": localStorage.getItem("token")
						}
					}).then(res => {
						if (res.data.code == '200') {
							this.$message({
								type: 'success',
								message: res.data.msg,
								duration: '700'
							});
							this.getData();
						} else {
							this.$message({
								type: 'error',
								message: res.data.msg,
								duration: '700'
							});
						}
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除',
						duration: '700'
					});
				});
			},

			getData(page, size) {
				page = page ? page : this.pageNow;
				size = size ? size : this.size;
				this.$axios.get("http://localhost:8081/user/users/" + page + '/' + size).then(res => {
					if (res.data.code == '200') {
						this.$message({
							message: res.data.msg,
							type: 'success',
							duration: '700'
						});
						this.tableData = res.data.data.records;
						this.total = res.data.data.total;
					} else {
						this.$message.error(res.data.msg);
					}
				})
			},

			updateUser(row) {
				this.$confirm('您正在为 工号为 ' + row.id + ' 姓名为 ' + row.name + ' 的用户重置密码', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.post('http://localhost:8081/user/' + row.id, {
						'id': row.id,
						'name': row.name,
						'password': 'e10adc3949ba59abbe56e057f20f883e'
					}).then(res => {
						if (res.data.code == '200') {
							this.$message({
								type: 'success',
								message: '已将 ' + row.name + ' 将密码重置为123456',
								duration: '1000'
							});
						} else {
							this.$message({
								type: 'error',
								message: res.data.msg,
								duration: '700'
							});
						}
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消',
						duration: '700'
					});
				});
			},

			findPage(page) {
				this.page = page;
				this.getData(page, this.size);
			},

			findSize(size) {
				this.size = size;
				this.getData(this.page, size);
			}
		},

		created() {
			this.getData();
		}
	}
</script>

<style>
</style>
