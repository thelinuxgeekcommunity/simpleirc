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
 * Command: /notice <nickname> <message>
 * <p/>
 * Send a notice to an other user
 */
public class NoticeHandler extends BaseHandler {
  /**
   * Execute /notice
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length > 2 ) {
      String text = BaseHandler.mergeParams(params);

      Message message = new Message(">" + params[1] + "< " + text);
      message.setIcon(R.drawable.info);
      conversation.addMessage(message);

      Intent intent = Broadcast.createConversationIntent(
          Broadcast.CONVERSATION_MESSAGE,
          server.getId(),
          conversation.getName()
      );
      service.sendBroadcast(intent);

      service.getConnection(server.getId()).sendNotice(params[1], text);
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /notice
   */
  @Override
  public String getUsage() {
    return "/notice <nickname> <message>";
  }

  /**
   * Description of /notice
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_notice);
  }
}
