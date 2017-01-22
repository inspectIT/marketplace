package rocks.inspectit.marketplace.mvc.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Make sure to stay in test context, therefore use test and h2 profiles
 * for test application.properties by adding {@link ActiveProfiles} annotation.
 * <p/>
 * Simple test case for rest controller.
 * Checks if HttpStatusCode is OK and
 * JSON contains expected values
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.1-SNAPSHOT
 */
@ActiveProfiles("test,h2_db")
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class WhoAmIControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testOk() throws Exception {
		mockMvc.perform(get(WhoAmIController.URL_MAPPING)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(200));
	}

	@Test
	public void testResponseFormat() throws Exception {
		mockMvc.perform(get(WhoAmIController.URL_MAPPING)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(200))
				.andExpect(jsonPath("$").value(hasKey("name")))
				.andExpect(jsonPath("$").value(hasValue("inspectit.rocks.marketplace-test")))
				.andExpect(jsonPath("$").value(hasKey("version")));
	}

}