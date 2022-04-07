# 行为式验证码
全新人机验证方式，高效拦截机器行为，业务安全第一道防线。搭载风险感知引擎，智能切换验证难度，安全性高，极致用户体验。读屏软件深度适配，视障群体也可轻松使用，符合工信部无障碍适配要求

## 平台支持（兼容性）
  | Android|iOS|  
  | ---- | ----- |
  | 适用版本区间：4.4 - 13.0|适用版本区间：9 - 14| 

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
| debug | Boolean | 否 | false | 是否启动 debug 模式 |
| is_no_sense_mode | Boolean | 否 | false | 是否为智能无感知 |
| dimAmount | Number | 否 | 0.5 | 验证码框遮罩层透明度 |
| is_touch_outside_disappear | Boolean | 否 | true | 点击弹窗外部是否可以关闭验证码 |
| timeout | Number | 否 | 10000 | 超时时间 |
| is_hide_close_button | Boolean | 否 | false | 是否隐藏关闭按钮 |
| use_default_fallback | Boolean| 否 | true | 是否采用默认降级 |
| failed_max_retry_count | Number | 否 | 3 | 失败后尝试最大次数 |
| language_type | String | 否 | zh-CN | 多语言语言类型 |
| loading_text | String | 否 | 智能检测中 | 自定义加载文案 |

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

- 验证成功回调 onSuccess

```
NTESRNRouterEmitter.addListener('onSuccess', (event) => {
      // validate：返回的结果码
      alert(event.validate);
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
NTESRNRouterEmitter.addListener('onCancel', (event) => {
      //message：取消的具体场景
      alert(event.message);
});
```
