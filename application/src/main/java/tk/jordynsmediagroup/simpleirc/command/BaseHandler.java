package tk.jordynsmediagroup.simpleirc.command;

import android.content.Context;

import tk.jordynsmediagroup.simpleirc.exception.CommandException;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Base class for commands
 */
public abstract class BaseHandler {
  /**
   * Execute the command
   *
   * @param params  The params given (0 is the command itself)
   * @param server  The server object
   * @param channel The channel object or null if no channel is selected
   * @param service The service with all server connections
   * @throws CommandException if command couldn't be executed
   */
  public abstract void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException;

  /**
   * Get the usage description for this command
   *
   * @return The usage description
   */
  public abstract String getUsage();

  /**
   * Get the description for this command
   *
   * @param context The current context. Needed for getting string resources
   * @return
   */
  public abstract String getDescription(Context context);

  /**
   * Merge params to a string
   *
   * @params params The params to merge
   */
  public static String mergeParams(String[] params) {
    return mergeParams(params, 1);
  }

  /**
   * Merge params to a string
   *
   * @param params   The params to merge
   * @param position Start at given param
   */
  public static String mergeParams(String[] params, int position) {
    StringBuffer buffer = new StringBuffer();

    for(; position < params.length; position++ ) {
      buffer.append(params[position]);
      buffer.append(" ");
    }

    return buffer.toString().trim();
  }
}
