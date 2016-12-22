package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /quiet <nickname>
 * <p/>
 * Quiets a user on the current channel
 * Or gets the quiet list if executed with no parameters
 */
public class QuietHandler extends BaseHandler {
  /**
   * Execute /quiet
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( conversation.getType() != Conversation.TYPE_CHANNEL ) {
      throw new CommandException(service.getString(R.string.only_usable_from_channel));
    }

    if( params.length == 2 ) {
      service.getConnection(server.getId()).quiet(conversation.getName(), params[1]);
    } else if ( params.length == 1 ) {
      service.getConnection(server.getId()).sendRawLineViaQueue("MODE " + conversation.getName() + " +q");
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /quiet
   */
  @Override
  public String getUsage() {
    return "/quiet <nickname>";
  }

  /**
   * Description of /quiet
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_quiet);
  }
}
