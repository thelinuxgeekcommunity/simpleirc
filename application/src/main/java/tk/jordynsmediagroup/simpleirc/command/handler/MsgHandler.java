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
 * Command: /msg <target> <message>
 * <p/>
 * Send a message to a channel or user
 */
public class MsgHandler extends BaseHandler {
  /**
   * Execute /msg
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length > 2 ) {
      String text = BaseHandler.mergeParams(params, 2);
      service.getConnection(server.getId()).sendMessage(params[1], text);

      Conversation targetConversation = server.getConversation(params[1]);

      if( targetConversation != null ) {
        Message message = new Message("<" + service.getConnection(server.getId()).getNick() + "> " + text);
        targetConversation.addMessage(message);

        Intent intent = Broadcast.createConversationIntent(
            Broadcast.CONVERSATION_MESSAGE,
            server.getId(),
            targetConversation.getName()
        );

        service.sendBroadcast(intent);
      }
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /msg
   */
  @Override
  public String getUsage() {
    return "/msg <target> <message>";
  }

  /**
   * Description of /msg
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_msg);
  }
}
