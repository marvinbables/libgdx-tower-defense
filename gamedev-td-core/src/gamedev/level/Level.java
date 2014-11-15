package gamedev.level;

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
	
	
	public static String[] getLevel(int level) {
		switch(level) {
			case 1: return level_1;
			default: return level_1;
		}
	}
}
