package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;
import android.content.Intent;

import java.util.Collection;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Broadcast;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Message;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /amsg <message>
 * <p/>
 * Send a message to all channels on the server
 */
public class AMsgHandler extends BaseHandler {
  /**
   * Execute /amsg
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length > 1 ) {
      String text = BaseHandler.mergeParams(params);

      Collection<Conversation> mConversations = server.getConversations();

      for( Conversation currentConversation : mConversations ) {
        if( currentConversation.getType() == Conversation.TYPE_CHANNEL ) {
          Message message = new Message("<" + service.getConnection(server.getId()).getNick() + "> " + text);
          currentConversation.addMessage(message);

          Intent intent = Broadcast.createConversationIntent(
              Broadcast.CONVERSATION_MESSAGE,
              server.getId(),
              currentConversation.getName()
          );

          service.sendBroadcast(intent);

          service.getConnection(server.getId()).sendMessage(currentConversation.getName(), text);
        }
      }
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /amsg
   */
  @Override
  public String getUsage() {
    return "/amsg <message>";
  }

  /**
   * Description of /amsg
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_amsg);
  }
}
