<template>
	<div>
		<el-button icon="el-icon-search" circle style="float: right; margin-bottom: 0.3125rem;" @click='dialogTableVisible=true'></el-button>
		<el-table :data="tableData" border style="width: 100%">
			<el-table-column fixed prop="id" label="ID" width="150" sortable>
			</el-table-column>
			<el-table-column fixed prop="name" label="姓名" width="150">
			</el-table-column>
			<el-table-column fixed prop="department_name" label="部门" width="150">
			</el-table-column>
			<el-table-column v-for="item in th" fixed :prop='item' :label='item' width="150">
			</el-table-column>
			<!-- <el-table-column fixed="right" label="操作" width="200">
					<template slot-scope="scope">
						<el-button type="text" size="small" @click='update(scope.row)' style="margin-right: 10px;">编辑</el-button>
						<el-popconfirm title="确定删除？" @onConfirm='deleteItem(scope.row)'>
							<el-button type="text" size="small" slot="reference">删除</el-button>
						</el-popconfirm>
	-->
			<!-- <el-button type="text" size="small" slot="reference" @click='deleteItem(scope.row)'>删除</el-button> -->
			<!-- </template> -->
			</el-table-column>
		</el-table>
		<el-row>
			<el-col :span="10" :offset='8'>
				<el-pagination layout="prev, pager, next, jumper, total, sizes" style="margin-top: 20px; margin-bottom: 20px; margin-right: 0px; margin-left: 0px;"
				 @current-change="findPage" @size-change="findSize" :page-size='size' :current-page="pageNow" :total='total'>
				</el-pagination>
			</el-col>
		</el-row>

		<el-dialog title="查询(模糊查询)" :visible.sync="dialogTableVisible">
			<el-form label-width="200px">
				<el-form-item label="请选择部门" prop="department" style="margin-top: 3.125rem;">
					<el-select v-model="department" placeholder="请选择部门" @change="getEmployees">
						<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id" />
					</el-select>
				</el-form-item>
				<el-form-item label="请选择员工" style="margin-top: 3.125rem;">
					<el-select v-model="employee_id" placeholder="请选择员工">
						<el-option v-for="item in employee_ids" :key="item.id" :label="item.name" :value="item.id" />
					</el-select>
				</el-form-item>
				<el-form-item label="请选择导入项目" style="margin-top: 3.125rem;">
					<el-select v-model="itemname" placeholder="请选择导入项目">
						<el-option v-for="item in itemnames" :key="item.name" :label="item.name" :value="item.name" />
					</el-select>
				</el-form-item>
				<el-form-item label="请输入时间" style="margin-top: 3.125rem; width: 420px;">
					<div class="block">
						<el-date-picker v-model="lowMonth" value-format="yyyy-MM" type="month" placeholder="请输入时间">
						</el-date-picker>
					</div>
					<!-- <div class="block">
						<el-date-picker v-model="highMonth" value-format="yyyy-MM" type="month" placeholder="结束月">
						</el-date-picker>
					</div> -->
				</el-form-item>
			</el-form>
			<el-button type="primary" @click='getData(1)' style="margin-left: 300px;">查询</el-button>
			<el-button @click="fun1" tyle="margin-left: 300px;">取消</el-button>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'AllImportItemData',
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

			update(row) {

				delete row.department_id;
				delete row.department_name;
				delete row.name;
				const id = row.id;
				delete row.id;
				console.log(row);
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
				this.$axios.get("/importitem/importitem").then(res => {
					if (res.data.code == '200') {
						this.itemnames = res.data.data;
					}
				})
			},

			fun1() {
				this.dialogTableVisible = false;
			},

			findPage(page) {
				this.page = page; //更新page的值
				this.getData(page, this.size); //显示表单
			},
			/**
			 * @description 在表单每页的数量改变时触发，显示改变时的表单
			 * @param {Object} size
			 */
			findSize(size) {
				this.size = size; //更新size的值
				this.getData(this.page, size); //显示表单
			},
			// open(item, row) {
			// 	this.$prompt('请输入修改', '修改', {
			// 		confirmButtonText: '确定',
			// 		cancelButtonText: '取消',
			// 		inputPattern: '^[0-9]*$',
			// 		inputErrorMessage: '请输入正确金额'
			// 	}).then(({
			// 		value
			// 	}) => {

			// 		this.$axios.post("/importitemdata//importitemdata/" + row.id, {
			// 			item: value
			// 		}).then(res => {
			// 			if (res.data.code == '200') {
			// 				this.$message({
			// 					type: 'success',
			// 					message: res.data.msg
			// 				});
			// 			} else {
			// 				this.$message({
			// 					type: 'error',
			// 					message: res.data.msg
			// 				});
			// 			}
			// 		})

			// 	}).catch(() => {
			// 		this.$message({
			// 			type: 'info',
			// 			message: '取消输入'
			// 		});
			// 	});
			// },


			getData(page, size) {
				page = page ? page : this.pageNow; //有page参数page=page,没有时page为默认的pageNow值
				size = size ? size : this.size;
				this.$axios.post("/importitemdata/importitemdatas/" + page + "/" + size, {
					deptId: this.department,
					itemName: this.itemname,
					employeeId: this.employee_id,
					lowMonth: this.lowMonth,
					highMonth: this.lowMonth
				}).then(res => {
					if (res.data.code == '200') {
						this.total = res.data.data.total;
						this.th = res.data.data.names;
						res.data.data.data.forEach(element => {
							element["show"] = false
						});
						this.tableData = res.data.data.data;
						console.log(res.data.data.data);
					}
				});
				this.dialogTableVisible = false;
			},


		},

		created() {
			this.dialogTableVisible = true;
			this.getDepartments();
			this.getItemNames();
		}
	}
</script>

<style>
</style>
