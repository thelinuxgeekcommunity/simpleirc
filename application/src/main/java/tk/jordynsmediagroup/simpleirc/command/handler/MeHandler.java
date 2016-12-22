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
 * Command: /me <action>
 */
public class MeHandler extends BaseHandler {
  /**
   * Execute /me
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( conversation.getType() == Conversation.TYPE_SERVER ) {
      throw new CommandException(service.getString(R.string.only_usable_from_channel_or_query));
    }

    if( params.length > 1 ) {
      String action = BaseHandler.mergeParams(params);
      String nickname = service.getConnection(server.getId()).getNick();

      Message message = new Message(action, nickname, Message.TYPE_ACTION);
      message.setIcon(R.drawable.action);
      server.getConversation(conversation.getName()).addMessage(message);

      Intent intent = Broadcast.createConversationIntent(
          Broadcast.CONVERSATION_MESSAGE,
          server.getId(),
          conversation.getName()
      );
      service.sendBroadcast(intent);

      service.getConnection(server.getId()).sendAction(conversation.getName(), action);
    } else {
      throw new CommandException(service.getString(R.string.text_missing));
    }
  }

  /**
   * Usage of /me
   */
  @Override
  public String getUsage() {
    return "/me <text>";
  }

  /**
   * Description of /me
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_me);
  }
}
