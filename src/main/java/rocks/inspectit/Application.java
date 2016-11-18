package rocks.inspectit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Use {@link SuppressWarnings} to suppress all PMD warnings, since this entry-class will trigger a lot of false positive warnings.
 *
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.0-SNAPSHOT
 */
@SuppressWarnings("PMD")
@SpringBootApplication
public class Application {

	/**
	 * Spring-Boot entry point.
	 *
	 * @param args as {@link String} array - arguments on startup. Usually empty.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
