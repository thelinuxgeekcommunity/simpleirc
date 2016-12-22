package tk.jordynsmediagroup.simpleirc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tk.jordynsmediagroup.simpleirc.SimpleIRC;
import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.model.Server;

/**
 * Adapter for server lists
 */
public class ServerListAdapter extends BaseAdapter {

  private ArrayList<Server> servers;

  /**
   * Create a new adapter for server lists
   */
  public ServerListAdapter() {
    loadServers();
  }

  /**
   * Load servers from database
   * <p/>
   * Delegate call to yaaic instance
   */
  public void loadServers() {
    servers = SimpleIRC.getInstance().getServersAsArrayList();
    notifyDataSetChanged();
  }

  /**
   * Get number of items
   */
  @Override
  public int getCount() {
    int size = servers.size();

    // Display "Add server" item
    if( size == 0 ) {
      return 1;
    }

    return size;
  }

  /**
   * Get item at position
   *
   * @param position
   */
  @Override
  public Server getItem(int position) {
    if( servers.size() == 0 ) {
      return null; // No server object for the "add server" view
    }

    return servers.get(position);
  }

  /**
   * Get id of item at position
   *
   * @param position
   */
  @Override
  public long getItemId(int position) {
    if( servers.size() == 0 ) {
      return 0;
    }

    return getItem(position).getId();
  }

  /**
   * Get view for item at given position
   *
   * @param position
   * @param convertView
   * @param parent
   */
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Server server = getItem(position);

    LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    if( server == null ) {
      // Return "Add server" view
      return inflater.inflate(R.layout.addserveritem, null);
    }

    View v = inflater.inflate(R.layout.serveritem, null);

    TextView titleView = (TextView)v.findViewById(R.id.title);
    titleView.setText(server.getTitle());

    TextView hostView = (TextView)v.findViewById(R.id.host);
    hostView.setText(server.getIdentity().getNickname() + "@" + server.getHost() + ":" + server.getPort());

    ((ImageView)v.findViewById(R.id.status)).setImageResource(server.getStatusIcon());

    return v;
  }
}
