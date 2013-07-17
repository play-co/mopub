var MoPub = Class(function () {
	this.showInterstitial = function () {
		logger.log("{moPub} Showing interstitial");

		if (NATIVE && NATIVE.plugins && NATIVE.plugins.sendEvent) {
			NATIVE.plugins.sendEvent("MoPubPlugin", "showInterstitial",
				JSON.stringify({}));
		}
	};
});

exports = new MoPub();
