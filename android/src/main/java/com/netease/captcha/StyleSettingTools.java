package com.netease.captcha;

import android.text.TextUtils;

import com.facebook.react.bridge.ReadableMap;
import com.netease.nis.captcha.CaptchaConfiguration;
import com.netease.nis.captcha.CaptchaConfiguration.LangType;

/**
 * @author liuxiaoshuai
 * @date 2022/7/26
 * @desc
 * @email liulingfeng@mistong.com
 */
public class StyleSettingTools {
    public static CaptchaConfiguration.Builder setStyle(ReadableMap styleConfig, CaptchaConfiguration.Builder builder) {
        try {
            int radius = styleConfig.hasKey("radius") ? styleConfig.getInt("radius") : -1;
            String capBarTextAlign = styleConfig.hasKey("capBarTextAlign") ? styleConfig.getString("capBarTextAlign") : "";
            String capBarBorderColor = styleConfig.hasKey("capBarBorderColor") ? styleConfig.getString("capBarBorderColor") : "";
            String capBarTextColor = styleConfig.hasKey("capBarTextColor") ? styleConfig.getString("capBarTextColor") : "";
            int capBarTextSize = styleConfig.hasKey("capBarTextSize") ? styleConfig.getInt("capBarTextSize") : -1;
            String capBarTextWeight = styleConfig.hasKey("capBarTextWeight") ? styleConfig.getString("capBarTextWeight") : "";
            int capBarTitleHeight = styleConfig.hasKey("capBarTitleHeight") ? styleConfig.getInt("capBarTitleHeight") : -1;
            int capBodyPadding = styleConfig.hasKey("capBodyPadding") ? styleConfig.getInt("capBodyPadding") : -1;
            int capPaddingTop = styleConfig.hasKey("capPaddingTop") ? styleConfig.getInt("capPaddingTop") : -1;
            int capPaddingRight = styleConfig.hasKey("capPaddingRight") ? styleConfig.getInt("capPaddingRight") : -1;
            int capPaddingBottom = styleConfig.hasKey("capPaddingBottom") ? styleConfig.getInt("capPaddingBottom") : -1;
            int capPaddingLeft = styleConfig.hasKey("capPaddingLeft") ? styleConfig.getInt("capPaddingLeft") : -1;
            int paddingTop = styleConfig.hasKey("paddingTop") ? styleConfig.getInt("paddingTop") : -1;
            int paddingBottom = styleConfig.hasKey("paddingBottom") ? styleConfig.getInt("paddingBottom") : -1;
            int capBorderRadius = styleConfig.hasKey("capBorderRadius") ? styleConfig.getInt("capBorderRadius") : -1;
            String borderColor = styleConfig.hasKey("borderColor") ? styleConfig.getString("borderColor") : "";
            String background = styleConfig.hasKey("background") ? styleConfig.getString("background") : "";
            String borderColorMoving = styleConfig.hasKey("borderColorMoving") ? styleConfig.getString("borderColorMoving") : "";
            String backgroundMoving = styleConfig.hasKey("backgroundMoving") ? styleConfig.getString("backgroundMoving") : "";
            String borderColorSuccess = styleConfig.hasKey("borderColorSuccess") ? styleConfig.getString("borderColorSuccess") : "";
            String backgroundSuccess = styleConfig.hasKey("backgroundSuccess") ? styleConfig.getString("backgroundSuccess") : "";
            String backgroundError = styleConfig.hasKey("backgroundError") ? styleConfig.getString("backgroundError") : "";
            String borderColorError = styleConfig.hasKey("borderColorError") ? styleConfig.getString("borderColorError") : "";
            String slideBackground = styleConfig.hasKey("slideBackground") ? styleConfig.getString("slideBackground") : "";
            int textSize = styleConfig.hasKey("textSize") ? styleConfig.getInt("textSize") : -1;
            String textColor = styleConfig.hasKey("textColor") ? styleConfig.getString("textColor") : "";
            int height = styleConfig.hasKey("height") ? styleConfig.getInt("height") : -1;
            int borderRadius = styleConfig.hasKey("borderRadius") ? styleConfig.getInt("borderRadius") : -1;
            String gap = styleConfig.hasKey("gap") ? styleConfig.getString("gap") : "";
            int executeBorderRadius = styleConfig.hasKey("executeBorderRadius") ? styleConfig.getInt("executeBorderRadius") : -1;
            String executeBackground = styleConfig.hasKey("executeBackground") ? styleConfig.getString("executeBackground") : "";
            String executeTop = styleConfig.hasKey("executeTop") ? styleConfig.getString("executeTop") : "";
            String executeRight = styleConfig.hasKey("executeRight") ? styleConfig.getString("executeRight") : "";

            if (radius != -1) {
                builder.setRadius(radius);
            }
            if (TextUtils.isEmpty(capBarTextAlign)) {
                builder.setCapBarTextAlign(capBarTextAlign);
            }
            if (TextUtils.isEmpty(capBarBorderColor)) {
                builder.setCapBarBorderColor(capBarBorderColor);
            }
            if (TextUtils.isEmpty(capBarTextColor)) {
                builder.setCapBarTextColor(capBarTextColor);
            }
            if (capBarTextSize != -1) {
                builder.setCapBarTextSize(capBarTextSize);
            }
            if (TextUtils.isEmpty(capBarTextWeight)) {
                builder.setCapBarTextWeight(capBarTextWeight);
            }
            if (capBarTitleHeight != -1) {
                builder.setCapBarHeight(capBarTitleHeight);
            }
            if (capBodyPadding != -1) {
                builder.setCapPadding(capBodyPadding);
            }
            if (capPaddingTop != -1) {
                builder.setCapPaddingTop(capPaddingTop);
            }
            if (capPaddingRight != -1) {
                builder.setCapPaddingRight(capPaddingRight);
            }
            if (capPaddingBottom != -1) {
                builder.setCapPaddingBottom(capPaddingBottom);
            }
            if (capPaddingLeft != -1) {
                builder.setCapPaddingLeft(capPaddingLeft);
            }
            if (paddingTop != -1) {
                builder.setPaddingTop(paddingTop);
            }
            if (paddingBottom != -1) {
                builder.setPaddingBottom(paddingBottom);
            }
            if (capBorderRadius != -1) {
                builder.setImagePanelBorderRadius(capBorderRadius + "px");
            }
            if (TextUtils.isEmpty(borderColor)) {
                builder.setControlBarBorderColor(borderColor);
            }
            if (TextUtils.isEmpty(background)) {
                builder.setControlBarBackground(background);
            }
            if (TextUtils.isEmpty(borderColorMoving)) {
                builder.setControlBarBorderColorMoving(borderColorMoving);
            }
            if (TextUtils.isEmpty(backgroundMoving)) {
                builder.setControlBarBackgroundMoving(backgroundMoving);
            }
            if (TextUtils.isEmpty(borderColorSuccess)) {
                builder.setControlBarBorderColorSuccess(borderColorSuccess);
            }
            if (TextUtils.isEmpty(backgroundSuccess)) {
                builder.setControlBarBackgroundSuccess(backgroundSuccess);
            }
            if (TextUtils.isEmpty(backgroundError)) {
                builder.setControlBarBackgroundError(backgroundError);
            }
            if (TextUtils.isEmpty(borderColorError)) {
                builder.setControlBarBorderColorError(borderColorError);
            }
            if (TextUtils.isEmpty(slideBackground)) {
                builder.setControlBarSlideBackground(slideBackground);
            }
            if (textSize != -1) {
                builder.setControlBarTextSize(textSize + "px");
            }
            if (TextUtils.isEmpty(textColor)) {
                builder.setControlBarTextColor(textColor);
            }
            if (height != -1) {
                builder.setControlBarHeight(height + "px");
            }
            if (borderRadius != -1) {
                builder.setControlBarBorderRadius(borderRadius + "px");
            }
            if (TextUtils.isEmpty(gap)) {
                builder.setGap(gap);
            }
            if (executeBorderRadius != -1) {
                builder.setExecuteBorderRadius(executeBorderRadius + "px");
            }
            if (TextUtils.isEmpty(executeBackground)) {
                builder.setExecuteBackground(executeBackground);
            }
            if (TextUtils.isEmpty(executeTop)) {
                builder.setExecuteTop(executeTop);
            }
            if (TextUtils.isEmpty(executeRight)) {
                builder.setExecuteRight(executeRight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }

    public static CaptchaConfiguration.LangType string2LangType(String langTypeStr) {
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
}
