<template>
	<div>
		<el-form :model="ruleForm" ref="ruleForm" style="margin-left: 18.75rem; margin-top: 6.25rem;"
		 label-width="200px" class="demo-ruleForm">
			<el-form-item label="请选择部门" prop="department" style="margin-top: 3.125rem;">
				<el-select v-model="ruleForm.department" placeholder="请选择部门">
					<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id" />
				</el-select>
			</el-form-item>
			<el-form-item label="请选择导入项目" prop="itemname" style="margin-top: 3.125rem;">
				<el-select v-model="ruleForm.itemname" placeholder="请选择导入项目">
					<el-option v-for="item in itemnames" :key="item.id" :label="item.name" :value="item.id" />
				</el-select>
			</el-form-item>
			<el-form-item label="请选择日期" prop="itemname" style="margin-top: 3.125rem;">
				<div class="block">
				    <el-date-picker
					value-format="yyyy-MM"
				      v-model="ruleForm.month"
				      type="month"
				      placeholder="选择月">
				    </el-date-picker>
				  </div>
			</el-form-item>
			<el-form-item label="请输入默认数据" prop="data" style="margin-top: 3.125rem; width: 420px;">
				<el-input v-model.number="ruleForm.data"></el-input>
			</el-form-item>
			<el-form-item style="margin-top: 3.125rem;">
				<el-button type="primary" @click="submititem">立即修改</el-button>
				<el-button @click="resetForm('ruleForm')">重置</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		name:'DeptImportItem',
		data() {
			return {
				departments: [],
				itemnames: [],
				name: '',
				departmentname: '',
				ruleForm: {
					itemname: '',
					department: '',
					month:'',
					data: 0
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
				this.$axios.get("/importitem/importitem").then(res => {
					if (res.data.code == '200') {
						this.itemnames = res.data.data;
						console.log(this.itemnames);
					}
				})
			},
		
			submititem() {
				console.log(this.ruleForm);
				this.$axios.post("/importitem/importitem/1/5", {
					id: this.ruleForm.itemname
				}).then(res => {
					if (res.data.code == '200') {
						this.name = res.data.data.records[0].name;
						this.$axios.get("/department/" + this.ruleForm.department).then(res => {
							if (res.data.code == '200') {
								this.departmentname = res.data.data.name;
								this.$axios.post("/importitemdata/importitemdata", {
									itemName: this.name,
									itemId: this.ruleForm.itemname,
									deptId: this.ruleForm.department,
									deptName: this.departmentname,
									month:this.ruleForm.month,
									data: this.ruleForm.data
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
