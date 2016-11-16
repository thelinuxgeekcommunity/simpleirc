package tk.jordynsmediagroup.simpleirc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
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

    TextView licenseDetails = (TextView)findViewById(R.id.about_license_info);
    licenseDetails.setText(Html.fromHtml(getString(R.string.licence_info)));


  }
}
