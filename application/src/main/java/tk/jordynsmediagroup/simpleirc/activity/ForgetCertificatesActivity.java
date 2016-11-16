package tk.jordynsmediagroup.simpleirc.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import java.io.File;

import tk.jordynsmediagroup.simpleirc.R;

public class ForgetCertificatesActivity extends Activity {


  public ForgetCertificatesActivity() {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    Builder builder = new Builder(this);
    builder.setTitle(R.string.settings_forget_certificates);
    builder.setMessage(R.string.settings_forget_certificates_long);
    builder.setPositiveButton(android.R.string.yes, new OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        File dir = getApplicationContext().getDir(KEYSTORE_DIR, Context.MODE_PRIVATE);
        File keyStoreFile = new File(dir + File.separator + KEYSTORE_FILE);
        keyStoreFile.delete();
        finish();

      }
    });
    builder.setNegativeButton(android.R.string.no, new OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        finish();

      }
    });
    builder.create().show();


  }

  static String KEYSTORE_DIR = "KeyStore";
  static String KEYSTORE_FILE = "KeyStore.bks";


}
