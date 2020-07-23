<!--
 * @FileDescription: 岗位添加组件
 * @Author: 陈辰
 * @Date: 
 * @LastEditors: 陈辰
 * @LastEditTime:
-->
<template>
	<div>
		<el-form :model="form" :rules="rules" ref="form" style="margin-top: 20px; margin-left: 200px;">
			<el-form-item label="岗位所在部门省份" style="width: 1000px;" :label-width="formLabelWidth" prop="province">
				<el-select v-model="form.province" style="width: 300px;" filterable placeholder="请选择岗位部门所在省" @change="getDepartments">
					<el-option v-for="item in provinces" :key="item.value" :label="item.label" :value="item.label">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="岗位所在部门名称" style="width: 1000px;" :label-width="formLabelWidth" prop="department">
				<el-select v-model="form.department" style="width: 300px;" filterable placeholder="请选择部门名称" @change="findId">
					<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.name">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="岗位名称" style="width: 500px;" :label-width="formLabelWidth" prop="position">
				<el-input v-model="form.position" autocomplete="off" placeholder="请输入岗位名称"></el-input>
			</el-form-item>
			<el-form-item>
				<el-popover placement="top" width="160" v-model="visible">
					<p>确定要添加该部门吗？</p>
					<div style="text-align: right; margin: 0">
						<el-button size="mini" type="text" @click="visible = false">取消</el-button>
						<el-button type="primary" size="mini" @click="submitForm('form')">确定</el-button>
					</div>
					<el-button slot="reference" type="info" style="margin-top: 20px; margin-left: 200px;">添加部门</el-button>
				</el-popover>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		name: 'NewPosition',
		data() {
			return {
				visible: false, //弹出框绑定的布尔值，true显示，false消失
				provinces: [],//省份列表
				departments: [],//部门列表
				departmentId: 0,//部门Id
				formLabelWidth: '200px',//表单标签宽度
				form: {
					position: '',//岗位名称
					province: '',//省份名称
					department: ''//部门名称
				},//增加表单
				rules: {
					department: [{
						required: true,//是否必须
						message: '请选择岗位所在部门',//校验提示信息
						trigger: 'change'//校验触发条件
					}],//部门
					province: [{
						required: true,
						message: '请选择岗位所在部门省份',
						trigger: 'change'
					}],//省份
					position: [{
						required: true,
						message: '请输入岗位名称',
						trigger: 'blur'
					}],//岗位
				},//校验规则
			}
		},
		methods: {
			/**
			 * @description 根据省份和部门名字获取部门id
			 * @return {void}
			 */
			findId() {
				this.$axios.post('/department/department', {
					'name': this.form.department,
					'province': this.form.province
				}).then(res => {
					if (res.data.code == '200') {
						this.departmentId = res.data.data[0].id;
					} else {

					}
				})
			},
			/**
			 * @description 点击弹出框确定后触发，添加岗位成功显示成功消息，失败显示失败消息
			 * @return {void}
			 */
			addPosition() {
				this.visible = false;
				this.$axios.post("http://localhost:8081/position", {
					'name': this.form.position,
					'department': this.form.department,
					'departmentId': this.departmentId
				}).then(res => {
					if (res.data.code == '200') {
						this.$message({
							type: 'success',
							message: res.data.msg,
							duration: '700'
						});
						this.position='',
						this.province='',
						this.department=''
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg,
							duration: '700'
						});
					}
				});
			},
			/**
			 * @description 获取省份下的所有部门
			 * @return {void}
			 */
			getDepartments() {
				this.$axios.post("http://localhost:8081/department/department", {
					'province': this.form.province
				}).then(res => {
					this.departments = res.data.data;
				})
			},
			/**
			 * @description 表单提交验证
			 * @param {Object} formName
			 */
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.addPosition();
					} else {
						this.visible = !this.visible;
						return false;
					}
				});
			},
		},
		/**
		 * @description 组件创建时触发，从后端获得数据填入城市列表中
		 * @return {void}
		 */
		created() {
			this.$parent.activeIndex = "/position/newposition";
			this.$axios.get("http://localhost:8081/department/departments/provinces").then(res => {
				this.provinces = res.data.data;
			})
		}
	}
</script>

<style>
</style>
