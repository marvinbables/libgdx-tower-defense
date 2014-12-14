package gamedev.screen.render;

import gamedev.entity.Tower;
import gamedev.td.Config;
import gamedev.td.helper.MathHelper;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TowerRangeRenderer extends ShapeRenderer {
	private float attackRange = 0;
	private Tower towerToBuild, towerClone;

	public TowerRangeRenderer() {
		setColor(1, 1, 1, .5f);
	}
	
	public void setTowerClone(Tower towerClone) {
		this.towerClone = towerClone;
	}
	
	public void setTowerToBuild(Tower towerToBuild) {
		this.towerToBuild = towerToBuild;
	}

	public void render() {
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		begin(ShapeType.Filled);
		// TODO: Fix this code; it's ugly. What are you trying to do?
		
		if(towerToBuild != null){
			Point convertedCoordinates = MathHelper.convertToGrid(towerToBuild.getPosition().x, towerToBuild.getPosition().y);
			circle(convertedCoordinates.x + Config.tileSize / 2, convertYforShapeRenderer(convertedCoordinates.y + Config.tileSize * 3 / 2), attackRange);
		
		}
		// if (towerToBuild != null) {
		// circle(towerToBuild.getX() + Config.tileSize / 2, convertYforShapeRenderer(towerToBuild.getY() + Config.tileSize * 3 / 2), rangeRadius);
		// } else if (towerClone != null) {
		// if (towerClone.getX() != -50 && towerClone.getY() != -50) {
		// circle(towerClone.getX() + Config.tileSize / 2, convertYforShapeRenderer(towerClone.getY() + Config.tileSize * 3 / 2), rangeRadius);
		// }
		// }
		end();
	}
	
	private float convertYforShapeRenderer(float y) {
		return Gdx.graphics.getWidth() - y;
	}

	public void setAttackRange(float attackRange) {
		this.attackRange = attackRange;
	}

}
