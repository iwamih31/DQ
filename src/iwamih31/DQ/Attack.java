package iwamih31.DQ;

public class Attack {
	private static String menuList[][] = {
		{"どうしますか","? ｛ "    },
		{"1."          ,"探す "    },
		{"2."          ,"使う "    },
		{"3."          ,"買い物 "  },
		{"4."          ,"泊まる ｝"}
	};

	public static void command( ) {
		for(String[] menus : menuList){
			for(String menu : menus){
			System.out.print(menu);
			}
		}
	}

	public void setMenuList(String[][] menuList) {
		Attack.menuList = menuList;
	}

	public static Object[][] getMenuList() {
		return menuList;
	}
}
