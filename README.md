
# react-native-captcha

## Getting started

`$ npm install react-native-captcha --save`

### Mostly automatic installation

`$ react-native link react-native-captcha`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-captcha` and add `RNCaptcha.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNCaptcha.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.netease.captcha.RNCaptchaPackage;` to the imports at the top of the file
  - Add `new RNCaptchaPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-captcha'
  	project(':react-native-captcha').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-captcha/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-captcha')
  	```


## Usage
```javascript
import RNCaptcha from 'react-native-captcha';

// TODO: What to do with the module?
RNCaptcha;
```
  