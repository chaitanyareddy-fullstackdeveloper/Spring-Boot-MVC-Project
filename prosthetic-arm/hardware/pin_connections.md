# Pin Connections

## ESP32 Pin Mapping

| Function | ESP32 Pin | Notes |
|---|---:|---|
| EMG analog input | GPIO 34 | BioAmp EXG Pill output |
| Servo 1 | GPIO 18 | Finger control |
| Servo 2 | GPIO 14 | Finger control |
| Servo 3 | GPIO 15 | Finger control |
| Servo 4 | GPIO 13 | Finger control |
| Servo 5 | GPIO 19 | Finger control |

## Power Connections

| Device | Connection |
|---|---|
| ESP32 | USB or regulated board supply |
| Servo power rail | External 5V supply |
| BioAmp EXG Pill | 3.3V or the supply recommended by its board documentation |
| All grounds | Common ground |

## Wiring Checklist

- Signal wires are connected to the correct GPIO pins
- Servo power is not taken from the ESP32 3.3V pin
- All grounds are tied together
- The BioAmp output is connected to an analog-capable pin

## Current Firmware Pin Values

The firmware uses the following constants:

- EMG input: `34`
- Servo pins: `18, 14, 15, 13, 19`
