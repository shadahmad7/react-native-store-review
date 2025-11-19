// ReactNativeStoreReviewExceptions.swift

import Foundation

final class MissingCurrentWindowSceneException: NSError, @unchecked Sendable {
  static let error = MissingCurrentWindowSceneException(
    domain: "ReactNativeStoreReview",
    code: 1001,
    userInfo: [
      NSLocalizedDescriptionKey: "Unable to find the active UIWindowScene"
    ]
  )
}
