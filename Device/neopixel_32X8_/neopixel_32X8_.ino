#include <Adafruit_GFX.h>
#include <Adafruit_NeoMatrix.h>
#include <Adafruit_NeoPixel.h>
#include <SoftwareSerial.h>

#define PIN 3
#define HOME_X 1
#define HOME_Y 1
#define AWAY_X 19
#define AWAY_Y 1

// MATRIX DECLARATION:
// Parameter 1 = width of NeoPixel matrix
// Parameter 2 = height of matrix
// Parameter 3 = pin number (most are valid)
// Parameter 4 = matrix layout flags, add together as needed:
//   NEO_MATRIX_TOP, NEO_MATRIX_BOTTOM, NEO_MATRIX_LEFT, NEO_MATRIX_RIGHT:
//     Position of the FIRST LED in the matrix; pick two, e.g.
//     NEO_MATRIX_TOP + NEO_MATRIX_LEFT for the top-left corner.
//   NEO_MATRIX_ROWS, NEO_MATRIX_COLUMNS: LEDs are arranged in horizontal
//     rows or in vertical columns, respectively; pick one or the other.
//   NEO_MATRIX_PROGRESSIVE, NEO_MATRIX_ZIGZAG: all rows/columns proceed
//     in the same order, or alternate lines reverse direction; pick one.
//   See example below for these values in action.
// Parameter 5 = pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)

Adafruit_NeoMatrix matrix = Adafruit_NeoMatrix(32, 8, PIN,
  NEO_MATRIX_BOTTOM    + NEO_MATRIX_RIGHT +
  NEO_MATRIX_COLUMNS + NEO_MATRIX_ZIGZAG,
  NEO_GRB            + NEO_KHZ800);

String home_score="00";
String away_score="00";
String stringSerial="";

int home_point=0;
int away_point=0;
const uint16_t colors[] = {
  matrix.Color(255, 0, 0), matrix.Color(0, 255, 0), matrix.Color(255, 255, 0),matrix.Color(0, 0, 255), matrix.Color(255, 0, 255), matrix.Color(0, 255, 255), matrix.Color(255, 255, 255)};

void setSet(String s, int set, int pass)
{
  if(s.equals("hn"))
  {
    switch(set)
    {
      case 1:
        matrix.setPixelColor(9*16+1,colors[pass]);
        matrix.setPixelColor(9*16+2,colors[pass]);
        matrix.setPixelColor(8*16+13,colors[pass]);
        matrix.setPixelColor(8*16+14,colors[pass]);
        break;
      case 2:
        matrix.setPixelColor(9*16+1,colors[pass]);
        matrix.setPixelColor(9*16+2,colors[pass]);
        matrix.setPixelColor(8*16+13,colors[pass]);
        matrix.setPixelColor(8*16+14,colors[pass]);
        matrix.setPixelColor(9*16+3,colors[pass]);
        matrix.setPixelColor(9*16+4,colors[pass]);
        matrix.setPixelColor(8*16+11,colors[pass]);
        matrix.setPixelColor(8*16+12,colors[pass]);
        break;
      case 3:
        matrix.setPixelColor(9*16+1,colors[pass]);
        matrix.setPixelColor(9*16+2,colors[pass]);
        matrix.setPixelColor(8*16+13,colors[pass]);
        matrix.setPixelColor(8*16+14,colors[pass]);
        matrix.setPixelColor(9*16+3,colors[pass]);
        matrix.setPixelColor(9*16+4,colors[pass]);
        matrix.setPixelColor(8*16+11,colors[pass]);
        matrix.setPixelColor(8*16+12,colors[pass]);
        matrix.setPixelColor(9*16+5,colors[pass]);
        matrix.setPixelColor(9*16+6,colors[pass]);
        matrix.setPixelColor(8*16+9,colors[pass]);
        matrix.setPixelColor(8*16+10,colors[pass]);
        break;
    }
  }
  else if(s.equals("an"))
  {
    switch(set)
    {
      case 1:
        matrix.setPixelColor(7*16+1,colors[pass]);
        matrix.setPixelColor(7*16+2,colors[pass]);
        matrix.setPixelColor(7*16+13,colors[pass]);
        matrix.setPixelColor(7*16+14,colors[pass]);
        break;
      case 2:
        matrix.setPixelColor(7*16+1,colors[pass]);
        matrix.setPixelColor(7*16+2,colors[pass]);
        matrix.setPixelColor(7*16+13,colors[pass]);
        matrix.setPixelColor(7*16+14,colors[pass]);
        matrix.setPixelColor(7*16+3,colors[pass]);
        matrix.setPixelColor(7*16+4,colors[pass]);
        matrix.setPixelColor(7*16+11,colors[pass]);
        matrix.setPixelColor(7*16+12,colors[pass]);
        break;
      case 3:
        matrix.setPixelColor(7*16+1,colors[pass]);
        matrix.setPixelColor(7*16+2,colors[pass]);
        matrix.setPixelColor(7*16+13,colors[pass]);
        matrix.setPixelColor(7*16+14,colors[pass]);
        matrix.setPixelColor(7*16+3,colors[pass]);
        matrix.setPixelColor(7*16+4,colors[pass]);
        matrix.setPixelColor(7*16+11,colors[pass]);
        matrix.setPixelColor(7*16+12,colors[pass]);
        matrix.setPixelColor(7*16+5,colors[pass]);
        matrix.setPixelColor(7*16+6,colors[pass]);
        matrix.setPixelColor(7*16+9,colors[pass]);
        matrix.setPixelColor(7*16+10,colors[pass]);
        break;
    }
  }
}

void setup() {
  matrix.begin();
  matrix.setTextWrap(false);
  matrix.setBrightness(5);
  matrix.setTextColor(colors[0]);

  Serial.begin(9600);
  Serial.println("Goodnight moon!");
}

//int x    = matrix.width();
int pass = 0;

void loop() {
  matrix.fillScreen(0);
  if(Serial.available())
  {
    stringSerial=Serial.readString();
    if(stringSerial.substring(0,2).equals("hn"))
    {
      int num=home_score.toInt()+stringSerial.substring(2,4).toInt();
      if(num<10)
        home_score="0"+String(num);
      else
        home_score=String(num);
    }
    else if(stringSerial.substring(0,2).equals("an"))
    {
      int num=away_score.toInt()+stringSerial.substring(2,4).toInt();
      if(num<10)
        away_score="0"+String(num);
      else
        away_score=String(num);
    }
    if(stringSerial.equals("hn1"))
    {
      ++home_point;
    }
    else if(stringSerial.equals("hn2"))
    {
      away_point+=3;
    }
    else if(stringSerial.equals("an1"))
    {
      ++away_point;
    }
    else if(stringSerial.equals("an2"))
    {
      away_point+=3;
    }
  }

  if(home_point>3||away_point>3)
  {
    home_point=0;away_point=0;
    home_score="00";
    away_score="00";
  }
  
  matrix.setCursor(HOME_X, HOME_Y);
  matrix.print(home_score);
  matrix.setCursor(AWAY_X, AWAY_Y);
  matrix.print(away_score);
  matrix.setCursor(14,7);

  setSet("hn",home_point,pass);
  setSet("an",away_point,pass);

  /*if(--x < -30
  ) {
    x = matrix.width();

    if(++pass >= 8) pass = 0;
    matrix.setTextColor(colors[pass]);
  }*/
  if(++pass >= 7)
    pass = 0;
  matrix.setTextColor(colors[pass]);
  matrix.show();
  stringSerial="";
  delay(500);
}
