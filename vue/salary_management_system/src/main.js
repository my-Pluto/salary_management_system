import Vue from 'vue'
import App from './App.vue'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import "./permission"
import "./axios"
import axios from 'axios'
import router from './router'
import store from './store'





Vue.config.productionTip = false
Vue.use(Element)
Vue.prototype.$axios = axios
// if (localStorage.getItem("token") != null || localStorage.getItem("token") != '') {
// 	console.log(localStorage.getItem("token"));
// 	 // axios.defaults.headers.common['Authorization'] = localStorage.getItem("token");
// }


new Vue({
	router,
	store: store,
  render: h => h(App),
}).$mount('#app')
