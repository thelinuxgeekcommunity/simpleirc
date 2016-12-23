package tk.jordynsmediagroup.simpleirc.indicator;


/**
 * Interface for a {@link ConversationStateProvider} that provides the apropriate
 * state color for a position in the pager.
 */
public interface ConversationStateProvider {
  /**
   * Get the state color for all positions lower than the given position.
   *
   * @param position
   * @return
   */
  public int getColorForLowerThan(int position);

  public Boolean isLowerSpecial(int position);

  public Boolean isGreaterSpecial(int position);

  /**
   * Get the state color for the given position.
   *
   * @param position
   * @return
   */
  public int getColorAt(int position);

  /**
   * Get the state color for all positions greater than the given position.
   *
   * @param position
   * @return
   */
  public int getColorForGreaterThan(int position);
}
