package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /nick <nickname>
 */
public class NickHandler extends BaseHandler {
  /**
   * Execute /nick
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length == 2 ) {
      service.getConnection(server.getId()).changeNick(params[1]);
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /nick
   */
  @Override
  public String getUsage() {
    return "/nick <nickname>";
  }

  /**
   * Description of /nick
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_nick);
  }
}
