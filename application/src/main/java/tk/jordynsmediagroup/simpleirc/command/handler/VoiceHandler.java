package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /voice <nickname>
 */
public class VoiceHandler extends BaseHandler {
  /**
   * Execute /voice
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( conversation.getType() != Conversation.TYPE_CHANNEL ) {
      throw new CommandException(service.getString(R.string.only_usable_from_channel));
    }

    if( params.length == 2 ) {
      service.getConnection(server.getId()).voice(conversation.getName(), params[1]);
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /voice
   */
  @Override
  public String getUsage() {
    return "/voice <nickname>";
  }

  /**
   * Description of /voice
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_voice);
  }
}
