package newlang3;

import java.util.ArrayList;
import java.util.List;

public class StatementList extends Node {
	List<Node> StatementList = new ArrayList<Node>();
	LexicalAnalyzer lex;

	// parseメソッドをオーバーライド
	@Override
	public boolean Parse() {
		LexicalUnit lu = lex.get();
		Node node = Stmt.isMatch(env, lu);
		if (node != null) {
			return node.Parse();
		} else {
			node = Block.isMatch(env, lu);
			if (node != null) {
				return node.Parse();
			} else {
				return false;
			}
		}
	}
}
