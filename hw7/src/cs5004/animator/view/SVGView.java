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

/**
 * this SVGView class implements the IView interface and includes all the fields and operations
 * that belong to a SVGView. The fields of this SVG view includes an PrintStream out, and a speed.
 */
public class SVGView implements IView {
  private final PrintStream out;
  private final int speed; //integer ticks per second; ticks per 1000ms

  /**
   * This constructor creates a SVGView based on the passed in output and speed.
   * @param out   output of this view, PrintStream type.
   * @param speed the speed of this view, integer type.
   */
  public SVGView(PrintStream out, int speed) {
    if (speed <= 0) {
      throw new IllegalArgumentException("invalid speed");
    }
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
        //show each motion of shape x
        this.showMotion(x);
        //shape footer
        this.showShapeFooter(x);
      }
    }
    out.append("\n</svg>");
  }

  /*
   * This function shows the header of the SVG file and the bounds attributes of the animation.
   * @param animation animation to show, IAnimation type.
   */
  private void showHeader(IAnimation animation) {
    //the overall svg width is w and height is h.
    //By default anything drawn between (0,0) and (width,height) will be visible
    int w = animation.getBounds()[2] + animation.getBounds()[0];
    int h = animation.getBounds()[3] + animation.getBounds()[1];
    int vb1 = animation.getBounds()[0];
    int vb2 = animation.getBounds()[1];
    int vb3 = animation.getBounds()[2];
    int vb4 = animation.getBounds()[3];
    out.append("<svg width=\"").append(String.valueOf(w)).append("\" height=\"")
            .append(String.valueOf(h)).append("\" version=\"1.1\" viewBox=\"")
            .append(String.valueOf(vb1)).append(", ").append(String.valueOf(vb2)).append(", ")
            .append(String.valueOf(vb3)).append(", ").append(String.valueOf(vb4)).append("\" ")
            .append("xmlns=\"http://www.w3.org/2000/svg\">");
    out.append("\n");
  }

  /*
   * private helper function to help display in showAll method.
   */
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
      out.append("<rect id=\"").append(id).append("\" x=\"").append(String.valueOf(x))
              .append("\" y=\"").append(String.valueOf(y)).append("\" width=\"")
              .append(String.valueOf(width)).append("\" height=\"").append(String.valueOf(height))
              .append("\" fill=\"").append("rgb(").append(String.valueOf(r)).append(",")
              .append(String.valueOf(g)).append(",").append(String.valueOf(b))
              .append(")\" visibility=\"visible\" >\n");
    } else { //when the shape type if ellipse
      out.append("<ellipse id=\"").append(id).append("\" cx=\"").append(String.valueOf(x))
              .append("\" cy=\"").append(String.valueOf(y)).append("\" rx=\"")
              .append(String.valueOf(width / 2)).append("\" ry=\"")
              .append(String.valueOf(height / 2))
              .append("\" fill=\"").append("rgb(").append(String.valueOf(r)).append(",")
              .append(String.valueOf(g)).append(",").append(String.valueOf(b))
              .append(")\" visibility=\"visible\" >\n");
    }
  }

  /*
   * private helper function to help display in showAll method.
   */
  private void showMotion(IShape shape) {
    if (shape.getMovementList() != null) {
      LinkedList<Movement> motionList = new LinkedList<>(shape.getMovementList());
      motionList.sort(Comparator.comparingInt(Movement::getEndTime));
      for (Movement movement : motionList) {
        if (movement instanceof Move) {
          showMove(shape, (Move) movement);
        } else if (movement instanceof ColorChange) {
          showColorChange((ColorChange) movement);
        } else if (movement instanceof WidthScale) {
          showWidthScale(shape, (WidthScale) movement);
        } else if (movement instanceof HeightScale) {
          showHeightScale(shape, (HeightScale) movement);
        } else {
          throw new IllegalArgumentException("motion type error");
        }
      }
    }
  }

  /*
   * private helper function to help display in showAll method.
   */
  private void showShapeFooter(IShape shape) {
    out.append("<animate attributeType=\"xml\" begin=\"")
            .append(String.valueOf(shape.getDisappearTime() * 1000 / speed))
            .append("ms\" dur=\"1ms\" attributeName=\"visibility\" from=\"visible")
            .append("\" to=\"hidden\" fill=\"freeze\" />\n");
    if (shape.getType() == ShapeType.RECTANGLE) {
      out.append("</rect>\n");
    } else if (shape.getType() == ShapeType.ELLIPSE) {
      out.append("</ellipse>\n");
    }
  }

  /*
   * private helper function to help display in showAll method.
   */
  private void showMove(IShape shape, Move motion) {
    int begin = motion.getStartTime() * 1000 / speed; //in ms (1s = 1000ms)
    int dur = ((motion.getEndTime()) - motion.getStartTime()) * 1000 / speed; //in ms (1s = 1000ms)
    int fromX = motion.getInitial().getX();
    int fromY = motion.getInitial().getY();
    int toX = motion.getTarget().getX();
    int toY = motion.getTarget().getY();
    if (shape.getType() == ShapeType.ELLIPSE) {
      out.append("<animate attributeType=\"xml\" begin=\"").append(String.valueOf(begin))
              .append("ms\" ").append("dur=\"").append(String.valueOf(dur))
              .append("ms\" attributeName=\"cx\" from=\"").append(String.valueOf(fromX))
              .append("\" to=\"").append(String.valueOf(toX))
              .append("\" fill=\"freeze\" />\n");
      out.append("<animate attributeType=\"xml\" begin=\"").append(String.valueOf(begin))
              .append("ms\" ").append("dur=\"").append(String.valueOf(dur))
              .append("ms\" attributeName=\"cy\" from=\"").append(String.valueOf(fromY))
              .append("\" to=\"").append(String.valueOf(toY))
              .append("\" fill=\"freeze\" />\n");
    } else { //shape is a rectangle
      out.append("<animate attributeType=\"xml\" begin=\"").append(String.valueOf(begin))
              .append("ms\" ").append("dur=\"").append(String.valueOf(dur))
              .append("ms\" attributeName=\"x\" from=\"").append(String.valueOf(fromX))
              .append("\" to=\"").append(String.valueOf(toX)).append("\" fill=\"freeze\" />\n");
      out.append("<animate attributeType=\"xml\" begin=\"").append(String.valueOf(begin))
              .append("ms\" ").append("dur=\"").append(String.valueOf(dur))
              .append("ms\" attributeName=\"y\" from=\"").append(String.valueOf(fromY))
              .append("\" to=\"").append(String.valueOf(toY)).append("\" fill=\"freeze\" />\n");
    }
  }

  /*
   * private helper function to help display in showAll method.
   */
  private void showColorChange(ColorChange motion) {
    int begin = motion.getStartTime() * 1000 / speed; //in ms (1s = 1000ms)
    int dur = ((motion.getEndTime()) - motion.getStartTime()) * 1000 / speed; //in ms (1s = 1000ms)
    int r1 = motion.getInitial().getRed();
    int g1 = motion.getInitial().getGreen();
    int b1 = motion.getInitial().getBlue();
    int r2 = motion.getTargetColor().getRed();
    int g2 = motion.getTargetColor().getGreen();
    int b2 = motion.getTargetColor().getBlue();
    out.append("<animate attributeType=\"xml\" begin=\"").append(String.valueOf(begin))
            .append("ms\" dur").append("=\"").append(String.valueOf(dur))
            .append("ms\" attributeName=\"fill\" from=\"rgb(").append(String.valueOf(r1))
            .append(",").append(String.valueOf(g1)).append(",").append(String.valueOf(b1))
            .append(")\" to=\"rgb(").append(String.valueOf(r2)).append(",")
            .append(String.valueOf(g2)).append(",").append(String.valueOf(b2))
            .append(")\" ").append("fill=\"freeze\" />\n");
  }

  /*
   * private helper function to help display in showAll method.
   */
  private void showWidthScale(IShape shape, WidthScale motion) {
    int begin = motion.getStartTime() * 1000 / speed; //in ms (1s = 1000ms)
    int dur = ((motion.getEndTime()) - motion.getStartTime()) * 1000 / speed; //in ms (1s = 1000ms)
    int from = motion.getOldPara();
    int to = motion.getNewPara();
    if (shape.getType() == ShapeType.RECTANGLE) {
      out.append("<animate attributeType=\"xml\" begin=\"").append(String.valueOf(begin))
              .append("ms\" dur=\"").append(String.valueOf(dur)).append("ms\" ")
              .append("attributeName=\"width\" from=\"").append(String.valueOf(from))
              .append("\" to=\"").append(String.valueOf(to)).append("\" fill=\"freeze\" />\n");
    } else { //shape is an ellipse
      out.append("<animate attributeType=\"xml\" begin=\"").append(String.valueOf(begin))
              .append("ms\" dur=\"").append(String.valueOf(dur)).append("ms\" ")
              .append("attributeName=\"rx\" from=\"").append(String.valueOf(from / 2))
              .append("\" to=\"").append(String.valueOf(to / 2)).append("\" fill=\"freeze\" />\n");
    }
  }

  /*
   * private helper function to help display in showAll method.
   */
  private void showHeightScale(IShape shape, HeightScale motion) {
    int begin = motion.getStartTime() * 1000 / speed; //in ms (1s = 1000ms)
    int dur = ((motion.getEndTime()) - motion.getStartTime()) * 1000 / speed; //in ms (1s = 1000ms)
    int from = motion.getOldPara();
    int to = motion.getNewPara();
    if (shape.getType() == ShapeType.RECTANGLE) {
      out.append("<animate attributeType=\"xml\" begin=\"").append(String.valueOf(begin))
              .append("ms\" dur=\"").append(String.valueOf(dur)).append("ms\" ")
              .append("attributeName=\"height\" from=\"").append(String.valueOf(from))
              .append("\" to=\"").append(String.valueOf(to)).append("\" fill=\"freeze\" />\n");
    } else { //shape is an ellipse
      out.append("<animate attributeType=\"xml\" begin=\"").append(String.valueOf(begin))
              .append("ms\" dur=\"").append(String.valueOf(dur)).append("ms\" ")
              .append("attributeName=\"ry\" from=\"").append(String.valueOf(from / 2))
              .append("\" to=\"").append(String.valueOf(to / 2)).append("\" fill=\"freeze\" />\n");
    }
  }

  @Override
  public void showOneShape(IAnimation animation, String shapeName) {
    if (animation == null || shapeName == null) {
      throw new IllegalArgumentException("invalid input");
    }
    boolean found = false;
    for (IShape x : animation.getListOfShapes()) {
      if (x.getName().equals(shapeName)) {
        found = true;
      }
    }
    if (!found) {
      throw new IllegalArgumentException("shape not found");
    }
    this.showHeader(animation);
    for (IShape x : animation.getListOfShapes()) {
      if (x.getName().equals(shapeName) && (x.getMovementList() != null)) {
        //show shape header
        this.showShapeHeader(x);
        //show each motion of shape x
        this.showMotion(x);
        //shape footer
        this.showShapeFooter(x);
        break;
      }
    }
    out.append("\n</svg>");
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }
}
