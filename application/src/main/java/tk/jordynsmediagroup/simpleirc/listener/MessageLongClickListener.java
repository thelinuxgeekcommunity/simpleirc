package tk.jordynsmediagroup.simpleirc.listener;

import android.view.View;
import android.view.View.OnLongClickListener;

import tk.jordynsmediagroup.simpleirc.model.Settings;

public class MessageLongClickListener implements OnLongClickListener {

  Settings s;

  public MessageLongClickListener() {
    s = new Settings(null);
  }

  @Override
  public boolean onLongClick(View v) {

    // Handle the appropriate response


    return false;
  }

}
