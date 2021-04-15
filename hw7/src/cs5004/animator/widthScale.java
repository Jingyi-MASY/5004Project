package cs5004.animator;
/**
 * This class represents a Scale movement that implements the movement interface, this class
 * includes all the fields and methods that are specific to a a Scale movement. The fields of this
 * class includes a shape, a scaling factor, a start time, and an end time.
 */
public class widthScale implements Movement {
  IShape shape;
  int oldPara;
  int newPara;
  int startTime;
  int endTime;

  /**
   * this constructor initializes this Scale movement based on the following parameters.
   *
   * @param shape         shape that would conduct this movement, IShape type.
   * @param oldPara       the initial length of the parameter.
   * @param newPara       the target length of the parameter.
   * @param startTime     the start time of this movement, int type.
   * @param endTime       the end time of this movement, int type.
   */
  public widthScale(IShape shape, int oldPara, int newPara, int startTime, int endTime)
          throws IllegalArgumentException {
    this.shape = shape;
    this.oldPara = oldPara;
    this.newPara = newPara;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String display() {
    return null;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }
}
