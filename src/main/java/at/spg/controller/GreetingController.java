package at.spg.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import at.spg.model.Greeting;
import at.spg.repository.GreetingRepository;

@RequestMapping(path="/greeting")
@RestController
public class GreetingController {
	
	@Autowired
	private GreetingRepository greetingRepository;

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Greeting(counter.incrementAndGet(),
              String.format(template, name));
  }
  
  @GetMapping
  public @ResponseBody Iterable<Greeting> getAllGreetings() {
    // This returns a JSON or XML with the users
    return greetingRepository.findAll();
  }
}