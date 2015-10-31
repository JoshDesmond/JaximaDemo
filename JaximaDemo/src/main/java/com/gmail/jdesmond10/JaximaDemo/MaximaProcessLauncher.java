package com.gmail.jdesmond10.JaximaDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MaximaProcessLauncher {

	/**
	 * Launches a given file (given via filepath), and returns a MaximaProcess
	 * which can be used to control the process
	 * 
	 * @param absolutePath
	 *            a string for a file path of an executable file.
	 * @return the MaximaProcess which represents the launch of this file.
	 * @throws IOException
	 *             if an I/O error occurs, (i.e if absolutePath can't be found).
	 */
	public MaximaProcess launch(final String absolutePath) throws IOException {

		final ProcessBuilder pb = new ProcessBuilder();
		pb.command(absolutePath); // sets the command to be launching the given
		// file

		final Process process = pb.start();


		final BufferedReader r = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		final BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
				process.getOutputStream()));

		return new MaximaProcess(r,w);
	}

}
