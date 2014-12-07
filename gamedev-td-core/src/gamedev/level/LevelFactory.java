package gamedev.level;

import java.awt.Point;

public class LevelFactory {
	
	public static Level MakeLevel(int type){
		
		switch(type){
			case 1:
				String[] grid = {
						"00000000000000000",
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
						"00000000000000000",
						};

				Point[] waypoints = {
						new Point(0, 40),
						new Point(240, 40),
						new Point(240, 360),
						new Point(400, 360),
						new Point(400, 240),
						new Point(480, 240)
						};
				
				int[][] enemies = {
						{10, 1}, {1,1}
					};
				
				return new Level(grid, waypoints, enemies);
			default:
				return null;
		
		}
		
	}

}
