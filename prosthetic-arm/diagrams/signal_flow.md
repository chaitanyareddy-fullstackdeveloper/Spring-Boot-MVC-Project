# Signal Flow

```mermaid
flowchart LR
  Muscle[Forearm muscle activity] --> Electrode[EMG electrodes]
  Electrode --> BioAmp[BioAmp EXG Pill]
  BioAmp --> ESP32ADC[ESP32 ADC on GPIO 34]
  ESP32ADC --> Logic[Threshold comparison]
  Logic --> PWM[Servo PWM outputs]
  PWM --> Hand[Finger and thumb motion]
  Hand --> Grip[Open or grasp object]
```

## Signal Path Explanation

The BioAmp EXG Pill captures the EMG signal from the forearm. The ESP32 reads the analog value, compares it with a threshold, and drives the servos to either open or close the hand.
