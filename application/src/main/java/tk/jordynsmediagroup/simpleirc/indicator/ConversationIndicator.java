package tk.jordynsmediagroup.simpleirc.indicator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import tk.jordynsmediagroup.simpleirc.adapter.ConversationPagerAdapter;
import tk.jordynsmediagroup.simpleirc.indicator.ConversationTitlePageIndicator.IndicatorStyle;
import tk.jordynsmediagroup.simpleirc.irc.IRCService;
import tk.jordynsmediagroup.simpleirc.model.Conversation;
import tk.jordynsmediagroup.simpleirc.model.Server;
import tk.jordynsmediagroup.simpleirc.utils.DisplayUtils;

/**
 * A ConversationIndicator is a group of a ConversationTitlePageIndicator in the
 * center and two drawables left and right to indicate the state of the items
 * that are not displayed by the ConversationTitlePageIndicator.
 */
public class ConversationIndicator extends FrameLayout implements OnPageChangeListener {
  private Server server;
  private ViewPager pager;

  private View leftIndicatorView;
  private View rightIndicatorView;

  private ConversationTitlePageIndicator titleIndicator;
  private ConversationStateProvider stateProvider;

  private OnPageChangeListener pageChangeListener;

  /**
   * Create a new {@link ConversationIndicator} instance for the given context.
   *
   * @param context
   */
  public ConversationIndicator(Context context) {
    super(context);

    init();
  }


  /**
   * Create a new {@link ConversationIndicator} instance for the given context
   * and attribute set.
   *
   * @param context
   * @param attrs
   */
  public ConversationIndicator(Context context, AttributeSet attrs) {
    super(context, attrs);

    init();
  }

  /**
   * Initialize the indicator view.
   */
  public void init() {
    titleIndicator = new ConversationTitlePageIndicator(getContext());
    titleIndicator.setLayoutParams(
        new LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        )
    );

    int indicatorWidth = DisplayUtils.convertToPixels(getContext(), 5);
    int indicatorHeight = DisplayUtils.convertToPixels(getContext(), 5);

    leftIndicatorView = new View(getContext());
    leftIndicatorView.setLayoutParams(
        new LayoutParams(
            indicatorWidth,
            indicatorHeight,
            Gravity.LEFT | Gravity.BOTTOM
        )
    );
    leftIndicatorView.setVisibility(View.INVISIBLE);

    rightIndicatorView = new View(getContext());
    rightIndicatorView.setLayoutParams(
        new LayoutParams(
            indicatorWidth,
            indicatorHeight,
            Gravity.RIGHT | Gravity.BOTTOM
        )
    );
    rightIndicatorView.setVisibility(View.INVISIBLE);

    addView(leftIndicatorView);
    addView(rightIndicatorView);
    addView(titleIndicator);
  }

  /**
   * Set the {@link Server} this indicator is used for.
   *
   * @param server
   */
  public void setServer(Server server) {
    this.server = server;
  }

  /**
   * Set typeface of title indicator.
   *
   * @param typeface
   */
  public void setTypeface(Typeface typeface) {
    titleIndicator.setTypeface(typeface);
  }

  /**
   * Set the {@link ViewPager} this indicator is used for.
   *
   * @param pager
   */
  public void setViewPager(ViewPager pager) {
    this.pager = pager;

    titleIndicator.setViewPager(pager);
    titleIndicator.setOnPageChangeListener(this);

    stateProvider = (ConversationStateProvider)pager.getAdapter();
  }

  /**
   * Set the color of the footer line.
   *
   * @param footerColor
   */
  public void setFooterColor(int footerColor) {
    titleIndicator.setFooterColor(footerColor);
  }

  /**
   * Set the height of the footer line.
   *
   * @param footerLineHeight
   */
  public void setFooterLineHeight(float footerLineHeight) {
    titleIndicator.setFooterLineHeight(footerLineHeight);
  }

  /**
   * Set the height of the footer indicator.
   *
   * @param footerTriangleHeight
   */
  public void setFooterIndicatorHeight(float footerTriangleHeight) {
    titleIndicator.setFooterIndicatorHeight(footerTriangleHeight);
  }

  /**
   * Set the style of the footer indicator.
   *
   * @param indicatorStyle
   */
  public void setFooterIndicatorStyle(IndicatorStyle indicatorStyle) {
    titleIndicator.setFooterIndicatorStyle(indicatorStyle);
  }

  /**
   * Set wether selected items should be displayed as bold text.
   *
   * @param selectedBold
   */
  public void setSelectedBold(boolean selectedBold) {
    titleIndicator.setSelectedBold(selectedBold);
  }

  /**
   * Set the text color of a selected item.
   *
   * @param selectedColor
   */
  public void setSelectedColor(int selectedColor) {
    titleIndicator.setSelectedColor(selectedColor);
  }

  /**
   * Set the text size for the title indicator.
   *
   * @param textSize
   */
  public void setTextSize(float textSize) {
    titleIndicator.setTextSize(textSize);
  }

  /**
   * Set the OnPageChangeListener that resides inside (which will pass events down)
   *
   * @param listener The listener to pass events down to.
   */
  public void setOnPageChangeListener(OnPageChangeListener listener) {
    pageChangeListener = listener;
  }

  /**
   * On page selected: Update states of the indicators.
   */
  @Override
  public void onPageSelected(int page) {
    updateStateColors();
    if( pageChangeListener != null ) {
      pageChangeListener.onPageSelected(page);
    }
  }

  /**
   * Update the colors of the state indicators.
   */
  public void updateStateColors() {
    int page = pager.getCurrentItem();

    ConversationPagerAdapter adapter = (ConversationPagerAdapter)pager.getAdapter();
    Conversation conversation = adapter.getItem(page);

    Conversation previousConversation = server.getConversation(server.getSelectedConversation());
    if( previousConversation != null ) {
      previousConversation.setStatus(Conversation.STATUS_DEFAULT);
    }

    if( conversation.getNewMentions() > 0 ) {
      Context context = pager.getContext();

      Intent intent = new Intent(context, IRCService.class);
      intent.setAction(IRCService.ACTION_ACK_NEW_MENTIONS);
      intent.putExtra(IRCService.EXTRA_ACK_SERVERID, server.getId());
      intent.putExtra(IRCService.EXTRA_ACK_CONVTITLE, conversation.getName());
      context.startService(intent);
    }

    conversation.setStatus(Conversation.STATUS_SELECTED);
    server.setSelectedConversation(conversation.getName());

    if( page - 2 >= 0 ) {
      int color = stateProvider.getColorForLowerThan(page - 1);
      leftIndicatorView.setBackgroundColor(color);
      leftIndicatorView.setVisibility(
          stateProvider.isLowerSpecial(page) ? View.VISIBLE : View.INVISIBLE
      );
    } else {
      leftIndicatorView.setVisibility(View.INVISIBLE);
    }

    if( page + 2 < adapter.getCount() ) {
      int color = stateProvider.getColorForGreaterThan(page + 1);

      rightIndicatorView.setBackgroundColor(color);
      rightIndicatorView.setVisibility(
          stateProvider.isGreaterSpecial(page) ? View.VISIBLE : View.INVISIBLE
      );
    } else {
      rightIndicatorView.setVisibility(View.INVISIBLE);
    }

    titleIndicator.invalidate();
  }

  /**
   * On scroll state of page changed.
   */
  @Override
  public void onPageScrollStateChanged(int page) {
    if( pageChangeListener != null ) {
      pageChangeListener.onPageScrollStateChanged(page);
    }
  }

  /**
   * On page scrolled.
   */
  @Override
  public void onPageScrolled(int arg0, float arg1, int arg2) {
    if( pageChangeListener != null ) {
      pageChangeListener.onPageScrolled(arg0, arg1, arg2);
    }
  }
}
