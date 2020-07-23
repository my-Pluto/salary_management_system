<template >
	<div class="note">
		<el-carousel :interval="4000" type="card" height="1050px">
			<el-carousel-item v-for="item in imags" :key="item">
				<el-image style="height: 350px;" :src="item" fit="fill">
				</el-image>
			</el-carousel-item>
		</el-carousel>
	</div>
</template>

<script>
	import Image1 from '../assets/indexImages/1.jpg'
	import Image2 from '../assets/indexImages/2.jpg'
	import Image3 from '../assets/indexImages/3.jpg'
	export default {
		name: 'Index',
		data(){
			return{
				imags:[Image1,Image2,Image3]
			}
		}
	}
</script>

<style>
	.note{
		background-image: url("../assets/0.jpg");
		background-repeat: no-repeat;
		background-size: cover;
	}
</style>
