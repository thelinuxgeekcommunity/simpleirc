package tk.jordynsmediagroup.simpleirc.model;

import android.content.Intent;

/**
 * Constants and helpers for Broadcasts
 */
public abstract class Broadcast {
  public static final String SERVER_UPDATE = "jordynsmediagroup.simpleirc.server.status";
  public static final String SERVER_RECONNECT = "jordynsmediagroup.simpleirc.server.reconnect.";

  public static final String CONVERSATION_MESSAGE = "jordynsmediagroup.simpleirc.conversation.message";
  public static final String CONVERSATION_NEW = "jordynsmediagroup.simpleirc.conversation.new";
  public static final String CONVERSATION_REMOVE = "jordynsmediagroup.simpleirc.conversation.remove";
  public static final String CONVERSATION_TOPIC = "jordynsmediagroup.simpleirc.conversation.topic";
  public static final String CONVERSATION_CLEAR = "jordynsmediagroup.simpleirc.conversation.clear";

  /**
   * Create an Intent for conversation broadcasting
   *
   * @param broadcastType    The type of the broadcast, some constant of Broadcast.*
   * @param serverId         The id of the server
   * @param conversationName The unique name of the conversation
   * @return The created Intent
   */
  public static Intent createConversationIntent(String broadcastType, int serverId, String conversationName) {
    Intent intent = new Intent(broadcastType);

    intent.putExtra(Extra.SERVER, serverId);
    intent.putExtra(Extra.CONVERSATION, conversationName);

    return intent;
  }

  /**
   * Create an Intent for server broadcasting
   *
   * @param broadcastType The typo of the broadcast, some constant of Broadcast.*
   * @param serverId      The id of the server
   * @return The created Intent
   */
  public static Intent createServerIntent(String broadcastType, int serverId) {
    Intent intent = new Intent(broadcastType);

    intent.putExtra(Extra.SERVER, serverId);

    return intent;
  }
}
