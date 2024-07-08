import request from '@/sheep/request';

export default {
    list: (data,params) =>
        request({
            url: 'h5/order/myFarmlandLesseeList',
            method: 'POST',
            data,
            params,
            custom: {
                showLoading: false,
                showError: false,
            },
        }),

    detail: (data) =>
        request({
            url: '/h5/order/myFarmlandLesseeDetail',
            method: 'POST',
            data,
        }),
    activityDetail: (id) =>
        request({
            url: 'h5/coupon/activity/get?id=' + id,
            method: 'GET',
        }),
    userCoupon: (data,params) =>
        request({
            url: 'h5/coupon/list',
            method: 'POST',
            params,
            data,
            custom: {
                showLoading: true,
            },
        }),
    get: (id) =>
        request({
            url: 'h5/coupon/receive?id=' + id,
            method: 'POST',
        }),
    listByGoods: (id) =>
        request({
            url: 'coupon/listByGoods/' + id,
            method: 'GET',
        }),
};
