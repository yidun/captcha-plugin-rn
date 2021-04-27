
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
            [self.manager configureVerifyCode:captchaid timeout:timeout];
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
                } else {
                    self.manager.lang = NTESVerifyCodeLangCN;
                }
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

- (NSArray<NSString *> *)supportedEvents
{
  return @[@"verifyCodeInitFailed", @"verifyCodeValidateFinish", @"verifyCodeCloseWindow", @"verifyCodeNetError"];
}

/**
 * 验证码组件初始化出错
 *
 * @param message 错误信息
 */
- (void)verifyCodeInitFailed:(NSString *)message{
  NSLog(@"收到初始化失败的回调:%@",message);
  [self sendEventWithName:@"onError" body:@{@"message": message}];
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
  [self sendEventWithName:@"onSuccess" body:@{@"result": @(result), @"validate": validate, @"message": message}];
  dispatch_async(dispatch_get_main_queue(), ^(){
    
  });
}

/**
 * 关闭验证码窗口后的回调
 */
- (void)verifyCodeCloseWindow{
  NSLog(@"收到关闭验证码视图的回调");
  [self sendEventWithName:@"onCancel" body:@{@"message": @"关闭验证码视图"}];
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
  
