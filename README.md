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

#### Testing the package
To run tests
```
mvn test
```

#### Example usage of the package 

Add the following package to your dependencies in `pom.xml`
```xml
<dependency>
    <groupId>com.decide.app</groupId>
    <artifactId>decide</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Example usage of `decide()`
```java
import java.util.Arrays;

import com.decide.app.Main;
import com.decide.app.model.Point;
import com.decide.app.model.Connectors;
import com.decide.app.model.Parameters;

public class ExampleUsage {
	public static void main(String[] args) {
		int numpoints = 4;
		Point[] points = new Point[] { new Point(1.0, 2.0), new Point(3.0, 4.0), new Point(5.0, 6.0),
				new Point(7.0, 8.0) };

		Parameters defaultParameters = new Parameters();

		Connectors[][] lcm = new Connectors[15][15];
		for (int i = 0; i < lcm.length; i++) {
			Arrays.fill(lcm[i], Connectors.AND);
		}

		boolean[] puv = new boolean[15];

		Main main = new Main(numpoints, points, defaultParameters, lcm, puv);
		main.decide();
	}
}
```

## Way of working
We would consider our way of working to be in the "in use" state. This is in part supported by the fact that the practices and tools are being used to do real work and that they support communication and collaboration. However, a weakness with our current approach is that many of our practices have only been established verbally. This causes some practices to not always be followed, and sometimes it becomes unclear what practices should be followed when none have been made. To move forward, we should document our way of working so it becomes easier to follow. We should also establish practices where there are gaps to streamline our work.

## Contribution

### Melker Trané
- Implemented LIC5, LIC12, LIC14. 
- Wrote our way of working. 
- Reviewed around 1/2 of other team members PRs.

### Edwin Nordås Jogensjö
- Implemented LIC2, LIC3, LIC9 and LIC13.
- Implemented the calculation of the PUM.
- Implemented DistanceMatrix.
- Created Main.java and the decide method.

### Maria Pettersson
- Implemented LIC4, LIC6, including associated helper method and unit tests.
- Wrote project description.

### Vidar Nykvist
- Implemented LIC0, LIC1 and LIC11.
- Implemented calculation of FUV. 
- Wrote "Getting Started"

## License
[MIT](LICENSE)
