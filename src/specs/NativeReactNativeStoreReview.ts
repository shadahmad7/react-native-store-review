/**
 * NativeReactNativeStoreReview TurboModule Spec
 *
 * This file defines the TypeScript interface that describes all native
 * functions exposed by the TurboModule. React Native uses this Spec to
 * automatically generate:
 *
 *  - iOS bridging code (ObjC++ / Swift bridge)
 *  - Android bridging code (Java / Kotlin bridge)
 *  - Type-safe JS ↔ Native communication
 *
 */

import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

/**
 * TurboModule native interface for store review functionality.
 *
 * Every native function exposed from Swift/Java must appear here
 * with the exact same name and parameter types.
 */
export interface Spec extends TurboModule {
  /**
   * Checks if the review dialog can be displayed.
   *
   * iOS returns:
   *  - true  → Review dialog allowed
   *  - false → TestFlight / sandbox / unavailable environment
   *
   * Android always returns true (if implemented).
   *
   * @returns Promise<boolean>
   */
  isAvailableAsync(): Promise<boolean>;

  /**
   * Requests the App Store / SKStore in-app review dialog.
   *
   * - On iOS 16+: uses `AppStore.requestReview(in:)`
   * - On iOS <16: uses `SKStoreReviewController.requestReview(in:)`
   * - Rejects if no active foreground UIWindowScene is found
   *
   * @returns Promise<void>
   */
  requestReview(): Promise<void>;
}

/**
 * Retrieve the native TurboModule instance.
 *
 * `getEnforcing` throws an error if the module is missing,
 * ensuring developers are alerted immediately during development.
 */
const NativeReactNativeStoreReview =
  TurboModuleRegistry.getEnforcing<Spec>('ReactNativeStoreReview');

export default NativeReactNativeStoreReview;
