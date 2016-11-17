#include <SoftwareSerial.h>

int btn1 = 2;
int btn2 = 3;
int btn3 = 4;

boolean flag1 = false;
boolean flag2 = false;
boolean flag3 = false;
boolean flag4 = false;

long times = 0;

void setup(){

  Serial.begin(9600);

  pinMode(btn1, INPUT_PULLUP);
  pinMode(btn2, INPUT_PULLUP);
  pinMode(btn3, INPUT_PULLUP);
}

void loop(){

  if(digitalRead(btn1)==LOW){ 
      flag1 = true;
      delay(100);

      if((digitalRead(btn1)==LOW)&&(digitalRead(btn2)==LOW)){
          
          while(flag1){
            delay(1);
            times++;
    
            if((digitalRead(btn1)==HIGH)&&(digitalRead(btn2)==HIGH)){
              if(times>500){
                Serial.write("se");
              }
              times = 0;
              flag1 = false;
            }
          }
        }
      else{
          while(flag1){
            delay(1);
            times++;
    
            if(digitalRead(btn1)==HIGH){
              if(times>500){
                Serial.write("hn2");
              }
              else{
                Serial.write("hn1");
              }
              times = 0;
              flag1 = false;  
            }
          }
      }
  }

  if(digitalRead(btn2)==LOW){
      flag2 = true;
      delay(100);

      if((digitalRead(btn1)==LOW)&&(digitalRead(btn2)==LOW)){
          
          while(flag2){
            delay(1);
            times++;
    
            if((digitalRead(btn1)==HIGH)&&(digitalRead(btn2)==HIGH)){
              if(times>500){
                Serial.write("se");
              }
              times = 0;
              flag2 = false;
            }
          }
        }
        else{
          while(flag2){
            delay(1);
            times++;
    
            if(digitalRead(btn2)==HIGH){
              if(times>500){
                Serial.write("an2");
              }
              else{
                Serial.write("an1");
              }
              times = 0;
              flag2 = false;  
            }
          }
        }
  }
  
  if(digitalRead(btn3)==LOW){
      flag3 = true;
      
      while(flag3){
        delay(1);
        times++;

        if(digitalRead(btn3)==HIGH){\
          Serial.write("cc");
          times = 0;
          flag3 = false;  
        }
     }
  }
}
