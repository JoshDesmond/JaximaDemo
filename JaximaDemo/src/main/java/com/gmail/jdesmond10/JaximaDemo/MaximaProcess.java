package com.gmail.jdesmond10.JaximaDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class MaximaProcess {

	private boolean alive;
	BufferedReader r;
	BufferedWriter w;

	public MaximaProcess(final BufferedReader reader,
			final BufferedWriter writer) {
		alive = true;
		this.r = reader;
		this.w = writer;
		try {
			r.readLine();
			r.readLine();
			r.readLine();
			r.readLine();
			r.readLine();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean alive() {
		// TODO Auto-generated method stub
		return alive;
	}

	/**
	 * Destroys the maxima process by calling quit(), and closing the Buffered Reader.
	 */
	public void kill() {
		try {
			w.write("quit;");
			w.flush();
			w.close();
			r.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		alive = false;
	}

	/**
	 * Calls Maxima using the given string, and returns a string representing
	 * whatever Maxima returns.
	 * 
	 * @param string
	 *            A command in the form "5 + 5;". Must end in a semicolon.
	 * @return A string in the form "10"
	 * @throws IOException
	 */
	public String call(final String string) throws IOException {
		w.write(string);
		w.flush();
		System.out.println("First line: " + r.readLine());
		return retrieveSingleLineAnswer(r.readLine());
	}


	/**
	 * Parses the answer from a Maxima return string. That is, converts something in the form:
	 * "(%o1)                                 10" -> "10"
	 * @param s
	 */
	public static String retrieveSingleLineAnswer(String s) {
		s = s.substring(6);

		return s.trim();
	}


}
