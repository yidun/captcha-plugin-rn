# 行为式验证码
全新人机验证方式，高效拦截机器行为，业务安全第一道防线。搭载风险感知引擎，智能切换验证难度，安全性高，极致用户体验。读屏软件深度适配，视障群体也可轻松使用，符合工信部无障碍适配要求

## 平台支持（兼容性）
  | Android|iOS|  
  | ---- | ----- |
  | 适用版本区间：4.4 - 13.0|适用版本区间：9以上| 

## 环境准备

[CocoaPods安装教程](https://guides.cocoapods.org/using/getting-started.html)

## 资源引入/集成
```
npm install @yidun/captcha-plugin-rn
```

### 项目开发配置

## 调用示例

```
import React, {Component} from 'react';
import {
    SafeAreaView,
    NativeModules,
    Button
} from 'react-native';

const captchaHelper = NativeModules.NTESCaptchaHelper;

class Demo extends Component {
    render() {
        return (
            <SafeAreaView style={{flex: 1}}>
                <Button onPress={() => captchaHelper.init({
                    captcha_id: '易盾获取到的业务id',
                    is_no_sense_mode: false
                     styleConfig:{
                        radius: 10,
                        capBarTextColor: "#25D4D0",
                        capBarTextSize: 18,
                        capBarTextWeight: "bold",
                     }
                 })} title="初始化"/>
                <Button onPress={() => captchaHelper.showCaptcha()} title="显示验证码"/>
            </SafeAreaView>
        )
    }
}
```

## SDK 方法说明

### 1. 初始化

#### 代码说明：
```
import {NativeModules} from 'react-native';
const captchaHelper = NativeModules.NTESCaptchaHelper;//对象创建
captchaHelper.init(options)
```

##### options 支持的配置项说明

| key | value 类型 | 是否必填 | 默认值 | 描述 |
| ---- | ---- | -------- |------| ---- |
| captcha_id | String | 是 | 无 | 易盾获取到的业务 id |
| failed_max_retry_count | Number | 是 | 无 | 失败后尝试最大次数 |
| is_debug | Boolean | 否 | false | 是否启动 debug 模式 |
| is_no_sense_mode | Boolean | 否 | false | 是否为智能无感知 |
| dimAmount | Number | 否 | 0.5 | 验证码框遮罩层透明度 |
| is_touch_outside_disappear | Boolean | 否 | true | 点击弹窗外部是否可以关闭验证码 |
| timeout | Number | 否 | 10000 | 超时时间 |
| is_hide_close_button | Boolean | 否 | false | 是否隐藏关闭按钮 |
| use_default_fallback | Boolean| 否 | true | 是否采用默认降级 |
| language_type | String | 否 | zh-CN | 多语言语言类型 |
| loading_text | String | 否 | 智能检测中 | 自定义加载文案 |
| theme | String | 否 | light | 主题/dark、light两种 |
| is_show_loading | Boolean | 否 | true | 是否显示loading |
| is_close_button_bottom | Boolean | 否 | false | 关闭按钮是否在下方 |
| styleConfig | object/ReadableMap | 否 | 空 | 验证码高级UI自定义配置 |

###### language_type 多语言对应表

| 多语言值 | 说明 |
| ---- | ---- |
| zh-TW | 中文繁体 |
| en | 英文 |
| ja | 日语 |
| ko | 韩文 |
| th | 泰语 |
| vi | 越南语 |
| fr | 法语 |
| ru | 俄语|
| ar | 阿拉伯语 |
| de | 德语 |
| it | 意大利语 |
| he | 希伯来语 |
| hi | 印地语 |
| id | 印尼语 |
| my | 缅甸语 |
| lo | 老挝语 |
| ms | 马来语 |
| pl | 波兰语 |
| pt | 葡萄牙语 |
| es | 西班牙语 |
| tr | 土耳其语 |
| nl | 荷兰语 |
| es-la | 拉美西语 |
| pt-br | 巴西葡语 |
| sv | 瑞典语  |
| no | 挪威语  |
| da | 丹麦语  |
| cs | 捷克语  |
| hu | 匈牙利语  |
| sk | 斯洛伐克语  |
| ro | 罗马尼亚语  |
| el | 希腊语  |
| sr | 塞尔维亚语  |
| bs | 波斯尼亚语  |
| mk | 马其顿语  |
| bg | 保加利亚语  |
| fi | 芬兰语   |
| et | 爱沙尼亚语   |
| lv | 拉脱维亚语   |
| lt | 立陶宛语   |
| sl | 斯洛文尼亚语    |
| hr | 克罗地亚语    |
| uk | 乌克兰语   |
| vi | 越南语    |
| fa | 波斯语    |
| ca | 加泰罗尼亚语    |
| gl | 加利西亚语     |
| eu | 巴斯克语     |
| ka | 格鲁吉亚语     |
| az | 阿塞拜疆语     |
| uz | 乌兹别克语     |
| km | 高棉语     |
| si | 僧伽罗语     |
| ur | 乌尔都语     |
| bo | 藏语     |
| be | 白俄罗斯语     |
| kk | 哈萨克语（西里尔文）     |
| bn | 孟加拉语     |
| fil | 菲律宾语     |
| jv | 爪哇语     |
| ne | 尼泊尔语     |
| sw | 斯瓦西里语     |
| mi | 毛利语     |
| am | 阿姆哈拉语     |
| te | 泰卢固语     |
| mr | 马拉地语     |
| ta | 泰米尔语     |
| gu | 古吉拉特语     |
| kn | 卡纳达语     |
| ml | 马来亚拉姆语     |
| or | 欧里亚语     |
| pa | 旁遮普语     |
| as | 阿萨姆语     |
| mai | 迈蒂利语     |
| mn | 蒙古语（西里尔文）     |
| ug | 维吾尔语     |

###### styleConfig 验证码UI自定义配置对应表

| UI配置项 | 类型 | 描述 |
| ------ |----| ---- |
| radius |int |验证码圆角|
| capBarTextAlign |String |弹框头部标题文字对齐方式，可选值为 left center right|
| capBarBorderColor |String |弹框头部下边框颜色，想要去掉的话可取 transparent 或者与背景色同色 #fff|
| capBarTextColor |String |弹框头部标题文字颜色|
| capBarTextSize |int |弹框头部标题文字字体大小|
| capBarTextWeight |String | 弹框头部标题文字字体体重，可设置粗细，参考：capBarTextWeight: normal、bold、lighter、bolder、100、200、300、400、500、600、700、800、900更多详情参考：https://developer.mozilla.org/en-US/docs/Web/CSS/font-weight|
| capBarTitleHeight |int |弹框头部标题所在容器高度|
| capBodyPadding |int |验证码弹框 body 部分的内边距，相当于总体设置 capPaddingTop，capPaddingRight，capPaddingBottom，capPaddingLeft|
| capPaddingTop |int |验证码弹框 body 部分的【上】内边距，覆盖 capPadding 对于上内边距的设置,单位px|
| capPaddingRight |int |验证码弹框 body 部分的【右】内边距，覆盖 capPadding 对于右内边距的设置,单位px|
| capPaddingBottom |int |验证码弹框 body 部分的【底】内边距，覆盖 capPadding 对于底内边距的设置,单位px|
| capPaddingLeft |int |验证码弹框 body 部分的【左】内边距，覆盖 capPadding 对于左内边距的设置,单位px|
| paddingTop |int |弹框【上】内边距，实践时候可与 capPaddingTop 配合,单位px|
| paddingBottom |int |弹框【下】内边距，实践时候可与 capPaddingBottom 配合,单位px|
| capBorderRadius |int |验证码弹框body圆角|
| borderColor |String |滑块的边框颜色|
| background |String |滑块的背景颜色|
| borderColorMoving |String |滑块的滑动时边框颜色，滑动类型验证码下有效|
| backgroundMoving |String |滑块的滑动时背景颜色，滑动类型验证码下有效|
| borderColorSuccess |String |滑块的成功时边框颜色，此颜色同步了文字成功时文字颜色、滑块背景颜色|
| backgroundSuccess |String |滑块的成功时背景颜色|
| backgroundError |String |滑块的失败时背景颜色|
| borderColorError |String |失败时边框颜色|
| slideBackground |String |滑块的滑块背景颜色|
| textSize |int |滑块的内容文本大小|
| textColor |String |滑块内容文本颜色（滑块滑动前的颜色，失败、成功前的颜色）|
| height |int |滑块的高度|
| borderRadius |int |滑滑块的圆角|
| gap |String |滑块与验证码视图之间的距离,单位px|
| executeBorderRadius |int |组件圆角大小|
| executeBackground |String |组件背景色|
| executeTop |String |组件外层容器距离组件顶部距离,单位px|
| executeRight |String |组件外层容器距离组件右侧距离,单位px|

### 2. 显示验证码

#### 代码说明：
```
captchaHelper.showCaptcha()
```
### 3. 验证状态监听
使用的是 react-native 的 event 发送事件

首先导入 NativeEventEmitter 

```
import {NativeEventEmitter} from 'react-native'
const NTESRNRouterEmitter = new  NativeEventEmitter(NativeModules.NTESCaptchaHelper)
```

然后添加事件监听，总共三种事件

- 验证码弹窗准备完成 onLoaded

```
NTESRNRouterEmitter.addListener('onLoaded', (event) => {
    
});
```

- 验证成功回调 onSuccess

```
NTESRNRouterEmitter.addListener('onSuccess', (event) => {
      // validate：返回的结果码
      alert(event.validate);
      alert(event.result);
      alert(event.message);
});
```

- 失败回调 onError

```
NTESRNRouterEmitter.addListener('onError', (event) => {
      // code：错误码 message：错误信息
      alert(event.code);
      alert(event.message);
});
```

- 取消验证码回调 onCancel

```
NTESRNRouterEmitter.addListener('onClose', (event) => {
      //message：取消的具体场景
      alert(event.message);
});
```
