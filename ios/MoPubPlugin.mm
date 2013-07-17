#import "MoPubPlugin.h"

@implementation MoPubPlugin

// The plugin must call super dealloc.
- (void) dealloc {
	self.adView = nil;
	self.appDelegate = nil;

	[super dealloc];
}

// The plugin must call super init.
- (id) init {
	self = [super init];
	if (!self) {
		return nil;
	}

	self.adView = nil;
	self.appDelegate = nil;

	return self;
}

- (void) initializeWithManifest:(NSDictionary *)manifest appDelegate:(TeaLeafAppDelegate *)appDelegate {
	@try {
		NSDictionary *ios = [manifest valueForKey:@"ios"];
		NSString *moPubID = [ios valueForKey:@"moPubID"];
		
		NSLog(@"{moPub} Initializing with manifest moPubID: '%@'", moPubID);

		self.adView = [[[MPAdView alloc] initWithAdUnitId:moPubID size:MOPUB_BANNER_SIZE] autorelease];
		self.adView.delegate = self;

		CGRect frame = self.adView.frame;
		CGSize size = [self.adView adContentViewSize];

		frame.origin.y = [[UIScreen mainScreen] applicationFrame].size.height - size.height;

		self.adView.frame = frame;
	}
	@catch (NSException *exception) {
		NSLog(@"{moPub} Failure to get ios:moPubID key from manifest file: %@", exception);
	}
}

- (void) showInterstitial:(NSDictionary *)jsonObject {
	@try {
		NSLog(@"{moPub} Showing interstitial");

		[self.appDelegate.tealeafViewController.view addSubview:self.adView];
		[self.adView loadAd];
	}
	@catch (NSException *exception) {
		NSLog(@"{moPub} Failure during interstitial: %@", exception);
	}
}

@end

