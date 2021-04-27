
# 易盾验证码RN接入使用文档
在使用前请确保已申请到易盾验证码的业务id

## 导入插件
```
npm install --save https://github.com/yidun/captcha-react-native
react-native link react-native-captcha
```
也可以直接使用
```
npm install react-native-captcha
```

## 配置依赖(Android必须)
在react-native工程对应的android/app/build.gradle 文件的android域中添加
```
 repositories {
        flatDir {
            dirs project(':react-native-captcha').file('libs')
        }
    }
```
## 引入插件
```js
import {NativeModules} from 'react-native'
const captchaHelper = NativeModules.NTESCaptchaHelper;
```
## 验证码API说明

### init
```js
init({
    'captcha_id': '易盾获取到的id',
    'is_no_sense_mode': false, // 是否为智能无感知
    'language_type': 'zh-CN', // 多语言，默认中文
    'dimAmount': 1, // 验证码框遮罩层透明度，一般无需设置
    'is_touch_outside_disappear': false,//点击外部是否可以关闭验证码
    'timeout': 12000,//超时时间，单位毫秒
    'is_hide_close_button': false,//是否隐藏关闭按钮
    'use_default_fallback': true,//是否使用默认降级方案，默认开启
    'failed_max_retry_count': 3//当出现服务不可用时，尝试加载的最大次数，超过此次数仍然失败将触发降级，默认3次
},(result)=>{
    if (result.validate) {
        // TODO: 初始化成功
      } else {
        // TODO: 发生错误
      }
})
```
*方法描述：*
初始化
*多语言对应表：*
- zh-TW:中文繁体
- en:英文
- ja:日语
- ko:韩文
- th:泰语
- vi:越南语
- fr:法语
- ru:俄语
- ar:阿拉伯语
- de:德语
- it:意大利语
- he:希伯来语
- hi:印地语
- id:印尼语
- my:缅甸语
- lo:老挝语
- ms:马来语
- pl:波兰语
- pt:葡萄牙语
- es:西班牙语
- tr:土耳其语

### showCaptcha()
*方法描述：*
显示验证码弹窗

### 事件监听，使用的是react-native的event发送事件
*导入DeviceEventEmitter：*
```js
import {DeviceEventEmitter} from 'react-native'
```
总共三种事件
- 验证成功 onSuccess
```js
this.onSuccess = DeviceEventEmitter.addListener('onSuccess', (event) => {
			alert(event.validate);
		});
```
- 失败 onError
```js
this.onError = DeviceEventEmitter.addListener('onError', (event) => {
            alert(event.code);
			alert(event.message);
		});
```
- 取消 onCancel
```js
this.onCancel = DeviceEventEmitter.addListener('onCancel', (event) => {
			alert(event.message);
		});
```

