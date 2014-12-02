package gamedev.level;

import java.awt.Point;


public class Level {
	public static String level_1[] = {"00000000000000000",
									  "11111110000000000",
									  "00000010000000000",
									  "00000010000000000",
									  "00000010000022220",
									  "00000010000020020",
									  "00000010001110320",
									  "00000010001020020",
									  "00000010001022220",
									  "00000011111000000",
									  "00000000000000000",
									  "00000000000000000",};
	public static Point level_1_waypoints[] = {	// x, y
												new Point(0, 40),
												new Point(240, 40),
												new Point(240, 360),
												new Point(400, 360),
												new Point(400, 240),
												new Point(480, 240)
												};
	
	
	public static String[] getLevel(int level) {
		switch(level) {
			case 1: return level_1;
			default: return level_1;
		}
	}
	
	public static int[][] level_1_enemies = {
		// instances, enemy type
		{1, 1}, {1,1}
	};
	
	
	
}
