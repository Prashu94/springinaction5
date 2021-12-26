package taco.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import taco.Ingredient;
import taco.Ingredient.Type;
import taco.Taco;

@Slf4j
/**
 * @Controller
 * - Designates the class to be scanned by Spring.
 * **/
@Controller
/**
 * @RequestMapping 
 * - Annotation defines the general purpose request handling
 * - At class level means that the api path with /design must be controlled through this class.
 * **/
@RequestMapping("/design")
public class DesignTacoController {
	
	/**@ModelAttribute
	 * - This annotated method is always run before the @GetMapping/@PostMapping
	 * annotated method is called.
	 * - Here, the method will initialize the Ingredient List 
	 * and add attributes to the Model object(which ferries the data between the controller and view)
	 * **/
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
				  new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				  new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				  new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				  new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				  new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				  new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				  new Ingredient("CHED", "Cheddar", Type.CHEESE),
				  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				  new Ingredient("SLSA", "Salsa", Type.SAUCE),
				  new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
				);
		Type [] types = Ingredient.Type.values();
		for(Type type: types) {
			model.addAttribute(type.toString().toLowerCase(), 
					filterByType(ingredients, type));
		}
	}
	
	/**@GetMapping
	 * - annotated method will be called when GET request on /design is called.
	 * - here the method just returns the string "design" and adds the attribute desing to the model.**/
	@GetMapping
	public String showDesignForm(Model model) {
		model.addAttribute("design", new Taco());
		return "design";
	}
	
	/**@PostMapping
	 * - annotated method will be called when POST request is made from the view to the controller.
	 * - accepts the Taco object model
	 * - accpets the Errors object to be checked if there are any errors.**/
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors) {
		if(errors.hasErrors()) {
			return "design";
		}
		
		log.info("Processing design: "+design);
		return "redirect:/orders/current";
	}
	
	// utility method to add attribute to the Model according to the type.
	private List<Ingredient> filterByType(List<Ingredient> ingredients,
			Type type){
		return ingredients.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}
}
