package tk.jordynsmediagroup.simpleirc.listener;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;

import tk.jordynsmediagroup.simpleirc.activity.ConversationActivity;

/**
 * OnClickListener for the Speech Recognition Button
 */
public class SpeechClickListener implements OnClickListener {
  private final Activity activity;

  /**
   * Create a new listener for speech button
   *
   * @param activity
   * @param input
   */
  public SpeechClickListener(Activity activity) {
    this.activity = activity;
  }

  /**
   * On Click on speech button
   */
  @Override
  public void onClick(View v) {
    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "");

    activity.startActivityForResult(intent, ConversationActivity.REQUEST_CODE_SPEECH);
  }
}
