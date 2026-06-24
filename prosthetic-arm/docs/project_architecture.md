# Project Architecture

This repository is split into hardware notes, firmware, diagrams, and examples so it can be shared as a clean GitHub project.

## Firmware Layers

- `prosthetic_arm.ino`: main control loop and serial output
- `config.h`: pin definitions, thresholds, and motion constants
- `servo_control.h`: reusable servo control wrapper

## Control Flow

1. Read the EMG signal from the BioAmp EXG Pill.
2. Compare the muscle value against the threshold.
3. If the value is above the threshold, close the hand.
4. If the value is below the threshold, open the hand.
5. Repeat continuously while printing diagnostic data to Serial.

## Design Goal

The goal is reliable binary control for grasp and release. The project intentionally keeps the first version simple so it is easier to calibrate and safer to debug.

## Extension Points

- Add filtering or smoothing in the firmware
- Add separate presets for pinch, power grasp, or pointing
- Add wireless diagnostics
- Add a calibration mode for live threshold tuning
