package tk.jordynsmediagroup.simpleirc.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import tk.jordynsmediagroup.simpleirc.App;
import tk.jordynsmediagroup.simpleirc.logging.Logging;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Message;
import tk.jordynsmediagroup.simpleirc.model.Settings;

/**
 * Adapter for (channel) messages in a ListView
 */
public class MessageListAdapter extends BaseAdapter {
  private final LinkedList<Message> messages;
  private int historySize;

  private Settings _settings;


  /**
   * Create a new MessageAdapter
   *
   * @param conversation
   * @param context
   */
  public MessageListAdapter(Conversation conversation, Context context) {

    _settings = App.getSettings();

    LinkedList<Message> messages = new LinkedList<Message>();

    // Optimization - cache field lookups
/*    LinkedList<Message> mHistory = conversation.getHistory();
    int mSize = mHistory.size();

    for( int i = 0; i < mSize; i++ ) {
      messages.add(mHistory.get(i));
    } */

    messages.addAll(conversation.getHistory());


    // XXX: We don't want to clear the buffer, we want to add only
    //      buffered messages that are not already added (history)
    conversation.clearBuffer();

    this.messages = messages;
    historySize = conversation.getHistorySize();
  }


  /**
   * Add a message to the list
   *
   * @param message
   */
  public void addMessage(Message message) {
    messages.add(message);

    if( messages.size() > historySize ) {
      messages.remove(0);
    }

    notifyDataSetChanged();
  }

  /**
   * Add a list of messages to the list
   *
   * @param messages
   */
  public void addBulkMessages(LinkedList<Message> messages) {
    LinkedList<Message> mMessages = this.messages;
    int mSize = messages.size();

    for( int i = mSize - 1; i > -1; i-- ) {
      mMessages.add(messages.get(i));

      if( mMessages.size() > historySize ) {
        mMessages.remove(0);
      }
    }

    notifyDataSetChanged();
  }

  /**
   * Get number of items
   *
   * @return
   */
  @Override
  public int getCount() {
    return messages.size();
  }

  /**
   * Get item at given position
   *
   * @param position
   * @return
   */
  @Override
  public Message getItem(int position) {
    return messages.get(position);
  }

  /**
   * Get id of item at given position
   *
   * @param position
   * @return
   */
  @Override
  public long getItemId(int position) {
    return position;
  }

  /**
   * Get item view for the given position
   *
   * @param position
   * @param convertView
   * @param parent
   * @return
   */
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    TextView view = (TextView)convertView;
    if( view == null ) {
      view = new TextView(parent.getContext());
      view.setAutoLinkMask(Linkify.ALL);
      view.setLinksClickable(true);
      view.setTypeface(Typeface.MONOSPACE);
    }

    view = getItem(position).render(view);
    view.setTextSize(_settings.getFontSize());

    return view;
  }

  /**
   * Clears the conversation of any messages.
   */
  public void clear() {
    messages.clear();
    notifyDataSetChanged();
  }

  /**
   * XXX This is almost certainly covering up a bug elsewhere -- find it!
   */
  @Override
  public void unregisterDataSetObserver(DataSetObserver observer) {
    if( observer == null ) {
      return;
    }
    super.unregisterDataSetObserver(observer);
  }
}
