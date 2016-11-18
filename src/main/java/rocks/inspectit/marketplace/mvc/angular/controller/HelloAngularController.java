package rocks.inspectit.marketplace.mvc.angular.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * For test purposes...
 * Will be deleted in future version!
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.2-SNAPSHOT
 */
@RestController
public class HelloAngularController {

	@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
	@RequestMapping("/test")
	public MyTmpJson getDefault() {
		return new MyTmpJson("test", "success");
	}

	private static class MyTmpJson {
		private final String name;
		private final String value;

		public MyTmpJson(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}
	}
}
