<template>
	<div>
		<el-form :model="ruleForm" :rules="rules" ref="ruleForm" style="margin-left: 18.75rem; margin-top: 6.25rem;"
		 label-width="200px" class="demo-ruleForm">
			<el-form-item label="请选择部门" prop="department" style="margin-top: 3.125rem;">
				<el-select v-model="ruleForm.department" placeholder="请选择部门">
					<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id" />
				</el-select>
			</el-form-item>
			<el-form-item label="请选择固定项目" prop="itemname" style="margin-top: 3.125rem;">
				<el-select v-model="ruleForm.itemname" placeholder="请选择固定项目">
					<el-option v-for="item in itemnames" :key="item.id" :label="item.name" :value="item.id" />
				</el-select>
			</el-form-item>
			<el-form-item label="请输入金额" prop="salary" style="margin-top: 3.125rem; width: 420px;">
				<el-input v-model.number="ruleForm.salary"></el-input>
			</el-form-item>
			<el-form-item style="margin-top: 3.125rem;">
				<el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
				<el-button @click="resetForm('ruleForm')">重置</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		name: 'DeptFixedItemData',
		data() {
			return {
				departments: [],
				itemnames: [],
				name: '',
				departmentname: '',
				ruleForm: {
					itemname: '',
					department: '',
					salary: 0
				},
				rules: {

					department: [{
						required: true,
						message: '请选择部门',
						trigger: 'change'
					}],
					itemname: [{
						required: true,
						message: '请选择固定项目',
						trigger: 'change'
					}],
					salary: [{
							required: true,
							message: '金额不能为空'
						},
						{
							type: 'number',
							message: '金额必须为数字值'
						}
					]
				}
			};
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.submit();
					} else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},

			getDepartments() {
				this.$axios.get("/department/departments").then(res => {
					if (res.data.code == '200') {
						this.departments = res.data.data;
					}
				})
			},

			getItemNames() {
				this.$axios.get("/fixeditem/fixeditem").then(res => {
					if (res.data.code == '200') {
						this.itemnames = res.data.data;
						console.log(this.itemnames);
					}
				})
			},

			submit() {
				this.$axios.post("/fixeditem/fixeditem/1/5", {
					id: this.ruleForm.itemname
				}).then(res => {
					if (res.data.code == '200') {
						this.name = res.data.data.records[0].name;
						this.$axios.get("/department/" + this.ruleForm.department).then(res => {
							if (res.data.code == '200') {
								this.departmentname = res.data.data.name;
								console.log(this.name + " ",
									this.ruleForm.itemname + " ",
									this.ruleForm.department + " ",
									this.departmentname + " ",
									this.ruleForm.salary);
								this.$axios.post("/fixeditemdata/fixeditemdata", {
									name: this.name,
									itemId: this.ruleForm.itemname,
									deptId: this.ruleForm.department,
									deptName: this.departmentname,
									salary: this.ruleForm.salary
								}).then(res => {
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
							} else {
								this.$message({
									type: 'error',
									message: '更新失败！'
								});
								return;
							}
						});
					} else {
						this.$message({
							type: 'error',
							message: '更新失败！'
						});
						return;
					}
				});




			}
		},

		created() {
			this.getDepartments();
			this.getItemNames();
		}

	}
</script>

<style>
</style>
