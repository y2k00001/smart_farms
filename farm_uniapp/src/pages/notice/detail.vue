<template>
  <s-layout title="活动详情">

    <view class="order-card-box ss-m-b-14">

      <mp-html :content="state.news.content"></mp-html>
    </view>

  </s-layout>
</template>

<script setup>
import { reactive, computed } from 'vue';
import { onLoad, onPageScroll, onShow } from '@dcloudio/uni-app';
import sheep from '@/sheep';
import { isEmpty } from 'lodash';
import {numSub} from "@/sheep/hooks/useGoods";



const state = reactive({
  news:{}
});



onLoad(async (options) => {
  // 非法参数
  if (!options.id) {
    state.news = null;
    return;
  }
  state.id = options.id;
  // 加载商品信息
  sheep.$api.news.detail(state.id).then((res) => {
    console.log('种子数据：', res)
    state.news = res.data;

  });

});
</script>

<style lang="scss" scoped>
:deep() {
  .uni-input-wrapper {
    width: 320rpx;
  }

  .uni-easyinput__content-input {
    font-size: 28rpx;
    height: 72rpx;
    text-align: right !important;
    padding-right: 0 !important;

    .uni-input-input {
      font-weight: 500;
      color: #333333;
      font-size: 26rpx;
      height: 32rpx;
      margin-top: 4rpx;
    }
  }
  .uni-easyinput__content {
    display: flex !important;
    align-items: center !important;
    justify-content: right !important;
  }
}
.score-img {
  width: 36rpx;
  height: 36rpx;
  margin: 0 4rpx;
}
.order-item {
  height: 80rpx;

  .item-title {
    font-size: 28rpx;
    font-weight: 400;
  }

  .item-value {
    font-size: 28rpx;
    font-weight: 500;
  }

  .item-value {
    font-family: OPPOSANS;
  }
  .text-disabled {
    color: #bbbbbb;
  }

  .item-icon {
    color: $dark-9;
  }

  .remark-input {
    text-align: right;
  }

  .item-placeholder {
    color: $dark-9;
    font-size: 26rpx;
    text-align: right;
  }
}

.total-box-footer {
  height: 90rpx;

  .total-num {
    color: #333333;
    font-family: OPPOSANS;
  }
}

.footer-box {
  height: 100rpx;

  .submit-btn {
    width: 240rpx;
    height: 70rpx;
    font-size: 28rpx;
    font-weight: 500;

    .goto-pay-text {
      line-height: 28rpx;
    }
  }

  .cancel-btn {
    width: 240rpx;
    height: 80rpx;
    font-size: 26rpx;
    background-color: #e5e5e5;
    color: $dark-9;
  }
}
.title {
  font-size: 36rpx;
  font-weight: bold;
  color: #333333;
}
.subtitle {
  font-size: 28rpx;
  color: #999999;
}
.cicon-checkbox {
  font-size: 36rpx;
  color: var(--ui-BG-Main);
}
.cicon-box {
  font-size: 36rpx;
  color: #999999;
}


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
.item {
  margin: 5px; /* 举例中的外边距 */
}

/* 为了确保第二行只有一列，我们可以通过媒体查询或者直接设定第三列占据全部空间 */
.item3 {
  flex-basis: 100%; /* 或使用width: 100%; 在此上下文中两者效果相似 */
}
.text-detail{
  font-size: 24rpx;
  font-weight: 400;
  color: #999999;
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

.planting-box {
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
</style>
