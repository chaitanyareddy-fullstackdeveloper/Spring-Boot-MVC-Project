# ESP32 EMG Prosthetic Arm

An open-source ESP32 project for controlling a prosthetic robotic hand with forearm EMG signals from a BioAmp EXG Pill sensor. When the muscle is relaxed, the hand opens. When the muscle is flexed, the hand closes into a fist so it can grasp and release objects.

## 1. Project Description

This repository contains the firmware, wiring notes, hardware reference, and build documentation for an EMG-controlled prosthetic arm using:

- ESP32 microcontroller
- BioAmp EXG Pill EMG sensor
- MG90S servo motors
- A simple open/close control model based on muscle flex and relaxation

The code is organized so it is easy to calibrate, maintain, and extend.

## 2. Features

- Reads EMG values from the BioAmp EXG Pill
- Detects flex and relaxation using a configurable threshold
- Opens all fingers when the muscle is relaxed
- Closes all fingers into a fist when the muscle is flexed
- Uses modular firmware files for cleaner maintenance
- Includes beginner-friendly setup and wiring documentation

## 3. Hardware Components

The recommended hardware is listed in detail in [hardware/bill_of_materials.md](./hardware/bill_of_materials.md). At minimum you will need:

- ESP32 development board
- BioAmp EXG Pill sensor
- 5x MG90S servo motors
- External 5V power supply for the servos
- Jump wires and breadboard or custom harness
- Prosthetic hand mechanism or finger linkage

## 4. Wiring Diagram

![ESP32 EMG prosthetic arm wiring diagram](./docs/images/wiring-diagram.svg)

See:

- [docs/wiring_diagram.md](./docs/wiring_diagram.md)
- [hardware/pin_connections.md](./hardware/pin_connections.md)
- [diagrams/signal_flow.md](./diagrams/signal_flow.md)

## 5. Installation

1. Install the Arduino IDE or PlatformIO.
2. Install the ESP32 board package.
3. Install the `ESP32Servo` library.
4. Open `firmware/prosthetic_arm.ino`.
5. Verify the board type, COM port, and pin mapping.

## 6. Uploading Firmware

1. Connect the ESP32 by USB.
2. Select the correct ESP32 board in the IDE.
3. Select the correct serial port.
4. Make sure servo power comes from an external 5V supply, not the ESP32 3.3V pin.
5. Upload `firmware/prosthetic_arm.ino`.
6. Open Serial Monitor at `115200` baud.

## 7. Usage

1. Place the EMG electrodes on the forearm muscle group you want to use for control.
2. Power the ESP32 and the servo supply.
3. Relax the muscle to open the hand.
4. Flex the muscle to close the hand.
5. Adjust the threshold in `firmware/config.h` if the hand triggers too easily or not enough.

## 8. Folder Structure

```text
prosthetic-arm/
├── README.md
├── LICENSE
├── .gitignore
├── firmware/
│   ├── prosthetic_arm.ino
│   ├── config.h
│   └── servo_control.h
├── docs/
│   ├── wiring_diagram.md
│   ├── hardware_setup.md
│   ├── project_architecture.md
│   └── images/
├── hardware/
│   ├── bill_of_materials.md
│   ├── pin_connections.md
│   └── mechanical_design.md
├── diagrams/
│   ├── system_flowchart.md
│   └── signal_flow.md
└── examples/
    └── basic_open_close_demo.ino
```

## 9. Troubleshooting

- If the servos jitter, use a stronger 5V supply and confirm that grounds are shared.
- If the hand moves in the wrong direction, swap the open and close angles in `firmware/config.h`.
- If EMG triggers are too sensitive, raise the threshold.
- If EMG triggers are too weak, lower the threshold slightly.
- If nothing moves, check that the servo library is installed and that the pins match the code.

## 10. Future Enhancements

- Add analog filtering and adaptive calibration
- Add proportional control instead of only open/close states
- Add battery monitoring
- Add Bluetooth or Wi-Fi telemetry
- Add per-finger grasp modes for pinch, power grasp, and release

## 11. License

This project is released under the MIT License. See [LICENSE](./LICENSE).

## Firmware Notes

The main firmware keeps the behavior from your original sketch:

- BioAmp signal input on GPIO 34
- Threshold-based flex detection
- Servo pins on 18, 14, 15, 13, and 19
- Open and close positions preserved for each finger

The logic is split across `config.h`, `servo_control.h`, and `prosthetic_arm.ino` so the project is easier to tune and publish.
