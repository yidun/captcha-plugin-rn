
package com.netease.captcha;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNCaptchaModule extends ReactContextBaseJavaModule {
    private CaptchaHelper captchaHelper = null;
    private final ReactApplicationContext reactContext;

    public RNCaptchaModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        captchaHelper = new CaptchaHelper();
    }

    @Override
    public String getName() {
        return "CaptchaHelper";
    }

    @ReactMethod
    public void init(String businessId) {
        captchaHelper.init(reactContext, businessId);
    }

    @ReactMethod
    public void showCaptcha(final Callback callback) {
        if (getCurrentActivity() != null) {
            getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    captchaHelper.show(callback);
                }
            });
        }
    }

    @ReactMethod
    public void showNoSenseCaptcha(final Callback callback) {
        if (getCurrentActivity() != null) {
            getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    captchaHelper.showNoSense(callback);
                }
            });
        }
    }
}