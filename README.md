# CPSC 544 - HW2 - Stack App Development Notes

## Project Overview
This is a Stack application for Android that allows users to perform stack operations (push, pop) and manage the application lifecycle.

**Note:** This README documents only MY contributions to the group project.

---

## My Feature: Quit Button Functionality

### What I Did
Created a dedicated function to handle application exit with proper separation of concerns, following clean code principles.

### Where the Code is Located

**Main Implementation:**
- File: `app/src/main/java/com/example/cpsc544group9stackapp/MainActivity.kt`

**Quit Function:**
- Lines 77-81: `quitApplication()` private method
- Contains the logic to close the application

**Quit Button Click Listener:**
- Lines 62-65: Button click handler in `setupButtons()` method
- Calls the `quitApplication()` function when Quit button is pressed

**UI Component:**
- File: `app/src/main/res/layout/activity_main.xml`
- Lines 10-20: Quit button definition (btnQuit)

### How It Works

1. **User Interaction**:
   - User clicks the "Quit" button in the UI

2. **Event Handling**:
   - Click listener (line 63-65) detects the button press
   - Listener calls `quitApplication()` method

3. **Application Exit**:
   - `quitApplication()` method (line 79-81) executes
   - Calls `finish()` - Android's built-in method to close the current Activity
   - This terminates the MainActivity and removes it from the activity stack
   - App returns to home screen or previous app

4. **Clean Code Design**:
   - Separation of concerns: UI event handling separate from business logic
   - Dedicated function for quit operation allows for future enhancements
   - Easy to modify if we need to add cleanup operations before exit
   - Well-documented with comments explaining purpose

---

## My Feature: Input Validation (0-9 Only)

### What I Did
Implemented input validation to restrict stack entries to single-digit integers (0-9) only, providing user feedback for invalid entries.

### Where the Code is Located

**Main Implementation:**
- File: `app/src/main/java/com/example/cpsc544group9stackapp/MainActivity.kt`

**Validation Logic:**
- Lines 41-51: Range validation in push button click listener
- Checks if the input number is within the valid range (0-9)
- Displays appropriate error messages for out-of-range values

**Push Button Click Listener:**
- Lines 33-58: Complete push button handler in `setupButtons()` method
- Includes input validation, range checking, and error handling

### How It Works

1. **User Input**:
   - User enters a number in the input field
   - User clicks the "Push" button

2. **Input Validation**:
   - System checks if input is not empty (line 37)
   - Converts input to integer and catches NumberFormatException (lines 38-39)

3. **Range Validation** (New Feature):
   - System checks if number is between 0 and 9 using `if (number in 0..9)` (line 43)
   - If valid: pushes to stack, clears input, updates display, shows success message
   - If invalid: displays Toast message "Please enter a number between 0 and 9" (line 50)

4. **User Story**:
   - As a player, I want to be able to add only integers 0-9
   - This ensures the stack only contains single-digit values
   - Prevents data entry errors and maintains consistent stack content

5. **Error Handling**:
   - Empty input: "Please enter a number"
   - Invalid format: "Please enter a valid number"
   - Out of range: "Please enter a number between 0 and 9"

---

## My Feature: Stack Size Limit (Max 3 Items)

### What I Did
Implemented stack capacity validation to ensure the stack can hold a maximum of 3 integers at any time, preventing overflow and maintaining game constraints.

### Where the Code is Located

**Main Implementation:**
- File: `app/src/main/java/com/example/cpsc544group9stackapp/MainActivity.kt`

**Stack Size Validation Logic:**
- Lines 44-54: Stack capacity check in push button click listener
- Checks if stack size is less than 3 before allowing push operation
- Lines 46-50: Push operation executes only when stack has space
- Lines 52-53: Error message displayed when stack is full

**Push Button Click Listener:**
- Lines 33-64: Complete push button handler in `setupButtons()` method
- Includes nested validation: input validation → range checking → stack size checking

**Stack Declaration:**
- Line 15: `private val stack = Stack<Int>()` - The stack data structure

### How It Works

1. **User Input**:
   - User enters a number (0-9) in the input field
   - User clicks the "Push" button

2. **Validation Chain**:
   - System first validates input is not empty (line 37)
   - Converts input to integer (line 39)
   - Validates number is between 0-9 (line 43)

3. **Stack Capacity Check** (New Feature):
   - System checks if stack size is less than 3 using `if (stack.size < 3)` (line 46)
   - If stack has space: pushes number, clears input, updates display, shows success message (lines 47-50)
   - If stack is full: displays Toast message "Stack is full! Maximum 3 items allowed. Pop an item first." (line 53)

4. **User Story**:
   - As a player, I want the StackApp to hold only 3 integers max at a time
   - This enforces game rules and prevents unlimited stack growth
   - User must pop items before pushing new ones when stack is full

5. **Error Handling**:
   - Empty input: "Please enter a number"
   - Invalid format: "Please enter a valid number"
   - Out of range: "Please enter a number between 0 and 9"
   - **Stack full: "Stack is full! Maximum 3 items allowed. Pop an item first."**

6. **Integration with Pop Feature**:
   - Pop button allows users to remove items from full stack
   - After popping, users can push new items again
   - Maintains the 3-item limit throughout the app lifecycle

---

## Build Configuration Notes (Related to My Feature)

### Fixed Issues for Quit Button Development
1. **Java Version**: Configured project to use Java 17 (Java 25 not yet supported by Kotlin)
   - File: `gradle.properties:24-25`
   - Required for successful builds

---

## Development Environment
- Android Studio
- Gradle 8.13
- Kotlin 2.0.21
- Java: 17 (Microsoft OpenJDK 17.0.16)

---

## Git Workflow
- Feature branch: `feature/quit-button` (contains my quit functionality work)
- Feature branch: `feature/restrict-input-0-9` (contains input validation 0-9 work)
- Feature branch: `feature/max-3-stack-limit` (contains max 3 stack limit work)
- Remote: https://github.com/HieuNgoGH/CPSC-544---HW2--StackApp.git

---

## How to Run the App Manually

If the Android Studio run configuration is not working, follow these steps to manually build and run the app:

### Step 1: Start the Android Emulator
```bash
# List available emulators
~/Library/Android/sdk/emulator/emulator -list-avds

# Start your emulator (replace with your AVD name)
~/Library/Android/sdk/emulator/emulator -avd Pixel_2_API_35_version_1 &
```

**Or** start the emulator from Android Studio:
1. Go to **Tools** → **Device Manager**
2. Click the **Play** button next to your emulator device

### Step 2: Build the APK
From the project root directory, run:
```bash
./gradlew assembleDebug
```

This will create the APK at: `app/build/outputs/apk/debug/app-debug.apk`

### Step 3: Install the APK to the Emulator
```bash
# Wait for emulator to fully boot
~/Library/Android/sdk/platform-tools/adb wait-for-device

# Install the app
~/Library/Android/sdk/platform-tools/adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Step 4: Launch the App
```bash
~/Library/Android/sdk/platform-tools/adb shell am start -n com.example.cpsc544group9stackapp/.MainActivity
```

### All-in-One Command
Once the emulator is running, you can build, install, and launch with:
```bash
./gradlew assembleDebug && ~/Library/Android/sdk/platform-tools/adb install -r app/build/outputs/apk/debug/app-debug.apk && ~/Library/Android/sdk/platform-tools/adb shell am start -n com.example.cpsc544group9stackapp/.MainActivity
```

---

## Pull Request

**PR URL:** https://github.com/HieuNgoGH/CPSC-544---HW2--StackApp/pull/1

You can now:
1. View the PR in your browser at the URL above
2. Request reviews from team members
3. Merge the PR when ready by clicking "Merge pull request" on GitHub
