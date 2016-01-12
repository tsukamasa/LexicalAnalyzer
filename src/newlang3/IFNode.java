package newlang3;

public class IFNode extends Node {
	Environment env;
	LexicalAnalyzer lex;
	Node cond, body;

	static Node isMatch(Environment env, LexicalUnit first) {
		if (first.getType() != LexicalType.IF) {
			return null;
		}
		return new IFNode();
	}

	@Override
	public boolean Parse() {
		LexicalUnit lu = lex.get();
		lu = lex.get();
		cond = Cond.isMatch(env, lu);

		if (cond == null) {
			return false;
		}

		if (cond.Parse() == false) {
			return false;
		}

		lu = lex.get();
		if(lu.getType() != LexicalType.THEN){
			return false;
		}

		lu = lex.get();
		if(lu.getType() != LexicalType.NL){
			body = Stmt.isMatch(env,lu);
			if(body.Parse() != true){
				return false;
			}
		}

		lu = lex.get();
		if(lu.getType() != LexicalType.ELSE){
			//NLだったら
			return (lu.getType() == LexicalType.NL);
		}

		//ELSE文の処理
		return false;
	}
}
