# Mechanical Design

The mechanical design should prioritize smooth finger movement, light weight, and low servo strain.

## Design Goals

- Open the fingers fully when relaxed
- Close all fingers together into a fist when flexed
- Keep the servo load balanced
- Avoid binding in the finger joints

## Suggested Build Approach

1. Use one servo per finger or one servo for grouped finger linkage depending on the frame design.
2. Route tendons or linkages so the fingers return cleanly when the servo releases tension.
3. Limit travel so the servos do not stall at the ends of motion.
4. Test one finger at a time before connecting the full hand.

## Servo Mounting Tips

- Use rigid mounting points for the servo bodies.
- Keep the linkage straight wherever possible.
- Leave enough slack to prevent the wire or tendon from snapping under load.
- Make sure every finger has a repeatable open position.

## Practical Notes

- A fist motion is easier to achieve if the mechanical linkage is lightweight.
- If the hand closes unevenly, adjust the linkage lengths before changing the firmware.
- Reduce friction before increasing servo torque.
