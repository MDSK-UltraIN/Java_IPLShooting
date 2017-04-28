import java.awt.*;
import java.awt.event.*;

/**
*玩家子彈Class<p>
*/
public class MyBullet extends GameObject
{
	/**
	 * 建構子
	 */
	MyBullet()
	{
		active = false;
	}
	
	/**
	 *移動處理
	 */
	public void move()
	{
        y -= 15;
		//若戰機置頂則不發射子彈
		if ( (y < 0) )
		{
			active = false;
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.gray);
		g.drawRect((int)x-3, (int)y-10, (int)6, (int)20);
	}
	
	//衝始化
	public void activate(double ix, double iy)
	{
		x = ix;
		y = iy;
		active = true;			//子彈啟用
	}
}

