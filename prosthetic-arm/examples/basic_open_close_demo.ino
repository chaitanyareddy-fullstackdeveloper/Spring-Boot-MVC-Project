#include <ESP32Servo.h>

// Simple demo sketch for testing the servo motion without EMG input.
// It opens the hand, waits, closes the hand, and repeats.

Servo servo1;
Servo servo2;
Servo servo3;
Servo servo4;
Servo servo5;

const int servoPins[5] = {18, 14, 15, 13, 19};
const int openAngles[5] = {90, 0, 0, 0, 0};
const int closeAngles[5] = {0, 180, 180, 180, 180};

void writePose(const int angles[5]) {
  servo1.write(angles[0]);
  servo2.write(angles[1]);
  servo3.write(angles[2]);
  servo4.write(angles[3]);
  servo5.write(angles[4]);
}

void setup() {
  Serial.begin(115200);

  servo1.setPeriodHertz(50);
  servo2.setPeriodHertz(50);
  servo3.setPeriodHertz(50);
  servo4.setPeriodHertz(50);
  servo5.setPeriodHertz(50);

  servo1.attach(servoPins[0], 500, 2500);
  servo2.attach(servoPins[1], 500, 2500);
  servo3.attach(servoPins[2], 500, 2500);
  servo4.attach(servoPins[3], 500, 2500);
  servo5.attach(servoPins[4], 500, 2500);

  writePose(openAngles);
  Serial.println("Open/close demo started");
}

void loop() {
  writePose(openAngles);
  delay(2000);

  writePose(closeAngles);
  delay(2000);
}
