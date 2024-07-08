import request from '@/sheep/request';

export default {
    list: (data,params) =>
        request({
            url: '/h5/news/list',
            method: 'Get',
            data,
            params,
            custom: {
                showLoading: false,
                showError: false,
            },
        }),

    detail: (id) =>
        request({
            url: '/h5/news/'+id,
            method: 'Get'
        }),
};
