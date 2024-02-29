package iwamih31.DQ;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

public class Main extends AbstractTableModel implements Serializable{

	static String sFile  = "sData.txt";

	private static String yName = "おぬし";

	private static String name = "チーム勇者" + yName;

	private static int g;

	private static String mName = "魔物たち";

	static int bHp = 3;

	private static String bName = "竜王";

	//配列要素名を簡略化
	private static Member[] party ;
	static Member fi;
	static Member he;
	static Member pr;
	static Member mg;

	private static Member hu;

	private static String get;

	static Main mai;

	private static Screen sc;

	private static String a;

	private static String[] pNa;

	static Object[][] pSt;

	private static int fiHP;

	private static int prHP;

	private static int mgHP;

	private static int innG;

	public static String[] innMenu;

	public static String[] innText;

	private static String[] doText;

	private static String[] text;

	private static Battle bat;

	private static ArrayList<String> array;

	private static Member select;

	private static int remG;

	Main(){

	}

	public static void main(String[] args) {///////////////////RPGスタート

		mai = new Main();///////////////////////////////////////////////////

		// eclipseConsole();//Eclipseでコンソールにフォーカスを移動＊Eclipse以外の時は削除して下さい＊

		party = new Member[4];
		setHu(new Human());
		bHp = 3;
		g = 0;

		Input.clear();///////////////////////////////////////////画面クリア

		File newfile = new File("sData.txt");////////セーブ用ファイル作成//////////
		try {
			newfile.createNewFile();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}//////////////////////////////////////////////////////////////////////////

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Screen.setTex("最初から始めますか？・・・");
				sc = new Screen("RPG");
				Screen.que();
			}
		});

		System.out.println("");
		System.out.println("");
		System.out.println("最初から始めますか？・・・ [ １. はい ][ ２. いいえ ]");
		System.out.println("");
	}

	public static void begin() {

		System.out.println("");
		System.out.println("");
		System.out.println("主人公の名前は何にしますか？");
		System.out.println("");

			name = "チーム勇者" + yName;

			fi = new Fighter();// ///////////////////キャラを実体化
			he = new Hero();
			pr = new Priest();
			mg = new Mage();

			party[0] = fi;// /////実体化したキャラを配列変数に代入
			party[1] = he;
			party[2] = pr;
			party[3] = mg;

			setFiHP(fi.getHp());
			setPrHP(pr.getHp());
			setMgHP(mg.getHp());

			party[0].setHp(0);
			party[2].setHp(0);
			party[3].setHp(0);

			pGet();

			bHp = 3;

			g = 0;

			array = new ArrayList<String>();

			System.out.println("");
			System.out.println("  ・・・ある日[ " + yName + " ]は、王様に呼び出された・・・");
	}

	static void pGet() {
		int pCount = 0;
		for (int i = 0; i < party.length; i++) {
			if (party[i].getHp() > 0) pCount++;
		}
		Common.___logOut___("現パーティ人数 = " + pCount);
		pNa = new String[pCount];
		pSt = new Object[3][pCount];
		int i = 0;
		while (i < pCount) {
			for (int j = 0; j < party.length; j++) {
				if (party[j].getHp() > 0) {
					getpNa()[i] = party[j].getName();
					pSt[0][i] = ("Lev = " + party[j].getLev());
					pSt[1][i] = ("HP = " + party[j].getHp());
					pSt[2][i] = ("MP = " + party[j].getMp());
					i = (i + 1);
				}
			}
		}
		Common.___logOut___("現パーティ最後尾 = " + getpNa()[i-1]);
	}

	private static void pSt() {
		pNa = new String[party.length];
		pSt = new Object[3][party.length];
		for (int i = 0; i < party.length; i++) {
			getpNa()[i] = party[i].getName();
			pSt[0][i] = ("Lev = " + party[i].getLev());
			pSt[1][i] = ("HP = " + party[i].getHp());
			pSt[2][i] = ("MP = " + party[i].getMp());
			if (party[i].getHp() < 1) {
				getpNa()[i] = party[i].getName() + "(死体)";
			}
		}
	}


	static void action(int select) {
		Common.___logOut___("Main.action( " + select +" ) します");
		Battle.pTable();//パーティーのステータスを表示
		party[0].setHp(party[0].getHp() - 1);
		party[1].setHp(party[1].getHp() - 1);
		party[2].setHp(party[2].getHp() - 1);
		party[3].setHp(party[3].getHp() - 1);
		party[0].setMp(party[0].getMp() + 1);
		party[1].setMp(party[1].getMp() + 1);
		party[2].setMp(party[2].getMp() + 1);
		party[3].setMp(party[3].getMp() + 1);
		if(party[1].getWp() == 9){////////////////////ムラマサ
			//攻撃力＋
			//防御力＋
		}
		if(party[1].getWp() == 9){////////////////////英雄の剣
			party[1].setHp(party[1].getHp() + 1);
			//クリティカルヒット率アップ
		}
		if(party[2].getWp() == 9){////////////////////神の十字架
			party[2].setHp(party[2].getHp() + 1);
			party[2].setMp(party[2].getMp() + 1);
		}
		if(party[3].getWp() == 9){////////////////////魔導師の杖
			party[3].setMp(party[3].getMp() + 2);
		}
		for (int j = 0; j < party.length; j++) {
			if (party[j].getHp() < 0) {
				party[j].setHp(0);
				party[j].setMp(0);
			}
		}
		Object[] choice;
		arrayClear();
		switch (select) {
		case 1:
			array.add(("―――――" + getName() + "は探検を続けた―――――"));
			setText(array);
			break;
		case 2:
			Battle.pTable();
			System.out.println("");
			System.out.println( "⇒どちらを使いますか？ [ 1. 道具 ] [ 2. 能力 ]");
			System.out.println("");
			message("⇒どちらを使いますか？");
			choice = new Object[]{"道具","能力"};
			button(choice);
			break;
		case 3 :
			System.out.print("＊持ち物< ");
			Item.items();
			System.out.println(">");
			System.out.println("");
			System.out.println("「いらっしゃいませ、御用は何でしょうか？ [ 1. 道具 ] [ 2. 武器 ]");
			System.out.println("");
			message("「いらっしゃいませ、御用は何でしょうか？」");
			choice = new Object[]{ "買う", "売る" };
			button(choice);
			break;
		case 4:
			innG();// /////////////////////////////宿代を計算
			Battle.pTable();
			message("⇒宿代は [ " + innG + "G ] ですが、お泊りになりますか？");
			System.out.println("");
			System.out.println("⇒宿代は [ " + innG
					+ "G ] ですが、お泊りになりますか？ [ 1.はい ][ 2.いいえ ][ 3.状態確認 ][ 4.復活の儀式 ]");
			System.out.println("");
			System.out.print("[所持金＝ " + g + "G ] ");
			innText = new String[3];
			innText[0] = name + "は、宿で体を休めた・・・";
			innText[1] = "♪♪♪旅の疲れが癒された ♪♪♪";
			innText[2] = "「またのお越しをお待ちしております。（*^o^*）」";
			break;
		default:
			Battle.pTable();
			System.out.println("");
			int ran = new java.util.Random().nextInt(100);
			if (ran < 10) {
				System.out.println("・・・もしかして、からかってます？笑");
			} else {
				System.out.println("＊できれば、1～4の数字でお願いします・・・");
			}
			Input.ent();
		}
		Battle.pTable();
		save();
	}

	private static void arrayClear() {
		if (array == null){
			array = new ArrayList<String>();
		}else{
			for (int i = array.size(); i > 0; i--) {
				array.remove(i-1);
			}
		}
	}

	private static void button(Object[] choices) {
		Screen.setMenu(choices);
	}

	static void shop(int select) {
		if (select == 1) {
			Battle.pTable();
			System.out.println("");
			System.out.print("何を買いますか？");
		} else {
			Battle.pTable();
			System.out.println("");
			System.out.print("何を売りますか？");
			Battle.pList();
			System.out.println("");
		}
		Object[] choice = new Object[]{ "道具", "武器" };

		button(choice);
	}

	static void buy(int select) {
		if (select == 1) {
			Battle.pTable();
			System.out.println("");
			System.out.print("どれを買いますか？");
		} else {
			Battle.pTable();
			System.out.println("");
			System.out.print("誰の武器を買いますか？");
			Battle.pList();
			System.out.println("");
		}
	}

	static void sell(int select) {
		if (select == 1) {
			Battle.pTable();
			System.out.println("");
			System.out.print("どれを売りますか？");
		} else {
			Battle.pTable();
			System.out.println("");
			System.out.print("誰の武器を売りますか？");
			Battle.pList();
			System.out.println("");
		}
	}


	private static void wapon(int who) {
		switch (who) {
			case 1:
				Shop.wapon(party[0]);
				break;
			case 2:
				Shop.wapon(party[1]);
				break;
			case 3:
				Shop.wapon(party[2]);
				break;
			case 4:
				Shop.wapon(party[3]);
				break;
			default:
				Battle.pTable();
				System.out.println("");
				System.out.println(name + "は、何も買わずに店を出た・・・");
		}
	}


	static void use(int job) {
		if (job == 1) {
			Battle.pTable();
			System.out.println("");
			System.out.print("どのアイテムを使いますか？");
			Item.bag(getHu());
		} else {
			Battle.pTable();
			System.out.println("");
			System.out.print("誰が行いますか？");
			Battle.pList();
			System.out.println("");
		}
	}

	static void ex(int who) {
		System.out.println("");
		party[who].ex();
	}

	static void event() {
		Common.___logOut___("Main.event() します");
		System.out.println( "                 ／       ＼"           );
		System.out.println( "               ／           ＼"         );
		System.out.println(  "―――――" + getName() + "は探検を続けた―――――" );
		System.out.println( "           ／                   ＼"     );
		System.out.println( "         ／                       ＼"   );
		System.out.println( "" );
		int r = new java.util.Random ().nextInt(100);
		Common.___logOut___("r = " + r);
		if( r < 5) {
			setDoText(new String[3]);
			getDoText()[0] = ("「 ・・・!!? 」");
			getDoText()[1] = (name + "は、良い人に出会った♪");
			getDoText()[2] = ("「少し元気をもらった  ↑↑↑」");
			Screen.setMode(11);
			Battle.pTable();
			System.out.println("");
			System.out.print("      ");
			System.out.println("「 ・・・!!? 」");
			Battle.pTable();
			System.out.println("");
			System.out.print("      ");
			System.out.println(name + "は、良い人に出会った♪");
			healing();
		} else if (r < 10) {
			Screen.setMode(12);
			int c = new java.util.Random().nextInt(3);
			String comment = null;
			switch (bHp) {
				case 3 :
					if 		(c == 0) comment = ("「竜王っていう魔物が現れて世界を破壊しているらしい・・・  (＋_＋)」");
					else if (c == 1) comment = ("「英雄の剣は切れ味抜群なんだってさ・・・  (・ｏ・)」");
					else if (c == 2) comment = ("「勇者" + getyName() + "、どうか世界を救って下さいね・・・  (Ｔ_Ｔ)」");
					break;
				case 2 :
					if 		(c == 0) comment = ("「あの倒された竜王は影武者だったらしいよ・・・  (＋_＋)」");
					else if (c == 1) comment = ("「竜王には影武者が何人かいるみたいだな・・・  (・ｏ・)」");
					else if (c == 2) comment = ("「勇者" + getyName() + "、どうか世界を救って下さいね・・・  (Ｔ_Ｔ)」");
					break;
				case 1 :
					if 		(c == 0) comment = ("「あの竜王もまた影武者だったみたいだな・・・いつになったら平和になるのかねぇ？  (＋_＋)」");
					else if (c == 1) comment = ("「竜王は影武者が居なくなってあせっているみたいだな・・・  (・ｏ・)」");
					else if (c == 2) comment = ("「勇者" + getyName() + "、どうか世界を救って下さいね・・・  (Ｔ_Ｔ)」");
					break;
			}
			setDoText(new String[3]);
			getDoText()[0] = ("「 ・・・!!? 」");
			getDoText()[1] = (comment);
			getDoText()[2] = ("・・・");
			Battle.pTable();
			System.out.println("");
			System.out.print("      ");
			System.out.println("「 ・・・!!? 」");
			Battle.pTable();
			System.out.println("");
			System.out.print("      ");
			System.out.println(comment);
		} else if (r < 75) {
			Screen.setMode(1);
			setDoText(new String[0]);
			Battle.pTable();
			System.out.println( "" );
			System.out.print( "      " );
			System.out.println( "「・・・・・何も起きない、少し疲れた  (+_+)」" );
		} else if( r < 95) {
			Screen.setMode(14);
			setDoText(new String[1]);
			getDoText()[0] = ( "「!!!★★★★★!!? モンスターが現れた !!!★★★★★!!!」" );
			try {
				int s = 50;//★表示スピード（速い＜遅い）
				Robot rb = new Robot();
				System.out.println( "「                      !!!★!!!                      」" );
				System.out.println( "「                      !!!★!!!                      」" );
				System.out.println( "「                      !!!★!!!                      」" );
				System.out.println( "「                      !!!★!!!                      」" );
				System.out.println( "「                      !!!★!!!                      」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「                    !!!★★★!!!                    」" );
				System.out.println( "「                    !!!★★★!!!                    」" );
				System.out.println( "「                    !!!★★★!!!                    」" );
				System.out.println( "「                    !!!★★★!!!                    」" );
				System.out.println( "「                    !!!★★★!!!                    」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「                  !!!★★★★★!!!                  」" );
				System.out.println( "「                  !!!★★★★★!!!                  」" );
				System.out.println( "「                  !!!★★★★★!!!                  」" );
				System.out.println( "「                  !!!★★★★★!!!                  」" );
				System.out.println( "「                  !!!★★★★★!!!                  」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「                !!!★★★★★★★!!!                」" );
				System.out.println( "「                !!!★★★★★★★!!!                」" );
				System.out.println( "「                !!!★★★★★★★!!!                」" );
				System.out.println( "「                !!!★★★★★★★!!!                」" );
				System.out.println( "「                !!!★★★★★★★!!!                」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「              !!!★★★★★★★★★!!!              」" );
				System.out.println( "「              !!!★★★★★★★★★!!!              」" );
				System.out.println( "「              !!!★★★★★★★★★!!!              」" );
				System.out.println( "「              !!!★★★★★★★★★!!!              」" );
				System.out.println( "「              !!!★★★★★★★★★!!!              」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「            !!!★★★★★?!★★★★★!!!            」" );
				System.out.println( "「            !!!★★★★★?!★★★★★!!!            」" );
				System.out.println( "「            !!!★★★★★?!★★★★★!!!            」" );
				System.out.println( "「            !!!★★★★★?!★★★★★!!!            」" );
				System.out.println( "「            !!!★★★★★?!★★★★★!!!            」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「          !!!★★★★★!!?!!!★★★★★!!!          」" );
				System.out.println( "「          !!!★★★★★!!?!!!★★★★★!!!          」" );
				System.out.println( "「          !!!★★★★★!!?!!!★★★★★!!!          」" );
				System.out.println( "「          !!!★★★★★!!?!!!★★★★★!!!          」" );
				System.out.println( "「          !!!★★★★★!!?!!!★★★★★!!!          」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「        !!!★★★★★!!?★★!!!★★★★★!!!        」" );
				System.out.println( "「        !!!★★★★★!!?★★!!!★★★★★!!!        」" );
				System.out.println( "「        !!!★★★★★!!?★★!!!★★★★★!!!        」" );
				System.out.println( "「        !!!★★★★★!!?★★!!!★★★★★!!!        」" );
				System.out.println( "「        !!!★★★★★!!?★★!!!★★★★★!!!        」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「      !!!★★★★★!!?★★★★!!!★★★★★!!!      」" );
				System.out.println( "「      !!!★★★★★!!?★★★★!!!★★★★★!!!      」" );
				System.out.println( "「      !!!★★★★★!!?★★★★!!!★★★★★!!!      」" );
				System.out.println( "「      !!!★★★★★!!?★★★★!!!★★★★★!!!      」" );
				System.out.println( "「      !!!★★★★★!!?★★★★!!!★★★★★!!!      」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「    !!!★★★★★!!?★★★★★★!!!★★★★★!!!    」" );
				System.out.println( "「    !!!★★★★★!!?★★★★★★!!!★★★★★!!!    」" );
				System.out.println( "「    !!!★★★★★!!?★★★★★★!!!★★★★★!!!    」" );
				System.out.println( "「    !!!★★★★★!!?★★★★★★!!!★★★★★!!!    」" );
				System.out.println( "「    !!!★★★★★!!?★★★★★★!!!★★★★★!!!    」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「  !!!★★★★★!!?★★★★★★★★!!!★★★★★!!!  」" );
				System.out.println( "「  !!!★★★★★!!?★★★★★★★★!!!★★★★★!!!  」" );
				System.out.println( "「  !!!★★★★★!!?★★★★★★★★!!!★★★★★!!!  」" );
				System.out.println( "「  !!!★★★★★!!?★★★★★★★★!!!★★★★★!!!  」" );
				System.out.println( "「  !!!★★★★★!!?★★★★★★★★!!!★★★★★!!!  」" );
				rb.delay( s );
				Input.clear();
				System.out.println( "「!!!★★★★★!!? モンスターが現れた !!!★★★★★!!!」" );
				} catch (AWTException e) {
				e.printStackTrace();
			}
		} else {
			Screen.setMode(15);
			setDoText(new String[1]);
			getDoText()[0] = ( "「 ・・・!!? 」" );
			Battle.pTable();
			System.out.println( "" );
			System.out.print( "      " );
			System.out.println( "「 ・・・!!? 」" );
			getItem();
		}
		save();
	}

	static void battle() {
		bat= new Battle();
		bat.battle();
	}

	public static void healing() {
		if (party[0].getHp() > 0) party[0].setHp(party[0].getHp() + 40);
		if (party[1].getHp() > 0) party[1].setHp(party[1].getHp() + 30);
		if (party[2].getHp() > 0) party[2].setHp(party[2].getHp() + 20);
		if (party[3].getHp() > 0) party[3].setHp(party[3].getHp() + 10);
		Battle.pTable();
		System.out.println("");
		System.out.print("      ");
		System.out.println("「少し元気をもらった  ↑↑↑」");
	}

	static void innMenu(int i) {
		arrayClear();
		switch (i) {
			case 1 :
				if(getG() < innG) {
					System.out.print("お金が足りません");
				} else {
					setG(getG() - innG);
					Battle.pTable();
					System.out.println("");
					System.out.println(name + "は、宿で体を休めた・・・");
					array.add(name + "は、宿で体を休めた・・・");
					Battle.pTable();
					System.out.println("");
					System.out.println(name + "は、宿で体を休めた・・・");
					System.out.println("");
					Battle.pTable();
					scroll(10);
					Battle.inn();
					Battle.pTable();
					System.out.println("");
					System.out.println("♪♪♪旅の疲れが癒された ♪♪♪");
					array.add("♪♪♪旅の疲れが癒された ♪♪♪");
					Battle.pTable();
					System.out.println("");
					System.out.println("[所持金＝ " + getG() + "G ]");
					array.add("[所持金＝ " + getG() + "G ]");
				}
				break;
			case 3 :
				Battle.status();
				Battle.pTable();
				System.out.println("");
				System.out.println(name + "は、宿を出た・・・");
				array.add(name + "は、宿を出た・・・");
				break;
			case 4 :
				Battle.pTable();
				System.out.println("");
				System.out.print("誰を復活させますか？");
				Battle.pList();
				System.out.println("");
				Screen.setMessage("誰を復活させますか？");
				break;
			default :
				Screen.setMessageEnt("「またのお越しをお待ちしております。（*^o^*）」");
		}
		Battle.pTable();
		System.out.println("");
		System.out.println("「またのお越しをお待ちしております。（*^o^*）」");
		array.add("「またのお越しをお待ちしております。（*^o^*）」");
		setText(array);
		save();
	}

	static void revive(int who) {
		arrayClear();
		select = Main.getParty() [ who ];
		if(select.getHp() > 0){
			Battle.pTable();
			System.out.println( "" );
			System.out.println( "勝手に殺したら可哀想だよ・・・ (~_~;)" );
			Screen.setMessageEnt( "勝手に殺したら可哀想だよ・・・ (~_~;)" );
		}else{
			remG = (party[who].getLev() * 200);
			System.out.println("  [所持金＝ " + getG() + "G ]");
			System.out.println("");
			System.out.println(remG + "Gかかるけど復活するかい？・・・ [ 1. はい ][ 2. いいえ ]");
			System.out.println("");
			Screen.setMode(4444);
			Screen.setMessage(remG + "Gかかるけど復活するかい？・・・");
			Screen.setMenu(new String[]{"はい","いいえ"});
		}
		setText(array);
	}

	static void reviveYes(int co) {
		arrayClear();
		switch (co) {
			case 1:
				Screen.setMode(445);
				if (Main.getG() < remG) {
					array.add("お金が足りません!!");
				} else {
					select.setHp((int) (select.getAp() * 5));
					select.setMp((int) (select.getEp() * 2));
					Main.setG(Main.getG() - remG);
					Battle.pTable();
					System.out.println("");
					System.out.println(select.getName() + "は生き返った!!!");
					array.add(select.getName() + "は生き返った!!!");
					Battle.pTable();
					System.out.println("");
					System.out.println("  [所持金＝ " + getG() + "G ]");
					array.add("  [所持金＝ " + getG() + "G ]");
				}
				break;
			}
		setText(array);
	}

	private static void message(String setMessage) {
		Screen.setMessage(setMessage);
	}

	private static void innG() {
		int lev = (party[0].getLev() + party[1].getLev() + party[2].getLev() + party[3].getLev());
		innG = (lev * lev) * (2);
	}

	static void save() {
		try {
			ObjectOutputStream sData = new ObjectOutputStream(new FileOutputStream(sFile));
			sData.writeObject(yName);
			sData.writeObject(fi);
			sData.writeObject(he);
			sData.writeObject(pr);
			sData.writeObject(mg);
			sData.writeInt(bHp);
			sData.writeInt(g);
			sData.writeObject(Item.getItemList());
			sData.close();
		} catch (FileNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	static void load() {
		try {
			ObjectInputStream sData = new ObjectInputStream(new FileInputStream(sFile));
			yName = (String) sData.readObject();
			fi = (Fighter) sData.readObject();// ///////////引き継いだキャラを変数に代入
			he = (Hero) sData.readObject();
			pr = (Priest) sData.readObject();
			mg = (Mage) sData.readObject();
			bHp = sData.readInt();
			g = sData.readInt();
			Object ItemList = sData.readObject();
			//////////////////////////////＊アイテム数も追加する＊
			sData.close();
			name = "チーム勇者" + yName;
			party [ 0 ] = fi;///////実体化したキャラを配列変数に代入
			party [ 1 ] = he;
			party [ 2 ] = pr;
			party [ 3 ] = mg;
			Item.setItemList((Object[][]) ItemList);
		} catch (FileNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	static void getItem() {
		Screen.setCount(0);
		doText = (new String[4]);
		doText[0] = ("「 ・・・!!? 」");
		doText[1] = ("「!!? 宝箱を見つけた  ☆☆☆」");
		doText[2] = ("「開けると○○が入っていた");
		doText[3] = ("          ⇒"+get + " をリュックに詰め込んだ。");
		System.out.print( "      " );
		System.out.println( "「!!? 宝箱を見つけた  ☆☆☆」" );
		System.out.println( "      " );
		System.out.print( "「開けると" );
		int what = new java.util.Random ( ).nextInt( 100 ) + 1;
		if( what < 45 ){
			Item.plus( 1 );
			get = (String) Item.getItemList()[ 1 ][ 1 ];
		}else if( what < 70 ){
			Item.plus( 2 );
			get = (String) Item.getItemList()[ 2 ][ 1 ];
		}else if( what < 85 ){
			Item.plus( 3 );
			get = (String) Item.getItemList()[ 3 ][ 1 ];
		}else if( what < 90 ){
			Item.plus( 4 );
			get = (String) Item.getItemList()[ 4 ][ 1 ];
		}
		if( what < 90 ){
			System.out.println( "が入っていた」" );
			doText[2] = ("「開けると[ " + get + " ]が入っていた");
			System.out.println( "" );
			System.out.println("          ⇒"+get + " をリュックに詰め込んだ。" );
			doText[3] = ("          ⇒"+get + " をリュックに詰め込んだ。" );
		}else{
			Screen.setCount(0);
			doText = (new String[5]);
			doText[0] = ("「 ・・・!!? 」");
			doText[1] = ("「!!? 宝箱を見つけた  ☆☆☆」");
			doText[2] = ("「開けると[ party[ who ].wep() ]が入っていた");
			doText[3] = ("party[ who ].getName() + は + party[ who ].wep() + を装備した" );
			doText[4] = ("[ sell + ]はメルカリに出し[ + price  + G ]で売れた");
			int who = new java.util.Random ( ).nextInt( 4 );
			String sell = party[ who ].getWeapon()[ party[ who ].getWp( ) ];
			int pP = (new java.util.Random ( ).nextInt( 10 ) - 5);
			int wp = party[ who ].getWp( );
			int price = ((wp * wp * wp * 200) - ((wp - 1) * 1000)) / 2 + pP;
			setG(getG() + price);
			party[ who ].setWp( wp + 1 );
			int wMax = (party[ who ].getWeapon().length - 1);
			if (party[ who ].getWp( ) > wMax) party[ who ].setWp( wMax );
			party[ who ].wep();
			System.out.println( "が入っていた" );
			System.out.println( "" );
			doText[2] = ("「開けると[ " + party[who].wep() + " ]が入っていた");
			System.out.print( party[ who ].getName() + "は" );
			party[ who ].wep();
			System.out.println( "を装備した" );
			doText[3] = ("[ " + party[ who ].getName() + " ]は[" + party[ who ].wep() + " ]を装備した" );
			System.out.println( sell + "はメルカリに出し"+ price +"Gで売れた");
			doText[4] = ("[ " + sell + " ]はメルカリに出し[ "+ price +"G ]で売れた");
		}
	}

	private static void end() {
	System.out.println( "・・・・・・・・・・!?" );
	System.out.println( "・・・おまえはもう、死んでいる" );
	System.out.println( "・・・・・あべしっ!!!!  ☠" );
	}

	static void scroll(int d) {
		for (int i = 0; i < 30; i++) {
			System.out.println("");
			Robot rob;
			try {
				rob = new Robot();
				rob.delay(d);///////////////////表示ディレイ値
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void setParty(Member[] party) {
		Main.party = party;
	}

	public static Member[] getParty() {
		return party;
	}

	public static void setName(String name) {
		Main.name = name;
	}

	public static String getName() {
		return name;
	}

	public static void setmName(String mName) {
		Main.mName = mName;
	}

	public static String getmName() {
		return mName;
	}

	public static void setG(int g) {
		Main.g = g;
	}

	public static int getG() {
		return g;
	}

	public static void setbName(String bName) {
		Main.bName = bName;
	}

	public static String getbName() {
		return bName;
	}

	private static void eclipseConsole() {
		Robot rob;
		try {
			rob = new Robot();

			rob.keyPress(KeyEvent.VK_ALT);//
			rob.keyPress(KeyEvent.VK_SHIFT);
			rob.keyPress(KeyEvent.VK_Q);

			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_SHIFT);
			rob.keyRelease(KeyEvent.VK_Q);

			rob.keyPress(KeyEvent.VK_C);
			rob.keyRelease(KeyEvent.VK_C);//

			rob.keyPress(KeyEvent.VK_BACK_SPACE);
			rob.keyRelease(KeyEvent.VK_BACK_SPACE);

			rob.keyPress(KeyEvent.VK_BACK_SPACE);
			rob.keyRelease(KeyEvent.VK_BACK_SPACE);

			rob.keyPress(KeyEvent.VK_INPUT_METHOD_ON_OFF);
			rob.keyRelease(KeyEvent.VK_INPUT_METHOD_ON_OFF);

			rob.keyPress(KeyEvent.VK_ALT);//
			rob.keyPress(KeyEvent.VK_SHIFT);
			rob.keyPress(KeyEvent.VK_Q);

			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_SHIFT);
			rob.keyRelease(KeyEvent.VK_Q);

			rob.keyPress(KeyEvent.VK_C);
			rob.keyRelease(KeyEvent.VK_C);//

			rob.keyPress(KeyEvent.VK_BACK_SPACE);
			rob.keyRelease(KeyEvent.VK_BACK_SPACE);

			rob.keyPress(KeyEvent.VK_BACK_SPACE);
			rob.keyRelease(KeyEvent.VK_BACK_SPACE);

			rob.keyPress(KeyEvent.VK_INPUT_METHOD_ON_OFF);
			rob.keyRelease(KeyEvent.VK_INPUT_METHOD_ON_OFF);

		} catch (AWTException e2) {
			System.out.println( getName() + "エラー" );
			e2.printStackTrace();
		}
	}

	public static void setyName(String yName) {
		Main.yName = yName;
	}

	public static String getyName() {
		return yName;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return pSt[0][columnIndex].getClass();
	}

	@Override
	public String getColumnName(int column) {
		return getpNa()[column];
	}

	@Override
	public int getRowCount() {
		return pSt.length;
	}

	@Override
	public int getColumnCount() {
		if (Screen.getMode() < (1)) {
			pGet();
		}else{
			pSt();
		}
		return getpNa().length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return pSt[rowIndex][columnIndex];
	}

	public static void setA(String a) {
		Main.a = a;
	}

	public static String getA() {
		return a;
	}

	public static void setSc(Screen sc) {
		Main.sc = sc;
	}

	public static Screen getSc() {
		return sc;
	}

	public static void ent() {
		try {
			Robot rob = new Robot();
			rob.delay(200);
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public static void setFiHP(int fiHP) {
		Main.fiHP = fiHP;
	}

	public static int getFiHP() {
		return fiHP;
	}

	public static void setPrHP(int prHP) {
		Main.prHP = prHP;
	}

	public static int getPrHP() {
		return prHP;
	}


	public static void setMgHP(int mgHP) {
		Main.mgHP = mgHP;
	}


	public static int getMgHP() {
		return mgHP;
	}


	public static void setText(ArrayList<String> arrayList) {
		text = new String[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			text[i] = arrayList.get(i);
		}
	}


	public static String[] getText() {
		return text;
	}


	public static void setDoText(String[] doText) {
		Main.doText = doText;
	}


	public static String[] getDoText() {
		return doText;
	}


	public static void setpNa(String[] pNa) {
		Main.pNa = pNa;
	}


	public static String[] getpNa() {
		return pNa;
	}


	public static void setHu(Member hu) {
		Main.hu = hu;
	}


	public static Member getHu() {
		return hu;
	}


	public static void setBat(Battle bat) {
		Main.bat = bat;
	}


	public static Battle getBat() {
		return bat;
	}

}