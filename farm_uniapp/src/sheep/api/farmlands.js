import request from '@/sheep/request';

export default {
  // 农场详情
  detail: (id, params = {}) =>
    request({
      url: '/front/farmland/' + id,
      method: 'GET',
      custom: {
        showLoading: false,
        showError: false,
      },
    }),

    // 商品列表
    list: (data, params) =>
        request({
            url: '/front/farmland/list',
            method: 'get',
            data,
            params,
            custom: {
                showLoading: false,
                showError: false,
            },
        }),



};
