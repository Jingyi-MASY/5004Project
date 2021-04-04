public class Point2D {
  int x;
  int y;

  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    String str = "(" + this.x + ", " + this.y + ")";
    return str;
  }
}
