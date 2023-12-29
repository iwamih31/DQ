package iwamih31.RPGwin;

import javax.swing.table.AbstractTableModel;

public class Shop extends AbstractTableModel{
	
	
	private static Object itemList[][] = {
		{""  ,""        ,"" ,""          ,"｛ "},
		{"１.","ヒロポン","(", 20 * lev(),"G) "},
		{"２.","毒針"    ,"(", 50 * lev(),"G) "},
		{"３.","爆弾"    ,"(",100 * lev(),"G) "},
		{"４.","命の玉"  ,"(",300 * lev(),"G) "}
	};

	private Object[][] shop;
	private static String[] shopText;

	private static Member member;

	public static void store( ) {
		for(Object[] stocks : itemList){
			for(Object stock : stocks){
				System.out.print(stock);
			}
		}
	System.out.println("｝＊()=金額");
	System.out.println("");
	System.out.print( "[所持金＝" + Main.getG() +"G] ");
	int inp = Input.input(itemList);

//	System.out.println("");

	switch ( inp ) {

		case 1:
			int stock1 = (Integer) Item.getItemList()[inp][3];
			int g1 = (Integer) Shop.getItemList()[inp][3];
			if ( Main.getG()  <  g1) {
				Battle.pTable();
				System.out.println( "" );
				System.out.println( itemList[inp][1] + "を買うには、お金が足りません  ×××" );
				Input.ent();
			} else {
				Battle.pTable();
				System.out.println( "" );
				System.out.print(Main.getName()  + "は[" + g1 +"G]支払って[" );
				System.out.println( itemList[inp][1] + "]を手に入れた!" );
				Input.ent();
				Main.setG(Main.getG() - g1);
				//intに変換したアイテム残数に１を足して元の要素に代入
				Item.getItemList()[inp][3] = stock1 + 1;
			}
			break;

		case 2:
			int stock2 = (Integer) Item.getItemList()[inp][3];
			int g2 = (Integer) Shop.getItemList()[inp][3];
			if ( Main.getG()  <  g2) {
				Battle.pTable();
				System.out.println( "" );
				System.out.println( itemList[inp][1] + "を買うには、お金が足りません  ×××" );
				Input.ent();
			} else {
				Battle.pTable();
				System.out.println( "" );
				System.out.print(Main.getName()  + "は" + g2 +"G支払って" );
				System.out.println( itemList[inp][1] + "を手に入れた!" );
				Input.ent();
				Main.setG(Main.getG() - g2);
				//intに変換したアイテム残数に１を足して元の要素に代入
				Item.getItemList()[inp][3] = stock2 + 1;
			}
			break;

		case 3:
			int stock3 = (Integer) Item.getItemList()[inp][3];
			int g3 = (Integer) Shop.getItemList()[inp][3];
			if ( Main.getG()  <  g3) {
				Battle.pTable();
				System.out.println( "" );
				System.out.println( itemList[inp][1] + "を買うには、お金が足りません  ×××" );
				Input.ent();
			} else {
				Battle.pTable();
				System.out.println( "" );
				System.out.print(Main.getName()  + "は" + g3 +"G支払って" );
				System.out.println( itemList[inp][1] + "を手に入れた!" );
				Input.ent();
				Main.setG(Main.getG() - g3);
				//intに変換したアイテム残数に１を足して元の要素に代入
				Item.getItemList()[inp][3] = stock3 + 1;
			}
			break;

		case 4:
			int stock4 = (Integer) Item.getItemList()[inp][3];
			int g4 = (Integer) Shop.getItemList()[inp][3];
			if ( Main.getG()  <  g4) {
				Battle.pTable();
				System.out.println( "" );
				System.out.println( itemList[inp][1] + "を買うには、お金が足りません  ×××" );
				Input.ent();
			} else {
				Battle.pTable();
				System.out.println( "" );
				System.out.print(Main.getName()  + "は" + g4 +"G支払って" );
				System.out.println( itemList[inp][1] + "を手に入れた!" );
				Input.ent();
				Main.setG(Main.getG() - g4);
				//intに変換したアイテム残数に１を足して元の要素に代入
				Item.getItemList()[inp][3] = stock4 + 1;
			}
			break;

		default:
			Battle.pTable();
			System.out.println( "" );
			System.out.println( "「またのお越しをお待ちしております。」(^。^)y-.。o○" );
			Input.ent();
		}
		System.out.println( " 財布の中身は[" + Main.getG()  + "G]です" );
		System.out.println( "" );
		System.out.println( " ありがとうございました♪ (*^o^*)y-.。o○" );
		Input.ent();
	}


	private static int lev() {
		// TODO 自動生成されたメソッド・スタブ
		Member[] party = Main.getParty();
		int lev = (party[0].getLev() + party[1].getLev() + party[2].getLev() + party[3].getLev()) / (party.length);
		return lev;
	}


	public static void sellItem(Member user, int sellItem) {

		int item = sellItem + 1;
		// TODO 自動生成されたメソッド・スタブ
		for (Object[] stocks : itemList) {
			for (Object stock : stocks) {
				System.out.print(stock);
			}
		}
		System.out.println("｝＊()の半額=売値");
		System.out.println("");
		System.out.print("[所持金＝" + Main.getG() + "G] ");
//	int inp = Input.input(itemList);

//	System.out.println("");

		String name = user.getName();

		if (user == Main.getHu()) name = Main.getName();

		int stock = (Integer) Item.getItemList()[item][3];

		for (int i = 1; i < itemList.length; i++) {

			itemList[i][3]=(Integer)itemList[i][3]/2;

		}
		int g = (Integer) itemList[item][3];

		Battle.pTable();
		System.out.println("");
		System.out.print(name + "は[" + itemList[item][1] + "]を売って[");
		System.out.println(g + "G]手に入れた!");
//					Input.ent();
		Main.setG(Main.getG() + g);

		Item.getItemList()[item][3] = stock - 1;

//		Input.ent();

		System.out.println(" 財布の中身は[" + Main.getG() + "G]です");
		System.out.println("");
		System.out.println(" ありがとうございました♪ (*^o^*)y-.。o○");
//		Input.ent();

		shopText = new String[3];
		shopText[0] = (name + "は[" + itemList[item][1] + "]を売って[" + g + "G]手に入れた!");
		shopText[1] = (" 財布の中身は[" + Main.getG() + "G]です");
		shopText[2] = (" ありがとうございました♪ (*^o^*)y-.。o○");

	}


	public static void buyItem(Member user, int buyItem) {

		int item = buyItem + 1;
		// TODO 自動生成されたメソッド・スタブ
		for (Object[] stocks : itemList) {
			for (Object stock : stocks) {
				System.out.print(stock);
			}
		}
		System.out.println("｝＊()=買い値");
		System.out.println("");
		System.out.print("[所持金＝" + Main.getG() + "G] ");
//	int inp = Input.input(itemList);

//	System.out.println("");

		String name = user.getName();

		if (user == Main.getHu()) name = Main.getName();

		int stock = (Integer) Item.getItemList()[item][3];

		for (int i = 1; i < itemList.length; i++) {

			itemList[i][3]=(Integer)itemList[i][3];

		}
		int g = (Integer) itemList[item][3];

		Battle.pTable();
		System.out.println("");
		System.out.print(name + "は[" + itemList[item][1] + "]を[");
		System.out.println(g + "G]で手に入れた!");
//					Input.ent();
		Main.setG(Main.getG() - g);

		Item.getItemList()[item][3] = stock + 1;

//		Input.ent();

		System.out.println(" 財布の中身は[" + Main.getG() + "G]です");
		System.out.println("");
		System.out.println(" ありがとうございました♪ (*^o^*)y-.。o○");
//		Input.ent();

		shopText = new String[3];
		shopText[0] = (name + "は[" + itemList[item][1] + "]を[" + g + "G]で手に入れた!");
		shopText[1] = (" 財布の中身は[" + Main.getG() + "G]です");
		shopText[2] = (" ありがとうございました♪ (*^o^*)y-.。o○");

	}

	public static void buyWapon(int i) {
		
		member = Main.getParty()[i];
		
		Battle.pTable();
		System.out.println( "" );
		System.out.print( "どれにいたしましょう？" );
		System.out.print( "１." );
		member.wep(1);
		System.out.print( "(" + (( member.getWp() + 1) * 200 * 1 * 1 ) + "Ｇ)" );
		System.out.print( "２." );
		member.wep(2);
		System.out.print( "(" + (( member.getWp() + 2) * 200 * 2 * 2 ) + "Ｇ)" );
		System.out.print( "３." );
		member.wep(3);
		System.out.print( "(" + (( member.getWp() + 3) * 200 * 3 * 3 ) + "Ｇ)" );
		System.out.println( "" );
		System.out.println( "" );
//		int inp = Input.input();
		
		Screen.setMessage( "どれにいたしましょう？" );
		
		Screen.setMenu(new String[]{member.wepName(1),member.wepName(2),member.wepName(3)});
		
//		buyWaponWhich(inp);
	}

	static void buyWaponWhich(int inp) {
		
		if (0 < inp && inp < 4) {
			
			int wp = member.getWp();
			int inpWp = wp + inp;
			
			String buyWapon = member.wepName(inp);
			int buyPrice = (inpWp * inpWp * inpWp * 200) - ((inpWp - 1) * 1000);
			
			String sellWapon = member.wepName(0);
			int sellPrice = ((wp * wp * wp * 200) - ((wp - 1) * 1000)) / 2;

			if (Main.getG() < buyPrice) {

				Battle.pTable();
				System.out.println("");
				System.out.println(buyWapon + "を買うには、お金が足りません  ×××");
//				Input.ent();

				shopText = new String[]{ buyWapon + "を買うには、お金が足りません  ×××" };
			} else {

				shopText = new String[2];

				Battle.pTable();
				System.out.println("");
				System.out.print("「");
				member.wep(inp);
				System.out.print("だね。まいどあり!!");
//				Input.ent();
				shopText[0] = buyWapon + "だね。まいどあり!!";

				Main.setG(Main.getG() - buyPrice + sellPrice);

				Battle.pTable();
				System.out.println("");
				member.wep();
				System.out.print("は(" + sellPrice + "Ｇ)で引き取ってくれた・・・");
//				Input.ent();
				member.setWp(member.getWp() + inp);

				shopText[1] = sellWapon + "は(" + sellPrice + "Ｇ)で引き取ってくれた・・・";
			}

		} else {

			Battle.pTable();
			System.out.println("");
			System.out.println("また来てくださいね （*^o^*）");
//			Input.ent();

			shopText = new String[]{ "また来てくださいね （*^o^*）" };

		}
	}

	public static void wapon(Member who) {
		Battle.pTable();
		System.out.println( "" );
		System.out.print( "どれにいたしましょう？" );
		System.out.print( "１." );
		who.wep(1);
		System.out.print( "(" + (( who.getWp() + 1) * 200 * 1 ) + "Ｇ)" );
		System.out.print( "２." );
		who.wep(2);
		System.out.print( "(" + (( who.getWp() + 2) * 200 * 3 ) + "Ｇ)" );
		System.out.print( "３." );
		who.wep(3);
		System.out.println( "(" + (( who.getWp() + 3) * 200 * 9 ) + "Ｇ)" );
		System.out.println( "" );
		int inp = Input.input();

		switch (inp) {
		case 1:
			Battle.pTable();
			System.out.println( "" );
			System.out.print( "「" );
			who.wep(inp);
			System.out.print( "だね。まいどあり!!" );
			Input.ent();
			Battle.pTable();
			System.out.println( "" );
			who.wep();
			System.out.print( "は(" + (( who.getWp()) * 100 ) + "Ｇ)で引き取ってくれた・・・" );
			Input.ent();
			who.setWp(who.getWp() + inp);
			break;
		case 2:
			Battle.pTable();
			System.out.println( "" );
			System.out.print( "「" );
			who.wep(inp);
			System.out.print( "だね。まいどあり!!" );
			Input.ent();
			Battle.pTable();
			System.out.println( "" );
			who.wep();
			System.out.print( "は(" + (( who.getWp()) * 100 ) + "Ｇ)で引き取ってくれた・・・" );
			Input.ent();
			who.setWp(who.getWp() + inp);
			break;
		case 3:
			Battle.pTable();
			System.out.println( "" );
			System.out.print( "「" );
			who.wep(inp);
			System.out.print( "だね。まいどあり!!" );
			Input.ent();
			Battle.pTable();
			System.out.println( "" );
			who.wep();
			System.out.print( "は(" + (( who.getWp()) * 100 ) + "Ｇ)で引き取ってくれた・・・" );
			Input.ent();
			who.setWp(who.getWp() + inp);
			break;
		default:
			Battle.pTable();
			System.out.println( "" );
			System.out.println( "また来てくださいね （*^o^*）" );
			Input.ent();
			break;
		}
	}


	public static void  item( int clickItem ) {

		System.out.println( clickItem );
	}

	public void setItemList(Object itemList[][]) {
		Shop.itemList = itemList;
	}

	public static Object[][] getItemList() {
		return itemList;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		setShop();
		return shop[0][columnIndex].getClass();
	}

	@Override
	public String getColumnName(int column) {
		setShop();
		return (String) shop[column][0];
	}

	@Override
	public int getRowCount() {
		setShop();
		return shop.length;
	}


	@Override
	public int getColumnCount() {
		setShop();
		return shop[0].length;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		setShop();
		return shop[rowIndex][columnIndex];
	}

	private void setShop() {
		shop = new Object[][] {
			{itemList[1][1], "["+itemList[1][3]+"]" },
			{itemList[2][1], "["+itemList[2][3]+"]" },
			{itemList[3][1], "["+itemList[3][3]+"]" },
			{itemList[4][1], "["+itemList[4][3]+"]" }
		};
	}

	public static String[] getShopText() {
		return shopText;
	}
}

