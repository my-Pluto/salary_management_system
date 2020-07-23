<!--
 * @FileDescription: 部门添加组件
 * @Author: 陈辰
 * @Date: 
 * @LastEditors: 陈辰
 * @LastEditTime:
-->
<template>
	<div>
		<el-form :model="form" :rules="rules" ref="form" style="margin-top: 20px; margin-left: 200px;">
			<el-form-item label="部门所在省" style="width: 500px;" :label-width="formLabelWidth" prop="province">
				<el-select v-model="form.province" style="width: 375px;" filterable placeholder="请选择部门所在省">
					<el-option v-for="item in provinces" :key="item.id" :label="item.name" :value="item.name">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="部门名称" style="width: 500px;" :label-width="formLabelWidth" prop="name">
				<el-input v-model="form.name" autocomplete="off" placeholder="请输入部门名称"></el-input>
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
		name: 'NewDepartment',
		data() {
			return {
				formLabelWidth: '120px',//表单标签宽度
				form: {
					name: '',//部门名称
					province: ''//部门所在省份
				},//添加部门表单
				visible: false, //弹出框绑定的布尔值，true显示，false消失
				provinces: [{
						id: '1',//省份的id
						name: "北京"//省份的名称
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
				rules: {
					name: [{
						required: true,//是否必填
						message: '请输入部门名字',//校验信息
						trigger: 'blur'//触发校验条件
					}],
					province: [{
						required: true,
						message: '请选择部门所在省份',
						trigger: 'change'
					}],
				},//表单校验准则
			}
		},
		methods: {
			/**
			 * @description 点击弹出框确定后触发，添加部门成功显示成功消息，失败显示失败消息
			 * @return {void}
			 */
			addDepartment() {
				this.visible = false;
				this.$axios.post("http://localhost:8081/department", {
					name: this.form.name,
					province: this.form.province
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
				this.form.department = '',
				this.form.province = ''
			},
			/**
			 * @description 提交表单验证
			 * @param {Object} formName
			 * @return {boolean}
			 */
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.addDepartment();
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
			this.$parent.activeIndex = "/department/newdepartment";
		}
	}
</script>

<style>
</style>
