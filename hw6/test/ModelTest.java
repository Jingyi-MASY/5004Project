import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class ModelTest {
  private AnimationImpl anime;
  private IShape r1;

  @Before
  public void setup() {
    anime = new AnimationImpl();
    r1 = new Rectangle("r1", ShapeType.RECTANGLE, new Point2D(50, 50), Color.WHITE, 0, 20, 30, 20);
  }

  @Test
  public void testAddShape() {
    assertEquals("", anime.showAllShapes());
    anime.addShape(new Rectangle("r1", ShapeType.RECTANGLE, new Point2D(50, 50), Color.WHITE, 0, 20, 30, 20));
    try{
      anime.addShape(null);
      fail("Accepting Null");
    } catch (IllegalArgumentException ignored) {
    }
    try{
      anime.addShape(new Oval("r1", ShapeType.OVAL, new Point2D(50, 50), Color.WHITE, 0, 20, 30, 20));
      fail("Accepting Null");
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals("Name: r1" + System.lineSeparator() +
            "Type: rectangle" + System.lineSeparator() +
            "Lower left corner: (50, 50), Width: 30, Height: 20 Color: java.awt.Color[r=255,g=255,b=255]"
            +  System.lineSeparator() +
            "Appears at: t=0" +  System.lineSeparator() +
            "Disappears at t=20" + System.lineSeparator(), anime.showAllShapes());
  }

  @Test
  public void testColor(){
    anime.addShape(r1);
    anime.addShape(new Rectangle("r2", ShapeType.RECTANGLE, new Point2D(30, 50), Color.RED, 0, 20, 30, 20));

    //Invalid name
    try{
      anime.changeColor("r0", Color.BLUE, 0, 10);
      fail("Accepting invalid name");
    } catch (IllegalArgumentException ignored) {
    }
    //Invalid time
    try{
      anime.changeColor("r1", Color.BLUE, 10, 40);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }
    //Invalid name
    try{
      anime.changeColor("r1", null, 0, 10);
      fail("Accepting null color");
    } catch (IllegalArgumentException ignored) {
    }
    anime.changeColor("r1", Color.BLUE, 10, 20);
    assertEquals(Color.BLUE, r1.getColorAt(10));
  }

  @Test
  public void testMove(){
    anime.addShape(r1);
    anime.move("r1", new Point2D(30, 30),0, 10);
    try{
      anime.move("r1", new Point2D(60, 60),5, 10);
      fail("Movement conflict");
    } catch (IllegalArgumentException ignored) {
    }
    try{
      anime.move("r1", new Point2D(60, 60),10, 25);
      fail("Movement invalid time");
    } catch (IllegalArgumentException ignored) {
    }
    try{
      anime.move("r0", new Point2D(60, 60),10, 25);
      fail("Invalid name");
    } catch (IllegalArgumentException ignored) {
    }
    try{
      anime.move("r1", null,10, 15);
      fail("Movement invalid position");
    } catch (IllegalArgumentException ignored) {
    }
    //assertEquals();
  }


  @Test
  public void testScale(){

  }

  @Test
  public void testGetMovement() {

  }

}
