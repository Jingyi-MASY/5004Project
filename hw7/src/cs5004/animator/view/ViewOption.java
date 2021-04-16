package cs5004.animator.view;

public enum ViewOption {
  TEXT("text"),
  VISUAL("visual"),
  SVG("svg");
  private final String txt;

  /**
   * This constructor creates view options
   *
   * @param txt passed in text indicating which view needs to be created
   */
  ViewOption(String txt) {
    this.txt = txt;
  }
  protected boolean isView(String string){
    return this.txt.equalsIgnoreCase(string);
  }
}
