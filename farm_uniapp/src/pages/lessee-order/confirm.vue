<template>
  <s-layout title="确认订单">

    <view class="order-card-box ss-m-b-14">


      <!--农场信息展示-->
      <s-farmlands-item
          :farmlandName="state.orderInfo.farmlandName"
          :landAreas="state.orderInfo.landAreas"
          :location="state.orderInfo.location"
          :img="state.img"
          marginBottom="10"
          :lesseeDayText = "state.lesseeDayArray[state.lesseeDayIndex]"
          :service ="state.service"
      >
      </s-farmlands-item>

      <!--租赁周期-->
      <view class="bg-white ss-p-20 ss-r-20">
        <view class="order-item ss-flex ss-col-center ss-row-between">
          <view class="item-title">租赁周期</view>
          <view class="ss-flex ss-col-center" >
<!--            <text class="item-value">{{state.lesseeDayArray[state.lesseeDayIndex]}} </text>-->
            <view class="ss-flex ss-col-center">
              <picker @change="bindPickerChange" :value="state.lesseeDayIndex" :range="state.lesseeDayArray">
                <view class="uni-input">{{state.lesseeDayArray[state.lesseeDayIndex]}}
                  <text class="cicon-forward"></text>
                </view>
              </picker>
            </view>
          </view>
        </view>
      </view>

      <!--服务类型-->
      <view class="bg-white ss-p-20 ss-r-20">
        <view class="order-item ss-flex ss-col-center ss-row-between">
          <view class="item-title">服务类型</view>
          <view class="ss-flex ss-col-center">
            <picker @change="bindPickerServiceChange" :value="state.serviceListIndex" :range="state.farmlandServiceNameList">
              <view class="uni-input">{{state.farmlandServiceNameList[state.serviceListIndex]}}
                <text class="cicon-forward"></text>
              </view>
            </picker>
          </view>
        </view>
      </view>

      <!--选择种子-->
      <view class="bg-white ss-p-20 ss-r-20" v-if="state.farmlandServiceList[state.serviceListIndex].serviceType < 30">
        <view class="order-item ss-flex ss-col-center ss-row-between">
          <view class="item-title">选择种子</view>
          <view class="ss-flex ss-col-center">
            <picker @change="bindPickerSeedChange" :value="state.seedListIndex" :range="state.farmlandSeedList" :range-key="'seedName'">
              <view class="uni-input">{{state.farmlandSeedList[state.seedListIndex].seedName}}
                <text class="cicon-forward"></text>
              </view>
            </picker>
          </view>
        </view>
      </view>

      <!--全托管-配送信息-->
      <view class="bg-white ss-p-20 ss-r-20"  v-if="state.farmlandServiceList[state.serviceListIndex].serviceType < 20">
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
      <view class="ss-modal-box bg-white" v-if="state.farmlandServiceList[state.serviceListIndex].serviceType < 30">
        <view class="modal-header">采摘信息</view>
        <view class="modal-content ss-p-t-50">
          <view class="sale-item ss-flex ss-col-top" >
            <view class="item-title">采摘地址:</view>
            <view class="item-value">{{state.farmlandInfo.location}}</view>
          </view>

          <view class="sale-item ss-flex ss-col-top" >
            <view class="item-title">预约电话</view>
            <view class="item-value">{{state.farmlandInfo.contactPhone}}</view>
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
            <uni-easyinput
                maxlength="20"
                placeholder="请填写姓名"
                v-model="state.orderPayload.lessee_name"
                :inputBorder="false"
                :clearable="false"
            ></uni-easyinput>
          </view>
        </view>
        <view class="order-item ss-flex ss-col-center ss-row-between ss-p-x-20 bg-white ss-r-10">
          <view class="item-title">电话</view>
          <view class="ss-flex ss-col-center">
            <uni-easyinput
                maxlength="20"
                placeholder="请填写联系电话"
                v-model="state.orderPayload.lessee_phone"
                :inputBorder="false"
                :clearable="false"
            ></uni-easyinput>
          </view>
        </view>
        <view class="order-item ss-flex ss-col-center ss-row-between ss-p-x-20 bg-white ss-r-10">
          <view class="item-title">备注</view>
          <view class="ss-flex ss-col-center">
            <uni-easyinput
                maxlength="20"
                placeholder="备注信息.."
                v-model="state.orderPayload.remark"
                :inputBorder="false"
                :clearable="false"
            ></uni-easyinput>
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

    <!-- 底部 -->
    <su-fixed bottom :opacity="false" bg="bg-white" placeholder :noFixed="false" :index="200">
      <view class="footer-box border-top ss-flex ss-row-between ss-p-x-20 ss-col-center">
        <view class="total-box-footer ss-flex ss-flex-wrap">

            <view class="total-num ss-font-30 text-red item "> ￥{{state.orderAmount}}元</view>
            <view class="text-detail" @tap="onShowAmountDetail">明细</view>
          <view class="item item3">
            <checkbox-group @change="checkboxChange">
              <label class="">
                <checkbox :value="false" :checked="false" style="transform:scale(0.7)" class="round green" />
                阅读并同意<text class="text-blue">《租赁协议》</text>
              </label>
            </checkbox-group>

          </view>
        </view>



        <view>
        <button
            class="ss-reset-button ui-BG-Main-Gradient ss-r-40 submit-btn ui-Shadow-Main"
            @tap="onConfirm"
        >
          {{ exchangeNow ? '立即兑换' : '提交订单' }}
        </button>
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
  img:''
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

//租赁周期选择监听函数
function bindPickerChange(e){
  console.log('picker发送选择改变，携带值为', e.detail.value);

  state.lesseeDayIndex = e.detail.value;
  state.lesseeDay = state.lesseeDays[state.lesseeDayIndex];
  // 重新计算订单金额
  calculateOrderAmount();
}

//服务选择监听函数
function bindPickerServiceChange(e){
  console.log('picker发送选择改变，携带值为', e.detail.value);
  state.serviceListIndex = e.detail.value;
  // 重新计算订单金额
  calculateOrderAmount();
}
//种子选择监听函数
function bindPickerSeedChange(e){
  console.log('picker发送选择改变，携带值为', e.detail.value);
  state.seedListIndex = e.detail.value;
  state.formSeedList.length = 0;
  state.formSeedList.push(state.farmlandSeedList[state.seedListIndex].seedId);
  // 重新计算订单金额
  calculateOrderAmount();
}

function onShowAmountDetail(){
  state.showAmountDetail = true;
}

function checkboxChange(e){
  console.log(e);
  if(e.detail.value.length>0){
    state.showAgreement = true;
  }else {
    state.showAgreement = false;
  }
}
// 选择发票信息
function onSelectInvoice() {
  uni.$once('SELECT_INVOICE', (e) => {
    state.invoiceInfo = e.invoiceInfo;
    state.orderPayload.invoice_id = e.invoiceInfo.id || 0;
  });
  sheep.$router.go('/pages/user/invoice/list');
}

// 提交订单/立即兑换
function onConfirm() {
  if (!state.orderPayload.address_id && state.orderInfo.need_address === 1) {
    sheep.$helper.toast('请选择收货地址');
    return;
  }
  submitOrder();

}

// 创建订单&跳转
async function submitOrder() {
  const params = {
    addressId:state.orderPayload.address_id,
    farmlandId:state.farmlandId,
    landAreaId:state.landAreaId,
    serviceId:state.serviceId,
    lesseeDay:state.lesseeDay,
    lesseeName:state.orderPayload.lessee_name,
    lesseePhone:state.orderPayload.lessee_phone,
    lesseeRemark:state.orderPayload.remark,
    orderAmount:state.orderAmount,
    seedIds:state.formSeedList,
    payType: 2,


  }
  console.log('订单params：', params)
  const res = await sheep.$api.order.create(params);
  // sheep.$helper.toast('下单成功')

  if (state.orderPayload.payAmount == 0) {
    sheep.$router.redirect('/pages/pay/result', {
      orderSN: res,
    });
  }
  sheep.$router.redirect('/pages/pay/index', {
    orderId: res.data.orderId,
    totalAmount: state.orderAmount,
    farmlandLesseeId:res.data.farmlandLesseeId,
    orderType: 'memberConsumer',
  });
  // if (exchangeNow.value) {
  //   sheep.$router.redirect('/pages/pay/result', {
  //     orderSN: data.order_sn,
  //   });
  // } else {
  //   sheep.$router.redirect('/pages/pay/index', {
  //     orderSN: data.order_sn,
  //   });
  // }
}

// 获取预下单信息
async function getPreOrderInfo() {
  let preOrderParam = {};
  preOrderParam.farmlandId = state.farmlandId;
  preOrderParam.landAreaId = state.landAreaId;
  preOrderParam.serviceId = state.serviceId;

  let res = await sheep.$api.order.getPreOrder(preOrderParam);
  if (!res) {
    setTimeout( () => {
      sheep.$router.back()
    }, 2000)
    return
  }
  console.log('预下单信息：', res);
  state.orderInfo = res.data;
  state.img = res.data.files[0].url;
  calculateOrderAmount();

}

async function getServiceInfo() {

  let res = await sheep.$api.service.detail(state.serviceId);
  state.service = res.data;

}

async function getFarmLandServiceList(farmlandId) {
  let res = await sheep.$api.service.farmlandService(farmlandId);
  state.farmlandServiceList = res.data;
  state.farmlandServiceNameList = res.data.map(
      item =>  item.serviceName+"\n"+item.servicePrice+"元/天"
  );

  state.farmlandServiceIdList = res.data.map(item => item.serviceId);
  state.farmlandServiceIdList.forEach(item => {
    if (item === state.serviceId) {
      state.serviceListIndex = state.farmlandServiceIdList.indexOf(item)
    }
  })
}
async function getFarmlandInfo() {

  let res = await sheep.$api.farmlands.detail(state.id);
  state.farmlandSeedList = res.data.seedVOS;
  state.formSeedList.length = 0;
  state.formSeedList.push(state.farmlandSeedList[state.seedListIndex].seedId);
  state.farmlandInfo = res.data;

}

async function calculateOrderAmount() {
    // 总金额 =（地块面积*地块面积单价）*（租赁天数/30）+（服务单价*（租赁天数/30））+种子单价*种子数量
  let area = state.orderInfo? (state.orderInfo.landAreas.area) : 0;
  let areaPrice = state.orderInfo? (state.orderInfo.landAreas.unitPrice) : 0;
  let servicePrice = state.orderInfo? (state.orderInfo.landServices.servicePrice) : 0;
  state.landLeaseAmount = (area) * (areaPrice) * (state.lesseeDay);
  state.landServiceAmount = servicePrice * (state.lesseeDay);
  state.landSeedAmount = 0;
  state.orderAmount = (area) * (areaPrice) * (state.lesseeDay)
        + servicePrice * (state.lesseeDay);

  console.log("订单金额= 面积%s*单价%s*租赁天数%s+服务单价%s*租赁天数%s+种子单价%s*种子数量%s"
      ,area,areaPrice,state.lesseeDay,servicePrice,state.lesseeDay,0,0);
  console.log(state.orderAmount);

}

onLoad(async (options) => {
  if (options.data) {
    state.orderPayload = JSON.parse(options.data);
    state.farmlandId = state.orderPayload.farmlandId;
    state.landAreaId = state.orderPayload.landAreaId;
    state.serviceId = state.orderPayload.serviceId;
    state.lesseeDayIndex = state.orderPayload.lesseeDay;
    state.lesseeDay = state.lesseeDays[state.lesseeDayIndex];
    state.id=state.orderPayload.id;
    changeConsignee();
    getPreOrderInfo();
    getServiceInfo();
    getFarmLandServiceList(state.farmlandId);
    getFarmlandInfo();


  }
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

</style>
