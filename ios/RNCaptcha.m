
#import "RNCaptcha.h"
#import <VerifyCode/NTESVerifyCodeManager.h>

@interface RNCaptcha () <NTESVerifyCodeManagerDelegate>

@property (nonatomic, strong) NTESVerifyCodeManager *manager;

@end

@implementation RNCaptcha

RCT_EXPORT_MODULE(NTESCaptchaHelper);

RCT_EXPORT_METHOD(init:(NSDictionary *)options)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        self.manager = [NTESVerifyCodeManager getInstance];
        self.manager.delegate = self;
        
        NSString *captchaid;
        if ([options isKindOfClass:[NSDictionary class]]) {
            captchaid = [options objectForKey:@"captcha_id"];
            BOOL is_no_sense_mode = [[options objectForKey:@"is_no_sense_mode"] boolValue];
            BOOL debug = [[options objectForKey:@"debug"] boolValue];
            BOOL is_hide_close_button = [[options objectForKey:@"is_hide_close_button"] boolValue];
            BOOL is_touch_outside_disappear = [[options objectForKey:@"is_touch_outside_disappear"] boolValue];
            BOOL use_default_fallback = [[options objectForKey:@"use_default_fallback"] boolValue];
            int failed_max_retry_count = [[options objectForKey:@"failed_max_retry_count"] intValue];
            int timeout = [[options objectForKey:@"timeout"] intValue] / 1000;
            NSString *language_type = [options objectForKey:@"language_type"];
            float dimAmount = [[options objectForKey:@"dimAmount"] floatValue];
            if (is_no_sense_mode) {
                self.manager.mode = NTESVerifyCodeBind;
            } else {
                self.manager.mode = NTESVerifyCodeNormal;
            }
            [self.manager enableLog:debug];
            self.manager.fallBackCount = failed_max_retry_count;
            self.manager.openFallBack = use_default_fallback;
            self.manager.closeButtonHidden = is_hide_close_button;
            self.manager.shouldCloseByTouchBackground = is_touch_outside_disappear;
            
            NSDictionary *styleConfig = [options objectForKey:@"styleConfig"];
            if (styleConfig) {
                NTESVerifyCodeStyleConfig *config = [[NTESVerifyCodeStyleConfig alloc] init];
                config.radius = [[styleConfig objectForKey:@"radius"] intValue];
                
                NSString *capBarTextAlign = [styleConfig objectForKey:@"capBarTextAlign"];
                if ([@"left" isEqualToString:capBarTextAlign]) {
                    config.capBarTextAlign = NTESCapBarTextAlignLeft;
                } else if ([@"right" isEqualToString:capBarTextAlign]) {
                    config.capBarTextAlign = NTESCapBarTextAlignRight;
                } else {
                    config.capBarTextAlign = NTESCapBarTextAlignCenter;
                }
                config.capBarBorderColor = [styleConfig objectForKey:@"capBarBorderColor"];
                config.capBarTextColor = [styleConfig objectForKey:@"capBarTextColor"];
                config.capBarTextSize = [[styleConfig objectForKey:@"capBarTextSize"] intValue];
                config.capBarTextWeight = [styleConfig objectForKey:@"capBarTextWeight"];
                config.capBarTitleHeight = [[styleConfig objectForKey:@"capBarTitleHeight"] intValue];
                config.capBodyPadding = [[styleConfig objectForKey:@"capBodyPadding"] intValue];
                
                config.capPaddingTop = [NSString stringWithFormat:@"%@",[styleConfig objectForKey:@"capPaddingTop"]] ;
                config.capPaddingRight = [NSString stringWithFormat:@"%@",[styleConfig objectForKey:@"capPaddingRight"]] ;
                config.capPaddingBottom = [NSString stringWithFormat:@"%@",[styleConfig objectForKey:@"capPaddingBottom"]] ;
                config.capPaddingLeft = [NSString stringWithFormat:@"%@",[styleConfig objectForKey:@"capPaddingLeft"]];
                config.paddingTop = [NSString stringWithFormat:@"%@",[styleConfig objectForKey:@"paddingTop"]];
                config.paddingBottom = [NSString stringWithFormat:@"%@",[styleConfig objectForKey:@"paddingBottom"]];
                
                
                config.capBorderRadius = [[styleConfig objectForKey:@"capBorderRadius"] intValue];
                config.borderColor = [styleConfig objectForKey:@"borderColor"];
                config.background = [styleConfig objectForKey:@"background"];
                config.borderColorMoving = [styleConfig objectForKey:@"borderColorMoving"];
                config.backgroundMoving = [styleConfig objectForKey:@"backgroundMoving"];
                config.borderColorSuccess = [styleConfig objectForKey:@"borderColorSuccess"];
                config.backgroundSuccess = [styleConfig objectForKey:@"backgroundSuccess"];
                config.backgroundError = [styleConfig objectForKey:@"backgroundError"];
                config.borderColorError = [styleConfig objectForKey:@"borderColorError"];
                config.slideBackground = [styleConfig objectForKey:@"slideBackground"];
                config.textSize = [[styleConfig objectForKey:@"textSize"] intValue];
                config.textColor = [styleConfig objectForKey:@"textColor"];
                config.height = [[styleConfig objectForKey:@"height"] intValue];
                config.borderRadius = [[styleConfig objectForKey:@"borderRadius"] intValue];
                config.gap = [styleConfig objectForKey:@"gap"];
                
                config.executeBorderRadius = [[styleConfig objectForKey:@"executeBorderRadius"] intValue];
                config.executeBackground = [styleConfig objectForKey:@"executeBackground"];
                config.executeTop = [styleConfig objectForKey:@"executeTop"];
                config.executeRight = [styleConfig objectForKey:@"executeRight"];
                [self.manager configureVerifyCode:captchaid timeout:timeout styleConfig:config];
            } else {
                [self.manager configureVerifyCode:captchaid timeout:timeout];
            }
            if (dimAmount != 0.0) {
                self.manager.alpha = dimAmount;
            }
           
            if ([language_type isKindOfClass:[NSString class]]) {
                if ([language_type isEqualToString:@"zh-TW"]) {
                    self.manager.lang = NTESVerifyCodeLangTW;
                } else if ([language_type isEqualToString:@"en"]) {
                    self.manager.lang = NTESVerifyCodeLangEN;
                } else if ([language_type isEqualToString:@"ja"]) {
                    self.manager.lang = NTESVerifyCodeLangJP;
                } else if ([language_type isEqualToString:@"ko"]) {
                    self.manager.lang = NTESVerifyCodeLangKR;
                } else if ([language_type isEqualToString:@"th"]) {
                    self.manager.lang = NTESVerifyCodeLangTL;
                } else if ([language_type isEqualToString:@"vi"]) {
                    self.manager.lang = NTESVerifyCodeLangVT;
                } else if ([language_type isEqualToString:@"fr"]) {
                    self.manager.lang = NTESVerifyCodeLangFRA;
                } else if ([language_type isEqualToString:@"ru"]) {
                    self.manager.lang = NTESVerifyCodeLangRUS;
                } else if ([language_type isEqualToString:@"ar"]) {
                    self.manager.lang = NTESVerifyCodeLangKSA;
                } else if ([language_type isEqualToString:@"de"]) {
                    self.manager.lang = NTESVerifyCodeLangDE;
                } else if ([language_type isEqualToString:@"it"]) {
                    self.manager.lang = NTESVerifyCodeLangIT;
                } else if ([language_type isEqualToString:@"he"]) {
                    self.manager.lang = NTESVerifyCodeLangHE;
                } else if ([language_type isEqualToString:@"hi"]) {
                    self.manager.lang = NTESVerifyCodeLangHI;
                } else if ([language_type isEqualToString:@"id"]) {
                    self.manager.lang = NTESVerifyCodeLangID;
                } else if ([language_type isEqualToString:@"my"]) {
                    self.manager.lang = NTESVerifyCodeLangMY;
                } else if ([language_type isEqualToString:@"lo"]) {
                    self.manager.lang = NTESVerifyCodeLangLO;
                } else if ([language_type isEqualToString:@"ms"]) {
                    self.manager.lang = NTESVerifyCodeLangMS;
                } else if ([language_type isEqualToString:@"pl"]) {
                    self.manager.lang = NTESVerifyCodeLangPL;
                } else if ([language_type isEqualToString:@"pt"]) {
                    self.manager.lang = NTESVerifyCodeLangPT;
                } else if ([language_type isEqualToString:@"es"]) {
                    self.manager.lang = NTESVerifyCodeLangES;
                } else if ([language_type isEqualToString:@"tr"]) {
                    self.manager.lang = NTESVerifyCodeLangTR;
                } else if ([language_type isEqualToString:@"nl"]) {
                    self.manager.lang = NTESVerifyCodeLangNL;
                } else if ([language_type isEqualToString:@"es-la"]) {
                    self.manager.lang = NTESVerifyCodeLangESLA;
                } else if ([language_type isEqualToString:@"pt-br"]) {
                    self.manager.lang = NTESVerifyCodeLangPTBR;
                } else if ([language_type isEqualToString:@"sv"]) {
                    self.manager.lang = NTESVerifyCodeLangSV;
                } else if ([language_type isEqualToString:@"no"]) {
                    self.manager.lang = NTESVerifyCodeLangNN;
                } else if ([language_type isEqualToString:@"da"]) {
                    self.manager.lang = NTESVerifyCodeLangDA;
                } else if ([language_type isEqualToString:@"cs"]) {
                    self.manager.lang = NTESVerifyCodeLangCS;
                } else if ([language_type isEqualToString:@"hu"]) {
                    self.manager.lang = NTESVerifyCodeLangHU;
                } else if ([language_type isEqualToString:@"sk"]) {
                    self.manager.lang = NTESVerifyCodeLangSK;
                } else if ([language_type isEqualToString:@"ro"]) {
                    self.manager.lang = NTESVerifyCodeLangRO;
                } else if ([language_type isEqualToString:@"el"]) {
                    self.manager.lang = NTESVerifyCodeLangEL;
                } else if ([language_type isEqualToString:@"sr"]) {
                    self.manager.lang = NTESVerifyCodeLangSR;
                } else if ([language_type isEqualToString:@"bs"]) {
                    self.manager.lang = NTESVerifyCodeLangBS;
                } else if ([language_type isEqualToString:@"mk"]) {
                    self.manager.lang = NTESVerifyCodeLangMK;
                } else if ([language_type isEqualToString:@"bg"]) {
                    self.manager.lang = NTESVerifyCodeLangBG;
                } else if ([language_type isEqualToString:@"fi"]) {
                    self.manager.lang = NTESVerifyCodeLangFI;
                } else if ([language_type isEqualToString:@"et"]) {
                    self.manager.lang = NTESVerifyCodeLangET;
                } else if ([language_type isEqualToString:@"lv"]) {
                    self.manager.lang = NTESVerifyCodeLangLV;
                } else if ([language_type isEqualToString:@"lt"]) {
                    self.manager.lang = NTESVerifyCodeLangLT;
                } else if ([language_type isEqualToString:@"sl"]) {
                    self.manager.lang = NTESVerifyCodeLangSL;
                } else if ([language_type isEqualToString:@"hr"]) {
                    self.manager.lang = NTESVerifyCodeLangHR;
                } else if ([language_type isEqualToString:@"uk"]) {
                    self.manager.lang = NTESVerifyCodeLangUK;
                } else if ([language_type isEqualToString:@"vi"]) {
                    self.manager.lang = NTESVerifyCodeLangVT;
                } else if ([language_type isEqualToString:@"fa"]) {
                    self.manager.lang = NTESVerifyCodeLangFA;
                } else if ([language_type isEqualToString:@"ca"]) {
                    self.manager.lang = NTESVerifyCodeLangCA;
                } else if ([language_type isEqualToString:@"gl"]) {
                    self.manager.lang = NTESVerifyCodeLangGL;
                } else if ([language_type isEqualToString:@"eu"]) {
                    self.manager.lang = NTESVerifyCodeLangEU;
                } else if ([language_type isEqualToString:@"ka"]) {
                    self.manager.lang = NTESVerifyCodeLangKA;
                } else if ([language_type isEqualToString:@"az"]) {
                    self.manager.lang = NTESVerifyCodeLangAZ;
                } else if ([language_type isEqualToString:@"uz"]) {
                    self.manager.lang = NTESVerifyCodeLangUZ;
                } else if ([language_type isEqualToString:@"km"]) {
                    self.manager.lang = NTESVerifyCodeLangKM;
                } else if ([language_type isEqualToString:@"si"]) {
                    self.manager.lang = NTESVerifyCodeLangSI;
                } else if ([language_type isEqualToString:@"ur"]) {
                    self.manager.lang = NTESVerifyCodeLangUR;
                } else if ([language_type isEqualToString:@"bo"]) {
                    self.manager.lang = NTESVerifyCodeLangBO;
                } else if ([language_type isEqualToString:@"be"]) {
                    self.manager.lang = NTESVerifyCodeLangBE;
                } else if ([language_type isEqualToString:@"kk"]) {
                    self.manager.lang = NTESVerifyCodeLangKK;
                } else if ([language_type isEqualToString:@"bn"]) {
                    self.manager.lang = NTESVerifyCodeLangBN;
                } else if ([language_type isEqualToString:@"fil"]) {
                    self.manager.lang = NTESVerifyCodeLangFIL;
                } else if ([language_type isEqualToString:@"jv"]) {
                    self.manager.lang = NTESVerifyCodeLangJV;
                } else if ([language_type isEqualToString:@"ne"]) {
                    self.manager.lang = NTESVerifyCodeLangNE;
                } else if ([language_type isEqualToString:@"sw"]) {
                    self.manager.lang = NTESVerifyCodeLangSW;
                } else if ([language_type isEqualToString:@"mi"]) {
                    self.manager.lang = NTESVerifyCodeLangMI;
                } else if ([language_type isEqualToString:@"am"]) {
                    self.manager.lang = NTESVerifyCodeLangAM;
                } else if ([language_type isEqualToString:@"te"]) {
                    self.manager.lang = NTESVerifyCodeLangTE;
                } else if ([language_type isEqualToString:@"mr"]) {
                    self.manager.lang = NTESVerifyCodeLangMR;
                } else if ([language_type isEqualToString:@"ta"]) {
                    self.manager.lang = NTESVerifyCodeLangTA;
                } else if ([language_type isEqualToString:@"gu"]) {
                    self.manager.lang = NTESVerifyCodeLangGU;
                } else if ([language_type isEqualToString:@"kn"]) {
                    self.manager.lang = NTESVerifyCodeLangKN;
                } else if ([language_type isEqualToString:@"ml"]) {
                    self.manager.lang = NTESVerifyCodeLangML;
                } else if ([language_type isEqualToString:@"or"]) {
                    self.manager.lang = NTESVerifyCodeLangOR;
                } else if ([language_type isEqualToString:@"pa"]) {
                    self.manager.lang = NTESVerifyCodeLangPA;
                } else if ([language_type isEqualToString:@"as"]) {
                    self.manager.lang = NTESVerifyCodeLangAS;
                } else if ([language_type isEqualToString:@"mai"]) {
                    self.manager.lang = NTESVerifyCodeLangMAI;
                } else if ([language_type isEqualToString:@"mn"]) {
                    self.manager.lang = NTESVerifyCodeLangMN;
                } else if ([language_type isEqualToString:@"ug"]) {
                    self.manager.lang = NTESVerifyCodeLangUG;
                } else {
                    self.manager.lang = NTESVerifyCodeLangCN;
                }
            } else {
              self.manager.lang = NTESVerifyCodeLangCN;
            }
        }
         
         // 设置颜色
         self.manager.color = [UIColor blackColor];
         
         // 设置frame
         self.manager.frame = CGRectNull;
     });
  
}

RCT_EXPORT_METHOD(showCaptcha)
{
  dispatch_async(dispatch_get_main_queue(), ^{
      [self.manager openVerifyCodeView:nil];
  });
}

RCT_EXPORT_METHOD(destroyCaptcha)
{
  dispatch_async(dispatch_get_main_queue(), ^{
      [self.manager closeVerifyCodeView];
  });
}

- (NSArray<NSString *> *)supportedEvents
{
  return @[@"onLoaded", @"onError", @"onSuccess", @"onClose"];
}

/**
 * 验证码组件初始化完成
 */
- (void)verifyCodeInitFinish {
    [self sendEventWithName:@"onLoaded" body:@{}];
    dispatch_async(dispatch_get_main_queue(), ^(){
   
    });
}


/**
 * 验证码组件初始化出错
 *
 * @param message 错误信息
 */
- (void)verifyCodeInitFailed:(NSString *)message{
  NSLog(@"收到初始化失败的回调:%@",message);
  [self sendEventWithName:@"onError" body:@{@"message": message,@"code":@(0)}];
  dispatch_async(dispatch_get_main_queue(), ^(){
 
  });
}

/**
 * 完成验证之后的回调
 *
 * @param result 验证结果 BOOL:YES/NO
 * @param validate 二次校验数据，如果验证结果为false，validate返回空
 * @param message 结果描述信息
 *
 */
- (void)verifyCodeValidateFinish:(BOOL)result validate:(NSString *)validate message:(NSString *)message{
  NSLog(@"收到验证结果的回调:(%d,%@,%@)", result, validate, message);
    [self sendEventWithName:@"onSuccess" body:@{@"result": @(result), @"validate": validate, @"message": message?:@""}];
  dispatch_async(dispatch_get_main_queue(), ^(){
    
  });
}

/**
 * 关闭验证码窗口后的回调
 */
- (void)verifyCodeCloseWindow:(NTESVerifyCodeClose)close {
    NSString *msg = @"";
    if (close == NTESVerifyCodeCloseAuto) {
        msg = @"auto"
    } else {
        msg = @"manual"
    }
  NSLog(@"收到关闭验证码视图的回调");
  [self sendEventWithName:@"onClose" body:@{@"message": msg?:@""}];
  dispatch_async(dispatch_get_main_queue(), ^(){
    
  });
}

/**
 * 网络错误
 *
 * @param error 网络错误信息
 */
- (void)verifyCodeNetError:(NSError *)error{
  NSString *message = [error localizedDescription];
  NSLog(@"收到网络错误的回调:%@(%ld)", [error localizedDescription], (long)error.code);
  [self sendEventWithName:@"onError" body:@{@"message": message,@"code":@(error.code)}];
  dispatch_async(dispatch_get_main_queue(), ^(){
   
  });
}

@end
  

