package com.tiarsoft.tutorialAdmob.android;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.tiarsoft.tutorialAdmob.AdHandler;
import com.tiarsoft.tutorialAdmob.MyNewGame;

public class AndroidLauncher extends AndroidApplication implements AdHandler {

	String BANNER_ID = "xxxx";
	String INTERSTITIAL_ID = "xxxxxx";

	AdRequest requestAd;
	AdView bannerAd;
	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		// Con estas lineas de codigo hacemos que la aplicacion se muestre en pantalla completa
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		// Este framelayout va contener al banner y a la vista del juego
		FrameLayout layout = new FrameLayout(this);

		// Creamos un objeto de la clase MyNewGame y como parametro le pasamos el AdHandler
		MyNewGame game = new MyNewGame(this);

		// Creamos una vista para nuestro juego
		View gameView = initializeForView(game, config);

		requestAd = new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build();

		bannerAd = new AdView(this);
		bannerAd.setAdSize(AdSize.BANNER);
		bannerAd.setAdUnitId(BANNER_ID);
		bannerAd.loadAd(requestAd);

		// Agregamos las propiedass del banner para que este aparesca en la parte superior
		bannerAd.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT,
				Gravity.TOP));

		// Primero agregamos la vista del juego
		layout.addView(gameView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
		layout.addView(bannerAd);

		setContentView(layout);

		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId(INTERSTITIAL_ID);
		interstitial.loadAd(requestAd);

	}

	@Override
	public void showInterstitial() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (interstitial.isLoaded()) {
					interstitial.show();
				}
				interstitial.loadAd(requestAd);
			}
		});
	}
}
