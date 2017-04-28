import java.awt.*;
import java.awt.event.*;

/**
*�p�[�e�B�N���N���X<p>
*�i������A�G�ɒe�����������Ƃ��̌��ʁj<p>
*�ړ������A�`�揈����
*/
public class Particle extends GameObject
{
	double direction;
	double speed;
	double speedX;
	double speedY;
	int size;
	
	Particle()
	{
		active = false;
	}
	
	/**
	 * ������K�肷��B���C�����[�v�P���ɂ����Ă΂��
	 */
	public void move()
	{
		x += speedX;
		y += speedY;
		size--;
		
		if ( (x < 0)||(500 < x)||(y < 0)||(500 < y) )
		{
			active = false;
		}
		if (size < 0)
		{
			active = false;
		}
	}
	
	/**
	 * �`�揈���B
	 * �P���[�v�ň��Ă΂��B
	 * @param g �`���O���t�B�b�N�n���h��
	 */
	public void draw(Graphics g)
	{
		g.setColor(Color.gray);
		g.drawOval((int)(x-size/2), (int)(y-size/2), size, size);
	}
	
	//�����������������ōs���i�I�u�W�F�N�g���g���񂵂Ă���̂Łj
	public void activate(double ix, double iy, double idirection, double ispeed)
	{
		x = ix;
		y = iy;
		direction = idirection;
		speed = ispeed;
		active = true;			//�e�̃C���X�^���X��L���ɂ���
		size = 30;
		
		//�������̂��߁A�ɍ��W����XY���x�ɕϊ����Ă����B
		double radian;
		radian = Math.toRadians(direction);	//�x�����W�A���ɕϊ�
		speedX = speed * Math.cos(radian);
		speedY = speed * Math.sin(radian);
	}
}

