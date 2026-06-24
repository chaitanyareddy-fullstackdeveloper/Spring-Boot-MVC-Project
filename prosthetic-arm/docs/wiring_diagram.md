# Wiring Diagram

The diagram below shows the basic electrical relationships in the project. It was built from the project pin map and the general wiring guidance in the Upside Down Labs BioAmp EXG Pill repository, which shows EMG signal acquisition through the BioAmp output and a common-ground setup for safe analog interfacing.

![ESP32 EMG prosthetic arm wiring diagram](./images/wiring-diagram.svg)

```mermaid
flowchart LR
  EMG[BioAmp EXG Pill] -->|Analog EMG signal| ESP32[ESP32 GPIO 34]
  ESP32 -->|PWM signal| S1[MG90S Servo 1]
  ESP32 -->|PWM signal| S2[MG90S Servo 2]
  ESP32 -->|PWM signal| S3[MG90S Servo 3]
  ESP32 -->|PWM signal| S4[MG90S Servo 4]
  ESP32 -->|PWM signal| S5[MG90S Servo 5]
  PSU[External 5V Supply] --> S1
  PSU --> S2
  PSU --> S3
  PSU --> S4
  PSU --> S5
  GND[Common Ground] --- EMG
  GND --- ESP32
  GND --- PSU
```

## Important Wiring Notes

- BioAmp output goes to `GPIO 34`.
- Servo signal pins are `18`, `14`, `15`, `13`, and `19`.
- Servo power should come from an external 5V source.
- All ground lines must be connected together.
- BioAmp EXG Pill is used as an analog EMG source into the ESP32 ADC path.

## Wiring Summary Table

| Component | Connection |
|---|---|
| BioAmp EXG Pill OUT | ESP32 GPIO 34 |
| BioAmp EXG Pill GND | Common GND |
| Servo 1 signal | GPIO 18 |
| Servo 2 signal | GPIO 14 |
| Servo 3 signal | GPIO 15 |
| Servo 4 signal | GPIO 13 |
| Servo 5 signal | GPIO 19 |
| Servo VCC | External 5V |
| Servo GND | Common GND |

For the full pin map, see [hardware/pin_connections.md](../hardware/pin_connections.md).

Source reference:

- Upside Down Labs BioAmp EXG Pill README: `upsidedownlabs/BioAmp-EXG-Pill`
- Upside Down Labs Servo Control example: `upsidedownlabs/Muscle-BioAmp-Arduino-Firmware`
