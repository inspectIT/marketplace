package rocks.inspectit.marketplace.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

import rocks.inspectit.marketplace.config.ActiveProfile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * add {@link WebAppConfiguration} to avoid {@link org.h2.jdbc.JdbcSQLException}
 * caused by {@link java.net.BindException}, because <em>H2</em> WebPort 9071, will already be used.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
@Ignore("Test case will fail on prod due to jvm bind exception")
@ActiveProfiles(ActiveProfile.DEV)
@WebAppConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestExecutionListeners({ DbUnitTestExecutionListener.class })
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserEntityRepository repository;

	@Test
	public void findUserEntityByUUID() throws Exception {
		assertThat((repository.findUserEntityByUserUuid(UUID.fromString("c848cad7-c3b2-499a-9688-8fb2a261313b"))).getEmail(), is("spam@me.de"));
	}

}