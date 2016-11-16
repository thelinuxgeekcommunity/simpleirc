package tk.jordynsmediagroup.simpleirc.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import tk.jordynsmediagroup.simpleirc.activity.MessageActivity;
import tk.jordynsmediagroup.simpleirc.adapter.MessageListAdapter;
import tk.jordynsmediagroup.simpleirc.model.Extra;
import tk.jordynsmediagroup.simpleirc.model.Message;

/**
 * Listener for clicks on conversation messages
 */
public class MessageClickListener implements OnItemClickListener,AdapterView.OnItemLongClickListener {
  private static MessageClickListener instance;

  /**
   * Private constructor
   */
  private MessageClickListener() {
  }

  /**
   * Get global instance of message click listener
   *
   * @return
   */
  public static synchronized MessageClickListener getInstance() {
    if( instance == null ) {
      instance = new MessageClickListener();
    }

    return instance;
  }

  private void doThing(AdapterView<?> group, int position) {
    android.util.Log.d("MessageClickListener", "clicking on item => "+position);
    MessageListAdapter adapter = (MessageListAdapter)group.getAdapter();
    Message m = adapter.getItem(position);
    Intent intent = new Intent(group.getContext(), MessageActivity.class);
    // this is going to be a parcelable.
    // Woo parcelables.
    intent.putExtra(Extra.MESSAGE, m);

    group.getContext().startActivity(intent);
  }

  /**
   * On message item clicked
   */
  @Override
  public void onItemClick(AdapterView<?> group, View view, int position, long id) {
    doThing(group, position);
  }

  @Override
  public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
    doThing(adapterView, i);

    return true;
  }
}
