package com.netease.captcha;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.netease.nis.captcha.Captcha;
import com.netease.nis.captcha.CaptchaConfiguration;
import com.netease.nis.captcha.CaptchaListener;

/**
 * Created by hzhuqi on 2018/8/21.
 */
public class CaptchaHelper {
    public static final String TAG = "RNCaptcha";
    private Activity mActivity = null;
    private ReactContext context;
    private float dimAmount = 0.5f;
    private boolean isTouchOutsideDisappear = false;
    private CaptchaConfiguration.LangType langType = CaptchaConfiguration.LangType.LANG_ZH_CN;
    private boolean isDebug = false;
    private String captcha_id;
    private boolean is_sense_mode;//是否智能无感知
    private boolean isHideCloseBtn = false;
    private boolean useDefaultFallback = false;
    private int failedMaxRetryCount = 3;
    private int timeout = 1000 * 10;

    public void init(Activity activity, ReactContext context, ReadableMap map, Callback callback) {
        this.mActivity = activity;
        this.context = context;
        this.captcha_id = map.hasKey("captcha_id") ? map.getString("captcha_id") : "";
        this.isDebug = map.hasKey("debug") && map.getBoolean("debug");
        this.is_sense_mode = map.hasKey("is_no_sense_mode") && map.getBoolean("is_no_sense_mode");
        this.dimAmount = map.hasKey("dimAmount") ? (float) map.getDouble("dimAmount") : 0.5f;
        this.isTouchOutsideDisappear = map.hasKey("is_touch_outside_disappear") && map.getBoolean("is_touch_outside_disappear");
        this.timeout = map.hasKey("timeout") ? map.getInt("timeout") : 1000 * 10;
        this.isHideCloseBtn = map.hasKey("is_hide_close_button") && map.getBoolean("is_hide_close_button");
        this.useDefaultFallback = map.hasKey("use_default_fallback") && map.getBoolean("use_default_fallback");
        this.failedMaxRetryCount = map.hasKey("failed_max_retry_count") ? map.getInt("failed_max_retry_count") : 3;
        if (map.hasKey("language_type") && !TextUtils.isEmpty("language_type")) {
            langType = string2LangType(map.getString("language_type"));
        }
        callback.invoke(true);
    }

    public void showCaptcha() {
        if (TextUtils.isEmpty(captcha_id)) {
            Log.d(TAG, "业务id不能为空");
            return;
        }
        final CaptchaConfiguration.Builder build = new CaptchaConfiguration.Builder()
                .captchaId(captcha_id)
                .listener(new CaptchaListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onValidate(String result, String validate, String msg) {
                        WritableMap event = Arguments.createMap();
                        if (!TextUtils.isEmpty(validate)) {
                            event.putString("validate", validate);
                            sendEvent("onSuccess", event);
                        } else {
                            event.putInt("code", -1001);
                            event.putString("message", msg);
                            sendEvent("onError", event);
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                        WritableMap event = Arguments.createMap();
                        event.putInt("code", code);
                        event.putString("message", msg);
                        sendEvent("onError", event);
                    }

                    @Override
                    public void onClose(Captcha.CloseType closeType) {
                        WritableMap event = Arguments.createMap();
                        if (closeType == Captcha.CloseType.USER_CLOSE) {
                            event.putString("message", "用户关闭验证码");
                        } else if (closeType == Captcha.CloseType.VERIFY_SUCCESS_CLOSE) {
                            event.putString("message", "校验通过，流程自动关闭");
                        } else if (closeType == Captcha.CloseType.TIP_CLOSE) {
                            event.putString("message", "loading关闭");
                        }
                        sendEvent("onCancel", event);
                    }
                })
                .languageType(langType)
                .debug(isDebug)
                .position(-1, -1, 0, 0)
                .timeout(timeout)
                .backgroundDimAmount(dimAmount)
                .failedMaxRetryCount(failedMaxRetryCount)
                .useDefaultFallback(useDefaultFallback)
                .hideCloseButton(isHideCloseBtn)
                .touchOutsideDisappear(isTouchOutsideDisappear);
        if (is_sense_mode) {
            build.mode(CaptchaConfiguration.ModeType.MODE_INTELLIGENT_NO_SENSE);
        }
        final Captcha captcha = Captcha.getInstance().init(build.build(mActivity));
        captcha.validate();
    }

    private CaptchaConfiguration.LangType string2LangType(String langTypeStr) {
        CaptchaConfiguration.LangType langType;
        switch (langTypeStr) {
            case "zh-TW": {
                langType = CaptchaConfiguration.LangType.LANG_ZH_TW;
            }
            break;
            case "en": {
                langType = CaptchaConfiguration.LangType.LANG_EN;
            }
            break;
            case "ja": {
                langType = CaptchaConfiguration.LangType.LANG_JA;
            }
            break;
            case "ko": {
                langType = CaptchaConfiguration.LangType.LANG_KO;
            }
            break;
            case "th": {
                langType = CaptchaConfiguration.LangType.LANG_TH;
            }
            break;
            case "vi": {
                langType = CaptchaConfiguration.LangType.LANG_VI;
            }
            break;
            case "fr": {
                langType = CaptchaConfiguration.LangType.LANG_FR;
            }
            break;
            case "ru": {
                langType = CaptchaConfiguration.LangType.LANG_RU;
            }
            break;
            case "ar": {
                langType = CaptchaConfiguration.LangType.LANG_AR;
            }
            break;
            case "de": {
                langType = CaptchaConfiguration.LangType.LANG_DE;
            }
            break;
            case "it": {
                langType = CaptchaConfiguration.LangType.LANG_IT;
            }
            break;
            case "he": {
                langType = CaptchaConfiguration.LangType.LANG_HE;
            }
            break;
            case "hi": {
                langType = CaptchaConfiguration.LangType.LANG_HI;
            }
            break;
            case "id": {
                langType = CaptchaConfiguration.LangType.LANG_ID;
            }
            break;
            case "my": {
                langType = CaptchaConfiguration.LangType.LANG_MY;
            }
            break;
            case "lo": {
                langType = CaptchaConfiguration.LangType.LANG_LO;
            }
            break;
            case "ms": {
                langType = CaptchaConfiguration.LangType.LANG_MS;
            }
            break;
            case "pl": {
                langType = CaptchaConfiguration.LangType.LANG_PL;
            }
            break;
            case "pt": {
                langType = CaptchaConfiguration.LangType.LANG_PT;
            }
            break;
            case "es": {
                langType = CaptchaConfiguration.LangType.LANG_ES;
            }
            break;
            case "tr": {
                langType = CaptchaConfiguration.LangType.LANG_TR;
            }
            break;
            default:
                langType = CaptchaConfiguration.LangType.LANG_ZH_CN;
                break;
        }
        return langType;
    }

    private void sendEvent(String eventName, WritableMap event) {
        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(
                eventName, event);
    }
}
