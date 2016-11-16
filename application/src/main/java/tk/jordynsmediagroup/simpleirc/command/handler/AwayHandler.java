package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /away [<reason>]
 * <p/>
 * Sets you away
 */
public class AwayHandler extends BaseHandler {
  /**
   * Execute /away
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    service.getConnection(server.getId()).sendRawLineViaQueue("AWAY " + BaseHandler.mergeParams(params));
  }

  /**
   * Get description of /away
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_away);
  }

  /**
   * Get usage of /away
   */
  @Override
  public String getUsage() {
    return "/away [<reason>]";
  }
}
