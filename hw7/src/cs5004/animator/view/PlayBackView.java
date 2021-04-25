package cs5004.animator.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Collections;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;

/**
 * This class represents the visual view that display an animation using swing. The field of
 * this class includes a speed.
 */
public class PlayBackView extends JFrame implements IView {
  private int speed;
  private PrintStream out;
  private IAnimation animation= null;

  /**
   * Constructor of a visual view.
   *
   * @param out   Appendable output file
   * @param speed tick per sec
   */
  public PlayBackView(PrintStream out, int speed) {
    super("Canvas");
    if (speed <= 0) {
      throw new IllegalArgumentException("invalid speed");
    }
    if (out == null) {
      out = System.out;
    }
    this.speed = speed;
    this.out = out;

    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }


  private void show(java.util.List<IShape> listOfShapes, int[] bounds) {

    ViewPanel viewPanel = new ViewPanel(listOfShapes, speed);

    viewPanel.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    setContentPane(viewPanel);
    JScrollPane scrollPane = new JScrollPane(viewPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    setContentPane(scrollPane);

    // Start & Pause & Resume button
    JToggleButton pause = new JToggleButton("Start");

    pause.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if(pause.isSelected()){
          viewPanel.playAnime();
          pause.setText("Pause");
        } else {
          viewPanel.pauseAnime();
          pause.setText("Play");
        }
      }
    });

    // Restart Button
    JButton restart = new JButton("Restart");

    restart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        viewPanel.restart();
      }
    });

    //Chang speed to 3 times or 1/3
    // Faster Button
    JButton faster = new JButton("Fast");

    faster.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        viewPanel.faster();
      }
    });

    // Slower Button
    JButton slower = new JButton("Slow");

    slower.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        viewPanel.slower();
      }
    });

    // Save
    JButton save = new JButton("Save");

    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame saveFrame = new JFrame("Save File");

        saveFrame.setSize(150, 120);
        saveFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        saveFrame.setLocationRelativeTo(null);

        JPanel savePanel = new JPanel();

        JRadioButton svg = new JRadioButton("SVG");
        JRadioButton text = new JRadioButton("TEXT");

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(svg);
        btnGroup.add(text);

        svg.setSelected(true);

        savePanel.add(svg);
        savePanel.add(text);


        JButton confirm = new JButton("Save");

        confirm.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            IView view = null;
            if(svg.isSelected()){
              view= new SVGView(out, speed);
            } else if(text.isSelected()){
              view = new TextView(out);
            }
            view.showAll(animation);
          }
        });

        savePanel.add(confirm);
        saveFrame.setContentPane(savePanel);
        saveFrame.setVisible(true);
      }
    });

    // Loop Button
    JToggleButton loop = new JToggleButton("Loop");

    loop.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if(loop.isSelected()){
          viewPanel.loop();
          loop.setText("NonLoop");
        } else {
          viewPanel.stopLoop();
          loop.setText("Loop");
        }
      }
    });


    //Add buttons
    viewPanel.add(pause);
    viewPanel.add(restart);
    viewPanel.add(loop);
    viewPanel.add(slower);
    viewPanel.add(faster);
    viewPanel.add(save);


    setVisible(true);
    viewPanel.animate();
    viewPanel.pauseAnime();
  }


  @Override
  public void showAll(IAnimation animation) {
    this.animation = animation;
    show(animation.getListOfShapes(), animation.getBounds());
  }


  @Override
  public void showOneShape(IAnimation animation, String shapeName) {
    this.animation = animation;
    for (IShape shape : animation.getListOfShapes()) {
      if (shape.getName().equalsIgnoreCase(shapeName)) {
        show(Collections.singletonList(shape), animation.getBounds());
        return;
      }
    }
  }


  @Override
  public int getSpeed() {
    return speed;
  }

}