package tk.jordynsmediagroup.simpleirc.logging;
/*
 * This class is for Logging
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import tk.jordynsmediagroup.simpleirc.App;
import tk.jordynsmediagroup.simpleirc.R;
import tk.jordynsmediagroup.simpleirc.activity.ConversationActivity;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Message;
import tk.jordynsmediagroup.simpleirc.model.MessageRenderParams;

public class Logging {
    // Logging TAG
    private static String TAG = "SimpleIRC/Logging";
    // Current conversation in pager
    private static Conversation conversation = ConversationActivity.pagerAdapter.getItem(ConversationActivity.pager.getCurrentItem());
    // Static function to add a line to the log
    public static void addLine(String line) {
        try {
            /* Try to write to the log here
             The directory to write to */
            String outDir = App.getSettings().getLogFile();
            // The format to write the log file in
            DateFormat filedf = DateFormat.getDateInstance();
            String filedate = filedf.format(Calendar.getInstance().getTime());
            // Open the log
            File file = new File(outDir + filedate + ".log");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            // Date to write to the log
            DateFormat df = DateFormat.getDateTimeInstance();
            String date = df.format(Calendar.getInstance().getTime());
            // String to write to the log
            String entry = "[" + date + "]" + " " + line;
            // Write to the log and then flush and close it
            writer.write(entry);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            // This needs to be fixed
            return;
        } catch (IOException e) {
            // This needs to be fixed
        }
    }
    // Static function to setup logging
    public static void setupLog() {
        File logFolder = new File(App.getSettings().getLogFile());
        // Make the directory here (if it doesn't exist already)
        if (!logFolder.exists()) {
            throwError("Log directory doesn't exist, creating...");
            try {
                if ( ! logFolder.mkdirs() ) {
                    // If mkdir fails, disconnect
                    throwError("Failed to make log directory...");
                }
            } catch (SecurityException e) {
                // Throw error upon a Security Exception
                throwError("Security execption: " + e);
                // This needs to be fixed
            }
        }
    }
    // A function to throw a error as a pager warning
    private static void throwError(String error) {
        Message message = new Message(error);
        message.setColor(Message.MessageColor.TOPIC);
        message.setIcon(R.drawable.warning);
        conversation.addMessage(message);
    }

    public static MessageRenderParams getTmpParams() {
        // Set the message paramaters for logging
        Message viewedMessage = null;
        MessageRenderParams tmpParams = App.getSettings().getRenderParams();
        tmpParams.messageColors = false;
        tmpParams.colorScheme = "default";
        tmpParams.smileys = false;
        tmpParams.icons = false;
        tmpParams.messageColors = false;
        tmpParams.useDarkScheme = true;
        return tmpParams;
    }
}
