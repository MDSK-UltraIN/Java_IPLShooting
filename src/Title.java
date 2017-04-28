import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;
import java.io.*;
import javax.imageio.*;
/**
*�^�C�g���N���X<p>
*�^�C�g����ʕ`��<p>
*�Q�[���I�[�o�[��ʕ\��
*/
public class Title
{
	//�A�j���[�V�����p�J�E���^
	Image image;
	int count;
	Font titleFont;
	Font infoFont;
	
	/**
	 * �R���X�g���N�^<p>
	 * �^�C�g���p�ɁA�t�H���g�N���X�̃C���X�^���X�𐶐�����
	 */
	Title()
	{
		count = 0;
		titleFont = new Font("sansserif", Font.BOLD, 30);
		infoFont = new Font("sansserif", Font.BOLD, 11);
	}
	
	/**
	 * �^�C�g����ʂ̕`�揈���B
	 * �P���[�v�ň��Ă΂��B
	 * @param g �`���O���t�B�b�N�n���h��
	 */
	public void drawTitle(Graphics g)
	{
		g.setColor(Color.black);
		count++;
		g.setFont(titleFont);
		g.drawString("ROGER S h o o t i n g",120,150);

		//�_�ł�����
		if (count%2 == 0)
		{
			g.setFont(infoFont);
			g.drawString("hit SPACE key",200,350);
		}
	}

	/**
	 * �Q�[���I�[�o�[�̕`�揈���B
	 * �P���[�v�ň��Ă΂��B
	 * @param g �`���O���t�B�b�N�n���h��
	 */
	public void drawGameover(Graphics g)
	{
		g.setColor(Color.black);
		count++;
		g.setFont(titleFont);
		g.drawString("GAMEOVER",150,150);
		
		try{
			image = ImageIO.read(new File("images/GameOver.jpg"));
		}
		catch(Exception ex){
			System.out.print("No GameOver's Image!!");
		}
		//戰機圖像
		g.drawImage(image, 123, 160, null);
	}
	
}

