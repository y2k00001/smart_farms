<template>
  <view class="ss-coupon-menu-wrap ss-flex ss-col-center">
    <view
      class="menu-item ss-flex-col ss-row-center ss-col-center"
      v-for="item in props.list"
      :key="item.title"
      @tap="sheep.$router.go(item.path, { type: item.type })"
      :class="item.type === 'all' ? 'menu-wallet' : 'ss-flex-1'"
    >
      <image class="item-icon" :src="item.icon" mode="aspectFit"></image>
      <view class="menu-title ss-m-t-28">{{ item.title }}</view>
    </view>
  </view>
</template>

<script setup>
  /**
   * 装修组件 - 农场租赁种植菜单
   */
  import sheep from '@/sheep';

  // 接收参数
  const props = defineProps({
    list: {
      type: Array,
      default() {
        return [
          {
            title: '我的土地',
            value: '0',
            icon: '\/static\/icons\/nouse_coupon.png',
            path: '/pages/coupon/list',
            type: 'geted',
          },
          {
            title: '地址管理',
            value: '0',
            icon: '\/static\/icons\/useend_coupon.png',
            path: '/pages/coupon/list',
            type: 'used',
          },
          {
            title: '服务工单',
            value: '0',
            icon: '\/static\/icons\/out_coupon.png',
            path: '/pages/coupon/list',
            type: 'expired',
          },
          {
            title: '我的活动',
            value: '0',
            icon: '\/static\/icons\/all_coupon.png',
            path: '/pages/coupon/list',
            type: 'all',
          },
        ];
      },
    },
  });
</script>

<style lang="scss" scoped>
  .ss-coupon-menu-wrap {
    .menu-item {
      height: 160rpx;
      .menu-title {
        font-size: 24rpx;
        line-height: 24rpx;
        color: #333333;
      }
      .item-icon {
        width: 44rpx;
        height: 44rpx;
      }
    }
    .menu-wallet {
      width: 144rpx;
    }
  }
</style>
