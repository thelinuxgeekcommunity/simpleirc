package tk.jordynsmediagroup.simpleirc.model;

/**
 * An IRC channel (extends Conversation)
 */
public class Channel extends Conversation {
  private String topic;

  /**
   * Create a new channel object
   *
   * @param name of the channel
   */
  public Channel(String name) {
    super(name);
    this.topic = "";
  }

  /**
   * Get the type of this conversation
   */
  @Override
  public int getType() {
    return Conversation.TYPE_CHANNEL;
  }

  /**
   * Set the channel's topic
   *
   * @param topic The topic of the channel
   */
  public void setTopic(String topic) {
    this.topic = topic;
  }

  /**
   * Get the topic of the channel
   *
   * @return The channel's topic
   */
  public String getTopic() {
    return topic;
  }
}
