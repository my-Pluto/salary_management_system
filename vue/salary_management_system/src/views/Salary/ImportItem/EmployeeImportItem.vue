<template>
	<div style="margin-left: 18.75rem;">
	<el-form label-width="200px">
		<el-form-item label="请选择部门" prop="department" style="margin-top: 3.125rem;">
			<el-select v-model="department" placeholder="请选择部门" @change="getEmployees">
				<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id" />
			</el-select>
		</el-form-item>
		<el-form-item label="请选择员工" style="margin-top: 3.125rem;">
			<el-select v-model="employee_id" placeholder="请选择员工" @change="getItemNames">
				<el-option v-for="item in employee_ids" :key="item.id" :label="item.name" :value="item.id" />
			</el-select>
		</el-form-item>
		<el-form-item label="请选择导入项目" style="margin-top: 3.125rem;" @change="getData">
			<el-select v-model="itemname" placeholder="请选择导入项目">
				<el-option v-for="item in itemnames" :key="item.name" :label="item.name" :value="item.name" />
			</el-select>
		</el-form-item>
		<el-form-item label="请输入时间" style="margin-top: 3.125rem; width: 420px;">
			<div class="block">
				<el-date-picker @change="getData" v-model="lowMonth" value-format="yyyy-MM" type="month" placeholder="请输入时间">
				</el-date-picker>
			</div>
			<!-- <div class="block">
				<el-date-picker v-model="highMonth" value-format="yyyy-MM" type="month" placeholder="结束月">
				</el-date-picker>
			</div> -->
		</el-form-item>
		<el-form-item label="请输入数据" style="margin-top: 3.125rem; width: 420px;">
			<el-input v-model.number="data" placeholder="请输入数据"></el-input>
		</el-form-item>
	</el-form>
	<el-button type="primary" @click="updateItem" style="margin-left: 250px;">更新</el-button>
	</div>
</template>

<script>
	export default {
		name: 'EmployeeImportItem',
		data() {
			return {
				tableData: [],
				th: [],
				pageNow: 1, //当前的页数
				size: 10, //每页的用户数
				total: 0, //所有的总和
				department: '',
				dialogTableVisible: false,
				lowMonth: '',
				highMonth: '',
				itemname: '',
				itemnames: [],
				departments: [],
				employee_id: '',
				employee_ids: [],
				data:'',
				temp:{}
		
		
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
		
			updateItem() {
				this.$axios.post("/importitemdata/importitemdata/employee", {
					employeeId: this.employee_id,
					itemName:this.itemname,
					month:this.lowMonth
				}).then(res => {
					if(res.data.code == '200') {
						this.temp = res.data.data;
						console.log(this.temp);
						this.$axios.put("/importitemdata/importitemdata/employee", {
							id:this.temp.id,
							itemName:this.temp.itemName,
							itemId:this.temp.itemId,
							deptName:this.temp.deptName,
							deptId:this.temp.deptId,
							employeeName:this.temp.employeeName,
							employeeId:this.temp.employeeId,
							data:this.data,
							month:this.temp.month
						}).then(res => {
							if (res.data.code == '200') {
								this.$message({
									type: 'success',
									message: res.data.msg,
									duration: '700'
								});
							}
						})
					}
				})
				
			},
		
			getEmployees() {
				console.log(this.department);
				this.$axios.post("http://localhost:8081/employee/employees", {
					department: this.department
				}).then(res => {
					this.employee_ids = res.data.data;
				})
			},
		
			getItemNames() {
				this.$axios.post("/importitem/importitem/1/100", {
					employee_id: this.employee_id
				}).then(res => {
					if (res.data.code == '200') {
						this.itemnames = res.data.data.records;
					}
				})
			},
			
			getData() {
				console.log(this.employee_id);
				this.$axios.post("/importitemdata/importitemdata/employee", {
					employeeId: this.employee_id,
					itemName:this.itemname,
					month:this.lowMonth
				}).then(res => {
					if(res.data.code == '200') {
						this.data = res.data.data.data;
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
