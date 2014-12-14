package gamedev.level;

import gamedev.entity.Tile;
import gamedev.entity.Tile.TileType;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Map {
	private static Map instance;
	public static Map getInstance() {
		if (instance == null)
			instance = new Map();
		return instance;
	}
	
	private static final String map_1[] = {
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
			  "00000000000000000",};
	
	public Point waypoints_1[] = {	// x, y
			new Point(0, 40),
			new Point(240, 40),
			new Point(240, 360),
			new Point(400, 360),
			new Point(400, 240),
			new Point(480, 240)
			};
	
	private static final String map_2[] = {
			  "00000000002222200",
			  "11100000002030200",
			  "00100000002212200",
			  "00100000000010000",
			  "00100111111110000",
			  "00100100000000000",
			  "00100100000000000",
			  "00100111111111000",
			  "00100000000001000",
			  "00100000000001000",
			  "00111111111111000",
			  "00000000000000000",};
	
	public Point waypoints_2[] = {	// x, y
		new Point(0, 40),
		new Point(80, 40),
		new Point(80, 400),
		new Point(520, 400),
		new Point(520, 280),
		new Point(200, 280),
		new Point(200, 160),
		new Point(480, 160),
		new Point(480, 80),
		};
	
	static String map_3[] = {"00011111111111000",
			  "00010000000001000",
			  "00010111111101000",
			  "00010100000101000",
			  "00010102220101000",
			  "00010102320101000",
			  "00010102120101000",
			  "00010100100101000",
			  "00010100111101000",
			  "00010100000001000",
			  "00010111111111000",
			  "00010000000000000",};

	Point waypoints_3[] = {	// x, y
		new Point(120, 440),
		new Point(120, 0),
		new Point(520, 0),
		new Point(520, 400),
		new Point(200, 400),
		new Point(200, 80),
		new Point(440, 80),
		new Point(440, 320),
		new Point(320, 320),
		new Point(320, 240)
	};
	
	public static HashMap<Integer, TileType[][]> maps = new HashMap<Integer, TileType[][]>();
	
	public static TileType[][] generateMap(int i){
		TileType[][] map = maps.get(i);
		if(maps.get(i) == null){
			switch(i){
				case 1:
					map = convertStringToGrid(map_1);
					break;
				case 2:
					map = convertStringToGrid(map_2);
					break;
				case 3:
					map = convertStringToGrid(map_3);
					break;
				default:
					return null;
						
			}
			maps.put(i, map);
		}
		
		return map;
	}
	
	private static TileType[][] convertStringToGrid(String[] grid){
		int x = grid[0].length(), y = grid.length;
		
		TileType[][] map = new TileType[x][y];
		for (y = 0; y < grid.length; y++) {
			for (x = 0; x < grid[y].length(); x++) {
				map[x][y] = Tile.interpretType(Character.getNumericValue(grid[y].charAt(x)));
			}
		}
		
		return map;
	}
	
	public List<Point> getWaypoints(int type){
		switch(type){
			case 1:
				return new ArrayList<Point>(Arrays.asList(waypoints_1));
			case 2:
				return new ArrayList<Point>(Arrays.asList(waypoints_2));
			case 3:
				return new ArrayList<Point>(Arrays.asList(waypoints_3));
		}
		
		return null;
		
	}
}
