<template>
	<div>
		<el-form ref="form" :rules="rules" :model="form" label-width="200px" style="margin-left: 300px;">
			<el-form-item label="项目名称" style="margin-top: 50px;" prop="name">
				<el-input v-model="form.name" style="width: 200px;"></el-input>
			</el-form-item>
			<el-form-item label="是否在工资条中显示" style="margin-top: 50px;" prop="display">
				<el-select v-model="form.isDisplay" placeholder="请选择">
					<el-option label="不显示" value="0"></el-option>
					<el-option label="显示" value="1"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="项目在工资条中的序号" style="margin-top: 50px; width: 350px;" prop="number">
				<el-input v-model="form.number"></el-input>
			</el-form-item>
			<el-form-item label="请选择增减项" style="margin-top: 50px;" prop="effect">
				<el-select v-model="form.effect" placeholder="请选择增减项">
					<el-option label="减少" value="0"></el-option>
					<el-option label="无影响" value="1"></el-option>
					<el-option label="增加" value="2"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="addItem(form)" style="margin-top: 50px;">增加</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		name : 'NewImportItem',
		data() {
			return {
				form: {
					name: '',
					effect: '',
					number: '',
					isDisplay: ''
				},
				// rules: {
				// 	name: [{
				// 			message: '请输入名称',
				// 			trigger: 'blur'
				// 		},
				// 		{
				// 			min: 1,
				// 			max: 50,
				// 			message: '检查输入',
				// 			trigger: 'blur'
				// 		}
				// 	],
				// 	effect: [{
				// 		required: true,
				// 		message: '请选择增减项',
				// 		trigger: 'change'
				// 	}],
				// 	display: [{
				// 		required: true,
				// 		message: '请选择是否显示',
				// 		trigger: 'change'
				// 	}],
				// 	number: [{
				// 			required: true,
				// 			message: '请输入',
				// 			trigger: 'blur'
				// 		},
				// 		{
				// 			type: 'number',
				// 			message: '输入的必须为数字值'
				// 		}
				// 	]
				// },
			}
		},
		
		methods: {
			addItem(formName) {
				// this.$refs[formName].validate((valid) => {
				// 	if (valid) {
						if (this.form.effect == '扣除') {
							this.form.effect = '0';
						} else if (this.form.effect == '无影响') {
							this.form.effect = '1';
						} else if (this.form.effect == '增加') {
							this.form.effect = '2';
						}
		
						if (this.form.isDisplay == '不显示') {
							this.form.isDisplay = '0';
						} else if (this.form.isDisplay == '显示') {
							this.form.isDisplay = '1';
						};
						console.log(this.form);
						this.$axios.post("/importitem/", {
							name: this.form.name,
							number: this.form.number,
							effect: this.form.effect,
							isDisplay: this.form.isDisplay
						}).then(res => {
							if (res.data.code == '200') {
								this.$message({
									message: res.data.msg,
									type: 'success',
									duration: '700'
								});
								console.log(res.data.msg);
							} else {
								this.$message({
									type: 'error',
									message: res.data.msg,
									duration: '700'
								});
							}
						})
					// } else {
					// 	console.log('error submit!!');
					// 	return false;
					// }
			
		
			},
		}
	}
</script>

<style>
</style>
