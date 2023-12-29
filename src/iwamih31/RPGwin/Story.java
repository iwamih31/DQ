package iwamih31.RPGwin;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.table.AbstractTableModel;

public class Story extends AbstractTableModel{

	static String[][] sent = { { "" }, { "" }, { "" }, };

	static Member fi = Main.fi;
	static Member he = Main.he;
	static Member pr = Main.pr;
	static Member mg = Main.mg;

	private static String[] textList;

	Story(){
		
		textList = new String[16];
		textList[0] = "";
		textList[1] = "「 勇者[ " + Main.getyName() + " ]よ、よくぞ来てくれた・・・ 」";
		textList[2] = "「 [ " + Main.getbName() + " ]と呼ばれる魔物が現れて、世界を滅ぼそうとしている・・・ 」";
		textList[3] = "「 勇者[ " + Main.getyName() + " ]よ、[ " + Main.getbName() + " ]を倒し、世界に平和をもたらすのじゃ!!! 」";
		textList[4] = "";
		textList[5] = "["+Main.getyName()+" は [" + Main.getG() +"G ] と道具を手に入れた";
		textList[6] = "";
		textList[7] = "・・・・・" + fi.getName( ) + "が仲間に加わった!!";
		textList[8] = "";
		textList[9] = "・・・・・" + pr.getName( ) + "が仲間に加わった!!";
		textList[10] = "";
		textList[11] = "・・・・・" + mg.getName( ) + "が仲間に加わった!!";
		textList[12] = "";
		textList[13] = "そして[" + Main.getName( ) + "]の伝説が始まった・・・";
		textList[14] = "";
		textList[15] = "☆[ " + Main.getName() + " ]の物語";
		
	}

	public static void start() {

		System.out.println( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
		System.out.println( "" );
		System.out.println( "" );
		Input.ent();
		System.out.println( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
		System.out.println( "" );
		System.out.println( " 「 勇者[ " + Main.getyName() + " ]よ、よくぞ来てくれた・・・ 」");
		Input.ent();
		System.out.println( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
		System.out.println( "" );
		System.out.print( " 「 [ " + Main.getbName() + " ]と呼ばれる魔物が現れて、");
		System.out.println( "世界を滅ぼそうとしている・・・ 」");
		Input.ent();
		System.out.println( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
		System.out.println( "" );
		System.out.print( " 「 勇者[ " + Main.getyName() + " ]よ、[ " + Main.getbName() + " ]を倒し、");
		System.out.println( "世界に平和をもたらすのじゃ!!! 」");
		Input.ent();
		System.out.println( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
		System.out.println( "" );
		System.out.println( "[所持金＝ " + Main.getG() +"G ] ");
		Input.ent();
		System.out.println( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
		System.out.println( "" );
		System.out.println( "  ・・・・・" + fi.getName( ) + "が仲間に加わった!!" );
		Input.ent();
		System.out.print( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
		System.out.println( "●[" + fi.getName( ) + "(Lev." + fi.getLev( ) + ")HP=" + fi.getHp() + ",MP=" + fi.getMp( ) + "] ");
		System.out.println( "" );
		System.out.println( "  ・・・・・" + pr.getName( ) + "が仲間に加わった!!" );
		Input.ent();
		System.out.print( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
		System.out.print( "●[" + fi.getName( ) + "(Lev." + fi.getLev( ) + ")HP=" + fi.getHp() + ",MP=" + fi.getMp( ) + "] ");
		System.out.println( "●[" + pr.getName( ) + "(Lev." + pr.getLev( ) + ")HP=" + pr.getHp() + ",MP=" + pr.getMp( ) + "] ");
		System.out.println( "" );
		System.out.println( "  ・・・・・" + mg.getName( ) + "が仲間に加わった!!" );
		Input.ent();
		System.out.print( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
		System.out.print( "●[" + fi.getName( ) + "(Lev." + fi.getLev( ) + ")HP=" + fi.getHp() + ",MP=" + fi.getMp( ) + "] ");
		System.out.print( "●[" + pr.getName( ) + "(Lev." + pr.getLev( ) + ")HP=" + pr.getHp() + ",MP=" + pr.getMp( ) + "] ");
		System.out.println( "●[" + mg.getName( ) + "(Lev." + mg.getLev( ) + ")HP=" + mg.getHp() + ",MP=" + mg.getMp( ) + "] ");
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "" );
		Robot rob;
		try {
			rob = new Robot();
			rob.delay(1000);///////////////////表示ディレイ値
			Input.clear();
			System.out.print( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
			System.out.print( "  ●[" + fi.getName( ) + "(Lev." + fi.getLev( ) + ")HP=" + fi.getHp() + ",MP=" + fi.getMp( ) + "] ");
			System.out.print( "●[" + pr.getName( ) + "(Lev." + pr.getLev( ) + ")HP=" + pr.getHp() + ",MP=" + pr.getMp( ) + "] ");
			System.out.println( "●[" + mg.getName( ) + "(Lev." + mg.getLev( ) + ")HP=" + mg.getHp() + ",MP=" + mg.getMp( ) + "] ");
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "" );
			rob.delay(2000);///////////////////表示ディレイ値
			Input.clear();
			System.out.print( "●[" + fi.getName( ) + "(Lev." + fi.getLev( ) + ")HP=" + fi.getHp() + ",MP=" + fi.getMp( ) + "] ");
			System.out.print( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
			System.out.print( "●[" + pr.getName( ) + "(Lev." + pr.getLev( ) + ")HP=" + pr.getHp() + ",MP=" + pr.getMp( ) + "] ");
			System.out.println( "●[" + mg.getName( ) + "(Lev." + mg.getLev( ) + ")HP=" + mg.getHp() + ",MP=" + mg.getMp( ) + "] ");
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "" );
			rob.delay(1000);///////////////////表示ディレイ値
			Input.clear();
			Battle.pTable();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "" );
			rob.delay(200);///////////////////表示ディレイ値
			Input.clear();
			System.out.print( "●[" + fi.getName( ) + "(Lev." + fi.getLev( ) + ")HP=" + fi.getHp() + ",MP=" + fi.getMp( ) + "] ");
			System.out.print( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
			System.out.print( "●[" + pr.getName( ) + "(Lev." + pr.getLev( ) + ")HP=" + pr.getHp() + ",MP=" + pr.getMp( ) + "] ");
			System.out.println( "●[" + mg.getName( ) + "(Lev." + mg.getLev( ) + ")HP=" + mg.getHp() + ",MP=" + mg.getMp( ) + "] ");
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "" );
			rob.delay(200);///////////////////表示ディレイ値
			Input.clear();
			Battle.pTable();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "" );
			rob.delay(200);///////////////////表示ディレイ値
			Input.clear();
			System.out.print( "●[" + fi.getName( ) + "(Lev." + fi.getLev( ) + ")HP=" + fi.getHp() + ",MP=" + fi.getMp( ) + "] ");
			System.out.print( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
			System.out.print( "●[" + pr.getName( ) + "(Lev." + pr.getLev( ) + ")HP=" + pr.getHp() + ",MP=" + pr.getMp( ) + "] ");
			System.out.println( "●[" + mg.getName( ) + "(Lev." + mg.getLev( ) + ")HP=" + mg.getHp() + ",MP=" + mg.getMp( ) + "] ");
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "" );
			rob.delay(200);///////////////////表示ディレイ値
			Input.clear();
			Battle.pTable();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "" );
			rob.delay(200);///////////////////表示ディレイ値
			Input.clear();
			System.out.print( "●[" + fi.getName( ) + "(Lev." + fi.getLev( ) + ")HP=" + fi.getHp() + ",MP=" + fi.getMp( ) + "] ");
			System.out.print( "●[" + he.getName( ) + "(Lev." + he.getLev( ) + ")HP=" + he.getHp() + ",MP=" + he.getMp( ) + "] ");
			System.out.print( "●[" + pr.getName( ) + "(Lev." + pr.getLev( ) + ")HP=" + pr.getHp() + ",MP=" + pr.getMp( ) + "] ");
			System.out.println( "●[" + mg.getName( ) + "(Lev." + mg.getLev( ) + ")HP=" + mg.getHp() + ",MP=" + mg.getMp( ) + "] ");
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "" );
			rob.delay(200);///////////////////表示ディレイ値
			Input.clear();
			Battle.pTable();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "" );
			rob.delay(2000);///////////////////表示ディレイ値
			Input.clear();
			Battle.pTable();
			System.out.println( "" );
			System.out.println( "  そして[" + Main.getName( ) + "]の伝説が始まった・・・" );
			System.out.println( "" );
			rob.delay(3000);///////////////////表示ディレイ値
			Input.clear();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "    ☆" );
			System.out.println( "" );
			rob.delay(300);
			Input.clear();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "    ☆[ " );
			System.out.println( "" );
			rob.delay(300);
			Input.clear();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "    ☆[ " + Main.getName() );
			System.out.println( "" );
			rob.delay(1000);
			Input.clear();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "    ☆[ " + Main.getName() + " ]" );
			System.out.println( "" );
			rob.delay(300);
			Input.clear();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "    ☆[ " + Main.getName() + " ]の" );
			System.out.println( "" );
			rob.delay(300);
			Input.clear();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "    ☆[ " + Main.getName() + " ]の物" );
			System.out.println( "" );
			rob.delay(300);
			Input.clear();
			System.out.println( "" );
			System.out.println( "" );
			System.out.println( "    ☆[ " + Main.getName() + " ]の物語" );
			System.out.println( "" );
			rob.delay(300);
			Input.clear();
			System.out.println( "" );
			System.out.println( "    ★★★★★★★★★★★★★★★" );
			System.out.println( "    ☆[ " + Main.getName() + " ]の物語" );
			System.out.println( "    ★★★★★★★★★★★★★★★" );
			rob.delay(1000);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}

	public static void end( ) {
	System.out.println( Main.getbName() + "は断末魔の叫びをあげた" );
	System.out.println( Main.getName() + "は" + Main.getbName() + "を退治した" );
	System.out.println( "世界にまた穏やかな日々がが戻ってきた" );
	System.out.println(   "                                           Fin?" );
	System.out.println(   "  もう一度最初から始めますか？ [ 1.YES  2.NO ]" );
	int inp = Input.input();
	switch ( inp ) {

		case 1:
			Main s = new Main();

			break;
		case 2:
			System.out.println( "修了します" );

			break;
		default:
			System.out.println( "1か2でお願いします" );

		}
	}

	public static void beBack() {
		Battle.pTable();
		System.out.println( "" );
		System.out.println( "  おお!! [" + Main.getyName( ) + " ]よ、調子はどうだ？" );
		Input.ent();
		Battle.pTable();
		System.out.println( "" );
		System.out.println( "  [" + Main.getName( ) + "]の活躍は、我が耳にも色々と届いておるぞ・・・" );
		Input.ent();
		Battle.pTable();
		System.out.println( "" );
		System.out.print( "「 勇者[ " + Main.getyName() + " ]よ、一刻も早く[ " + Main.getbName() );
		System.out.println( " ]を倒し、世界に平和をもたらすのじゃ!!! 」");
		Input.ent();

	}

	public static void relief() {
		Main.scroll(30);
		Battle.pTable();
		System.out.println( "" );
		System.out.println( "" );
		Input.ent();
		Battle.pTable();
		System.out.println( "" );
		System.out.println( " 「 目覚めたか、[ " + Main.getyName() + " ]よ・・・ 」");
		Input.ent();
		Battle.pTable();
		System.out.println( "" );
		System.out.print( " 「 [ " + Main.getName() + " ]を全滅させるとは、[ " + Main.getmName() );
		System.out.println( " ]は、相当手強い相手みたいじゃのう・・・ 」");
		Input.ent();
		Battle.pTable();
		System.out.println( "" );
		System.out.print( " 「なんとか [ " + Main.getyName() + " ]達の命を救う事は出来たが、");
		System.out.println( "お金を大分消費してしまった・・・ 」");
		Input.ent();
		Battle.pTable();
		System.out.println( "" );
		System.out.println( "[所持金＝ " + Main.getG() +"G ] ");
		Input.ent();
		Battle.pTable();
		System.out.println( "" );
		System.out.println( " 「 だが[ " + Main.getName() + " ]の命を救えて、本当に何よりじゃ・・・　」");
		Input.ent();
		Battle.pTable();
		System.out.println( "" );
		System.out.print( " 「 さあ、[ " + Main.getName() + " ]よ、一刻も早く[ " + Main.getbName() );
		System.out.println( " ]を倒し、世界に平和をもたらすのじゃ!!! 」");
		Input.ent();

	}



	@Override
	public int getRowCount() {
		// TODO 自動生成されたメソッド・スタブ
		return sent.length;
	}

	@Override
	public int getColumnCount() {
		// TODO 自動生成されたメソッド・スタブ
		return sent[0].length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return sent[rowIndex][columnIndex];
	}

	public void on(String s) {
		
		System.out.println("");//////////////////////////////////////////
		System.out.println("Story.on(" + s + ") します ");// //////////////////////////
		System.out.println("");//////////////////////////////////////////
		
		Story.sent[1][0] = (s);
		fireTableDataChanged();//テーブル更新
	}

	public static void setTextList(String[] textList) {
		Story.textList = textList;
	}

	public String[] getTextList() {
		return textList;
	}
}
