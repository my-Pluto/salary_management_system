<!--
 * @FileDescription: 岗位表单组件
 * @Author: 陈辰
 * @Date: 
 * @LastEditors: 陈辰
 * @LastEditTime:
-->
<template>
	<div>
		<el-table :data="tableData" border style="width: 100%">
			<el-table-column fixed prop="id" label="岗位号" width="300">
			</el-table-column>
			<el-table-column prop="name" label="岗位名称" width="250">
			</el-table-column>
			<el-table-column prop="departmentId" label="部门号" width="250">
			</el-table-column>
			<el-table-column prop="department" label="岗位所在部门" width="250">
			</el-table-column>
			<el-table-column width="210">
				<template slot="header" slot-scope="scope">
					<el-button type="primary" icon="el-icon-search" @click="findDialogEmerge" v-show="flag">搜索</el-button>
					<el-button type="primary" v-show="flag1" @click="returnTable">返回</el-button>
				</template>
				<template slot-scope="scope">
					<!-- <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button> -->
					<el-button type="text" size="small" @click='updateDialogEmerge(scope.row)'>更改岗位</el-button>
					<el-button type="text" size="small" @click='deletePosition(scope.row)'>删除岗位</el-button>
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
			<el-form :model="form" :rules="rules" ref="form" style="margin-left: 50px">
				<el-form-item label="部门所在省份" style="width: 1000px;" :label-width="formLabelWidth" prop="province">
					<el-select v-model="form.province" style="width: 300px;" filterable placeholder="请选择岗位部门所在省" @change="getDepartments">
						<el-option v-for="item in provinces" :key="item.value" :label="item.label" :value="item.label">
						</el-option>
					</el-select>
				</el-form-item>

				<el-form-item label="部门名称" style="width: 400px;" :label-width="formLabelWidth" prop="department">
					<el-select v-model="form.department" style="width: 300px;" filterable placeholder="请选择岗位部门" @change="findDepartmentId">
						<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.name">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="岗位名称" style="width: 400px;" :label-width="formLabelWidth" prop="position">
					<el-input v-model="form.position" autocomplete="off" placeholder="请输入岗位名称" @change="findPositionId"></el-input>
				</el-form-item>
				<el-form-item style="margin-top: 50px;margin-left: 150px;">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="submitForm('form')">确 定</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
		<el-dialog title="查询对话框" :visible.sync="dialogVisible" style="width: 1200px;">
			<el-form :model="form1">
				<el-form-item label="部门编号" style="width: 500px;" :label-width="formLabelWidth" prop="departmentId">
					<el-select v-model="form1.departmentId" placeholder="请选择" @change="fillDepartmentName">
						<el-option v-for="item in departments1" :key="item.id" :label="item.id" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="部门名称" style="width: 500px;" :label-width="formLabelWidth" prop="departmentName">
					<el-input v-model="form1.departmentName" autocomplete="off" placeholder="请输入部门名称" ></el-input>
				</el-form-item>
				<el-form-item label="岗位编号" style="width: 500px;" :label-width="formLabelWidth" prop="id">
					<el-select v-model="form1.id" placeholder="请选择" @change="fillPositionName">
						<el-option v-for="item in positions" :key="item.id" :label="item.id" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="岗位名称" style="width: 500px;" :label-width="formLabelWidth" prop="name">
					<el-input v-model="form1.position" autocomplete="off" placeholder="请输入岗位名称"></el-input>
				</el-form-item>
				<el-form-item style="margin-top: 50px;margin-left: 200px;">
					<el-button @click="dialogVisible = false">取 消</el-button>
					<el-button type="primary" @click="findPosition">确 定</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'PositionData',
		data() {
			return {
				provinces: [],//省份列表
				departments: [],//根据省份获得部门列表
				departments1: [],//所有部门列表
				flag: true,//控制搜索按钮显示
				flag1: false,//控制返回按钮显示
				tableData: [], //表单数据列表
				pageNow: 1, //当前的页数
				size: 10, //每页的用户数
				total: 100, //所有的用户数总和
				dialogFormVisible: false,//控制更新会话显示
				form: {
					position: '',//岗位
					department: '',//部门
					province: ''//省份
				},//更新表单
				form1: {
					position: '',//岗位
					departmentId: '',//部门编号
					departmentName: '',//部门名称
					id:''
				},//查询表单
				formLabelWidth: '120px',//表单标签宽度
				rules: {
					province: [{
						required: true,
						message: '请输入省份名字',
						trigger: 'change'
					}],
					position: [{
						required: true,
						message: '请输入岗位名字',
						trigger: 'blur'
					}],
					department: [{
						required: true,
						message: '请输入岗位所在部门',
						trigger: 'change'
					}],
				},
				dialogVisible: false,//控制查询会话显示
				departmentId: 0,//更新部门号
				positionId: 0,//更新岗位号
				positions:[]//部门列表
			}
		},

		methods: {
			/**
			 * @description 查询岗位
			 */
			findPositions(){
				this.$axios.post("/position/positions/1/1000",{
					departmentId:this.form1.departmentId
				}).then(res => {
					this.positions = res.data.data.records;
				})
			},
			/**
			 * @description 根据部门编号填充部门名称
			 */
			fillDepartmentName() {
				this.$axios.get("http://localhost:8081/department/" + this.form1.departmentId).then(res => {
					this.form1.departmentName = res.data.data.name;
				})
				this.findPositions();
			},
			/**
			 * @description 根据岗位编号填充岗位名称
			 */
			fillPositionName(){
				this.$axios.post("/position/positions/1/1000",{
					id:this.form1.id
				}).then(res => {
					this.form1.position = res.data.data.records[0].name;
				})
			},
			/**
			 * @description 查找岗位id
			 */
			findPositionId() {
				this.$axios.post('/position/positions/' + this.pageNow + '/' + this.size, {
					'name': this.form.position,
					'departmentId': this.departmentId
				}).then(res => {
					this.positionId = res.data.data.records[0].id;
				})
			},
			/**
			 * @description 查询部门id
			 */
			findDepartmentId() {
				this.$axios.post('/department/department', {
					'name': this.form.department,
					'province': this.form.province
				}).then(res => {
					this.departmentId = res.data.data[0].id;
				})
			},
			/**
			 * @description 返回原来的表格
			 */
			returnTable() {
				this.flag = !this.flag;
				this.flag1 = !this.flag1;
				this.getData();
			},
			/**
			 * @description 表单验证
			 * @param {Object} formName
			 */
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.updatePosition();
					} else {
						return false;
					}
				});
			},
			/**
			 * @description 根据省份获得部门
			 */
			getDepartments() {
				this.$axios.post("http://localhost:8081/department/department", {
					'province': this.form.province
				}).then(res => {
					this.departments = res.data.data;
				})
			},
			/**
			 * @description 查找部门
			 */
			findPosition() {
				this.$axios.post('/position/positions/' + this.pageNow + '/' + this.size, {
					'name': this.form1.position,
					'departmentId': this.form1.departmentId,
					'id':this.form1.id
				}).then(res => {
					if (res.data.code == '200') {
						this.dialogVisible = false;
						this.$message({
							type: 'success',
							message: '查询成功',
							duration: '1000'
						});
						this.tableData = res.data.data.records;
						this.tatal = res.data.data.total;
						this.flag = !this.flag;
						this.flag1 = !this.flag1;
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg,
							duration: '700',
						});
						this.dialogVisible = false;
					}
				})
			},
			/**
			 * @description 查找界面显示动作
			 */
			findDialogEmerge() {
				this.dialogVisible = true;
				this.form1.position='',
				this.form1.departmentId='',
				this.form1.departmentName=''
				this.$axios.get('/department/departments', {}).then(res => {
					if (res.data.code == '200') {
						this.departments1 = res.data.data;
					} else {

					}
				})
			},
			/**
			 * @description 更新会话显示动作
			 * @param {Object} row
			 */
			updateDialogEmerge(row) {
				this.dialogFormVisible = true;
				this.positionId = row.id;
				this.form.province = '';
				this.form.department = '';
				this.form.position = '';
				this.$axios.get("http://localhost:8081/department/departments/provinces").then(res => {
					this.provinces = res.data.data;
				})
			},
			/**
			 * @description 更新岗位
			 */
			updatePosition() {
				this.$axios.post('http://localhost:8081/position/' + this.positionId, {
					'id':this.positionId,
					'name': this.form.position,
					'departmentId': this.departmentId,
					'department': this.form.department
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
			 * @description 点击删除按钮后触发，删除指定行的岗位
			 * @param {Object} row
			 * @return {void}
			 */
			deletePosition(row) {
				this.$confirm('您正在删除该职位, 是否继续?', '警告', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('http://localhost:8081/position/' + row.id, {
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
				this.$axios.get("/position/positions/" + page + '/' + size).then(res => {
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
		}
	}
</script>

<style>
</style>
