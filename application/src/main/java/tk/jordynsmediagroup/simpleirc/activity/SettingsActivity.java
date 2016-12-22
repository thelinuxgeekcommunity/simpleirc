package tk.jordynsmediagroup.simpleirc.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import tk.jordynsmediagroup.simpleirc.R;

// Settings
public class SettingsActivity extends PreferenceActivity {
  // On create
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.preferences);
  }
}
