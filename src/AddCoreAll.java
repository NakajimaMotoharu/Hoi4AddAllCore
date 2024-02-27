import java.awt.*;
import java.awt.event.KeyEvent;

public class AddCoreAll {
	// 中核州の最大値、最小値の定義
	//public static final int MAX = 934, MIN = 1;
	public static final int MAX = 10, MIN = 1;
	// 入力に使用するbot
	private final Robot robot;

	// コンストラクタ
	public AddCoreAll(){
		// botの初期化
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}

	// 実行用メソッド
	public void run(){
		// 実行前カウントダウン
		for (int i = 5; i > 0; i = i - 1){
			System.out.println(String.format("%d秒後に実行します。", i));
			pause(1000);
		}
		System.out.println("実行します。");

		// 実行
		for (int i = MIN; i <= MAX; i = i + 1){
			outStr(i);
		}

		// 実行後メッセージ
		System.out.println("実行完了。");
	}

	// 出力用メソッド
	private void outStr(int n){
		// add_core の出力
		outAddCore();

		// nを1文字ずつに区切る
		int size = String.format("%d", n).length();
		int[] list = new int[size];
		for (int i = 0; i < size; i = i + 1){
			list[i] = n % 10;
			n = n / 10;
		}

		// 1文字ずつ出力
		for (int i = size - 1; i >= 0; i = i - 1){
			outChar(list[i]);
		}

		// 改行出力
		outEnter();
	}

	// add_core出力用メソッド
	private void outAddCore(){
		outKey(KeyEvent.VK_A);
		outKey(KeyEvent.VK_D);
		outKey(KeyEvent.VK_D);
		outUS();
		outKey(KeyEvent.VK_C);
		outKey(KeyEvent.VK_O);
		outKey(KeyEvent.VK_R);
		outKey(KeyEvent.VK_E);
		outKey(KeyEvent.VK_SPACE);
	}

	// 文字出力用メソッド
	private void outChar(int c){
		// kをキーコードで初期化
		int k = switch (c) {
			case 0 -> KeyEvent.VK_0;
			case 1 -> KeyEvent.VK_1;
			case 2 -> KeyEvent.VK_2;
			case 3 -> KeyEvent.VK_3;
			case 4 -> KeyEvent.VK_4;
			case 5 -> KeyEvent.VK_5;
			case 6 -> KeyEvent.VK_6;
			case 7 -> KeyEvent.VK_7;
			case 8 -> KeyEvent.VK_8;
			case 9 -> KeyEvent.VK_9;
			default -> -1;
		};

		// cが数字でなければreturn
		if (k == -1){
			return;
		}

		// そのキーを入力
		outKey(k);
	}

	// 改行出力用メソッド
	private void outEnter(){
		outKey(KeyEvent.VK_ENTER);
	}

	// キータイプ・リリースメソッド
	private void outKey(int n){
		robot.keyPress(n);
		pause(10);
		robot.keyRelease(n);
		pause(10);
	}

	// アンダースコア出力用メソッド(クリップボードに_を登録させておく)
	private void outUS(){
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		pause(10);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		pause(10);
	}

	// sleepメソッドの箱
	private void pause(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
