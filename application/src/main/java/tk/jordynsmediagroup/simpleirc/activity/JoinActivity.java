package tk.jordynsmediagroup.simpleirc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import tk.jordynsmediagroup.simpleirc.R;

/**
 * Small dialog to show an edittext for joining channels
 */
public class JoinActivity extends Activity implements OnClickListener {
  /**
   * On create
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.join);
    ((Button)findViewById(R.id.join)).setOnClickListener(this);
    ((EditText)findViewById(R.id.channel)).setSelection(1);
  }

  // On click
  @Override
  public void onClick(View v) {
    Intent intent = new Intent();
    intent.putExtra("channel", ((EditText)findViewById(R.id.channel)).getText().toString());
    setResult(RESULT_OK, intent);
    finish();
  }
}
