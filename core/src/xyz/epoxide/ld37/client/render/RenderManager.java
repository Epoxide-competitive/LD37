package xyz.epoxide.ld37.client.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.client.ClientRegistry;
import xyz.epoxide.ld37.entity.Entity;
import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.tile.Tile;
import xyz.epoxide.ld37.world.World;

import java.util.List;

public class RenderManager {
    private final Texture TILE_TEXTURE;
    final ShapeRenderer renderer = new ShapeRenderer();

    public RenderManager() {
        this.TILE_TEXTURE = new Texture("assets/ld37/textures/tile/tiles.png");
    }

    public void renderGame(SpriteBatch batch, float delta) {

        World world = LD37.INSTANCE.world;
        renderTiles(batch, world.getBackgroundTileMap(), delta);
        renderEntity(batch, world.getEntities(), delta);
        renderTiles(batch, world.getForegroundTileMap(), delta);
    }

    private void renderEntity(SpriteBatch batch, List<Entity> entities, float delta) {
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        batch.begin();
        for (Entity entity : entities) {
            ClientRegistry.entityRenderMap.get(entity.getClass()).render(entity, 0, 0, delta);
        }
        batch.end();
    }

    private void renderTiles(SpriteBatch batch, Tile[][] tileMap, float delta) {

        final EntityPlayer entityPlayer = LD37.INSTANCE.entityPlayer;

        final float countWidth = 16.0f;
        final float countHeight = 16.0f;
        final float scaleWidth = Gdx.graphics.getWidth() / countWidth;
        final float scaleHeight = Gdx.graphics.getHeight() / countHeight;

        final float x = entityPlayer.getX();
        final float y = entityPlayer.getY();

        final float xRem = (float) (x - Math.floor(x));
        final float yRem = (float) (y - Math.floor(y));

        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        batch.begin();
        for (int j = -1; j < countHeight + 1; j++) {
            int yy = (int) (j + Math.floor(y));
            for (int i = -1; i < countWidth + 1; i++) {
                int xx = (int) (i + Math.floor(x));
                if (xx >= 0 && xx < tileMap.length && yy >= 0 && yy < tileMap[xx].length) {
                    Tile t = tileMap[xx][yy];
                    if (t != Tile.VOID)
                        batch.draw(TILE_TEXTURE, (i - xRem) * scaleWidth, (j - yRem) * scaleHeight, scaleWidth, scaleHeight, t.u, t.v, t.u2, t.v2);
                }
            }
        }
        batch.end();
    }
}
