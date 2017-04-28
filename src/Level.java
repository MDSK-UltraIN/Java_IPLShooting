/**
*	難度LEVEL<p>
*	���x����500�t���[�����ɂP�������A�ő�łW�܂ő������邱�ƂƂ����B<br>
*	難度影響敵人產生間隔及子彈生成數
*/
public class Level
{
	static int level;
	
	/**
	 * 返回LEVEL
	 * @return LEVEL(0-8)
	 */
	public static int getLevel()
	{
		return level;
	}
	
	/**
	 * 增加1LEVEL
	 */
	public static void addLevel()
	{
		//最大LEVEL為8
		if (level < 8)
		{
			level++;
		}
		System.out.println("level:"+level);//DEBUG
	}

	/**
	 * 返回LEVEL0
	 */
	public static void initLevel()
	{
		level = 0;
	}
}

