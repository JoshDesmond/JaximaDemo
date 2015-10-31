package com.gmail.jdesmond10.JaximaDemo;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UseMaximaIT {

	MaximaProcess mp;

	@Before
	public void setUp() throws Exception {
		final String programLocation = "\"C:\\Program Files (x86)\\Maxima-sbcl-5.37.2\\bin\\maxima.bat\"";
		mp = new MaximaProcessLauncher().launch(programLocation);
	}

	@Test
	public void test() throws IOException {
		assertEquals("10", mp.call("5+5;"));
		assertEquals("5", mp.call("varx : 5;"));
		assertEquals("15", mp.call("varx + 10;"));
		assertEquals("1432", mp.call("1430 + 1 +1;"));
	}

	@Test
	public void testRetrieveSingleAnswer() {
		assertEquals("10", MaximaProcess.retrieveSingleLineAnswer("(%o1)                                 10"));
		assertEquals("143", MaximaProcess.retrieveSingleLineAnswer("(%o3)                                 143"));
	}

	@After
	public void tearDown() {
		mp.kill();

	}
}
