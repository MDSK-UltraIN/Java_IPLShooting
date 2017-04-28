import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
*�X�R�A�N���X<p>
*���_�̐���A�\��
*/
public class Score
{
	static int myscore;
	static int hiscore;
	Font scoreFont;
	
	/**
	 * �R���X�g���N�^
	 */
	Score()
	{
		scoreFont = new Font("sansserif", Font.BOLD, 10);
		myscore = 0;
	}
	
	/**
	 * �X�R�A�̕`��
	 * @param g �`���O���t�B�b�N�n���h��
	 */
	public void drawScore(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(scoreFont);
		g.drawString("score:"+myscore, 30, 30);
	}
	
	/**
	 * �n�C�X�R�A�̕`��
	 * @param g �`���O���t�B�b�N�n���h��
	 */
	public void drawHiScore(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(scoreFont);
		g.drawString("hiscore:"+hiscore, 420, 30);
	}
	
	
	/**
	 * �X�R�A�ɒǉ�
	 * @param gain �ǉ����链�_
	 */
	public static void addScore(int gain)
	{
		myscore += gain;
	}
	
	/**
	 * �n�C�X�R�A�X�V����<p>
	 * �n�C�X�R�A���z���Ă�����A�X�R�A���O���t�@�C���ɕۑ�����B
	 */
	public static void compareScore()
	{
		//�n�C�X�R�A���X�V
		if (myscore > hiscore)
		{
			hiscore = myscore;
			saveScore();
		}
	}


	/**
	 * �X�R�A�ۑ�����<p>
	 * data.dat�Ƀo�C�i���`���ŕۑ�����B
	 */
	static void saveScore()
	{
//            DataOutputStream dout = new DataOutputStream(new FileOutputStream("data.dat"));
//            dout.writeInt(hiscore);
//            dout.close();
	}

	/**
	 * �X�R�A�ǂݍ��ݏ���<p>
	 * data.dat����o�C�i���`���œǂݍ��ށB
	 */
	static void loadScore()
	{
//            DataInputStream din = new DataInputStream(new FileInputStream("data.dat"));
//            hiscore = din.readInt();
//            din.close();
	}




	//�X�R�A�̏�����
	public static void initScore()
	{
		myscore = 0;
	}
}

