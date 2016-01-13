package newlang3;
//一番大きなくくりの文
//分解すると <stmt_list> に分けられる

public class Program extends Node {
	Environment env;
	LexicalAnalyzer lex;

	public Program(Environment env,LexicalAnalyzer lex){
		this.env = env;
		this.lex = lex;
	}


	public static Node isMatch(Environment env, LexicalAnalyzer lex){
		return new Program(env,lex);
	}

	public boolean Parse() {

		return true;
	}

}
