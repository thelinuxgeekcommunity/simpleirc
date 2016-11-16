package tk.jordynsmediagroup.simpleirc.model;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import tk.jordynsmediagroup.simpleirc.App;

public class ColorSchemeManager implements OnSharedPreferenceChangeListener {

  MessageRenderParams _cParams;
  Settings _settings;
  ColorScheme _currentColorScheme;

  public ColorSchemeManager() {
    _settings = App.getSettings();
    _cParams = _settings.getRenderParams();
    reloadScheme();
  }

  private void reloadScheme() {
    _currentColorScheme = new ColorScheme(_cParams.colorScheme, _cParams.useDarkScheme);

  }

  public ColorScheme getCurrentScheme() {
    return _currentColorScheme;
  }

  @Override
  public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
    if(!_cParams.equals(_settings.getRenderParams())) {
      _cParams = _settings.getRenderParams();
      reloadScheme();
    }
  }

}
