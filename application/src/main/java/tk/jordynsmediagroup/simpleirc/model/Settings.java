package tk.jordynsmediagroup.simpleirc.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.preference.PreferenceManager;

import tk.jordynsmediagroup.simpleirc.R;

/**
 * The settings class is a helper class to access the different preferences via
 * small and simple methods.
 * <p/>
 * Note: As this class carries a Context instance as private member, instances
 * of this class should be thrown away not later than when the Context should be
 * gone. Otherwise this could leak memory.
 *
 */
public class Settings {
  private final SharedPreferences preferences;
  private final Resources resources;
  private int currentRelease;


  // This is static so that all instances of the Settings object will
  // keep in sync.

  /**
   * Create a new Settings instance
   *
   * @param context
   */
  public Settings(Context context) {
    this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    this.resources = context.getApplicationContext().getResources();
    try {
      this.currentRelease = context.getPackageManager().getPackageInfo(
              context.getPackageName(), 0).versionCode;
    } catch ( Exception ex ) {
      this.currentRelease = 99;
    }


  }


  /**
   * Prefix all messages with a timestamp?
   *
   * @return
   */
  public boolean showTimestamp() {
    return preferences.getBoolean(resources
            .getString(R.string.key_show_timestamp), Boolean.parseBoolean(resources
            .getString(R.string.default_show_timestamp)));
  }

  /**
   * Show icons to highlight special events
   *
   * @return
   */
  public boolean showIcons() {
    return preferences.getBoolean(resources.getString(R.string.key_show_icons),
            Boolean.parseBoolean(resources.getString(R.string.default_show_icons)));
  }

  /**
   * Show colors to highlight special events?
   *
   * @return
   */
  public boolean showMessageColors() {
    return preferences
            .getBoolean(resources.getString(R.string.key_show_colors), Boolean
                    .parseBoolean(resources.getString(R.string.default_show_colors)));
  }

  /**
   * Show colors to highlight nicknames?
   *
   * @return
   */
  public boolean showColorsNick() {
    return preferences.getBoolean(resources
            .getString(R.string.key_show_colors_nick), Boolean
            .parseBoolean(resources.getString(R.string.default_show_colors_nick)));
  }

  /**
   * Use 24 hour format for timestamps?
   *
   * @return
   */
  public boolean use24hFormat() {
    return preferences.getBoolean(resources.getString(R.string.key_24h_format),
            Boolean.parseBoolean(resources.getString(R.string.default_24h_format)));
  }

  /**
   * Include seconds in timestamps?
   *
   * @return
   */
  public boolean includeSeconds() {
    return preferences.getBoolean(resources
            .getString(R.string.key_include_seconds), Boolean
            .parseBoolean(resources.getString(R.string.default_include_seconds)));
  }

  /**
   * Is reconnect on disconnect enabled?
   *
   * @return
   */
  public boolean isReconnectEnabled() {
    return preferences.getBoolean(resources.getString(R.string.key_reconnect_error),
            Boolean.parseBoolean(resources.getString(R.string.default_reconnect_error)));
  }


  /**
   * Ignore the automatic MOTD?
   *
   * @return
   */
  public boolean isIgnoreMOTDEnabled() {
    return preferences
            .getBoolean(resources.getString(R.string.key_ignore_motd), Boolean
                    .parseBoolean(resources.getString(R.string.default_ignore_motd)));
  }

  /**
   * Get the quit message
   *
   * @return The message to display when the user disconnects
   */
  public String getQuitMessage() {
    return preferences.getString(resources.getString(R.string.key_quitmessage),
            resources.getString(R.string.default_quitmessage));
  }
  /**
   * Get the font size
   *
   * @return The font size for conversation messages
   */
  public int getFontSize() {
    return Integer.parseInt(preferences.getString(
            resources.getString(R.string.key_fontsize),
            resources.getString(R.string.default_fontsize)));
  }

  /**
   * Is voice recognition enabled?
   *
   * @return True if voice recognition is enabled, false otherwise
   */
  public boolean isVoiceRecognitionEnabled() {
    return preferences.getBoolean(resources
            .getString(R.string.key_voice_recognition), Boolean
            .parseBoolean(resources.getString(R.string.default_voice_recognition)));
  }

  /**
   * Play notification sound on highlight?
   *
   * @return True if sound should be played on highlight, false otherwise
   */
  public boolean isSoundHighlightEnabled() {
    return preferences.getBoolean(resources
            .getString(R.string.key_sound_highlight), Boolean
            .parseBoolean(resources.getString(R.string.default_sound_highlight)));
  }

  /**
   * What notification tone to play?
   *
   * @return U
   */
  public Uri getHighlightSoundLocation() {
    return Uri.parse(preferences.getString(
            resources.getString(R.string.key_sound_ring),
            "content://settings/system/notification_sound"));

    /*
     * return preferences.getBoolean(
     * resources.getString(R.string.key_sound_highlight),
     * Boolean.parseBoolean(resources
     * .getString(R.string.default_sound_highlight)) );
     */
  }

  /**
   * Vibrate on highlight?
   *
   * @return True if vibrate on highlight is enabled, false otherwise
   */
  public boolean isVibrateHighlightEnabled() {
    return preferences.getBoolean(resources
            .getString(R.string.key_vibrate_highlight), Boolean
            .parseBoolean(resources.getString(R.string.default_vibrate_highlight)));
  }

  /**
   * Auto rejoin after kick?
   *
   * @return True if Auto rejoin after kick is enabled, false otherwise
   */
  public boolean isAutoRejoinAfterKick() {
    return preferences.getBoolean(resources
            .getString(R.string.key_autorejoin_kick), Boolean.parseBoolean(resources
            .getString(R.string.default_autorejoin_kick)));
  }

  /**
   * LED light notification on highlight?
   *
   * @return True if LED light on highlight is enabled, false otherwise
   */
  public boolean isLedHighlightEnabled() {
    return preferences.getBoolean(resources
            .getString(R.string.key_led_highlight), Boolean.parseBoolean(resources
            .getString(R.string.default_led_highlight)));
  }

  /**
   * Should join, part and quit messages be displayed?
   *
   * @return True if joins, parts and quits should be displayed, false otherwise
   */
  public boolean showJoinPartAndQuit() {
    return preferences.getBoolean(resources
            .getString(R.string.key_show_joinpartquit), Boolean
            .parseBoolean(resources.getString(R.string.default_show_joinpartquit)));
  }

  /**
   * Should notices be shown in the server window instead in the focused window?
   *
   * @return True if notices should be shown in the server window
   */
  public boolean showNoticeInServerWindow() {
    return preferences.getBoolean(resources
            .getString(R.string.key_notice_server_window), Boolean
            .parseBoolean(resources
                    .getString(R.string.default_notice_server_window)));
  }

  /**
   * Render messages with color and style codes.
   *
   * @return True if colors should be rendered, false if they should be removed.
   */
  public boolean showMircColors() {
    return preferences
            .getBoolean(resources.getString(R.string.key_mirc_colors), Boolean
                    .parseBoolean(resources.getString(R.string.default_mirc_colors)));
  }

  /**
   * Keep screen on while in a ConversationActivity
   *
   * @return True if the screen should be kept on, false if the screen should be able to turn off
   */
  public boolean keepScreenOn() {
    return preferences
            .getBoolean(resources.getString(R.string.key_keep_screen_on), Boolean
                    .parseBoolean(resources.getString(R.string.default_keep_screen_on)));
  }

  /**
   * Goto new query(s)
   *
   * @return True if we should goto new query(s), false if we shouldn't goto new query(s)
   */
  public boolean gotoNewQuery() {
    return preferences
            .getBoolean(resources.getString(R.string.key_goto_new_query), Boolean
                    .parseBoolean(resources.getString(R.string.default_goto_new_query)));
  }

  /**
   * Render messages with graphical smilies.
   *
   * @return True if text smilies should be rendered as graphical smilies, false
   * otherwise.
   */
  public boolean showGraphicalSmilies() {
    return preferences.getBoolean(resources
            .getString(R.string.key_graphical_smilies), Boolean
            .parseBoolean(resources.getString(R.string.default_graphical_smilies)));
  }

  public String getColorScheme() {
    return preferences.getString(resources.getString(R.string.key_colorscheme),
            resources.getString(R.string.default_colorscheme));
  }

  public void setColorScheme(String val) {
    preferences.edit()
            .putString(resources.getString(R.string.key_colorscheme), val).commit();
  }

  /**
   * Whether message text should be autocorrected.
   */
  public boolean autoCorrectText() {
    return preferences.getBoolean(resources
            .getString(R.string.key_autocorrect_text), Boolean
            .parseBoolean(resources.getString(R.string.default_autocorrect_text)));
  }

  /**
   * Should IRC traffic be logged to the verbose log?
   *
   * @return
   */
  public boolean debugTraffic() {
    return preferences.getBoolean(resources
            .getString(R.string.key_debug_traffic), Boolean.parseBoolean(resources
            .getString(R.string.default_debug_traffic)));
  }

  public boolean getAutorejoinKick() {
    return preferences.getBoolean(resources
            .getString(R.string.key_debug_traffic), Boolean.parseBoolean(resources
            .getString(R.string.default_debug_traffic)));
  }

  /**
   * Whether sentences in messages should be automatically capitalized.
   */
  public boolean autoCapSentences() {
    return preferences.getBoolean(resources
            .getString(R.string.key_autocap_sentences), Boolean
            .parseBoolean(resources.getString(R.string.default_autocap_sentences)));
  }

  /**
   * Whether the fullscreen keyboard should be used in landscape mode.
   */
  public boolean imeExtract() {
    return preferences
            .getBoolean(resources.getString(R.string.key_ime_extract), Boolean
                    .parseBoolean(resources.getString(R.string.default_ime_extract)));
  }

  public boolean showChannelBar() {
    return preferences.getBoolean(resources
            .getString(R.string.key_show_channelbar), Boolean
            .parseBoolean(resources.getString(R.string.default_show_channelbar)));
  }

  public boolean reconnectTransient() {
    return preferences.getBoolean(resources
                    .getString(R.string.key_reconnect_transient),
            Boolean.parseBoolean(resources
                    .getString(R.string.default_reconnect_transient)));
  }

  public boolean reconnectLoss() {
    return preferences.getBoolean(resources
            .getString(R.string.key_reconnect_loss), Boolean.parseBoolean(resources
            .getString(R.string.default_reconnect_loss)));
  }

    public boolean logTraffic() {
        return preferences.getBoolean(resources
                .getString(R.string.key_log_traffic), Boolean.parseBoolean(resources
                .getString(R.string.default_log_traffic)));
    }

    public String getLogFile() {
        return preferences.getString(resources.getString(R.string.key_log_file),
                resources.getString(R.string.default_log_file));
    }
  /**
   * Get the conversation history size.
   *
   * @return The conversation history size
   */
  public int getHistorySize() {
    try {
      return Integer.parseInt(preferences.getString(
              resources.getString(R.string.key_history_size),
              resources.getString(R.string.default_history_size)));
    } catch ( NumberFormatException e ) {
      return Integer.parseInt(resources
              .getString(R.string.default_history_size));
    }
  }

  public boolean getUseDarkColors() {
    return preferences.getBoolean(resources
            .getString(R.string.key_colorscheme_dark), Boolean
            .parseBoolean(resources.getString(R.string.default_colorscheme_dark)));
  }

  public int getLastRunVersion() {
    return preferences.getInt("LAST_RUN_VERSION", 0);
  }

  public void resetLastRunVersion() {
    preferences.edit().putInt("LAST_RUN_VERSION", 0).commit();
  }

  public void updateLastRunVersion() {
    preferences.edit().putInt("LAST_RUN_VERSION", currentRelease).commit();
  }

  public int getCurrentVersion() {
    return currentRelease;
  }

  private String getRandomNick(int len) {
    char[] valid_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"
            .toCharArray();
    String ret = "";
    for( int i = 0; i < len; i++ ) {
      ret += valid_chars[(int)(Math.random() * valid_chars.length)];
    }

    return ret;
  }

  public String getDefaultNick() {

    String def = preferences.getString(resources.getString(R.string.key_default_nickname), null);
    if( def == null ) {
      def = "SimpleIRC_" + getRandomNick(5);
      preferences.edit().putString(resources.getString(R.string.key_default_nickname), def).apply();
    }
    return def;
  }

  public String getDefaultUsername() {
    return preferences.getString(resources.getString(R.string.key_default_username), resources.getString(R.string.default_default_username));
  }

  public void setDefaultUsername(String name) {
    preferences.edit().putString(resources.getString(R.string.key_default_username), name).apply();
  }

  public String getDefaultRealname() {
    return preferences.getString(resources.getString(R.string.key_default_realname), resources.getString(R.string.default_default_realname));
  }

  public void setDefaultRealname(String name) {
    preferences.edit().putString(resources.getString(R.string.key_default_realname), name).apply();
  }

  public int getHighlightLEDColor() {
    return preferences.getInt(resources.getString(R.string.key_led_color), 0xFFFFFFFF);
  }


  public MessageRenderParams getRenderParams() {
    MessageRenderParams params = new MessageRenderParams();
    params.colorScheme = this.getColorScheme();
    params.icons = this.showIcons();
    params.mircColors = this.showMircColors();
    params.messageColors = this.showMessageColors();
    params.smileys = this.showGraphicalSmilies();
    params.nickColors = this.showColorsNick();
    params.timestamps = this.showTimestamp();
    params.useDarkScheme = this.getUseDarkColors();
    params.timestamp24Hour = this.use24hFormat();
    params.timestampSeconds = this.includeSeconds();

    return params;
  }

  public boolean tintActionbar() {
      return preferences.getBoolean(
              resources.getString(R.string.key_tint_actionbar),
              Boolean.parseBoolean(resources.getString(R.string.default_tint_actionbar))
      );
  }
}
