<template>
	<div>
		<el-button icon="el-icon-search" circle style="float: right; margin-bottom: 0.3125rem;" @click='select'></el-button>
		<el-table :data="tableData" :rules="rules" border style="width: 100%">
			<el-table-column fixed prop="id" label="ID" width="100">
			</el-table-column>
			<el-table-column fixed prop="name" label="名称" width="300">
			</el-table-column>
			<el-table-column prop="effect" label="增减项" width="240">
			</el-table-column>
			<el-table-column prop="number" label="工资条序号" width="240">
			</el-table-column>
			<el-table-column prop="isDisplay" label="是否显示" width="240">
			</el-table-column>
			<el-table-column fixed="right" label="操作" width="200">
				<template slot-scope="scope">
					<el-button type="text" size="small" @click='update(scope.row)' style="margin-right: 10px;">编辑</el-button>
					<el-popconfirm
					  title="确定删除？"
					  @onConfirm='deleteItem(scope.row)'
					>
					  <el-button type="text" size="small" slot="reference">删除</el-button>
					</el-popconfirm>
					
					<!-- <el-button type="text" size="small" slot="reference" @click='deleteItem(scope.row)'>删除</el-button> -->
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
		<el-dialog title="修改" :visible.sync="dialogVisible" width="30%">
			<el-form ref="form" :model="form" label-width="100px">
				<el-form-item label="ID">
					<el-input v-model="form.id" style="width: 200px;" disabled></el-input>
				</el-form-item>
				<el-form-item label="项目名称" prop='name'>
					<el-input v-model="form.name" style="width: 200px;"></el-input>
				</el-form-item>
				<el-form-item label="是否在工资条中显示">
					<el-select v-model="form.isDisplay" placeholder="请选择">
						<el-option label="不显示" value="0"></el-option>
						<el-option label="显示" value="1"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="项目在工资条中的序号" prop='number'>
					<el-input v-model="form.number"></el-input>
				</el-form-item>
				<el-form-item label="请选择增减项">
					<el-select v-model="form.effect" placeholder="请选择增减项">
						<el-option label="减少" value="0"></el-option>
						<el-option label="无影响" value="1"></el-option>
						<el-option label="增加" value="2"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="updateItem">修改</el-button>
					<el-button @click="dialogVisible = false">取消</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'FixedItemData',
		methods: {
			handleClick(row) {
				console.log(row);
			},
			
			deleteItem(row) {
				console.log(row);
				this.$axios.delete("/fixeditem/" + row.id).then(res => {
					if(res.data.code == '200') {
						this.$message({
							message: res.data.msg,
							type: 'success',
							duration: '700'
						});
						location.reload();
					} else {
						this.$message({
							message: res.data.msg,
							type: 'error',
							duration: '700'
						});
					}
				})
			},

			select() {
				this.$prompt('请输入名称', '搜索', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
				}).then(({
					value
				}) => {

					this.$axios.post("/fixeditem/fixeditem/1/5", {
						name: value
					}).then(res => {
						if (res.data.code == '200') {
							this.$message({
								type: 'success',
								message: res.data.msg
							});
							res.data.data.records.forEach(function(item, index) {
								if (item.effect == '0') {
									item.effect = '扣除';
								} else if (item.effect == '1') {
									item.effect = '无影响';
								} else if (item.effect == '2') {
									item.effect = '增加';
								}
							
								if (item.isDisplay == '0') {
									item.isDisplay = '不显示';
								} else {
									item.isDisplay = '显示';
								}
							})
							this.tableData = res.data.data.records;
							console.log(this.tableData);
						} else {
							this.$message({
								type: 'error',
								message: res.data.msg
							});
						}
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '取消输入'
					});
				});
			},

			update(row) {
				this.form.id = row.id;
				this.form.name = row.name;
				this.form.number = row.number;
				this.form.effect = row.effect;
				this.form.isDisplay = row.isDisplay;
				this.dialogVisible = true;
			},

			updateItem() {
				this.dialogVisible = false;
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
				this.$axios.post("/fixeditem/" + this.form.id, {
					id: this.form.id,
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
						location.reload();
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg,
							duration: '700'
						});
					}
				})
			},

			getData(page, size) {
				page = page ? page : this.pageNow; //有page参数page=page,没有时page为默认的pageNow值
				size = size ? size : this.size; //有size参数size=size,没有时size为默认的this.size值
				this.$axios.get("http://localhost:8081/fixeditem/fixeditem/" + page + '/' + size).then(res => {
					if (res.data.code == '200') {
						this.$message({
							message: res.data.msg,
							type: 'success',
							duration: '700'
						});
						res.data.data.records.forEach(function(item, index) {
							if (item.effect == '0') {
								item.effect = '扣除';
							} else if (item.effect == '1') {
								item.effect = '无影响';
							} else if (item.effect == '2') {
								item.effect = '增加';
							}

							if (item.isDisplay == '0') {
								item.isDisplay = '不显示';
							} else {
								item.isDisplay = '显示';
							}
						})
						this.tableData = res.data.data.records;
						this.total = res.data.data.total;
					} else {
						this.$message.error(res.data.msg);
					}
				})
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
			}
		},

		data() {
			return {
				dialogVisible: false,
				tableData: [],
				pageNow: 1, //当前的页数
				size: 10, //每页的用户数
				total: 0, //所有的用户数总和
				form: {
					id: '',
					name: '',
					effect: '',
					number: '',
					isDisplay: ''
				},
				rules: {
					name: [{
							required: true,
							message: '请输入名称',
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
							type: 'number',
							message: '输入的必须为数字值'
						}
					]
				},
			}
		},

		created() {
			this.getData();
		}
	}
</script>

<style>
</style>
