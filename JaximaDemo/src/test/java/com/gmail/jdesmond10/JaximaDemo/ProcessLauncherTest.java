package com.gmail.jdesmond10.JaximaDemo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class ProcessLauncherTest {

	String absolutePath;
	MaximaProcessLauncher mpl;

	@Before
	public void setUp() throws Exception {

		// TODO, this logic should be a testable unit of code itself, not logic
		// within the test aye?
		final URL resource = this.getClass().getClassLoader()
				.getResource("HWC/hwc.bat");
		absolutePath = Paths.get(resource.toURI()).toString();

		mpl = new MaximaProcessLauncher();
	}

	@Test
	public void foundHWCTest() {
		assertTrue(
				"Path of hwc.bat should contain its parent folder name, HWC.",
				absolutePath.contains("HWC"));
		assertTrue("Path of hwc.bat should end with .bat",
				absolutePath.endsWith(".bat"));
	}

	@Test
	public void launchProcessTest() {
		MaximaProcess mp;
		try {
			mp = mpl.launch(absolutePath);
			assertTrue(mp.alive());
		} catch (final IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void launchProcessFailureTest() {
		// Testing the launch of a nonexistant path.
		try {
			mpl.launch("aFakePath.bat");
			fail("An exception should have been thrown");
		} catch (final IOException e) {
			// This should cause an IOException because there is no .bat there.
			assertTrue(e.getMessage().contains("CreateProcess error=2"));
			// Specifically an error which has a message with that info in its
			// error message.
		}
	}

	@Test
	public void launchProcessAndKillTest() {
		// TODO this isn't anything to do with Launcher...
		MaximaProcess mp;
		try {
			mp = mpl.launch(absolutePath);
			assertTrue(mp.alive());
		} catch (final IOException e) {
			fail(e.getLocalizedMessage());
			mp = null; // Just to get rid of compile errors
		}

		mp.kill();
		assertFalse(mp.alive());
		fail();
	}

}
