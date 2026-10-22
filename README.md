# Android Game Overlay Macro APK Project

This project compiles directly into a standalone Android APK that floats a programmable draggable HUD over any game or app on your phone.

## How It Works
- **SYSTEM_ALERT_WINDOW:** Allows the floating button to stay visible over any game.
- **AccessibilityService (dispatchGesture):** Injects hardware touches at exact (X, Y) pixel coordinates without needing root.

## 2-Minute Build Options:

### Option 1: GitHub Cloud Build (No Android Studio Needed!)
1. Create a free GitHub repository and push these files.
2. Go to the **Actions** tab in GitHub.
3. The workflow will automatically build your APK!
4. Download `app-debug.apk` from Artifacts and install on your phone.

### Option 2: Android Studio
1. Open Android Studio -> **Open** -> Select this project directory.
2. Click **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
3. Copy `app-debug.apk` to your Android phone and tap Install.

## First Launch Permissions
1. Grant **"Display over other apps"** in Android Settings.
2. Enable **"Game Overlay Macro"** under Accessibility Services.
3. Open any game and tap the floating trigger button!
