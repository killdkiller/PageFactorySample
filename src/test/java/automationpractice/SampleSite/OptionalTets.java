package automationpractice.SampleSite;

import java.util.Optional;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OptionalTets {
	
	@Test
	public void whenOrElseWorks_thenCorrect() {
	    String nullName = "sam";
	    String name = Optional.ofNullable(nullName).orElse("john");
	    System.out.println(name);
//	    Assert.assertEquals("john", name);
	}

}
