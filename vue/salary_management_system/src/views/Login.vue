
<template>
  <body id="poster">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="login-container" label-position="left" label-width="0px" >
      <h3 class="login_title">系统登录</h3>
      <el-form-item prop="id">
        <el-input type="text" v-model="ruleForm.id" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
 
      <el-form-item prop="password">
        <el-input type="password" v-model="ruleForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
 
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="submitForm('ruleForm')">登录</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>
 
 
<script>
	import md5 from 'js-md5';
    export default {
        name: "Login",
        data() {
            return {
                ruleForm: {
                    id: '',
                    password: ''
                },
				rules: {
				    id: [
				        { required: true, message: '请输入您的工号', trigger: 'blur' }
				    ],
				    password: [
				        { required: true, message: '请输入密码', trigger: 'blur'  }
				    ],
				        },
                responseResult: []
            }
        },
        methods: {
			submitForm(formName) {
		        this.$refs[formName].validate((valid) => {
		          if (valid) {
					  const _this = this
					this.ruleForm.password = md5(this.ruleForm.password);
		            this.$axios.post('http://localhost:8081/login', this.ruleForm).then(res => {
						const jwt = res.headers['authorization']
						const  userInfo = res.data.data;
						_this.$store.commit("SET_TOKEN", jwt)
						_this.$store.commit("SET_USERINFO", userInfo)
					})
		          } else {
		            console.log('error submit!!');
		            return false;
		          }
		        });
		      }
		    }
        }
</script>
 
<style>
  #poster {
    background:url("../assets/2.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
    padding: 0;
  }
 
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
 
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
 
 
</style>