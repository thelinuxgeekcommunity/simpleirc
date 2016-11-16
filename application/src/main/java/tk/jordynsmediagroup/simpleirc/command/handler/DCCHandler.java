package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import java.io.File;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Broadcast;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Message;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /dcc SEND <nickname> <file>
 * <p/>
 * Send a file to a user
 */
public class DCCHandler extends BaseHandler {
  /**
   * Execute /dcc
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( params.length == 4 ) {
      if( !params[1].equalsIgnoreCase("SEND") ) {
        throw new CommandException(service.getString(R.string.dcc_only_send));
      }
      File file = new File(params[3]);
      if( !file.exists() ) {
        throw new CommandException(service.getString(R.string.dcc_file_not_found, params[3]));
      }

      service.getConnection(server.getId()).dccSendFile(file, params[2], 60000);

      Message message = new Message(service.getString(R.string.dcc_waiting_accept, params[2]));
      message.setColor(Message.MessageColor.SERVER_EVENT);
      conversation.addMessage(message);

      service.sendBroadcast(
          Broadcast.createConversationIntent(Broadcast.CONVERSATION_MESSAGE, server.getId(), conversation.getName())
      );
    } else {
      throw new CommandException(service.getString(R.string.invalid_number_of_params));
    }
  }

  /**
   * Usage of /dcc
   */
  @Override
  public String getUsage() {
    return "/dcc SEND <nickname> <file>";
  }

  /**
   * Description of /dcc
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_dcc);
  }
}
