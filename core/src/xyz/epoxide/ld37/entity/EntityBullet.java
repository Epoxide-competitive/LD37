package xyz.epoxide.ld37.entity;

import java.util.List;

import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.input.keybind.KeyBind;
import xyz.epoxide.ld37.util.CombatSource;
import xyz.epoxide.ld37.util.CombatSource.Type;
import xyz.epoxide.ld37.world.World;

public class EntityBullet extends Entity {
    public EntityPlayer player = null;
    private boolean damageDealt = false;
    private int internalCount = 0;
    public EntityBullet(World world, float velX) {
        
        super(world);
        this.noClip = false;
        this.hasGravity = false;
        this.animFrames = 1;
        this.cycleSpeed = 0.025f;
        this.setMotionX(velX);
    }
    
    @Override
    public void onUpdate (float delta) {
    	super.onUpdate(delta);
    	this.setMotionX(30.0f*Math.signum(getMotionX()));
    	if (this.getX() < 0 || this.getX() > world.getMapWidth() || this.getY() < 0 || this.getY() > world.getMapHeight()){
    		setDead(CombatSource.STATE_BASED);
    	}
    	List<Entity> entities = world.getEntities();
    	if (this.isCollided){
			for (int j = 0; j < 1; j ++){
				world.addParticle(new EntityParticle(world,1), getX(), getY());
			}
			setDead(CombatSource.STATE_BASED);
    	}
    	for (int i = 0; i < entities.size(); i ++){
    		Entity e = entities.get(i);
    		if (e instanceof EntityLiving && !damageDealt){
	    		if (e.getX() >= x && e.getX() <= x+2.0f && e.getY() >= this.y && e.getY() <= y+2.0f){
	    			((EntityLiving) e).attack(new CombatSource(1.0f,Type.GENERIC,this.player));
	    			damageDealt = true;
	    			for (int j = 0; j < 1; j ++){
	    				world.addParticle(new EntityParticle(world,1), getX(), getY());
	    			}
	    			setDead(CombatSource.STATE_BASED);
	    		}
    		}
    	}
    }
}
