package cs5004.animator.controller;

import java.util.LinkedList;
import java.util.List;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;

/**
 * A mock of model IAnimation.
 */
public class MockModel implements IAnimation {
  private StringBuilder log;
  private final int uniqueCode;

  public MockModel(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void setBounds(int i, int i1, int i2, int i3) {
    log.append("Input: ").append(i).append(" ").append(i1).append(" ").append(i3);
  }

  @Override
  public int[] getBounds() {
    return new int[]{uniqueCode};
  }

  @Override
  public List<IShape> getListOfShapes() {
    return new LinkedList<>();
  }

  @Override
  public void declareShape(String s, String s1) {
    log.append("Input: ").append(s).append(" ").append(s1);
  }

  @Override
  public void addMotion(String s, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7,
                        int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
    log.append("Input: ").append(s).append(" ").append(i).append(" ").append(i1).append(" ")
            .append(i3).append(" ").append(i4).append(" ").append(i5).append(" ")
            .append(i6).append(" ").append(i6).append(" ").append(i8).append(" ")
            .append(i9).append(" ").append(i10).append(" ").append(i11).append(" ")
            .append(i12).append(" ").append(i13).append(" ").append(i14).append(" ")
            .append(i15);
  }

  public int getUniqueCode() {
    return uniqueCode;
  }
}