import java.awt.*;
import java.awt.event.*;

/**
*GAME CLASS(從這邊開始)<p>
*產生畫面
*/
public class Game extends Frame
{
	/**
	 * Main Class
	 */
	public static void main(String args[])
	{
		//Frame生成
		new Game();
	}
	
	/**
	 * 建構子
	 */
	Game()
	{
		//*** Window初始化
		//Title
		super("ROGER Shooting");

		//關閉鈕處理
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
		
		setSize(500, 500);		//window大小

		//*** 戰機初始化
		MyCanvas mc = new MyCanvas();
		add(mc);				//將戰機加入畫面
		setVisible(true);		//顯示
		//遊戲資料初始化
		mc.init();
		//thread生成
		mc.initThread();
	}
}

