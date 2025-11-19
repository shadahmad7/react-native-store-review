// RNReactNativeStoreReview.m

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(ReactNativeStoreReview, NSObject)

RCT_EXTERN_METHOD(isAvailableAsync:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(requestReview:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)

@end
