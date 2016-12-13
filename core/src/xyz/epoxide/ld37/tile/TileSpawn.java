package xyz.epoxide.ld37.tile;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.entity.EntityBoss;
import xyz.epoxide.ld37.entity.EntityGolem;
import xyz.epoxide.ld37.entity.EntityItem;
import xyz.epoxide.ld37.entity.EntityMask;
import xyz.epoxide.ld37.entity.EntityPitManager;
import xyz.epoxide.ld37.entity.EntityRadish;
import xyz.epoxide.ld37.entity.EntitySlime;
import xyz.epoxide.ld37.entity.EntitySprout;
import xyz.epoxide.ld37.entity.EntityWisp;
import xyz.epoxide.ld37.world.World;

public class TileSpawn extends Tile{
	private int id = 0;
	public TileSpawn(int color, int id) {
		super(color);
		this.id = id;
	}
	
	@Override
	public void onLoad(World world, int x, int y){
		if (id == 0){
			world.spawnEntity(new EntitySprout(world), x+0.5f, y+1f);
		}
		else if (id == 1){
			world.spawnEntity(new EntityRadish(world), x+0.5f, y+1f);
		}
		else if (id == 2){
			world.spawnEntity(new EntityPitManager(world), x+0.5f, y+1f);
		}
		else if (id == 3){
			if (!LD37.INSTANCE.entityPlayer.hasEarthKey){
				world.spawnEntity(new EntityItem(world,0), x+0.5f, y+1f);
			}
		}
		else if (id == 4){
			if (!LD37.INSTANCE.entityPlayer.hasWaterKey){
				world.spawnEntity(new EntityItem(world,1), x+0.5f, y+1f);
			}
		}
		else if (id == 5){
			if (!LD37.INSTANCE.entityPlayer.hasAirKey){
				world.spawnEntity(new EntityItem(world,2), x+0.5f, y+1f);
			}
		}
		else if (id == 6){
			if (!LD37.INSTANCE.entityPlayer.hasFireKey){
				world.spawnEntity(new EntityItem(world,3), x+0.5f, y+1f);
			}
		}
		else if (id == 7){
			world.spawnEntity(new EntitySlime(world), x+0.5f, y+1f);
		}
		else if (id == 8){
			if (LD37.INSTANCE.entityPlayer.hasWaterKey && LD37.INSTANCE.entityPlayer.hasEarthKey && !LD37.INSTANCE.entityPlayer.hasDoubleJump){
				world.spawnEntity(new EntityItem(world,4), x+0.5f, y+1f);
			}
		}
		else if (id == 9){
			if (LD37.INSTANCE.entityPlayer.hasAirKey && !LD37.INSTANCE.entityPlayer.hasExtraHealth){
				world.spawnEntity(new EntityItem(world,5), x+0.5f, y+1f);
			}
		}
		else if (id == 10){
			if (LD37.INSTANCE.entityPlayer.hasFireKey && !LD37.INSTANCE.entityPlayer.hasExtraDamage){
				world.spawnEntity(new EntityItem(world,6), x+0.5f, y+1f);
			}
		}
		else if (id == 11){
			world.spawnEntity(new EntityMask(world), x+0.5f, y+1f);
		}
		else if (id == 12){
			world.spawnEntity(new EntityGolem(world), x+0.5f, y+1f);
		}
		else if (id == 13){
			world.spawnEntity(new EntityWisp(world), x+0.5f, y+1f);
		}
		else if (id == 14){
			world.spawnEntity(new EntityBoss(world), x+0.5f, y+1f);
		}
		world.getTileMap()[x][y] = Tile.VOID;
	}
	
}
