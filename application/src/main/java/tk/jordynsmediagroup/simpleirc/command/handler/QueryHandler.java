package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;
import android.content.Intent;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Broadcast;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Query;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /query <nickname>
 * <p/>
 * Opens a private chat with the given user
 */
public class QueryHandler extends BaseHandler {
  /**
   * Execute /query
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length == 2 ) {
      // Simple validation
      if( params[1].startsWith("#") ) {
        throw new CommandException(service.getString(R.string.query_to_channel));
      }

      Conversation query = server.getConversation(params[1]);

      if( query != null ) {
        throw new CommandException(service.getString(R.string.query_exists));
      }

      query = new Query(params[1]);
      query.setHistorySize(service.getSettings().getHistorySize());
      server.addConversation(query);

      Intent intent = Broadcast.createConversationIntent(
          Broadcast.CONVERSATION_NEW,
          server.getId(),
          query.getName()
      );
      service.sendBroadcast(intent);
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /query
   */
  @Override
  public String getUsage() {
    return "/query <nickname>";
  }

  /**
   * Description of /query
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_query);
  }
}
