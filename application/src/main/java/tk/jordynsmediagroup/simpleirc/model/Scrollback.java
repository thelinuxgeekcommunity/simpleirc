package tk.jordynsmediagroup.simpleirc.model;

import java.util.LinkedList;

/**
 * Class for handling the scrollback history
 */
public class Scrollback {
  public static final int MAX_HISTORY = 10;

  private final LinkedList<String> messages;
  private int pointer;

  /**
   * Create a new scrollback object
   */
  public Scrollback() {
    messages = new LinkedList<String>();
  }

  /**
   * Add a message to the history
   */
  public void addMessage(String message) {
    messages.addLast(message);

    if( messages.size() > MAX_HISTORY ) {
      messages.removeFirst();
    }

    pointer = messages.size();
  }

  /**
   * Go back in history
   *
   * @return
   */
  public String goBack() {
    if( pointer > 0 ) {
      pointer--;
    }

    if( messages.size() > 0 ) {
      return messages.get(pointer);
    }

    return null;
  }

  /**
   * Go forward in history
   *
   * @return
   */
  public String goForward() {
    if( pointer < messages.size() - 1 ) {
      pointer++;
    } else {
      return "";
    }

    if( messages.size() > 0 ) {
      return messages.get(pointer);
    }

    return null;
  }
}
