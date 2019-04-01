#include <SoftwareSerial.h>
#include <SPI.h>
#include <MFRC522.h>     // 引用程式庫
 


 
#define RST_PIN      A0        // 讀卡機的重置腳位
#define SS_PIN       10        // 晶片選擇腳位
boolean DEBUG = true;
MFRC522 mfrc522(SS_PIN, RST_PIN);  // 建立MFRC522物件  
SoftwareSerial esp8266(2,3); // make RX Arduino line is pin 2, make TX Arduino line is pin 3.
                             // This means that you need to connect the TX line from the esp to the Arduino's pin 2
                             // and the RX line from the esp to the Arduino's pin 3


int returnSuccess = 5;
int returnError = 6;
int initESP = 7;
boolean key = false;
long saveTime = 0;

void setup() {
  Serial.begin(9600);
  //RFID初始化
  SPI.begin();
  mfrc522.PCD_Init();   // 初始化MFRC522讀卡機模組
  //esp8266初始化
  esp8266.begin(9600); // your esp's baud rate might be different

  pinMode(initESP,OUTPUT);
  pinMode(returnError,OUTPUT);
  pinMode(returnSuccess,OUTPUT);
  myf();
  //設定為station模式  ESP8266 可連線至一路由器 (AP)，如果回傳ok就不做初始化
//  if(
//  sendCommand("AT+CWMODE=1\r\n",2000,DEBUG).equals("ok") // reset module
//  ) return ;

 
}
 
void loop() { // run over and over
  /*
  while(esp8266.available()) {
    Serial.write(esp8266.read());
  }
   if (Serial.available()) {
    esp8266.write(Serial.read());
  }*/
  if (mfrc522.PICC_IsNewCardPresent() && mfrc522.PICC_ReadCardSerial()) {
    digitalWrite(returnError,LOW);
    digitalWrite(returnSuccess,LOW);
    digitalWrite(initESP,HIGH);
    byte *id = mfrc522.uid.uidByte;   // 取得卡片的UID
    byte idSize = mfrc522.uid.size;   // 取得UID的長度
    
    Serial.print("PICC type: ");      // 顯示卡片類型
    // 根據卡片回應的SAK值（mfrc522.uid.sak）判斷卡片類型
    MFRC522::PICC_Type piccType = mfrc522.PICC_GetType(mfrc522.uid.sak);
    Serial.println(mfrc522.PICC_GetTypeName(piccType));
    //    Serial.print("UID Size: ");       // 顯示卡片的UID長度值
    //Serial.println(idSize);
    String tmp1 = "id:";
    for (byte i = 0; i < idSize; i++) {  // 逐一顯示UID碼
      if(id[i] < 16) 
        tmp1+="0";
      tmp1+=String(id[i], HEX);
      
    }
    tmp1+="\r\n";
    String respon2 = sendCommand(tmp1,500,DEBUG);
    Serial.println(respon2);
    if(respon2.equals("NO")) {
      digitalWrite(returnError,HIGH);
    }
    if(respon2.equals("YES")) {
      digitalWrite(returnSuccess,HIGH);
    }
    mfrc522.PICC_HaltA();  // 讓卡片進入停止模式
  }
  digitalWrite(initESP,LOW); 
//String s = "OK!!";
//sendCommand(s,2000,DEBUG);
String respon = sendCommand("i\'m find\r\n",1000,DEBUG);

}
  



String sendCommand(String command, const int timeout, boolean debug){
    String response = "";
    esp8266.print(command); // send the read character to the esp8266
    long int time = millis();
    while( (time+timeout) > millis()){
      while(esp8266.available()){
        // The esp has data so display its output to the serial window
        char c = esp8266.read(); // read the next character.
        response+=c;
      } 
    }
    if(debug)
    {
      Serial.println(response);
    }
    return response;
}

void myf() {
  digitalWrite(initESP,HIGH);
//   連線到我的手機
  String s="AT+CWJAP=\"Xperia XZs_d695\",\"rock0800\"\r\n";
  sendCommand(s,4000,DEBUG);// reset module
//  設定包率
  s = "AT+UART=9600,8,1,0,0\r\n";
  sendCommand(s,2000,DEBUG);// reset module
  //sendCommand("AT+RST\r\n",2000,DEBUG); // reset module
  
  s = "AT+CIPSTART=\"TCP\",\"192.168.43.166\",3030\r\n";//连接tcp
  sendCommand(s,2000,DEBUG);// reset module
  s = "AT+CIPMODE=1\r\n";//开启透传模式
  sendCommand(s,2000,DEBUG);
  s = "AT+CIPSEND\r\n";//准备模块与电脑进行互传数据
  sendCommand(s,2000,DEBUG);
  digitalWrite(initESP,LOW);
}
