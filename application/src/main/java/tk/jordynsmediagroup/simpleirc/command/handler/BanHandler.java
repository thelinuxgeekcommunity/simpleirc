package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /ban <nickname>
 * <p/>
 * Bans a user from the current channel
 * Or gets the ban list if executed with no parameters
 */
public class BanHandler extends BaseHandler {
  /**
   * Execute /ban
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( conversation.getType() != Conversation.TYPE_CHANNEL ) {
      throw new CommandException(service.getString(R.string.only_usable_from_channel));
    }

    if( params.length == 2 ) {
      service.getConnection(server.getId()).ban(conversation.getName(), params[1]);
    } else if ( params.length == 1 ) {
      service.getConnection(server.getId()).sendRawLineViaQueue("MODE " + conversation.getName() + " +b");
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /ban
   */
  @Override
  public String getUsage() {
    return "/ban <nickname>";
  }

  /**
   * Description of /ban
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_ban);
  }
}
