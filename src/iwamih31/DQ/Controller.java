package iwamih31.DQ;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Controller {

		JLabel ansLabel;
		static JLabel display;
		protected static String value;
		String op1;
		String op2;
		String operator;
		int opMode;
		private static JLabel q;
		private static String[] ynList;
		private JTextArea inp_Text;
		private Story sto;
		private JComponent pict;
		private JTextArea textAreaN;
		private JTextArea textAreaW;
		private JTextArea textAreaC;
		private JTextArea textAreaE;
		private JTextArea textAreaS;
		private JTextArea textAreaB;
		private JTextArea menuAreaB;
		private JTextArea pictAreaB;
		private static String ent;
		private static Story story;
		private String buttonName;
		private String yName;
		private Ex useEx;
		private JPanel eventPanel;
		private JPanel backPanel;
		private int imageCode;
		private String cancel;
//		private MapPiece[][] mapData;
		private JLabel[][] drawMap;
		private JPanel mapPanel;
		private ImageIcon centerIcon;
		private JLabel centerLabel;
		private JButton button_Ent;
		private JButton[] menuButton;
		private JButton cancelButton;
		private int menuNum;
		private String imageURL;
		private Music music;
		private int mapNumber;
		private String center_Image;
		private String image_Map_URL;
		private String image_Map_Type;
		private String[][] map_Image;
		private Controller controller;
		private View sc;
		private static int x;
		private static int y;
		private static int[][] originalMap;
		private static int w;
		private static int h;
		private static int fontSize;
		private static int count;
		private static String message;
		private static Object[] menu;
		private static int mode;
		private static JLabel labelN;
		private static JLabel labelW;
		private static JLabel labelC;
		private static JLabel labelE;
		private static JLabel labelS;
		private static String tex;
		private static JFrame frame;
		private static JPanel panelSet;
		private static JPanel changePanelSet;
		private static CardLayout cardLayout;
		private static JPanel clear;
		private static JLabel space;
		private static String entMark;

		public Controller() {
			sc = new View("テスト");
		}

//		void start() { // 画面開始
//			que_YN("最初から始めますか？・・・");
//		}

		void que_YN(String question) { // 「はい」か「いいえ」の質問
			sc.que(question, ynList);
		}

		private void load() {
			Main.load();
			setMapNumber(0);
			x = 6;
			y = 6;
		}

		public void actionPerformed(String select) {
			Common.___logOut___("[" + select + "]ボタンがクリックされました");
			if(select.equals(buttonName)){ // 同じボタン名がクリックされたら
				// クリックしたボタン名はent
				buttonName = ent;
			}else{	// それ以外がクリックされたら
				// クリックしたボタン名はそのまま使用
				buttonName = select;
			}
			Common.___logOut___("mode = " + mode);
			Common.___logOut___("buttonName = " + buttonName);
			Common.___logOut___("count = " + count);
			actionPerformedSwitch0();
			actionPerformedSwitch1();
			actionPerformedSwitch21();
			actionPerformedSwitch22();
			actionPerformedSwitch3();
			actionPerformedSwitch4();
			actionPerformedSwitch5();
		}

		public void actionPerformedSwitch0() {
			switch (mode) {
				case 0 ://最初
					opening();
					break;
				case 1 ://探す
					count = 0;
					fieldAction(buttonName);
					break;
				case 2 ://使う
					count = 0;
					whichUse(buttonName);
					break;
				case 3 ://買い物
					break;
				case 4 ://宿屋
					break;
				case 5 ://戦闘
					break;
				case 6 ://お城
					repeatMusic("オープニング");
					field(6);
					break;
				case 7 ://ダンジョン
					repeatMusic("オープニング");
					field(7);
					break;
				case 9 ://死亡
					break;
				case 99 :// つづき
					beBack();
					break;
				default :
					break;
			}
		}

		private void opening() {
			// マップ上の位置を初期化
			position_Initial();
			if (buttonName.equals(ynList[0])) {
				musicReset();
				input("     主人公の名前は何にしますか？");
			}
			if (buttonName.equals(ynList[1])) {
				musicReset();
				load();
				setMode(99);
				beBack();
			}
			if (buttonName.equals("OK")) {
				int max_Bytes = 9;
				yName = null;
				// 入力された文字列
				String inputName = inp_Text.getText();
				while (yName == null) {
					if (Common.isBelow_Character_Bytes(inputName, max_Bytes)) {
						if (inputName.equals("")) inputName = Main.getyName();
						Main.setyName(inputName);
						yName = inputName;
						Common.___logOut___("yName = " + yName);
					} else {
						Common.___logOut___("yName = " + yName);
						buttonName.equals(null);
						change();
						input("もう少し短い名前でお願いします");
					}
				}
				begin();
			}
			if (buttonName.equals(ent)) {
				Common.___logOut___("buttonName = " + buttonName);
				Common.___logOut___("count = " + count);
				if (count < story.getTextList().length) {
					setMessageEnt(story.getTextList()[count]);
					if (count == 4) {
						Main.setG(100);
						prologue();
					}
					if (count == 7) Main.fi.setHp(Main.getFiHP());
					if (count == 9) Main.pr.setHp(Main.getPrHP());
					if (count == 11) Main.mg.setHp(Main.getMgHP());
					Main.pGet();
					count++;
					if (count < 5) {
						prologue();
					} else {
						castle();
					}
				} else {
					toNormal();
				}
			}
		}

		private void change() {
			sc.change();
		}

		private void input(String string) {
			sc.input(string);
		}

		private void beBack() {
			if (buttonName.equals(ent)) {
				Common.___logOut___("buttonName = " + buttonName);
				Common.___logOut___("count = " + count);
				if (count < story.getTextList().length) {
					setMessageEnt(story.getTextList()[count]);
					count++;
					field();
				} else {
					toNormal();
				}
			}
		}

		private void begin() {
			Main.begin();
			story = new Story();
			story.on("  ・・・ある日[ " + yName + " ]は、王様に呼び出された・・・");
			prologue();
		}

		private void position_Initial() {
			x = 6;
			y = 6;
		}

		private void field() {
			Common.___logOut___("field() します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			scene();
			menu(new Object[]{});
			comment();
			change();
		}

		private void menu(Object[] objects) {
			sc.menu(objects);
		}

		private void field(int modeNum) {
			Common.___logOut___("field(" + modeNum + ") します");
			setMode(modeNum);
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			scene();
			menu(Command.menu());
			comment();
			change();
		}

		private void fieldMenu(Object[] setMenu) {
			Common.___logOut___("fieldMenu(String[] setMenu) します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			scene();
			menu(setMenu);
			comment();
			change();
		}

		private void use() {
			Common.___logOut___("use() します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			scene();
			menu(menu);
			comment();
			change();
		}

		private void whichUse(String selectButtonName) {
			Common.___logOut___("whichUse(" + selectButtonName +") します");
			if (selectButtonName.equals(menu[0])) {
				buttonName = null;
				printMode();
				Main.use(1);
				setMessage("どのアイテムを使いますか？");
				item();
				setMode(21);
			}
			if (selectButtonName.equals(menu[1])) {
				buttonName = null;
				printMode();
				Main.use(2);
				setMessage("誰が行いますか？");
				who();
				setMode(22);
			}
			if (selectButtonName.equals(cancel)) {
				buttonName = null;
				toNormal();
			}
		}

		public void actionPerformedSwitch1() {
			switch (mode) {
				case 10 ://探す
					if (ent.equals(buttonName)) {
						buttonName = null;
						Main.event();
						String[] text = Main.getDoText();
						if(text.length <= count) {
							toNormal();
						}else {
							setMessageEnt(text[count]);
							adventure();
							count = (count + 1);
						}
					}
					break;
				case 11 ://良い人
					musicReset();
					eventLoop_Heal();
					break;
				case 12 ://情報
					musicReset();
					eventLoop();
					break;
				case 13 ://イベント無し
					toNormal();
					break;
				case 14 ://戦闘
					musicReset();
					repeatMusic("戦闘のテーマ");
					setMode(5);
					adventure();
					break;
				case 15 ://宝箱
					musicReset();
					eventLoop();
					break;
			}
		}

		private void adventure() {
			Common.___logOut___("adventure() します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			scene();
			menu(Command.menu());
			comment();
			change();
		}

		private void fieldAction(String selectButtonName) {
			Common.___logOut___("fieldAction(" + selectButtonName +" ) します");
			String[] menu = Command.menu();
			if (selectButtonName != null) {
				// 探す
				if (selectButtonName.equals(menu[0])) {
					buttonName = null;
					printMode();
					Main.action(1);
					setMessageEnt("―――――" + Main.getName() + "は探検を続けた―――――");
					setMode(10);
					adventure();
					buttonName = ent;
				}
				// 使う
				if (selectButtonName.equals(menu[1])) {
					buttonName = null;
					printMode();
					Main.action(2);
					setMessage("⇒どちらを使いますか？");
					setMode(2);
					use();
				}
				// 買い物
				if (selectButtonName.equals(menu[2])) {
					buttonName = null;
					printMode();
					Main.action(3);
					setMode(3);
					shop();
				}
				// 宿屋
				if (selectButtonName.equals(menu[3])) {
					buttonName = null;
					printMode();
					Main.action(4);
					setMode(4);
					inn();
				}
			}
		}

		private void eventLoop() {
			if (buttonName.equals(ent)) {
				Common.___logOut___("eventLoop count = " + count);
				String[] text = Main.getDoText();
				if (count < text.length) {
					setMessageEnt(text[count]);
					if (count == 0) {
					}
					count = (count + 1);
					adventure();
				} else {
					musicReset();
					toNormal();
				}
			}
		}

		private void loop(String[] text) {
			if (buttonName.equals(ent)) {
				Common.___logOut___("loop count = " + count);
				if (count < text.length) {
					setMessageEnt(text[count]);
					count++;
					field();
				} else {
					musicReset();
					toNormal();
				}
			}
		}

		private void eventLoop_Heal() {
			if (buttonName.equals(ent)) {
				Common.___logOut___("eventLoop_Heal count = " + count);
				String[] text = Main.getDoText();
				if (count < text.length) {
					setMessageEnt(text[count]);
					if (count == 2) {
						Main.healing();
					}
					count = (count + 1);
					adventure();
				} else {
					toNormal();
				}
			}
		}

		public void actionPerformedSwitch21() {
			switch (mode) {
				case 21 ://使う,道具
					if (Battle.getfMode() == 0){
						fieldItem(buttonName);
					}else{
						battleItem(buttonName);
					}
					break;
				case 200 ://使う,道具,結果
					if (buttonName.equals(ent)) {
						itemLoop();
					}
					break;
				case 211 ://使う,道具,[1],誰に？
					count = 0;
					menu = Main.getpNa();
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])){
							setMode(200);
							Item.who1(i);
							itemLoop();
						}
					}
					break;
				case 212 ://使う道具[2],結果
					count = 0;
					setMode(200);
					break;
				case 213 ://使う道具[3],結果
					count = 0;
					setMode(200);
					break;
				case 214 ://使う道具[4],誰に？
					count = 0;
					menu = Main.getpNa();
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])){
							setMode(200);
							Item.who4(i);
							itemLoop();
						}
					}
					break;
				case 2101 ://バトルモード,使う,道具,[1],誰に？
					count = 0;
					menu = Main.getpNa();
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])){
							setMode(555);
							Item.who1(i);
							battleLoop();
						}
					}
					break;
				case 2102 ://バトルモード,使う道具[2],どのモンスターに？
					count = 0;
					Monster[] mons = Battle.getMons();
					for (int i = 0; i < mons.length; i++) {
						if (buttonName.equals(mons[i].getName())){
							setMode(555);
							Item.who2(i);
							battleLoop();
						}
					}
					break;
				case 2103 ://バトルモード,使う道具[3]
					setMode(555);
					break;
				case 2104 ://バトルモード,使う道具[4],誰に？
					count = 0;
					menu = Main.getpNa();
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])){
							setMode(555);
							Item.who4(i);
							battleLoop();
						}
					}
					break;
			}
		}

		private void item() {
			Common.___logOut___("Item() します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			scene();
			menu(Item.menu());
			comment();
			change();
		}

		private void fieldItem(String setButtonName) {
			menu = Item.menu();
			Member user = Main.getHu();
			for (int i = 0; i < menu.length; i++) {
				if (setButtonName.equals(menu[i])) {
					setMode(211 + i);
					Item.use(user, i);
					who();
				}
			}
			if (setButtonName.equals(cancel)) {
				setMode(200);
				Item.use(user, 10);
				itemLoop();
			}
		}

		private void battleItem(String setButtonName) {
			menu = Item.menu();
			Member user = Main.getParty()[Battle.getActor()];
			if (setButtonName.equals(menu[0])) {
				setMode(2101);
				Item.use(user, 0);
				menu = Main.getpNa();
				battle();
			}
			if (setButtonName.equals(menu[1])) {
				setMode(2102);
				Item.use(user, 1);
				menu = Battle.mNa();
				battle();
			}
			if (setButtonName.equals(menu[2])) {
				setMode(555);
				count = 0;
				Item.use(user, 2);
				battleLoop();
			}
			if (setButtonName.equals(menu[3])) {
				setMode(2104);
				Item.use(user, 3);
				menu = Main.getpNa();
				battle();
			}
			if (setButtonName.equals(cancel)) {
				setMode(555);
				count = 0;
				Item.use(user, 10);
				battleLoop();
			}
		}

		private void itemLoop() {
			Common.___logOut___("itemLoop() します");
			String[] text = Item.getItemText();
			if (count < text.length) {
				setMessageEnt(text[count]);
				if (count == 0) {
				}
				count = (count + 1);
				item();
			} else {
				toNormal();
			}
		}

		public void actionPerformedSwitch22() {
			switch (mode) {
				case 22 ://使う,能力,誰が？
					whoExField(buttonName);
					break;
				case 220 ://使う,能力,何を？
					count = 0;
					menu = useEx.menu();
					whatEx();
					break;
				case 2250 ://使う,能力,結果
					if (buttonName.equals(ent)&&mode<3000) {
						exLoop();
					}
					break;
				case 22501 ://使う,能力,heal(),誰に？
					count = 0;
					menu = Main.getpNa();
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])) {
							setMode(2250);
							useEx.heal(i);
							exLoop();
						}
					}
					break;
				case 22502 ://使う,能力,resu(),誰に？
					count = 0;
					menu = Main.getpNa();
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])) {
							setMode(2250);
							useEx.resu(i);
							exLoop();
						}
					}
					break;
				case 22503 ://使う,能力,death(),どのモンスターに？
					count = 0;
					menu = Battle.mNa();
					for (int i = 0; i < Battle.getMons().length; i++) {
						if (buttonName.equals(Battle.getMons()[i].getName())) {
							setMode(2250);
							Magic.death(i);
							exLoop();
						}
					}
					break;
			}
		}

		private void ex() {
			Common.___logOut___("ex() します");
			buttonName = null;
			menu = useEx.menu();
			partySt();
			info(goldList(),itemList(),exList());
			if(Battle.getfMode()==0){
				scene();
			}else{
				monster();
			}
			menu(menu);
			comment();
			change();
		}

		private void whoExField(String setButtonName) {
			Common.___logOut___("whoExField(" + setButtonName + ") します");
			Member[] party = Main.getParty();
			setMessage("どの術を使いますか？");////////////////////次の質問
			menu = Main.getpNa();
				if (setButtonName.equals(menu[0])) {
					useEx = new Power(party[0]);
					Main.ex(0);
				}
				if (setButtonName.equals(menu[1])) {
					useEx = new Wonder(party[1]);
					Main.ex(1);
				}
				if (setButtonName.equals(menu[2])) {
					useEx = new Bless(party[2]);
					Main.ex(2);
				}
				if (setButtonName.equals(menu[3])) {
					useEx = new Magic(party[3]);
					Main.ex(3);
				}
				setMode(220);
				ex();
		}

		private void whoExBattle() {
			int actor = Battle.getActor();
			Common.___logOut___("whoExBattle(" + actor + ") します");
			Member[] party = Main.getParty();
			setMessage("どの術を使いますか？");////////////////////次の質問
			if (actor == 0) {
				useEx = new Power(party[0]);
			}
			if (actor == 1) {
				useEx = new Wonder(party[1]);
			}
			if (actor == 2) {
				useEx = new Bless(party[2]);
			}
			if (actor == 3) {
				useEx = new Magic(party[3]);
			}
			Common.___logOut___("useEx = " + useEx.getClass() + " です");
			Common.___logOut___("Ex.getName() = " + Ex.getName() + " です");
			party[actor].ex();
			setMode(220);
			menu = useEx.menu();
			battle();
		}

		private void whatEx() {
			menu = useEx.menu();
			for (int i = 0; i < menu.length; i++) {
				if (buttonName.equals(menu[i])) {
					setMode(2250);
					useEx.select(i);
					exLoop();
				}
			}
			if (buttonName.equals(cancel)) {
				setMode(2250);
				useEx.select(10);
				exLoop();
			}
		}

		private void exLoop() {
			Common.___logOut___("exLoop() します");
			String[] text = useEx.getExText();
			Common.___logOut___("count =(" + count + ")");
			if (count < text.length) {
				if (count == 0) {
				}
					if (Battle.getfMode() == 0) {
						if (mode > 10000) {
							setMessage(text[count]);
							who();
						} else {
							setMessageEnt(text[count]);
							ex();
						}
					}else{
						if (mode > 10000) {
							setMessage(text[count]);
						} else {
							setMessageEnt(text[count]);
						}
						battleEx();
					}
				count = (count + 1);
			} else {
				if (Battle.getfMode() == 0) {
					toNormal();
				}else{
					count = 0;
					Main.getBat().turn();
					menu = Battle.getMenu();
				}
			}
		}

		public void actionPerformedSwitch3() {
			Member user;
			switch (mode) {
				case 3 :// 店
					count = 0;
					musicReset();
					Main.action(3);
					setMessage("「いらっしゃいませ、御用は何でしょうか？」");
					set_Menu(new Object[]{ "買う", "売る" });
					shop();
					setMode(33);
					break;
				case 33 :// 店,どうする？
					count = 0;
					if(buttonName.equals(menu[0])){//買う
						setMode(30);
						Main.shop(1);
						setMessage("何を買いますか？");
						set_Menu(new Object[]{ "道具", "武器" });
						shop();
					}
					if(buttonName.equals(menu[1])){//売る
						setMode(31);
						Main.shop(2);
						setMessage("何を売りますか？");
						set_Menu(new Object[]{ "道具", "武器" });
						shop();
					}
					if (buttonName.equals(cancel)) {
						setMode(3000);
						Shop.leave();
						shopLoop();
					}
					break;
				case 30 :// 店,買う,どちら？
					count = 0;
					if(buttonName.equals(menu[0])){
						setMode(300);
						Main.buy(1);
						setMessage("どれを買いますか？");
						menu = Item.menu();
						shop();
					}
					if(buttonName.equals(menu[1])){
						setMode(301);
						Main.buy(2);
						setMessage("誰の武器を買いますか？");
						menu = Main.getpNa();
						shop();
					}
					if (buttonName.equals(cancel)) {
						setMode(3000);
						Shop.leave();
						shopLoop();
					}
					break;
				case 31 :// 店,売る,どちら？
					count = 0;
					if(buttonName.equals(menu[0])){
						setMode(310);
						Main.sell(0);
						setMessage("どれを売りますか？");
						menu = Item.menu();
						shop();
					}
					if(buttonName.equals(menu[1])){
						setMode(311);
						Main.sell(1);
						setMessage("誰の武器を売りますか？");
						menu = Main.getpNa();
						shop();
					}
					if (buttonName.equals(cancel)) {
						setMode(3000);
						Shop.leave();
						shopLoop();
					}
					break;
				case 300 :// 店,買う,道具,どれを？
					count = 0;
					menu = Item.menu();
					user = Main.getHu();
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])) {
							Shop.buyItem(i);
							setMode(3000);
							shopLoop();
						}
					}
					if (buttonName.equals(cancel)) {
						setMode(3000);
						Shop.leave();
						shopLoop();
					}
					break;
				case 3000 :// 店,結果ループ
					if (buttonName.equals(ent)) {
						shopLoop();
					}
					break;

				case 301 :// 店,買う,武器,誰の？
					count = 0;
					menu = Main.getpNa();
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])) {
							setMode(3010);
							Shop.buyWapon(i);
							shopWapon(i);
						}
					}
					if (buttonName.equals(cancel)) {
						setMode(3000);
						Shop.leave();
						shopLoop();
					}
					break;
				case 3010 :// 店,買う,武器,どれを？
					count = 0;
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])) {
							setMode(3000);
							Shop.buyWaponWhich(i + 1);
							shopLoop();
						}
					}
					if (buttonName.equals(cancel)) {
						setMode(3000);
						Shop.buyWaponWhich(10);
						shopLoop();
					}
					if (buttonName.equals(cancel)) {
						setMode(3000);
						Shop.leave();
						shopLoop();
					}
					break;
				case 310 :// 店,売る,道具
					count = 0;
					menu = Item.menu();
					user = Main.getHu();
					for (int i = 0; i < menu.length; i++) {
						if (buttonName.equals(menu[i])) {
							setMode(3000);
							Shop.sellItem(user, i);
							shopLoop();
						}
					}
					if (buttonName.equals(cancel)) {
						setMode(3000);
						Shop.leave();
						shopLoop();
					}
					break;
			}
		}

		private void set_Menu(Object[] menu_List) {
			menu = menu_List;
			String sMenu = "[";
			for (Object item : menu) {
				sMenu += (item + ", ");
			}
			sMenu += "]";
			sMenu = sMenu.replace(", ]", "]");
			Common.___logOut___(sMenu);
		}

		private void shop() {
				Common.___logOut___("Screen.shop() します");
				buttonName = null;
				partySt();
				info(goldList(),itemList(),shopList());
				scene();
				menu(menu);
				comment();
				change();
			}

		private void shopWapon(int i) {
			Common.___logOut___("shopWapon(" + i + ") します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),shopWaponList(i));
			scene();
			menu(menu);
			comment();
			change();
		}

		private void shopLoop() {
			Common.___logOut___("buttonName = " + buttonName);
			String[] text = Shop.getShopText();
			if (count < text.length) {
				setMessageEnt(text[count]);
				shop();
				count +=  1;
			} else {
				toNormal();
			}
		}

		public void actionPerformedSwitch4() {
			switch (mode) {
				case 4 :// 宿屋
					musicReset();
					count = 0;
					menu = new Object[]{ "はい", "いいえ", "状態確認", "復活の儀式" };
					if (buttonName.equals(menu[0])) {
						setMode(41);
						innMenu1();
					}
					if (buttonName.equals(menu[1])) {
						setMode(42);
						innMenu2();
					}
					if (buttonName.equals(menu[2])) {
						setMode(43);
						innMenu3();
					}
					if (buttonName.equals(menu[3])) {
						setMode(44);
						setMessage( "誰を復活させますか？" );
						who();
					}
					break;
				case 41 ://
					if (buttonName.equals(ent)) {
						innMenu1();
					}
					break;
				case 42 ://
					if (buttonName.equals(ent)) {
						innMenu2();
					}
					break;
				case 43 ://状態確認
					if (buttonName.equals(ent)) {
						innMenu3();
					}
					break;
				case 431 ://戻る
					if(buttonName.equals(ent)){
						setMessageEnt(Main.getName() + "は、宿を出た・・・");
						innMenu0();
					}
					break;
				case 44 ://復活の儀式,誰に？
					count = 0;
					menu = Main.getpNa();
					if (buttonName.equals(menu[0])) {
						setMode(440);
						Main.revive(0);
						fieldMenu(menu);
					}
					if (buttonName.equals(menu[1])) {
						setMode(441);
						Main.revive(1);
						fieldMenu(menu);
					}
					if (buttonName.equals(menu[2])) {
						setMode(442);
						Main.revive(2);
						fieldMenu(menu);
					}
					if (buttonName.equals(menu[3])) {
						setMode(443);
						Main.revive(3);
						fieldMenu(menu);
					}
					break;
				case 440 ://復活の儀式,[0]に,結果
				case 441 ://復活の儀式,[1]に,結果
				case 442 ://復活の儀式,[2]に,結果
				case 443 ://復活の儀式,[3]に,結果
				case 445 ://復活の儀式,[はい],結果
					if (buttonName.equals(ent)) {
						innMenu4();
					}
					break;
				case 444 ://復活の儀式,後処理
					if (buttonName.equals(ent)) {
						toNormal();
					}
					break;
				case 4444 ://復活の儀式,しますか？
					count = 0;
					menu = new String[]{"はい","いいえ"};
					if (buttonName.equals(menu[0])) {
						buttonName = null;
						Main.reviveYes(1);
						innMenu4();
					}
					if (buttonName.equals(menu[1])) {
						buttonName = null;
						innMenu4();
					}
					if (buttonName.equals(ent)) {
						buttonName = null;
						innMenu4();
					}
					break;
			}
		}

		private void inn() {
			Common.___logOut___("inn() します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			scene();
			Object[] innMenu = new Object[]{ "はい", "いいえ", "状態確認", "復活の儀式" };
			menu(innMenu);
			comment();
			change();
		}

		private void status() {
			Common.___logOut___("status() します");
			buttonName = null;
			Status.statusModel();
			partyStAll();
			info(goldList(),itemList(),"");
			sceneBlank();
			Object[] innMenu = new Object[]{ "はい", "いいえ", "状態確認", "復活の儀式" };
			menu(innMenu);
			setMessageEnt("⇒ で戻る");
			comment();
			change();
		}

		private void partyStAll() {
			sc.partyStAll();
		}

		private void innMenu0() {
			Common.___logOut___("innMenu0() します  buttonName = " + buttonName);
			if (count == 0) {
				count = (count + 1);
				inn();
			} else {
				toNormal();
			}
		}

		private void innMenu1() {
			Common.___logOut___("innMenu1() します  buttonName = " + buttonName);
			String[] text = Main.innText;
			if (count < text.length) {
				setMessageEnt(text[count]);
				if (count == 1) {
					Main.innMenu(1);
				}
				count = (count + 1);
				inn();
			} else {
				toNormal();
			}
		}

		private void innMenu2() {
			Common.___logOut___("innMenu2() します  buttonName = " + buttonName);
			count = 0;
			setMode(444);
			Main.innMenu(0);
			inn();
		}

		private void innMenu3() {
			Common.___logOut___("innMenu3() します  buttonName = " + buttonName);
			setMode(431);
			status();
			count = 0;
		}

		private void innMenu4() {
			Common.___logOut___("innMenu4() します  buttonName = " + buttonName);
			if (count == 0) {
			}
			String[] text = Main.getText();
			if (count < text.length) {
				setMessageEnt(text[count]);
				count = (count + 1);
				inn();
			} else {
				count = 0;
				setMode(444);
				Main.innMenu(0);
				inn();
			}
		}

		public void actionPerformedSwitch5() {
			switch (mode) {
				case 5 ://戦闘
					count = 0;
					menu = Battle.getMenu();
					Main.battle();
					break;
				case 50 ://戦闘,メンバー
					buttonName = null;
					setMessage(Battle.getBattleText()[0]);
					menu = Battle.getMenu();
					battle();
					setMode(55);
					break;
				case 55 ://戦闘,メンバー,どうする？
					count = 0;
					menu = Battle.getMenu();
					if (menu[0].equals(buttonName)) {//戦う
						Main.getBat().pSelect(0);
						setMode(550);
						setMessage(Battle.getBattleText()[0]);
						attack();
					}
					if (menu[1].equals(buttonName)) {//道具
						Main.getBat().pSelect(1);
						buttonName = null;
						Member user = Main.getParty()[Battle.getActor()];
						setMessage(user.getName() + " は、どのアイテムを使いますか？");
						menu = Item.menu();
						battle();
						setMode(21);
					}
					if (menu[2].equals(buttonName)) {//能力
						Main.getBat().pSelect(2);
						setMode(552);
						whoExBattle();
						menu = useEx.menu();
						battleEx();
					}
					if (menu[3].equals(buttonName)) {//逃げる
						Main.getBat().pSelect(3);
						setMode(553);
						battleLoop();
					}
					if (cancel.equals(buttonName)) {//何もしない
						Main.getBat().pSelect(10);
						setMode(555);
						battleLoop();
					}
					break;
				case 550 ://戦闘,メンバー,戦う
					count = 0;
					Monster[] mons = Battle.mons;
					for (int i = 0; i < mons.length; i++) {
						if (buttonName.equals(mons[i].getName())){
							setMode(555);
							Main.getBat().attack(i);
							battleLoop();
						}
					}
					if (buttonName.equals(cancel)){
						setMode(555);
						Main.getBat().attack(10);
						battleLoop();
					}
					break;
				case 551 ://戦闘,メンバー,道具
					break;
				case 552 ://戦闘,メンバー,能力
					if (buttonName.equals(ent)) {
						battleLoop();
					}
					break;
				case 553 ://戦闘,メンバー,逃げる
					if (buttonName.equals(ent)) {
						battleLoop();
					}
					break;
				case 555 ://戦闘,次へ
					if (buttonName.equals(ent)) {
						battleLoop();
					}
					break;
				case 5555 ://戦闘後
					musicReset();
					count = 0;
					setMode(55551);
					Battle.exp();
					expLoop();
					break;
				case 55551 ://戦闘後,EXP
					if (buttonName.equals(ent)) {
						expLoop();
					}
					break;
				case 55552 ://戦闘後,LEV
					if (buttonName.equals(ent)) {
						levLoop();
					}
					break;
				case 55553 ://戦闘後,GOLD
					if (buttonName.equals(ent)) {
						goldLoop();
					}
					break;
				case 55554 ://戦闘後,ITEM
					if (buttonName.equals(ent)) {
						getItemLoop();
						Battle.setItem(1);
					}
					break;
				case 55555 ://戦闘後,ITEM,有り
					if (buttonName.equals(ent)) {
						getItemLoop();
						Battle.setItem(2);
					}
					break;
			}
		}

		private void musicReset() {
			if (music != null) {
				music.stop();
				music = null;
			}
		}

		private void toNormal() {
			setMessage("どうしますか?");
			switch (mapNumber) {
				case 0:
					field(1);/////////////////////////////////////通常モードへ
					repeatMusic("冒険の歌");
					break;
				case 1:
					field(6);/////////////////////////////////////城内モードへ
					repeatMusic("オープニング");
					break;
				case 2:
					field(７);/////////////////////////////////////城内モードへ
					repeatMusic("オープニング");
					break;
			}
			Main.save();
			count = 0;
		}

		private void repeatMusic(String musicName) {
			if (music == null) {
				music = new Music(musicName);
				music.playRepeat();
			}
		}

		private void battle() {
			Common.___logOut___("battle() します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			monster();
			menu(menu);
			comment();
			change();
		}

		private void battleEx() {
			Common.___logOut___("battleEx() します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),exList());
			monster();
			menu(menu);
			comment();
			change();
		}

		private void battleLoop() {
			Common.___logOut___("battleLoop() します" );
			Common.___logOut___("count = " + count);
			String[] text = Battle.getBattleText();
			if (count < text.length) {
				setMessageEnt(text[count]);
				if (count == 0) {
				}
				count++;
				battle();
			} else {
				if(Battle.getfMode() == 0){
					musicReset();
					toNormal();
				}else{
					count = 0;
					Main.getBat().turn();
					menu = Battle.getMenu();
				}
			}
		}

		private void expLoop() {
			Common.___logOut___("expLoop() します");
			Common.___logOut___("count = " + count);
			String[] text = Battle.getBattleText();
			if (count < text.length) {
				setMessageEnt(text[count]);
				count++;
				battle();
			} else {
				if (Battle.getfMode() == 0) {
					toNormal();
				} else {
					count = 0;
					setMode(55553);
					Battle.gold();
				}
			}
		}

		private void levLoop() {
			Common.___logOut___("levLoop() します");
			Common.___logOut___("count = " + count);
			String[] text = Battle.getBattleText();
			if (count < 5) {
				setMessageEnt(text[0]);
				battle();
			} else {
				if (Battle.getfMode() == 0) {
					toNormal();
				} else {
					count = 0;
					setMode(55553);
					Battle.gold();
				}
			}
		}

		private void goldLoop() {
			Common.___logOut___("goldLoop() します");
			Common.___logOut___("count = " + count);
			String[] text = Battle.getBattleText();
			if (count < text.length) {
				setMessageEnt(text[count]);
				if (count == 0) {
				}
				count = (count + 1);
				battle();
			} else {
				if (Battle.getfMode() == 0) {
					toNormal();
				} else {
					count = 0;
					setMode(55554);
					Battle.item();
				}
			}
		}

		private void getItemLoop() {
			Common.___logOut___("getItemLoop() します");
			Common.___logOut___("count = " + count);
			String[] text = Battle.getBattleText();
			if (count < text.length) {
				setMessageEnt(text[count]);
				if (count == 0) {
				}
				count = (count + 1);
				battle();
			} else {
				if (Battle.getfMode() == 0) {
					toNormal();
				} else {
					toNormal();
					Common.___logOut___("戦闘後処理、未完了です");
				}
			}
		}

		private void who() {
			Common.___logOut___("who() します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			scene();
			menu(Main.getpNa());
			comment();
			change();
		}

		private void attack() {
			Common.___logOut___("attack() します");
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			monster();
			menu(Battle.mNa());
			comment();
			change();
		}

		private void printMode() {
			Common.___logOut___("mode = " + getMode() + " です");
		}

		private void partySt() {
			sc.partySt();
		}

		private void monster() {
			sc.monster();
		}

		public int[][] getOriginalMap() {
			int[][] originalMap = null;
			int[][] field1_Map = {
					{4,2,3,3,3,3,1,1,1,2,2,1,1,3,3},
					{1,1,2,3,3,3,1,2,2,1,2,1,3,3,2},
					{2,0,0,3,3,2,1,1,2,1,2,2,3,3,3},
					{3,3,0,0,0,1,2,1,2,1,1,1,2,3,3},
					{3,3,3,3,2,1,1,1,2,1,2,1,1,2,3},
					{3,3,1,0,1,0,2,2,2,0,0,2,1,1,1},
					{1,3,1,2,2,2,1,1,0,0,3,0,0,2,1},
					{3,3,1,2,0,2,1,2,2,0,0,2,2,1,1},
					{3,2,1,2,1,2,1,1,1,2,0,2,1,1,3},
					{1,2,1,1,1,2,2,2,1,2,1,2,2,2,1},
					{2,2,2,2,2,2,1,1,1,2,1,1,1,1,1},
					{1,2,0,0,0,0,1,2,2,2,1,1,0,0,1},
					{1,1,2,3,3,2,1,1,1,1,2,1,3,3,3},
					{3,1,1,1,3,3,3,3,2,1,2,1,3,9,3},
					{3,3,3,1,2,3,1,3,1,1,2,1,1,1,3}
			};
			int[][] castle1_Map = {
					{3,3,3,3,3,3,3,3,3,3,3,3,3,3,1},
					{3,3,3,1,1,1,1,1,1,1,1,1,1,1,1},
					{3,3,2,1,2,2,2,2,2,2,2,2,2,2,1},
					{3,1,2,0,0,2,0,9,2,0,2,0,0,2,1},
					{3,1,2,2,0,2,0,2,0,0,0,0,0,2,1},
					{3,1,2,0,0,2,0,0,0,0,2,0,2,2,1},
					{3,1,2,0,2,2,2,2,2,2,0,0,0,2,1},
					{3,1,2,0,0,2,0,0,0,0,2,0,0,2,1},
					{3,1,2,0,0,0,0,2,0,0,0,0,0,2,1},
					{3,1,2,2,2,2,2,2,2,2,2,2,0,2,1},
					{3,0,0,0,0,0,4,0,0,0,0,0,0,2,1},
					{3,2,2,2,2,2,2,0,2,2,2,2,2,2,1},
					{3,9,1,1,1,1,2,0,2,1,1,1,1,1,1},
					{3,3,3,1,3,1,8,8,8,1,3,3,3,3,3},
					{3,4,1,1,3,1,1,1,1,1,3,1,1,1,1}
			};
			int[][] dungeon1_Map = {
					{0,9,0,0,0,0,2,0,2,0,2,2,2,2,2},
					{0,0,0,2,2,0,2,0,2,0,0,0,0,0,2},
					{2,2,2,0,0,0,2,0,2,2,2,2,2,0,2},
					{0,0,0,0,2,2,2,0,0,0,2,0,2,0,2},
					{0,2,0,2,0,0,0,2,2,0,2,0,2,0,2},
					{0,2,0,2,0,2,0,0,0,0,2,0,0,0,2},
					{0,0,2,2,0,0,2,2,2,2,2,2,2,2,2},
					{2,0,2,2,2,0,2,0,0,0,0,0,0,0,0},
					{2,0,0,0,0,0,2,0,2,2,2,2,2,2,0},
					{2,0,2,2,2,2,2,0,2,0,0,0,0,2,0},
					{2,0,2,0,0,0,2,0,0,0,0,2,0,2,0},
					{2,0,2,0,2,0,2,2,2,2,2,0,0,2,0},
					{2,0,2,2,2,0,2,0,0,0,0,0,0,2,0},
					{2,0,0,0,0,0,2,0,2,2,2,2,2,0,0},
					{2,2,2,2,2,2,2,0,2,0,0,0,0,0,4}
			};
			switch (mapNumber) {
				case 0:
					originalMap = field1_Map;
					break;
				case 1:
					originalMap = castle1_Map;
					break;
				case 2:
					originalMap = dungeon1_Map;
					break;
				default:
					originalMap = field1_Map;
					break;
			}
			return originalMap;
		}

		private MapPiece mapPiece(int number) {
			MapPiece mapPiece = null;
			switch (number) {
				case 0 :
					String mapImage = "砂";
					if(mapNumber == 2) mapImage = "闇";
					mapPiece = new MapPiece(mapImage, 1);
					break;
				case 1 :
					mapPiece = new MapPiece("草", 2);
					break;
				case 2 :
					mapPiece = new MapPiece("山", 0);
					break;
				case 3 :
					mapPiece = new MapPiece("海", 0);
					break;
				case 4 :
					mapPiece = new MapPiece("洞窟", 4);
					break;
				case 5 :
					mapPiece = new MapPiece("階段", 4);
					break;
				case 6 :
					mapPiece = new MapPiece("山", 2);
					break;
				case 7 :
					mapPiece = new MapPiece("宝箱", 7);
					break;
				case 8 :
					mapPiece = new MapPiece("草", 9);
					break;
				case 9 :
					mapPiece = new MapPiece("城", 9);
					break;
				default :
					mapPiece = new MapPiece("砂", 0);
					break;
			}
			return mapPiece;
		}

		private int[][] shift_Map(int[][] originalMap, int x2, int y2) {
			int[][] map = new int[originalMap.length][originalMap[0].length];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					int row = i + y;
					if (map.length <= row) row -= map.length;
					int column = j + x;
					if (map[0].length <= column) column -= map[0].length;
					map[i][j] = originalMap[row][column];
				}
			}
			return map;
	}

		private MapPiece[][] map_Data(int[][] map) {
			MapPiece[][] map_Data = new MapPiece[map.length][map[0].length];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					map_Data[i][j] = mapPiece(map[i][j]);
				}
			}
			return map_Data;
		}

		private String[][] map_Image(MapPiece[][] map_Data) {
		String[][] map_Image = new String[map_Data.length][map_Data[0].length];
		for (int i = 0; i < map_Data.length; i++) {
			for (int j = 0; j < map_Data[i].length; j++) {
				map_Image[i][j] = map_Data[i][j].getImage();
			}
		}
		return map_Image;
		}

		private String[][] map_Image(int[][] map) {
			return map_Image(map_Data(map));
		}

		private ImageIcon createImageIcon(String imageUrl) {
			ClassLoader classLoader = this.getClass().getClassLoader();
			return new ImageIcon(classLoader.getResource(imageUrl));
		}

		private String drawMonster(int number) {
			Monster monster = Battle.mons[number];
			String drawMonster = null;
			switch (monster.getCode()) {
				case 0 :
					if(monster.getHp() < 1){
						drawMonster = "エアー";
					}else{
						drawMonster = "ゾンビ";
					}
					break;
				case 1 :
					drawMonster = "スライム";
					break;
				case 2 :
					drawMonster = "マッドドクター";
					break;
				case 3 :
					drawMonster = "ゴーレム";
					break;
				case 4 :
					drawMonster = "ドラゴラム";
					break;
				case 5 :
					drawMonster = "竜王";
					break;
				default :
					break;
			}
			return imageURL + drawMonster + ".png";
		}

		private String drawItem() {
			String drawItem = null;
			switch (Battle.getItem()) {
				case 0 :
					drawItem = "エアー";
					break;
				case 1 :
					drawItem = "宝箱";
					break;
				case 2 :
					drawItem = "宝箱オープン";
					break;
				case 3 :
					drawItem = (String) Item.menu()[0];
					break;
				case 4 :
					drawItem = (String) Item.menu()[1];
					break;
				case 5 :
					drawItem = (String) Item.menu()[2];
					break;
				case 6 :
					drawItem = (String) Item.menu()[3];
					break;
				case 7 :
					drawItem = "杖";
					break;
				case 8 :
					drawItem = "斧";
					break;
				case 9 :
					drawItem = "剣";
					break;
				case 10 :
					drawItem = "槍";
					break;
				default :
					drawItem = "エアー";
					break;
			}
			return imageURL + drawItem + ".png";
		}

		void prologue() {
			Common.___logOut___("prologue() します");
			buttonName = null;
			partyStBlank();
			info(goldList(),"","");
			scene();
			menu(Command.menu());
			comment();
			change();
		}

		private void partyStBlank() {
			sc.partyStBlank();
		}

		private void castle() {
			Common.___logOut___("castle() します");
			setMode(0);/////////////////////////////////// ?
			buttonName = null;
			partySt();
			info(goldList(),itemList(),"");
			scene();
			menu(Command.menu());
			comment();
			change();
		}

		private JPanel goldList() {
			return infoTable(new Gold(),"所持金");
		}

		private JPanel itemList() {
			return infoTable(new Item(),"アイテム");
		}

		private JPanel exList() {
			return infoTable(useEx,"使用 MP");
		}

		private JPanel shopList() {
			return infoTable(new Shop(),"価格");
		}

		private JPanel shopWaponList(int who) {
			return infoTable(new Wapon(who),"武器価格");
		}

		private JPanel infoTable(Object setTableModel,String tableName) {
			return sc.infoTable(setTableModel, tableName);
		}

		private void info(Object top, Object middle, Object bottom) {

		}

		private JPanel ent() {
			return sc.ent();
		}

		private void scene() {
			switch(mode) {
				case 1:
				case 6:
				case 7:
				case 10:
					setBackPanel(imageURL + "フィールド.png");
					eventPanel = map2D(map_Image);
					break;
				case 5:
					setBackPanel(imageURL + "バトル.png");
					setEventImage(eventImage());
					break;
				default:
					setBackPanel(imageURL + "フィールド.png");
					setEventImage(eventImage());
					break;
			}
			sc.scene();
		}

		private void setEventImage(String eventImage) {
			sc.setEventImage(eventImage);

		}

		private JPanel map2D(String[][] map_Image) {
			return map2D(map_Image);
		}

		private void setBackPanel(String string) {
			sc.setBackPanel(string);
		}

		private String eventImage() {
			String fileName = "";
			imageCode = mode;
			imageCodeOmit(3);
			imageCodeOmit(4);
			switch (imageCode) {
				case 11 :
					fileName = "スライム";//良い人
					break;
				case 12 :
					fileName = "マッドドクター";//情報
					break;
				case 15 :
					fileName = "宝箱";//宝箱
					break;
				case 3 :
				case 33 :
				case 30 :
				case 31 :
				case 310 :
				case 3100 :
				case 3101 :
				case 3102 :
				case 3103 :
					fileName = "商店";//店屋
					break;
				case 4 :
				case 41 :
				case 42 :
				case 43 :
				case 431 :
				case 44 :
				case 4444 :
					fileName = "マッドドクター";//宿屋
					break;
				default :
					fileName = "エアー";
					break;
			}
			return imageURL + fileName + ".png";
		}

		private void imageCodeOmit(int i) {
			if (i * 10 <= mode && mode < (i + 1) * 10) imageCode = i;
			if (i * 100 <= mode && mode < (i + 1) * 100) imageCode = i;
			if (i * 1000 <= mode && mode < (i + 1) * 1000) imageCode = i;
			if (i * 10000 <= mode && mode < (i + 1) * 10000) imageCode = i;
		}

		private void sceneBlank() {
			sc.sceneBlank();
		}

		private  JTextArea pictAreaB() {
			return sc.pictAreaB();
		}

		private void comment() {
			sc.comment();
		}

		public void keyTyped(KeyEvent keyEvent) {
		}

		public void keyPressed(KeyEvent keyEvent) {
			int[][] map = getOriginalMap();
			int pressedKey = keyEvent.getKeyCode();
			String keyName = KeyEvent.getKeyText(pressedKey);
			Common.___logOut___("buttonName = " + buttonName);
			Common.___logOut___("pressedKey = " + pressedKey);
			Common.___logOut___("keyEvent = " + keyEvent);
			if (mode == 1 || mode == 6 || mode == 7) {
				int moveX = 0;
				int moveY = 0;
				switch(pressedKey) {
				case KeyEvent.VK_KP_UP:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_8:
				case KeyEvent.VK_5:
					System.out.println("上が押されました");
					moveY--;
					moveMap(map, moveX, moveY);
					break;
				case KeyEvent.VK_KP_DOWN:
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_2:
				case KeyEvent.VK_0:
					System.out.println("下が押されました");
					moveY++;
					moveMap(map, moveX, moveY);
					break;
				case KeyEvent.VK_KP_LEFT:
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_4:
					System.out.println("左が押されました");
					moveX--;
					moveMap(map, moveX, moveY);
					break;
				case KeyEvent.VK_KP_RIGHT:
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_6:
					System.out.println("右が押されました");
					moveX++;
					moveMap(map, moveX, moveY);
					break;
				default:
					System.out.println(keyName + "KEYが押されました(mode = 1)");
//						メニューを表示する
					pushSound(); // キープッシュ音を鳴らす
					buttonName = Command.menu()[1];
					fieldAction(buttonName);
					break;
				}
			}else {
				switch(pressedKey) {
				case KeyEvent.VK_ENTER:
				case KeyEvent.VK_SPACE:
				case KeyEvent.VK_1:
					Common.___logOut___(keyName + "KEYが押されました");
					Common.___logOut___(ent + "ボタンをクリックします");
					pushSound(); // キープッシュ音を鳴らす
					if(entMark.equals(ent)) {
						button_Ent.doClick();
					} else {
						menuButton[menuNum].doClick();
					}
					Common.___logOut___("buttonName = " + buttonName);
					break;
				case KeyEvent.VK_KP_UP:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_8:
				case KeyEvent.VK_5:
					System.out.println("上が押されました");
					pushSound(); // キープッシュ音を鳴らす
					menuNum --;
					selectStyle();
					break;
				case KeyEvent.VK_KP_DOWN:
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_2:
				case KeyEvent.VK_0:
					System.out.println("下が押されました");
					pushSound(); // キープッシュ音を鳴らす
					menuNum ++;
					selectStyle();
					break;
				default:
					System.out.println(pressedKey + "が押されました");
					pushSound(); // キープッシュ音を鳴らす
					break;
				}
			}
		}

		private void pushSound() {
//			Toolkit.getDefaultToolkit().beep(); // ビープ音を鳴らす
			sound(440f,100);
		}

		private void sound(float frequency, int soundLength) {
			try {
				new Sound(frequency, soundLength);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}

		private void selectStyle() {
			sc.selectStyle();
		}

		private void moveMap(int[][] map, int moveX, int moveY) {
//			移動先が障害物でなければ移動する
			if(isBarrier(map, moveX, moveY) == false) {
				x += moveX;
				y += moveY;
//				はみ出し修正
				x = inRange(map[0].length, x);
				y = inRange(map.length, y);
				Common.___logOut___("縦" + y + "横" + x + "に移動しました");
//				mapNumberが１以外でmapCenterRole()が４と９以外の場合
				if(isDanger(map)) {
//					移動先でイベント発動
					buttonName = Command.menu()[0];
					fieldAction(buttonName);
//					count = 0;
					actionPerformedSwitch();
					button_Ent.doClick();
				} else {
//					移動先のRoleによって各処理を行う
					doRole(map);
				}
				buttonName = null;
			} else {
				Common.___logOut___("そちらへは移動できません");
			}
		}

		private void actionPerformedSwitch() {
			sc.actionPerformedSwitch();
		}

		private void doRole(int[][] map) {
			switch(mapCenterRole(map)) {
				case 4:
					if(mapNumber == 2) { // ダンジョン内の場合
						setMapNumber(0); // 平原MAPへ移動
//						洞窟の位置
						x=8;
						y=8;
						musicReset();
						toNormal();
					} else {
						Common.___logOut___("洞窟MAPへ移動します");
						setMapNumber(2); // 洞窟MAPへ移動
//						洞窟入口位置
						x=7;
						y=7;
						musicReset();
						field(7);
					}
					break;
				case 9:
					if(mapNumber == 1) { // 城内の場合
						setMapNumber(0); // 平原MAPへ移動
						x=6;
						y=6;
						musicReset();
						toNormal();
					} else {
						Common.___logOut___("城MAPへ移動します");
						setMapNumber(1); // 城MAPへ移動
//						城入口位置
						x=0;
						y=3;
						musicReset();
						field(6);
					}
					break;
				default:
			}
			actionPerformedSwitch();
		}

		public void setMapNumber(int mapNumber) {
			this.mapNumber = mapNumber;
			mapChangeSound();
		}

		private void mapChangeSound() {
			sound(100f,150);
			sound(100f,150);
		}

		private boolean isDanger(int[][] map) {
			boolean isDanger = true;
			switch(mapNumber) {
				case 1:
					isDanger = false;
					break;
				default:
			}
			switch(mapCenterRole(map)) {
				case 4:
				case 9:
					isDanger = false;
					break;
				default:
			}
			Common.___logOut___("mapNumber = " + mapNumber);
			Common.___logOut___("mapCenterRole() = " + mapCenterRole(map));
			Common.___logOut___("isDanger = " + isDanger);
			return isDanger;
		}

		private boolean isBarrier(int[][] map, int moveX, int moveY) {
			int[] mapCenter = centerXY(map);
			int nextX = mapCenter[0] + moveX;
			int nextY = mapCenter[1] + moveY;
			boolean barrier = false;
			MapPiece[][] map_Data = map_Data(map);
			if (map_Data[nextY][nextX].getRole() < 1 ) {
				barrier = true;
			}
			return barrier;
		}

		private int inRange(int range, int num) {
			int newNum = num;
			if (range <= num) newNum -= range;
			if (num < 0) newNum += range;
			return newNum;
		}

		private int mapCenterRole(int[][] map) {
			MapPiece[][] map_Data = map_Data(map);
			map2D(map_Image(map_Data));
			int[] mapCenter = centerXY(map);
			int nextX = mapCenter[0];
			int nextY = mapCenter[1];
			return map_Data[nextY][nextX].getRole();
		}

		private int[] centerXY(int[][] baseArray) {
			int[] centerXY = {baseArray[0].length / 2, baseArray.length / 2};
			return centerXY;
		}

		public void keyReleased(KeyEvent keyEvent) {
		}

		public static String inpDS(String s) {
			UIManager.put("OptionPane.okButtonText", "OK");
			UIManager.put("OptionPane.cancelButtonText", "Cancel");
			do {
				value = JOptionPane.showInputDialog(s);
				if (value == null) {// Cancelボタンが押された時
					display.setText("取消されました。");
					break;
				}
			} while (value.equals(null));
			if (value.equals("")) {
			} else {
				display.setText(value + " ");
			}
			return value;
		}

		public static int inpDI(String s) {
			UIManager.put("OptionPane.okButtonText", "OK");
			UIManager.put("OptionPane.cancelButtonText", "Cancel");
			do {
				value = JOptionPane.showInputDialog(s);
				if (value == null) {// Cancelボタンが押された時
					display.setText("取消されました。");
					break;
				}
			} while (value.equals(null));
			if (value.equals("")) {
				display.setText("数値を入力してください。");
				inpDI(s);
			} else {
				display.setText(value + " ");
			}
			int r = Integer.parseInt(value);
			return r;
		}

		public static void setFrame(JFrame frame) {
			Controller.frame = frame;
		}

		public JFrame getFrame() {
			return frame;
		}

		public static void rem() {
			// Money tableModel = Money.getMon();
			// Table.win.table.setModel(tableModel);
			// table.setRowSelectionInterval(0, Money.data.length - 1);
			// table.revalidate();
		}

		public static void setTex(String text) {
			tex = text;
		}

		public String getTex() {
			return tex;
		}

		public static void setMessage(String text) {
			ent = " 　 ";
			story = new Story();
			story.on(text);
		}

		public static void setMessageEnt(String text) {
			ent = entMark;
			story = new Story();
			story.on(text + "     next" + ent);
		}

		public String getMessage() {
			return message;
		}

		public static void setMode(int mode) {
			Common.___logOut___("Screen.setMode(" + mode +") します");
			Controller.mode = mode;
		}

		public static int getMode() {
			return mode;
		}

		public static void setMenu(Object[] menu) {
			Controller.menu = menu;
		}

		public Object[] getMenu() {
			return menu;
		}

		public static void setCount(int count) {
			Controller.count = count;
		}

		public static int getCount() {
			return count;
		}

		public void setEnt(String ent) {
			Controller.ent = ent;
		}

		public static String getEnt() {
			return ent;
		}
	}

