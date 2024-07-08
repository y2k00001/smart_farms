<!-- 页面 -->
<template>
  <view class="ss-goods-wrap">

    <!-- lg卡片：横向型，一行放一个，图片左内容右边  -->
    <view
        v-if="size === 'lg'"
        class="lg-goods-card ss-flex ss-col-stretch"
        :style="[elStyles]"
        @tap="onClick"
    >
      <view v-if="tagStyle.show" class="tag-icon-box">
        <image class="tag-icon" :src="sheep.$url.cdn(tagStyle.src)"></image>
      </view>
      <image class="lg-img-box" :src="props.news.thumbnailFileEntity.url" mode="aspectFill"></image>
      <view class="lg-goods-content ss-flex-1 ss-flex-col ss-row-between ss-p-b-10 ss-p-t-20">
        <view>
          <view
              v-if="goodsFields.title?.show"
              class="lg-goods-title ss-line-2"
              :style="[{ color: titleColor }]"
          >
            {{ props.news.newTitle }}
          </view>
          <!--地块面积-->
          <view
              v-if="goodsFields.subtitle?.show"
              class="lg-goods-subtitle ss-m-t-10 ss-line-1"
              :style="[{ color: titleColor, background: subTitleBackground }]"
          >
            {{props.news.newsTypeStr}}

          </view>
          <view
              v-if="goodsFields.subtitle?.show"
              class="lg-goods-subtitle ss-m-t-10 ss-line-1"
              :style="[{ color: subTitleColor, background: subTitleBackground }]"
          >
            {{props.news.summary}}
          </view>


          <!--服务类型，租赁周期-->
          <view
              v-if="goodsFields.subtitle?.show"
              class="lg-goods-subtitle ss-m-t-10 ss-line-1"
              :style="[{ color: subTitleColor, background: subTitleBackground }]"
          >
            {{props.news.releaseTime}}
          </view>

        </view>
      </view>

      <slot name="cart">
        <view class="seed-box ss-flex ss-col-center ss-row-center"
                >
          查看详情
        </view>

      </slot>
    </view>

  </view>
</template>

<script setup>
  /**
   * 商品卡片
   *
   * @property {Array} size = [xs | sm | md | lg | sl ] 			 	- 列表数据
   * @property {String} tag 											- md及以上才有
   * @property {String} img 											- 图片
   * @property {String} background 									- 背景色
   * @property {String} topRadius 									- 上圆角
   * @property {String} bottomRadius 									- 下圆角
   * @property {String} title 										- 标题
   * @property {String} titleColor 									- 标题颜色
   * @property {Number} titleWidth = 0								- 标题宽度，默认0，单位rpx
   * @property {String} subTitle 										- 副标题
   * @property {String} subTitleColor									- 副标题颜色
   * @property {String} subTitleBackground 							- 副标题背景
   * @property {String | Number} price 								- 价格
   * @property {String} priceColor 									- 价格颜色
   * @property {String | Number} originPrice 							- 原价/划线价
   * @property {String} originPriceColor 								- 原价颜色
   * @property {String | Number} sales 								- 销售数量
   * @property {String} salesColor									- 销售数量颜色
   *
   * @slots activity												 	- 活动插槽
   * @slots cart														- 购物车插槽，默认包含文字，背景色，文字颜色 || 图片 || 行为
   *
   * @event {Function()} click 										- 点击卡片
   *
   */
  import {computed, reactive, getCurrentInstance, watch} from 'vue';
  import sheep from '@/sheep';
  import { formatSales } from '@/sheep/hooks/useGoods';
  import { formatStock } from '@/sheep/hooks/useGoods';
  import goodsCollectVue from '@/pages/user/goods-collect.vue';
  import { isArray } from 'lodash';

  // 数据
  const state = reactive({
    buttonShow: {
      type: Boolean,
      default: true,
    },
    seedList:[]
  });

  // 接收参数
  const props = defineProps({
    news:{},
    farmlandLesseeVO:{},
    goodsFields: {
      type: [Array, Object],
      default() {
        return {
          title: { show: true },
          subtitle: { show: true },
          price: { show: true },
          original_price: { show: true },
          sales: { show: true },
          stock: { show: true },
        };
      },
    },
    tagStyle: {
      type: Object,
      default: {},
    },
    data: {
      type: Object,
      default: {},
    },
    size: {
      type: String,
      default: 'sl',
    },
    background: {
      type: String,
      default: '',
    },
    topRadius: {
      type: Number,
      default: 0,
    },
    bottomRadius: {
      type: Number,
      default: 0,
    },
    titleWidth: {
      type: Number,
      default: 0,
    },
    titleColor: {
      type: String,
      default: '#333',
    },
    priceColor: {
      type: String,
      default: '',
    },
    originPriceColor: {
      type: String,
      default: '#C4C4C4',
    },
    priceUnit: {
      type: String,
      default: '￥',
    },
    subTitleColor: {
      type: String,
      default: '#999999',
    },
    subTitleBackground: {
      type: String,
      default: '',
    },

    seckillTag: {
      type: Boolean,
      default: false,
    },
    grouponTag: {
      type: Boolean,
      default: false,
    },

  });



  // 组件样式
  const elStyles = computed(() => {
    return {
      background: props.background,
      'border-top-left-radius': props.topRadius + 'px',
      'border-top-right-radius': props.topRadius + 'px',
      'border-bottom-left-radius': props.bottomRadius + 'px',
      'border-bottom-right-radius': props.bottomRadius + 'px',
    };
  });



  // 返回事件
  const emits = defineEmits(['click', 'getHeight']);



  const onClick = () => {
    emits('click');
  };

  // 获取实时卡片高度
  const { proxy } = getCurrentInstance();
  const elId = `sheep_${Math.ceil(Math.random() * 10e5).toString(36)}`;
  function calculatePanelHeight(e) {
    if (props.size === 'md') {
      const view = uni.createSelectorQuery().in(proxy);
      view.select(`#${elId}`).fields({ size: true, scrollOffset: true });
      view.exec((data) => {
        const goodsPriceCard = data[0];
        const card = {
          width: goodsPriceCard.width,
          height: (goodsPriceCard.width / e.detail.width) * e.detail.height + goodsPriceCard.height,
        };
        emits('getHeight', card.height);
      });
    }
  }



</script>

<style lang="scss" scoped>
  .tag-icon-box {
    position: absolute;
    left: 0;
    top: 0;
    z-index: 2;
    .tag-icon {
      width: 72rpx;
      height: 44rpx;
    }
  }
  .seckill-tag {
    position: absolute;
    left: 0;
    top: 0;
    z-index: 2;
    width: 68rpx;
    height: 38rpx;
    background: linear-gradient(90deg, #ff5854 0%, #ff2621 100%);
    border-radius: 10rpx 0px 10rpx 0px;
    font-size: 24rpx;
    font-weight: 500;
    color: #ffffff;
    line-height: 32rpx;
  }
  .groupon-tag {
    position: absolute;
    left: 0;
    top: 0;
    z-index: 2;
    width: 68rpx;
    height: 38rpx;
    background: linear-gradient(90deg, #fe832a 0%, #ff6600 100%);
    border-radius: 10rpx 0px 10rpx 0px;
    font-size: 24rpx;
    font-weight: 500;
    color: #ffffff;
    line-height: 32rpx;
  }
  .goods-img {
    width: 100%;
    height: 100%;
    background-color: #f5f5f5;
  }
  .price-unit {
    margin-right: -4px;
  }
  .sales-text {
    display: table;
    font-size: 24rpx;
    transform: scale(0.8);
    margin-left: 0rpx;
    color: #c4c4c4;
  }

  .activity-tag {
    font-size: 20rpx;
    color: #ff0000;
    line-height: 30rpx;
    padding: 0 10rpx;
    border: 1px solid rgba(#ff0000, 0.25);
    border-radius: 4px;
    flex-shrink: 0;
  }

  .goods-origin-price {
    font-size: 20rpx;
    color: #c4c4c4;
    line-height: 36rpx;
    text-decoration: line-through;
  }

  // xs
  .xs-goods-card {
    overflow: hidden;
    // max-width: 375rpx;
    background-color: $white;
    position: relative;

    .xs-img-box {
      width: 128rpx;
      height: 128rpx;
      margin-right: 20rpx;
    }

    .xs-goods-title {
      font-size: 26rpx;
      color: #333;
      font-weight: 500;
    }

    .xs-goods-price {
      font-size: 30rpx;
      color: $red;
    }
  }

  // sm
  .sm-goods-card {
    overflow: hidden;
    // width: 223rpx;
    // width: 100%;
    background-color: $white;
    position: relative;

    .sm-img-box {
      // width: 228rpx;
      width: 100%;
      height: 208rpx;
    }
    .sm-goods-content {
      padding: 20rpx 16rpx;
      box-sizing: border-box;
    }
    .sm-goods-title {
      font-size: 26rpx;
      color: #333;
    }

    .sm-goods-price {
      font-size: 30rpx;
      color: $red;
    }
  }

  // md
  .md-goods-card {
    overflow: hidden;
    width: 100%;
    position: relative;
    z-index: 1;
    background-color: $white;
    position: relative;

    .md-img-box {
      width: 100%;
    }

    .md-goods-title {
      font-size: 26rpx;
      color: #333;
      width: 100%;
    }
    .md-goods-subtitle {
      font-size: 24rpx;
      font-weight: 400;
      color: #999999;
    }

    .md-goods-price {
      font-size: 30rpx;
      color: $red;
      line-height: 36rpx;
    }

    .cart-box {
      width: 54rpx;
      height: 54rpx;
      background: linear-gradient(90deg, #fe8900, #ff5e00);
      border-radius: 50%;
      position: absolute;
      bottom: 50rpx;
      right: 20rpx;
      z-index: 2;

      .cart-icon {
        width: 30rpx;
        height: 30rpx;
      }
    }
  }

  // lg
  .lg-goods-card {
    overflow: hidden;
    position: relative;
    z-index: 1;
    background-color: $white;
    height: 250rpx;

    .lg-img-box {
      width: 250rpx;
      height: 250rpx;
      margin-right: 20rpx;
    }

    .lg-goods-title {
      font-size: 28rpx;
      font-weight: 500;
      color: #333333;
      // line-height: 36rpx;
      // width: 410rpx;
    }
    .lg-goods-subtitle {
      font-size: 24rpx;
      font-weight: 400;
      color: #999999;
      // line-height: 30rpx;
      // width: 410rpx;
    }

    .lg-goods-price {
      font-size: 30rpx;
      color: $red;
      line-height: 36rpx;
    }

    .seed-box {
      position: absolute;
      bottom: 20rpx;
      right: 20rpx;
      z-index: 2;
      width: 120rpx;
      height: 50rpx;

      background: linear-gradient(90deg, #58CD86, #46C878);
      border-radius: 25rpx;
      font-size: 24rpx;
      color: #ffffff;
    }

    .growing-box {
      position: absolute;
      bottom: 20rpx;
      right: 20rpx;
      z-index: 2;
      width: 120rpx;
      height: 50rpx;

      background: linear-gradient(90deg, #58CD86, #46C878);
      border-radius: 25rpx;
      font-size: 24rpx;
      color: #ffffff;
    }

    .harvest-box {
      position: absolute;
      bottom: 20rpx;
      right: 20rpx;
      z-index: 2;
      width: 120rpx;
      height: 50rpx;
      background: linear-gradient(90deg, #fe8900, #ff5e00);
      border-radius: 25rpx;
      font-size: 24rpx;
      color: #ffffff;
    }
    .finish-box {
      position: absolute;
      bottom: 20rpx;
      right: 20rpx;
      z-index: 2;
      width: 120rpx;
      height: 50rpx;
      background: linear-gradient(90deg, #969696, #969696);
      border-radius: 25rpx;
      font-size: 24rpx;
      color: #ffffff;
    }
    .tag-box {
      width: 100%;
    }
  }

  // sl

  .sl-goods-card {
    overflow: hidden;
    position: relative;
    z-index: 1;
    width: 100%;
    background-color: $white;
    .sl-goods-content {
      padding: 20rpx 20rpx;
      box-sizing: border-box;
    }
    .sl-img-box {
      width: 100%;
      height: 360rpx;
    }

    .sl-goods-title {
      font-size: 26rpx;
      color: #333;
      font-weight: 500;
    }
    .sl-goods-subtitle {
      font-size: 24rpx;
      font-weight: 400;
      color: #999999;
      line-height: 30rpx;
    }

    .sl-goods-price {
      font-size: 30rpx;
      color: $red;
      line-height: 36rpx;
    }

    .buy-box {
      position: absolute;
      bottom: 20rpx;
      right: 20rpx;
      z-index: 2;
      width: 148rpx;
      height: 50rpx;
      background: linear-gradient(90deg, #fe8900, #ff5e00);
      border-radius: 25rpx;
      font-size: 24rpx;
      color: #ffffff;
    }
  }
</style>
