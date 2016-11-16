package tk.jordynsmediagroup.simpleirc.listener;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

import tk.jordynsmediagroup.simpleirc.adapter.ConversationPagerAdapter;
import tk.jordynsmediagroup.simpleirc.indicator.ConversationIndicator;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Channel;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Listener for conversation selections.
 */
public class ConversationSelectedListener implements OnPageChangeListener {
  private final Context context;
  private final Server server;
  private final TextView titleView;
  private final ConversationIndicator indicator;
  private final ConversationPagerAdapter adapter;

  /**
   * Create a new ConversationSelectedListener
   *
   * @param server
   * @param titleView
   */
  public ConversationSelectedListener(Context ctx, Server server, TextView titleView, ConversationPagerAdapter adapter, ConversationIndicator indicator) {
    this.context = ctx;
    this.server = server;
    this.titleView = titleView;
    this.indicator = indicator;
    this.adapter = adapter;
  }

  /**
   * On page has been selected.
   */
  @Override
  public void onPageSelected(int position) {
    Conversation conversation = adapter.getItem(position);

    if( conversation != null && conversation.getType() != Conversation.TYPE_SERVER ) {
      StringBuilder sb = new StringBuilder();
      sb.append(server.getTitle() + " - " + conversation.getName());
      if( conversation.getType() == Conversation.TYPE_CHANNEL && !((Channel)conversation).getTopic().equals("") ) {
        sb.append(" - " + ((Channel)conversation).getTopic());
      }
      titleView.setText(sb.toString());
    } else {
      titleView.setText(server.getTitle());
    }

    // Remember selection
    if( conversation != null ) {
      Conversation previousConversation = server.getConversation(server.getSelectedConversation());

      if( previousConversation != null ) {
        previousConversation.setStatus(Conversation.STATUS_DEFAULT);
      }

      if( conversation.getNewMentions() > 0 ) {
        Intent i = new Intent(context, IRCService.class);
        i.setAction(IRCService.ACTION_ACK_NEW_MENTIONS);
        i.putExtra(IRCService.EXTRA_ACK_SERVERID, server.getId());
        i.putExtra(IRCService.EXTRA_ACK_CONVTITLE, conversation.getName());
        context.startService(i);
      }

      conversation.setStatus(Conversation.STATUS_SELECTED);
      server.setSelectedConversation(conversation.getName());
    }

    indicator.invalidate();
  }

  /**
   * On scroll state of pager has been chanaged.
   */
  @Override
  public void onPageScrollStateChanged(int state) {
  }

  /**
   * On page has been scrolled.
   */
  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
  }
}
