#include <SoftwareSerial.h>

int btn1 = 2;
int btn2 = 3;
int btn3 = 4;

void setup(){

  Serial.begin(9600);

  pinMode(btn1, INPUT_PULLUP);
  pinMode(btn2, INPUT_PULLUP);
  pinMode(btn3, INPUT_PULLUP);
}

void loop(){

  if(digitalRead(btn1)==LOW){
    Serial.write("hn1");
    delay(400);
  }

  if(digitalRead(btn2)==LOW){
    Serial.write("an1");
    delay(400);
  }

  if(digitalRead(btn3)==LOW){
    Serial.write("cc");
    delay(400);
  }
}
