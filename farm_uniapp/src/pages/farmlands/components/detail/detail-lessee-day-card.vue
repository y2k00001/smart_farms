<template>
  <view class="detail-comment-card bg-white">
    <view class="card-header ss-flex ss-col-center ss-row-between ss-p-b-30">
      <view class="ss-flex ss-col-center">
        <view class="line"></view>
        <view class="title ss-m-l-20 ss-m-r-10">选择租赁周期</view>
      </view>
      <view class="ss-flex ss-col-center">
        <picker @change="bindPickerChange" :value="state.index" :range="state.array">
          <view class="uni-input">{{state.array[state.index]}}
            <text class="cicon-forward"></text>
          </view>


        </picker>
      </view>
    </view>
  </view>
</template>

<script setup>
import { reactive, onBeforeMount } from 'vue';
import sheep from '@/sheep';
import commentItem from './comment-item.vue';
const props = defineProps({
  goodsId: {
    type: [Number, String],
    default: 0,
  },
});
const state = reactive({
  commentList: [],
  total: 0,
  array: ['一个月', '一季度', '半年', '一年'],
  index:0
});

const emit = defineEmits(['lesseeDayChange']);
onBeforeMount(() => {

});

function bindPickerChange(e){
  console.log('picker发送选择改变，携带值为', e.detail.value);
  state.index = e.detail.value;
  emit("lesseeDayChange",e.detail.value);
}

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
  border-bottom: 2rpx solid #eeeeee;
  &:last-child {
    border: none;
  }
}
</style>
