package xyz.epoxide.ld37.entity;

import java.util.List;

import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.util.CombatSource.Type;
import xyz.epoxide.ld37.world.World;

public class EntityEnemy extends EntityLiving {
    public EntityEnemy(World world) {
        
        super(world);
    }
    
    @Override
    public void onUpdate(float delta){
    	super.onUpdate(delta);
    	List<Entity> entities = world.getEntities();
    	for (int i = 0; i < entities.size(); i ++){
    		Entity e = entities.get(i);
    		if (e instanceof EntityPlayer){
    			if (((EntityPlayer)e).damageTimer == 0){
    				if (e.getX() >= getX()-1.0f && e.getX() <= getX()+1.0f && e.getY() >= this.y && e.getY() <= y+2.0f){
    		    		((EntityLiving) e).attack(new CombatSource(1.0f,Type.GENERIC,this));
		    			for (int j = 0; j < 1; j ++){
		    				world.addParticle(new EntityParticle(world,1), e.getX()+(random.nextFloat()*0.5f-0.25f), e.getY()+(random.nextFloat()*0.5f-0.25f));
		    			}
		    		}
    			}
    		}
    	}
    }
}