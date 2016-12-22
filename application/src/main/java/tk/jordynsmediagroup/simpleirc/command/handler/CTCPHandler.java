package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /ctcp <nick> <command>
 * <p/>
 * Send a CTCP command to a user
 */
public class CTCPHandler extends BaseHandler {
  /**
   * Execute /ctcp
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length > 2 ) {
      String command = BaseHandler.mergeParams(params, 2);
      service.getConnection(server.getId()).sendCTCPCommand(params[1], command);
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /ctcp
   */
  @Override
  public String getUsage() {
    return "/ctcp <nick> <command>";
  }

  /**
   * Description of /ctcp
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_ctcp);
  }
}
