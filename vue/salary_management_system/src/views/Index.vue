<template>
	<div class="demo-image">
			<el-image style="width: 500px; height: 500px; margin-left: 450px; margin-top: 50px;" :src="url" :fit="fit"></el-image>
	</div>
</template>

<script>
	import logo from '../assets/4.jpg'
	export default {
		name: 'Index',
		data() {
			return {
				fits: 'fill',
				url: logo
			}
		}
	}
</script>

<style>
</style>
