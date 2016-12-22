package tk.jordynsmediagroup.simpleirc.command.handler;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.command.BaseHandler;
import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Channel;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Command: /topic [<topic>]
 * <p/>
 * Show the current topic or change the topic if a new topic is provided
 */
public class TopicHandler extends BaseHandler {
  /**
   * Execute /topic
   */
  @Override
  public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException {
    if( conversation.getType() != Conversation.TYPE_CHANNEL ) {
      throw new CommandException(service.getString(R.string.only_usable_from_channel));
    }

    Channel channel = (Channel)conversation;

    if( params.length == 1 ) {
      // Show topic
      service.getConnection(server.getId()).onTopic(channel.getName(), channel.getTopic(), "", 0, false);
    } else if( params.length > 1 ) {
      // Change topic
      service.getConnection(server.getId()).setTopic(channel.getName(), BaseHandler.mergeParams(params));
    }
  }

  /**
   * Usage of /topic
   */
  @Override
  public String getUsage() {
    return "/topic [<topic>]";
  }

  /**
   * Description of /topic
   */
  @Override
  public String getDescription(Context context) {
    return context.getString(R.string.command_desc_topic);
  }
}
