package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /raw <line>
 * <p/>
 * Send a raw line to the server
 */
public class RawHandler extends BaseHandler {
  /**
   * Execute /raw
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length > 1 ) {
      String line = BaseHandler.mergeParams(params);
      service.getConnection(server.getId()).sendRawLineViaQueue(line);
    } else {
      throw new CommandException(service.getString(R.string.line_missing));
    }
  }

  /**
   * Usage of /raw
   */
  @Override
  public String getUsage() {
    return "/raw <line>";
  }

  /**
   * Description of /raw
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_raw);
  }
}
