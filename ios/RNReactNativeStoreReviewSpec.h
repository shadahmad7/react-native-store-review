// RNReactNativeStoreReviewSpec.h

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>

@protocol NativeReactNativeStoreReviewSpec <NSObject>

- (void)isAvailableAsync:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject;

- (void)requestReview:(RCTPromiseResolveBlock)resolve
             rejecter:(RCTPromiseRejectBlock)reject;

@end

#ifdef RCT_NEW_ARCH_ENABLED
#import <React/RCTViewManager.h>
#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_REMAP_MODULE(ReactNativeStoreReview, ReactNativeStoreReview, NSObject)

RCT_EXTERN_METHOD(isAvailableAsync:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(requestReview:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)

@end
#endif
