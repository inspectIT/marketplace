package rocks.inspectit.marketplace.dao.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */

@ActiveProfiles("test,h2_db")
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class KeywordEntityRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private KeywordEntityRepository repository;

	@Test
	public void findAll() throws Exception {
		final List<KeywordEntity> entityList = (List) repository.findAll();
		assertThat(entityList.size(), is(4));
	}

	@Test
	public void findByUuidAndProductEntities() throws Exception {
		final KeywordEntity entity = repository.findOne(UUID.fromString("33c8d8e5-9096-4d7d-814f-6c1ed0601bcd"));
		assertThat(entity.getProductEntityList().size(), is(4));

		assertThat(entity.getProductEntityList().get(0).getName(), is("product  1"));
		assertThat(entity.getProductEntityList().get(1).getName(), is("product  2"));
		assertThat(entity.getProductEntityList().get(2).getName(), is("product  6"));
		assertThat(entity.getProductEntityList().get(3).getName(), is("product  8"));
	}

}
