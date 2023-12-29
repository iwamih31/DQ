package iwamih31.RPGwin;

public class Magic extends Ex{

	private static Object exList[][] = {
		{"*" ,"妖術名"  ,"=","使用MP","｛ "},
		{"1.","瞑想"    ,"=",   0    ,"  " },
		{"2.","死の術"  ,"=",  10    ,"  " },
		{"3.","炎の術"  ,"=", 100    ,"  " },////////////////100
		{"4.","血の契約","=",   0    ,"  " }
	};

	Magic(Character memb) {
		super(memb);
//		itemList[1][1] ="瞑想"    ; itemList[1][3] =  0;
//		itemList[2][1] ="死の術"  ; itemList[2][3] = 10;
//		itemList[3][1] ="炎の術"  ; itemList[3][3] = 10;
//		itemList[4][1] ="血の契約"; itemList[4][3] =  0;
		itemList=exList;
//		exMenu();
//		spell();
	}

	public void spell() {
//		for(Object[] stocks : itemList){
//			for(Object stock : stocks){
//				System.out.print(stock);
//			}
//		}
//		System.out.print("｝");
//
//		job = Input.input();
//
//		useEx =  itemList[ job ][ 1 ];
//
//		useMp = (Integer)itemList[ job ][ 3 ];
//
//		//System.out.println("");

		switch (job) {

		case 1:
			praying();
			break;
		case 2:
			death();
			break;

		case 3:
			fire();
			break;

		case 4:
			desolation();
			break;
		default:
			System.out.println("なにもしなかった");

		}
	}

	private void fire() {
		arrayClear();
		if (Battle.getfMode() == 0) {
			Battle.pTable();
			System.out.println("");
			System.out.println("敵が見当たらない ・・・");
//			Input.ent();
//			spell();
			
			getArray().add("敵が見当たらない ・・・");
			
		} else {
			if (mp < useMp) {
//				System.out.println(user.getName() + "のMP=" + mp + " 消費MP=" + useMp);//////////////////
//				System.out.println("");
//				System.out.println(useEx + "を行うには力が足りません ×××");
////				Input.ent();
////				spell();
//				
//				getArray().add(useEx + "を行うには力が足りません ×××");
				
				notMp();
				
			} else {
				Battle.pTable();
				System.out.println("");
				System.out.println(name + "は" + useEx + "を行った・・・");
//				Input.ent();
				
				getArray().add(name + "は" + useEx + "を行った・・・");
				
				for (int i = 0; i < 4; i++) {
					Monster mo = Battle.mons[i];
					if (mo.getHp() > 0) {
						int r = new java.util.Random().nextInt(6);
						int g = new java.util.Random().nextInt(5);
						int dmg = (r * (20 - g) + lev * ep * 2);
						mo.setHp(mo.getHp() - dmg);
						System.out.println("★★★★★★★★★★★★★★★★★★★★");
						System.out.println(mo.getName() + "に" + dmg + "のダメージ!!!");
						System.out.println("＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠");
//						Input.ent();
						
						getArray().add(mo.getName() + "に" + dmg + "のダメージ!!!");
						
						if (mo.getHp() < 1) {
							Main.setG(Main.getG() + mo.getGp());
							user.setExp(user.getExp() + mo.getExp());
							System.out.println(name + "は" + mo.getName() + "を倒した!!!");
							System.out.println( "" );
//							Input.ent();
							
							getArray().add(name + "は" + mo.getName() + "を倒した!!!");
							
							System.out.print(name + "は " + mo.getGp() + " Ｇと "+ mo.getExp() + " Ｐの経験値を手に入れた!!!");
							System.out.println("  [Exp = " + user.getExp() + "] [G = "+ Main.getG() + "]");
//							Input.ent();
							
							getArray().add(name + "は " + mo.getGp() + " Ｇと "+ mo.getExp() + " Ｐの経験値を手に入れた!!!");
							
						}
					}
				}
			}
		}
		setExText(getArray());
	}

	private void death() {
		if (Battle.getfMode() == 0) { ///////////// //アイテムの時と位置が違う
			Battle.pTable();
			System.out.println("");
			System.out.println("敵が見当たらない ・・・");
//			Input.ent();
//			spell();
		} else {
			if (mp < useMp) {
				System.out.println(user.getName() + "のMP=" + mp + " 消費MP=" + useMp);//////////////////
				System.out.println("");
				System.out.println(useEx + "を行うには力が足りません ×××");
//				Input.ent();
//				spell();
			} else {
				Battle.mList();
				System.out.println("");
				System.out.println("どのモンスターに行いますか？");
				System.out.println("");

				exText = new String[]{useEx + "を、どのモンスターに行いますか？"};
				
				Screen.setMenu(Battle.mNa());
				
				Screen.setMode(22503);
				
				
//				int who = Input.input()-1;

//				death(who);
			}
		}
	}

	static void death(int who) {

		Magic.exText = new String[2];

		System.out.println("＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠＠");
		System.out.println("");
		System.out.print(name + "は");
		System.out.println(useEx + "を行った・・・");
//		Input.ent();

		exText[0] = name + "は" + useEx + "を行った・・・";

		int r = new java.util.Random().nextInt(20) * user.getLev() / 2;
		if (r < 10) {
			Battle.mList();
			System.out.println("");
			System.out.println(useEx + "は効かなかった");
//			Input.ent();

			exText[1] = useEx + "は効かなかった";

		} else {
			Battle.getMons()[who].setHp(1);
			mp=(mp - (Integer) useMp);
			System.out.println(Battle.getMons()[who].getName() + "は瀕死の状態!!!");
//			Input.ent();

			exText[1] = Battle.getMons()[who].getName() + "は瀕死の状態!!!";
		}
	}
}
