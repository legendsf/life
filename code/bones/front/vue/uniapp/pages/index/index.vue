<template>
	<div>
		<view class="">
			<switch checked="true" @change="" />
			<uni-number-box></uni-number-box>
			<uni-number-box :min="0" :max="9"></uni-number-box>
			<uni-number-box @change="bindChange"></uni-number-box>
			
			<mycomponent title="xinwen1" content="daxinwen" @tap1="tap" ></mycomponent>
			
		
			
		</view>
		
		
		
	</div>
	
</template>

<script>
	
	import uniNumberBox from "@/components/uni-number-box/uni-number-box.vue";
	import mycomponent from "@/components/mycomponent.vue";
	

	
	export default {
		data() {
			return {
				title: 'Hello。。。。'
			}
		},
		onLoad() {

		},
		methods: {
			tap:function(e){
				console.log(e+"gggggg")
			}
		},
		components: {
		    uniNumberBox,
			mycomponent
		}
	}
</script>

<style lang="less">
@import "../../less.less";
</style>
