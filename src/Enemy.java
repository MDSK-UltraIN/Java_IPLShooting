import java.awt.*;
import java.awt.event.*;

/**
*敵人Class<p>
*移動、繪畫處理尛的
*/
public class Enemy extends GameObject
{
	//生存時間(配合射擊時機)
	int counter = 0;
	//血條
	int katasa;
	//種類
	int type;
	//射擊判定
	boolean ishit = false;
	//追蹤玩家位置
	Player player;
	//開始射擊flag
	boolean startshoot;
	int shootnum;

	/**
	 * 建構子
	 * @param Player Class instance(掌握玩家位置)
	 */
	Enemy(Player iplayer)
	{
		//掌握玩家位置
		player = iplayer;
		//初始化時false
		active = false;
	}

	/**
	 * 從此初始化
	 * @param ix 生成位置(X座標)
	 * @param iy 生成位置(Y座標)
	 */
	public void activate(double ix, double iy)
	{
		x = ix;
		y = iy;
		type = (int)(Math.random()+0.5);
		active = true;			//啟動
		katasa = 10;
		counter = 0;
		boolean ishit = false;
		shootnum = Level.getLevel();
		startshoot = false;
	}

	/**
	 * 被玩家擊中時效果
	 */
	public void hit()
	{
		//血條減少
		katasa--;
		ishit = true;
		if (katasa < 0)
		{
			//敵人分類得分
			switch(type)
			{
				case 0:
					Score.addScore(10);
					break;
				case 1:
					Score.addScore(20);
					break;
				default:
			}
			//分數增加

			//爆炸特效
			ObjectPool.newParticle(x, y, 45, 2);
			ObjectPool.newParticle(x, y, 135, 2);
			ObjectPool.newParticle(x, y, 225, 2);
			ObjectPool.newParticle(x, y, 315, 2);
			active = false;
		}
	}
	
	public void move()
	{
		//敵人分類
		switch(type)
		{
			case 0:
				move_enemy0();
				break;
			case 1:
				move_enemy1();
				break;
			default:
		}
	}
	
	/**
	 * 敵人動作1
	 */
	void move_enemy0()
	{
		counter++;
		y++;
		//搖曳
		x += Math.sin(y / 20);
		
		//若生成於畫面外則不做動
		if ( (500 < y) )
		{
			active = false;
		}
		
		//間隔時間
		if ((counter%80)==0)
		{
			Bullet.FireRound(x, y);
		}
	}

	/**
	 * 敵人動作2
	 */
	void move_enemy1()
	{
		counter++;
		double p = 60;	//靜止時間
		double q = 200;	//到哪停止
		//動態表現
		y = (-q / Math.pow(p,2) * Math.pow((counter - p), 2) + q);

		//若生成於畫面外則不做動
		if ( (-30 > y) )
		{
			active = false;
		}
		
		//間隔時間
		if ((counter%60)==0)
		{
			//開始射擊
			startshoot = true;
		}
		
		//射擊flag建立後，根據LEVEL射擊
		if (startshoot)
		{
			if (((counter%5)==0)&&(shootnum>0))
			{
				Bullet.FireAim(x, y, player);
				shootnum--;
			}
		}
	}
	
	public void draw(Graphics g)
	{
		//敵人被擊中後轉為橘色
		if (ishit)
		{
			g.setColor(Color.orange);
		}else{
			switch(type)
			{
				case 0:
					g.setColor(Color.black);
					break;
				case 1:
					g.setColor(Color.blue);
					break;
				default:
			}
		}
		ishit = false;
		g.drawRect((int)x-16, (int)y-16, (int)32, (int)32);
	}
	

}

