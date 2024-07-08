<!-- 我的土地  -->
<template>
  <s-layout title="认采任认养" :bgStyle="{ color: '#f2f2f2' }">
    <su-sticky bgColor="#fff">

    </su-sticky>
    <s-empty
      v-if="state.pagination.total === 0"
      icon="/static/coupon-empty.png"
      text="暂无数据"
    ></s-empty>
    <!-- 活动 -->

    <template  >
      <view class="ss-p-30"><mp-html class="richtext" :content="state.content"></mp-html></view>
    </template>

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
    content:"<p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 15px; line-height: 1.7; color: rgb(5, 7, 59); text-wrap: wrap;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">一、模块概述</span></p><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 14px; margin-bottom: 0px; scrollbar-width: none; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 15px; line-height: 1.7; color: rgb(5, 7, 59); text-wrap: wrap;\">在智慧农田项目中，我们特别推出了认采认养模块，旨在为用户提供一种全新的农业体验。用户可以通过我们的平台，选择自己心仪的果树、蔬菜或者牲畜进行认采认养。这一模式不仅让用户能够亲身体验农业生产的乐趣，还能确保他们享受到最新鲜、最健康的农产品。</p><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 14px; margin-bottom: 0px; scrollbar-width: none; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 15px; line-height: 1.7; color: rgb(5, 7, 59); text-wrap: wrap;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">二、操作流程</span></p><ol style=\"box-sizing: border-box; padding: 0px 0px 0px 30px; -webkit-font-smoothing: antialiased; list-style-position: outside; list-style-image: none; margin-top: 14px; margin-bottom: 1em; scrollbar-width: none; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 15px; line-height: 1.7; color: rgb(5, 7, 59); text-wrap: wrap;\" class=\" list-paddingleft-2\"><li><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; line-height: 1.7; display: inline;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">用户登录</span>：用户首先通过我们的微信小程序登录系统，进入认采认养模块。</p></li><li><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; line-height: 1.7; display: inline;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">选择认养对象</span>：在模块中，用户可以浏览到当前可供认养的果树、蔬菜和牲畜。每一种作物或牲畜都附带有详细的介绍，包括品种、种植/养殖地点、生长情况等。用户可以根据自己的喜好和需求进行选择。</p></li><li><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; line-height: 1.7; display: inline;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">认养确认</span>：选择好认养对象后，用户需要确认认养信息，并支付相应的费用。费用将直接用于该作物的种植/养殖和管理。</p></li><li><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; line-height: 1.7; display: inline;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">实时追踪</span>：认养成功后，用户可以通过小程序实时追踪自己认养作物的生长情况。系统提供了丰富的数据和图片，让用户能够随时了解作物的生长进度。</p></li><li><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; line-height: 1.7; display: inline;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">收获配送</span>：当果树结果、蔬菜成熟或牲畜长大时，我们将按照用户的意愿进行配送。用户可以选择将农产品直接送到家中，也可以选择到农场自取。对于牲畜，我们还可以提供宰杀和生鲜配送服务，确保用户能够品尝到最新鲜的肉类。</p><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; line-height: 1.7; display: inline;\"><br/></p></li></ol><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 14px; margin-bottom: 0px; scrollbar-width: none; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 15px; line-height: 1.7; color: rgb(5, 7, 59); text-wrap: wrap;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">三、业务优势</span></p><ol style=\"box-sizing: border-box; padding: 0px 0px 0px 30px; -webkit-font-smoothing: antialiased; list-style-position: outside; list-style-image: none; margin-top: 14px; margin-bottom: 1em; scrollbar-width: none; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 15px; line-height: 1.7; color: rgb(5, 7, 59); text-wrap: wrap;\" class=\" list-paddingleft-2\"><li><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; line-height: 1.7; display: inline;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">新鲜健康</span>：所有农产品均来自我们严格筛选的农场，保证了产品的品质和新鲜度。</p></li><li><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; line-height: 1.7; display: inline;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">体验丰富</span>：用户可以通过认采认养模块，亲身体验农业生产的乐趣，感受大自然的魅力。</p></li><li><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 0px; margin-bottom: 0px; scrollbar-width: none; line-height: 1.7; display: inline;\"><span style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin: 0px; scrollbar-width: none; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot; !important; font-weight: 600;\">服务周到</span>：我们提供从认养到配送的全流程服务，确保用户能够享受到便捷、高效的服务体验。</p></li></ol><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 14px; margin-bottom: 0px; scrollbar-width: none; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 15px; line-height: 1.7; color: rgb(5, 7, 59); text-wrap: wrap;\"><br/></p><p style=\"box-sizing: border-box; padding: 0px; -webkit-font-smoothing: antialiased; list-style: none; margin-top: 14px; margin-bottom: 0px; scrollbar-width: none; font-family: -apple-system, &quot;system-ui&quot;, &quot;Segoe UI&quot;, Roboto, Ubuntu, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Source Han Sans CN&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 15px; line-height: 1.7; color: rgb(5, 7, 59); text-wrap: wrap;\"><br/></p><p><br/></p>",
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

  .richtext {
  }
</style>
