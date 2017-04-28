import java.awt.*;
import java.awt.event.*;


/**
*�Q�[���I�u�W�F�N�g�̊Ǘ��N���X<p>
*�v���C���[��e�A�G�Ȃǂ̃C���X�^���X������<p>
*�I�u�W�F�N�g���m�̑��ݍ�p�i�Փˏ����Ȃǁj���ꊇ�Ǘ�����B
*/
public class ObjectPool
{

	static Bullet[] bullet;

	static Enemy[] enemy;

	static MyBullet[] mybullet;

	static Particle[] particle;

	Player player;
	
	//�萔
	static final int DIST_PLAYER_TO_BULLET = 8;
	static final int DIST_PLAYER_TO_ENEMY = 16;
	static final int DIST_ENEMY_TO_MYBULLET = 16;
	
	//�ő吔�̐ݒ�
	static final int BULLET_MAX = 100;
	static final int ENEMY_MAX = 100;
	static final int PARTICLE_MAX = 100;
	static final int MYBULLET_MAX = 5;
	

	/**
	 * ���\�q
	 */
	ObjectPool()
	{
		//�v���C���[�����
		player = new Player(250, 400, 4);
		player.active = true;
		
		//�e�̔z����m�ۂ��A�z��̗v�f���C���X�^���X�����
		bullet = new Bullet[BULLET_MAX];
		for(int i = 0; i < bullet.length; i++)
		{
				bullet[i] = new Bullet();
		}

		//�G�̔z����m�ۂ��A�z��̗v�f���C���X�^���X�����
		enemy = new Enemy[ENEMY_MAX];
		for(int i = 0; i < enemy.length; i++)
		{
				enemy[i] = new Enemy(player);
		}
		
		//�v���C���[�̒e�̔z����m�ۂ��A�z��̗v�f���C���X�^���X�����
		mybullet = new MyBullet[MYBULLET_MAX];
		for(int i = 0; i < mybullet.length; i++)
		{
				mybullet[i] = new MyBullet();
		}

		//�����̔z����m�ۂ��A�z��̗v�f���C���X�^���X�����
		particle = new Particle[PARTICLE_MAX];
		for(int i = 0; i < particle.length; i++)
		{
				particle[i] = new Particle();
		}
	}

	public void drawAll(Graphics g)
	{
        doGameObjects(g, bullet);
        doGameObjects(g, enemy);
        doGameObjects(g, mybullet);
        doGameObjects(g, particle);
		player.draw(g);
	}


    public void doGameObjects(Graphics g, GameObject[] objary)
    {
        for (int i = 0; i < objary.length; i++) {
			
            if (objary[i].active == true)
			{
                objary[i].move();
                objary[i].draw(g);				//�Ϗ�
            }
        }
    }
    
    /**
     * @param ix �����ʒu(Y���W)
     * @param iy �����ʒu(Y���W)
     * @param idirection �ړ�����
     * @param ispeed �ړ����x
     * @return �e��ID�i�󂫂��������-1�j
     */
	public static int newBullet(double ix, double iy, double idirection, double ispeed)
	{
		for (int i = 0; i < BULLET_MAX; i++)
		{
			if ((bullet[i].active) == false)
			{
				bullet[i].activate(ix, iy, idirection, ispeed);
				return i;
			}
		}
		return -1;		//������Ȃ�����
	}


	public static int newEnemy(double ix, double iy)
	{
		for (int i = 0; i < ENEMY_MAX; i++)
		{
			if ((enemy[i].active) == false)
			{
				enemy[i].activate(ix, iy);
				return i;
			}
		}
		return -1;		//������Ȃ�����
	}

    /**
     * �v���C���[�e�̐����E�������i���ۂ͔z��̃C���X�^���X���g���񂷁j
     * @param ix ������x���W
     * @param iy ������y���W
	 * @return �v���C���[�e��ID�i�󂫂��������-1�j
     */
	public static int newMyBullets(double ix, double iy)
	{
		for (int i = 0; i < MYBULLET_MAX; i++)
		{
			if ((mybullet[i].active) == false)
			{
				mybullet[i].activate(ix, iy);
				return i;
			}
		}
		return -1;		//������Ȃ�����
	}

    /**
     * �����̐����E�������i���ۂ͔z��̃C���X�^���X���g���񂷁j
     * @param ix ������x���W
     * @param iy ������y���W
     * @param idirection ����������
     * @param ispeed ���������x
	 * @return ������ID�i�󂫂��������-1�j
     */
	public static int newParticle(double ix, double iy, double idirection, double ispeed)
	{
		for (int i = 0; i < PARTICLE_MAX; i++)
		{
			if ((particle[i].active) == false)
			{
				particle[i].activate(ix, iy, idirection, ispeed);
				return i;
			}
		}
		return -1;		//������Ȃ�����
	}

	/**
	 * �v���C���[���e������
	 */
	public void shotPlayer()
	{
		//�v���[���[�����̎������e���łĂ�
		if (player.active)
		{
			newMyBullets(player.x, player.y);
		}
	}

	/**
	 * �v���C���[���ړ����鏈��
	 * @param keyinput �L�[���̓N���X�̃C���X�^���X�B
	 */
	public void movePlayer(KeyInput keyinput)
	{
		player.move(keyinput.getXDirection(), keyinput.getYDirection());
	}
	
	/**
	 * �Q�_�Ԃ̋�����Ԃ�
	 * @param ga �Q�[���I�u�W�F�N�g
	 * @param gb ��r��Q�[���I�u�W�F�N�g
	 * @return ����
	 */
	public double getDistance(GameObject ga, GameObject gb)
	{
		//�O�����̒藝
		double Xdiff = Math.abs(ga.x - gb.x);
		double Ydiff = Math.abs(ga.y - gb.y);
		return Math.sqrt(Math.pow(Xdiff,2) + Math.pow(Ydiff,2));
	}

	/**
	 * �Փ˔���
	 */
	public void getColision()
	{
		//�evs�v���C���[�̏Փ�
        for (int i = 0; i < bullet.length; i++) {
			if ((bullet[i].active)&&(player.active))
			{
				//�����蔻��
				if (getDistance(player, bullet[i]) < DIST_PLAYER_TO_BULLET)
				{
					//�v���C���[���Łi�����Ȃ����邾���j
					player.active = false;

					//�����𐶐�
					for (int j = 0; j < 360; j += 20)
					{
						newParticle(player.x, player.y, j, 2);
					}

					//�e����
					bullet[i].active = false;
				}
			}
        }

		//�v���C���[�̒evs�G�̏Փ�
        for (int i = 0; i < enemy.length; i++) {
			if (enemy[i].active == true)
			{
				for (int j = 0; j < mybullet.length; j++)
				{
					if (mybullet[j].active == true)
					{
						//�����蔻��
						if (getDistance(enemy[i], mybullet[j]) < DIST_ENEMY_TO_MYBULLET)
						{
							newParticle(mybullet[j].x, mybullet[j].y, 270, 2);
							//�G�̗̑͂����炷
							enemy[i].hit();
							//�e����
							mybullet[j].active = false;
						}
					}
				}
			}
        }

		//�Gvs�v���C���[�̏Փ�
        for (int i = 0; i < enemy.length; i++) {
			if ((enemy[i].active)&&(player.active))
			{
				//�����蔻��
				if (getDistance(player, enemy[i]) < DIST_PLAYER_TO_ENEMY)
				{
					//�v���C���[���Łi�����Ȃ����邾���j
					player.active = false;

					//�����𐶐�
					for (int j = 0; j < 360; j += 20)
					{
						newParticle(player.x, player.y, j, 2);
					}
					//�G�͏��ł��Ȃ�
				}
			}
        }

	}
	
	/**
	 * �Q�[���I�[�o�[���ǂ�����Ԃ�
	 * @return �Q�[���I�[�o�[�Ȃ�true
	 */
	public boolean isGameover()
	{
		return !player.active;
	}
	
	
	
}
