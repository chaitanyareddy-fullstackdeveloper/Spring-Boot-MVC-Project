#pragma once

#include <Arduino.h>

// ----------------------------
// Core control configuration
// ----------------------------

constexpr uint8_t BIOAMP_PIN = 34;
constexpr int EMG_THRESHOLD = 260;

// Simple averaging keeps the firmware modular and easy to tune.
// Set this to 1 to keep the raw analogRead behavior from the original sketch.
constexpr uint8_t EMG_SAMPLES = 1;
static_assert(EMG_SAMPLES > 0, "EMG_SAMPLES must be greater than zero");

// Servo configuration
constexpr uint8_t SERVO_COUNT = 5;
constexpr uint8_t SERVO_PINS[SERVO_COUNT] = {18, 14, 15, 13, 19};
constexpr int SERVO_MIN_PULSE_US = 500;
constexpr int SERVO_MAX_PULSE_US = 2500;
constexpr int SERVO_SIGNAL_HZ = 50;

// Preserved motion profile from the provided sketch.
// Relaxed hand = open.
constexpr int SERVO_OPEN_ANGLES[SERVO_COUNT] = {90, 0, 0, 0, 0};

// Flexed hand = closed fist.
constexpr int SERVO_CLOSED_ANGLES[SERVO_COUNT] = {0, 180, 180, 180, 180};

// Feedback and timing
constexpr bool ENABLE_SERIAL_DEBUG = true;
constexpr uint32_t LOOP_DELAY_MS = 200;
