<!-- 我的土地  -->
<template>
  <s-layout title="农场实景" :bgStyle="{ color: '#f2f2f2' }">
    <su-sticky bgColor="#fff">
      <su-tabs
        :list="tabMaps"
        :scrollable="false"
        @change="onTabsChange"
        :current="state.currentTab"
      ></su-tabs>
    </su-sticky>
    <s-empty
      v-if="state.pagination.total === 0"
      icon="/static/coupon-empty.png"
      text="暂无数据"
    ></s-empty>
    <!-- 活动 -->

    <template  >
      <view v-if="state.iconStatus && state.pagination.total > 0" class="ss-flex ss-flex-wrap ss-p-x-20 ss-m-t-20 ss-col-top">

        <view class="goods-list-box">
          <view class="left-list" v-for="item in state.leftGoodsList" :key="item.id">
            <s-lives-column
                class="goods-md-box"
                size="md"
                :data="item"
                :topRadius="10"
                :bottomRadius="10"
                @click="sheep.$router.go('/pages/live/detail', { id: item.id })"
                @getHeight="mountMasonry($event, 'left')"
            >
              <template v-slot:cart>
                <button class="ss-reset-button cart-btn"> </button>
              </template>
            </s-lives-column>
          </view>
        </view>
        <view class="goods-list-box">
          <view class="right-list" v-for="item in state.rightGoodsList" :key="item.id">
            <s-lives-column
                class="goods-md-box"
                size="md"
                :topRadius="10"
                :bottomRadius="10"
                :data="item"
                @click="sheep.$router.go('/pages/live/detail', { id: item.id })"
                @getHeight="mountMasonry($event, 'right')"
            >
              <template v-slot:cart>
                <button class="ss-reset-button cart-btn"> </button>
              </template>
            </s-lives-column>
          </view>
        </view>





      </view>
    </template>


    <uni-load-more
      v-if="state.pagination.total > 0"
      :status="state.loadStatus"
      :content-text="{
        contentdown: '上拉加载更多',
      }"
      @tap="loadmore"
    />
  </s-layout>
</template>

<script setup>
  import sheep from '@/sheep';
  import { onLoad, onReachBottom } from '@dcloudio/uni-app';
  import { computed, reactive } from 'vue';
  import _, {clone} from 'lodash';


  const pagination = {
    data: [],
    page: 1,
    total: 0,
    size: 10
  };
  // 数据
  const state = reactive({
    currentTab: 0,
    pagination: clone(pagination),
    loadStatus: '',
    type: '',
    growthStageStyle: {
      "mode": 1,
      "text": "播种期",
      "color1": "#FE832A",
      "color2": "#FF6000",
      "src": ""
    },
    iconStatus: false,
    isShowColumn:false,
    leftGoodsList: [],
    rightGoodsList: [],
    res : {
      total:4,
      rows:[
        {"id":"0","deviceName":"石柱岭1号球机","coverFile":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/pic-1.png","playUrl":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/camera-1.mp4","lon":"118.154","lat":"28.332","address":"广东揭阳石崇镇街道石柱岭1号球机"},
        {"id":"1","deviceName":"横湖2号球机","coverFile":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/pic-2.png","playUrl":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/camera-2.mp4","lon":"118.45","lat":"28.36","address":"广东揭阳石崇镇街道横湖2号球机"},
        {"id":"2","deviceName":"石柱岭1号球机","coverFile":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/pic-1.png","playUrl":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/camera-1.mp4","lon":"118.154","lat":"28.332","address":"广东揭阳石崇镇街道石柱岭1号球机"},
        {"id":"3","deviceName":"横湖2号球机","coverFile":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/pic-2.png","playUrl":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/camera-2.mp4","lon":"118.45","lat":"28.36","address":"广东揭阳石崇镇街道横湖2号球机"},
        {"id":"4","deviceName":"石柱岭1号球机","coverFile":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/pic-1.png","playUrl":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/camera-1.mp4","lon":"118.154","lat":"28.332","address":"广东揭阳石崇镇街道石柱岭1号球机"},
        {"id":"5","deviceName":"横湖2号球机","coverFile":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/pic-2.png","playUrl":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/camera-2.mp4","lon":"118.45","lat":"28.36","address":"广东揭阳石崇镇街道横湖2号球机"},
        {"id":"6","deviceName":"石柱岭1号球机","coverFile":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/pic-1.png","playUrl":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/camera-1.mp4","lon":"118.154","lat":"28.332","address":"广东揭阳石崇镇街道石柱岭1号球机"},
        {"id":"7","deviceName":"横湖2号球机","coverFile":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/pic-2.png","playUrl":"https://smart-farms.oss-cn-shenzhen.aliyuncs.com/storage/video/camera-2.mp4","lon":"118.45","lat":"28.36","address":"广东揭阳石崇镇街道横湖2号球机"},
      ]
    }
  });

  const tabMaps = [
    {
      name: '全部',
      value: 'all',
    },
    {
      name: '观看最多',
      value: 'most',
    },
    {
      name: '距离最近',
      value: 'nearest',
    }
  ];

  // 加载瀑布流
  let count = 0;
  let leftHeight = 0;
  let rightHeight = 0;

  // 支付按钮样式
  const payStyle = computed(() => {
    if ( state.payNowStyle.mode == 1) {
      // button
      return {
        background: `linear-gradient(to right, ${ state.payNowStyle.color1}, ${ state.payNowStyle.color2})`,
      };
    }

    if ( state.payNowStyle.mode == 2) {
      // image
      return {
        width: '54rpx',
        height: '54rpx',
        background: `url(${sheep.$url.cdn( state.payNowStyle.src)}) no-repeat`,
        backgroundSize: '100% 100%',
      };
    }
  });
  function onTabsChange(e) {
    state.pagination = clone(pagination)
    state.currentTab = e.index;
    state.type = e.value;
    console.log("qiehuan");
    state.iconStatus = false;
    if (state.currentTab == 0) {
      getList();
    } else {
      getList();
    }
  }
  async function getList() {

    state.loadStatus = "loading";
    let statusList = [];
    if (state.type ==="seed"){
      statusList.push(10);
    }else if (state.type ==="growing"){
      statusList.push(20);
    }else if (state.type ==="harvest"){
      statusList.push(30);
    }else if (state.type ==="finished"){
      statusList.push(40);
    } else {

    }
    const params = {
      // 生长周期状态;10，待支付，20，已生效，30已结束，40已结束
     statusList
    }
    // const res = await sheep.$api.growth.list(params,{ page: state.pagination.page - 1, size: state.pagination.size });
    const res = state.res;
    const { rows, total } = res;
    state.pagination.data = _.concat(state.pagination.data, rows);
    state.pagination.total = total;
    // 拆分列表
    mountMasonry();

    let totalPages = Math.ceil(total / state.pagination.size);
    if (state.pagination.page < totalPages) {
      state.loadStatus = "more";
    } else {
      state.loadStatus = "noMore";
    }
    state.iconStatus = true;
    console.log("request finish!");
  }

  async function getCoupon() {
    state.loadStatus = 'loading';
    let res = await sheep.$api.coupon.userCoupon({type: state.currentTab},{ page: state.pagination.page - 1, size: state.pagination.size });
    const { content, totalElements, totalPages } = res;
    state.pagination.data = _.concat(state.pagination.data, content);
    state.pagination.total = totalElements;
    if (state.pagination.page < totalPages) {
      state.loadStatus = "more";
    } else {
      state.loadStatus = "noMore";
    }
  }
  async function getBuy(id) {
    const res = await sheep.$api.coupon.get(id);
    if (res) {
      uni.showToast({
        title: '领取成功',
      });
      setTimeout(() => {
        state.pagination.data = []
        getActivity();
      }, 1000);
    }
  }

  function mountMasonry(height = 0, where = 'left') {
    if (!state.pagination.data[count]) return;
    if (count %2 === 0) {
      state.leftGoodsList.push(state.pagination.data[count]);
    } else {
      state.rightGoodsList.push(state.pagination.data[count]);
    }
    count++;
  }

  // 加载更多
  function loadmore() {
    if (state.loadStatus !== 'noMore') {
      state.pagination.page++;
      if (state.currentTab == 0) {
        getList();
      } else {
        getList();
      }
    }
  }
  onLoad((Option) => {
    if (Option.type === 'all' || !Option.type) {
      getList(Option.type);
    } else {
      state.type = Option.type;
      Option.type === 'seed'
        ? (state.currentTab = 1)
        : Option.type === 'growing'
        ? (state.currentTab = 2)
        : Option.type === 'harvest'
        ? (state.currentTab = 3)
         :(state.currentTab = 4);
      getList(Option.type);
    }
  });
  onReachBottom(() => {
    loadmore();
  });
</script>
<style lang="scss" scoped>
  .card-btn {
    // width: 144rpx;
    padding: 0 16rpx;
    height: 50rpx;
    border-radius: 40rpx;
    background: linear-gradient(90deg, var(--ui-BG-Main), var(--ui-BG-Main-gradient));
    color: #ffffff;
    font-size: 24rpx;
    font-weight: 400;
  }
  .border-btn {
    background: linear-gradient(90deg, var(--ui-BG-Main-opacity-4), var(--ui-BG-Main-light));
    color: #fff !important;
  }
  .disabled-btn {
    background: #cccccc;
    background-color: #cccccc !important;
    color: #fff !important;
  }
  .goods-list-box {
    width: 50%;
    box-sizing: border-box;
    .left-list {
      margin-right: 10rpx;
      margin-bottom: 20rpx;
    }
    .right-list {
      margin-left: 10rpx;
      margin-bottom: 20rpx;
    }
  }
  .goods-box {
    &:nth-last-of-type(1) {
      margin-bottom: 0 !important;
    }
    &:nth-child(2n) {
      margin-right: 0;
    }
  }
  .list-icon {
    width: 80rpx;
    .sicon-goods-card {
      font-size: 40rpx;
    }
    .sicon-goods-list {
      font-size: 40rpx;
    }
  }
  .goods-card {
    margin-left: 20rpx;
  }
  .list-filter-tabs {
    background-color: #fff;
  }
  .filter-list-box {
    padding: 28rpx 52rpx;
    .filter-item {
      font-size: 28rpx;
      font-weight: 500;
      color: #333333;
      line-height: normal;
      margin-bottom: 24rpx;
      &:nth-last-child(1) {
        margin-bottom: 0;
      }
    }
    .filter-item-active {
      color: var(--ui-BG-Main);
    }
  }
  .tab-item {
    height: 50px;
    position: relative;
    z-index: 11;

    .tab-title {
      font-size: 30rpx;
    }

    .cur-tab-title {
      font-weight: $font-weight-bold;
    }

    .tab-line {
      width: 60rpx;
      height: 6rpx;
      border-radius: 6rpx;
      position: absolute;
      left: 50%;
      transform: translateX(-50%);
      bottom: 10rpx;
      background-color: var(--ui-BG-Main);
      z-index: 12;
    }
  }
</style>
