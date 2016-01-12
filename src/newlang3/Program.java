package newlang3;

public class Program extends Node {
	Environment env;

	public Program(Environment env){
		this.env = env;
	}


	public static Node isMatch(Environment env, LexicalUnit u){
		return new Program(env);
	}

}
