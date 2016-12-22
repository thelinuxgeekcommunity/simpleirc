# simpleirc
A Simple open source irc client based off of Atmoic.

All code in the repository is licensed under the GPLv3

Supports the following:
* SSL
* SASL
* ZNC (I haven't tested this)
* DCC (Only supports send)
* Reciving CTCP FINGER, and VERSION
* Logging incomming and outgoing irc traffic
* Android API 16+ (Android 4.1 Jellybean+)
* Multi server and channel
* Away message
* AMSG support
* Notice support
* Whois support
* Colored Messages

The diffrences between Simple IRC and Atmoic:
* Logging support
* A black and white theme
* A grey theme
* Auto rejoin after kick
* Ported to API 16+ (Android 4.1 Jellybean)
* Some bug fixes and removed some redundant code
* What Simple IRC replys with when CTCP fingered
* Removed the messages that show when you connect to a server
* Added some more verbose logging
* Graphical emojis not enabled by default
* /CTCP support
* A notice when you get CTCP'ed
* A notice when you get a Malformed CTCP
* A option to keep the screen on while Simple IRC is in the foreground

# Prebuilt APK's
You can find prebuilt apk's in the directory "apks"

The apks with "fdroid" in the name are built just like the ones from fdroid execpt being signed with my key instead of theres

The apks with "debug" in the name are built with debugging symbols

# Help
To send a line to the server use /quote or /raw

The default quit message is "Simple IRC: The quit option."

The default log folder is /sdcard/SimpleIRC (You must create this if it doesn't exist or else it will not write to the log.)

