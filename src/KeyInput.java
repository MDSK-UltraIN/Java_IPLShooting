import java.awt.*;
import java.awt.event.*;

/**
*鍵盤輸入class<p>
*空白建射擊<p>
*定義移動鍵
*/
public class KeyInput extends KeyAdapter
{

	boolean keyup;
	boolean keydown;
	boolean keyleft;
	boolean keyright;
	
	/**
	 * 按鍵判斷，0-2數值分類
	 * 0:沒按 1:正在按 2:離開按鍵瞬間
	 */
	int keyshot;

	KeyInput() {
		keyup = false;
		keydown = false;
		keyleft = false;
		keyright = false;
		keyshot = 0;
	}

	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_LEFT)
		{
			keyleft = true;
		}
		if (keycode == KeyEvent.VK_RIGHT)
		{
			keyright = true;
		}
		if (keycode == KeyEvent.VK_UP)
		{
			keyup = true;
		}
		if (keycode == KeyEvent.VK_DOWN)
		{
			keydown = true;
		}
		if (keycode == KeyEvent.VK_SPACE)
		{
			//開始按
			if (keyshot == 0)
			{
				//放開瞬間
				keyshot = 2;
			}
			else
			{
				//正在按
				keyshot = 1;
			}
		}
		
		if (keycode == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
	}
	
	/**
	 * 離開按鍵處理
	 */
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_LEFT)
		{
			keyleft = false;
		}
		if (keycode == KeyEvent.VK_RIGHT)
		{
			keyright = false;
		}
		if (keycode == KeyEvent.VK_UP)
		{
			keyup = false;
		}
		if (keycode == KeyEvent.VK_DOWN)
		{
			keydown = false;
		}
		if (keycode == KeyEvent.VK_SPACE)
		{
			keyshot = 0;
		}
	}
	
	/**
	 * x軸輸入
	 * @return -1:右 0:無 1:左
	 */
	public int getXDirection()
	{
		int ret = 0;	//靜止
		if (keyright)
		{
			ret = 1;
		}
		if (keyleft)
		{
			ret = -1;
		}
		return ret;
	}
	
	/**
	 * y軸輸入
	 * @return -1:上 0:無 1:下
	 */
	public int getYDirection()
	{
		int ret = 0;	//靜止
		if (keydown)
		{
			ret = 1;
		}
		if (keyup)
		{
			ret = -1;
		}
		return ret;
	}
	
	/**
	 * 射擊按鍵(空白鍵)
	 * @return 0:沒按 1:正在按 2:離開按鍵瞬間
	 */
	public int checkShotKey()
	{
		int ret = keyshot;
		if (keyshot==2)
		{
			keyshot = 1;
		}
		return ret;
	}


}
