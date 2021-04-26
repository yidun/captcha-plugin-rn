package com.netease.captcha;

import android.text.TextUtils;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableNativeMap;
import com.netease.nis.captcha.Captcha;
import com.netease.nis.captcha.CaptchaConfiguration;
import com.netease.nis.captcha.CaptchaListener;

/**
 * Created by hzhuqi on 2018/8/21.
 */
public class CaptchaHelper {
    public static final String TAG = "RNCaptcha";
    private ReactContext mContext = null;
    private final float dimAmount = 0.5f;
    private final boolean isTouchOutsideDisappear = true;
    private final CaptchaConfiguration.LangType langType = CaptchaConfiguration.LangType.LANG_ZH_CN;
    private String businessId;

    public void init(ReactContext context, String businessId) {
        mContext = context;
        this.businessId = businessId;
    }

    public void show(final Callback callback) {
        if (TextUtils.isEmpty(businessId)) {
            return;
        }
        final CaptchaConfiguration configuration = new CaptchaConfiguration.Builder()
                .captchaId(businessId)
                .listener(new CaptchaListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onValidate(String result, String validate, String msg) {
                        if (!TextUtils.isEmpty(validate)) {
                            callback.invoke(true);
                        } else {
                            WritableNativeMap map = new WritableNativeMap();
                            map.putInt("code", -1001);
                            map.putString("msg", msg);
                            callback.invoke(false);
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                        WritableNativeMap map = new WritableNativeMap();
                        map.putInt("code", code);
                        map.putString("msg", msg);
                        callback.invoke(false);
                    }

                    @Override
                    public void onClose(Captcha.CloseType closeType) {
                        WritableNativeMap map = new WritableNativeMap();
                        if (closeType == Captcha.CloseType.USER_CLOSE) {
                            map.putString("msg", "用户关闭验证码");
                        } else if (closeType == Captcha.CloseType.VERIFY_SUCCESS_CLOSE) {
                            map.putString("msg", "校验通过，流程自动关闭");
                        } else if (closeType == Captcha.CloseType.TIP_CLOSE) {
                            map.putString("msg", "loading关闭");
                        }
                        map.putInt("code", -1000);
                        callback.invoke(false);
                    }
                })
                .languageType(langType)
                .debug(true)
                .position(-1, -1, 0, 0)
                .backgroundDimAmount(dimAmount)
                .touchOutsideDisappear(isTouchOutsideDisappear)
                .build(mContext);
        final Captcha captcha = Captcha.getInstance().init(configuration);
        captcha.validate();
    }


    public void showNoSense(final Callback callback) {
        if (TextUtils.isEmpty(businessId)) {
            return;
        }
        int failedMaxRetryCount = 3;
        final CaptchaConfiguration configuration = new CaptchaConfiguration.Builder()
                .captchaId(businessId)// 验证码业务id
                // 验证码类型，默认为传统验证码，如果要使用无感知请设置以下类型,否则请不要设置
                .mode(CaptchaConfiguration.ModeType.MODE_INTELLIGENT_NO_SENSE)
                .listener(new CaptchaListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onValidate(String result, String validate, String msg) {
                        if (!TextUtils.isEmpty(validate)) {
                            callback.invoke(true);
                        } else {
                            WritableNativeMap map = new WritableNativeMap();
                            map.putInt("code", -1001);
                            map.putString("msg", msg);
                            callback.invoke(false);
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                        WritableNativeMap map = new WritableNativeMap();
                        map.putInt("code", code);
                        map.putString("msg", msg);
                        callback.invoke(false);
                    }

                    @Override
                    public void onClose(Captcha.CloseType closeType) {
                        WritableNativeMap map = new WritableNativeMap();
                        if (closeType == Captcha.CloseType.USER_CLOSE) {
                            map.putString("msg", "用户关闭验证码");
                        } else if (closeType == Captcha.CloseType.VERIFY_SUCCESS_CLOSE) {
                            map.putString("msg", "校验通过，流程自动关闭");
                        } else if (closeType == Captcha.CloseType.TIP_CLOSE) {
                            map.putString("msg", "loading关闭");
                        }
                        map.putInt("code", -1000);
                        callback.invoke(false);
                    }
                }) // 验证码回调监听器
                .timeout(1000 * 10) // 超时时间，一般无需设置
                .languageType(langType) // 验证码语言类型，一般无需设置
                .debug(true) // 是否启用debug模式，一般无需设置
                // 设置验证码框的位置和宽度，一般无需设置，不推荐设置宽高，后面将逐步废弃该接口
                .position(-1, -1, 0, 0)
                .backgroundDimAmount(dimAmount) // 验证码框遮罩层透明度，一般无需设置
                .touchOutsideDisappear(isTouchOutsideDisappear)  // 点击验证码框外部是否消失，默认为系统默认配置(消失)，设置false不消失
                .useDefaultFallback(true) // 是否使用默认降级方案，默认开启
                .failedMaxRetryCount(failedMaxRetryCount) // 当出现服务不可用时，尝试加载的最大次数，超过此次数仍然失败将触发降级，默认3次
                .build(mContext);
        final Captcha captcha = Captcha.getInstance().init(configuration);
        captcha.validate();

    }
}
