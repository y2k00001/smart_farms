import request from '@/sheep/request';

/**
 * 种子相关接口
 */
export default {
  // 农场详情
  detail: (id, params = {}) =>
    request({
      url: '/h5/seed/' + id,
      method: 'GET',
      custom: {
        showLoading: false,
        showError: false,
      },
    }),

    // 种子列表
    list: (data, params) =>
        request({
            url: '/h5/seed/list',
            method: 'get',
            data,
            params,
            custom: {
                showLoading: false,
                showError: false,
            },
        }),



};
