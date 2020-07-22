<template>
	<el-form ref="form" :model="form" label-width="200px" style="margin-left: 12.5rem;">
		<el-form-item label="项目名称" prop='name'>
			<el-input v-model="form.name" style="width: 200px;"></el-input>
		</el-form-item>
		<el-form-item label="是否在工资条中显示" prop="region">
			<el-select v-model="form.isDisplay" placeholder="请选择" style="width: 200px;">
				<el-option label="不显示" value="0"></el-option>
				<el-option label="显示" value="1"></el-option>
			</el-select>
		</el-form-item>
		<el-form-item label="项目在工资条中的序号" prop='number'>
			<el-input v-model="form.number" style="width: 200px;"></el-input>
		</el-form-item>
		<el-form-item label="增减项" prop="region">
			<el-select v-model="form.effect" placeholder="请选择增减项" style="width: 200px;">
				<el-option label="扣除" value="0"></el-option>
				<el-option label="无影响" value="1"></el-option>
				<el-option label="增加" value="2"></el-option>
			</el-select>
		</el-form-item>
		<el-form-item label="计算项目" prop="region">
			<el-select style="width: 200px;" v-model="form.firstOperand" filterable placeholder="请选择计算项目">
				<el-option v-for="item in firstOperands" :key="item" :label="item" :value="item">
				</el-option>
			</el-select>
		</el-form-item>
		<el-form-item label="运算符" prop="region">
			<el-select v-model="form.operator" placeholder="请选择运算符" style="width: 200px;">
				<el-option label="+" value="+" style="width: 200px;"></el-option>
				<el-option label="-" value="-" style="width: 200px;"></el-option>
				<el-option label="*" value="*" style="width: 200px;"></el-option>
				<el-option label="/" value="/" style="width: 200px;"></el-option>
			</el-select>
		</el-form-item>
		<el-form-item label="系数" prop='number'>
			<el-input v-model="form.secentOperand" style="width: 200px;"></el-input>
		</el-form-item>
		<el-form-item>
			<el-button type="primary" @click="addItem">添加</el-button>
		</el-form-item>
	</el-form>
</template>

<script>
	export default {
		name: 'NewCountItem',
		data() {
			return {
				firstOperands: [],
				form: {
					id: '',
					name: '',
					effect: '',
					number: '',
					isDisplay: '',
					firstOperand: '',
					operator: '',
					secentOperand: ''
				},
				rules: {
					region: [{
						required: true,
						message: '请选择',
						trigger: 'change'
					}],
					name: [{
							required: true,
							message: '请输入',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 50,
							message: '检查输入',
							trigger: 'blur'
						}
					],
					number: [{
							required: true,
							message: '请输入',
							trigger: 'blur'
						},
						{
							type: 'float',
							message: '输入的必须为数字值'
						}
					]
				},
			}
		},

		methods: {
			getFirstOperands() {
				this.$axios.get("/countitem/countitem/firstOperation").then(res => {
					if (res.data.code == '200') {
						this.firstOperands = res.data.data;
						console.log(this.firstOperands);
					}
				})
			},

			addItem() {
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
				this.$axios.post("/countitem", {
					name: this.form.name,
					number: this.form.number,
					effect: this.form.effect,
					isDisplay: this.form.isDisplay,
					firstOperand: this.form.firstOperand,
					operator: this.form.operator,
					secentOperand: this.form.secentOperand
				}).then(res => {
					if (res.data.code == '200') {
						this.$message({
							message: res.data.msg,
							type: 'success',
							duration: '700'
						});
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg,
							duration: '700'
						});
					}
				})
			}
		},


		created() {
			this.getFirstOperands();
		}
	}
</script>

<style>
</style>
