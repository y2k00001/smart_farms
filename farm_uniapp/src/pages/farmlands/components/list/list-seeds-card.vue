<template>
  <view class="detail-comment-card bg-white">
    <view class="card-header ss-flex ss-col-center ss-row-between ss-p-b-30">
      <view class="ss-flex ss-col-center">
        <view class="line"></view>
        <view class="title ss-m-l-20 ss-m-r-10">种子列表</view>

      </view>
      <view
          class="ss-flex ss-col-center"
          @tap="sheep.$router.go('/pages/seeds/list', { seedIds: state.seedIds })"
          v-if="state.seedList.length > 0"
      >
        <button class="ss-reset-button more-btn">查看全部</button>
        <text class="cicon-forward"></text>
      </view>
    </view>
    <view class="ss-flex ss-flex-wrap">
      <view class="comment-box ss-p-y-30   ss-col-stretch" v-for="item in state.seedList" :key="item.id">
        <seed-item :item="item"></seed-item>
      </view>
      <s-empty
          v-if="state.seedList.length === 0"
          paddingTop="0"
          icon="/static/comment-empty.png"
          text="该地块未关联种子"
      ></s-empty>
    </view>
  </view>
</template>

<script setup>
import { reactive, onBeforeMount } from 'vue';
import sheep from '@/sheep';
import SeedItem from "../detail/seed-item.vue";


const props = defineProps({
  landSeedList: {
    type: Array,
    default: [],
  },
});
const state = reactive({
  seedList: [],
  seedIds:[]
});
state.seedList = props.landSeedList;
state.seedList.forEach(item => {
  state.seedIds.push(item.seedId)
})
onBeforeMount(() => {

});
</script>

<style lang="scss" scoped>
.detail-comment-card {
  margin: 0 20rpx 20rpx 20rpx;
  padding: 20rpx 20rpx 0 20rpx;
  .card-header {
    .line {
      width: 6rpx;
      height: 30rpx;
      background: linear-gradient(180deg, var(--ui-BG-Main) 0%, var(--ui-BG-Main-gradient) 100%);
      border-radius: 3rpx;
    }

    .title {
      font-size: 30rpx;
      font-weight: bold;
      line-height: normal;
    }

    .des {
      font-size: 24rpx;
      color: $dark-9;
    }

    .more-btn {
      font-size: 24rpx;
      color: var(--ui-BG-Main);
      line-height: normal;
    }

    .cicon-forward {
      font-size: 24rpx;
      line-height: normal;
      color: var(--ui-BG-Main);
      margin-top: 4rpx;
    }
  }
}
.comment-box {

  &:last-child {
    border: none;
  }
  width: 50%;
}
</style>
