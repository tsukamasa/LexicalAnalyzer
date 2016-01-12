package newlang3;

public class Expression extends Node{//四則演算　演算子の性質は二つのものを演算する。式クラス
/*
 * operand  : 定数、変数、かっこ
 * operator : + - * /
 *
 *operandを区別
 *
 * TODO：binaryクラスを作る
 */

	public Node getOperand(){
		return null;
	}

	public boolean Parse(){
		Node n1 = getO	perand();
		LexicalUnit opr = lex.get();
		switch (opr.getValue()) {
		case "":
			Node n2 = getOperand();
			break;

		default:
			break;
		}


		return false;
	}

}
