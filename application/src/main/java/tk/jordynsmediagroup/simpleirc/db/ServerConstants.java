package tk.jordynsmediagroup.simpleirc.db;

import android.provider.BaseColumns;

/**
 * Constants for the server table
 */
public interface ServerConstants extends BaseColumns {
  public static final String TABLE_NAME = "servers";

  // fields
  public static final String TITLE = "title";
  public static final String HOST = "host";
  public static final String PORT = "port";
  public static final String PASSWORD = "password";
  public static final String AUTOCONNECT = "autoConnect";
  public static final String USE_SSL = "useSSL";
  public static final String CHARSET = "charset";
  public static final String IDENTITY = "identity";
  public static final String NICKSERV_PASSWORD = "nickserv_password";
  public static final String SASL_USERNAME = "sasl_username";
  public static final String SASL_PASSWORD = "sasl_password";

  /**
   * All fields of the table
   */
  public static final String[] ALL = {
      _ID,
      TITLE,
      HOST,
      PORT,
      PASSWORD,
      AUTOCONNECT,
      USE_SSL,
      CHARSET,
      IDENTITY,
      NICKSERV_PASSWORD,
      SASL_USERNAME,
      SASL_PASSWORD
  };
}
