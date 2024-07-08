<template>
  <view>
    <s-layout :onShareAppMessage="shareInfo" navbar="goods">
      <!-- 标题栏 -->
      <detailNavbar/>

      <!-- 骨架屏 -->
      <detailSkeleton v-if="state.skeletonLoading"/>
      <!-- 下架/售罄提醒 -->
      <s-empty
          v-else-if="state.goodsInfo === null"
          text="商品不存在或已下架"
          icon="/static/soldout-empty.png"
          showAction
          actionText="再逛逛"
          actionUrl="/pages/goods/list"
      />
      <block v-else>
        <view class="detail-swiper-selector">
          <!-- 商品轮播图  -->
          <su-swiper
              class="ss-m-b-14"
              isPreview
              :list="state.goodsSwiper"
              dotStyle="tag"
              imageMode="widthFix"
              dotCur="bg-mask-40"
              :seizeHeight="750"
          />

          <!-- 价格+标题 -->
          <view class="title-card detail-card ss-p-y-40 ss-p-x-20">
            <view class="ss-flex ss-row-between ss-col-center ss-m-b-26">
              <view class="price-box ss-flex ss-col-bottom">
                <view class="price-text ss-m-r-16">
                  {{ state.goodsInfo.leasePrice }}
                </view>
                <view class="origin-price-text" v-if="state.goodsInfo.leasePrice > 0">
                  {{ state.goodsInfo.leasePrice }}
                </view>
              </view>
              <view class="sales-text">
                已出租16次
              </view>
            </view>

            <view class="title-text ss-line-2 ss-m-b-6">{{ state.goodsInfo.farmlandName }}</view>
            <view class="subtitle-text ss-line-1">{{ state.goodsInfo.location }}</view>
          </view>
        </view>

        <!--选择地块面积-->
        <detail-land-areas-card class="detail-content-selector" :dataList="state.dataList" @landAreaChange="landAreaChange"/>


        <!--选择租赁周期-->
        <detail-lessee-day-card class="detail-content-selector" @lesseeDayChange = "lesseeDayChange"/>

        <!--选择服务-->
        <detail-land-service-card class="detail-content-selector" :landServiceList="state.landServiceList" @landServiceChange = "landServiceChange"/>

        <!--种子列表-->
        <list-seeds-card class="detail-comment-card" :landSeedList = "state.landSeedList" />

        <!-- 详情 -->
        <detail-content-card class="detail-content-selector" :content="state.goodsInfo.description"/>


        <!-- 活动跳转 -->
        <detail-activity-tip
            v-if="state.goodsInfo.activities"
            :data="state.goodsInfo"
        ></detail-activity-tip>

        <!-- 详情tabbar -->
        <detail-tabbar v-model="state.goodsInfo">
          <!-- TODO: 缺货中 已售罄 判断 设计-->
          <view class="buy-box ss-flex ss-col-center ss-p-r-20" v-if="state.isLease">
            <button
                class="ss-reset-button buy-btn ui-Shadow-Main"
                @tap="onBuy"
            >
              立即租赁
            </button>
          </view>
          <view class="buy-box ss-flex ss-col-center ss-p-r-20" v-else>
            <button class="ss-reset-button disabled-btn" disabled> 已出租</button>
          </view>
        </detail-tabbar>
        <s-coupon-get
            v-model="state.couponInfo"
            :show="state.showModel"
            @close="state.showModel = false"
            @get="onGet"
        />
        <s-activity-pop
            v-model="state.activityInfo"
            :show="state.showActivityModel"
            @close="state.showActivityModel = false"
        />
      </block>
    </s-layout>
  </view>
</template>

<script setup>
import {reactive, computed} from 'vue';
import {onLoad, onPageScroll} from '@dcloudio/uni-app';
import sheep from '@/sheep';
import {formatSales, formatGoodsSwiper, formatPrice} from '@/sheep/hooks/useGoods';
import detailNavbar from './components/detail/detail-navbar.vue';
import detailCellSku from './components/detail/detail-cell-sku.vue';
import detailCellService from './components/detail/detail-cell-service.vue';
import detailCellParams from './components/detail/detail-cell-params.vue';
import detailTabbar from './components/detail/detail-tabbar.vue';
import detailSkeleton from './components/detail/detail-skeleton.vue';
import detailCommentCard from './components/detail/detail-comment-card.vue';
import detailContentCard from './components/detail/detail-content-card.vue';

import detailActivityTip from './components/detail/detail-activity-tip.vue';
import {isEmpty} from 'lodash';
import DetailLandAreasCard from "./components/detail/detail-land-areas-card.vue";
import DetailLesseeDayCard from "./components/detail/detail-lessee-day-card.vue";
import DetailLandServiceCard from "./components/detail/detail-land-service-card.vue";
import ListSeedsCard from "./components/list/list-seeds-card.vue";

// import detailActivityTip from './components/detail/detail-activity-tip.vue';
// import detailTab from './components/detail/detail-tab.vue';
// import detailCoupon from './components/detail/detail-coupon.vue';
onPageScroll(() => {
});

const state = reactive({
  goodsId: 0,
  skeletonLoading: true,
  goodsInfo: {},
  showSelectSku: false,
  goodsSwiper: [],
  selectedSkuPrice: {},
  showModel: false,
  total: 0,
  couponInfo: [],
  showActivityModel: false,
  activityInfo: [],
  dataList: [{text: '2121', value: '243111', disable: true}, {text: '441231231', value: '333321'}, {
    text: '987766',
    value: '34332122tt'
  }, {text: '7u77555', value: '44f3ds3'}],
  landServiceList:[{text: '2121', value: '243111', disable: true}, {text: '441231231', value: '333321'},],
  landSeedList:[],
  isLease: false,
  preOrderParam: {
    farmlandId: "",
    landAreaId: "",
    serviceId: "",
    lesseeDay: 0
  },
});

// 规格变更
function onSkuChange(e) {
  console.log('e', e)
  state.selectedSkuPrice = e;
}

// 添加购物车
function onAddCart(e) {
  sheep.$store('cart').add(e);
}

// 立即购买
function onBuy(e) {

  if(state.preOrderParam.landAreaId ===""){
    sheep.$helper.toast('请先选择地块');
    return;
  };
  if(state.preOrderParam.serviceId ===""){
    sheep.$helper.toast('请先选择服务');
    return;
  };
  if(state.preOrderParam.lesseeDay ===0){
    sheep.$helper.toast('请先选择租赁周期');
    return;
  }

  sheep.$router.go('/pages/lessee-order/confirm', {
    data: JSON.stringify({
      id:state.goodsId,
      farmlandId: state.preOrderParam.farmlandId,
      landAreaId: state.preOrderParam.landAreaId,
      serviceId: state.preOrderParam.serviceId,
      lesseeDay: state.preOrderParam.lesseeDay
    }),
  });
}

//营销活动
function onActivity() {
  state.activityInfo = state.goodsInfo.promos;
  state.showActivityModel = true;
}

//立即领取
async function onGet(id) {
  const {error, msg} = await sheep.$api.coupon.get(id);
  if (error === 0) {
    uni.showToast({
      title: msg,
    });
    setTimeout(() => {
      getCoupon();
    }, 1000);
  }
}

const shareInfo = computed(() => {
  if (isEmpty(state.goodsInfo)) return {};
  return sheep.$platform.share.getShareInfo(
      {
        title: state.goodsInfo.title,
        image: sheep.$url.cdn(state.goodsInfo.image),
        desc: state.goodsInfo.subtitle,
        params: {
          page: '2',
          query: state.goodsInfo.id,
        },
      },
      {
        type: 'goods', // 商品海报
        title: state.goodsInfo.farmlandName, // 商品标题
        image: state.goodsInfo.files[0].url, // 商品主图
        price: state.goodsInfo.leasePrice, // 商品价格
        original_price: state.goodsInfo.leasePrice, // 商品原价
      },
  );
});

function landAreaChange(e){
  console.log("recieve landarea change:");
  console.log(e);
  state.preOrderParam.landAreaId = e.detail.value;
}
function landServiceChange(e){
  console.log("recieve landService change:");
  console.log(e);
  state.preOrderParam.serviceId = e.detail.value;
}

function lesseeDayChange(value){
  console.log("recieve lesseeDay change:");
  console.log(value);
  state.preOrderParam.lesseeDay = value;
}

onLoad(async (options) => {
  // 非法参数
  if (!options.id) {
    state.goodsInfo = null;
    return;
  }
  state.goodsId = options.id;
  // 加载商品信息
  sheep.$api.farmlands.detail(state.goodsId).then((res) => {
    console.log('地块数据：', res)
    state.skeletonLoading = false;
    state.goodsInfo = res.data;
    state.preOrderParam.farmlandId = state.goodsInfo.farmlandId;
    console.log('地块数据：', state.goodsInfo)
    state.goodsSwiper = formatGoodsSwiper(state.goodsInfo.files);
    let totalStock = 0;
    state.goodsInfo.totalStock = totalStock;
    state.dataList = res.data.landAreas.map((item) => {
      return {
        text: item.landAreaName + (item.lesseeStatus === 1 ? '(已租)' : ''),
        value: item.landAreaId,
        disable: item.lesseeStatus === 1,
      };
    });
    state.landServiceList = res.data.landServices.map((item) => {
      return {
        text: item.serviceName+"\n"+item.servicePrice+"元/天",
        value: item.serviceId,
      };
    });
    console.log("服务数据");
    console.log(state.landServiceList);
    state.landSeedList = res.data.seedVOS;
    state.isLease = res.data.landAreas.some((item) => item.lesseeStatus === 0);
  });

});
</script>

<style lang="scss" scoped>
.detail-card {
  background-color: #ffff;
  margin: 14 rpx 20 rpx;
  border-radius: 10 rpx;
  overflow: hidden;
}

// 价格标题卡片
.title-card {
  .price-box {
    .price-text {
      font-size: 42 rpx;
      font-weight: 500;
      color: #ff3000;
      line-height: 30 rpx;
      font-family: OPPOSANS;

      &::before {
        content: '￥';
        font-size: 30 rpx;
      }
    }

    .origin-price-text {
      font-size: 26 rpx;
      font-weight: 400;
      text-decoration: line-through;
      color: $gray-c;
      font-family: OPPOSANS;

      &::before {
        content: '￥';
      }
    }
  }

  .sales-text {
    font-size: 26 rpx;
    font-weight: 500;
    color: $gray-c;
  }

  .discounts-box {
    .tag-content {
      flex: 1;
      min-width: 0;
      white-space: nowrap;
    }

    .tag-box {
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .tag {
      flex-shrink: 0;
      padding: 4 rpx 10 rpx;
      font-size: 24 rpx;
      font-weight: 500;
      border-radius: 4 rpx;
      color: var(--ui-BG-Main);
      background: var(--ui-BG-Main-tag);
    }

    .discounts-title {
      font-size: 24 rpx;
      font-weight: 500;
      color: var(--ui-BG-Main);
      line-height: normal;
    }

    .cicon-forward {
      color: var(--ui-BG-Main);
      font-size: 24 rpx;
      line-height: normal;
      margin-top: 4 rpx;
    }
  }

  .title-text {
    font-size: 30 rpx;
    font-weight: bold;
    line-height: 42 rpx;
  }

  .subtitle-text {
    font-size: 26 rpx;
    font-weight: 400;
    color: $dark-9;
    line-height: 42 rpx;
  }
}

// 购买
.buy-box {
  .add-btn {
    width: 214 rpx;
    height: 72 rpx;
    font-weight: 500;
    font-size: 28 rpx;
    border-radius: 40 rpx 0 0 40 rpx;
    background-color: var(--ui-BG-Main-light);
    color: var(--ui-BG-Main);
  }

  .buy-btn {
    width: 214 rpx;
    height: 72 rpx;
    font-weight: 500;
    font-size: 28 rpx;
    padding-left: 10rpx;
    padding-right: 10rpx;
    border-radius: 0 40 rpx 40 rpx 0;
    background: linear-gradient(90deg, var(--ui-BG-Main), var(--ui-BG-Main-gradient));
    color: $white;
  }

  .disabled-btn {
    width: 428 rpx;
    height: 72 rpx;
    border-radius: 40 rpx;
    background: #999999;
    color: $white;
    padding-left: 10rpx;
    padding-right: 10rpx;
  }
}

.model-box {
  height: 60vh;

  .model-content {
    height: 56vh;
  }

  .title {
    font-size: 36 rpx;
    font-weight: bold;
    color: #333333;
  }

  .subtitle {
    font-size: 26 rpx;
    font-weight: 500;
    color: #333333;
  }
}
</style>
