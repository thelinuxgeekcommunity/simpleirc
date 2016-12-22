package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;
import tk.jordynsmediagroup.simpleirc.model.Status;

/**
 * Command: /quit [<reason>]

 */
public class QuitHandler extends BaseHandler {
  /**
   * Execute /quit
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length == 1 ) {
      server.setMayReconnect(false);
      server.setStatus(Status.DISCONNECTED);
      service.getConnection(server.getId()).quitServer();
    } else {
      server.setMayReconnect(false);
      server.setStatus(Status.DISCONNECTED);
      service.getConnection(server.getId()).quitServer(BaseHandler.mergeParams(params));
    }
  }

  /**
   * Usage of /quit
   */
  @Override
  public String getUsage() {
    return "/quit [<reason>]";
  }

  /**
   * Description of /quit
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_quit);
  }
}
