package newlang3;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\C0113093\\workspace\\practical_programming\\src\\newlang3\\input.txt");

		LexicalAnalyzer la = new LexicalAnalyzerImpl(file);
		LexicalUnit lu;
		do {
			lu = la.get();
			System.out.println(lu);
		} while (lu.type != LexicalType.EOF);

	}

}
