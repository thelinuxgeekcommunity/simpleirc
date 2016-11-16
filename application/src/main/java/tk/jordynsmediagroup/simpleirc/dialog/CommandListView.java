package tk.jordynsmediagroup.simpleirc.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tk.jordynsmediagroup.simpleirc.R;

/**
 * Adding commands (to execute after connect) to a server
 */
public class CommandListView extends LinearLayout implements OnClickListener, OnItemClickListener {
  private EditText commandInput;
  private ArrayList<String> commands;
  private ArrayAdapter<String> adapter;


  public CommandListView(Context context, ArrayList<String> commands) {
    super(context);

    this.commands = (ArrayList<String>)commands.clone();

    LayoutInflater.from(context).inflate(R.layout.commandadd, this, true);


    commandInput = (EditText)findViewById(R.id.command);

    adapter = new ArrayAdapter<String>(context, R.layout.commanditem, this.commands);

    ListView list = (ListView)findViewById(R.id.commands);
    list.setAdapter(adapter);
    list.setOnItemClickListener(this);

    ((Button)findViewById(R.id.add)).setOnClickListener(this);

    commandInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
          addCurrentCommand();
          textView.requestFocus();
          return true;
        }
        return false;
      }
    });


  }

  private void addCurrentCommand() {
    String command = commandInput.getText().toString().trim();

    if(command.length() < 1 || command.equals("/")) return;

    if( !command.startsWith("/") ) {
      command = "/" + command;
    }

    commands.add(command);
    adapter.notifyDataSetChanged();
    // This is a silly trick to force the cursor to the end of the line.
    commandInput.setText("/");
    commandInput.setSelection(commandInput.getText().length());

  }


  /**
   * On Click
   */
  @Override
  public void onClick(View v) {
    switch ( v.getId() ) {
      case R.id.add:
        addCurrentCommand();
        break;
    }
  }

  public ArrayList<String> getCommands() {
    return commands;
  }

  /**
   * On item clicked
   */
  @Override
  public void onItemClick(AdapterView<?> list, View item, int position, long id) {
    final String command = adapter.getItem(position);

    // We're going to remove the item at the specified place, but confirm that action.


    AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());

    builder.setNegativeButton(R.string.action_cancel, null);
    builder.setPositiveButton(R.string.action_remove, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        commands.remove(command);
        adapter.notifyDataSetChanged();
      }
    });
    builder.setMessage(command);

    AlertDialog alert = builder.create();
    alert.show();
  }
}
