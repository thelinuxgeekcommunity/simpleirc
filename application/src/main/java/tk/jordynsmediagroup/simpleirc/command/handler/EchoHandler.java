package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;
import android.content.Intent;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Broadcast;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Message;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /echo <text>
 */
public class EchoHandler extends BaseHandler {
  /**
   * Execute /echo
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length > 1 ) {
      Message message = new Message(BaseHandler.mergeParams(params));
      conversation.addMessage(message);

      Intent intent = Broadcast.createConversationIntent(
          Broadcast.CONVERSATION_MESSAGE,
          server.getId(),
          conversation.getName()
      );
      service.sendBroadcast(intent);
    } else {
      throw new CommandException(service.getString(R.string.text_missing));
    }
  }

  /**
   * Usage of /echo
   */
  @Override
  public String getUsage() {
    return "/echo <text>";
  }

  /**
   * Description of /echo
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_echo);
  }
}
