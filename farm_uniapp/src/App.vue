<script setup>
	import {
		onLaunch,
		onShow,
		onError
	} from '@dcloudio/uni-app';
	import {
		ShoproInit
	} from './sheep';

	onLaunch(() => {
		// 隐藏原生导航栏 使用自定义底部导航
		uni.hideTabBar();

		// 加载Shopro底层依赖
		ShoproInit();
	});

	onError((err) => {
		console.log('AppOnError:', err);
	});
	onShow(() => {
		// #ifdef APP-PLUS
		// 获取urlSchemes参数
		const args = plus.runtime.arguments;
		if (args) {}

		// 获取剪贴板
		uni.getClipboardData({
			success: (res) => {},
		});
		// #endif
	});
</script>

<style lang="scss">
	@import '@/sheep/scss/index.scss';


  //设置圆角
  checkbox.round .wx-checkbox-input,
  checkbox.round .uni-checkbox-input {
    border-radius: 100upx;
  }

  //设置背景色
  checkbox.red[checked] .wx-checkbox-input,
  checkbox.red.checked .uni-checkbox-input{
    background-color: #e54d42 !important;
    border-color: #e54d42 !important;
    color: #ffffff !important;
  }

  checkbox.green[checked] .wx-checkbox-input,
  checkbox.green.checked .uni-checkbox-input{
    background-color:   #00B42B !important;
    border-color:   #00B42B !important;
    color:   #ffffff !important;
  }

  //元素使用的时候就是使用 round 和 red
</style>
