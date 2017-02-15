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

import rocks.inspectit.marketplace.dao.repository.jpa.entity.TagEntity;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Make sure to stay in test context, therefore use test and h2 profiles
 * for test application.properties by adding {@link ActiveProfiles} annotation.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@ActiveProfiles("test,h2_db")
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagEntityRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private TagEntityRepository repository;

	@Test
	public void findAll() throws Exception {
		final List<TagEntity> entityList = (List) repository.findAll();
		assertThat(entityList.size(), is(2));
	}

}