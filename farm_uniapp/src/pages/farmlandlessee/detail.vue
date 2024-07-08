<template>
  <s-layout title="确认订单">

    <view class="order-card-box ss-m-b-14">


      <!--农场信息展示-->
      <s-farmlands-item
          :farmlandName="state.farmlandLessee.farmlandVO.farmlandName"
          :landAreas="state.farmlandLessee.landArea"
          :location="state.farmlandLessee.farmlandVO.location"
          :img="state.img"
          marginBottom="10"
          :lesseeDayText = "state.farmlandLessee.lesseeDay"
          :service ="state.farmlandLessee.landService"
      >
      </s-farmlands-item>

      <!--租赁周期-->
      <view class="bg-white ss-p-20 ss-r-20">
        <view class="order-item ss-flex ss-col-center ss-row-between">
          <view class="item-title">租赁周期</view>
          <view class="ss-flex ss-col-center" >
            <!--            <text class="item-value">{{state.lesseeDayArray[state.lesseeDayIndex]}} </text>-->
            <view class="ss-flex ss-col-center">
              {{state.farmlandLessee.lesseeDay}}天
            </view>
          </view>
        </view>
      </view>

      <!--服务类型-->
      <view class="bg-white ss-p-20 ss-r-20">
        <view class="order-item ss-flex ss-col-center ss-row-between">
          <view class="item-title">服务类型</view>
          <view class="ss-flex ss-col-center">
            {{state.farmlandLessee.landService.serviceName}}
          </view>
        </view>
      </view>

      <!--选择种子-->
      <view class="bg-white ss-p-20 ss-r-20" v-if="state.farmlandLessee.landService.serviceType < 30">
        <view class="order-item ss-flex ss-col-center ss-row-between">
          <view class="item-title">选择种子</view>
          <view class="ss-flex ss-col-center">
                {{state.seedNameStr}}
          </view>
        </view>
      </view>

      <!--全托管-配送信息-->
      <view class="bg-white ss-p-20 ss-r-20"  v-if="state.farmlandLessee.landService.serviceType < 20">
        <view class="order-item ss-flex ss-col-center ss-row-between">
          <view class="item-title">配送信息</view>
        </view>
        <view  class="bg-white address-box ss-m-b-14 ss-r-b-10"  @tap="onSelectAddress">
          <s-address-item :item="state.addressInfo" :hasBorderBottom="false">
            <view class="ss-rest-button"><text class="_icon-forward"></text></view>
          </s-address-item>
        </view>
      </view>

      <!--半托管-线下采摘信息-->
      <view class="ss-modal-box bg-white" v-if="state.farmlandLessee.landService.serviceType < 30">
        <view class="modal-header">采摘信息</view>
        <view class="modal-content ss-p-t-50">
          <view class="sale-item ss-flex ss-col-top" >
            <view class="item-title">采摘地址:</view>
            <view class="item-value">{{ state.farmlandLessee.farmlandVO.location }}</view>
          </view>

          <view class="sale-item ss-flex ss-col-top" >
            <view class="item-title">预约电话</view>
            <view class="item-value">{{ state.farmlandLessee.farmlandVO.contactPhone }}</view>
          </view>


        </view>

      </view>


    </view>
    <!--租户信息-->
    <view class="bg-white ss-p-20 ss-r-20 ss-m-b-10">
      <view class="order-item ss-flex ss-col-center ss-row-between">
        <view class="item-title">租户信息</view>
      </view>
      <view class="order-item ss-flex ss-col-center ss-row-between ss-p-x-20 bg-white ss-r-10">
        <view class="item-title">姓名</view>
        <view class="ss-flex ss-col-center">
          {{state.farmlandLessee.lessee.lesseeName}}
        </view>
      </view>
      <view class="order-item ss-flex ss-col-center ss-row-between ss-p-x-20 bg-white ss-r-10">
        <view class="item-title">电话</view>
        <view class="ss-flex ss-col-center">
          {{state.farmlandLessee.lessee.lesseePhone}}
        </view>
      </view>
      <view class="order-item ss-flex ss-col-center ss-row-between ss-p-x-20 bg-white ss-r-10">
        <view class="item-title">备注</view>
        <view class="ss-flex ss-col-center">
          {{state.farmlandLessee.lessee.remark}}
        </view>
      </view>

    </view>


    <s-coupon-select
        v-model="state.orderInfo.couponList"
        :show="state.showCoupon"
        @confirm="onSelectCoupon"
        @close="state.showCoupon = false"

    />
    <su-popup
        :show="state.showAgreement "
        type="dialog"
        round="10"
        :space="sys_navBar + 38"
        backgroundColor="#F6F6F6"
        :zIndex="10"
        @close="state.showAgreement = false"
    >
      <view>
        <s-agreement />
      </view>
    </su-popup>

    <!-- 合同信息 -->
    <!-- 作物信息 -->
    <!-- 田间管理记录 -->

    <!-- 底部 -->
    <su-fixed bottom :opacity="false" bg="bg-white" placeholder :noFixed="false" :index="200">
      <view class="footer-box border-top ss-flex ss-row-between ss-p-x-20 ss-col-center">
        <view class="total-box-footer ss-flex ss-flex-wrap">

          <view class="total-num ss-font-30 text-red item "> ￥{{state.farmlandLessee.lesseeAmount}}元</view>
          <view class="text-detail" @tap="onShowAmountDetail">明细</view>
<!--          <view class="item item3">-->
<!--            <checkbox-group @change="checkboxChange">-->
<!--              <label class="">-->
<!--                <checkbox :value="false" :checked="false" style="transform:scale(0.7)" class="round green" />-->
<!--                阅读并同意<text class="text-blue">《租赁协议》</text>-->
<!--              </label>-->
<!--            </checkbox-group>-->

<!--          </view>-->
        </view>



        <view>
          <button
              v-if="state.farmlandLessee.status === 0 "
              class="ss-reset-button ui-BG-Main-Gradient ss-r-40 submit-btn ui-Shadow-Main"
              @tap="continuePay"
          >
            {{ exchangeNow ? '立即兑换' : '继续支付' }}
          </button>
        </view>
        <view class="planting-box ss-flex ss-col-center ss-row-center"
              v-if="state.farmlandLessee.status === 1 "    >
          种植中
        </view>
        <view class="finish-box ss-flex ss-col-center ss-row-center"
              v-if="state.farmlandLessee.status === 2 "     >
          已结束
        </view>
      </view>
    </su-fixed>
    <su-popup
        :show="state.showAmountDetail "
        type="center"
        round="10"
        :space="sys_navBar + 38"
        backgroundColor="#F6F6F6"
        :zIndex="10"
        @close="state.showAmountDetail = false"
    >
      <view class="ss-modal-box bg-white">
        <view class="modal-header">金额明细</view>
        <view class="modal-content ss-p-t-50">
          <view class="sale-item ss-flex ss-col-top" >
            <view class="item-title">土地租赁费用</view>
            <view class="item-value">¥{{state.landLeaseAmount}}</view>
          </view>

          <view class="sale-item ss-flex ss-col-top" >
            <view class="item-title">土地服务费用</view>
            <view class="item-value">¥{{state.landServiceAmount}}</view>
          </view>

          <view class="sale-item ss-flex ss-col-top" >
            <view class="item-title">种子费用</view>
            <view class="item-value">¥{{state.landSeedAmount}}</view>
          </view>

          <view class="sale-item ss-flex ss-col-top" >
            <view class="item-title">总计</view>
            <view class="item-value">¥{{state.orderAmount}}元</view>
          </view>
        </view>
      </view>
    </su-popup>
  </s-layout>
</template>

<script setup>
import { reactive, computed } from 'vue';
import { onLoad, onPageScroll, onShow } from '@dcloudio/uni-app';
import sheep from '@/sheep';
import { isEmpty } from 'lodash';
import {numSub} from "@/sheep/hooks/useGoods";



const state = reactive({
  farmlandId: '',
  landAreaId: '',
  serviceId: '',
  farmlandLessee:{
    landArea:{}
  },
  lesseeDayArray: ['一个月', '一季度', '半年', '一年'],
  lesseeDayIndex: 0,
  lesseeDays: [30, 90, 180, 365],
  lesseeDay: 30,
  service:{},
  farmlandServiceList:[],
  serviceListIndex:0,
  farmlandServiceNameList:[],
  farmlandServiceIdList:[],
  farmlandSeedList:[],
  formSeedList:[],
  seedListIndex:0,
  farmlandInfo:{},
  showAgreement:false,
  showAmountDetail:false,
  orderAmount: 0.00,
  orderPayload: {},
  orderInfo: {},
  landLeaseAmount:0,
  landServiceAmount:0,
  landSeedAmount:0,
  addressInfo: {},
  invoiceInfo: {},
  totalNumber: 0,
  showCoupon: false,
  couponInfo: [],
  showDiscount: false,
  img:'',
  seedNameStr:''
});

// 立即兑换(立即兑换无需跳转收银台)
const exchangeNow = computed(
    () => state.orderPayload.order_type === 'score' && state.orderInfo.pay_fee == 0,
);

// 选择地址
function onSelectAddress() {
  uni.$once('SELECT_ADDRESS', (e) => {
    changeConsignee(e.addressInfo);
  });
  sheep.$router.go('/pages/user/address/list');
}

// 更改收货人地址&计算订单信息
async function changeConsignee(addressInfo = {}) {
  if (isEmpty(addressInfo)) {
    const res = await sheep.$api.user.address.default();
    if (!isEmpty(res)) {
      addressInfo = res;
    }
  }
  if (!isEmpty(addressInfo)) {
    state.addressInfo = addressInfo;
    state.orderPayload.address_id = state.addressInfo.id;
  }

}





function onShowAmountDetail(){
  state.showAmountDetail = true;

  calculateOrderAmount();
}

function checkboxChange(e){
  console.log(e);
  if(e.detail.value.length>0){
    state.showAgreement = true;
  }else {
    state.showAgreement = false;
  }
}

async function calculateOrderAmount() {
  // 总金额 =（地块面积*地块面积单价）*（租赁天数/30）+（服务单价*（租赁天数/30））+种子单价*种子数量
  let area = state.farmlandLessee.landArea.area? (state.farmlandLessee.landArea.area) : 0;
  let areaPrice = state.farmlandLessee.landArea? (state.farmlandLessee.landArea.unitPrice) : 0;
  let servicePrice = state.farmlandLessee.landService.servicePrice? ( state.farmlandLessee.landService.servicePrice) : 0;
  state.landLeaseAmount = (area) * (areaPrice) * (state.farmlandLessee.lesseeDay);
  state.landServiceAmount = servicePrice * (state.farmlandLessee.lesseeDay);
  state.landSeedAmount = 0;
  state.orderAmount = (area) * (areaPrice) * (state.farmlandLessee.lesseeDay)
      + servicePrice * (state.farmlandLessee.lesseeDay);

  console.log("订单金额= 面积%s*单价%s*租赁天数%s+服务单价%s*租赁天数%s+种子单价%s*种子数量%s"
      ,area,areaPrice,state.farmlandLessee.lesseeDay,servicePrice,sstate.farmlandLesseeate.lesseeDay,0,0);
  console.log(state.orderAmount);

}


// 继续支付跳转
async function continuePay() {
  sheep.$router.redirect('/pages/pay/index', {
    orderId: state.farmlandLessee.farmlandLesseeOrder.orderId,
    totalAmount: state.farmlandLessee.lesseeAmount,
    farmlandLesseeId:state.farmlandLessee.farmlandLesseeId,
    orderType: 'memberConsumer',
  });

}

// 获取预下单信息
async function getFarmlandLesseeDetail() {
  let param = {};
  param.farmlandLesseeId = state.farmlandLesseeId;
  param.orderId = state.orderId;


  let res = await sheep.$api.farmlandlessee.detail(param);
  if (!res) {
    setTimeout( () => {
      sheep.$router.back()
    }, 2000)
    return
  }
  console.log('预下单信息：', res);
  state.farmlandLessee = res.data;
  state.img = state.farmlandLessee.farmlandVO.files[0].url;
  state.seedNameStr = state.farmlandLessee.seedList.map(item => item.seedName).join('、');
}




onLoad(async (options) => {
  console.log('options', options);
  state.farmlandLesseeId = options.farmlandLesseeId;
  state.orderId = options.orderId;
  getFarmlandLesseeDetail(state.farmlandLesseeId, state.orderId);

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
