<template>
  <view v-if="template && !loading">
    <s-layout
        title="首页"
        navbar="custom"
        tabbar="/pages/index/index"
        :bgStyle="template.style?.background"
        :navbarStyle="template.style?.navbar"
        onShareAppMessage
    >
      <s-block v-for="(item, index) in template.data" :key="index" :styles="item.style">
        <s-block-item :type="item.type" :data="item.data" :styles="item.style" />
      </s-block>
      <!-- 广告模块 -->
<!--      <s-popup-image />-->
    </s-layout>
  </view>
</template>

<script setup>
import { computed, ref } from 'vue';
import { onLoad, onPageScroll, onPullDownRefresh } from '@dcloudio/uni-app';
import sheep from '@/sheep';
import $share from '@/sheep/platform/share';

// 隐藏原生tabBar
uni.hideTabBar();

const loading = ref(false);

const template = computed(() => sheep.$store('app').template?.home);

onLoad((options) => {
  // #ifdef MP
  // 小程序识别二维码
  if (options.scene) {
    const sceneParams = decodeURIComponent(options.scene).split('=');
    options[sceneParams[0]] = sceneParams[1];
  }
  // #endif

  // 预览模板
  if (options.templateId) {
    sheep.$store('app').init(options.templateId);
  }

  // 解析分享信息
  if (options.spm) {
    $share.decryptSpm(options.spm);
  }

  // 进入指定页面(完整页面路径)
  if (options.page) {
    sheep.$router.go(decodeURIComponent(options.page));
  }
});

// 下拉刷新
onPullDownRefresh(async () => {
  loading.value = true;
  uni.showLoading({
    title: '正在刷新...',
    mask: true,
    fail: () => {
      uni.hideLoading();
    },
  });
  await sheep.$store('app').init();
  setTimeout(function () {
    uni.stopPullDownRefresh();
    loading.value = false;
    uni.hideLoading();
  }, 800);
});

onPageScroll(() => {});
</script>

<style></style>
