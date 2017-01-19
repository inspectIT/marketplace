package rocks.inspectit.marketplace.mvc.advice;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import rocks.inspectit.marketplace.config.ActiveProfile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.2-SNAPSHOT
 */
@Ignore("Test case will fail on prod due to jvm bind exception")
@ActiveProfiles(ActiveProfile.DEV)
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GeneralControllerAdviceTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void before() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void shouldReturnErrorView() throws Exception {
		mockMvc.perform(get("/123"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
}