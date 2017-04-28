import java.awt.*;
import java.awt.event.*;

/**
* 敵人Class<p>
* 移動、繪畫處理尛的
*/
public class Bullet extends GameObject
{
	double direction;
	double speed;
	double speedX;
	double speedY;
	
	/**
	 * 建構子
	 */
	Bullet()
	{
		//初始化時false
		active = false;
	}
	
	/**
	 * 設定迴旋動作
	 */
	public void move()
	{
		x += speedX;
		y += speedY;
		
		if ( (x < 0)||(500 < x)||(y < 0)||(500 < y) )
		{
			active = false;
		}
	}
	
	/**
	 * 繪畫處理
	 * 呼叫一次Loop
	 * @param 繪畫操縱
	 */
	public void draw(Graphics g)
	{
		g.setColor(Color.blue);
		g.drawRect((int)(x-3), (int)(y-3), (int)6, (int)6);
	}
	
	/**
	 * 從此開始初始化
	 * @param ix 生成位置(X座標)
	 * @param iy 生成位置(Y座標)
	 * @param idirection 方向(角度為單位 0-360)
	 * @param ispeed 速度(Pixel為單位)
	 */
	public void activate(double ix, double iy, double idirection, double ispeed)
	{
		x = ix;
		y = iy;
		direction = idirection;
		speed = ispeed;
		active = true;			//啟動子彈
		
		
		//為達到高速，將極座標轉換為XY速度
		double radian;
		radian = Math.toRadians(direction);	//�x�����W�A���ɕϊ�
		speedX = speed * Math.cos(radian);
		speedY = speed * Math.sin(radian);
	}

	/**
	 * 全方位子彈射擊
	 */
	public static void FireRound(double x, double y)
	{
		for (int i = 0; i < 360; i += 60 )
		{
			ObjectPool.newBullet(x, y, i, 3);
		}
	}

	//向玩家射擊
	public static void FireAim(double x, double y, Player player)
	{
		double degree = Math.toDegrees(Math.atan2(player.y - y, player.x - x));
		ObjectPool.newBullet(x, y, degree, 4);
		ObjectPool.newBullet(x, y, degree+20, 4);
		ObjectPool.newBullet(x, y, degree-20, 4);
	}
}

