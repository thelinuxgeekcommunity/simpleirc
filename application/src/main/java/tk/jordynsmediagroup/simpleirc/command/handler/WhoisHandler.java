package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /whois <nickname>
 * <p/>
 * Get information about a user
 */
public class WhoisHandler extends BaseHandler {
  /**
   * Execute /whois
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length != 2 ) {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }

    service.getConnection(server.getId()).sendRawLineViaQueue("WHOIS " + params[1]);
  }

  /**
   * Get description of /whois
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_whois);
  }

  /**
   * Get usage of /whois
   */
  @Override
  public String getUsage() {
    return "/whois <nickname>";
  }
}
