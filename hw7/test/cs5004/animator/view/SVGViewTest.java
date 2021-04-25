package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.IAnimation;

import static org.junit.Assert.assertEquals;

/**
 * this is the test class for SVGView, in this class it test all public methods of a SVGView.
 */
public class SVGViewTest {
  private IView view1;
  private IView view2;
  ByteArrayOutputStream os = new ByteArrayOutputStream();
  private final PrintStream out = new PrintStream(os);
  private IAnimation animation;

  @Before
  public void setUp() {
    animation = new AnimationImpl(100, 100, 600, 600);
    view1 = new SVGView(out, 20);
    animation.declareShape("r1", "rectangle");
    //this motion should display outside of the canvas
    animation.addMotion("r1", 1, 0, 0, 100, 200, 0, 128, 128,
            100, 200, 200, 50, 100, 200, 150, 100);
    animation.declareShape("r2", "rectangle");
    //this motion should display exactly from the canvas
    animation.addMotion("r2", 50, 100, 200, 200, 50, 128, 100,
            100, 150, 200, 400, 50, 200, 100, 150, 200);
    animation.declareShape("e1", "ellipse");
    //this motion should display from the edge of the canvas
    animation.addMotion("e1", 1, 100, 200, 100, 200, 0, 0, 255,
            20, 250, 280, 200, 100, 255, 0, 255);
    animation.declareShape("r3", "rectangle");
    //this motion should display within the canvas
    animation.addMotion("r3", 50, 1, 300, 60, 80, 128, 128, 56,
            120, 400, 450, 200, 90, 50, 50, 96);
    animation.declareShape("e2", "ellipse");
    //this motion should display from within the canvas and move to the corner by the end
    animation.addMotion("e2", 20, 400, 450, 100, 75, 60, 80,
            120, 120, 700, 700, 60, 45, 100, 200, 186);
    animation.declareShape("r4", "rectangle");
    //this motion starts from within the canvas but move out at the end
    animation.addMotion("r4", 20, 300, 450, 100, 75, 60, 80,
            120, 120, 700, 700, 60, 45, 100, 200, 186);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeConstructor1() {
    //when speed is 0, invalid speed
    view2 = new SVGView(out, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeConstructor2() {
    //when speed is negative, invalid speed
    view2 = new SVGView(out, -2);
  }

  @Test
  public void testShowAll() throws UnsupportedEncodingException {
    view1.showAll(animation);
    assertEquals("<svg width=\"700\" height=\"700\" version=\"1.1\" viewBox=\"100, 100,"
            + " 600, 600\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"r1\" x=\"0\" y=\"0\" width=\"100\" height=\"200\" fill=\"rgb(0,128,"
            + "128)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName=\"fill\""
            + " from=\"rgb(0,128,128)\" to=\"rgb(200,150,100)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName=\"x\""
            + " from=\"0\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName=\"y\" "
            + "from=\"0\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName=\"width\""
            + " from=\"100\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName="
            + "\"height\" from=\"200\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"r2\" x=\"100\" y=\"200\" width=\"200\" height=\"50\" fill=\"rgb(128,100,"
            + "100)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\""
            + "fill\" from=\"rgb(128,100,100)\" to=\"rgb(100,150,200)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\"x\""
            + " from=\"100\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\"y\""
            + " from=\"200\" to=\"400\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\""
            + "width\" from=\"200\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\""
            + "height\" from=\"50\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"7500ms\" dur=\"1ms\" attributeName=\""
            + "visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"e1\" cx=\"100\" cy=\"200\" rx=\"50\" ry=\"100\" fill=\"rgb(0,0,"
            + "255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\"fill\""
            + " from=\"rgb(0,0,255)\" to=\"rgb(255,0,255)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\"cx\""
            + " from=\"100\" to=\"250\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\"cy\""
            + " from=\"200\" to=\"280\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\"rx\""
            + " from=\"50\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\"ry\""
            + " from=\"100\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" attributeName=\""
            + "visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</ellipse>\n" + "<rect id=\"r3\" x=\"1\" y=\"300\" width=\"60\" height=\"80\" "
            + "fill=\"rgb(128,128,56)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"3500ms\" attributeName=\""
            + "fill\" from=\"rgb(128,128,56)\" to=\"rgb(50,50,96)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"3500ms\" attributeName=\"x\""
            + " from=\"1\" to=\"400\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"3500ms\" attributeName=\"y\""
            + " from=\"300\" to=\"450\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"3500ms\" attributeName=\""
            + "width\" from=\"60\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"3500ms\" attributeName=\""
            + "height\" from=\"80\" to=\"90\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"6000ms\" dur=\"1ms\" attributeName=\""
            + "visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"e2\" cx=\"400\" cy=\"450\" rx=\"50\" ry=\"37\" fill=\""
            + "rgb(60,80,120)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "fill\" from=\"rgb(60,80,120)\" to=\"rgb(100,200,186)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "cx\" from=\"400\" to=\"700\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "cy\" from=\"450\" to=\"700\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "rx\" from=\"50\" to=\"30\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "ry\" from=\"37\" to=\"22\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"6000ms\" dur=\"1ms\" attributeName=\""
            + "visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<rect id=\"r4\" x=\"300\" y=\"450\" width=\"100\" height=\"75\" fill=\"rgb(60,80,"
            + "120)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "fill\" from=\"rgb(60,80,120)\" to=\"rgb(100,200,186)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "x\" from=\"300\" to=\"700\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "y\" from=\"450\" to=\"700\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "width\" from=\"100\" to=\"60\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"5000ms\" attributeName=\""
            + "height\" from=\"75\" to=\"45\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"6000ms\" dur=\"1ms\" attributeName=\""
            + "visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</rect>\n\n"
            + "</svg>", os.toString(StandardCharsets.UTF_8));
  }

  @Test
  public void testShowOneShape1() {
    view1.showOneShape(animation, "r1");
    assertEquals("<svg width=\"700\" height=\"700\" version=\"1.1\" viewBox=\""
            + "100, 100, 600, 600\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"r1\" x=\"0\" y=\"0\" width=\"100\" height=\"200\" fill=\"rgb"
            + "(0,128,128)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName=\""
            + "fill\" from=\"rgb(0,128,128)\" to=\"rgb(200,150,100)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName=\"x\""
            + " from=\"0\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName=\"y\""
            + " from=\"0\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName=\""
            + "width\" from=\"100\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"4950ms\" attributeName=\""
            + "height\" from=\"200\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"1ms\" attributeName=\""
            + "visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</rect>\n\n</svg>", os.toString(StandardCharsets.UTF_8));
  }

  @Test
  public void testShowOneShape2() {
    view1.showOneShape(animation, "e1");
    assertEquals("<svg width=\"700\" height=\"700\" version=\"1.1\" v"
            + "iewBox=\"100, 100, 600, 600\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<ellipse id=\"e1\" cx=\"100\" cy=\"200\" rx=\"50\" ry=\"100\" fill=\""
            + "rgb(0,0,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\""
            + "fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,0,255)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\""
            + "cx\" from=\"100\" to=\"250\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\""
            + "cy\" from=\"200\" to=\"280\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\""
            + "rx\" from=\"50\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"950ms\" attributeName=\""
            + "ry\" from=\"100\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" attributeName=\""
            + "visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</ellipse>\n\n</svg>", os.toString(StandardCharsets.UTF_8));
  }

  @Test
  public void testShowOneShape3() {
    view1.showOneShape(animation, "r2");
    assertEquals("<svg width=\"700\" height=\"700\" version=\"1.1\" viewBox=\""
            + "100, 100, 600, 600\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"r2\" x=\"100\" y=\"200\" width=\"200\" height=\"50\" fill=\""
            + "rgb(128,100,100)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\""
            + "fill\" from=\"rgb(128,100,100)\" to=\"rgb(100,150,200)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\""
            + "x\" from=\"100\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\""
            + "y\" from=\"200\" to=\"400\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\""
            + "width\" from=\"200\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"5000ms\" attributeName=\""
            + "height\" from=\"50\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"7500ms\" dur=\"1ms\" attributeName=\""
            + "visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</rect>\n\n</svg>", os.toString(StandardCharsets.UTF_8));
  }

  @Test
  public void testGetSpeed() {
    assertEquals(20, view1.getSpeed());
    view2 = new SVGView(out, 100);
    assertEquals(100, view2.getSpeed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeShowOneShape1() {
    view1.showOneShape(null, "r1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeShowOneShape2() {
    view1.showOneShape(animation, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeShowOneShape3() {
    view1.showOneShape(animation, "badName");
  }
}