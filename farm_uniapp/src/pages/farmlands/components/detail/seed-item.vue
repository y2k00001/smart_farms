<template>

    <view class=" ss-flex ss-m-b-14">
      <view class="ss-flex ">
        <image class="seed-img" :src="state.seed.files[0].url"></image>
      </view>
      <view class="ss-flex-col ss-m-x-22">
        <view >{{ state.seed.seedName }}</view>
        <view style="font-size: 26rpx;font-weight: 400;color: #999999;">生长周期:{{ state.seed.growthCycle }}天</view>
      </view>
    </view>
</template>

<script setup>
  import sheep from '@/sheep';
  import { reactive } from 'vue';


  const props = defineProps({
    item: {
      type: Object,
      default() {},
    },
  });
  const state = reactive({
    commentImages: [],
    data:{
      src: "/storage/upload/baicai.png"
    },
    seed:{}
  });
  state.seed = props.item;
  props.item.images?.forEach((i) => {
    state.commentImages.push(sheep.$url.cdn(i));
  });
</script>

<style lang="scss" scoped>
  .seed-img {
    width: 120rpx;
    height: 120rpx;
    border-radius: 10%;
  }

  .nickname {
    font-size: 26rpx;
    font-weight: 500;
    color: #999999;
  }

  .content {
    width: 636rpx;
    font-size: 26rpx;
    font-weight: 400;
    color: #333333;
  }
</style>
