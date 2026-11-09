# Turab Android app

A private, offline-first Android streak companion with a home-screen widget.

## Build an installable APK using GitHub Actions (phone-friendly)
1. Create a new GitHub repository named `Turab`.
2. Upload the contents of this project ZIP to the repository root.
3. Open the repository's **Actions** tab.
4. Enable GitHub Actions if prompted.
5. Run the workflow named **Build Turab APK** (or push a commit).
6. Open the completed workflow run and download the `turab-debug-apk` artifact.
7. Extract the artifact and install `app-debug.apk` on your Android phone. If Android asks, allow installation from your browser/files app.

## Add the widget
After installing, long-press an empty area of your Android home screen, choose Widgets, find Turab, and drag its widget onto the home screen.

## Important behavior
- Calendar-day streak is calculated from the device's local date.
- Start Journey records today as Day 1.
- The day count advances automatically on subsequent calendar dates.
- Reset requires confirmation and preserves the longest streak.
- Data is stored locally in app-private SharedPreferences; there is no account or cloud sync.
- The widget updates on app refresh and Android date/time/timezone change broadcasts. Android launchers may delay background widget refreshes.

## Current prototype limitations
This is a functional first prototype, not a polished release. The mascot image is a static character illustration; full character animation, milestone-specific artwork, backup/export, and more extensive timezone-change safeguards can be added in a later iteration. The streak is a calendar counter based on the journey start date; a reset sets the journey inactive until Start Journey is pressed again.
