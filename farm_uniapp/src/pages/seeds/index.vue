<!-- 页面  -->
<template>
  <s-layout title="种子详情">
    <view>

      <su-image :src="state.seedInfo.pictureUrl" mode="widthFix"></su-image>
    </view>
    <view class="ss-modal-box bg-white">
      <view class="modal-header">{{state.seedInfo.seedName}}</view>
      <view class="modal-content ss-p-t-50">
        <view class="sale-item ss-flex ss-col-top" >
          <view class="item-title">预计产量</view>
          <view class="item-value">{{state.seedInfo.yield}}kg/㎡</view>
        </view>

        <view class="sale-item ss-flex ss-col-top" >
          <view class="item-title">播种季节</view>
          <view class="item-value">{{state.seedInfo.seedtime}}</view>
        </view>

        <view class="sale-item ss-flex ss-col-top" >
          <view class="item-title">生长周期</view>
          <view class="item-value">周期：{{state.seedInfo.growthCycle}}天</view>
        </view>

        <view class="sale-item ss-flex ss-col-top" >
          <view class="item-title">适宜气候</view>
          <view class="item-value">{{state.seedInfo.climate}}</view>
        </view>
        <view class="sale-item ss-flex ss-col-top" >
          <view class="item-title">种植技术</view>
          <view class="item-value">{{state.seedInfo.plantingTechnique}}</view>
        </view>
      </view>

    </view>
    <s-empty v-if="state.pagination.total === 0" text="暂无数据" icon="/static/data-empty.png" />
  </s-layout>
</template>

<script setup>
  import sheep from '@/sheep';
  import { onLoad, onReachBottom } from '@dcloudio/uni-app';
  import { computed, reactive } from 'vue';


  const state = reactive({
    id: '',
    list: [],
    type: [],
    currentTab: 0,
    pagination: {
      data: [],
      current_page: 1,
      total: 1,
      last_page: 1,
    },
    commentId: 0,
    code: 'all',
    seedInfo: {
      pictureUrl: ""
    },

  });
  onLoad(async (options) => {
    // 非法参数
    if (!options.id) {
      state.seedInfo = null;
      return;
    }
    state.id = options.id;
    // 加载商品信息
    sheep.$api.seeds.detail(state.id).then((res) => {
      console.log('种子数据：', res)
      state.seedInfo = res.data;
      state.seedInfo.pictureUrl = state.seedInfo.files[0].url;
    });

  });
</script>

<style lang="scss" scoped>
  .list-item {
    padding: 32rpx 30rpx 20rpx 20rpx;
    background: #fff;
    .avatar {
      width: 52rpx;
      height: 52rpx;
      border-radius: 50%;
    }
    .nickname {
      font-size: 26rpx;
      font-weight: 500;
      color: #999999;
    }
    .create-time {
      font-size: 24rpx;
      font-weight: 500;
      color: #c4c4c4;
    }
    .content-title {
      font-size: 26rpx;
      font-weight: 400;
      color: #666666;
      line-height: 42rpx;
    }
    .content-img {
      width: 174rpx;
      height: 174rpx;
    }
    .cicon-info-o {
      font-size: 26rpx;
      color: #c4c4c4;
    }
    .foot-title {
      font-size: 24rpx;
      font-weight: 500;
      color: #999999;
    }
  }

  .btn-box {
    width: 100%;
    height: 120rpx;
    background: #fff;
    border-top: 2rpx solid #eee;
  }
  .tab-btn {
    width: 130rpx;
    height: 62rpx;
    background: #eeeeee;
    border-radius: 31rpx;
    font-size: 28rpx;
    font-weight: 400;
    color: #999999;
    border: 1px solid #e5e5e5;
    margin-right: 10rpx;
  }


  .ss-modal-box {
    border-radius: 30rpx 30rpx 0 0;
    max-height: 730rpx;

    .modal-header {
      position: relative;
      padding: 30rpx 20rpx 40rpx;
      font-size: 36rpx;
      font-weight: bold;
    }

    .modal-content {
      padding: 0 30rpx;
      max-height: 500rpx;
      box-sizing: border-box;

      .sale-item {
        margin-bottom: 24rpx;
        padding-bottom: 24rpx;
        border-bottom: 2rpx solid rgba(#dfdfdf, 0.4);

        .item-title {
          font-size: 28rpx;
          font-weight: 500;
          line-height: 42rpx;
          width: 120rpx;
          white-space: normal;
        }

        .item-value {
          font-size: 26rpx;
          font-weight: 400;
          color: $dark-9;
          line-height: 42rpx;
          flex: 1;
          margin-left: 20rpx;
        }
      }
    }

    .modal-footer {
      height: 120rpx;

      .save-btn {
        width: 710rpx;
        height: 80rpx;
        border-radius: 40rpx;
        background: linear-gradient(90deg, var(--ui-BG-Main), var(--ui-BG-Main-gradient));
        color: $white;
      }
    }
  }
</style>
