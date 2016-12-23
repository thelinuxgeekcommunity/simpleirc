package tk.jordynsmediagroup.simpleirc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import tk.jordynsmediagroup.simpleirc.listener.ServerListener;

/**
 * A server receiver for receiving server updates
 */
public class ServerReceiver extends BroadcastReceiver {
  private final ServerListener listener;

  /**
   * Create a new server receiver
   *
   * @param listener
   */
  public ServerReceiver(ServerListener listener) {
    this.listener = listener;
  }

  /**
   * On receive broadcast
   */
  @Override
  public void onReceive(Context context, Intent intent) {
    listener.onStatusUpdate();
  }
}
