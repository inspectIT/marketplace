package rocks.inspectit.marketplace.dao.repository;

import org.hamcrest.CoreMatchers;
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

import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;

/**
 * Make sure to stay in test context, therefore use test and h2 profiles
 * for test application.properties by adding {@link ActiveProfiles} annotation.
 * <p/>
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
@ActiveProfiles("test,h2_db")
@WebAppConfiguration
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//@TestExecutionListeners({ DbUnitTestExecutionListener.class })
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserEntityRepository repository;

	@Test
	public void findAll() throws Exception {
		final List<UserEntity> entityList = (List) repository.findAll();
		assertThat(entityList.size(), is(3));
	}

	@Test
	public void findAllWithProducts() throws Exception {
		List<UserEntity> list = (List<UserEntity>) this.repository.findAll();
		assertThat(list.get(0).getProductEntityList().size(), CoreMatchers.is(30));
		assertThat(list.get(1).getProductEntityList().size(), CoreMatchers.is(17));
		assertThat(list.get(2).getProductEntityList().size(), CoreMatchers.is(0));
	}

	@Test
	public void findAllWithrRatings() throws Exception {
		List<UserEntity> list = (List<UserEntity>) this.repository.findAll();
		assertThat(list.get(0).getRatingEntityList().size(), CoreMatchers.is(23));
		assertThat(list.get(1).getRatingEntityList().size(), CoreMatchers.is(25));
		assertThat(list.get(2).getRatingEntityList().size(), CoreMatchers.is(0));
	}

	@Test
	public void findUserEntityByUUID() throws Exception {
		assertThat((repository.findUserEntityByUserUuid(UUID.fromString("c848cad7-c3b2-499a-9688-8fb2a261313b"))).getEmail(), is("nik_n@me.com"));
	}

	@Test
	public void findUserEntityWithRole() throws Exception {
		final UserEntity entity = repository.findOneByName("nik n");
		assertThat(entity.getRoleEntity().getRole(), not("user"));
		assertThat(entity.getRoleEntity().getRole(), is("admin"));
	}

	@Test
	public void testInsert() throws Exception {
		final UserEntity entity = new UserEntity();
		entity.setName("awesome o");
		entity.setEmail("awesome_o@email");
		entity.setIp("192.168");

		this.repository.save(entity);

		final List<UserEntity> list = (List<UserEntity>) repository.findAll();
		assertThat(list.size(), is(4));
		// assertNull(list.get(3).getLastLoginDate());

		entity.setIp("192");
		this.repository.save(entity);

		final UserEntity testEntity = repository.findOneByName("awesome o");
		assertNotNull(testEntity.getLastLoginDate());
	}

}
