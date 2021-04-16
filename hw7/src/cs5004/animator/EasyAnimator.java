package cs5004.animator;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import cs5004.animator.model.ViewOption;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

public final class EasyAnimator {
  private static int speed;
  private static Readable in;
  private static Appendable out;
  private static AnimationBuilder builder;
  private static JFrame frame;

  private static void message(String message) {
    frame = new JFrame();
    JOptionPane.showMessageDialog(frame,message,
            "Input Error",
            JOptionPane.ERROR_MESSAGE);

  }

  public static void main(String[] args) {
    // Set default value
    speed = 1;
    builder = null;
    out = System.out;
    in = null;


    String[] fields = {"-view", "-in", "-out", "-speed"};
    for(int i = 0; i < args.length; i+=2){
      if(args[i].equalsIgnoreCase(fields[3])){
        try{
          speed = Integer.parseInt(args[i + 1]);
        } catch (Exception e){
          message("Invalid speed format.");
        }
      } else if(args[i].equalsIgnoreCase(fields[0])){
        if(ViewOption.SVG.isView(args[i + 1])){
          builder = new SVGView();
        }else if(ViewOption.VISUAL.isView(args[i + 1])){
          builder = new VisualView();
        }else if(ViewOption.TEXT.isView(args[i + 1])){
          builder = new TextView();
        }
        if(ViewOption.VISUAL.isView(args[i + 1])){
          builder = new VisualView();
        }
      }else if(args[i].equalsIgnoreCase(fields[1])){
        try {
          //Need to change, if refactor
          String filePath = new File("").getAbsolutePath()
                  + "\\5004Project-main\\hw7\\code\\" + args[i + 1];
          in = new FileReader(filePath);
        } catch (FileNotFoundException e) {
          message("Input File not found.");
        }

      }else if(args[i].equalsIgnoreCase(fields[2])){
        try {
          //Need to change, if refactor
          String filePath = new File("").getAbsolutePath()
                  + "\\5004Project-main\\hw7\\code\\" + args[i + 1];
          out = new FileWriter(filePath);
        } catch (IOException e) {
          message("Output File not found.");
        }
      }
    }

    AnimationReader reader = new AnimationReader();
    reader.parseFile(in, builder);
  }
}
