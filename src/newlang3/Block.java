package newlang3;

public class Block extends Node {
	Environment env;
	LexicalAnalyzer lex;

	private Block(Environment env) {
		this.env = env;
		lex = env.getInput();
	}

	static Node isMatch(Environment env, LexicalUnit first) {
		Node node = IFNode.isMatch(env, first);
		if (node != null) {
			return node;
		}
		node = DoNode.isMatch(env, first);
		return node;
	}

	@Override
	public boolean Parse() {
		LexicalUnit lu = lex.get();
		Node node = IFNode.isMatch(env, lu);

		if (node != null) {
			return node.Parse();
		}

		node = DoNode.isMatch(env, lu);

		if (node != null) {
			return node.Parse();
		}

		/*
		 * node = Block.isMatch(env,lu);
		 */
		return false;
	}
}
