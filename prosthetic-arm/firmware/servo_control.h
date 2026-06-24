#pragma once

#include <ESP32Servo.h>
#include "config.h"

// ServoController keeps all servo behavior in one place so the sketch stays clean.
class ServoController {
public:
  void begin() {
    for (uint8_t i = 0; i < SERVO_COUNT; ++i) {
      servos_[i].setPeriodHertz(SERVO_SIGNAL_HZ);
      servos_[i].attach(SERVO_PINS[i], SERVO_MIN_PULSE_US, SERVO_MAX_PULSE_US);
    }

    openHand();
  }

  void openHand() {
    writeAngles(SERVO_OPEN_ANGLES);
    isClosed_ = false;
  }

  void closeHand() {
    writeAngles(SERVO_CLOSED_ANGLES);
    isClosed_ = true;
  }

  void setFromMuscleValue(int muscleValue) {
    if (muscleValue > EMG_THRESHOLD) {
      closeHand();
    } else {
      openHand();
    }
  }

  bool isClosed() const {
    return isClosed_;
  }

private:
  Servo servos_[SERVO_COUNT];
  bool isClosed_ = false;

  void writeAngles(const int (&angles)[SERVO_COUNT]) {
    for (uint8_t i = 0; i < SERVO_COUNT; ++i) {
      servos_[i].write(angles[i]);
    }
  }
};
