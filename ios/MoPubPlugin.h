#import "PluginManager.h"
#import "MPAdView.h"

@interface MoPubPlugin : GCPlugin<MPAdViewDelegate>

@property (nonatomic, retain) MPAdView *adView;
@property (nonatomic, retain) TeaLeafAppDelegate *appDelegate;

@end

