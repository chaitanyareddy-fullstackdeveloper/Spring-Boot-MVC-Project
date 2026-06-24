# Hardware Setup Guide

This guide explains how to assemble the ESP32, BioAmp EXG Pill, and MG90S servos into a working prosthetic hand control system.

## Power Rules

- Power the ESP32 by USB or a regulated source.
- Power the servos from a separate regulated 5V supply.
- Connect all grounds together: ESP32 GND, BioAmp GND, and servo power supply GND.
- Do not power multiple servos from the ESP32 3.3V pin.

## Recommended Wiring Order

1. Mount the ESP32 and power supply safely.
2. Connect the BioAmp EXG Pill output to GPIO 34.
3. Connect each servo signal wire to its assigned GPIO pin.
4. Connect servo power to 5V and servo ground to common ground.
5. Attach electrodes to the target forearm muscle.
6. Upload the firmware and test the open/close response.

## EMG Sensor Placement

- Place one electrode on the muscle belly.
- Place the reference electrode according to the BioAmp EXG Pill instructions.
- Keep the electrode pads firmly attached for stable readings.
- Avoid loose wires and movement during calibration.

## Servo Calibration

1. Upload the firmware.
2. Watch the serial monitor at `115200`.
3. Flex the muscle and confirm the hand closes.
4. Relax the muscle and confirm the hand opens.
5. If motion is inverted, adjust the open and closed angles in `firmware/config.h`.
6. If servos buzz or stall, reduce the mechanical load and re-center the linkage.

## First Test Checklist

- Serial monitor shows a changing muscle value
- Relaxed state opens the fingers
- Flexed state closes the fingers
- Servos move smoothly and do not brown out the board

## Safety Notes

- Test without a heavy object first.
- Keep fingers clear of pinch points while calibrating.
- Stop immediately if the supply gets hot or the servos chatter loudly.
