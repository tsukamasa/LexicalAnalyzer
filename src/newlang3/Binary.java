package newlang3;

public class Binary extends Node {
	/*
	 * 1 + 3 のような式を扱うもの
	 * a + 1 みたいにright operand left
	 * 二分木みたいに実装
	 *
	 * スタックにoperandとoperatorを入れて次を読む
	 */

	Node right;		// ★a + 1
	Node opr;		// a ★+ 1
	Node left;		// a + ★1


}
