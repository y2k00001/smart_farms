<template>
<view class="" >
  <mp-html class="" :content="content" :containerStyle="state.contentStyle">
  </mp-html>
</view>


</template>

<script setup>
import sheep from '@/sheep';
import {reactive} from "vue";


const props = defineProps({
  content: {
    type: String,
    default: ' <pre style="overflow-wrap: break-word; text-wrap: wrap;">&nbsp;&nbsp;&nbsp;&nbsp;租地种植协议智慧农场正式交付拉，秉承友好、平等、自愿的原则，对一米菜园租赁种菜做交付说明；我们为你提供什么服务内容？&nbsp;\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;1&nbsp;．地租：所使用土地的租赁费用。&nbsp;\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;2&nbsp;．防护篱笆和标识：根据您为耕地取的名字，为您提供防护篱笆和写有菜地名字的标识牌，标注您土地的归属！&nbsp;\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;3&nbsp;．水电费：耕种过程中所产生的水电费用。&nbsp;\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;4&nbsp;．劳动工具出借：日常农耕所使用的农耕用具及工作围裙等可提供免费租借。&nbsp;\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;5&nbsp;．确认种植计划：技术人员会为您提供多种农场种植计划。您可以根据喜好进行选择，以确立您的品种需求。我们将综合农时、气候等因素，对您的需求给出合理化建议，为您制作种植计划表，最后与您确认完成。&nbsp;\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;6&nbsp;．种苗与肥料：为保证作物营养充足，耕种前及作物生长中期所施用有机肥料。有机种植对种子有较高要求，您所使用的种子都是经过我们精心筛选的，非转基因、无化学处理。&nbsp;\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;7&nbsp;．农资基础物料：种植前期所使用的地膜、支撑作物所使用的架杆、育苗所使用的苗盘和草炭、日常农耕所使用的农耕用具等</pre><pre style="overflow-wrap: break-word; text-wrap: wrap;">&nbsp;&nbsp;&nbsp;&nbsp;8&nbsp;．农事操作拖管：农事操作的内容贯穿整个的种植期，包括：整地、做畦、播种、育苗、定植、中耕、除草等。&nbsp;\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;9&nbsp;．技术指导：当您希望到田间地头体验农耕时，我们将有专业的技术人员指点您如何进行耕作；而日常农民们帮您耕作的时候，我们的农艺师也将随时更正他们的耕作方法，以保证您作物的产量和品质。&nbsp;\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;10&nbsp;．农作物成长监控：您可通过微信小程序、手机&nbsp;APp&nbsp;、微信群、客服等多方式查看监控摄像头了解观察自家菜园的农作物生长情况，及时掌握作物动态\n' +
        '&nbsp;&nbsp;&nbsp;&nbsp;11&nbsp;．当作物成熟时，我们将第一时间通知您过来采摘。</pre><p><br/></p>',
  },
});

const state = reactive({
  contentStyle: 'padding: 5px; ' +
      'font-size: 18px; ' +
      'overflow: hidden; ' +
      'display: inline; ' +
      'white-space: pre-wrap; ' +
      'white-space: pre-line; ',
});

const onLoad = () => {

}
</script>
<style lang="scss" scoped>
.richtext {
  font-size: 24rpx;
  font-weight: 500;
  color: #999999;
  margin: 20rpx 0 0 0;
}
</style>
