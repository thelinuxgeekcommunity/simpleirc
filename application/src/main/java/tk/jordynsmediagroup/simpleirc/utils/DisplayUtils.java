package tk.jordynsmediagroup.simpleirc.utils;

import android.content.Context;

/**
 * Helper class for methods regarding the display of the current device.
 */
public class DisplayUtils {
  private static float density = -1;

  /**
   * Convert the given density-independent pixels into real pixels for the
   * display of the device.
   *
   * @param dp
   * @return
   */
  public static int convertToPixels(Context context, int dp) {
    float density = getScreenDensity(context);

    return (int)(dp * density + 0.5f);
  }

  /**
   * Get the density of the display of the device.
   *
   * @param context
   * @return
   */
  public static float getScreenDensity(Context context) {
    if( density == -1 ) {
      density = context.getResources().getDisplayMetrics().density;
    }

    return density;
  }
}
