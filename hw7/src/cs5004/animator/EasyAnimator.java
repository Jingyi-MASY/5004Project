package cs5004.animator;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;


import cs5004.animator.model.AnimationImpl;
import cs5004.animator.util.AnimationReader;

import cs5004.animator.view.IView;
import cs5004.animator.view.InputMessage;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.ViewOption;
import cs5004.animator.model.IAnimation;

public final class EasyAnimator {
  private static int speed;
  private static Readable in;
  private static PrintStream out;

  public static void main(String[] args) {
    // Set default value
    speed = 1;
    IView iView = null;
    out = null;
    in = null;
    ViewOption view = null;

    String[] fields = {"-view", "-in", "-out", "-speed"};

    //TODO: Get speed into View
    for(int i = 0; i < args.length; i+=2){
      if(args[i].equalsIgnoreCase(fields[3])){
        try{
          speed = Integer.parseInt(args[i + 1]);
        } catch (Exception e){
          InputMessage.Message("Invalid speed format.");
        }
      } else if(args[i].equalsIgnoreCase(fields[0])){
        for (ViewOption v: ViewOption.values()) {
          if(v.isView(args[i + 1])){
            view = v;
          }
        }
      }
      //Read input source, and build FileReader, if not found JFrame ErrorMessage
      else if(args[i].equalsIgnoreCase(fields[1])){
        try {
          //TODO:Need to change, if refactor
          String filePath = new File("").getAbsolutePath()
                  + "\\5004Project-main\\hw7\\code\\" + args[i + 1];
          in = new FileReader(filePath);
        } catch (FileNotFoundException e) {
          InputMessage.Message("Input File not found.");
        }

      }else if(args[i].equalsIgnoreCase(fields[2])){
        //TODO:Need to change, if refactor
        String filePath = new File("").getAbsolutePath()
                + "\\5004Project-main\\hw7\\code\\" + args[i + 1];
        try {
          out = new PrintStream(filePath);
        } catch (FileNotFoundException e) {
          InputMessage.Message("Output File not found.");
        }
      }
    }
    //If no in and view parameter passed in, JPanel ErrorMessage
    if(in == null || view == null){
      InputMessage.Message("Must provide -in and -view.");
    }

    //AnimationBuilder implementation
    IAnimation model = AnimationReader.parseFile(in, new AnimationImpl.Builder());
    //Create a view;
    if(view == ViewOption.SVG){
      iView = new SVGView(out);
    } else if(view == ViewOption.VISUAL) {
      iView = new VisualView(out);
    } else if(view == ViewOption.VISUAL) {
      iView = new TextView(out);
    }
    iView.showAll(model);
  }
}
