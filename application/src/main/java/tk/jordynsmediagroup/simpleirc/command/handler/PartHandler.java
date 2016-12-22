package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /part [<channel>]
 * <p/>
 * Leave the current or the given channel
 */
public class PartHandler extends BaseHandler {
  /**
   * Execute /part
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length == 1 ) {
      if( conversation.getType() != Conversation.TYPE_CHANNEL ) {
        throw new CommandException(service.getString(R.string.only_usable_from_channel));
      }

      service.getConnection(server.getId()).partChannel(conversation.getName());
    } else if( params.length == 2 ) {
      service.getConnection(server.getId()).partChannel(params[1]);
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /part
   */
  @Override
  public String getUsage() {
    return "/part [<channel>]";
  }

  /**
   * Description of /part
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_part);
  }
}
