package tk.jordynsmediagroup.simpleirc.logging;

import android.util.Log;
import tk.jordynsmediagroup.simpleirc.App;


public class DebugLogging {
    // A Log.* wrapper that checks to see if debugging is turned on in the settings before logging.
    public static void AddDebugLine(String tag, String line) {
        if( App.getSettings().debugTraffic() ) {
            // If we can debug traffic, do it here and not repeat code and if's all over the place.
            Log.d(tag, line);
        }
    }
}
