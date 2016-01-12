package newlang3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.HashMap;
import java.util.Map;

public class LexicalAnalyzerImpl implements LexicalAnalyzer {
	private FileReader fr;
	private PushbackReader pbr;
	Map<String, LexicalUnit> reserved;

	public LexicalAnalyzerImpl(File file) {
		try {
			fr = new FileReader(file);
			pbr = new PushbackReader(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		reserved = new HashMap<String, LexicalUnit>();
		reserved.put("DO", new LexicalUnit(LexicalType.DO));
		reserved.put("UNTIL", new LexicalUnit(LexicalType.UNTIL));
		reserved.put("LOOP", new LexicalUnit(LexicalType.LOOP));
		reserved.put("END", new LexicalUnit(LexicalType.END));
	}

	@Override
	public LexicalUnit get() {
		LexicalType lt;
		try {
			while (true) {
				char c = getChar();
				if (c == (char)-1) { // エラーも入ってるけどまあいいや
					return new LexicalUnit(LexicalType.EOF);
				}
				if (c == ' ' || c == '\t' || c == '\r') {
					continue;
				}
				if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
					unGetChar(c);
					return getString();
				} else if (c >= '0' && c <= '9') {
					unGetChar(c);
					return getNumber();

				} else if (c == '"') {
					unGetChar(c);
					return getLiteral();
				} else if (c == '\n') {
					lt = LexicalType.NL;
					return new LexicalUnit(lt);
				} else if (c == '=') {
					return new LexicalUnit(LexicalType.EQ);
				} else if (c == '<') {
					return new LexicalUnit(LexicalType.LT);
				} else if (c == '>') {

				} else if (c == '.') {
					return new LexicalUnit(LexicalType.DOT);
				} else if (c == '+') {
					return new LexicalUnit(LexicalType.ADD);
				} else if (c == '-') {
					return new LexicalUnit(LexicalType.SUB);
				} else if (c == '*') {
					return new LexicalUnit(LexicalType.MUL);
				} else if (c == '/') {
					return new LexicalUnit(LexicalType.DIV);
				} else if (c == '(') {
					return new LexicalUnit(LexicalType.RP);
				} else if (c == ')') {
					return new LexicalUnit(LexicalType.LP);
				} else if (c == ',') {
					return new LexicalUnit(LexicalType.COMMA);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new LexicalUnit(LexicalType.EOF);
	}

	public char getChar() {
		if (pbr == null) {
			return (char) -1;
		}
		try {
			int in = pbr.read();

			if (in < 0) {
				pbr.close();
				pbr = null;
				return (char) -1;
			}
			return (char) in;
		} catch (IOException e) {
			pbr = null;
			return (char) -1;
		}
	}

	public void unGetChar(char c) {
		try {
			if (c != -1) {
				pbr.unread(c);
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public LexicalUnit getString() {
		String buffer = "";

		while (true) {
			char c = getChar();
			if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
				buffer += c;
			} else {
				unGetChar(c);
				break;
			}
		}
		if (reserved.get(buffer) != null) {
			return reserved.get(buffer);
		} else {
//			System.out.println("name");
			return new LexicalUnit(LexicalType.NAME, new ValueImpl(buffer));
		}
	}

	public LexicalUnit getNumber() {
		String buffer = "";
		boolean isDot = false;

		while (true) {
			char c = getChar();
			if (c >= '0' && c <= '9') {
				buffer += c;
			} else if (c == '.' && !isDot) {
				isDot = true;
				buffer += c;
			} else {
				unGetChar(c);
				break;
			}
		}
		if (isDot) {
			return new LexicalUnit(LexicalType.DOUBLEVAL, new ValueImpl(Double.parseDouble(buffer)));
		}
		return new LexicalUnit(LexicalType.INTVAL, new ValueImpl(Integer.parseInt(buffer)));
	}

	public LexicalUnit getLiteral() {
		String buffer = "";
		boolean flag = false;

		while (true) {
			char c = getChar();
			if (c == '"' && !flag) {
				flag = true;
				continue;
			} else if (c == '"' && flag) {
				break;
			} else if (flag) {
				buffer += c;
			}
		}
		return new LexicalUnit(LexicalType.LITERAL, new ValueImpl(buffer));
	}

	@Override
	public boolean expect(LexicalType type) {

		return false;
	}

	@Override
	public void unget(LexicalUnit token) {
	}

}
