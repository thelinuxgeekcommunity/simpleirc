package tk.jordynsmediagroup.simpleirc;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import tk.jordynsmediagroup.simpleirc.model.ColorScheme;
import tk.jordynsmediagroup.simpleirc.model.ColorSchemeManager;
import tk.jordynsmediagroup.simpleirc.model.Settings;
import tk.jordynsmediagroup.simpleirc.utils.LatchingValue;

public class App extends Application {

  Atomic atomic;

  public App() {
    super();

    autoconnectComplete = new LatchingValue<Boolean>(true, false);
  }

  private static LatchingValue<Boolean> autoconnectComplete;

  private static Settings _s;

  private static Context _ctx;

  public static Context getAppContext() {
    return _ctx;
  }


    private static ColorSchemeManager _csMgr;
  public static ColorScheme getColorScheme() {
    return new ColorScheme(_s.getColorScheme(), _s.getUseDarkColors());
  }


  public static Settings getSettings() {
    if(_s == null) {
      _s = new Settings(getAppContext());
    }
    return _s;
  }

  public static Boolean doAutoconnect() {
    return autoconnectComplete.getValue();
  }

  private static Resources _r;

  public static Resources getSResources() {
    return _r;
  }


  @Override
  public void onCreate() {
    // Context exists here.

    _ctx = getApplicationContext();

    Atomic.getInstance().loadServers(_ctx);


    tk.jordynsmediagroup.simpleirc.model.Settings _settings = new Settings(this);
    _s = _settings;
    // Release 16 changes things for colors.
    // This is a much more elegant solution than I had here. Be glad.
    if( _s.getLastRunVersion() < 16 ) {
      _settings.setColorScheme("default");
    }

    _r = getResources();

    /*
    _csMgr = new ColorSchemeManager();

    PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(_csMgr);
*/


    if( _settings.getCurrentVersion() > _settings.getLastRunVersion() ) {
      Intent runIntent = new Intent(this, FirstRunActivity.class);
      runIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      this.startActivity(runIntent);
    }

    String ll = _settings.getDefaultNick();
    ll = ll.trim();

    super.onCreate();
  }
}
