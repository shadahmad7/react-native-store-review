package com.shadahmad7.reactnativestorereview

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import java.util.HashMap
import com.facebook.react.uimanager.ViewManager

/**
 * Package that registers the module and exposes module metadata so RN can
 * treat it as a TurboModule when new architecture is enabled.
 */
class ReactNativeStoreReviewPackage : BaseReactPackage() {
  override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
    return if (name == ReactNativeStoreReviewModule.NAME) {
      ReactNativeStoreReviewModule(reactContext)
    } else {
      null
    }
  }

  override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
    return ReactModuleInfoProvider {
      val moduleInfos: MutableMap<String, ReactModuleInfo> = HashMap()
      moduleInfos[ReactNativeStoreReviewModule.NAME] = ReactModuleInfo(
        ReactNativeStoreReviewModule.NAME,
        ReactNativeStoreReviewModule.NAME,
        false,  // canOverrideExistingModule
        false,  // needsEagerInit
        false,  // isCxxModule
        true    // isTurboModule
      )
      moduleInfos
    }
  }

  // No view managers needed
  override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
    return emptyList()
  }
}
