package iwamih31.DQ;

public class Console {

	/**
	 * コンソールに引数 string を表示（上下に空白行付き）
	 * @param string - コンソールに表示する文字列
	 */
	public static void ___OUT___(String string) {
		System.out.println("");
		System.out.println(string);
		System.out.println("");
	}

	public static void items() {
		System.out.print("＊持ち物< ");
		Item.items();
		System.out.println(">");
	}
}
