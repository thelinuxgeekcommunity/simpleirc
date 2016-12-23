package tk.jordynsmediagroup.simpleirc.listener;

/**
 * Listener for changes regarding a server
 */
public interface ServerListener {
  /**
   * On server status update (disconnected, connecting, connected)
   */
  public void onStatusUpdate();
}
