<!--
 * @FileDescription: 员工添加页面
 * @Author: 陈辰
 * @Date: 
 * @LastEditors: 陈辰
 * @LastEditTime:
-->
<template>
	<div>
		<el-form :model="form" :rules="rules" ref="form" style="margin-top: 50px; margin-left: 200px;">
			<el-form-item label="岗位所在部门省份" style="width: 1000px;" :label-width="formLabelWidth" prop="province">
				<el-select v-model="form.province" style="width: 300px;" filterable placeholder="请选择岗位部门所在省" @change="getDepartments">
					<el-option v-for="item in provinces" :key="item.value" :label="item.label" :value="item.label">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="岗位所在部门名称" style="width: 1000px;" :label-width="formLabelWidth" prop="department">
				<el-select v-model="form.department" style="width: 300px;" filterable placeholder="请选择部门名称" @change="getPositions">
					<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="岗位名称" style="width: 500px;" :label-width="formLabelWidth" prop="position">
				<el-select v-model="form.position" style="width: 300px;" filterable placeholder="请选择岗位名称">
					<el-option v-for="item in positions" :key="item.id" :label="item.name" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>

			<el-form-item label="员工姓名" style="width: 500px;" :label-width="formLabelWidth" prop="name">
				<el-input v-model="form.name" autocomplete="off" placeholder="请输入员工姓名"></el-input>
			</el-form-item>
			<el-form-item label="员工性别" style="width: 500px;" :label-width="formLabelWidth" prop="sex">
				<el-select v-model="form.sex" style="width: 300px;" placeholder="请选择员工性别">
					<el-option v-for="item in options" :key="item.value" :label="item.value" :value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="员工邮箱" style="width: 500px;" :label-width="formLabelWidth" prop="email">
				<el-input v-model="form.email" autocomplete="off" placeholder="请输入员工邮箱"></el-input>
			</el-form-item>
			<el-form-item label="员工进入时间" style="width: 500px;" :label-width="formLabelWidth" prop="date">
				<el-col :span="40">
					<el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="form.date" style="width: 100%;"></el-date-picker>
				</el-col>
			</el-form-item>
			<el-popover placement="top" width="160" v-model="visible">
				<p>确定要添加该员工吗？</p>
				<div style="text-align: right; margin: 0">
					<el-button size="mini" type="text" @click="visible = false">取消</el-button>
					<el-button type="primary" size="mini" @click="submitForm('form')">确定</el-button>
				</div>
				<el-button slot="reference" type="info" style="margin-top: 20px; margin-left: 200px;">添加员工</el-button>
			</el-popover>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		name: 'NewEmployee',
		data() {
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
				options: [{
					value: '男'
				}, {
					value: '女'
				}],
				visible: false, //弹出框绑定的布尔值，true显示，false消失
				provinces: [],
				departments: [],
				positions: [],
				formLabelWidth: '200px',
				departmentName: '',
				form: {
					position: '',
					province: '',
					department: '',
					name: '',
					sex: '',
					email: '',
					data: ''
				},
				rules: {
					department: [{
						required: true,
						message: '请选择岗位所在部门',
						trigger: 'change'
					}],
					province: [{
						required: true,
						message: '请选择岗位所在部门省份',
						trigger: 'change'
					}],
					position: [{
						required: true,
						message: '请选择岗位名称',
						trigger: 'change'
					}],
					name: [{
						required: true,
						validator: checkName,
						trigger: 'blur'
					}, ],
					sex: [{
						required: true,
						message: '请选择员工性别',
						trigger: 'change'
					}],
					email: [{
						required: true,
						validator: checkEmail,
						trigger: 'blur'
					}],
					date: [{
						required: true,
						message: '请选择员工进入日期',
						trigger: 'change'
					}]

				},

			}
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.addEmployee();
					} else {
						this.visible = !this.visible;
						return false;
					}
				});
			},
			getDepartments() {

				this.$axios.post("/department/department", {
					'province': this.form.province
				}).then(res => {
					this.departments = res.data.data;
				})
			},
			getPositions() {
				this.$axios.post("/position/positions/1/1000", {
					departmentId: this.form.department
				}).then(res => {
					this.positions = res.data.data.records;
				})
			},
			/**
			 * @description 点击弹出框确定后触发，添加用户成功显示成功消息，失败显示失败消息
			 * @return {void}
			 */
			addEmployee() {
				this.visible = false;

				this.$axios.post("/employee", {
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
							message: res.data.msg,
							duration: '700'
						});
						this.position='',
						this.province='',
						this.department='',
						this.name='',
						this.sex='',
						this.email='',
						this.data=''
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg,
							duration: '700'
						});
					}
				});

			}
		},
		/**
		 * @description 组件创建时触发，从后端获得数据填入城市列表中
		 * @return {void}
		 */
		created() {
			this.$parent.activeIndex = "/employee/newemployee";
			this.$axios.get("http://localhost:8081/department/departments/provinces").then(res => {
				this.provinces = res.data.data;
			})
		}
	}
</script>

<style>
</style>
