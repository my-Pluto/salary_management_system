<!--
 * @FileDescription: 用户添加页面
 * @Author: 陈辰
 * @Date: 
 * @LastEditors: 陈辰
 * @LastEditTime:
-->
<template>
	<div>
		<el-row :gutter="20" style="margin-top: 100px; margin-left: 300px;">
			<el-col :span="4">
				<div class="grid-content bg-purple">请添加员工所在城市</div>
			</el-col>
			<el-col :span="16">
				<div class="grid-content bg-purple" style="margin-left: 25px;">
					<el-select v-model="provinceValue" style="width: 250px;" filterable placeholder="请选择添加员工所在城市" @change="getProvence">
						<el-option v-for="item in provinces" :key="item.value" :label="item.label" :value="item.value">
						</el-option>
					</el-select>
				</div>
			</el-col>
			<el-col :span="2">
				<div class="grid-content bg-purple"></div>
			</el-col>
		</el-row>
		<el-row :gutter="20" style="margin-top: 100px; margin-left: 300px;">
			<el-col :span="4">
				<div class="grid-content bg-purple">请添加员工所在部门</div>
			</el-col>
			<el-col :span="16" style="margin-left: 25px;">
				<div class="grid-content bg-purple">
					<el-select v-model="departmentValue" style="width: 250px;" filterable placeholder="请选择添加员工所在部门" @change="getDepartment">
						<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id">
						</el-option>
					</el-select>
				</div>
			</el-col>
			<el-col :span="2">
				<div class="grid-content bg-purple"></div>
			</el-col>
		</el-row>
		<el-row :gutter="20" style="margin-top: 100px; margin-left: 300px;">
			<el-col :span="4">
				<div class="grid-content bg-purple">请选择添加的员工</div>
			</el-col>
			<el-col :span="16" style="margin-left: 25px;">
				<div class="grid-content bg-purple">
					<el-select v-model="employeeValue" style="width: 250px;" filterable placeholder="请选择添加的员工" @change="getEmployee">
						<el-option v-for="item in emloyees" :key="item.id" :label="item.name" :value="item.id">
						</el-option>
					</el-select>
				</div>
			</el-col>
			<el-col :span="2">
				<div class="grid-content bg-purple"></div>
			</el-col>
		</el-row>
		<el-popover placement="top" width="160" v-model="visible">
			<p>确定要添加该用户吗？</p>
			<div style="text-align: right; margin: 0">
				<el-button size="mini" type="text" @click="visible = false">取消</el-button>
				<el-button type="primary" size="mini" @click="addUser">确定</el-button>
			</div>
			<el-button slot="reference" type="info" style="margin-top: 50px; margin-left: 500px;">添加用户</el-button>
		</el-popover>
	</div>
</template>

<script>
	export default {
		name: 'NewUser',
		data() {
			return {
				visible: false,//弹出框绑定的布尔值，true显示，false消失
				provinces: [],//用户所在城市列表
				provinceValue: '',//用户所在城市选择器绑定的值
				departments: [],//用户所在部门列表
				departmentValue: '',//用户所在部门选择器绑定的值
				emloyees: [],//用户名字列表
				employeeValue: '',//用户名字选择器绑定的值
				userName: 'aaa',//选中用户的名字
			}
		},
		methods: {
			/**
			 * @description 用户所在城市选择器选中值发生改变时触发，从后端获得数据填入部门列表
			 * @return {void}
			 */
			getProvence() {
				this.departments = [];
				this.departmentValue = '';
				this.emloyees = [];
				this.employeeValue = '';
				this.$axios.post("http://localhost:8081/department/department", {
					province: this.provinceValue
				}).then(res => {
					this.departments = res.data.data;
				})
			},
			/**
			 * @description 用户所在部门选择器选中值发生变化时触发，从后端获得数据填入用户列表
			 * @return {void}
			 */
			getDepartment() {
				this.emloyees = [];
				this.employeeValue = '';
				this.$axios.post("http://localhost:8081/employee/employees", {
					department: this.departmentValue
				}).then(res => {
					this.emloyees = res.data.data;
				})
			},
			/**
			 * @description 用户名字选择器选中值发生变化时触发，从后端获得数据给userName赋值
			 * @return {void}
			 */
			getEmployee() {
				const _this = this;
				this.$axios.get("http://localhost:8081/employee/" + this.employeeValue ).then(res => {

					this.userName = res.data.data;
				});
			},
			/**
			 * @description 点击弹出框确定后触发，添加用户成功显示成功消息，失败显示失败消息
			 * @return {void}
			 */
			addUser() {
				this.visible = false;
				this.$axios.post("http://localhost:8081/user", {
					id: this.employeeValue,
					name: this.userName.name,
					password: 'e10adc3949ba59abbe56e057f20f883e'
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
				});

			}
		},
		/**
		 * @description 组件创建时触发，从后端获得数据填入城市列表中
		 * @return {void}
		 */
		created() {
			this.$parent.activeIndex = "/users/newuser";
			this.provinces = [],
				this.provinceValue = '',
				this.departments = [],
				this.departmentValue = '',
				this.emloyees = [],
				this.employeeValue = '',
				this.$axios.get("http://localhost:8081/department/departments/provinces").then(res => {
					this.provinces = res.data.data;
				})
		}
	}
</script>

<style>

</style>
