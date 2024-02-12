# Introduction

This is the Dog Show app for Android, written in Kotlin.

The user interface is implemented with Jetpack Compose.

The app supports Android 7 or greater.

# Getting Started

You'll need the following tools installed:

- Android Studio, you can get it from the [official website](https://developer.android.com/studio).

## Structure

The hierarchy of this project is as follows:

- `app/src/main/java/com/rodrigocopetti/thedogshow`: Location of the app project, in there we find:
  - `/screens`: Implements screens using Jetpack Compose.
  - `/data`: Implements the network API client.
  - `/navigation`: Implements the navigation hierarchy of the screens.
  - `/ui`: Implements re-usable Jetpack Compose components.

## Dependencies

The project uses Gradle for organising dependencies. You can see the list of dependencies in `app/build.gradle` file.

# Build and Test

You can either build this project through Android Studio, or through Gradle:

`./gradlew assemble`