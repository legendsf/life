<template>
	<view class="">
		<mycomponent :title="title" content="content...." @tap1="tap"></mycomponent>
	</view>
</template>

<script>
import uniNumberBox from "@/components/uni-number-box/uni-number-box.vue"
import mycomponent from "@/components/mycomponent.vue"
export default {
    data() {
    	return {
			title : "新闻标题"
    	}
    },
	onLoad:function(){
		var _self = this;
		setTimeout(function() {
			_self.title = "hi123"
		}, 3000);
	},
	methods:{
		tap : function (e) {
			console.log(e)
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