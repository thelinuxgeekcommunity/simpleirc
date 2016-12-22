package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /back
 * <p/>
 * Turn off the away status
 */
public class BackHandler extends BaseHandler {
  /**
   * Execute /back
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    service.getConnection(server.getId()).sendRawLineViaQueue("AWAY");
  }

  /**
   * Get description of /back
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_back);
  }

  /**
   * Get usage of /back
   */
  @Override
  public String getUsage() {
    return "/back";
  }
}
