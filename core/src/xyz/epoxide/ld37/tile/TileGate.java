package xyz.epoxide.ld37.tile;

import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.world.World;

public class TileGate extends Tile{
	private int id = 0;
	public TileGate(int color, int id) {
		super(color);
		this.id = id;
	}
	
	@Override
	public void onLoad(World world, int x, int y){
		for (int i = 0; i < world.getEntities().size(); i ++){
			if (world.getEntities().get(i) instanceof EntityPlayer){
				EntityPlayer player = ((EntityPlayer)world.getEntities().get(i));
				if (player.hasEarthKey && this.id == 0){
					world.getTileMap()[x][y] = Tile.VOID;
				}
				else if (player.hasWaterKey && this.id == 1){
					world.getTileMap()[x][y] = Tile.VOID;
				}
				else if (player.hasAirKey && this.id == 2){
					world.getTileMap()[x][y] = Tile.VOID;
				}
				else if (player.hasFireKey && this.id == 3){
					world.getTileMap()[x][y] = Tile.VOID;
				}
			}
		}
	}
	
}
