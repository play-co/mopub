package com.tealeaf.plugin.plugins;

import com.tealeaf.logger;
import com.tealeaf.plugin.IPlugin;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.BannerAdListener;
import com.mopub.mobileads.MoPubErrorCode;

public class MoPubPlugin implements IPlugin, BannerAdListener {
	private MoPubView _moPubView;
	private Context _ctx;

    @Override public void onBannerLoaded(MoPubView banner) { }
    @Override public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) { }
    @Override public void onBannerClicked(MoPubView banner) { }
    @Override public void onBannerExpanded(MoPubView banner) { }
    @Override public void onBannerCollapsed(MoPubView banner) { }

	public MoPubPlugin() {
	}

	public void onCreateApplication(Context applicationContext) {
		_ctx = applicationContext;
	}

	public void onCreate(Activity activity, Bundle savedInstanceState) {
		PackageManager manager = activity.getPackageManager();
		String moPubID = "";
		try {
			Bundle meta = manager.getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA).metaData;
			if (meta != null) {
				moPubID = meta.getString("MOPUB_ID");
			}

			logger.log("{moPub} Initializing from manifest with moPubID =", moPubID);

			int adViewId = _ctx.getResources().getIdentifier("adview", "id", _ctx.getPackageName());

			_moPubView = (MoPubView) activity.findViewById(adViewId);
			_moPubView.setAdUnitId(moPubID);
		} catch (Exception e) {
			android.util.Log.d("{moPub} EXCEPTION", e.getMessage().toString());
		}
	}

    public void showInterstitial(String json) {
        try {
            logger.log("{moPub} Showing interstitial");
			_moPubView.loadAd();
			_moPubView.setBannerAdListener(this);
        } catch (Exception e) {
            logger.log("{moPub} Failure while showing interstitial:", e.getMessage().toString());
        }
    }

	public void onResume() {
	}

	public void onStart() {
	}

	public void onPause() {
	}

	public void onStop() {
	}

	public void onDestroy() {
		_moPubView.destroy();
	}

	public void onNewIntent(Intent intent) {
	}

	public void setInstallReferrer(String referrer) {
	}

	public void onActivityResult(Integer request, Integer result, Intent data) {
	}

	public boolean consumeOnBackPressed() {
		return true;
	}

	public void onBackPressed() {
	}
}

