# **@shadahmad7/react-native-store-review**

A lightweight, fully-native **In-App Store Review TurboModule** for React Native CLI apps.

This module lets you trigger the **native rating popup** without sending users outside the app — and without using Expo.

⚡ **TurboModule + Native (Obj-C + Kotlin)**

📱 **iOS & Android Support**

🔒 **System-controlled & Safe**

🎯 **Zero UI Maintenance — Always native look**

---

## 🚀 Features

- Fully native TurboModule (supports **New Architecture**)
- Works on both **iOS & Android**
- **Does not redirect** users to the App Store/Play Store
- Uses **official native APIs**
    - iOS: `SKStoreReviewController`
    - Android: **Google Play In-App Review API**
- Automatic availability handling — shows **only when allowed**
- System decides when to display the dialog (no spam risk)

---

## 📦 Installation

```bash
npm install @shadahmad7/react-native-store-review

```

or

```bash
yarn add @shadahmad7/react-native-store-review

```

---

## 💻 Usage

```tsx
import StoreReview from "@shadahmad7/react-native-store-review";

async function askForReview() {
  const available = await StoreReview.isAvailableAsync();
  if (!available) return;

  await StoreReview.requestReview();
}

```

💡 **Tip:** Only call after meaningful user actions

(e.g., successful payment, booking, completed level, etc.)

---

## 📌 Platform Notes

### 📱 iOS

- Works only on **real devices**
- Apple decides whether to show the popup
- It may **not appear** on:
    - Debug builds
    - TestFlight builds
    - Xcode installed builds

### 🤖 Android

- Requires **Google Play Store + Play Services**
- Dialog may not show on:
    - Devices without Play Store (Huawei, AOSP)
    - Sideloaded APKs
    - Debug builds
    - If shown too frequently

---

## 📚 API

### `isAvailableAsync(): Promise<boolean>`

Checks if the device allows triggering the review dialog.

```tsx
const canRequest = await StoreReview.isAvailableAsync();

```

### `requestReview(): Promise<void>`

Requests the in-app review popup.

```tsx
await StoreReview.requestReview();

```

⚠️ **Note:** The system may choose not to show anything even if the promise resolves.

---

## 🎯 Best Practices

✔ Trigger only after **positive/milestone actions**

✔ Never show a custom popup asking for rating first

✔ **Do not spam calls**

✔ Call **once at defined checkpoints**

---

## ❓ FAQ

### 🔹 Can I detect if the popup actually appeared?

No. Apple and Google do not reveal that information.

### 🔹 Can I customize the UI?

No. The system controls the UI.

### 🔹 How do I open the store page manually?

Use a **fallback** when unavailable:

```tsx
import { Linking, Platform } from "react-native";
import StoreReview from "@shadahmad7/react-native-store-review";

async function requestRating() {
  const ok = await StoreReview.isAvailableAsync();
  if (ok) return StoreReview.requestReview();

  if (Platform.OS === "ios") {
    Linking.openURL("https://apps.apple.com/app/idYOUR_APP_ID");
  } else {
    Linking.openURL("https://play.google.com/store/apps/details?id=YOUR_PACKAGE_NAME");
  }
}

```

---

## 📄 License

**MIT © @shadahmad7**