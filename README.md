@shadahmad7/react-native-store-review

A lightweight, fully native In-App Store Review TurboModule for React Native CLI apps, supporting both iOS & Android. This module allows apps to request the system-native review popup, without redirecting users out of the app, and without depending on Expo.

⚡ Native Objective-C + Kotlin implementation
🔒 Safe: The system decides when to show the dialog
📦 Zero UI — always native look, no custom dialogs to maintain

Features

Fully native TurboModule (New Architecture ready)

Works on both iOS & Android

Doesn’t force users to leave the app

Uses official store APIs

iOS: SKStoreReviewController / AppStore.requestReview

Android: Google Play In-App Review API

Auto-handles system restrictions and availability

Safe: System decides if/when to show the dialog

Installation
Using npm
npm install @shadahmad7/react-native-store-review

Using Yarn
yarn add @shadahmad7/react-native-store-review

Usage
import StoreReview from "@shadahmad7/react-native-store-review";

async function askForReview() {
  const available = await StoreReview.isAvailableAsync();
  if (!available) return;

  await StoreReview.requestReview();
}


💡 Tip: Only call after meaningful user actions (e.g., purchase, level completion, booking successful, etc.)

Platform Notes
📱 iOS

Works only on real devices (not on Simulator).

iOS decides whether the dialog is shown.

Won’t show for:

TestFlight builds

Debug builds

Xcode installs

🤖 Android

Requires Google Play Store + Play Services.

May not show dialog on:

No Play Store (Huawei, AOSP phones)

APK sideloads

Debug builds

Too frequent requests

API
isAvailableAsync()

Checks whether the review dialog is allowed to be triggered on the device.

const canRequest = await StoreReview.isAvailableAsync();


Returns:

Promise<boolean>

requestReview()

Requests the native in-app review popup.

await StoreReview.requestReview();


Returns:

Promise<void>


⚠️ Note: The system may decide to show nothing, even if the request succeeds.

Best Practices

✔ Call only after positive user actions
✔ Do not show a custom prompt asking for rating
✔ Do not spam or force requests
✔ Call once after a user-defined checkpoint

FAQ

🔹 Can I detect if the dialog actually appeared?
No. Apple and Google intentionally hide that.

🔹 Can I customize how it looks?
No. It is 100% native.

🔹 What if I want to redirect users to the store page?
Use a fallback: open the app’s store page if unavailable.

Fallback Example
import { Linking, Platform } from "react-native";
import StoreReview from "@shadahmad7/react-native-store-review";

async function requestRating() {
  const ok = await StoreReview.isAvailableAsync();
  if (ok) return StoreReview.requestReview();

  // Fallback: open store page
  if (Platform.OS === "ios") {
    Linking.openURL("https://apps.apple.com/app/idYOUR_APP_ID");
  } else {
    Linking.openURL("https://play.google.com/store/apps/details?id=YOUR_PACKAGE_NAME");
  }
}

License

MIT © @shadahmad7