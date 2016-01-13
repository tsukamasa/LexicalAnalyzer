package newlang3;

import java.util.ArrayList;
import java.util.List;

public class StatementList extends Node {
	List<Node> StatementList = new ArrayList<Node>();
	LexicalAnalyzer lex;
	Environment env;

	public StatementList(Environment env, LexicalAnalyzer lex) {
		this.env = env;
		this.lex = lex;
	}

	public Node isMatch(Environment env , LexicalUnit lu){
		return new Stmt(env);
	}

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
