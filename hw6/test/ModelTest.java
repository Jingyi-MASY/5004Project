import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * A junit test for model.
 */
public class ModelTest {
  private AnimationImpl anime;
  private IShape r1;

  @Before
  public void setup() {
    anime = new AnimationImpl();
    r1 = new Rectangle("r1", ShapeType.RECTANGLE, new Point2D(50, 50),
            Color.WHITE, 0, 20, 30, 20);
  }

  @Test
  public void testAddShape() {
    assertEquals("", anime.showAllShapes());
    anime.addShape(new Rectangle("r1", ShapeType.RECTANGLE, new Point2D(50, 50),
            Color.WHITE, 0, 20, 30, 20));
    try {
      anime.addShape(null);
      fail("Accepting Null");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      anime.addShape(new Oval("r1", ShapeType.OVAL, new Point2D(50, 50),
              Color.WHITE, 0, 20, 30, 20));
      fail("Accepting Null");
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals("Name: r1" + System.lineSeparator() +
            "Type: rectangle" + System.lineSeparator() +
            "Lower left corner: (50, 50), Width: 30, Height: 20"
            + "Color: java.awt.Color[r=255,g=255,b=255]"
            + System.lineSeparator() +
            "Appears at: t=0" + System.lineSeparator() +
            "Disappears at t=20" + System.lineSeparator(), anime.showAllShapes());
    anime.addShape(new Oval("o1", ShapeType.OVAL, new Point2D(15, -15),
            Color.WHITE, 5, 25, 4, 6));
    assertEquals( "Name: r1" + System.lineSeparator() +
            "Type: rectangle" + System.lineSeparator() +
            "Lower left corner: (50, 50), Width: 30, Height: 20 "
            + "Color: java.awt.Color[r=255,g=255,b=255]"
            + System.lineSeparator() +
            "Appears at: t=0" + System.lineSeparator() +
            "Disappears at t=20" + System.lineSeparator()
            + "Name: o1" + System.lineSeparator() +
            "Type: oval" + System.lineSeparator() +
            "Lower left corner: (15, -15), xRadius: 4, yRadius: 26 "
            + "Color: java.awt.Color[r=255,g=255,b=255]"
            + System.lineSeparator() +
            "Appears at: t=5" + System.lineSeparator() +
            "Disappears at t=25" + System.lineSeparator(), anime.showAllShapes());
    assertEquals("Name: o1" + System.lineSeparator() +
            "Type: oval" + System.lineSeparator() +
            "Lower left corner: (15, -15), xRadius: 4, yRadius: 26 "
            + "Color: java.awt.Color[r=255,g=255,b=255]"
            + System.lineSeparator() +
            "Appears at: t=23" + System.lineSeparator() +
            "Disappears at t=23" + System.lineSeparator(),anime.showStatusAt(23));
  }

  @Test
  public void testColor() {
    anime.addShape(r1);
    anime.addShape(new Rectangle("r2", ShapeType.RECTANGLE, new Point2D(30, 50),
            Color.RED, 0, 20, 30, 20));

    //Invalid name
    try {
      anime.changeColor("r0", Color.BLUE, 0, 10);
      fail("Accepting invalid name");
    } catch (IllegalArgumentException ignored) {
    }
    //Invalid time
    try {
      anime.changeColor("r1", Color.BLUE, 10, 40);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }
    //Invalid name
    try {
      anime.changeColor("r1", null, 0, 10);
      fail("Accepting null color");
    } catch (IllegalArgumentException ignored) {
    }
    anime.changeColor("r1", Color.BLUE, 10, 20);
    assertEquals(Color.BLUE, r1.getColorAt(10));
  }

  @Test
  public void testMove() {
    anime.addShape(r1);
    anime.move("r1", new Point2D(30, 30), 5, 10);
    anime.addShape(new Oval("o1", ShapeType.OVAL, new Point2D(15, -15),
            Color.WHITE, 0, 40, 4, 6));
    assertEquals("Name: r1" + System.lineSeparator() +
            "Type: rectangle" + System.lineSeparator() +
            "Lower left corner: 30, 30), Width: 30, Height: 20"
            + "Color: java.awt.Color[r=255,g=255,b=255]"
            + System.lineSeparator() +
            "Appears at: t=10" + System.lineSeparator() +
            "Disappears at t=10" + System.lineSeparator()
            + "Name: o1" + System.lineSeparator() +
            "Type: oval" + System.lineSeparator() +
            "Lower left corner: (15, -15), xRadius: 4, yRadius: 26 "
            + "Color: java.awt.Color[r=255,g=255,b=255]"
            + System.lineSeparator() +
            "Appears at: t=10" + System.lineSeparator() +
            "Disappears at t=10" + System.lineSeparator(),anime.showStatusAt(10));

    anime.move("o1", new Point2D(70,70), 20, 25);
    anime.move("o1", new Point2D(-30,30), 0, 15);
    assertEquals("Name: r1" + System.lineSeparator() +
            "Type: rectangle" + System.lineSeparator() +
            "Lower left corner: 30, 30), Width: 30, Height: 20"
            + "Color: java.awt.Color[r=255,g=255,b=255]"
            + System.lineSeparator() +
            "Appears at: t=10" + System.lineSeparator() +
            "Disappears at t=10" + System.lineSeparator()
            + "Name: o1" + System.lineSeparator() +
            "Type: oval" + System.lineSeparator() +
            "Lower left corner: (-15, 15), xRadius: 4, yRadius: 26 "
            + "Color: java.awt.Color[r=255,g=255,b=255]"
            + System.lineSeparator() +
            "Appears at: t=10" + System.lineSeparator() +
            "Disappears at t=10" + System.lineSeparator(),anime.showStatusAt(10));
    try {
      anime.move("r1", new Point2D(60, 60), 5, 10);
      fail("Movement conflict");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      anime.move("r1", new Point2D(60, 60), 10, 25);
      fail("Movement invalid time");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      anime.move("r0", new Point2D(60, 60), 10, 25);
      fail("Invalid name");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      anime.move("r1", null, 10, 15);
      fail("Movement invalid position");
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals("Shape o1 moves from (15,-15) to (-30, 30) from t= 0 to t= 15"
            + System.lineSeparator()
            + "Shape r1 moves from (50, 50) to (30, 30) from t= 5 to t= 10"
            + System.lineSeparator()
            + "Shape o1 moves from (15,-15) to (70, 70) from t= 20 to t= 25"
            + System.lineSeparator(), anime.displayAll());
  }


  @Test
  public void testScale() {

  }

}
