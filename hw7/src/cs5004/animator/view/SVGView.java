package cs5004.animator.view;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.LinkedList;

import cs5004.animator.model.ColorChange;
import cs5004.animator.model.HeightScale;
import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Move;
import cs5004.animator.model.Movement;
import cs5004.animator.model.ShapeType;
import cs5004.animator.model.WidthScale;

public class SVGView implements IView{
  private PrintStream out;
  private int speed; //integer ticks per second; ticks per 1000ms

  public SVGView(PrintStream out, int speed) {
    if (out == null) {
      out = System.out;
    }
    this.out = out;
    this.speed = speed;
  }

  @Override
  public void showAll(IAnimation animation) {
    this.showHeader(animation);
    for (IShape x : animation.getListOfShapes()) {
      if (x.getMovementList() != null) {
        //show shape header
        this.showShapeHeader(x);
        //todo: show each motion of shape x
        this.showMotion(x);
        //shape footer
        this.showShapeFooter(x);
      }
    }
    out.append("\n</svg>");
  }

  /**
   * This function shows the header of the SVG file and the bounds attributes of the animation.
   * @param animation animation to show, IAnimation type.
   */
  private void showHeader(IAnimation animation) {
    //the overall svg width is w and height is h.
    //By default anything drawn between (0,0) and (width,height) will be visible
    int w = animation.getBounds()[2];
    int h = animation.getBounds()[3];
    out.append(("<svg width=\"%d\" height=\"%d\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">").formatted(w, h));
    out.append("\n");
  }

  private void showShapeHeader(IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("shape cannot be null");
    }
    String id = shape.getName();
    int x = shape.getPositionAt(shape.getAppearTime()).getX();
    int y = shape.getPositionAt(shape.getAppearTime()).getY();
    int width = shape.getPara1At(shape.getAppearTime());
    int height = shape.getPara2At(shape.getAppearTime());
    int r = shape.getColorAt(shape.getAppearTime()).getRed();
    int g = shape.getColorAt(shape.getAppearTime()).getGreen();
    int b = shape.getColorAt(shape.getAppearTime()).getBlue();

    if (shape.getType() == ShapeType.RECTANGLE) {
      out.append(("<rect id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\""
              + "rgb(%d,%d,%d)\" visibility=\"visible\" >\n")
              .formatted(id, x, y, width, height, r, g, b));
    } else { //when the shape type if ellipse
      out.append(("<ellipse id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" fill=\""
              + "rgb(%d,%d,%d)\" visibility=\"visible\" >\n")
              .formatted(id, x, y, width/2, height/2, r, g, b));
    }
  }

  private void showMotion(IShape shape) {
    LinkedList<Movement> motionList = new LinkedList<>();
    if (shape.getMovementList() != null) {
        motionList.addAll(shape.getMovementList());
        motionList.sort(Comparator.comparingInt(Movement::getEndTime));
        for (int i = 0; i < motionList.size(); i++) {
          //todo: initiate all variables
          if (motionList.get(i) instanceof Move) {
            showMove(shape, (Move) motionList.get(i));
          } else if (motionList.get(i) instanceof ColorChange) {
            showColorChange(shape, (ColorChange) motionList.get(i));
          } else if (motionList.get(i) instanceof WidthScale) {
            showWidthScale(shape, (WidthScale)motionList.get(i));
          } else if (motionList.get(i) instanceof HeightScale) {
            showHeightScale(shape, (HeightScale)motionList.get(i));
          } else {
            throw new IllegalArgumentException("motion type error");
          }
        }
    }
  }

  private void showShapeFooter(IShape shape) {
    if (shape.getType() == ShapeType.RECTANGLE) {
      out.append("</rect>\n");
    } else if (shape.getType() == ShapeType.ELLIPSE) {
      out.append("</ellipse>\n");
    }
  }

  private void showMove(IShape shape, Move motion) {
    int begin = motion.getStartTime() * 1000 / speed; //in ms (1s = 1000ms)
    int dur = ((motion.getEndTime()) - motion.getStartTime()) * 1000 / speed; //in ms (1s = 1000ms)
    int fromX = motion.getInitial().getX();
    int fromY = motion.getInitial().getY();
    int toX = motion.getTarget().getX();
    int toY = motion.getTarget().getY();
    if (shape.getType() == ShapeType.ELLIPSE) {
      out.append(("<animate attributeType=\"xml\" type= \"move\" begin=\"%dms\" "
              + "dur=\"%dms\" attributeName=\"cx\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n")
              .formatted(begin, dur, fromX, toX));
      out.append(("<animate attributeType=\"xml\" type= \"move\" begin=\"%dms\" "
              + "dur=\"%dms\" attributeName=\"cy\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n")
              .formatted(begin, dur, fromY, toY));
    } else {//shape is a rectangle
      out.append(("<animate attributeType=\"xml\" type= \"move\" begin=\"%dms\" "
              + "dur=\"%dms\" attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n")
              .formatted(begin, dur, fromX, toX));
      out.append(("<animate attributeType=\"xml\" type= \"move\" begin=\"%dms\" "
              + "dur=\"%dms\" attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n")
              .formatted(begin, dur, fromY, toY));
    }
  }

  private void showColorChange(IShape shape, ColorChange motion) {
    int begin = motion.getStartTime() * 1000 / speed; //in ms (1s = 1000ms)
    int dur = ((motion.getEndTime()) - motion.getStartTime()) * 1000 / speed; //in ms (1s = 1000ms)
    int r1 = motion.getInitial().getRed();
    int g1 = motion.getInitial().getGreen();
    int b1 = motion.getInitial().getBlue();
    int r2 = motion.getTargetColor().getRed();
    int g2 = motion.getTargetColor().getGreen();
    int b2 = motion.getTargetColor().getBlue();
    out.append(("<animate attributeType=\"xml\" type=\"color\" begin=\"%dms\" dur"
            + "=\"%dms\" attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />\n")
            .formatted(begin, dur, r1, g1, b1, r2, g2, b2));
  }

  private void showWidthScale(IShape shape, WidthScale motion) {
    int begin = motion.getStartTime() * 1000 / speed; //in ms (1s = 1000ms)
    int dur = ((motion.getEndTime()) - motion.getStartTime()) * 1000 / speed; //in ms (1s = 1000ms)
    int from = motion.getOldPara();
    int to = motion.getNewPara();
    if (shape.getType() == ShapeType.RECTANGLE) {
      out.append(("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" type=\"scale\" "
              + "attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n")
              .formatted(begin, dur, from, to));
    } else { //shape is an ellipse
      out.append(("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" type=\"scale\" "
              + "attributeName=\"rx\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n")
              .formatted(begin, dur, from/2, to/2));
    }
  }

  private void showHeightScale(IShape shape, HeightScale motion) {
    int begin = motion.getStartTime() * 1000 / speed; //in ms (1s = 1000ms)
    int dur = ((motion.getEndTime()) - motion.getStartTime()) * 1000 / speed; //in ms (1s = 1000ms)
    int from = motion.getOldPara();
    int to = motion.getNewPara();
    if (shape.getType() == ShapeType.RECTANGLE) {
      out.append(("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" type=\"scale\" "
              + "attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n")
              .formatted(begin, dur, from, to));
    } else { //shape is an ellipse
      out.append(("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" type=\"scale\" "
              + "attributeName=\"ry\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n")
              .formatted(begin, dur, from/2, to/2));
    }
  }

  @Override
  public void showOneShape(IAnimation animation, String shapeName) {
    this.showHeader(animation);
    for (IShape x : animation.getListOfShapes()) {
      if (x.getName().equals(shapeName) && (x.getMovementList() != null)) {
        //show shape header
        this.showShapeHeader(x);
        //todo: show each motion of shape x
        this.showMotion(x);
        //shape footer
        this.showShapeFooter(x);
      }
    }
    out.append("\n</svg>");
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public PrintStream getOutput() {
    return out;
  }
}
