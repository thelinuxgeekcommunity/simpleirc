package tk.jordynsmediagroup.simpleirc.db;

import android.provider.BaseColumns;

/**
 * Constants for the channel table
 */
public interface ChannelConstants extends BaseColumns {
  public static final String TABLE_NAME = "channels";

  // fields
  public static final String NAME = "name";
  public static final String PASSWORD = "password";
  public static final String SERVER = "server";

  /**
   * All fields of the table
   */
  public static final String[] ALL = {
      NAME,
      PASSWORD,
      SERVER
  };
}
