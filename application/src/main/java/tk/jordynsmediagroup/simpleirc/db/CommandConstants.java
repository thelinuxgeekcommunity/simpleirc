package tk.jordynsmediagroup.simpleirc.db;

import android.provider.BaseColumns;

/**
 * Constants for the command table
 */
public interface CommandConstants extends BaseColumns {
  public static final String TABLE_NAME = "commands";

  // fields
  public static final String COMMAND = "command";
  public static final String SERVER = "server";

  /**
   * All fields of the table
   */
  public static final String[] ALL = {
      COMMAND,
      SERVER
  };
}
