<!-- 我的土地  -->
<template>
  <s-layout title="农场商城" :bgStyle="{ color: '#f2f2f2' }">
    <su-sticky bgColor="#fff">

    </su-sticky>
    <s-empty
      v-if="state.pagination.total === 0"
      icon="/static/coupon-empty.png"
      text="暂无数据"
    ></s-empty>
    <!-- 活动 -->



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
    content:"<p>在城市的喧嚣与忙碌之中，人们渴望一片属于自己的宁静之地，一抹绿色的宁静，一份耕耘与收获的喜悦。智慧农园系统，作为5G时代下的创新产物，正是这样一座桥梁，它将现代都市与传统农耕文化巧妙地连接起来，为繁忙的城市居民提供了一种全新的生活方式——“云种菜”。</p><h3>&nbsp;&nbsp;&nbsp;&nbsp;系统概述</h3><p>智慧农园系统是一款综合性的农业服务平台，它利用先进的5G技术，结合物联网、大数据分析和人工智能，构建了一个高效、便捷的虚拟农场。用户只需通过智能手机或电脑，就能轻松管理自己的农田，享受从播种到收获的全过程体验。</p><h3>&nbsp;&nbsp;&nbsp;&nbsp;功能亮点</h3><h4>线上租赁</h4><p>用户可以在平台上选择心仪的地块，进行线上租赁。无论是想要一块小巧的阳台菜园，还是一片宽阔的郊外土地，智慧农园系统都能满足您的需求。</p><h4>种子选择</h4><p>系统提供了丰富的种子库，用户可以根据个人喜好和季节特点，选择适宜的蔬菜或水果种子进行种植。每一种子都附带详细的种植指南，即使是没有任何种植经验的新手也能轻松上手。</p><h4>智能管理</h4><p>通过集成的高精度传感器和自动化设备，智慧农园系统能够实现精准的浇水、施肥和病虫害防治。用户无需亲自下田，一切管理工作都可以通过远程控制完成。</p><h4>实时监控</h4><p>用户可以通过高清摄像头实时查看自己农田的情况，观察作物的生长状态，感受植物从种子发芽到成熟的全过程。这种亲眼见证生命成长的体验，是智慧农园系统独有的魅力所在。</p><h4>互动体验</h4><p>智慧农园系统还定期举办线下农场体验活动，邀请用户走进真实的农田，亲手参与耕作，体验传统的农耕乐趣，增进亲子间的互动与情感交流。</p><h4>收获分享</h4><p>当作物成熟时，用户可以选择亲自去农场采摘，或者让系统将新鲜的农产品直接邮寄到家。无论是自食其力，还是与亲朋好友共享丰收的喜悦，都是智慧农园系统带来的美好体验。</p><h3>结语</h3><p>智慧农园系统不仅是一种新型的休闲方式，更是一种健康生活的倡导。它让城市居民在快节奏的生活中找到一片心灵的净土，体验到种植的乐趣，感受到自然的馈赠。随着5G技术的不断发展，智慧农园系统将继续拓展其功能和服务，为更多人带来绿色生活的可能。让我们一起期待，这片智慧的田园，能在钢筋水泥的森林中绽放出更加绚烂的光彩。</p><p><br/></p>",
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
      name: '认养最多',
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

  .richtext {
  }
</style>
