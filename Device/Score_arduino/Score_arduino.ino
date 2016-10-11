#include <SoftwareSerial.h>

SoftwareSerial BTSerial(2, 3); // SoftwareSerial(RX, TX)

int btn1 = 7;
int btn2 = 6;
int btn3 = 5;
int led = 13;

void setup(){

  Serial.begin(9600);
  Serial.println("Hello!");

  pinMode(btn1, INPUT_PULLUP);
  pinMode(btn2, INPUT_PULLUP);
  pinMode(btn3, INPUT_PULLUP);

  // set the data rate for the BT port
  BTSerial.begin(9600);
}

void loop(){

  if(digitalRead(btn1)==LOW){
    BTSerial.write("hn1");
    delay(200);
  }

  if(digitalRead(btn2)==LOW){
    BTSerial.write("an1");
    delay(200);
  }

  if(digitalRead(btn3)==LOW){
    BTSerial.write("cc");
    delay(200);
  }
}
