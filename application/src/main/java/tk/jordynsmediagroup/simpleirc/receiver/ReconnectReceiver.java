package tk.jordynsmediagroup.simpleirc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Broadcast;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * A receiver to listen for alarms and start a reconnect attempt
 */
public class ReconnectReceiver extends BroadcastReceiver {
  private IRCService service;
  private Server server;

  /**
   * Create a new reconnect receiver
   *
   * @param server The server to reconnect to
   */
  public ReconnectReceiver(IRCService service, Server server) {
    this.service = service;
    this.server = server;
  }

  /**
   * On receive broadcast
   */
  @Override
  public void onReceive(Context context, Intent intent) {
    if( !intent.getAction().equals(Broadcast.SERVER_RECONNECT + server.getId()) ) {
      return;
    }
    service.connect(server);
  }
}
