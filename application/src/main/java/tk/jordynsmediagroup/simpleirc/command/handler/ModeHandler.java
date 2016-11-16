package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /mode <channel> <mode>
 * <p/>
 * Set or remove channel modes
 */
public class ModeHandler extends BaseHandler {
  /**
   * Execute /mode
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length > 2 ) {
      String modes = BaseHandler.mergeParams(params, 2);

      service.getConnection(server.getId()).setMode(params[1], modes);
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /mode
   */
  @Override
  public String getUsage() {
    return "/mode <channel> <mode>";
  }

  /**
   * Description of /mode
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_mode);
  }
}
