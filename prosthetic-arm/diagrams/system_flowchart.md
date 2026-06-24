# System Flowchart

```mermaid
flowchart TD
  A[Start] --> B[Read EMG value]
  B --> C{Muscle value above threshold?}
  C -->|Yes| D[Close all fingers]
  C -->|No| E[Open all fingers]
  D --> F[Print debug value]
  E --> F
  F --> B
```

## Control Summary

- Above threshold = flex = close fist
- Below threshold = relaxed = open hand

This simple binary model is a good first control strategy for a prosthetic hand prototype.
