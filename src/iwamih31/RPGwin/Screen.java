package iwamih31.RPGwin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Screen extends JFrame implements ActionListener, KeyListener {

	JLabel ansLabel;
	static JLabel display;
	protected static String value;
	String op1;
	String op2;
	String operator;
	int opMode;
	private static JLabel q;
	private static Object[] ynList;
	private JTextArea inpT;
	private Story sto;
	private JComponent pict;
	private JTextArea textAreaN;
	private JTextArea textAreaW;
	private JTextArea textAreaC;
	private JTextArea textAreaE;
	private JTextArea textAreaS;
	private static Border border;
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
	private MapPiece[][] mapData;
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
	private static Component panelN;
	private static Component panelW;
	private static Component panelC;
	private static Component panelE;
	private static Component panelS;
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

	public Screen(Object[] mList) {
		super("メニュー");

		/*
		 * String exterior =
		 * "javax.swing.plaf.windows.WindowsLookAndFeel";//メタルはplaf
		 * .の後をmetal.MetalLookAndFeelへ変える try{
		 * UIManager.setLookAndFeel(exterior);
		 * SwingUtilities.updateComponentTreeUI(this.frame); } catch (Exception
		 * e) { e.printStackTrace(); }
		 */

//		//キー入力の有効化
//		addKeyListener(this);
//		setFocusable(true);

		menu(mList);
	}

	public Screen(String s) {
		super(s);

//		//キー入力の有効化
//		addKeyListener(this);
//		setFocusable(true);

		start(s);
	}

	private void start(String s) {
//		ディスプレイサイズを基準に、横1％、縦1％、フォントサイズを決定
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        w = screenSize.width / 100;
        h = screenSize.height / 100;
        fontSize = w;
		setMode(0);
		border = new LineBorder(Color.WHITE, 2, true);
//		yName = Main.getyName();
		ynList = new Object[]{ "はい", "いいえ" };
		entMark = (" ⇒ ");
		ent = entMark;
		cancel = "Cancel";
		labelSet("");
		int bWE = 30;
		textAreaB = textAreaSet("",1,5);
		menuAreaB = textAreaSet("",1,1);
		textAreaN = textAreaSet("",5,5);/////////////メンバーステータス
		textAreaW = textAreaSet("",5,bWE);///////////現状
		textAreaC = textAreaSet("",5,9);////////////画面
		textAreaE = textAreaSet("",5,bWE);///////////コマンド
		textAreaS = textAreaSet("",1,5);/////////////メッセージ

		labelN = labelSet("メンバーステータス");
		labelW = labelSet("現状");
		labelC = labelSet("画面");
		labelE = labelSet("コマンド");
		labelS = labelSet("メッセージ");

		panelN = panelSetUD(textAreaN, textAreaB);
		panelW = panelSetUD(null, textAreaW);
		panelC = panelSetUD(null,labelC);
		panelE = panelSetUD(null, textAreaE);
		panelS = panelSetUD(null, textAreaS);

		space = labelSet("                                       ");

		imageURL =  "image/";

		music = null;

		repeatMusic("オープニング");

		outer();
	}

	private static JLabel b() {
		JLabel b = labelSet(" ");
		return b;
	}

	private static JTextArea textAreaSet(String text, int rows, int columns) {

		JTextArea textAreaSet = new JTextArea(text, rows,columns);
		format(textAreaSet);
		textAreaSet.setEditable(false);
		textAreaSet.setOpaque(false);///////////////背景を透明にする
		return textAreaSet;
	}

	private static JLabel labelSet(String string) {

		JLabel labelSet = new JLabel(string, JLabel.CENTER);
		format(labelSet);
		labelSet.setOpaque(false);///////////////背景を透明にする
		return labelSet;
	}

	private static JPanel panelSetLR(Object left, Object right) {
		JPanel panelSet = new JPanel();
		format(panelSet, 100, 100);
		panelSet.setLayout(new BoxLayout(panelSet, BoxLayout.X_AXIS));
		if (left != null) {
			panelSet.add((Component) left);
		}
		if (right != null) {
			panelSet.add((Component) right);
		}
		return panelSet;
	}

	private static JPanel panelSetUD(Object up, Object down) {
		JPanel panelSet = new JPanel();
		format(panelSet, 100, 100);
		panelSet.setLayout(new BoxLayout(panelSet, BoxLayout.Y_AXIS));
		if (up != null) {
			panelSet.add((Component) up);
		}
		if (down != null) {
			panelSet.add((Component) down);
		}
		return panelSet;
	}

	private static JPanel panelSetWCE(Object west, Object center, Object east) {
		JPanel panelSet = new JPanel();
		format(panelSet, 100, 100);
		panelSet.setLayout(new BorderLayout());
		if (west != null) {
			panelSet.add((Component) west, BorderLayout.WEST);
		}
		if (center != null) {
			panelSet.add((Component) center, BorderLayout.CENTER);
		}
		if (east != null) {
			panelSet.add((Component) east, BorderLayout.EAST);
		}
		return panelSet;
	}

	private static JPanel panelSetNCS(Object north, Object center, Object south) {
		JPanel panelSet = new JPanel();
		format(panelSet);
		panelSet.setLayout(new BorderLayout());
		if (north != null) {
			panelSet.add((Component) north, BorderLayout.NORTH);
		}
		if (center != null) {
			panelSet.add((Component) center, BorderLayout.CENTER);
		}
		if (south != null) {
			panelSet.add((Component) south, BorderLayout.SOUTH);
		}
		return panelSet;
	}

	private static JPanel panelSetTMB(Object top, Object middle, Object bottom) {
		JPanel panelSet = new JPanel();
		format(panelSet);
		panelSet.setLayout(new FlowLayout());
		if (top != null) {
			panelSet.add((Component) top);
		}
		if (middle != null) {
			panelSet.add((Component) middle);
		}
		if (bottom != null) {
			panelSet.add((Component) bottom);
		}
		return panelSet;
	}

	private static void outer() {
		if (frame != null) {
			frame.setVisible(false);
		}
		frame = new JFrame("RPG");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(w*100, h*100);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setLayout(new FlowLayout());


		changePanelSet = new JPanel();
		format(changePanelSet);
		cardLayout = new CardLayout();
		changePanelSet.setLayout(cardLayout);

		changePanelSet.add(panelSet(), "通常");

		clear = new JPanel();
		format(clear);
		changePanelSet.add(clear, "背景");

		frame.add(changePanelSet);

		change("通常");

		frame.setVisible(true);

	}

	private static void change(String mode) {

		cardLayout.removeLayoutComponent(panelSet);
		changePanelSet.add(panelSet, mode);
		cardLayout.show(changePanelSet,mode);

//		frame.setFocusable(false);
//
////			Robot の delay() で間を空ける
//		Robot robot = null;
//		try {
//			robot = new Robot();
//		} catch (AWTException e1) {
//			e1.printStackTrace();
//		}
//		System.out.println("");//////////////////////////////////////////
//		System.out.println("robot.delay(20)します");/////////////////////
//		System.out.println("");//////////////////////////////////////////
//		robot.delay(20);
	}

	private static void change() {

		panelSet();
		change("通常");
	}

	private static JPanel panelSet() {

//		JPanel panelSetC = new JPanel();
//		format(panelSetC);
//		panelSetC.setPreferredSize(new Dimension(w*70, h*100));
//		panelSetC.setLayout(new BoxLayout(panelSetC, BoxLayout.Y_AXIS));
//		panelSetC.add(panelN);
//		panelSetC.add(panelC);
//		panelSetC.add(panelS);

		JPanel panelSetC = panelSetNCS(panelN, panelC, panelS);
		panelSetC.setPreferredSize(new Dimension(w*70, h*80));

		panelSet = new JPanel();
		format(panelSet);
		panelSet.setLayout(new FlowLayout());
		panelSet.add(panelW);
		panelSet.add(b());
//		panelSet.add(panelSetC);
		panelSet.add(panelSetC);
		panelSet.add(b());
		panelSet.add(panelE);

		return panelSet;

	}

	private static void centerSet(Object west, Object center, Object east) {

		JLabel space = labelSet("                                       ");
		JPanel panelQ = panelSetWCE(west, center, east);
		JPanel panelQA = panelSetWCE(space, panelQ, space);


//		int rows = 7;
//		int columns = 10;
//		JTextArea blankAreaN = textAreaSet(" ",rows,columns);
//		JTextArea blankAreaS = textAreaSet(" ",rows,columns);

		JLabel blankAreaN = labelSet("");
		blankAreaN.setPreferredSize(new Dimension(w*2, h*20));

		JLabel blankAreaS = labelSet("");
		blankAreaS.setPreferredSize(new Dimension(w*2, h*30));

		panelC = panelSetNCS(blankAreaN, panelQA,blankAreaS);

		change();
	}


	private static void format(Component component, int width, int height) {
		format(component);
		component.setSize(width, height);
//		component.setPreferredSize(new Dimension(width, height));
	}


	private static void format(Component component) {

		component.setFont(new Font("Monospaced", Font.BOLD, fontSize));
		component.setForeground(Color.WHITE);
		component.setBackground(Color.BLACK);

		if (mode > 0 && dead() > 0) {
			component.setForeground(Color.RED);
		}

	}


	private static Border border() {

		border = new LineBorder(Color.WHITE, 2, true);

		if (mode > 0 && denger() > 0) {
			border = new LineBorder(Color.YELLOW, 2, true);
		}

		return border;
	}

	private static int denger() {

		int dengerLevel = 0;
		Member[] party = Main.getParty();
		for (int i = 0; i < party.length; i++) {
			if (party[i].getHp() <= party[i].getAp()) dengerLevel++;
		}
		return dengerLevel;
	}

	private static int dead() {

		int deadNumber = 0;
		Member[] party = Main.getParty();
		for (int i = 0; i < party.length; i++) {
			if (party[i].getHp() < 1) deadNumber++;
		}
		return deadNumber;
	}

	static void que() {

		labelC = labelSet(tex);

		JPanel bPanel = new JPanel();
		format(bPanel);
		bPanel.setLayout(new GridLayout(2, 0, 0, 0));
		bPanel.setPreferredSize(new Dimension(w*5, h/2));

		JButton[] button = new JButton[2];////////////////////ボタンの数

		for (int i = 0; i < 2; i++) {
			String bN = (String) ynList[i];
			button[i] = new JButton(bN);
			format(button[i]);
			button[i].setFocusPainted(false);
			button[i].addActionListener(Main.getSc());
			button[i].addKeyListener(Main.getSc());
			button[i].setBorder(border());

			bPanel.add(button[i]);
		}

		centerSet(space,labelC, bPanel);

		// more();
	}

	private void inputName(String s) {

		buttonName = null;

		inpT = new JTextArea(1, 8);

		format(inpT);

		inpT.setBorder(border());
//		inpT.setPreferredSize(new Dimension(w*2, h/5));

		JPanel bPanel = new JPanel();
		format(bPanel);
		bPanel.setLayout(new GridLayout(1, 0, 0, 0));

//		bPanel.setPreferredSize(new Dimension(w*2, h/5));
//		bPanel.setPreferredSize(new Dimension(60, 20));

		int bI = 1;/////////////////////////////////////ボタンの数

		JButton[] button = new JButton[bI];

		String[] bList = { "OK" };

		for (int i = 0; i < bI; i++) {
			String bN = (String) bList[i];
			button[i] = new JButton(bN);
			format(button[i]);
			button[i].setPreferredSize(new Dimension(w*2, h*2));
			button[i].setMargin(new Insets(20, 10, 20, 10));///////文字周りの幅
			button[i].setFocusPainted(false);
			button[i].addActionListener(this);
			button[i].addKeyListener(this);
			button[i].setBorder(border());
			bPanel.add(button[i]);
		}

		q = labelSet(tex);
//		q.setPreferredSize(new Dimension(w*2, h/2));

		JLabel blankAreaN = labelSet("");
		blankAreaN.setPreferredSize(new Dimension(w*2, h*4));
		JLabel blankAreaS = labelSet("");
		blankAreaS.setPreferredSize(new Dimension(w*2, h*4));
		JPanel a = panelSetLR(inpT, bPanel);
		JPanel answer = panelSetNCS(blankAreaN, a, blankAreaS);

//		a.setPreferredSize(new Dimension(w*5, h/5));


		centerSet(space, q, answer);

	}

	private void begin() {
		tex = "     主人公の名前は何にしますか？";
		inputName("名前");
	}

	private void load() {
		Main.load();
		setMapNumber(0);
		x = 6;
		y = 6;
	}

	public void menu(Object[] mList) {

		JPanel panel = new JPanel();
		format(panel);
		panel.setLayout(new GridLayout(5, 0, 0, 0));
//			panel.setBorder(border);
		panel.setPreferredSize(new Dimension(w * 10, h * 50));

		menuButton = new JButton[mList.length];////////////////////ボタンの数

		for (int i = 0; i < (mList.length); i++) {
			String bN =  String.valueOf(mList[i]);
			menuButton[i] = new JButton(bN);
			format(menuButton[i], 50, 50);
			menuButton[i].setMargin(new Insets(20, 10, 20, 10));///////文字周りの幅
			menuButton[i].setFocusPainted(false);
			menuButton[i].addActionListener(this);
			menuButton[i].addKeyListener(this);
			menuButton[i].setBorder(border());
			panel.add(menuButton[i]);
		}

		String bN =  cancel;
		cancelButton = new JButton(bN);
		format(cancelButton);
		cancelButton.setMargin(new Insets(20, 10, 20, 10));///////文字周りの幅
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(this);
		cancelButton.addKeyListener(this);
		cancelButton.setBorder(border());
		cancelButton.setPreferredSize(new Dimension(w*10, h*13));
//			panel.add(jButton);

		JPanel bPanel = panelSetNCS(panel, menuAreaB,cancelButton);
		bPanel.setPreferredSize(new Dimension(w*10, h*80));

		bPanel.setFocusable(true);

		panelE = panelSetWCE(null, bPanel, null);

		menuNum = 0;

		if(mode == 1) menuNum = 1;

		selectStyle();

	}

	public void actionPerformed(ActionEvent e) {

		String select = e.getActionCommand();

		System.out.println("");////////////////////////////////////////////
		System.out.println("[" + select + "]ボタンがクリックされました");//
		System.out.println("");////////////////////////////////////////////

		if(select.equals(buttonName)){ // 同じボタン名がクリックされたら
			// クリックしたボタン名はent
			buttonName = ent;
		}else{	// それ以外がクリックされたら
			// クリックしたボタン名はそのまま使用
			buttonName = select;
		}


		System.out.println("");///////////////////////////////////////////
		System.out.println("buttonName = " + buttonName);/////////////////
		System.out.println("");///////////////////////////////////////////

		actionPerformedSwitch();

	}

	public void actionPerformedSwitch() {

		System.out.println("");///////////////////////////////////////////
		System.out.println("mode = " + mode);/////////////////////////////
		System.out.println("");///////////////////////////////////////////

		System.out.println("");///////////////////////////////////////////
		System.out.println("buttonName = " + buttonName);/////////////////
		System.out.println("");///////////////////////////////////////////

		System.out.println("");///////////////////////////////////////////
		System.out.println("count = " + count);///////////////////////////
		System.out.println("");///////////////////////////////////////////

		// フォーカスをframeに持ってくる
		frame.setFocusable(true);
		System.out.println("");/////////////////////////////////////
		System.out.println("frameにフォーカスをあてました");////////
		System.out.println("");/////////////////////////////////////

		//キー入力の有効化
		frame.addKeyListener(this);
		System.out.println("");/////////////////////////////////////
		System.out.println("frameのキー入力を有効化しました");////////
		System.out.println("");/////////////////////////////////////

//		JOptionPane.showMessageDialog(null, "mode = " + mode);

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

				if (buttonName.equals(ynList[0])) {
					musicReset();
					begin();
					opening();
				}
				if (buttonName.equals(ynList[1])) {
					musicReset();
					load();
					toNormal();
				}else{
					opening();
				}
				break;

			case 1 ://探す

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

			default :

				break;
		}

	}


	private void opening() {
//		JOptionPane.showMessageDialog(null, "opening() します");
		x = 6;
		y = 6;

		if (buttonName.equals("OK")) {
			yName = null;
			String inputName = inpT.getText();

			int p = 0;
			char[] chars = inputName.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				p += (String.valueOf(chars[i]).getBytes().length);
			}
			System.out.println("");///////////////////////////////////////////
			System.out.println("文字バイト数 = " + p);/////////////////////////////
			System.out.println("");///////////////////////////////////////////

			while (yName == null) {
				if (p < 8) {
					if (inputName.equals("")) {
						inputName = Main.getyName();
					}
					Main.setyName(inputName);
					yName = inputName;

					System.out.println("");///////////////////////////////////////////
					System.out.println("yName = " + yName);/////////////////////////////
					System.out.println("");///////////////////////////////////////////
				} else {

					System.out.println("");///////////////////////////////////////////
					System.out.println("yName = " + yName);/////////////////////////////
					System.out.println("");///////////////////////////////////////////

					buttonName.equals(null);
					tex = "もう少し短い名前でお願いします";
					change();
					inputName("名前");
				}
			}
//			JOptionPane.showMessageDialog(null, "yName = " + yName);
			Main.begin();
			story = new Story();
			story.on("  ・・・ある日[ " + yName + " ]は、王様に呼び出された・・・");
			prologue();
		}

		if (buttonName.equals(ent)) {

			System.out.println("");///////////////////////////////////////////
			System.out.println("buttonName = " + buttonName);/////////////////////////////
			System.out.println("");///////////////////////////////////////////

			System.out.println("");///////////////////////////////////////////
			System.out.println("count = " + count);/////////////////////////////
			System.out.println("");///////////////////////////////////////////

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

				count = (count + 1);

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

	private void field(int modeNum) {

		System.out.println("");////////////////////////////////////////
		System.out.println("field(" + modeNum + ") します");///////////
		System.out.println("");////////////////////////////////////////

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
		// TODO 自動生成されたメソッド・スタブ

		System.out.println("");//////////////////////////////////////////
		System.out.println("fieldMenu(String[] setMenu) します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		buttonName = null;

		partySt();
		info(goldList(),itemList(),"");
		scene();
		menu(setMenu);
		comment();

		change();

	}

	private void use() {
		// TODO 自動生成されたメソッド・スタブ

		System.out.println("");//////////////////////////////////////////
		System.out.println("use() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		buttonName = null;

		partySt();
		info(goldList(),itemList(),"");
		scene();
		menu(menu);
		comment();

		change();

	}

	private void whichUse(String selectButtonName) {

		System.out.println("");//////////////////////////////////////////
		System.out.println("whichUse(" + selectButtonName +") します");////////
		System.out.println("");//////////////////////////////////////////

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

			case 1 ://探す
				count = 0;

				fieldAction(buttonName);

				break;

			case 10 ://
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

		System.out.println("");//////////////////////////////////////////
		System.out.println("adventure() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		buttonName = null;

		partySt();
		info(goldList(),itemList(),"");
		scene();
		menu(Command.menu());
		comment();

		change();

	}

	private void fieldAction(String selectButtonName) {

		System.out.println("");//////////////////////////////////////////
		System.out.println("fieldAction(" + selectButtonName +" ) します");////////
		System.out.println("");//////////////////////////////////////////

		String[] menu = Command.menu();
		if (selectButtonName != null) {


			// 探す
			if (selectButtonName.equals(menu[0])) {

				buttonName = null;

				printMode();

				Main.action(1);

//				setMessageEnt(Main.getText());

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

			System.out.println("");/////////////////////////////
			System.out.println("eventLoop count = " + count);///
			System.out.println("");/////////////////////////////

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

	private void eventLoop_Heal() {
		if (buttonName.equals(ent)) {

			System.out.println("");/////////////////////////////
			System.out.println("eventLoop_Heal count = " + count);///
			System.out.println("");/////////////////////////////

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

				if (Battle.getfMode()==0){
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

//			case 2110 ://使う,道具,[1],[0],結果
//			case 2111 ://使う,道具,[1],[1],結果
//			case 2112 ://使う,道具,[1],[2],結果
//			case 2113 ://使う,道具,[1],[3],結果
//
//				if (buttonName.equals(ent)) {
//					itemMenu1(mode - 2110);
//				}
//				break;

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

//			case 2140 ://使う,道具,[4],[0],結果
//			case 2141 ://使う,道具,[4],[1],結果
//			case 2142 ://使う,道具,[4],[2],結果
//			case 2143 ://使う,道具,[4],[3],結果
//
//				if (buttonName.equals(ent)) {
//					itemMenu4(mode - 2140);
//				}
//				break;

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

//			case 21010 ://バトルモード,使う,道具,[1],[0],結果
//			case 21011 ://バトルモード,使う,道具,[1],[1],結果
//			case 21012 ://バトルモード,使う,道具,[1],[2],結果
//			case 21013 ://バトルモード,使う,道具,[1],[3],結果
//
//				if (buttonName.equals(ent)) {
//					battleLoop();
//				}
//				break;

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

//			case 21040 ://バトルモード,使う,道具,[4],[0],結果
//			case 21041 ://バトルモード,使う,道具,[4],[1],結果
//			case 21042 ://バトルモード,使う,道具,[4],[2],結果
//			case 21043 ://バトルモード,使う,道具,[4],[3],結果
//
//				if (buttonName.equals(ent)) {
//					battleLoop();
//				}
//				break;
		}
	}

	private void item() {

		System.out.println("");//////////////////////////////////////////
		System.out.println("Item() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

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

		System.out.println("");////////////////////////////////
		System.out.println("itemLoop() します");///
		System.out.println("");////////////////////////////////

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
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("");//////////////////////////////////////////
		System.out.println("ex() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

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

		System.out.println("");//////////////////////////////////////////
		System.out.println("whoExField(" + setButtonName + ") します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		Member[] party = Main.getParty();

		setMessage("どの術を使いますか？");////////////////////次の質問

		menu = Main.getpNa();



			if (setButtonName.equals(menu[0])) {
				useEx = new Power(party[0]);
				Main.ex(0);
				setMode(220);
				ex();
			}

			if (setButtonName.equals(menu[1])) {
				useEx = new Wonder(party[1]);
				Main.ex(1);
				setMode(220);
				ex();
			}

			if (setButtonName.equals(menu[2])) {
				useEx = new Bless(party[2]);
				Main.ex(2);
				setMode(220);
				ex();
			}

			if (setButtonName.equals(menu[3])) {
				useEx = new Magic(party[3]);
				Main.ex(3);
				setMode(220);
				ex();
			}

	}

	private void whoExBattle() {

		int actor = Battle.getActor();

		System.out.println("");//////////////////////////////////////////
		System.out.println("whoExBattle(" + actor + ") します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

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

		System.out.println("");//////////////////////////////////////////
		System.out.println("useEx = " + useEx.getClass() + " です");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		System.out.println("");//////////////////////////////////////////
		System.out.println("Ex.getName() = " + Ex.getName() + " です");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

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

		System.out.println("");////////////////////////////////
		System.out.println("exLoop() します");///
		System.out.println("");////////////////////////////////

		String[] text = useEx.getExText();

		System.out.println("");////////////////////////////////
		System.out.println("count =(" + count + ")");///
		System.out.println("");////////////////////////////////

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
//				setMode(50);
				Main.getBat().turn();
				menu = Battle.getMenu();
//				battle();
			}
		}
	}

	public void actionPerformedSwitch3() {

		Member user;
		switch (mode) {

			case 3 ://店

				musicReset();

				count = 0;
				Main.action(3);
				setMessage("「いらっしゃいませ、御用は何でしょうか？」");
				menu = new Object[]{ "買う", "売る" };
				shop();
				setMode(33);

				break;

			case 33 ://店,どうする？
				count = 0;
				if(buttonName.equals(menu[0])){//買う
					setMode(30);
					Main.shop(0);
					setMessage("何を買いますか？");
					menu = new Object[]{ "道具", "武器" };
					shop();
				}

				if(buttonName.equals(menu[1])){//売る
					setMode(31);
					Main.shop(1);
					setMessage("何を売りますか？");
					menu = new Object[]{ "道具", "武器" };
					shop();
				}
				break;

			case 30 ://店,買う,どちら？
				count = 0;
				if(buttonName.equals(menu[0])){
					setMode(300);
					Main.buy(0);
					setMessage("どれを買いますか？");
					menu = Item.menu();
					shop();
				}

				if(buttonName.equals(menu[1])){
					setMode(301);
					Main.buy(1);
					setMessage("誰の武器を買いますか？");
					menu = Main.getpNa();
					shop();
				}
				break;

			case 31 ://店,売る,どちら？
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
				break;

			case 300 ://店,買う,道具,どれを？

				count = 0;
				menu = Item.menu();
				user = Main.getHu();

				for (int i = 0; i < menu.length; i++) {
					if (buttonName.equals(menu[i])) {
						setMode(3000);
						Shop.buyItem(user, i);
						shopLoop();
					}
				}

				if (buttonName.equals(cancel)) {
					setMode(3000);
					Shop.buyWaponWhich(10);
					shopLoop();
				}
				break;

			case 3000 ://店,結果ループ

				if (buttonName.equals(ent)) {
					shopLoop();
				}
				break;

			case 301 ://店,買う,武器,誰の？
				count = 0;

				menu = Main.getpNa();

				for (int i = 0; i < menu.length; i++) {

					if (buttonName.equals(menu[i])) {
						setMode(3010);
						Shop.buyWapon(i);
						shopWapon(i);
					}
				}
				break;

			case 3010 ://店,買う,武器,どれを？

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
				break;

			case 310 ://店,売る,道具

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
				break;
		}
	}

	private void shop() {
			// TODO 自動生成されたメソッド・スタブ

	//		setMode(3);/////////////////////////////////// ?

			System.out.println("");//////////////////////////////////////////
			System.out.println("shop() します");//////////////////////////////
			System.out.println("");//////////////////////////////////////////

			buttonName = null;

			partySt();
			info(goldList(),itemList(),shopList());
			scene();
			menu(menu);
			comment();

			change();

		}

	private void shopWapon(int i) {
		// TODO 自動生成されたメソッド・スタブ

//		setMode(3);/////////////////////////////////// ?

		System.out.println("");//////////////////////////////////////////
		System.out.println("shop() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		buttonName = null;

		partySt();
		info(goldList(),itemList(),shopWaponList(i));
		scene();
		menu(menu);
		comment();

		change();

	}

	private void shopLoop() {

		System.out.println("");/////////////////////////////
		System.out.println("buttonName = " + buttonName);///
		System.out.println("");/////////////////////////////

		String[] text = Shop.getShopText();

		if (count < text.length) {

			setMessageEnt(text[count]);

			if (count == 0) {

			}

			count = (count + 1);

			shop();

		} else {

			toNormal();

		}
	}

	public void actionPerformedSwitch4() {

		switch (mode) {

			case 4 ://宿屋

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

		}
	}

	private void inn() {

		System.out.println("");//////////////////////////////////////////
		System.out.println("inn() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

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
		System.out.println("");//////////////////////////////////////////
		System.out.println("status() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

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

	private void innMenu0() {

		System.out.println("");////////////////////////////////////////////////
		System.out.println("innMenu0() します  buttonName = " + buttonName);///
		System.out.println("");////////////////////////////////////////////////

		if (count == 0) {
			count = (count + 1);
			inn();

		} else {

			toNormal();
		}
	}

	private void innMenu1() {

		System.out.println("");////////////////////////////////////////////////
		System.out.println("innMenu1() します  buttonName = " + buttonName);///
		System.out.println("");////////////////////////////////////////////////

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

		System.out.println("");////////////////////////////////////////////////
		System.out.println("innMenu2() します  buttonName = " + buttonName);///
		System.out.println("");////////////////////////////////////////////////

		count = 0;

		setMode(444);

		Main.innMenu(0);

		inn();
	}

	private void innMenu3() {

		System.out.println("");////////////////////////////////////////////////
		System.out.println("innMenu3() します  buttonName = " + buttonName);///
		System.out.println("");////////////////////////////////////////////////

		setMode(431);
		status();
		count = 0;
	}

	private void innMenu4() {

		System.out.println("");///////////////////////////////
		System.out.println("innMenu4() します");///
		System.out.println("");///////////////////////////////

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
//				buttonName = null;
				menu = Battle.getMenu();
				Main.battle();
				break;

			case 50 ://戦闘,メンバー

//				JOptionPane.showMessageDialog(null, "mode = " + mode);

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

//				buttonName = null;

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

//			case 5500 ://戦闘,メンバー,戦う,0
//			case 5501 ://戦闘,メンバー,戦う,1
//			case 5502 ://戦闘,メンバー,戦う,2
//			case 5503 ://戦闘,メンバー,戦う,3
//
////				buttonName = null;
//
//				if (buttonName.equals(ent)) {
//
//					battleLoop();
//				}
//
//				break;

			case 551 ://戦闘,メンバー,道具

//				JOptionPane.showMessageDialog(null, "mode = " + mode);
//
//				buttonName = null;
//
//				setMessage("どのアイテムを使いますか？");
//
//				menu = Item.menu();
//
//				battle();
//
//				setMode(21);

				break;

			case 552 ://戦闘,メンバー,能力

//				buttonName = null;

				if (buttonName.equals(ent)) {

					battleLoop();
				}

				break;

			case 553 ://戦闘,メンバー,逃げる

//				buttonName = null;

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

		System.out.println("");//////////////////////////////////////////
		System.out.println("battle() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		buttonName = null;

		partySt();
		info(goldList(),itemList(),"");
		monster();
		menu(menu);
		comment();

		change();
	}

	private void battleEx() {

		System.out.println("");//////////////////////////////////////////
		System.out.println("battleEx() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		buttonName = null;

		partySt();
		info(goldList(),itemList(),exList());
		monster();
		menu(menu);
		comment();

		change();
	}

	private void battleLoop() {

		System.out.println("");///////////////////
		System.out.println("battleLoop() します" );///
		System.out.println("");///////////////////

		System.out.println("");///////////////////
		System.out.println("count = " + count);///
		System.out.println("");///////////////////

		String[] text = Battle.getBattleText();

		if (count < text.length) {

			setMessageEnt(text[count]);

			if (count == 0) {

			}

			count = (count + 1);

			battle();

		} else {
			if(Battle.getfMode() == 0){
				musicReset();
				toNormal();
			}else{
				count = 0;
//				setMode(50);
				Main.getBat().turn();
				menu = Battle.getMenu();
//				battle();
			}
		}
	}

	private void expLoop() {

		System.out.println("");///////////////////
		System.out.println("expLoop() します");///
		System.out.println("");///////////////////

		System.out.println("");///////////////////
		System.out.println("count = " + count);///
		System.out.println("");///////////////////


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

		System.out.println("");///////////////////
		System.out.println("levLoop() します");///
		System.out.println("");///////////////////

		System.out.println("");///////////////////
		System.out.println("count = " + count);///
		System.out.println("");///////////////////

		String[] text = Battle.getBattleText();

		if (count < 5) {

//			Battle.lev(count-1);

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

		System.out.println("");///////////////////
		System.out.println("goldLoop() します");///
		System.out.println("");///////////////////

		System.out.println("");///////////////////
		System.out.println("count = " + count);///
		System.out.println("");///////////////////

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

		System.out.println("");///////////////////
		System.out.println("getItemLoop() します");///
		System.out.println("");///////////////////

		System.out.println("");///////////////////
		System.out.println("count = " + count);///
		System.out.println("");///////////////////

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
				System.out.println("");//////////////////////////////////////////
				System.out.println("戦闘後処理、未完了です");//////////////////////////////
				System.out.println("");//////////////////////////////////////////
			}
		}
	}


	private void who() {

		System.out.println("");//////////////////////////////////////////
		System.out.println("who() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		buttonName = null;

		partySt();
		info(goldList(),itemList(),"");
		scene();
		menu(Main.getpNa());
		comment();

		change();
	}



	private void attack() {

		System.out.println("");//////////////////////////////////////////
		System.out.println("attack() します");//////////////////////////////
		System.out.println("");//////////////////////////////////////////

		buttonName = null;

		partySt();
		info(goldList(),itemList(),"");
		monster();
		menu(Battle.mNa());
		comment();

		change();
	}

	private void printMode() {

		System.out.println("");//////////////////////////////////////////
		System.out.println("mode = " + getMode() + " です");//////////////////
		System.out.println("");//////////////////////////////////////////
	}

	private void partyStAll() {
		JTable pTab = new JTable();
		format(pTab);
		Status tableModel = new Status();
		pTab.setModel(tableModel);
		pTab.setAutoCreateRowSorter(true);
		pTab.setRowHeight(30);
		pTab.setShowVerticalLines(true);// 縦枠
		pTab.setShowHorizontalLines(false);// 横枠
		pTab.setPreferredSize(new Dimension(890, 280));
		pTab.setBorder(border());

		DefaultTableCellRenderer tableCellRendererC = new DefaultTableCellRenderer();
		tableCellRendererC.setHorizontalAlignment(JLabel.CENTER);

		TableColumn[] name = new TableColumn[pTab.getColumnModel().getColumnCount()];

		for (int i = 0; i < name.length; i++) {
			name[i] = pTab.getColumnModel().getColumn(i);
			name[i].setCellRenderer(tableCellRendererC);
		}

		format(pTab.getTableHeader(), 30, 30);
		pTab.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JPanel panel = panelSetUD(pTab.getTableHeader(), pTab);
		format(panel);
		panel.setBorder(border());

		panelN = panelSetWCE(null, panel, null);


	}

	private void partySt() {
		JTable pTab = new JTable();
		format(pTab);
		Main tableModel1 = Main.mai;
		pTab.setModel(tableModel1);
//		pTab.setAutoCreateRowSorter(true);
		pTab.setRowHeight(fontSize * 2);
		pTab.setShowVerticalLines(true);// 縦枠
		pTab.setShowHorizontalLines(false);// 横枠
//		pTab.setPreferredSize(new Dimension(w*70, h*6));
		pTab.setBorder(border());

		DefaultTableCellRenderer tableCellRendererC = new DefaultTableCellRenderer();
		tableCellRendererC.setHorizontalAlignment(JLabel.CENTER);

		TableColumn[] name = new TableColumn[pTab.getColumnModel().getColumnCount()];

		for (int i = 0; i < name.length; i++) {
			name[i] = pTab.getColumnModel().getColumn(i);
			name[i].setCellRenderer(tableCellRendererC);
		}

		format(pTab.getTableHeader());
		pTab.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JPanel panel = panelSetUD(pTab.getTableHeader(), pTab);
		format(panel);
		panel.setBorder(border());

		panelN = panelSetWCE(null, panel, null);

	}

	private void partyStBlank() {

		JTextArea partyStBlank = textAreaSet(" ", 5, 10);
		panelN = panelSetNCS(null, partyStBlank, null);
	}

	private void monster() {
		JTable pTab = new JTable();
		format(pTab);
		Battle tableModel1 = Main.getBat();
		pTab.setModel(tableModel1);
//		pTab.setAutoCreateRowSorter(true);
		pTab.setRowHeight(fontSize * 2);
		pTab.setShowVerticalLines(true);// 縦枠
		pTab.setShowHorizontalLines(false);// 横枠
//		pTab.setPreferredSize(new Dimension(w*70, h*6));
		pTab.setBorder(border());

		DefaultTableCellRenderer tableCellRendererC = new DefaultTableCellRenderer();
		tableCellRendererC.setHorizontalAlignment(JLabel.CENTER);

		TableColumn[] name = new TableColumn[pTab.getColumnModel().getColumnCount()];

		for (int i = 0; i < name.length; i++) {
			name[i] = pTab.getColumnModel().getColumn(i);
			name[i].setCellRenderer(tableCellRendererC);
		}

		format(pTab.getTableHeader());
		pTab.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JPanel stPanel = panelSetUD(pTab.getTableHeader(), pTab);
		format(stPanel);
		stPanel.setBorder(border());

		setBackPanel(imageURL + "バトル.png");

		ImageIcon icon0 = createImageIcon(drawMonster(0));
		JLabel label0 = new JLabel(icon0);

		ImageIcon icon1 = createImageIcon(drawMonster(1));
		JLabel label1 = new JLabel(icon1);

		ImageIcon icon2 = createImageIcon(drawMonster(2));
		JLabel label2 = new JLabel(icon2);

		ImageIcon icon3 = createImageIcon(drawMonster(3));
		JLabel label3 = new JLabel(icon3);

		ImageIcon iconItem = createImageIcon(drawItem());
		JLabel labelItem = new JLabel(iconItem);

		JPanel monsterPanel = new JPanel();
		format(monsterPanel);
		monsterPanel.setOpaque(false);///////////////背景を透明にする
//		monsterPanel.setPreferredSize(new Dimension(900, 200));
		int m = Battle.mNumber();
		if (m < 1){/////////////////////////////////////////////////戦闘後の場合
			monsterPanel.setLayout(new GridLayout(0, 1, 0, 0));
			monsterPanel.add(labelItem);
		} else {////////////////////////////////////////////////////戦闘中の場合
			monsterPanel.setLayout(new GridLayout(0, m, 0, 0));
			if (Battle.mons[0].getHp() > 0) monsterPanel.add(label0);
			if (Battle.mons[1].getHp() > 0) monsterPanel.add(label1);
			if (Battle.mons[2].getHp() > 0) monsterPanel.add(label2);
			if (Battle.mons[3].getHp() > 0) monsterPanel.add(label3);
		}

		JPanel scenePanel = new JPanel();
		format(scenePanel);
		scenePanel.setPreferredSize(new Dimension(890, 200));
		SpringLayout layout = new SpringLayout();
		scenePanel.setLayout(layout);
		layout.putConstraint(SpringLayout.NORTH, backPanel, 1, SpringLayout.NORTH, scenePanel);
		layout.putConstraint(SpringLayout.NORTH, monsterPanel, 1, SpringLayout.NORTH, scenePanel);
		layout.putConstraint(SpringLayout.WEST, backPanel, 5, SpringLayout.WEST, scenePanel);
		layout.putConstraint(SpringLayout.WEST, monsterPanel, 5, SpringLayout.WEST, scenePanel);
		layout.putConstraint(SpringLayout.EAST, backPanel, 5, SpringLayout.EAST, scenePanel);
		layout.putConstraint(SpringLayout.EAST, monsterPanel, 5, SpringLayout.EAST, scenePanel);
		scenePanel.add(monsterPanel);
		scenePanel.add(backPanel);

		JTextArea monsterBlank = textAreaSet(" ", 1, 10);

		JPanel battlePanel = panelSetUD(scenePanel, stPanel);

		panelC = panelSetNCS(monsterBlank, battlePanel,monsterBlank);

	}

	private int[][] getOriginalMap() {

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

	private JPanel map2D() {

		originalMap = getOriginalMap();

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


		mapData = new MapPiece[map.length][map[0].length];
		drawMap = new JLabel[map.length][map[0].length];
		mapPanel = new JPanel();
		format(mapPanel);
//		mapPanel.setPreferredSize(new Dimension(890, 200));
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));

		for (int i = 0; i < map.length; i++) {
			JPanel row = new JPanel();
			row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
			for (int j = 0; j < map[i].length; j++) {
				mapData[i][j] = mapPiece(map[i][j]);
				String mapImage = mapData[i][j].getImage();

//				ImageIcon icon = new ImageIcon("image_map/" + mapImage + ".png");

				ImageIcon icon = createImageIcon("image_map/" + mapImage + ".png");

				drawMap[i][j] = new JLabel(icon);
				if(i == map.length / 2 && j == map[0].length /2) {
					row.add(mapCenter(drawMap[i][j]));
				} else {
					row.add(drawMap[i][j]);
				}
			}

			mapPanel.add(row);
		}

		frame.setFocusable(true);

		return mapPanel;

	}

	private ImageIcon createImageIcon(String imageUrl) {

		ClassLoader classLoader = this.getClass().getClassLoader();
		return new ImageIcon(classLoader.getResource(imageUrl));
	}

	private JPanel mapCenter(JLabel centerPiecelabel) {

		String centerImage = "勇者";

		centerIcon = createImageIcon("image_map/" + centerImage + ".png");
		centerLabel = new JLabel(centerIcon);

		JPanel panel = new JPanel();
		OverlayLayout layout=new OverlayLayout(panel);
		panel.setLayout(layout);

		panel.add(centerLabel);
		panel.add(centerPiecelabel);

		return panel;

	}


	private JPanel setBackPanel(String backURL) {

		ImageIcon iconBack = createImageIcon(backURL);
		JLabel labelBack = new JLabel(iconBack);

		backPanel = new JPanel();
		format(backPanel);
		backPanel.add(labelBack);
//		backPanel.setPreferredSize(new Dimension(900, 200));

		return backPanel;
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

		System.out.println("");//////////////////////////////////////////
		System.out.println("prologue() します");/////////////////////////
		System.out.println("");//////////////////////////////////////////

//		JOptionPane.showMessageDialog(null, "prologue() します");

		buttonName = null;

		partyStBlank();
		info(goldList(),"","");
		scene();
		menu(Command.menu());
		comment();

		change();
	}

	private void castle() {

		System.out.println("");//////////////////////////////////////////
		System.out.println("castle() します");////////////////////////////
		System.out.println("");//////////////////////////////////////////

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

		JTable pTab = new JTable();
		format(pTab);
		pTab.setModel((TableModel) setTableModel);
		pTab.setAutoCreateRowSorter(true);
		pTab.setRowHeight(fontSize * 2);
		pTab.setShowVerticalLines(false);// 縦枠
		pTab.setShowHorizontalLines(false);// 横枠
		pTab.setPreferredSize(new Dimension(w*25, pTab.getRowCount() * fontSize));

		DefaultTableCellRenderer tableCellRendererC = new DefaultTableCellRenderer();
		tableCellRendererC.setHorizontalAlignment(JLabel.LEFT);

		TableColumn[] name = new TableColumn[pTab.getColumnModel().getColumnCount()];

		for (int i = 0; i < name.length; i++) {
			name[i] = pTab.getColumnModel().getColumn(i);
			name[i].setCellRenderer(tableCellRendererC);
		}

		JLabel tName = labelSet(tableName);

		JPanel panelT = panelSetLR(b(), pTab);
		panelT.setBorder(border());

		JPanel panel = panelSetUD(tName, panelT);
		format(panel);
		panel.setBorder(border());
		panel.setPreferredSize(new Dimension(w*13, (pTab.getRowCount()+1) * fontSize * 2 - 4));

		return panel;

	}

	private void info(Object top, Object middle, Object bottom) {

		if (top.equals("")) top = null;
		if (middle.equals("")) middle = null;
		if (bottom.equals("")) bottom = null;

		JPanel infoPanel = panelSetTMB(top, middle, bottom);
		format(infoPanel);
		infoPanel.setPreferredSize(new Dimension(w*13, h*80));
		panelW = panelSetNCS(infoPanel, null, null);

	}


	private JPanel ent() {

		int bI = 1;/////////////////////////////////////ボタンの数

		JPanel panel = new JPanel();
		format(panel);
		// panel.setSize(300, 300);
		panel.setLayout(new GridLayout(bI, 0, 10, 1));
		LineBorder b = new LineBorder(getForeground(), 2, true);
		panel.setBorder(b);

		System.out.println("");//////////////////////////////////////////
		System.out.println("ent = " + ent);////////////////////////////
		System.out.println("");//////////////////////////////////////////


			button_Ent = new JButton(ent);
			format(button_Ent);
			// button_Ent.setSize(100, 50);
			button_Ent.setFocusPainted(false);
			button_Ent.addActionListener(this);
			button_Ent.addKeyListener(this);

			panel.add(button_Ent);

		return panel;
	}

	private void scene() {

		switch(mode) {
			case 1:
			case 6:
			case 7:
			case 10:
				setBackPanel(imageURL + "フィールド.png");
				eventPanel = map2D();
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

		JPanel fieldPanel = new JPanel();
		format(fieldPanel);
		fieldPanel.setPreferredSize(new Dimension(890, 200));
		SpringLayout layout = new SpringLayout();
		fieldPanel.setLayout(layout);
		layout.putConstraint(SpringLayout.NORTH, backPanel, 1, SpringLayout.NORTH, fieldPanel);
		layout.putConstraint(SpringLayout.NORTH, eventPanel, 1, SpringLayout.NORTH, fieldPanel);
		layout.putConstraint(SpringLayout.WEST, backPanel, 0, SpringLayout.WEST, fieldPanel);
		layout.putConstraint(SpringLayout.WEST, eventPanel, 0, SpringLayout.WEST, fieldPanel);
		layout.putConstraint(SpringLayout.EAST, backPanel, 0, SpringLayout.EAST, fieldPanel);
		layout.putConstraint(SpringLayout.EAST, eventPanel, 0, SpringLayout.EAST, fieldPanel);
		fieldPanel.add(eventPanel);
		fieldPanel.add(backPanel);

		JPanel scene = panelSetNCS(null, fieldPanel,null);
		scene.setPreferredSize(new Dimension(890, 300));
		scene.setForeground(Color.BLACK);
		scene.setBackground(Color.GRAY);
		scene.setBorder(border());

		panelC = panelSetNCS(pictAreaB(), scene,pictAreaB());

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

	private JPanel setEventImage(String imageFileName) {

		ImageIcon eventIcon = createImageIcon(imageFileName);
		JLabel label = new JLabel(eventIcon);

		eventPanel = new JPanel();
		format(eventPanel);
		eventPanel.setOpaque(false);///////////////背景を透明にする
		eventPanel.add(label);

		return eventPanel;
	}

	private void sceneBlank() {
		pict = textAreaSet("",7,1);

		pict.setOpaque(true);

		panelC = panelSetNCS(null, pict,null);

	}

	private Object pictAreaB() {
		pictAreaB = textAreaSet("",1,4);
		pictAreaB.setFont(new Font("Monospaced", Font.BOLD, 16));
		return pictAreaB;
	}

	private void comment() {

		sto = new Story();

		JTable st = new JTable();
		Story tableModel2 = sto;
		st.setAutoCreateRowSorter(true);
		st.setModel(tableModel2);

		DefaultTableCellRenderer tableCellRendererC = new DefaultTableCellRenderer();
		tableCellRendererC.setHorizontalAlignment(JLabel.CENTER);

		TableColumn col = st.getColumnModel().getColumn(0);
		col.setCellRenderer(tableCellRendererC);
		format(st);
		st.setRowHeight(fontSize*2);
		st.setShowVerticalLines(false);// 縦枠
		st.setShowHorizontalLines(false);// 横枠
		st.setPreferredSize(new Dimension(w*70, h*15));
		format(st.getTableHeader());
		st.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JPanel panel = new JPanel();
		format(panel);
		panel.setLayout(new BorderLayout());
		panel.setBorder(border());
		panel.add(st, BorderLayout.CENTER);

		panelS= panelSetWCE(null, panel, ent());

	}

	public void keyTyped(KeyEvent keyEvent) {

	}

	public void keyPressed(KeyEvent keyEvent) {

		int pressedKey = keyEvent.getKeyCode();

		String keyName = KeyEvent.getKeyText(pressedKey);

		 System.out.println("");//////////////////////////////////////////
		 System.out.println("buttonName = " + buttonName);/////////////////
		 System.out.println("");//////////////////////////////////////////

		 System.out.println("");//////////////////////////////////////////
		 System.out.println("pressedKey1 = " + pressedKey);/////////////////
		 System.out.println("");//////////////////////////////////////////

		 System.out.println("");//////////////////////////////////////////
		 System.out.println("keyEvent = " + keyEvent);/////////////////
		 System.out.println("");//////////////////////////////////////////

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
					 moveMap(moveX, moveY);
					break;

				case KeyEvent.VK_KP_DOWN:
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_2:
				case KeyEvent.VK_0:
					System.out.println("下が押されました");
					moveY++;
					moveMap(moveX, moveY);
					break;

				case KeyEvent.VK_KP_LEFT:
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_4:
					System.out.println("左が押されました");
					moveX--;
					moveMap(moveX, moveY);
					break;

				case KeyEvent.VK_KP_RIGHT:
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_6:
					System.out.println("右が押されました");
					moveX++;
					moveMap(moveX, moveY);
					break;

				default:
					System.out.println(keyName + "KEYが押されました(mode = 1)");
//					メニューを表示する
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

					System.out.println("");//////////////////////////////////////////
					System.out.println(keyName + "KEYが押されました");////////////
					System.out.println("");//////////////////////////////////////////

					System.out.println("");//////////////////////////////////////////
					System.out.println(ent + "ボタンをクリックします");////////////
					System.out.println("");//////////////////////////////////////////

					pushSound(); // キープッシュ音を鳴らす

//					buttonName = null;

					if(entMark.equals(ent)) {
						button_Ent.doClick();
					} else {
						menuButton[menuNum].doClick();
					}

//					buttonName = null;


					System.out.println("");//////////////////////////////////////////
					System.out.println("buttonName = " + buttonName);////////////////
					System.out.println("");//////////////////////////////////////////

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
		keyPressed(null);


////		間を開ける
//		try {
//			Thread.sleep(200);
//
//		} catch (InterruptedException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}

	}


	private void pushSound() {
//		Toolkit.getDefaultToolkit().beep(); // ビープ音を鳴らす
		sound(440f,100);
	}

	private void sound(float frequency, int soundLength) {
		try {
			new Sound(frequency, soundLength);
		} catch (LineUnavailableException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private void selectStyle() {

		if (entMark.equals(ent) == false) {

			if (menuNum < 0) menuNum += menuButton.length;
			if (menuButton.length <= menuNum) menuNum -= menuButton.length;
			for (JButton jButton : menuButton) {
				format(jButton);
			}
			menuButton[menuNum].setForeground(Color.BLACK);
			menuButton[menuNum].setBackground(Color.WHITE);
		}
	}

	private void moveMap(int moveX, int moveY) {

//		移動先が障害物でなければ移動する
		if(isBarrier(moveX, moveY) == false) {
			x += moveX;
			y += moveY;

//			はみ出し修正
			x = inRange(originalMap[0].length, x);
			y = inRange(originalMap.length, y);

			System.out.println("");////////////////////////////////////////
			System.out.println("縦" + y + "横" + x + "に移動しました");
			System.out.println("");////////////////////////////////////////

//			mapNumberが１以外でmapCenterRole()が４と９以外の場合
			if(isDanger()) {

//				移動先でイベント発動

				buttonName = Command.menu()[0];

				fieldAction(buttonName);

//				count = 0;

				actionPerformedSwitch();

				button_Ent.doClick();
			} else {

//				移動先のRoleによって各処理を行う
				doRole();
			}

			buttonName = null;

		} else {
			System.out.println("");////////////////////////////////////////
			System.out.println("そちらへは移動できません");////////////////
			System.out.println("");////////////////////////////////////////
		}
	}

	private void doRole() {

		switch(mapCenterRole()) {

			case 4:
				if(mapNumber == 2) { // ダンジョン内の場合
					setMapNumber(0); // 平原MAPへ移動
//					洞窟の位置
					x=8;
					y=8;
					musicReset();
					toNormal();

				} else {
					System.out.println("");/////////////////////////////////
					System.out.println("洞窟MAPへ移動します");//////////////
					System.out.println("");/////////////////////////////////

					setMapNumber(2); // 洞窟MAPへ移動
//					洞窟入口位置
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
					System.out.println("");/////////////////////////////////
					System.out.println("城MAPへ移動します");////////////////
					System.out.println("");/////////////////////////////////

					setMapNumber(1); // 城MAPへ移動
//					城入口位置
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

	private boolean isDanger() {

		boolean isDanger = true;

		switch(mapNumber) {
			case 1:
				isDanger = false;
				break;
			default:
		}

		switch(mapCenterRole()) {
			case 4:
			case 9:
				isDanger = false;
				break;
			default:
		}
		System.out.println("");//////////////////////////////////////////
		System.out.println("mapNumber = " + mapNumber);////////////////
		System.out.println("");//////////////////////////////////////////
		System.out.println("mapCenterRole() = " + mapCenterRole());////////////////
		System.out.println("");//////////////////////////////////////////
		System.out.println("isDanger = " + isDanger);////////////////
		System.out.println("");//////////////////////////////////////////

		return isDanger;
	}

	private int inRange(int range, int num) {
		int newNum = num;
		if (range <= num) newNum -= range;
		if (num < 0) newNum += range;
		return newNum;
	}

	private boolean isBarrier(int moveX, int moveY) {
		int[] mapCenter = centerXY(originalMap);
		int nextX = mapCenter[0] + moveX;
		int nextY = mapCenter[1] + moveY;
		boolean barrier = false;
		if (mapData[nextY][nextX].getRole() < 1 ) {
			barrier = true;
		}
		return barrier;
	}

	private int mapCenterRole() {
		map2D();
		int[] mapCenter = centerXY(originalMap);
		int nextX = mapCenter[0];
		int nextY = mapCenter[1];
		return mapData[nextY][nextX].getRole();
	}

	private int[] centerXY(int[][] baseArray) {
		int[] centerXY = {baseArray[0].length / 2, baseArray.length / 2};
		return centerXY;
	}

	public void keyReleased(KeyEvent keyEvent) {

	}

	private void clickButton(String s) {////////////////
	// for (int i = 0; i < 5; i++) {
	// if (s.equals(Money.menu[i]))
	// button[1].doClick(100);
	// }
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
		Screen.frame = frame;
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

		System.out.println("");//////////////////////////////////////////
		System.out.println("Screen.setMode(" + mode +") します");////////
		System.out.println("");//////////////////////////////////////////

		Screen.mode = mode;
	}

	public static int getMode() {
		return mode;
	}

	public static void setMenu(Object[] menu) {
		Screen.menu = menu;
	}

	public Object[] getMenu() {
		return menu;
	}

	public static void setCount(int count) {
		Screen.count = count;
	}

	public static int getCount() {
		return count;
	}

	public void setEnt(String ent) {
		Screen.ent = ent;
	}

	public static String getEnt() {
		return ent;
	}
}
