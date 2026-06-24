#include "config.h"
#include "servo_control.h"

ServoController hand;

static int readMuscleValue() {
  long total = 0;

  for (uint8_t i = 0; i < EMG_SAMPLES; ++i) {
    total += analogRead(BIOAMP_PIN);
  }

  return static_cast<int>(total / EMG_SAMPLES);
}

void setup() {
  Serial.begin(115200);
  pinMode(BIOAMP_PIN, INPUT);

  hand.begin();

  if (ENABLE_SERIAL_DEBUG) {
    Serial.println("BioAmp Control Started");
    Serial.println("Relax = open hand, flex = close fist");
  }
}

void loop() {
  const int muscleValue = readMuscleValue();

  if (ENABLE_SERIAL_DEBUG) {
    Serial.print("Muscle Value: ");
    Serial.println(muscleValue);
  }

  hand.setFromMuscleValue(muscleValue);
  delay(LOOP_DELAY_MS);
}
