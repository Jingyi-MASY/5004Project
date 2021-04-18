package cs5004.animator.model;

/**
 * This Movement interface includes all operations that applies to all movements.
 */
public interface Movement {

  /**
   * This method display a movement in text description format.It describes the movement type, the
   * original status and the new status in string format.
   *
   * @return a string describes this movement, String type.
   */
  String display();

  /**
   * This method gets and returns the start time of this movement, which is when this movement is
   * just about to start.
   *
   * @return start time of this movement, int type.
   */
  int getStartTime();

  /**
   * This method gets and returns the end time of this movement, which is when this movement is
   * just about to start.
   *
   * @return end time of this movement, int type.
   */
  int getEndTime();

  String getMotionType();
}
