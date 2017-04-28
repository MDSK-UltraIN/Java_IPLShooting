import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
*描画先コンポーネントクラス(Canvasを継承)<p>
*主迴圈
*/
public class MyCanvas extends Canvas implements Runnable{
	ObjectPool objectpool;
	KeyInput keyinput;
	Image imgBuf;
	Graphics gBuf;
	Random random;
	Title title;
	Score score;
	
	/**
	 * 畫面控制<p>
	 * 0:開始畫面 1:遊戲畫面
	 */
	public int scene;
	static final int SCENE_TITLE = 0;
	static final int SCENE_GAMEMAIN = 1;
	
	
	public boolean gameover;
	int counter;
	
	
	//按住鍵
	static final int SHOT_PRESSED = 1;
	//離開按鍵瞬間
	static final int SHOT_DOWN = 2;
	int shotkey_state;


	/**
	 * 建構子
	 */
	MyCanvas()
	{
		//KeyListener安裝
		keyinput = new KeyInput();
		addKeyListener(keyinput);
		setFocusable(true);				
		random = new Random();			//亂數object
		title = new Title();
		score = new Score();
	}

	/**
	 * 初始化<p>
	 * �A�v���P�[�V�����̊J�n���A�܂��̓��X�^�[�g���ɌĂ΂�A<br>
	 * �Q�[�����Ŏg����ϐ��̏��������s���B
	 */
	public void init()
	{
		objectpool = new ObjectPool();
		Score.loadScore();
		
		//�V�[���̓^�C�g�����
		scene = SCENE_TITLE;
		gameover = false;
		//���x���̏�����
		Level.initLevel();
		//�X�R�A�̏�����
		Score.initScore();
	}
	
	/**
	 * �O������X���b�h������������B
	 */
	public void initThread()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * �`�揈��<p>
	 * repaint()�̍ۂɌĂ΂�āA
	 * �I�t�X�N���[���o�b�t�@����摜���R�s�[���\������B
	 * @param g �`���O���t�B�b�N�n���h��
	 */
	public void paint(Graphics g)
	{
		//�I�t�X�N���[���o�b�t�@�̓��e�������ɃR�s�[
		g.drawImage(imgBuf, 0, 0, this);
	}

	/**
	 * ���C�����[�v
	 */
	public void run()
	{
		//�I�t�X�N���[���o�b�t�@�쐬
		imgBuf = createImage(500, 500);
		gBuf = imgBuf.getGraphics();
		
		for(counter = 0; ; counter++)
		{
			shotkey_state = keyinput.checkShotKey();

			//�o�b�t�@���N���A
			gBuf.setColor(Color.white);
			gBuf.fillRect(0, 0, 500, 500);

			//�V�[���J�ڗp�̕ϐ��ŕ���
			switch (scene)
			{
				//�^�C�g�����
				case 0:
					title.drawTitle(gBuf);
					score.drawScore(gBuf);
					score.drawHiScore(gBuf);
					
					//�X�y�[�X�L�[�������ꂽ
					if (shotkey_state == SHOT_DOWN)
					{
						//���C����ʂɍs��
						scene = SCENE_GAMEMAIN;
					}
					break;
				
				//�Q�[���̃��C�����
				case 1:
					gameMain();
					break;
			}

			//�ĕ`���v��
			repaint();
			
			try{
				Thread.sleep(20);				//���[�v�̃E�F�C�g
			}
			catch(InterruptedException e)
			{}
		}
	}

	/**
	 * ��ʂ����������N���A���Ȃ��悤�ɂ��邽�߁A
	 * update���\�b�h���I�[�o�[���C�h����B
	 * @param g �X�V��O���t�B�b�N�n���h��
	 */
	public void update(Graphics g)
	{
		paint(g);
	}
	
	/**
	 * �Q�[����ʂ̃��C������
	 */
	void gameMain()
	{
		//�Q�[���I�[�o�[���H
		if (objectpool.isGameover())
		{
			//�Q�[���I�[�o�[������\��
			title.drawGameover(gBuf);
			if (shotkey_state == SHOT_DOWN)
			{
				//�X�R�A���n�C�X�R�A�ɓK�p�A�K�v������΃Z�[�u
				Score.compareScore();

				//�Q�[�����ď�����
				init();
			}
		}		
		
		//�Փ˂̔���
		objectpool.getColision();
		objectpool.movePlayer(keyinput);
		
		//�G�o���Ԋu�F���x���ɉ����ĒZ���Ȃ�
		if (counter % (100 - Level.getLevel() * 10) == 0)
		{
			ObjectPool.newEnemy(100 + random.nextInt(300), 0);
		}

		//500�t���[���o�߂���ƃ��x�����㏸
		if ((counter % 500) == 0)
		{
			Level.addLevel();
		}
		
		//�X�y�[�X�L�[��������Ă���A���J�E���^���R�̔{���Ȃ�e�����i���Ԋu�ɒe�����Ă�j
		if ((shotkey_state == SHOT_PRESSED)&&(counter % 3 == 0))
		{
			objectpool.shotPlayer();
		}

		//�Q�[���I�u�W�F�N�g�̈ꊇ�`�揈��
		objectpool.drawAll(gBuf);
		//�X�R�A�`��
		score.drawScore(gBuf);
		score.drawHiScore(gBuf);
		
	}
}

