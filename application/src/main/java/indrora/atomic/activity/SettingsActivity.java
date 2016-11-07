package indrora.atomic.activity;

import indrora.atomic.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Settings
 *
 */
public class SettingsActivity extends PreferenceActivity {
  /**
   * On create
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.preferences);
  }
}
