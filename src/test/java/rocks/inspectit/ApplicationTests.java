package rocks.inspectit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import rocks.inspectit.marketplace.config.ActiveProfile;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.0-SNAPSHOT
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(ActiveProfile.PROD)
public class ApplicationTests {

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
		assertThat(env.getActiveProfiles(), is(new String[] { ActiveProfile.PROD }));

	}
}
