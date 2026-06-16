# PocketCode

PocketCode is a cross-platform mobile IDE experience built with Kotlin Multiplatform and Compose Multiplatform. It brings project browsing, code editing, and source control into a fast, touch-friendly interface for Android and iOS.

The goal is simple: make coding on mobile devices feel premium, focused, and genuinely enjoyable.

## What PocketCode Is

PocketCode is a mobile-first development workspace concept with:

- Project selection and quick re-entry into active work
- File tree navigation for source and config files
- Multi-tab editor workflows
- Built-in Git staging and commit flows
- Offline status awareness for pending pushes

## Who It Is Designed For

PocketCode is designed for:

- Developers who want to review, edit, and commit while away from a desktop setup
- Kotlin Multiplatform teams building for Android and iOS
- Indie makers and students who need lightweight, fast coding sessions
- Engineers who value a bold visual style and responsive UI feedback

## What It Is Designed For

PocketCode is optimized for short, high-value coding tasks on mobile:

- Reviewing open changes and making targeted edits
- Managing files and project structure quickly
- Staging and committing small batches of work
- Tracking offline progress and syncing later
- Maintaining coding momentum during travel, meetings, or downtime

## Design Philosophy

PocketCode breaks away from rigid, corporate IDE patterns. It uses an energetic dark-forward visual style with high contrast, clear hierarchy, and smooth micro-interactions.

- Primary: #FF6B00 (Electric Orange)
- Secondary: #1A1A1A (Deep Charcoal)
- Accent: #00C4FF (Neon Cyan)
- Background: #0D0D0D (Rich Black)

## Features

- Responsive layout optimized for Android and iOS
- File tree sidebar with animated expand and collapse
- Multi-tab editor shell for fast context switching
- Built-in Git panel for staging and committing
- Offline banner support for pending push status

## Tech Stack

- Kotlin Multiplatform (KMP)
- Compose Multiplatform
- Material 3
- Coroutines and Flow

## Getting Started

### Prerequisites

- Android Studio or IntelliJ IDEA
- Kotlin Multiplatform Mobile plugin
- JDK 17+

### Running the App

- Android: ./gradlew :composeApp:installDebug
- iOS: Run the shared Compose target from Android Studio or IntelliJ with KMP support.

## License

This project is licensed under the MIT License.
