import request from '@/sheep/request';

export default {
    list: (data,params) =>
        request({
            url: '/h5/growth/myGrowthList',
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
            url: '/h5/growth/myGrowthDetail',
            method: 'POST',
            data,
        }),
};
