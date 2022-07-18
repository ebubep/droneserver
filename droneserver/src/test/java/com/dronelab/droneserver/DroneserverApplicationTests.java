package com.dronelab.droneserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DroneserverApplicationTests {

	@Test
	void contextLoads() {
	}
        
        @Test
        void testAll(){
            System.out.println("being tested");
            assertEquals("hello","hello");
        }

}
