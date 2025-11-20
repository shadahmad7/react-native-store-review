// ReactNativeStoreReview.swift

import Foundation
import StoreKit
import UIKit

@objc(ReactNativeStoreReview)
class ReactNativeStoreReview: NSObject {

  // MARK: - isAvailableAsync

  @objc(isAvailableAsync:rejecter:)
  func isAvailableAsync(
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    resolve(!isRunningFromTestFlight())
  }

  // MARK: - requestReview

  @objc(requestReview:rejecter:)
  func requestReview(
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    // These are now explicitly escaping → no compiler error
    let safeResolve = resolve
    let safeReject = reject

    DispatchQueue.main.async { [weak self] in
      guard let self = self else { return }

      guard let scene = self.getForegroundActiveScene() else {
        safeReject(
          "ERR_NO_SCENE",
          MissingCurrentWindowSceneException.error.localizedDescription,
          MissingCurrentWindowSceneException.error
        )
        return
      }

      if #available(iOS 16.0, *) {
          AppStore.requestReview(in: scene)
      } else if #available(iOS 14.0, *) {
          SKStoreReviewController.requestReview(in: scene)
      } else {
          SKStoreReviewController.requestReview()
      }

      safeResolve(nil)
    }
  }

  // MARK: - Helpers

  private func getForegroundActiveScene() -> UIWindowScene? {
    return UIApplication.shared.connectedScenes
      .compactMap { $0 as? UIWindowScene }
      .first(where: { $0.activationState == .foregroundActive })
  }

  private func isRunningFromTestFlight() -> Bool {
    #if targetEnvironment(simulator)
    return false
    #endif

    guard let receipt = Bundle.main.appStoreReceiptURL?.lastPathComponent else {
      return false
    }

    let isSandbox = (receipt == "sandboxReceipt")
    return isSandbox && !hasEmbeddedMobileProvision()
  }

  private func hasEmbeddedMobileProvision() -> Bool {
    Bundle.main.path(forResource: "embedded", ofType: "mobileprovision") != nil
  }
}
