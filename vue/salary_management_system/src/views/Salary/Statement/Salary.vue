<template>
	<div>
		<el-form ref="form" :model="form" label-width="150px" style="margin-left: 300px;">
			<el-form-item label="请选择部门" prop="department" style="margin-top: 3.125rem;">
				<el-select v-model="department" placeholder="请选择部门">
					<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id" />
				</el-select>
			</el-form-item>
			<el-form-item label="请输入时间" style="margin-top: 3.125rem; width: 420px;">
				<div class="block">
					<el-date-picker v-model="month" value-format="yyyy-MM" type="month" placeholder="请输入时间">
					</el-date-picker>
				</div>
				<!-- <div class="block">
						<el-date-picker v-model="highMonth" value-format="yyyy-MM" type="month" placeholder="结束月">
						</el-date-picker>
					</div> -->
			</el-form-item>
			<el-button type="primary" @click="count" style="margin-left: 200px; margin-top: 50px;">立即发放</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		name:'Salary',
		data() {
			return {
				departments: [],
				department: '',
				month: ''
			}
		},
		methods: {
			getDepartments() {
				this.$axios.get("/department/departments").then(res => {
					if (res.data.code == '200') {
						this.departments = res.data.data;
					}
				})
			},
		
			count() {
				this.$axios.get("/statement/salary/provided/" + this.department + "/" + this.month).then(res => {
					if (res.data.code == '200') {
						this.$message({
							type: 'success',
							message: res.data.msg,
							duration: '700'
						});
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg,
							duration: '700'
						});
					}
				})
			}
		},
		
		created() {
			this.getDepartments();
		}
	}
</script>

<style>
</style>
