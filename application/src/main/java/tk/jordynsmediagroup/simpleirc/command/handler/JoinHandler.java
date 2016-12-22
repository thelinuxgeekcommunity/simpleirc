package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /join <channel> [<key>]
 */
public class JoinHandler extends BaseHandler {
  /**
   * Execute /join
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length == 2 ) {
      service.getConnection(server.getId()).joinChannel(params[1]);
    } else if( params.length == 3 ) {
      service.getConnection(server.getId()).joinChannel(params[1], params[2]);
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /join
   */
  @Override
  public String getUsage() {
    return "/join <channel> [<key>]";
  }

  /**
   * Description of /join
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_join);
  }
}
