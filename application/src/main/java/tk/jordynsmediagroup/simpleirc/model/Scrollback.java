package tk.jordynsmediagroup.simpleirc.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import tk.jordynsmediagroup.simpleirc.App;
import tk.jordynsmediagroup.simpleirc.logging.Logging;

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
    // Add message to the buffer here
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
