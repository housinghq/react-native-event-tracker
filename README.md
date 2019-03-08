
# react-native-event-tracker

## Getting started

`$ npm install react-native-event-tracker --save`

### Mostly automatic installation

`$ react-native link react-native-event-tracker`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-event-tracker` and add `RNEventTracker.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNEventTracker.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.housing.tracker.RNEventTrackerPackage;` to the imports at the top of the file
  - Add `new RNEventTrackerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-event-tracker'
  	project(':react-native-event-tracker').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-event-tracker/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-event-tracker')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNEventTracker.sln` in `node_modules/react-native-event-tracker/windows/RNEventTracker.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Event.Tracker.RNEventTracker;` to the usings at the top of the file
  - Add `new RNEventTrackerPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNEventTracker from 'react-native-event-tracker';

// TODO: What to do with the module?
RNEventTracker;
```
  