package iwamih31.RPGwin;

public class Wonder extends Ex {

	private static Object exList[][] = {
		{"*" ,"妖術名","=","使用MP","｛ "},
		{"1.","感謝"  ,"=",   10   ,"  " },
		{"2.","奇跡"  ,"=",   10   ,"  " },
		{"3.","蘇生"  ,"=",   100  ,"  " },
		{"4.","覚醒"  ,"=",   100  ,"  " }
	};

	Wonder(Character memb) {
		super(memb);
//		itemList[1][1] ="感謝"; itemList[1][3] = 10;
//		itemList[4][1] ="覚醒"; itemList[4][3] = 100;
		itemList=exList;
//		exMenu();
//		spell();
	}

	public void spell() {

		switch ( job ) {

			case 1:
				if ( mp < useMp ) {
					System.out.println(user.getName() + "のMP=" + mp + " 消費MP=" + useMp);//////////////////
					System.out.println( "" );
					System.out.println( useEx + "を行うには気力が足りません ×××" );
					Input.ent();
					spell();
				} else {
					Battle.pTable();
					System.out.println( "" );
					System.out.println( name  + "は" +useEx + "を行った・・・" );
					Input.ent();
					Battle.pTable();
					System.out.println( "" );
					System.out.println( "仲間の攻撃力が上昇した!!!" );
					Battle.setK(2);
					user.setMp(user.getMp() - useMp);
					Input.ent();
				}
				break;

			case 2:
				heal( );
				break;

			case 3:
				resu( );
				break;

			case 4:
				if ( mp >= lev*10 ) {
					System.out.println(user.getName() + "のMP=" + mp + " 消費MP=" + useMp);//////////////////
					System.out.println( "" );
					System.out.println( useEx + "の限界です ×××" );
					Input.ent();
					spell();
				} else {
					System.out.println( "" );
					System.out.println( name  + "は" +useEx + "を行った・・・" );
					System.out.println( "" );
					Input.ent();
					System.out.println( "" );
					System.out.println( name  + "は光を放ちながら空を飛んでいる!!!" );
					System.out.println( "" );
					Battle.setFly(3);
					user.setMp(user.getMp() - useMp);
					Input.ent();
				}
				break;

			default:
				System.out.println( "なにもしなかった" );

		}
	}
}
