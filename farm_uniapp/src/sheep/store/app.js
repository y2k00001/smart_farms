import appApi from '@/sheep/api/app';
import { defineStore } from 'pinia';
import $platform from '@/sheep/platform';
import $router from '@/sheep/router';
import user from './user';
import sys from './sys';

const app = defineStore({
  id: 'app',
  state: () => ({
    info: {
      // 应用信息
      name: '', // 商城名称
      logo: '', // logo
      version: '', // 版本号
      cdnurl: '', // 云存储域名
      filesystem: '', // 云存储平台
      user_protocol: {}, // 用户协议
      privacy_protocol: {}, // 隐私协议
      about_us: {}, // 关于我们
      copyright: '', // 版权信息 I
      copytime: '', // 版权信息 II
    },
    platform: {
      payment: [], // 支持的支付方式
      recharge_payment: [], // 支持的充值支付方式
      share: {
        methods: [], // 支持的分享方式
        forwardInfo: {}, // 默认转发信息
        posterInfo: {}, // 海报信息
        linkAddress: '', // 复制链接地址
      },
      auto_login: 0, // 自动登陆
      bind_mobile: 0, // 登陆后绑定手机号提醒 (弱提醒，可手动关闭)
    },
    chat: {},
    template: {
      // 店铺装修模板
      basic: {}, // 基本信息
      home: {
        // 首页模板
        style: {},
        data: [],
      },
      user: {
        // 个人中心模板
        style: {},
        data: [],
      },
    },
    shareInfo: {}, // 全局分享信息
    authInfo: null
  }),
  actions: {
    // 获取Shopro应用配置和模板
    async init(templateId = null) {
      //检查网络
      // const networkStatus = await $platform.checkNetwork();
      // if (!networkStatus) {
      //   $router.error('NetworkError');
      // }
      const res = {
    "error": 0,
    "msg": "初始化",
    "data": {
        "app": {
            "name": "Shopro",
            "logo": "\/static\/img\/shop\/logo.png",
            "cdnurl": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com",
            "filesystem": "qcloud",
            "version": "1.1.13",
            "user_protocol": {
                "title": "用户协议",
                "id": "1"
            },
            "privacy_protocol": {
                "title": "隐私协议",
                "id": "2"
            },
            "about_us": {
                "title": "关于我们",
                "id": "3"
            },
            "copyright": "XXX有限公司版权所有",
            "copytime": "Copyright© 2018-2030"
        },
        "platform": {
            "auto_login": 0,
            "bind_mobile": 0,
            "payment": [
                "money",
                "alipay",
                "wechat",
                "offline"
            ],
            "recharge_payment": [
                "wechat",
                "alipay"
            ],
            "share": {
                "methods": [
                    "poster",
                    "link"
                ],
                "linkAddress": "https:\/\/mall.ichengle.top\/uni\/#\/",
                "posterInfo": {
                    "user_bg": "\/static\/img\/shop\/config\/user-poster-bg.png",
                    "goods_bg": "\/static\/img\/shop\/config\/goods-poster-bg.png",
                    "groupon_bg": "\/static\/img\/shop\/config\/groupon-poster-bg.png"
                }
            }
        },
        "template": {
            "basic": {
                "tabbar": {
                    "mode": 1,
                    "layout": 1,
                    "inactiveColor": "#8C8C8C",
                    "activeColor": "#FF7A0C",
                    "list": [
                        {
                            "inactiveIcon": "\/static\/icons\/home.png",
                            "activeIcon": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/static\/icons\/home.gif",
                            "url": "\/pages\/index\/index",
                            "text": "首页"
                        },
                        {
                            "inactiveIcon": "\/static\/icons\/cart.png",
                            "activeIcon": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/static\/icons\/cart.gif",
                            "url": "\/pages\/farmlands\/list",
                            "text": "农场"
                        },
                        {
                            "inactiveIcon": "\/static\/icons\/cart.png",
                            "activeIcon": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/static\/icons\/cart.gif",
                            "url": "\/pages\/index\/cart",
                            "text": "圈子"
                        },
                        {
                            "inactiveIcon": "\/static\/icons\/my.png",
                            "activeIcon": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/static\/icons\/my.gif",
                            "url": "\/pages\/index\/user",
                            "text": "我的"
                        }
                    ],
                    "background": {
                        "type": "color",
                        "bgImage": "",
                        "bgColor": "#FFFFFF"
                    }
                },
                "floatMenu": {
                    "show": 0,
                    "mode": 1,
                    "isText": 0,
                    "list": [
                        {
                            "src": "",
                            "url": "",
                            "title": {
                                "text": "",
                                "color": ""
                            }
                        }
                    ]
                },
                "popupImage": {
                    "list": [
                        {
                            "src": "\/storage\/decorate\/20221115\/6bfd03d0ad7f3d7f6ba7494c903cdc0c.png",
                            "url": "\/pages\/index\/category?id=21",
                            "show": 2
                        }
                    ]
                },
                "theme": "orange"
            },
            "home": {
                "data": [
                    {
                        "type": "imageBanner",
                        "data": {
                            "mode": 1,
                            "autoplay": true,
                            "interval": 500,
                            "indicator": 1,
                            "list": [
                                {
                                    "title": "banner01",
                                    "type": "image",
                                    "src": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/storage\/uniapp\/banner03.jpg",
                                    "poster": "",
                                    "url": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/storage\/uniapp\/banner03.jpg"
                                },
                                {
                                    "title": "banner01",
                                    "type": "image",
                                    "src": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/storage\/uniapp\/banner02.jpg",
                                    "poster": "",
                                    "url": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/storage\/uniapp\/banner02.jpg"
                                },
                                {
                                    "title": "banner01",
                                    "type": "image",
                                    "src": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/storage\/uniapp\/banner01.jpg",
                                    "poster": "",
                                    "url": "https:\/\/smart-farms.oss-cn-shenzhen.aliyuncs.com\/storage\/uniapp\/banner01.jpg"
                                }
                            ],
                            "space": 0
                        },
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": "#FFFFFF"
                            },
                            "marginLeft": 0,
                            "marginRight": 0,
                            "marginTop": 0,
                            "marginBottom": 10,
                            "borderRadiusTop": 0,
                            "borderRadiusBottom": 0,
                            "padding": 0
                        }
                    },
                    {
                        "type": "noticeBlock",
                        "data": {
                            "layout": 1,
                            "col": 5,
                            "row": 2,
                            "src":"\/storage\/uniapp\/notice-small.png",
                            "title":{
                                "text":"智慧农场平台现已正式上线！系统是基于5G智慧农业技术的综合性农业服务平台，旨在为城市居民提供便捷、高效的“云种菜”体验",
                                "color": "#222222"
                            },
                            "list": [

                            ]
                        },
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": ""
                            },
                            "marginLeft": 0,
                            "marginRight": 0,
                            "marginTop": 0,
                            "marginBottom": 10,
                            "borderRadiusTop": 0,
                            "borderRadiusBottom": 0,
                            "padding": 0
                        }
                    },
                    {
                        "type": "menuButton",
                        "data": {
                            "layout": 1,
                            "col": 5,
                            "row": 2,
                            "list": [
                                {
                                    "src": "\/storage\/uniapp\/farmland.png",
                                    "title": {
                                        "text": "租地种植",
                                        "color": "#222222"
                                    },
                                    "url": "\/pages\/farmlands\/list",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#68DE99",
                                        "bgColor": "#68DE99"
                                    }
                                },
                                {
                                    "src": "\/storage\/uniapp\/camera.png",
                                    "title": {
                                        "text": "农场实景",
                                        "color": "#222222"
                                    },
                                    "url": "\/pages\/live\/list",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    }
                                },
                                {
                                    "src": "\/storage\/uniapp\/breed.png",
                                    "title": {
                                        "text": "认采认养",
                                        "color": "#222222"
                                    },
                                    "url": "\/pages\/farming\/index",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    }
                                },
                                {
                                    "src": "\/storage\/uniapp\/party.png",
                                    "title": {
                                        "text": "农场活动",
                                        "color": "#222222"
                                    },
                                    "url": "\/pages\/news\/list?type=10",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    }
                                },
                                {
                                    "src": "\/storage\/uniapp\/store.png",
                                    "title": {
                                        "text": "农场商城",
                                        "color": "#222222"
                                    },
                                    "url": "\/pages\/shop\/index",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    }
                                },
                                {
                                    "src": "\/storage\/uniapp\/notice.png",
                                    "title": {
                                        "text": "农场公告",
                                        "color": "#222222"
                                    },
                                    "url": "\/pages\/notice\/list?type=20",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    }
                                },
                                {
                                    "src": "\/storage\/uniapp\/introduction.png",
                                    "title": {
                                        "text": "平台简介",
                                        "color": "#222222"
                                    },
                                    "url": "\/pages\/introduction\/index",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    }
                                }
                            ]
                        },
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": ""
                            },
                            "marginLeft": 0,
                            "marginRight": 0,
                            "marginTop": 0,
                            "marginBottom": 10,
                            "borderRadiusTop": 0,
                            "borderRadiusBottom": 0,
                            "padding": 0
                        }
                    },
                    {
                        "type": "imageCube",
                        "data": {
                            "borderRadiusTop": 8,
                            "borderRadiusBottom": 8,
                            "space": 7,
                            "list": [
                                {
                                    "width": 2,
                                    "height": 2,
                                    "top": 0,
                                    "left": 0,
                                    "src": "\/storage\/decorate\/20221115\/63cbe4b8088a28a129923b65f412fcb2.png",
                                    "url": "\/pages\/index\/category?id=21"
                                },
                                {
                                    "width": 2,
                                    "height": 1,
                                    "top": 0,
                                    "left": 2,
                                    "src": "\/storage\/decorate\/20221115\/50e077b0a5df1c48dcd9be4e47b03324.png",
                                    "url": "\/pages\/index\/category?id=21"
                                },
                                {
                                    "width": 2,
                                    "height": 1,
                                    "top": 1,
                                    "left": 2,
                                    "src": "\/storage\/decorate\/20221115\/a44ee2a893e6b296efcd167fe0dc246d.png",
                                    "url": "\/pages\/index\/category?id=21"
                                }
                            ]
                        },
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": ""
                            },
                            "marginLeft": 8,
                            "marginRight": 8,
                            "marginTop": 0,
                            "marginBottom": 10,
                            "borderRadiusTop": 0,
                            "borderRadiusBottom": 0,
                            "padding": 0
                        }
                    },
                    {
                        "type": "titleBlock",
                        "data": {
                            "src": "\/storage\/uniapp\/bk-long.png",
                            "location": "left",
                            "skew": 34,
                            "title": {
                                "text": "热门农场",
                                "color": "#FFFFFF",
                                "textFontSize": 16,
                                "other": []
                            },
                            "subtitle": {
                                "text": "热门农场2",
                                "color": "#8c8c8c",
                                "textFontSize": 0,
                                "other": []
                            },
                            "more": {
                                "show": 1,
                                "url": "\/pages\/index\/category?id=21"
                            }
                        },
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": ""
                            },
                            "marginLeft": 8,
                            "marginRight": 8,
                            "marginTop": 0,
                            "marginBottom": 0,
                            "borderRadiusTop": 0,
                            "borderRadiusBottom": 0,
                            "padding": 0,
                            "height": 40
                        }
                    },
                    {
                        "type": "farmlandsCard",
                        "data": {
                            "mode": 1,
                            "goodsFields": {
                                "title": {
                                    "show": 1,
                                    "color": "#262626"
                                },
                                "subtitle": {
                                    "show": 1,
                                    "color": "#8C8C8C"
                                },
                                "price": {
                                    "show": 1,
                                    "color": "#FF3000"
                                },
                                "original_price": {
                                    "show": 1,
                                    "color": "#C4C4C4"
                                },
                                "sales": {
                                    "show": 1,
                                    "color": "#C4C4C4"
                                },
                                "stock": {
                                    "show": 0,
                                    "color": "#C4C4C4"
                                }
                            },
                            "buyNowStyle": {
                                "mode": 1,
                                "text": "立即购买",
                                "color1": "#FE832A",
                                "color2": "#FF6000",
                                "src": ""
                            },
                            "tagStyle": {
                                "show": 0,
                                "src": ""
                            },
                            "goodsIds": [
                                17,
                                18,
                                19
                            ],
                            "borderRadiusTop": 6,
                            "borderRadiusBottom": 6,
                            "space": 8
                        },
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": ""
                            },
                            "marginLeft": 8,
                            "marginRight": 8,
                            "marginTop": 0,
                            "marginBottom": 10,
                            "borderRadiusTop": 0,
                            "borderRadiusBottom": 0,
                            "padding": 0
                        }
                    },
                    {
                        "type": "titleBlock",
                        "data": {
                            "src": "\/storage\/uniapp\/bk-long2.png",
                            "location": "left",
                            "skew": null,
                            "title": {
                                "text": "认采认养",
                                "color": "#FFFFFF",
                                "textFontSize": 16,
                                "other": []
                            },
                            "subtitle": {
                                "text": "",
                                "color": "#8c8c8c",
                                "textFontSize": 0,
                                "other": []
                            },
                            "more": {
                                "show": 1,
                                "url": "\/pages\/index\/category?id=21"
                            }
                        },
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": ""
                            },
                            "marginLeft": 8,
                            "marginRight": 8,
                            "marginTop": 0,
                            "marginBottom": 0,
                            "borderRadiusTop": 0,
                            "borderRadiusBottom": 0,
                            "padding": 0,
                            "height": 40
                        }
                    },
                    {
                        "type": "farmlandsCard",
                        "data": {
                            "mode": 2,
                            "goodsFields": {
                                "title": {
                                    "show": 1,
                                    "color": "#262626"
                                },
                                "subtitle": {
                                    "show": 1,
                                    "color": "#8C8C8C"
                                },
                                "price": {
                                    "show": 1,
                                    "color": "#FF3000"
                                },
                                "original_price": {
                                    "show": 1,
                                    "color": "#C4C4C4"
                                },
                                "sales": {
                                    "show": 1,
                                    "color": "#C4C4C4"
                                },
                                "stock": {
                                    "show": 0,
                                    "color": "#C4C4C4"
                                }
                            },
                            "buyNowStyle": {
                                "mode": 2,
                                "text": "立即购买",
                                "color1": "#E9B461",
                                "color2": "#EECC89",
                                "src": "\/storage\/decorate\/20221115\/4782356b4587dc4f4a218f2540a0bafc.png"
                            },
                            "tagStyle": {
                                "show": 0,
                                "src": ""
                            },
                            "goodsIds": [
                                14,
                                16,
                                12,
                                8,
                                9,
                                21
                            ],
                            "borderRadiusTop": 6,
                            "borderRadiusBottom": 6,
                            "space": 8
                        },
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": ""
                            },
                            "marginLeft": 8,
                            "marginRight": 8,
                            "marginTop": 0,
                            "marginBottom": 10,
                            "borderRadiusTop": 0,
                            "borderRadiusBottom": 0,
                            "padding": 0
                        }
                    },

                ],
                "style": {
                    "background": {
                        "color": "#FFFFFF",
                        "src": ""
                    },
                    "navbar": {
                        "mode": "inner",
                        "alwaysShow": 1,
                        "type": "color",
                        "color": "#EB3924",
                        "src": "",
                        "list": {
                            "mp": [
                                {
                                    "width": 5,
                                    "height": 1,
                                    "top": 0,
                                    "left": 0,
                                    "type": "search",
                                    "text": "",
                                    "textColor": "#111111",
                                    "src": "",
                                    "url": "",
                                    "placeholder": "请输入关键字",
                                    "borderRadius": 20
                                },
                                {
                                    "width": 1,
                                    "height": 1,
                                    "top": 0,
                                    "left": 5,
                                    "type": "image",
                                    "text": "",
                                    "textColor": "#111111",
                                    "src": "\/static\/quan.png",
                                    "url": "\/pages\/coupon\/list",
                                    "placeholder": "",
                                    "borderRadius": 0
                                }
                            ],
                            "app": [
                                {
                                    "width": 7,
                                    "height": 1,
                                    "top": 0,
                                    "left": 0,
                                    "type": "search",
                                    "text": "",
                                    "textColor": "#111111",
                                    "src": "",
                                    "url": "",
                                    "placeholder": "请输入关键字",
                                    "borderRadius": 23
                                },
                                {
                                    "width": 1,
                                    "height": 1,
                                    "top": 0,
                                    "left": 7,
                                    "type": "image",
                                    "text": "",
                                    "textColor": "#111111",
                                    "src": "\/static\/quan.png",
                                    "url": "\/pages\/coupon\/list",
                                    "placeholder": "",
                                    "borderRadius": 0
                                }
                            ]
                        }
                    }
                }
            },
            "user": {
                "data": [
                    {
                        "type": "userCard",
                        "style": {
                            "marginLeft": 0,
                            "marginRight": 0,
                            "marginTop": 70,
                            "marginBottom": 10,
                            "borderRadiusTop": 0,
                            "borderRadiusBottom": 0,
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": ""
                            }
                        }
                    },

                    {
                        "type": "farmlandlesseeCard",
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": "#FFFFFF"
                            },
                            "marginLeft": 10,
                            "marginRight": 10,
                            "marginTop": 0,
                            "marginBottom": 8,
                            "borderRadiusTop": 8,
                            "borderRadiusBottom": 8,
                            "padding": 0
                        }
                    },
                    {
                        "type": "orderCard",
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": "#FFFFFF"
                            },
                            "marginLeft": 10,
                            "marginRight": 10,
                            "marginTop": 0,
                            "marginBottom": 8,
                            "borderRadiusTop": 8,
                            "borderRadiusBottom": 8,
                            "padding": 0
                        }
                    },
                    // {
                    //     "type": "walletCard",
                    //     "style": {
                    //         "background": {
                    //             "type": "color",
                    //             "bgImage": "",
                    //             "bgColor": "#FFFFFF"
                    //         },
                    //         "marginLeft": 10,
                    //         "marginRight": 10,
                    //         "marginTop": 0,
                    //         "marginBottom": 8,
                    //         "borderRadiusTop": 8,
                    //         "borderRadiusBottom": 8,
                    //         "padding": 0
                    //     }
                    // },
                    {
                        "type": "menuGrid",
                        "data": {
                            "col": 4,
                            "list": [
                                {
                                    "src": "\/static\/icons\/set.png",
                                    "title": {
                                        "text": "清空缓存",
                                        "color": "#333"
                                    },
                                    "tip": {
                                        "text": "",
                                        "color": "#bbb"
                                    },
                                    "url": "\/pages\/public\/setting",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    },
                                    finish: true
                                },
                                {
                                    "src": "\/static\/icons\/edit.png",
                                    "title": {
                                        "text": "意见反馈",
                                        "color": "#333"
                                    },
                                    "tip": {
                                        "text": "",
                                        "color": "#bbb"
                                    },
                                    "url": "\/pages\/public\/feedback",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    },
                                    finish: true
                                },
                                {
                                    "src": "\/static\/icons\/message.png",
                                    "title": {
                                        "text": "常见问题",
                                        "color": "#333"
                                    },
                                    "tip": {
                                        "text": "",
                                        "color": "#bbb"
                                    },
                                    "url": "\/pages\/public\/richtext?key=mall.question&title=常见问题",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    },
                                    finish: true
                                },
                                {
                                    "src": "\/static\/icons\/userStar.png",
                                    "title": {
                                        "text": "关于我们",
                                        "color": "#333"
                                    },
                                    "tip": {
                                        "text": "",
                                        "color": "#bbb"
                                    },
                                    "url": "\/pages\/public\/richtext?key=mall.aboutUs&title=关于我们",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    },
                                    finish: true
                                },
                                {
                                    "src": "\/static\/icons\/privacy.png",
                                    "title": {
                                        "text": "隐私协议",
                                        "color": "#333"
                                    },
                                    "tip": {
                                        "text": "",
                                        "color": "#bbb"
                                    },
                                    "url": "\/pages\/public\/richtext?key=mall.privacyAgreement&title=隐私协议",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    },
                                    finish: true
                                },
                                {
                                    "src": "\/static\/icons\/team.png",
                                    "title": {
                                        "text": "我的团队",
                                        "color": "#333"
                                    },
                                    "tip": {
                                        "text": "",
                                        "color": "#bbb"
                                    },
                                    "url": "\/pages\/user\/address\/list",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    },
                                    finish: false
                                },
                                {
                                    "src": "\/static\/icons\/customer_service.png",
                                    "title": {
                                        "text": "联系客服",
                                        "color": "#333"
                                    },
                                    "tip": {
                                        "text": "",
                                        "color": "#bbb"
                                    },
                                    "url": "\/pages\/chat\/index",
                                    "badge": {
                                        "show": 0,
                                        "text": "",
                                        "color": "#FFFFFF",
                                        "bgColor": "#FF6000"
                                    },
                                    finish: true
                                }
                            ]
                        },
                        "style": {
                            "background": {
                                "type": "color",
                                "bgImage": "",
                                "bgColor": "#FFFFFF"
                            },
                            "marginLeft": 10,
                            "marginRight": 10,
                            "marginTop": 0,
                            "marginBottom": 10,
                            "borderRadiusTop": 8,
                            "borderRadiusBottom": 8,
                            "padding": 0
                        }
                    }
                ],
                "style": {
                    "background": {
                        "color": "#F6F6F6",
                        "src": "\/storage\/decorate\/20221115\/b530150a466c8cda0a4cd5b29e2c8d11.png"
                    },
                }
            }
        },
        "chat": {
            "room_id": "admin",
            "chat_domain": "https:\/\/api.shopro.sheepjs.com\/chat"
        }
    }
}
      if (res.error === 0) {
        this.info = res.data.app;
        this.platform = res.data.platform;
        this.template = res.data.template;
        if (!res.data.template) {
          $router.error('TemplateError');
        }
        this.chat = res.data.chat;

        // 加载主题
        const sysStore = sys();
        sysStore.setTheme();

        // 模拟用户登录
        const userStore = user();
        if (userStore.isLogin) {
          userStore.loginAfter();
        }
        return Promise.resolve(true);
      } else {
        $router.error('InitError', res.msg || '加载失败');
      }
    },
  },
  persist: {
    enabled: true,
    strategies: [
      {
        key: 'app-store',
      },
    ],
  },
});

export default app;
