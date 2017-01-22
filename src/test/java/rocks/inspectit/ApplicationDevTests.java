package rocks.inspectit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Make sure to stay in test context, therefore use test and h2 profiles
 * for test application.properties by adding {@link ActiveProfiles} annotation.
 * <p/>
 *
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.1-SNAPSHOT
 */
@ActiveProfiles("test,h2_db")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationDevTests {

	@Autowired
	private Environment env;

	@Test
	public void contextLoads() {
		final String empty = "";
		assertTrue(empty.isEmpty());
		assertThat(env.getDefaultProfiles(), is(new String[] { "default" }));
	}

	/**
	 * @since 1.0.1-SNAPSHOT
	 */
	@Test
	public void testEnvironment() {
		assertThat(env.getActiveProfiles(), is(new String[] { "test", "h2_db" }));

	}
}
