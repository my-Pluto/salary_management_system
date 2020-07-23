<!--
 * @FileDescription: 部门表格组件
 * @Author: 陈辰
 * @Date: 
 * @LastEditors: 陈辰
 * @LastEditTime:
-->
<template>
	<div>
		<el-table :data="tableData" border style="width: 100%">
			<el-table-column fixed prop="id" label="部门号" width="300">
			</el-table-column>
			<el-table-column prop="name" label="部门名称" width="250">
			</el-table-column>
			<el-table-column prop="province" label="部门所在省份" width="250">
			</el-table-column>
			<el-table-column width="210">
				<template slot="header" slot-scope="scope">
					<el-button type="primary" icon="el-icon-search" v-show="flag" @click="findDialogEmerge">搜索</el-button>
					<el-button type="primary" v-show="flag1" @click="returnTable">返回</el-button>
				</template>
				<template slot-scope="scope">
					<!-- <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button> -->
					<el-button type="text" size="small" @click='updateDialogEmerge(scope.row)'>更改部门</el-button>
					<el-button type="text" size="small" @click='deleteDepartment(scope.row)'>删除部门</el-button>
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
			<el-form :model="form" :rules="rules" ref="form" style="margin-left: 60px;">
				<el-form-item label="部门名称" style="width: 370px;" :label-width="formLabelWidth" prop="name">
					<el-input v-model="form.name" autocomplete="off" placeholder="请输入部门名称"></el-input>
				</el-form-item>
				<el-form-item label="部门所在省份" style="width: 500px;" :label-width="formLabelWidth" prop="province">
					<el-select v-model="form.province" style="width: 250px;" filterable placeholder="请选择添加部门所在省份">
						<el-option v-for="item in provinces" :key="item.id" :label="item.name" :value="item.name">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item style="margin-top: 50px;margin-left: 150px;">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="submitForm('form')">确 定</el-button>
				</el-form-item>
			</el-form>

		</el-dialog>
		<el-dialog title="查询对话框" :visible.sync="dialogVisible" style="width: 1200px;">
			<el-form :model="form1" style="margin-left: 50px;">
				<el-form-item label="部门所在省" style="width: 500px;" :label-width="formLabelWidth" prop="province">
					<el-select v-model="form1.province" style="width: 250px;" filterable placeholder="请选择部门所在省" @change="getDepartments">
						<el-option v-for="item in provinces1" :key="item.label" :label="item.lebel" :value="item.label">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="部门编号" style="width: 500px;" :label-width="formLabelWidth" prop="id">
					<el-select v-model="form1.id" style="width: 250px;" filterable placeholder="请选择部门编号" @change="fillname">
						<el-option v-for="item in departments" :key="item.id" :label="item.id" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="部门名称" style="width: 375px;" :label-width="formLabelWidth" prop="name">
					<el-input v-model="form1.name" autocomplete="off" placeholder="请输入部门名称"></el-input>
				</el-form-item>
				<el-form-item style="margin-left: 150px;">
					<el-button @click="dialogVisible = false">取 消</el-button>
					<el-button type="primary" @click="findDepartment">确 定</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'DepartmentData',
		data() {
			return {
				flag: true,//控制查询按钮显示，true显示，false不显示
				flag1: false,//控制
				tableData: [], //表单数据列表
				pageNow: 1, //当前的页数
				size: 10, //每页的用户数
				total: 100, //所有的用户数总和
				dialogFormVisible: false,//控制更新对话框显示，true显示，false不显示
				form: {
					name: '',//部门名字
					province: ''//部门所在省份
				},//更新表单
				form1: {
					id: '',//部门编号
					name: '',//部门名称
					province: ''//部门所在省
				},//查找表单
				formLabelWidth: '120px',//表单标签宽度
				provinces: [{
						id: '1',//省份id
						name: "北京"//省份名称
					}, {
						id: '2',
						name: "天津"
					}, {
						id: '3',
						name: "上海"
					}, {
						id: '4',
						name: "重庆"
					}, {
						id: '5',
						name: "河北"
					},
					{
						id: '6',
						name: "山西"
					}, {
						id: '7',
						name: "辽宁"
					}, {
						id: '8',
						name: "吉林"
					}, {
						id: '9',
						name: "黑龙江"
					}, {
						id: '10',
						name: "江苏"
					},
					{
						id: '11',
						name: "浙江"
					}, {
						id: '12',
						name: "安徽"
					}, {
						id: '13',
						name: "福建"
					}, {
						id: '14',
						name: "江西"
					}, {
						id: '15',
						name: "山东"
					}, {
						id: '16',
						name: "河南"
					}, {
						id: '17',
						name: "湖北"
					}, {
						id: '18',
						name: "湖南"
					},
					{
						id: '19',
						name: "广东"
					}, {
						id: '20',
						name: "海南"
					}, {
						id: '21',
						name: "四川"
					}, {
						id: '22',
						name: "贵州"
					}, {
						id: '23',
						name: "云南"
					}, {
						id: '24',
						name: "陕西"
					}, {
						id: '25',
						name: "甘肃"
					}, {
						id: '26',
						name: "青海"
					},
					{
						id: '27',
						name: "台湾"
					}, {
						id: '28',
						name: "内蒙古"
					}, {
						id: '29',
						name: "广西"
					}, {
						id: '30',
						name: "西藏"
					}, {
						id: '31',
						name: "宁夏"
					}, {
						id: '32',
						name: "新疆"
					}, {
						id: '33',
						name: "香港"
					}, {
						id: '34',
						name: "澳门"
					}
				], //用户所在城市列表
				departments: [],//部门列表
				provinces1: [],//建立部门的省份列表
				rowId: 0,//表格当前行的id
				rules: {
					name: [{
						required: true,
						message: '请选择部门名字',
						trigger: 'blur'
					}],
					province: [{
						required: true,
						message: '请选择部门所在省份',
						trigger: 'change'
					}],
				},
				dialogVisible: false//控制查询对话显示
			}
		},

		methods: {
			/**
			 * @description 当表单部门id改变时，自动填充部门名字
			 */
			fillname() {
				this.$axios.get("http://localhost:8081/department/" + this.form1.id).then(res => {
					this.form1.name = res.data.data.name;
				})
			},
			/**
			 * @description 表单验证
			 * @param {Object} form
			 */
			submitForm(form) {
				this.$refs[form].validate((valid) => {
					if (valid) {
						this.updateDepartment();
					} else {
						return false;
					}
				});
			},
			/**
			 * @description 点击返回后回到原来的表格
			 */
			returnTable() {
				this.flag = !this.flag;
				this.flag1 = !this.flag1;
				this.getData();
			},
			/**
			 * @description 根据省份获得部门
			 */
			getDepartments() {
				this.$axios.post("http://localhost:8081/department/department", {
					'province': this.form1.province
				}).then(res => {
					this.departments = res.data.data;
				})
			},
			/**
			 * @description 模糊查询部门
			 */
			findDepartment() {
				this.$axios.post('/department/department/' + this.pageNow + '/' + this.size, {
					'name': this.form1.name,
					'province': this.form1.province,
					'id': this.form1.id,
				}).then(res => {
					if (res.data.code == '200') {
						this.$message({
							type: 'success',
							message: '查询成功',
							duration: '1000'
						});
						this.dialogVisible = false;
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
			 * @description 查询会话显示时动作
			 */
			findDialogEmerge() {
				this.dialogVisible = true;
				this.$axios.get("http://localhost:8081/department/departments/provinces").then(res => {
					this.provinces1 = res.data.data;
				})
				this.form1.id = '',
					this.form1.name = '',
					this.form1.province = ''
			},
			/**
			 * @description 更新会话显示时动作
			 * @param {Object} row
			 */
			updateDialogEmerge(row) {
				this.dialogFormVisible = true;
				this.rowId = row.id;
				this.form.name = row.name;
				this.form.province = row.province;
			},
			/**
			 * @description 更新部门
			 */
			updateDepartment() {
				this.$axios.post('http://localhost:8081/department/' + this.rowId, {
					'id': this.rowId,
					'name': this.form.name,
					'province': this.form.province
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
			 * @description 点击删除按钮后触发，删除指定行的部门
			 * @param {Object} row
			 * @return {void}
			 */
			deleteDepartment(row) {
				this.$confirm('您正在删除部门, 是否继续?', '警告', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('http://localhost:8081/department/' + row.id, {
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
				this.$axios.get("http://localhost:8081/department/departments/" + page + '/' + size).then(res => {
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
