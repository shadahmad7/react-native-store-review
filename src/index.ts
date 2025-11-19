/**
 * React Native Store Review Module
 *
 * This file provides JS wrappers for the native TurboModule implementation.
 * All native calls are fully typed through the Spec defined in:
 *    ./specs/NativeReactNativeStoreReview.ts
 */

import NativeReactNativeStoreReview from './specs/NativeReactNativeStoreReview';

/**
 * Checks if the review dialog can be shown on this device/environment.
 * 
 * On iOS, this returns `false` when:
 * - The app is running via TestFlight
 * - The app is built directly from Xcode (sandbox receipt)
 * - The environment isn’t allowed to show the review prompt
 *
 * @returns Promise<boolean>
 */
export function isAvailableAsync(): Promise<boolean> {
  return NativeReactNativeStoreReview.isAvailableAsync();
}

/**
 * Requests the in-app review dialog.
 *
 * On iOS:
 * - Uses `AppStore.requestReview(in:)` on iOS 16+
 * - Falls back to `SKStoreReviewController.requestReview(in:)` on older versions
 * - Will reject if no active UIWindowScene is available
 *
 * @returns Promise<void>
 */
export function requestReview(): Promise<void> {
  return NativeReactNativeStoreReview.requestReview();
}

/**
 * Default export for convenience.
 * Allows:
 *    import StoreReview from '@shadahmad7/react-native-store-review';
 */
export default {
  isAvailableAsync,
  requestReview,
};
