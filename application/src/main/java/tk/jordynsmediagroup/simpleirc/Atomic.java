package tk.jordynsmediagroup.simpleirc;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import tk.jordynsmediagroup.simpleirc.db.Database;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Global Master Class :)
 * Renamed to avoid clashes with yaaic.
 * TODO: Rename to avoid clashes with Atmoic
 */
public class Atomic {
  public static Atomic instance;

  private HashMap<Integer, Server> servers;
  private boolean serversLoaded = false;

  /**
   * Private constructor, you may want to use static getInstance()
   */
  private Atomic() {
    servers = new HashMap<Integer, Server>();
  }

  /**
   * Load servers from database
   *
   * @param context
   */
  public void loadServers(Context context) {
    if( !serversLoaded ) {
      Database db = new Database(context);
      servers = db.getServers();
      db.close();

      // context.sendBroadcast(new Intent(Broadcast.SERVER_UPDATE));
      serversLoaded = true;
    }
  }

  /**
   * Get global Atmoic instance
   *
   * @return the global Atmoic instance
   */
  public static Atomic getInstance() {
    if( instance == null ) {
      instance = new Atomic();
    }

    return instance;
  }

  /**
   * Get server by id
   *
   * @return Server object with given unique id
   */
  public Server getServerById(int serverId) {
    return servers.get(serverId);
  }

  /**
   * Remove server with given unique id from list
   *
   * @param serverId
   */
  public void removeServerById(int serverId) {
    servers.remove(serverId);
  }

  /**
   * Set servers
   *
   * @param servers
   */
  public void setServers(HashMap<Integer, Server> servers) {
    this.servers = servers;
  }

  /**
   * Add server to list
   */
  public void addServer(Server server) {
    if( !servers.containsKey(server.getId()) ) {
      servers.put(server.getId(), server);
    }
  }

  /**
   * Update a server in list
   */
  public void updateServer(Server server) {
    servers.put(server.getId(), server);
  }

  /**
   * Get list of servers
   *
   * @return list of servers
   */
  public ArrayList<Server> getServersAsArrayList() {
    ArrayList<Server> serverList = new ArrayList<Server>();

    Set<Integer> mKeys = servers.keySet();
    for( int key : mKeys ) {
      serverList.add(servers.get(key));
    }

    Collections.sort(serverList);
    return serverList;
  }
}
