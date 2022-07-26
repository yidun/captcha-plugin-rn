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
    private ReactContext context;
    private CaptchaListener captchaListener;

    public void init(Activity activity, ReactContext context, ReadableMap map) {
        initListener();
        this.context = context;
        String captchaId = map.hasKey("captcha_id") ? map.getString("captcha_id") : "";
        boolean isNoSenseMode = map.hasKey("is_no_sense_mode") && map.getBoolean("is_no_sense_mode");
        int timeout = map.hasKey("timeout") ? map.getInt("timeout") : 1000 * 10;
        String languageStr = map.hasKey("language_type") ? map.getString("language_type") : "";
        boolean isDebug = map.hasKey("is_debug") && map.getBoolean("is_debug");
        float dimAmount = map.hasKey("dimAmount") ? (float) map.getDouble("dimAmount") : 0.5f;
        boolean isTouchOutsideDisappear = !map.hasKey("is_touch_outside_disappear") || map.getBoolean("is_touch_outside_disappear");
        boolean isHideCloseBtn = map.hasKey("is_hide_close_button") && map.getBoolean("is_hide_close_button");
        boolean useDefaultFallback = !map.hasKey("use_default_fallback") || map.getBoolean("use_default_fallback");
        int failedMaxRetryCount = map.hasKey("failed_max_retry_count") ? map.getInt("failed_max_retry_count") : 3;
        String theme = map.hasKey("theme") ? map.getString("theme") : "";
        String loadingText = map.hasKey("loading_text") ? map.getString("loading_text") : "";
        String apiServer = map.hasKey("api_server") ? map.getString("api_server") : "";
        String staticServer = map.hasKey("static_server") ? map.getString("static_server") : "";
        boolean isShowLoading = !map.hasKey("is_show_loading") || map.getBoolean("is_show_loading");
        boolean isCloseButtonBottom = map.hasKey("is_close_button_bottom") && map.getBoolean("is_close_button_bottom");
        ReadableMap styleConfig = map.hasKey("styleConfig") ? map.getMap("styleConfig") : null;

        CaptchaConfiguration.Builder builder = new CaptchaConfiguration.Builder();
        // 验证码业务id
        if (!TextUtils.isEmpty(captchaId)) {
            builder.captchaId(captchaId);
        } else {
            Log.e(TAG, "业务id不能为空");
            return;
        }
        builder.debug(isDebug);
        if (isNoSenseMode) {
            builder.mode(CaptchaConfiguration.ModeType.MODE_INTELLIGENT_NO_SENSE);
        }
        if (timeout != 0) {
            builder.timeout(timeout);
        }
        if (!TextUtils.isEmpty(languageStr)) {
            builder.languageType(StyleSettingTools.string2LangType(languageStr));
        }
        if (dimAmount != 0) {
            builder.backgroundDimAmount(dimAmount);
        }
        if (failedMaxRetryCount != 0) {
            builder.failedMaxRetryCount(failedMaxRetryCount);
        }
        builder.touchOutsideDisappear(isTouchOutsideDisappear);
        builder.useDefaultFallback(useDefaultFallback);
        builder.hideCloseButton(isHideCloseBtn);
        builder.isShowLoading(isShowLoading);
        builder.isCloseButtonBottom(isCloseButtonBottom);
        if (!TextUtils.isEmpty(theme)) {
            builder.theme(theme.equals("light") ? Theme.LIGHT : Theme.DARK);
        }
        if (!TextUtils.isEmpty(loadingText)) {
            builder.loadingText(loadingText);
        }
        if (!TextUtils.isEmpty(apiServer)) {
            builder.apiServer(apiServer);
        }
        if (!TextUtils.isEmpty(staticServer)) {
            builder.staticServer(staticServer);
        }
        builder.listener(captchaListener);
        if (styleConfig != null) {
            StyleSettingTools.setStyle(styleConfig, builder);
        }
        Captcha.getInstance().init(builder.build(activity));
    }

    public void showCaptcha() {
        Captcha.getInstance().validate();
    }

    public void destroy() {
        Captcha.getInstance().destroy();
    }

    private void initListener() {
        captchaListener = new CaptchaListener() {

            @Override
            public void onReady() {
                WritableMap event = Arguments.createMap();
                sendEvent("onLoaded", event);
            }

            @Override
            public void onValidate(String result, String validate, String msg) {
                WritableMap event = Arguments.createMap();
                event.putString("validate", validate);
                event.putString("result", result);
                event.putString("message", msg);
                sendEvent("onSuccess", event);
                Log.i(TAG, "验证结果:" + result);
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
                    event.putString("message", "manual");
                } else if (closeType == Captcha.CloseType.VERIFY_SUCCESS_CLOSE) {
                    event.putString("message", "auto");
                } else if (closeType == Captcha.CloseType.TIP_CLOSE) {
                    event.putString("message", "manual");
                }
                sendEvent("onClose", event);
            }
        };
    }

    private void sendEvent(String eventName, WritableMap event) {
        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(
                eventName, event);
    }
}
