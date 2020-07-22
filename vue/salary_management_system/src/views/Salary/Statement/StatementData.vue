<template>
	<div>
			<el-button icon="el-icon-search" circle style="float: right; margin-bottom: 0.3125rem;" @click='dialogTableVisible=true'></el-button>
			<el-table :data="tableData" border style="width: 100%">
				<el-table-column fixed prop="id" label="ID" width="100" sortable>
				</el-table-column>
				<el-table-column fixed prop="name" label="姓名" width="100">
				</el-table-column>
				<el-table-column fixed prop="department_name" label="部门" width="100">
				</el-table-column>
				<el-table-column v-for="item in th" :prop='item' :label='item' width="100">
				</el-table-column>
				<el-table-column fixed="right" label="操作" width="200">
					<template slot-scope="scope" v-show="this.isProvided == 1">
						<el-button type="text" size="small"  @click='updateItem(scope.row)'>编辑</el-button>
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
	
			<el-dialog title="查询" :visible.sync="dialogTableVisible">
				<el-form label-width="200px">
					<el-form-item label="请选择部门" prop="department" style="margin-top: 3.125rem;">
						<el-select v-model="department" placeholder="请选择部门">
							<el-option v-for="item in departments" :key="item.id" :label="item.name" :value="item.id" />
						</el-select>
					</el-form-item>
					<el-form-item label="请输入时间" style="margin-top: 3.125rem; width: 420px;">
						<div class="block">
							<el-date-picker v-model="lowMonth" value-format="yyyy-MM" type="month" placeholder="请输入时间">
							</el-date-picker>
						</div>
					</el-form-item>
				</el-form>
				<el-button type="primary" @click='getData(1)' style="margin-left: 300px;">查询</el-button>
				<el-button @click="fun1" tyle="margin-left: 300px;">取消</el-button>
			</el-dialog>
			
			<el-dialog
			  title="修改"
			  :visible.sync="dialogVisible"
			  width="30%"
			  >
			  <el-form :model="temp" label-width="100px">
			  	<el-form-item label="ID">
			  		<el-input v-model="id" style="width: 200px;" disabled></el-input>
			  	</el-form-item>
			  	<el-form-item label="员工名称" prop='name'>
			  		<el-input v-model="name" style="width: 200px;" disabled></el-input>
			  	</el-form-item>
				<el-form-item label="部门名称" prop='name'>
					<el-input v-model="department_name" style="width: 200px;" disabled></el-input>
				</el-form-item>
				<el-form-item label="项目" prop="region">
					<el-select style="width: 200px;" v-model="itemname" filterable placeholder="请选择项目">
						<el-option v-for="(value, index) in temp" :key="index" :label="index" :value="index">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="数据" prop='name'>
					<el-input v-model="data" style="width: 200px;"></el-input>
				</el-form-item>
				</el-form>
			  	
			  <span slot="footer" class="dialog-footer">
			    <el-button @click="dialogVisible = false">取 消</el-button>
			    <el-button type="primary" @click="updateNew">确 定</el-button>
			  </span>
			</el-dialog>
		</div>
</template>

<script>
	export default {
		name:'StatementData',
		data() {
			return {
				tableData: [],
				th: [],
				pageNow: 1, //当前的页数
				size: 10, //每页的用户数
				total: 0, //所有的总和
				department: '',
				dialogTableVisible: false,
				lowMonth: '',
				itemname: '',
				itemnames: [],
				departments: [],
				isProvided:'',
				temp:{},
				dialogVisible : false,
				id:'',
				name:'',
				department_name:'',
				department_id:'',
				data:''
				
			}
		},
		
		methods: {
			updateItem(row) {
				this.temp = row;
				console.log(this.temp);
				this.dialogVisible = true;
				this.id=this.temp.id;
				this.name=this.temp.name;
				this.department_name=this.temp.department_name;
				this.department_id = this.temp.department_id;
				delete this.temp.id;
				delete this.temp.department_name;
				delete this.temp.name;
				delete this.temp.department_id;
			},
			
			updateNew() {
				this.dialogVisible = false;
				const name = this.itemname;
				this.$axios.post("/statement/count/" + this.id + "/" + this.lowMonth + "/" + this.itemname, {
					name:this.data
				}).then(res => {
					if (res.data.code == '200') {
						this.$message({
							type: 'success',
							message: res.data.msg,
							duration: '700',
						});
						this.getData(this.page, this.size);
					} else {
						this.$message({
							type: 'error',
							message: res.data.msg,
							duration: '700'
						});
					}
				})
			},
			
			getDepartments() {
				this.$axios.get("/department/departments").then(res => {
					if (res.data.code == '200') {
						this.departments = res.data.data;
					}
				})
			},
			
			fun1() {
				this.dialogTableVisible = false;
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
			},
			
			getData(page, size) {
				page = page ? page : this.pageNow; //有page参数page=page,没有时page为默认的pageNow值
				size = size ? size : this.size;
				this.$axios.post("/statement/salary/" + page + "/" + size, {
					departmentId:this.department,
					month:this.lowMonth
				}).then(res => {
					if (res.data.code == '200') {
						this.total = res.data.data.total;
						this.th = res.data.data.names;
						this.tableData = res.data.data.data;
						this.isProvided = res.data.data.isProvided;
					}
				});
				this.dialogTableVisible = false;
			},
		},
		
		created() {
			this.dialogTableVisible = true;
			this.getDepartments();
		}
	}
</script>

<style>
</style>
