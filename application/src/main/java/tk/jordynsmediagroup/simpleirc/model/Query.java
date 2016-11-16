package tk.jordynsmediagroup.simpleirc.model;

/**
 * A query (a private chat between to users)
 */
public class Query extends Conversation {
  /**
   * Create a new query
   *
   * @param name The user's nickname
   */
  public Query(String name) {
    super(name);
  }

  /**
   * Get the type of this conversation
   */
  @Override
  public int getType() {
    return Conversation.TYPE_QUERY;
  }
}
