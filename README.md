## Getting Started

### Authenticating Your Client

Certain Google Play services (such as Google Sign-in and App Invites) require you to provide the SHA-1 of your signing certificate so we can create an OAuth2 client and API key for your app. To get your SHA-1, follow these instructions:

To get the debug certificate fingerprint:

Mac/Linux
```
keytool -exportcert -list -v \
-alias androiddebugkey -keystore ~/.android/debug.keystore
```

The keytool utility prompts you to enter a password for the keystore. The default password for the debug keystore is `android`.

The keytool then prints the fingerprint to the terminal. For example

```
Certificate fingerprint: SHA1: DA:39:A3:EE:5E:6B:4B:0D:32:55:BF:EF:95:60:18:90:AF:D8:07:09
```

### Adding your SHA-1 to the Firebase console.

Follow the link below to get to the timesheet firebase console

[Timesheet Firebase Console](https://console.firebase.google.com/u/0/project/timesheetsapp-4fc92/settings/general/android:rraya.nearsoft.com.timesheetsapp/ "Timesheet Firebase Console") 

Inside Settings -> General -> your apps

At the bottom of the page, under SHA certificate fingerprints, you can find the option ``ADD FINGERPRINT``.

Which a dialog will prompt you to add the certificate fingerprint, which you generated on the `Authenticating Your Client`.

### Importing the google-services.json to your Android project

Once you've completed the previous steps, You can download the `google-services.json` file by clicking on the `google-services.json` button.

You will want to add the json file inside your android project in the directory TimeSheetsApp/app/

 ### Reference
 - https://developers.google.com/android/guides/client-auth

 