package rocks.inspectit.marketplace.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

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

import java.util.List;

import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;

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
@ActiveProfiles("h2")
@WebAppConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestExecutionListeners({ DbUnitTestExecutionListener.class })
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ProductEntityRepository repository;

	@Test
	public void findAll() throws Exception {
		List<ProductEntity> entityList = repository.findAll();
		assertThat(entityList.size(), is(72));
		assertThat(entityList.get(0).getUserEntity().getEmail(), is("spam@me.de"));
	}

	@Test
	public void findTop10ByTag() throws Exception {
		List<ProductEntity> entityList = repository.findTop10ByTag("promoted");
		assertThat(entityList.size(), is(10));
		entityList = repository.findTop10ByTag("rated");
		assertThat(entityList.size(), is(10));
		entityList = repository.findTop10ByTag("recent");
		assertThat(entityList.size(), is(10));
		entityList = repository.findTop10ByTag("featured");
		assertThat(entityList.size(), is(10));
	}

	@Test
	public void findByTag() throws Exception {
		List<ProductEntity> entityList = repository.findByTag("promoted");
		assertThat(entityList.size(), is(15));
		entityList = repository.findByTag("rated");
		assertThat(entityList.size(), is(13));
		entityList = repository.findByTag("recent");
		assertThat(entityList.size(), is(10));
		entityList = repository.findByTag("featured");
		assertThat(entityList.size(), is(18));
	}

}