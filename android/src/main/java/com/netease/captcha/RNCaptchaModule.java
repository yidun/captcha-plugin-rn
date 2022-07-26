
package com.netease.captcha;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

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
        return "NTESCaptchaHelper";
    }

    @ReactMethod
    public void init(ReadableMap map) {
        captchaHelper.init(getCurrentActivity(), reactContext, map);
    }

    @ReactMethod
    public void showCaptcha() {
        if (getCurrentActivity() != null) {
            getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    captchaHelper.showCaptcha();
                }
            });
        }
    }

    @ReactMethod
    public void destroyCaptcha() {
        if (getCurrentActivity() != null) {
            getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    captchaHelper.destroy();
                }
            });
        }
    }
}