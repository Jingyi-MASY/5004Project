package cs5004.animator.view;

/**
 * this Enum class ViewOption represents all the ViewOptions. Any other view options besides this
 * enum should be considered invalid.
 */
public enum ViewOption {
  TEXT("text"),
  VISUAL("visual"),
  PLAYBACK("playback"),
  SVG("svg");
  private final String txt;

  /**
   * This constructor creates view options.
   *
   * @param txt passed in text indicating which view needs to be created
   */
  ViewOption(String txt) {
    this.txt = txt;
  }

  /**
   * this methods returns a boolean value that whether if it is view.
   * @param string  String type.
   * @return  boolean value, true if it is view, false otherwise.
   */
  public boolean isView(String string) {
    return this.txt.equalsIgnoreCase(string);
  }
}
