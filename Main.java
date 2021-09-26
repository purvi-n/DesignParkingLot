/*
	main method initializes object of Parser class and calls parseFileInput method using input file
*/

import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		Parser input = new Parser();
		input.parseFileInput("input.txt");
	}
}