package tk.jordynsmediagroup.simpleirc.model;

/**
 * ServerInfo for all messages regarding the server
 */
public class ServerInfo extends Conversation {
  public static final String DEFAULT_NAME = "";

  /**
   * Create a new ServerInfo object
   *
   * @param name
   */
  public ServerInfo() {
    super(DEFAULT_NAME);
  }

  /**
   * Get the type of this conversation
   */
  @Override
  public int getType() {
    return Conversation.TYPE_SERVER;
  }
}
