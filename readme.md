# Template-Android

When starting a new Android project, it is boring to write some codes such as permission verification, network
interface creation, which are necessary for almost all projects.
<br>This template quickly solves such boring parts and allows you to devote time to tasks that are more important and
need to be paid attention to.

Korean description(한국어 설명) : [https://win-record.tistory.com/7](https://win-record.tistory.com/7)
<br/>
## Directory

![Template Directory](https://github.com/loggingWin/template-android/blob/main/directory.png?raw=ture)

|Name|Description|
|:---|:---|
|base|base classes such as base activity, fragment, dialog, etc|
|ㄴ[BaseActivity](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/base/BaseActivity.kt)|activity that all activities will inherit<br/>`showToast()` `showDialog()` `showProgressDialog()` `hideProgressDialog()`<br/>`transActivity()` `setToolbar()` `hideToolbar()` `getEditorActionListener()`
|network|about network task|
|ㄴ[ApiInterface](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/network/ApiInterface.kt)|retrofit api interface(samples included)|
|ㄴ[XAccessTokenInterceptor](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/network/XAccessTokenInterceptor.kt)|interceptor that put jwt into OkHttpClient from shared preferences|
|receiver|broadcast receivers|
|ㄴ[DownloadReceiver](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/receiver/DownloadReceiver.kt)|receive when download complete such as file, image, video, etc|
|ㄴ[RebootReceiver](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/receiver/RebootReceiver.kt)|receive when device reboots|
|util|useful utils|
|ㄴ[Enums](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/util/Enums.kt)|enum classes|
|ㄴ[ImageTask](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/util/ImageTask.kt)|tasks about image<br/>`createImageFile()` `getFullPathFromUri()` `downloadImage()`|
|ㄴ[NotificationTask](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/util/NotificationTask.kt)|task about notification<br/>`showNotification()`|
|ㄴ[Permission](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/util/Permission.kt)|tasks about permissions<br/>`checkCameraPermission()` `checkReadPermission()` <br/>`checkWritePermission()` `checkCamaraAndReadPermission()` <br/>`checkReadAndWritePermission()` `requestCameraPermission()` <br/>`requestReadPermission()` `requestCameraAndReadPermission()`<br>`requestReadAndWritePermission()`|
|ㄴ[ProgressDialog](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/util/ProgressDialog.kt)|loading dialog|
|ㄴ[ValueCheck](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/util/ValueCheck.kt)|check values with regex patterns<br/>`isEmail()` `getUrl()`
|ㄴ[ValueConvert](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/util/ValueConvert.kt)|convert value to another<br/>`dpToPx()` `pxToDp()`
|[App](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/App.kt)|application class|
|[MainActivity](https://github.com/loggingWin/template-android/blob/main/app/src/main/java/com/loggingwin/androidtemplate/MainActivity.kt)|main activity using view binding|

<br/>

packages(folders) that would be good to add
- adapter : adapter classes such as recyclerview adapter, fragment adapter, etc
- data : data classes such as XXXresponse, XXXbody, user, etc
- service : service classes
- view : view classes such as activity, fragment, etc (+ you can add each activity, fragment package into)
  <br/><br/>

## Gradle :app

### android

- min sdk version : 28<br/>
- target sdk version : 30<br/>
- using view binding

### dependencies

- [swipe refresh layout](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout)
- [Glide](https://github.com/bumptech/glide)
- [Retrofit2](https://github.com/square/retrofit)
- [Gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)
- [fragment](https://developer.android.com/jetpack/androidx/releases/fragment)

## License

```
MIT License

Copyright (c) 2021 loggingWin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
