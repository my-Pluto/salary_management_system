<!--
 * @FileDescription: 员工表格组件
 * @Author: 陈辰
 * @Date: 
 * @LastEditors: 陈辰
 * @LastEditTime:
-->
<template>
	<div>
		<el-table :data="tableData" border style="width: 100%">
			<el-table-column fixed prop="id" label="员工编号" width="300">
			</el-table-column>
			<el-table-column prop="name" label="员工姓名" width="250">
			</el-table-column>
			<el-table-column prop="sex" label="员工性别" width="250">
			</el-table-column>
			<el-table-column prop="department" label="员工所在部门编号" width="300">
			</el-table-column>
			<el-table-column prop="position" label="员工所在岗位编号" width="250">
			</el-table-column>
			<el-table-column prop="email" label="员工邮件" width="250">
			</el-table-column>
			<el-table-column prop="entryTime" label="员工进入时间" width="250">
			</el-table-column>
			<el-table-column width="210">
				<template slot="header" slot-scope="scope">
					<el-button type="primary" icon="el-icon-search" @click="findDialogEmerge" v-show="flag">搜索</el-button>
					<el-button type="primary" v-show="flag1" @click="returnTable">返回</el-button>
				</template>
				<template slot-scope="scope">
					<!-- <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button> -->
					<el-button type="text" size="small" @click='updateDialogEmerge(scope.row)'>更改员工</el-button>
					<el-button type="text" size="small" @click='deleteEmployee(scope.row)'>删除员工</el-button>
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
		<el-dialog title="更新对话框" :visible.sync="dialogFormVisible" style="width: 1200px;">
			<el-form :model="form" :rules="rules" ref="form">
				<el-form-item label="部门编号" style="width: 1000px;" :label-width="formLabelWidth" prop="department">
					<el-select v-model="form.department" style="width: 300px;" filterable placeholder="请选择部门名称" @change="getPositions">
						<el-option v-for="item in departments" :key="item.id" :label="item.id" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="岗位编号" style="width: 500px;" :label-width="formLabelWidth" prop="position">
					<el-select v-model="form.position" style="width: 300px;" filterable placeholder="请选择岗位名称">
						<el-option v-for="item in positions" :key="item.id" :label="item.id" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="员工编号" style="width: 500px;" :label-width="formLabelWidth" prop="id">
					<el-input v-model="form.id" autocomplete="off" placeholder="请输入员工编号"></el-input>
				</el-form-item>
				<el-form-item label="员工姓名" style="width: 500px;" :label-width="formLabelWidth" prop="name">
					<el-input v-model="form.name" autocomplete="off" placeholder="请输入员工名称"></el-input>
				</el-form-item>
				<el-form-item label="员工性别" style="width: 500px;" :label-width="formLabelWidth" prop="sex">
					<el-select v-model="form.sex" style="width: 300px;" placeholder="请选择员工性别">
						<el-option v-for="item in options" :key="item.value" :label="item.value" :value="item.value">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="员工邮箱" style="width: 500px;" :label-width="formLabelWidth" prop="email">
					<el-input v-model="form.email" autocomplete="off" placeholder="请输入员工邮件"></el-input>
				</el-form-item>
				<el-form-item label="入职时间" style="width: 500px;" :label-width="formLabelWidth" prop="department">
					<el-col :span="40">
						<el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="form.date" style="width: 100%;"></el-date-picker>
					</el-col>
				</el-form-item>
				<el-form-item style="margin-top: 50px;margin-left: 200px;">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="submitForm('form')">确 定</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
		<el-dialog title="查询对话框" :visible.sync="dialogVisible" style="width: 1200px;">
			<el-form :model="form1">
				<el-form-item label="员工编号" style="width: 500px;" :label-width="formLabelWidth" prop="id">
					<el-input v-model="form1.id" autocomplete="off" placeholder="请输入员工编号"></el-input>
				</el-form-item>
				<el-form-item label="员工名称" style="width: 500px;" :label-width="formLabelWidth" prop="name">
					<el-input v-model="form1.name" autocomplete="off" placeholder="请输入员工名称"></el-input>
				</el-form-item>
				<el-form-item label="员工性别" style="width: 500px;" :label-width="formLabelWidth" prop="sex">
					<el-select v-model="form1.sex" style="width: 300px;" placeholder="请选择员工性别">
						<el-option v-for="item in options" :key="item.value" :label="item.value" :value="item.value">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="员工部门编号" style="width: 500px;" :label-width="formLabelWidth" prop="department">
					<el-input v-model="form1.department" autocomplete="off" placeholder="请输入员工部门编号"></el-input>
				</el-form-item>
				<el-form-item label="员工岗位编号" style="width: 500px;" :label-width="formLabelWidth" prop="position">
					<el-input v-model="form1.position" autocomplete="off" placeholder="请输入员工岗位编号"></el-input>
				</el-form-item>
				<el-form-item label="员工邮箱" style="width: 500px;" :label-width="formLabelWidth" prop="email">
					<el-input v-model="form1.name" autocomplete="off" placeholder="请输入员工邮箱"></el-input>
				</el-form-item>
				<el-form-item label="员工进入时间" style="width: 500px;" :label-width="formLabelWidth" prop="entryTime">
					<el-col :span="40">
						<el-date-picker type="date" placeholder="选择日期" v-model="form1.date" style="width: 100%;"></el-date-picker>
					</el-col>
				</el-form-item>
				<el-form-item style="margin-top: 50px;margin-left: 200px;">
					<el-button @click="dialogVisible = false">取 消</el-button>
					<el-button type="primary" @click="findEmployee">确 定</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'EmployeeData',
		data() {
			//员工姓名验证规则
			var checkName = (rule, value, callback) => {
				const nameReg = /^([a-zA-Z0-9\u4e00-\u9fa5\·]{1,10})$/
				if (!value) {
					return callback(new Error('姓名不能为空'))
				}
				setTimeout(() => {
					if (nameReg.test(value)) {
						callback()
					} else {
						callback(new Error('请输入正确的姓名格式'))
					}
				}, 100)
			}
			//邮箱验证
			var checkEmail = (rule, value, callback) => {
				const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
				if (!value) {
					return callback(new Error('邮箱不能为空'))
				}
				setTimeout(() => {
					if (mailReg.test(value)) {
						callback()
					} else {
						callback(new Error('请输入正确的邮箱格式'))
					}
				}, 100)
			}
			return {
				flag: true,//控制搜索按钮显示
				flag1: false,//控制返回按钮显示
				options: [{
					value: '男'
				}, {
					value: '女'
				}],//性别选择框
				tableData: [], //表单数据列表
				pageNow: 1, //当前的页数
				size: 10, //每页的用户数
				total: 100, //所有的用户数总和
				dialogFormVisible: false,//控制查找会话显示
				provinces: [],//省份列表
				departments: [],//部门列表
				positions: [],//岗位列表
				form: {
					id: '',
					name: '',
					sex: '',
					department: '',
					position: '',
					email: '',
					date: ''
				},//更改表单绑定
				form1: {
					id: '',
					name: '',
					sex: '',
					department: '',
					position: '',
					email: '',
					date: ''
				},//查询表单绑定
				formLabelWidth: '120px',//表单标签宽度
				rules: {
					id: [{
						required: true,
						message: '请输入员工编号',
						trigger: 'blur'
					}],
					name: [{
						required: true,
						validator: checkName,
						trigger: 'blur'
					}],
					sex: [{
						required: true,
						message: '请输入员工性别',
						trigger: 'change'
					}],
					department: [{
						required: true,
						message: '请输入员工部门',
						trigger: 'change'
					}],
					position: [{
						required: true,
						message: '请输入员工岗位',
						trigger: 'change'
					}],
					email: [{
						required: true,
						validator: checkEmail,
						trigger: 'blur'
					}],
					date: [{
						required: true,
						message: '请输入员工进入时间',
						trigger: 'change'
					}]
				},//验证规则
				dialogVisible: false//修改会话显示
			}
		},

		methods: {
			/**
			 * @description 回到原来的表格
			 */
			returnTable() {
				this.flag = !this.flag;
				this.flag1 = !this.flag1;
				this.getData();
			},
			/**
			 * @description  表单验证
			 * @param {Object} formName
			 */
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.updateEmployee();
					} else {
						return false;
					}
				});
			},
			/**
			 * @description 获得所有部门
			 */
			getDepartments() {
				this.$axios.get("department/departments", {}).then(res => {
					this.departments = res.data.data;
				})
			},
			/**
			 * @description 根据部门id获得所有岗位
			 */
			getPositions() {
				this.$axios.post("/position/positions/1/1000", {
					departmentId: this.form1.department
				}).then(res => {
					this.positions = res.data.data.records;
				})
			},
			/**
			 * @description 查找员工
			 */
			findEmployee() {
				this.$axios.post('/employee/employees/' + this.pageNow + '/' + this.size, {
					id: this.form1.id,
					name: this.form1.name,
					sex: this.form1.sex,
					department: this.form1.department,
					position: this.form1.position,
					email: this.form1.email,
					entryTime: this.form1.date,
				}).then(res => {
					if (res.data.code == '200') {
						this.$message({
							type: 'success',
							message: '查询成功',
							duration: '1000'
						});
						this.tableData = res.data.data.records;
						this.tatal = res.data.data.total;
						this.flag = !this.flag;
						this.flag1 = !this.flag1;
						this.dialogVisible = false;
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg,
							duration: '700',
						});
					}
				})
			},
			/**
			 * @description 查找会话显示
			 */
			findDialogEmerge() {
				this.dialogVisible = true;
			},
			/**
			 * @description 更新会话显示
			 * @param {Object} row
			 */
			updateDialogEmerge(row) {
				this.form.id=row.id;
				this.form.name=row.name;
				this.form.sex=row.sex;
				this.form.department=row.department;
				this.form.position=row.position;
				this.form.email=row.email;
				this.form.date=row.date;
				this.dialogFormVisible = true;
				this.$axios.get("http://localhost:8081/department/departments/provinces").then(res => {
					this.provinces = res.data.data;
				})
			},
			/**
			 * @description 更新员工
			 */
			updateEmployee() {
				this.$axios.post('http://localhost:8081/employee/' + this.form.id, {
					'id':this.form.id,
					"name": this.form.name,
					"sex": this.form.sex,
					"department": this.form.department,
					"position": this.form.position,
					"email": this.form.email,
					"entryTime": this.form.date
				}).then(res => {
					if (res.data.code == '200') {
						this.$message({
							type: 'success',
							message: '更新成功',
							duration: '1000'
						});
						this.dialogFormVisible = false;
						this.getData();
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg,
							duration: '700'
						});
					}
				})
			},
			/**
			 * @description 点击删除按钮后触发，删除指定行的员工
			 * @param {Object} row
			 * @return {void}
			 */
			deleteEmployee(row) {
				this.$confirm('您正在删除该职位, 是否继续?', '警告', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('http://localhost:8081/employee/' + row.id, {
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
			/**
			 * @description 用于获取数据到表单列表中并返回总数给total赋值
			 * @param {Object} page
			 * @param {Object} size
			 * @return {void}
			 */
			getData(page, size) {
				page = page ? page : this.pageNow; //有page参数page=page,没有时page为默认的pageNow值
				size = size ? size : this.size; //有size参数size=size,没有时size为默认的this.size值
				this.$axios.get("/employee/employees/" + page + '/' + size).then(res => {
					if (res.data.code == '200') {
						this.tableData = res.data.data.records;
						this.total = res.data.data.total;
					} else {
						this.$message.error(res.data.msg);
					}
				})
			},

			/**
			 * @description 在表单页数改变时触发，显示改变后的表单
			 * @param {Object} page
			 * @return {void}
			 */
			findPage(page) {
				this.pageNow = page; //更新page的值
				this.getData(page, this.size); //显示表单
			},
			/**
			 * @description 在表单每页的数量改变时触发，显示改变时的表单
			 * @param {Object} size
			 */
			findSize(size) {
				this.size = size; //更新size的值
				this.getData(this.pageNow, size); //显示表单
			}
		},

		created() {
			this.getData(); //创建时显示表单
			this.$axios.get("http://localhost:8081/department/departments").then(res => {
				this.departments = res.data.data;
			})

		}
	}
</script>

<style>
</style>
