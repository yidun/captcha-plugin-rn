package com.netease.captcha;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.netease.nis.captcha.Captcha;
import com.netease.nis.captcha.CaptchaConfiguration;
import com.netease.nis.captcha.CaptchaListener;
import com.netease.nis.captcha.CaptchaConfiguration.LangType;
import com.netease.nis.captcha.CaptchaConfiguration.Theme;

/**
 * Created by hzhuqi on 2018/8/21.
 */
public class CaptchaHelper {
    public static final String TAG = "RNCaptcha";
    private Activity mActivity = null;
    private ReactContext context;
    private float dimAmount = 0.5f;
    private boolean isTouchOutsideDisappear = true;
    private String langType;
    private Theme theme = Theme.LIGHT;
    private boolean isDebug = false;
    private String captcha_id;
    private boolean isNoSenseMode = false;//是否智能无感知
    private boolean isHideCloseBtn = false;
    private boolean useDefaultFallback = true;
    private int failedMaxRetryCount = 3;
    private int timeout = 1000 * 10;
    private String loadingText;
    private String apiServer, staticServer;

    public void init(Activity activity, ReactContext context, ReadableMap map) {
        this.mActivity = activity;
        this.context = context;
        this.captcha_id = map.hasKey("captcha_id") ? map.getString("captcha_id") : "";
        this.isDebug = map.hasKey("debug") && map.getBoolean("debug");
        this.isNoSenseMode = map.hasKey("is_no_sense_mode") && map.getBoolean("is_no_sense_mode");
        this.dimAmount = map.hasKey("dimAmount") ? (float) map.getDouble("dimAmount") : 0.5f;
        this.isTouchOutsideDisappear = !map.hasKey("is_touch_outside_disappear") || map.getBoolean("is_touch_outside_disappear");
        this.timeout = map.hasKey("timeout") ? map.getInt("timeout") : 1000 * 10;
        this.isHideCloseBtn = map.hasKey("is_hide_close_button") && map.getBoolean("is_hide_close_button");
        this.useDefaultFallback = !map.hasKey("use_default_fallback") || map.getBoolean("use_default_fallback");
        this.failedMaxRetryCount = map.hasKey("failed_max_retry_count") ? map.getInt("failed_max_retry_count") : 3;
        if (map.hasKey("language_type") && !TextUtils.isEmpty(map.getString("language_type"))) {
            this.langType = map.getString("language_type");
        }
        if (map.hasKey("theme") && !TextUtils.isEmpty(map.getString("theme"))) {
            this.theme = map.getString("theme").equals("light") ? Theme.LIGHT : Theme.DARK;
        }
        if (map.hasKey("loading_text") && !TextUtils.isEmpty(map.getString("loading_text"))) {
            this.loadingText = map.getString("loading_text");
        }
        if (map.hasKey("api_server") && !TextUtils.isEmpty(map.getString("api_server"))) {
            this.apiServer = map.getString("api_server");
        }
        if (map.hasKey("static_server") && !TextUtils.isEmpty(map.getString("static_server"))) {
            this.staticServer = map.getString("static_server");
        }
    }

    public void showCaptcha() {
        if (TextUtils.isEmpty(captcha_id)) {
            Log.e(TAG, "业务id不能为空");
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
                            Log.d(TAG, "验证失败:" + msg);
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
                .debug(isDebug)
                .timeout(timeout)
                .backgroundDimAmount(dimAmount)
                .failedMaxRetryCount(failedMaxRetryCount)
                .useDefaultFallback(useDefaultFallback)
                .hideCloseButton(isHideCloseBtn)
                .touchOutsideDisappear(isTouchOutsideDisappear);
        if (!TextUtils.isEmpty(langType)) {
            build.languageType(string2LangType(langType));
        }
        if (isNoSenseMode) {
            build.mode(CaptchaConfiguration.ModeType.MODE_INTELLIGENT_NO_SENSE);
        }
        if (!TextUtils.isEmpty(loadingText)) {
            build.loadingText(loadingText);
        }
        if (!TextUtils.isEmpty(apiServer)) {
            build.apiServer(apiServer);
        }
        if (!TextUtils.isEmpty(staticServer)) {
            build.staticServer(staticServer);
        }
        build.theme(theme);
        final Captcha captcha = Captcha.getInstance().init(build.build(mActivity));
        captcha.validate();
    }

    private CaptchaConfiguration.LangType string2LangType(String langTypeStr) {
        CaptchaConfiguration.LangType langType;
        switch (langTypeStr) {
            case "am": {
                langType = LangType.LANG_AM;
            }
            break;
            case "ar": {
                langType = LangType.LANG_AR;
            }
            break;
            case "as": {
                langType = LangType.LANG_AS;
            }
            break;
            case "az": {
                langType = LangType.LANG_AZ;
            }
            break;
            case "be": {
                langType = LangType.LANG_BE;
            }
            break;
            case "bg": {
                langType = LangType.LANG_BG;
            }
            break;
            case "bn": {
                langType = LangType.LANG_BN;
            }
            break;
            case "bo": {
                langType = LangType.LANG_BO;
            }
            break;
            case "bs": {
                langType = LangType.LANG_BS;
            }
            break;
            case "ca": {
                langType = LangType.LANG_CA;
            }
            break;
            case "cs": {
                langType = LangType.LANG_CS;
            }
            break;
            case "da": {
                langType = LangType.LANG_DA;
            }
            break;
            case "de": {
                langType = LangType.LANG_DE;
            }
            break;
            case "el": {
                langType = LangType.LANG_EL;
            }
            break;
            case "en":
            case "en-GB": {
                langType = LangType.LANG_EN;
            }
            break;
            case "en-US": {
                langType = LangType.LANG_EN_US;
            }
            break;
            case "es": {
                langType = LangType.LANG_ES;
            }
            break;
            case "es-la": {
                langType = LangType.LANG_ES_LA;
            }
            break;
            case "et": {
                langType = LangType.LANG_ET;
            }
            break;
            case "eu": {
                langType = LangType.LANG_EU;
            }
            break;
            case "fa": {
                langType = LangType.LANG_FA;
            }
            break;
            case "fi": {
                langType = LangType.LANG_FI;
            }
            break;
            case "fr": {
                langType = LangType.LANG_FR;
            }
            break;
            case "gl": {
                langType = LangType.LANG_GL;
            }
            break;
            case "gu": {
                langType = LangType.LANG_GU;
            }
            break;
            case "hi": {
                langType = LangType.LANG_HI;
            }
            break;
            case "hr": {
                langType = LangType.LANG_HR;
            }
            break;
            case "hu": {
                langType = LangType.LANG_HU;
            }
            break;
            case "id": {
                langType = LangType.LANG_ID;
            }
            break;
            case "it": {
                langType = LangType.LANG_IT;
            }
            break;
            case "he": {
                langType = LangType.LANG_HE;
            }
            break;
            case "ja": {
                langType = LangType.LANG_JA;
            }
            break;
            case "jv": {
                langType = LangType.LANG_JV;
            }
            break;
            case "ka": {
                langType = LangType.LANG_KA;
            }
            break;
            case "kk": {
                langType = LangType.LANG_KK;
            }
            break;
            case "km": {
                langType = LangType.LANG_KM;
            }
            break;
            case "kn": {
                langType = LangType.LANG_KN;
            }
            break;
            case "ko": {
                langType = LangType.LANG_KO;
            }
            break;
            case "lo": {
                langType = LangType.LANG_LO;
            }
            break;
            case "lt": {
                langType = LangType.LANG_LT;
            }
            break;
            case "lv": {
                langType = LangType.LANG_LV;
            }
            break;
            case "mai": {
                langType = LangType.LANG_MAI;
            }
            break;
            case "mi": {
                langType = LangType.LANG_MI;
            }
            break;
            case "mk": {
                langType = LangType.LANG_MK;
            }
            break;
            case "ml": {
                langType = LangType.LANG_ML;
            }
            break;
            case "mn": {
                langType = LangType.LANG_MN;
            }
            break;
            case "mr": {
                langType = LangType.LANG_MR;
            }
            break;
            case "ms": {
                langType = LangType.LANG_MS;
            }
            break;
            case "my": {
                langType = LangType.LANG_MY;
            }
            break;
            case "no": {
                langType = LangType.LANG_NO;
            }
            break;
            case "ne": {
                langType = LangType.LANG_NE;
            }
            break;
            case "nl": {
                langType = LangType.LANG_NL;
            }
            break;
            case "or": {
                langType = LangType.LANG_OR;
            }
            break;
            case "pa": {
                langType = LangType.LANG_PA;
            }
            break;
            case "pl": {
                langType = LangType.LANG_PL;
            }
            break;
            case "pt": {
                langType = LangType.LANG_PT;
            }
            break;
            case "pt-br": {
                langType = LangType.LANG_PT_BR;
            }
            break;
            case "ro": {
                langType = LangType.LANG_RO;
            }
            break;
            case "ru": {
                langType = LangType.LANG_RU;
            }
            break;
            case "si": {
                langType = LangType.LANG_SI;
            }
            break;
            case "sk": {
                langType = LangType.LANG_SK;
            }
            break;
            case "sl": {
                langType = LangType.LANG_SL;
            }
            break;
            case "sr": {
                langType = LangType.LANG_SR;
            }
            break;
            case "sv": {
                langType = LangType.LANG_SV;
            }
            break;
            case "sw": {
                langType = LangType.LANG_SW;
            }
            break;
            case "ta": {
                langType = LangType.LANG_TA;
            }
            break;
            case "te": {
                langType = LangType.LANG_TE;
            }
            break;
            case "th": {
                langType = LangType.LANG_TH;
            }
            break;
            case "fil": {
                langType = LangType.LANG_FIL;
            }
            break;
            case "tr": {
                langType = LangType.LANG_TR;
            }
            break;
            case "ug": {
                langType = LangType.LANG_UG;
            }
            break;
            case "uk": {
                langType = LangType.LANG_UK;
            }
            break;
            case "ur": {
                langType = LangType.LANG_UR;
            }
            break;
            case "uz": {
                langType = LangType.LANG_UZ;
            }
            break;
            case "vi": {
                langType = LangType.LANG_VI;
            }
            break;
            case "zh-HK": {
                langType = LangType.LANG_ZH_HK;
            }
            break;
            case "zh-TW": {
                langType = LangType.LANG_ZH_TW;
            }
            break;
            default: {
                langType = LangType.LANG_ZH_CN;
            }
        }
        return langType;
    }

    private void sendEvent(String eventName, WritableMap event) {
        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(
                eventName, event);
    }
}
