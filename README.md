# DECIDE: A Launch Intercepter Program

A group implementation of the DECIDE() function which determines interceptor launch signals based on radar tracking data.

## Description 
The program evaluates up to 100 planar data points, representing radar echoes, against fifteen *Launch Interceptor Conditions* (LICs) to form a *Condition Met Vector* (CMV). By combining these results with an input *Logical Connector Matrix* (LCM) and an input *Preliminary Unlocking Vector* (PUV), the program calculates a *Final Unlocking Vector* (FUV) to generate a final "YES" or "NO" launch decision.

## Getting Started

### Dependencies
* Apache Maven: 3.9.12
* Java version: 25.0.2

### Installation

```bash
mvn clean install
```

### Running the program

## Way of working
We would consider our way of working to be in the "in use" state. This is in part supported by the fact that the practices and tools are being used to do real work and that they support communication and collaboration. However, a weakness with our current approach is that many of our practices have only been established verbally. This causes some practices to not always be followed, and sometimes it becomes unclear what practices should be followed when none have been made. To move forward, we should document our way of working so it becomes easier to follow. We should also establish practices where there are gaps to streamline our work.

## Contribution

## License
[MIT](LICENSE)
