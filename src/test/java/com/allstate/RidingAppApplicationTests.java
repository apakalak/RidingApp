package com.allstate;

import com.allstate.services.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@SpringBootTest
@Suite.SuiteClasses({
		CityServicesTest.class,
		DriverServicesTest.class,
		CarServicesTest.class,
		PassangerServicesTest.class,
		TripServicesTest.class
})
public class RidingAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}
