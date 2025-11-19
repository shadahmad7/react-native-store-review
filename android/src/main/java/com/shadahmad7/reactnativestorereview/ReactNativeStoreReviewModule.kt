package com.shadahmad7.reactnativestorereview

import android.app.Activity
import android.content.pm.PackageManager
import com.facebook.react.bridge.*
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewInfo

class ReactNativeStoreReviewModule(private val reactContext: ReactApplicationContext) :
  NativeReactNativeStoreReviewSpec(reactContext) {

  private val reactCtx: ReactApplicationContext get() = reactContext

  // ------------------- Availability -------------------
  @ReactMethod
  override fun isAvailableAsync(promise: Promise) {
    try {
      promise.resolve(isPlayStoreInstalled())
    } catch (e: Exception) {
      promise.reject("IS_AVAILABLE_ERROR", e)
    }
  }

  // ------------------- Request Review -------------------
  @ReactMethod
  override fun requestReview(promise: Promise) {
    val activity: Activity? = currentActivity
    if (activity == null) {
      promise.reject("ERR_NO_ACTIVITY", "No current activity available.")
      return
    }

    try {
      val manager: ReviewManager = ReviewManagerFactory.create(reactCtx)

      manager.requestReviewFlow().addOnCompleteListener { task ->
        if (!task.isSuccessful) {
          promise.reject("ERR_REVIEW_REQUEST", Exception("Failed to request review flow"))
          return@addOnCompleteListener
        }

        val reviewInfo = task.result

        manager.launchReviewFlow(activity, reviewInfo)
          .addOnCompleteListener { result ->
            if (result.isSuccessful) {
              promise.resolve(null)
            } else {
              promise.reject("ERR_REVIEW_FLOW", Exception("Failed to launch review flow"))
            }
          }
      }
    } catch (e: Exception) {
      promise.reject("REQUEST_REVIEW_ERROR", e)
    }
  }

  // ------------------- Helpers -------------------
  private fun isPlayStoreInstalled(): Boolean = try {
    reactCtx.packageManager.getPackageInfo("com.android.vending", 0)
    true
  } catch (_: Exception) {
    false
  }

  companion object {
    const val NAME = "ReactNativeStoreReview"
  }

  override fun getName(): String = NAME
}
