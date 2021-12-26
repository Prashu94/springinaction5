package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Controller
 * - Primary purpose is to identify this class as a component for component scanning.
 * - Annotations like @Component, @Service and @Repository all fall under the component scanning.
 * **/
@Controller
public class HomeController {

		@GetMapping("/") // indicates the HTTP GET method is received for the root path.
		public String home() {
			return "home";
		}
}
