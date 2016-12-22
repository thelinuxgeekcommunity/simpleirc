package tk.jordynsmediagroup.simpleirc.view;

import android.content.Context;
import android.widget.ListView;

import tk.jordynsmediagroup.simpleirc.App;
import tk.jordynsmediagroup.simpleirc.adapter.MessageListAdapter;
import tk.jordynsmediagroup.simpleirc.listener.MessageClickListener;
import tk.jordynsmediagroup.simpleirc.model.ColorScheme;

/**
 * A customized ListView for Messages
 */
public class MessageListView extends ListView {



  /**
   * Create a new MessageListView
   *
   * @param context
   */
  public MessageListView(Context context) {
    super(context);

    ColorScheme _scheme = App.getColorScheme();

    setOnItemLongClickListener(MessageClickListener.getInstance());


    setStackFromBottom(true);

    setDivider(null);

    setFastScrollEnabled(true);

    setCacheColorHint(_scheme.getBackground());
    setVerticalFadingEdgeEnabled(false);
    setScrollBarStyle(SCROLLBARS_INSIDE_OVERLAY);

    setBackgroundColor(_scheme.getBackground());

    // Scale padding by screen density
    float density = context.getResources().getDisplayMetrics().density;
    int padding = (int)(5 * density);
    setPadding(padding, padding, padding, padding);

    setTranscriptMode(TRANSCRIPT_MODE_NORMAL);
  }

  /**
   * Get the adapter of this MessageListView
   * (Helper to avoid casting)
   *
   * @return The MessageListAdapter
   */
  @Override
  public MessageListAdapter getAdapter() {
    return (MessageListAdapter)super.getAdapter();
  }
}
