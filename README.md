# AndroidDemoApps

To clone this repository on your local machine you need ```git```.
Use this command to clone the repository :
```
git clone https://github.com/devansh08/AndroidDemoApps.git
```
___
To build and generate apk for any project from command line :
* Go to the root directory of the project.
* Run the gradlew script.
  ```
  ./gradlew assembleDebug
  ```
  Note: This may cause gradle to download a few required files. Dont stop the download.
        Also make sure ```$ANDROID_HOME``` points to the correct location of Android SDK.
* If no errors have occured the APK file will be generated.
___
JAVA source files are located in :
```
AndroidDemoApps/<AppName>/app/src/androidTest/java/com/example/devansh/<appname>/
```
APK files after building the project will be in :
```
AndroidDemoApps/<AppName>/app/build/outputs/apk/
```
