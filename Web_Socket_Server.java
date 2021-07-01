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

public class Web_Socket_Server extends PApplet {



WebsocketServer ws;
float x, y;

public void setup() {
  
  background(0);

  ws = new WebsocketServer(this, 8080, "/draw");
  x = 0;
  y = 0;
}

public void mouseDragged() {
  fill(255, 100, 0);
  ellipseMode(CENTER);
  ellipse(mouseX, mouseY, 20, 20);
  ws.sendMessage(mouseX + "," + mouseY);
}

public void draw() {
  if (x != 0 && y != 0) {
    fill(0, 255, 100);
    ellipseMode(CENTER);
    ellipse(x, y, 20, 20);
  }
}

public void webSocketServerEvent(String msg) {
  String[] points = msg.split(",");
  x = PApplet.parseFloat(points[0]);
  y = PApplet.parseFloat(points[1]);
}
  public void settings() {  size(300, 300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Web_Socket_Server" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
