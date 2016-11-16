package tk.jordynsmediagroup.simpleirc.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import tk.jordynsmediagroup.simpleirc.App;
import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.model.Extra;
import tk.jordynsmediagroup.simpleirc.model.Message;
import tk.jordynsmediagroup.simpleirc.model.MessageRenderParams;

/**
 * Activity for single message view
 */
public class MessageActivity extends Activity implements Toolbar.OnMenuItemClickListener{

  Message viewedMessage = null;
  TextView messageView = null;

  /**
   * On create
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    setTheme(R.style.AppDialogTheme);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.message);

    MessageRenderParams tmpParams = App.getSettings().getRenderParams();
    tmpParams.messageColors = false;
    tmpParams.colorScheme = "default";
    tmpParams.smileys = false;
    tmpParams.icons = false;
    tmpParams.messageColors = false;
    tmpParams.useDarkScheme = true;


    viewedMessage = getIntent().getExtras().getParcelable(Extra.MESSAGE);

    Toolbar tb = (Toolbar)findViewById(R.id.toolbar);

    tb.inflateMenu(R.menu.messageops);

    tb.setOnMenuItemClickListener(this);

    messageView = (TextView)findViewById(R.id.message);
    messageView.setBackgroundColor(Color.BLACK);

    CharSequence msgSequence = Message.render(viewedMessage, tmpParams);

    messageView.setText(msgSequence);

  }

  /**
   * Handle toolbar menu clicks.
   *
   * @param item
   * @return
   */
  @Override
  public boolean onMenuItemClick(MenuItem item) {
    switch(item.getItemId()) {
      case R.id.message_copy:
        // copy the thing
        ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData cd = ClipData.newPlainText("IRC Message", messageView.getText().toString());
        clipboard.setPrimaryClip(cd);
        break;
      case R.id.close:
        break;
      default:
        return false;
    }
    this.finish();
    return true;
  }
}
