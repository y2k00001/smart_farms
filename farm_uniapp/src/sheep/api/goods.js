import request from '@/sheep/request';

export default {
  // 商品详情
  detail: (id, params = {}) =>
    request({
      url: 'no-auth/product/detail/' + id,
      method: 'GET',
      custom: {
        showLoading: false,
        showError: false,
      },
    }),

    // 商品列表
    list: (data, params) =>
        request({
            url: 'no-auth/product/list',
            method: 'post',
            data,
            params,
            custom: {
                showLoading: false,
                showError: false,
            },
        }),

  // 商品查询
  ids: (params = {}) =>
    request({
      url: 'goods/goods/ids',
      method: 'GET',
      params,
      custom: {
        showLoading: false,
        showError: false,
      },
    }),

  // 商品评价列表
  comment: (id, params = {}) =>
    request({
      url: 'goods/comment/' + id,
      method: 'GET',
      params,
      custom: {
        showLoading: false,
        showError: false,
      },
    }),
  // 商品评价类型
  getType: (id) =>
    request({
      url: 'goods/comment/getType/' + id,
      method: 'GET',
      custom: {
        showLoading: false,
        showError: false,
      },
    }),
  // 活动商品查询
  // 商品查询
  activity: (params = {}) =>
    request({
      url: 'goods/goods/activity',
      method: 'GET',
      params,
      custom: {
        showLoading: false,
        showError: false,
      },
    }),
  activityList: (params = {}) =>
    request({
      url: 'goods/goods/activityList',
      method: 'GET',
      params,
    }),


};
