import websockets.*;

WebsocketServer ws;
float x, y;

void setup() {
  size(300, 300);
  background(0);

  ws = new WebsocketServer(this, 8080, "/draw");
  x = 0;
  y = 0;
}

void mouseDragged() {
  fill(255, 100, 0);
  ellipseMode(CENTER);
  ellipse(mouseX, mouseY, 20, 20);
  ws.sendMessage(mouseX + "," + mouseY);
}

void draw() {
  if (x != 0 && y != 0) {
    fill(0, 255, 100);
    ellipseMode(CENTER);
    ellipse(x, y, 20, 20);
  }
}

void webSocketServerEvent(String msg) {
  String[] points = msg.split(",");
  x = float(points[0]);
  y = float(points[1]);
}
