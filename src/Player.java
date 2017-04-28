import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
/**
*玩家Class<p>
*移動、繪畫處理尛的
*/
public class Player extends GameObject
{
    double speed;
    Image image;
	/**
	 * Constructor
	 * @param ix �������x���W
	 * @param iy �������y���W
	 * @param ispeed 移動速度
	 */
    Player(double ix, double iy, double ispeed)
    {
        x = ix;
		y = iy;
		speed = ispeed;
		active = false;
	}

    /**
     * �_�~�[�֐�<p>
     * (�������X�[�p�[�N���X�̃A�u�X�g���N�g�E���\�b�h�̒�`�ƈقȂ邽��)
     */
    public void move()
    {
    }
	
	/**
	 * �ړ�����
	 * @param mx x�����̓���(-1 ... +1)
	 * @param my y�����̓���(-1 ... +1)
	 */
	public void move(int mx, int my)
	{
		//Canvas�̊O�ɂ͈ړ��ł��Ȃ��悤�ɂ���
		double postX = x + mx * speed;
		double postY = y + my * speed;
		
		if ((0 < postX)&&(postX < 500))
		{
			x = postX;
		}
		if ((0 < postY)&&(postY < 480))
		{
			y = postY;
		}
	}

	public void draw(Graphics g)
	{
		if (active)
		{
			try{
				image = ImageIO.read(new File("images/canvas.jpg"));
			}
			catch(Exception ex){
				System.out.print("No Canvas's Image!!");
			}
			//戰機圖像
			g.drawImage(image, (int)(x-20), (int)(y), null);
			
			//g.setColor(Color.red);
			/*g.drawLine((int)(x), (int)(y-14), (int)(x-10), (int)(y+7));
			g.drawLine((int)(x), (int)(y-14), (int)(x+10), (int)(y+7));
			g.drawLine((int)(x-10), (int)(y+7), (int)(x+10), (int)(y+7));*/
		}
	}
	
	
}
