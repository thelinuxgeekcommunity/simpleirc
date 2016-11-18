package tk.jordynsmediagroup.simpleirc.activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import tk.jordynsmediagroup.simpleirc.R;

// About activity
public class AboutActivity extends Activity {
  /**
   * On activity getting created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.about);
      try
      {
          // Set the context of the textview to the app VerionName
          TextView version = (TextView)findViewById(R.id.version_label);
          String app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
          version.setText("Version" + " " + app_ver);
      }
      catch (PackageManager.NameNotFoundException e)
      {
          // This should never happen
          Log.e("SimpleIRC/AboutActivity", e.getMessage());
      }
    TextView licenseDetails = (TextView)findViewById(R.id.about_license_info);
    licenseDetails.setText(Html.fromHtml(getString(R.string.licence_info)));


  }
}
