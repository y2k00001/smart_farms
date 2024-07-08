<!-- 我的土地  -->
<template>
  <s-layout title="我的土地" :bgStyle="{ color: '#f2f2f2' }">
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
      text="暂无土地"
    ></s-empty>
    <!-- 活动 -->

    <template v-if="state.currentTab == '0'" >
      <view v-if="state.iconStatus && state.pagination.total > 0" class="goods-list ss-m-t-20">
        <view v-for="item in state.pagination.data" :key="item.id" class="ss-p-l-20 ss-p-r-20 ss-m-b-20 ">
          <s-farmlandlessee-column
              class=""
              size="lg"
              :data="item.farmlandVO"
              :farmlandLessee = "item"
              :topRadius="10"
              :bottomRadius="10"
              @click="sheep.$router.go('/pages/farmlandlessee/detail',
               { farmlandLesseeId: item.farmlandLesseeId,orderId: item.farmlandLesseeOrder.orderId})"
          >

          </s-farmlandlessee-column>

        </view>
      </view>
    </template>
    <template v-else>
      <view v-if="state.iconStatus && state.pagination.total > 0" class="goods-list ss-m-t-20">
        <view v-for="item in state.pagination.data" :key="item.id" class="ss-p-l-20 ss-p-r-20 ss-m-b-20">
          <s-farmlandlessee-column
              class=""
              size="lg"
              :data="item.farmlandVO"
              :farmlandLessee = "item"
              :topRadius="10"
              :bottomRadius="10"
              @click="sheep.$router.go('/pages/farmlandlessee/detail',
               { farmlandLesseeId: item.farmlandLesseeId,orderId: item.farmlandLesseeOrder.orderId})"
          >

          </s-farmlandlessee-column>
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
    payNowStyle: {
      "mode": 1,
      "text": "立即支付",
      "color1": "#FE832A",
      "color2": "#FF6000",
      "src": ""
    },
    iconStatus: true,
  });

  const tabMaps = [
    {
      name: '全部',
      value: 'all',
    },
    {
      name: '待支付',
      value: 'nopay',
    },
    {
      name: '种植中',
      value: 'planting',
    },
    {
      name: '已结束',
      value: 'finished',
    },
  ];

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
    if (state.currentTab == 0) {
      getList();
    } else {
      getList();
    }
  }
  async function getList() {
    state.loadStatus = "loading";
    let statusList = [];
    if (state.type ==="nopay"){
      statusList.push(0);
    }else if (state.type ==="planting"){
      statusList.push(1);
    }else if (state.type ==="finished"){
      statusList.push(2);
    }else {

    }
    const params = {
      // 租赁状态;0，待支付，1，已生效，2已结束，3违约
     statusList
    }
    const res = await sheep.$api.farmlandlessee.list(params,{ page: state.pagination.page - 1, size: state.pagination.size });
    const { rows, total } = res;
    state.pagination.data = _.concat(state.pagination.data, rows);
    state.pagination.total = total;
    let totalPages = Math.ceil(total / state.pagination.size);
    if (state.pagination.page < totalPages) {
      state.loadStatus = "more";
    } else {
      state.loadStatus = "noMore";
    }
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
      Option.type === 'nopay'
        ? (state.currentTab = 1)
        : Option.type === 'planting'
        ? (state.currentTab = 2)
        : (state.currentTab = 3);
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

</style>
