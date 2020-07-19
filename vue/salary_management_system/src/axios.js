import axios from 'axios'
import Element from "element-ui";
import store from "./store";
import router from "./router";
axios.defaults.baseURL='http://localhost:8081'

axios.interceptors.request.use(config => {
  let token = localStorage.getItem('token');
  if (token) {
	  config.headers = {
		  "Authorization": token
	  }
  }
  return config
})