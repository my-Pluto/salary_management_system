<template>
<div>
	<el-container>
	  <el-aside width="180px">
		  <el-row class="tac">
		    <el-col >
		      <el-menu
		        default-active="1"
		        class="el-menu-vertical-demo"
		        @select="handleOpen">
				<el-menu-item index="1">
				  <i class="el-icon-menu"></i>
				  <span slot="title">导航1</span>
				</el-menu-item>
		        <el-menu-item index="2">
		          <i class="el-icon-menu"></i>
		          <span slot="title">导航二</span>
		        </el-menu-item>
		        <el-menu-item index="4">
		          <i class="el-icon-setting"></i>
		          <span slot="title">导航四</span>
		        </el-menu-item>
		      </el-menu>
		    </el-col>
		  </el-row>
	  </el-aside>
	  <el-main>Main</el-main>
	</el-container>
</div>
</template>

<script>
	export default {
		name:'Users',
		methods: {
			handleOpen(key, keyPath) {
			        console.log(key, keyPath);
			      },
				handleClose(key, keyPath) {
				        console.log(key, keyPath);
				      }
		}
	}
</script>

<style>
	html,body,#app,.el-container{
	        /*设置内部填充为0，几个布局元素之间没有间距*/
	        padding: 0px;
	         /*外部间距也是如此设置*/
	        margin: 0px;
	        /*统一设置高度为100%*/
	        height: 100%;
			}
			
	.el-menu-item {
		  margin-left: 20px;
	}
</style>
