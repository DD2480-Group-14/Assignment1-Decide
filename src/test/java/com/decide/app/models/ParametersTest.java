import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.decide.app.model.Parameters;

/**
 * Unit test for Parameters
 */
public class ParametersTest {
	@Test
	public void testSetAndGetParameters() {
		Parameters parameters = new Parameters(1.0, 2.0, 3.0, 4.0, 5, 6, 7.0, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17.0, 18.0, 19.0);
		assertEquals(parameters.LENGTH1(), 1.0);
		assertEquals(parameters.PTS(), 5);
	}
}
