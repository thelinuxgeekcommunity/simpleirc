package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;
import android.content.Intent;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Broadcast;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /close
 * <p/>
 * Closes the current window
 */
public class CloseHandler extends BaseHandler {
  /**
   * Execute /close
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( conversation.getType() == Conversation.TYPE_SERVER ) {
      throw new CommandException(service.getString(R.string.close_server_window));
    }

    if( params.length == 1 ) {
      if( conversation.getType() == Conversation.TYPE_CHANNEL ) {
        service.getConnection(server.getId()).partChannel(conversation.getName());
      }
      if( conversation.getType() == Conversation.TYPE_QUERY ) {
        server.removeConversation(conversation.getName());

        Intent intent = Broadcast.createConversationIntent(
            Broadcast.CONVERSATION_REMOVE,
            server.getId(),
            conversation.getName()
        );
        service.sendBroadcast(intent);
      }
    }
  }

  /**
   * Usage of /close
   */
  @Override
  public String getUsage() {
    return "/close";
  }

  /**
   * Description of /close
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_close);
  }
}
