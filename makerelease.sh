#!/bin/bash
# A script to build Simple IRC
# Licensed under the GPLv3
# Get the version for the AndroidManifest
VERSION=$(cat application/src/main/AndroidManifest.xml | grep "versionName" | awk -v FS="(=|>)" '{print $2}' | tr -d '"')
# Set the gradle arguments from the makerelease command line arguments
GRADLEARGS="$@"

# If the output directory doesn't exist, make it.
if [ ! -e apks ]; then
mkdir -p apks
fi

# Build stuff here
echo "Building a debug build..."
./gradlew assembleNormalDebug $GRADLEARGS
echo "Copying the built apk to the output directory..."
cp -v application/build/outputs/apk/application-normal-debug.apk apks/SimpleIRC-$VERSION-debug.apk

echo "Building a fdroid debug build..."
./gradlew assemblefdroidDebug $GRADLEARGS
echo "Copying the built apk to the output directory..."
cp -v application/build/outputs/apk/application-fdroid-debug.apk apks/SimpleIRC-$VERSION-fdroid-debug.apk

if [ -e application/keystore ] || [ -e keystore.properties ]; then
echo "Building a release build..."
./gradlew assembleNormalRelease $GRADLEARGS
echo "Copying the built apk to the output directory..."
cp -v application/build/outputs/apk/application-normal-release.apk apks/SimpleIRC-$VERSION-release.apk
else
echo "Keystore not found, not building a release build..."
fi

if [ -e application/keystore ] || [ -e keystore.properties ]; then
echo "Building a fdroid release build..."
./gradlew assemblefdroidRelease $GRADLEARGS
echo "Copying the built apk to the output directory..."
cp -v application/build/outputs/apk/application-fdroid-release.apk apks/SimpleIRC-$VERSION-fdroid-release.apk
else
echo "Keystore not found, not building a fdroid release build..."
fi

