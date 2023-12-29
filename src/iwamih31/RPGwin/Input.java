package iwamih31.RPGwin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.xml.crypto.dsig.keyinfo.KeyName;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.org.apache.xml.internal.security.keys.content.KeyValue;


public class Input {

	//フィールド

	public static int input() {

		String choice = null;
		try { 
			Robot rob = new Robot();////////表示ディレイ、オート半角モード用
//			rob.keyPress(KeyEvent.VK_SPACE);
//			rob.keyRelease(KeyEvent.VK_SPACE);
			System.out.print("数字を半角で入力してください⇒⇒⇒⇒⇒");
			rob.delay(200);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_END);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_END);
			rob.delay(600);
			// System.in -> キーボード入力を受け付けるための引数
			Scanner scanner = new Scanner(System.in);
			// キーボード入力を受け付け、変数choiceに代入
			// int r = scanner.nextInt();
			choice = scanner.next();
			scanner = null;
			System.out.println("[choice]選んだのは、" + choice + "です");
			if (judg(choice).equals("全")) {
				rob.keyPress(KeyEvent.VK_INPUT_METHOD_ON_OFF);
				rob.keyRelease(KeyEvent.VK_INPUT_METHOD_ON_OFF);
				System.out.println("全角モードです");
				clear();
			}			
		} catch (AWTException e1) {
			e1.getMessage();
			e1.printStackTrace();
		}
		int r = 0;
		try {
			r = Integer.parseInt(choice);
			System.out.println("[parseInt]選んだのは、" + r + "です");/////入力値確認用
		} catch (NumberFormatException e) {
//			e.printStackTrace();
			System.out.println("選んだのは、" + choice + "です");
			System.out.println("");
			System.out.println(choice + "は選択できません・・・");
			System.out.println("");
			r = 10;
			Input.input();
		}
		System.out.println("[return]選んだのは、" + r + "です");
		clear();
		return r;
	}


	public static boolean yn() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("");
		System.out.print("y か n を半角小文字で入力してください⇒⇒⇒⇒⇒");
		String inp = scanner.nextLine();
		clear();
		if (inp.equals("y")) {
			return true;
		} else {
			return false;
		}
	}

	public static String inputS() {

		String choice = null;
		try { 
			Robot rob = new Robot();////////////表示ディレイ、オート半角モード用
//			rob.keyPress(KeyEvent.VK_SPACE);
//			rob.keyRelease(KeyEvent.VK_SPACE);
			System.out.print("入力してください⇒⇒⇒⇒⇒");
			rob.delay(200);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_END);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_END);
			rob.delay(600);
			// System.in -> キーボード入力を受け付けるための引数
			Scanner scanner = new Scanner(System.in);
			// キーボード入力を受け付け、変数choiceに代入
			choice = scanner.nextLine();
			scanner = null;
			System.out.println("[choice]選んだのは、" + choice + "です");
			if (judg(choice).equals("全")) {
				rob.keyPress(KeyEvent.VK_INPUT_METHOD_ON_OFF);
				rob.keyRelease(KeyEvent.VK_INPUT_METHOD_ON_OFF);
				System.out.print("全角モードです");
			}
		} catch (AWTException e1) {
			e1.getMessage();
			e1.printStackTrace();
		} 
		clear();
		return choice;
	}

	
	public static void ent() {
		
		Robot rob;
		try {
			rob = new Robot();////////////表示ディレイ、オート半角モード用
			System.out.println("");
			System.out.print("[Enter]ボタンを押してください⇒⇒⇒⇒⇒⇒");
			rob.delay(200);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_END);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_END);
//			rob.delay(300);
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();
			System.out.println("[choice]選んだのは、" + choice + "です");
			if (judg(choice).equals("全")) {
				rob.keyPress(KeyEvent.VK_INPUT_METHOD_ON_OFF);
				rob.keyRelease(KeyEvent.VK_INPUT_METHOD_ON_OFF);
				System.out.println("全角モードです");
			}
			clear();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	static void clear() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

		Robot rob;              //////////表示ディレイ
		try {
			rob = new Robot();
			rob.delay(10);
		} catch (AWTException e1) {
			e1.printStackTrace();
			System.out.println("エラー");
		}

	}

	static String judg(String s) {
		char[] chars = s.toCharArray();
		String r = "半" ;
		for (int i = 0; i < chars.length; i++) {
			if (String.valueOf(chars[i]).getBytes().length < 2) {
				r = "半";
			} else {
				r = "全";
			}
		}
		return r;
	}


	public static int input(Object[][] menuList) {
		String choice = null;
		try { // ////////表示ディレイ、オート半角モード
			Robot rob = new Robot();
//			rob.keyPress(KeyEvent.VK_SPACE);
//			rob.keyRelease(KeyEvent.VK_SPACE);
			System.out.print("数字を半角で入力してください⇒⇒⇒⇒⇒");
			rob.delay(200);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_END);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_END);
			rob.delay(600);
			// System.in -> キーボード入力を受け付けるための引数
			Scanner scanner = new Scanner(System.in);
			// キーボード入力を受け付け、変数choiceに代入
			// int r = scanner.nextInt();
			choice = scanner.nextLine();
			System.out.println("[choice]選んだのは、" + choice + "です");
			if (judg(choice).equals("全")) {
				rob.keyPress(KeyEvent.VK_INPUT_METHOD_ON_OFF);
				rob.keyRelease(KeyEvent.VK_INPUT_METHOD_ON_OFF);
				System.out.print("全角モードです");
			}
			scanner = null;
			// 受け取った値(int choice)を数字に変換し呼び出し元へ戻す
		} catch (AWTException e1) {
			e1.printStackTrace();
			System.out.println("エラー");
		}
		int r = 0;
		try {
			r = Integer.parseInt(choice);
			System.out.println("[parseInt]選んだのは、" + r + "です");// //////////////入力値確認用
		} catch (NumberFormatException e) {
//			e.printStackTrace();
			System.out.println("[エラー]選んだのは、" + choice + "です");
			System.out.println("");
			System.out.println(choice + "は選択できません・・・");
			r = 10;
//			ent();
//			Battle.pTable();
//			System.out.println( "" );
//			for( Object[] menus : menuList ){
//				for( Object menu : menus ){
//				System.out.print(menu);
//				}
//			}
//			System.out.println( "" );
//			System.out.println( "" );
//			Input.input(menuList);
		}
		System.out.println("[return]選んだのは、" + r + "です");
		clear();
		return r;
	}
}