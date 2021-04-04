public enum ShapeType {
  RECTANGLE("rectangle"),
  OVAL("oval"),
  TRIANGLE("triangle"),
  CIRCLE("circle");

  private final String txt;

  ShapeType(String txt) {
    this.txt = txt;
  }

  //Need change
  String getTxt() {
    return txt;
  }

}
