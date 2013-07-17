# Game Closure DevKit Plugin: MoPub

This plugin allows you to collect analytics using the [MoPub](https://mopub.com/) toolkit.  Both iOS and Android targets are supported.

## Usage

Install the addon with `basil install mopub`.

Include it in the `manifest.json` file under the "addons" section for your game:

~~~
"addons": [
	"mopub"
],
~~~

To specify your game's AppID and AppSignature, edit the `manifest.json "android" and "ios" sections as shown below:

~~~
	"android": {
		"versionCode": 1,
		"icons": {
			"36": "resources/icons/android36.png",
			"48": "resources/icons/android48.png",
			"72": "resources/icons/android72.png",
			"96": "resources/icons/android96.png"
		},
		"moPubAdUnitID": "7c5a4b7ffb404727987f55be6ecb468b"
	},
~~~

~~~
	"ios": {
		"bundleID": "mmp",
		"appleID": "568975017",
		"version": "1.0.3",
		"icons": {
			"57": "resources/images/promo/icon57.png",
			"72": "resources/images/promo/icon72.png",
			"114": "resources/images/promo/icon114.png",
			"144": "resources/images/promo/icon144.png"
		},
		"moPubAdUnitID": "7c5a4b7ffb404727987f55be6ecb468b"
	},
~~~

Note that the manifest keys are case-sensitive.

Then you can edit your game JavaScript code to import the MoPub object:

~~~
import plugins.mopub.moPub as moPub;
~~~

And use the `showInterstitial` method to show an ad:

~~~
moPub.showInterstitial();
~~~
