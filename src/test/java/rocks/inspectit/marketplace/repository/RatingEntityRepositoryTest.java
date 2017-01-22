package rocks.inspectit.marketplace.repository;

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

import rocks.inspectit.marketplace.repository.jpa.entity.RatingEntity;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@ActiveProfiles("test,h2_db")
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingEntityRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private RatingEntityRepository repository;

	@Test
	public void findAll() throws Exception {
		final List<RatingEntity> entityList = (List) repository.findAll();
		assertThat(entityList.size(), is(14));
	}

	@Test
	public void findByUuid() throws  Exception{
		final RatingEntity entity = repository.findOne( UUID.fromString("da647f2c-3ff0-4c7b-8a92-5e4a2ecb741f"));
		assertThat(entity.getRatingAsNumber(), is(5));
	}

}