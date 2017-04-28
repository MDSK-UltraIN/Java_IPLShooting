import java.awt.*;
import java.awt.event.*;

/**
*GameObject抽象Class<p>
*玩家、子彈、敵人等父類別
*/
public abstract class GameObject
{
	/**
	 * 建構子做動flag(false則不處理)
	*/
    public boolean active;
	/**
	 * X座標
	*/
	public double x;
	/**
	 * Y座標
	*/
	public double y;

	/**
	 * 移動
	 */
    abstract void move();

	/**
	 * 繪畫
	 */
    abstract void draw(Graphics g);
}

