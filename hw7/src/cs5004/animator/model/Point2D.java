package cs5004.animator.model;

/**
 * this class represents a Point2D class and includes all fields and methods that are specific to
 * this class. The fields of this class includes an x and an y indicating its position on x axis and
 * position on y axis.
 */
public class Point2D {
  protected int x;
  protected int y;

  /**
   * This constructor initializes a position based on its passed in parameters x and y.
   *
   * @param x the position on x-axis, int type.
   * @param y the position on y-axis, int type.
   */
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * This gets are returns the position on x axis of this position.
   *
   * @return the position on x axis of this position, int type.
   */
  public int getX() {
    return x;
  }

  /**
   * This gets are returns the position on y axis of this position.
   *
   * @return the position on y axis of this position, int type.
   */
  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object another) {
    if (this == another) {
      return true;
    }
    if (!(another instanceof Point2D)) {
      return false;
    }
    Point2D other = (Point2D) another;
    return this.getY() == other.getY() && this.getX() == other.getX();
  }

  @Override
  public int hashCode() {
    return 0;
  }

  /**
   * This gets and returns a String representation of specific desired format to represent this.
   *
   * @return a string representation of this position, String type.
   */
  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
