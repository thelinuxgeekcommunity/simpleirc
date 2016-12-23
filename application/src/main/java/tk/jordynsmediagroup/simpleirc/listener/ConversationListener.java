package tk.jordynsmediagroup.simpleirc.listener;

/**
 * Listener for conversations
 *
 */
public interface ConversationListener {
  /**
   * On new conversation message for given target
   *
   * @param target
   */
  public void onConversationMessage(String target);

  /**
   * On new conversation created (for given target)
   *
   * @param target
   */
  public void onNewConversation(String target);

  /**
   * On conversation removed (for given target)
   *
   * @param target
   */
  public void onRemoveConversation(String target);

  /**
   * On topic changed (for given target)
   *
   * @param target
   */
  public void onTopicChanged(String target);

  /**
   * Clear the given target conversation.
   *
   * @param target
   */
  public void onClearConversation(String target);
}
