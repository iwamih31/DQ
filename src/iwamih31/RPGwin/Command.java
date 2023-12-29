package iwamih31.RPGwin;

public class Command {
	private static String[ ][ ] menuList = {
		{"どうしますか","?｛ "    },
		{"1."          ,"探す "    },
		{"2."          ,"使う "    },
		{"3."          ,"買い物 "  },
		{"4."          ,"宿屋 "}
	};



	public static void command( ) {
		System.out.println( "               ／           ＼" );
		for( String[ ] menus : menuList ){
			for( String menu : menus ){
			System.out.print(menu);
			}
		}
		System.out.println( "" );
		System.out.println( "           ／                   ＼" );

	}

	/*public static void  menu( int clickMenu ) {

		System.out.println( clickMenu );
	}*/

	public void setMenuList(String[][] menuList) {
		Command.menuList = menuList;
	}

	public static Object[][] getMenuList() {
		return menuList;
	}

	public static String[] menu() {
		// TODO 自動生成されたメソッド・スタブ
		String[] menu = new String[menuList.length - 1];
		for (int i = 0; i < menu.length; i++) {
			menu[i] = menuList[i + 1][1];
		}

		return menu;
	}

}
