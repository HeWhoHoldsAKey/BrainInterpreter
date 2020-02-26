package Editor;

import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {
	private int progPoint;
	private int arrayPoint;

	// Level 2 of let this please be over
	@SuppressWarnings("null")
	public Interpreter(String str) {
		progPoint = 0;
		arrayPoint = 0;

		ArrayList<Character> prog = new ArrayList<Character>();
		ArrayList<Integer> loops = new ArrayList<Integer>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (char c : str.toCharArray()) {
			switch (c) {
			// Move left
			case '<':
				prog.add(c);
				break;
			// Move right
			case '>':
				prog.add(c);
				break;
			// Display ASCII char
			case '.':
				prog.add(c);
				break;
			// Input ASCII Char
			case ',':
				prog.add(c);
				break;
			// Add to cell
			case '+':
				prog.add(c);
				break;
			// Subtract to cell
			case '-':
				prog.add(c);
				break;
			// End loop
			case ']':
				prog.add(c);
				break;
			// Start loop
			case '[':
				prog.add(c);
				break;
			}
		}

		a.add(0);

		while (progPoint != prog.size()) {
			switch (prog.get(progPoint)) {
			case '<':
				// Cant go below 1
				if (arrayPoint >= 1) {
					arrayPoint--;
				} else {
					System.out.print("You wnt too low nugget boi");
				}
				break;
			case '>':
				// Go as big as you want
				arrayPoint++;
				if (a.size() >= arrayPoint) {
					a.add(0);
				}
				break;
			case '.':
				// I dont like where this is going.............

				Scanner input = new Scanner(System.in);
				String tempA = input.next();

				// NOW TO CHARACTER and no that was not caps lock
				Character tempB = tempA.charAt(0);

				// Converts it to ascii
				a.set(arrayPoint, Character.getNumericValue(tempB));

				break;
			case ',':
				// Yay ascii conversion is easy
				int temp = (int) a.get(arrayPoint);
				char tempChar = (char) temp;
				Editor.setOutputBox(null, tempChar);
				break;
			case '+':
				// Addition?
				a.set(arrayPoint, a.get(arrayPoint) + 1);
				if (a.get(arrayPoint) == 256)
					a.set(arrayPoint, 0);
				break;
			case '-':
				// Yay we are subtracting
				a.set(arrayPoint, a.get(arrayPoint) - 1);
				if (a.get(arrayPoint) == -1)
					a.set(arrayPoint, 255);
				break;
			case ']':
				// If it equals 0 the move on in live but other than that keep going.
				if (a.get(arrayPoint) == 0) {
					loops.remove(loops.size() - 1);
				} else {
					progPoint = loops.get(loops.size() - 1);
				}
				break;
			case '[':
				// This works by making a massive list of places the pointer needs to go if
				// there is a loop.
				loops.add(progPoint);
				break;
			}
			progPoint++;
		}

		// Yay its done hopefully
		System.out.println(a);
		try {
			// Well this causes an issue but we fixed it with 2 levels of not caring anymore
			// and wanting this to be over...
			Editor.setOutputBox(a, (Character) null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
