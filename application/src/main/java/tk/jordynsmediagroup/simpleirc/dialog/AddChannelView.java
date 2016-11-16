package tk.jordynsmediagroup.simpleirc.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import tk.jordynsmediagroup.simpleirc.R;

/**
 * Adding auto join channels to a server
 */
public class AddChannelView extends LinearLayout implements OnClickListener, OnItemClickListener {
  private EditText channelInput;
  private ArrayAdapter<String> adapter;
  private ArrayList<String> channels;


  public AddChannelView(Context ctx, ArrayList<String> channels) {
    super(ctx);
    LayoutInflater.from(ctx).inflate(R.layout.channeladd, this, true);
    channelInput = (EditText)findViewById(R.id.channel);
    channelInput.setSelection(1);

    this.channels = (ArrayList<String>)channels.clone();
    adapter = new ArrayAdapter<String>(ctx, R.layout.channelitem, this.channels);

    ListView list = (ListView)findViewById(R.id.channels);
    list.setAdapter(adapter);
    list.setOnItemClickListener(this);

    ((Button)findViewById(R.id.add)).setOnClickListener(this);

  }

  public ArrayList<String> getChannels() {
    return channels;
  }

  /**
   * On Click
   */
  @Override
  public void onClick(View v) {
    switch ( v.getId() ) {
      case R.id.add:
        String channel = channelInput.getText().toString().trim();
        if(channel.equals("#")) return;
        channels.add(channel);
        adapter.notifyDataSetChanged();
        channelInput.setText("#");
        channelInput.setSelection(1);
        break;
    }
  }

  /**
   * On item clicked
   */
  @Override
  public void onItemClick(AdapterView<?> list, View item, int position, long id) {
    final String channel = adapter.getItem(position);

    AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
    builder.setTitle(channel);

    builder.setPositiveButton(R.string.action_remove, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {

        channels.remove(channel);
        adapter.notifyDataSetChanged();
      }
    });
    builder.setNegativeButton(R.string.action_cancel, null);

    AlertDialog alert = builder.create();
    alert.show();
  }
}
