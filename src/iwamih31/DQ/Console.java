package iwamih31.DQ;

public class Console {

	/**
	 * コンソールに引数 string を表示（上下に空白行付き）
	 * @param string - コンソールに表示する文字列
	 */
	public static void _____OUT_____(String string) {
		System.out.println("");
		System.out.println(string);
		System.out.println("");
	}

	public static void items() {
		System.out.print("＊持ち物< ");
		Item.items();
		System.out.println(">");
	}

	public static void menu_List(Object[] menu_List) {
		String sMenu = "[ ";
		int menu_Number = 1;
		for (Object item : menu_List) {
			sMenu += (menu_Number + ". " + item + ", ");
		}
		sMenu += "]";
		sMenu = sMenu.replace(", ]", " ]");
		_____OUT_____(sMenu);
	}
}
