import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import websockets.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Web_Socket_Client extends PApplet {



WebsocketClient wsc;
float x, y;

public void setup() {
  
  background(0);

  wsc = new WebsocketClient(this, "ws://localhost:8080/draw");
  x = 0;
  y = 0;
}

public void mouseDragged() {
  fill(255, 100, 0);
  ellipseMode(CENTER);
  ellipse(mouseX, mouseY, 20, 20);
  wsc.sendMessage(mouseX + "," + mouseY);
}

public void webSocketEvent(String msg) {
  String[] points = msg.split(",");
  x = PApplet.parseFloat(points[0]);
  y = PApplet.parseFloat(points[1]);
}

public void draw() {
  if (x != 0 && y != 0) {
    fill(0, 255, 100);
    ellipseMode(CENTER);
    ellipse(x, y, 20, 20);
  }
}

  public void settings() {  size(300, 300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Web_Socket_Client" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
