package newlang3;

public class Stmt extends Node {
	public Stmt(Environment my_env){
		type = NodeType.STMT;
		env = my_env;
		next = null;
	}

	// ステートメントとunitがマッチしているか
	static Node isMatch(Environment env, LexicalUnit unit) {
//		LexicalUnit next;
//		Node handler;

//        if (u.getType() == LexicalType.END)
//        	return new Node(NodeType.END);
//    	handler = AssignStmt.isMatch(env, u);
//    	if (handler != null) return handler;
////    	handler = SubCall.isMatch(env, u);
//   	if (handler != null) return handler;
//		handler = ForStmt.isMatch(env, u);
//    	return handler;

		switch (unit.getType()) {
		case NAME:
			return new NameNode();
		case FOR:
			return new ForNode();
		case END:
			return new EndNode();
		default:
			return null;
		}

	}

	@Override
	public boolean Parse() {
		return false;
	}
}
