package xyz.epoxide.ld37.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import xyz.epoxide.ld37.LD37;
import xyz.epoxide.ld37.entity.EntityPlayer;
import xyz.epoxide.ld37.tile.Tile;
import xyz.epoxide.ld37.world.World;

public class RenderManager {
    final ShapeRenderer renderer = new ShapeRenderer();

    public void renderGame(SpriteBatch batch) {
        final EntityPlayer entityPlayer = LD37.INSTANCE.entityPlayer;

        renderTiles(batch, LD37.INSTANCE.world.getBackgroundTileMap());

        renderTiles(batch, LD37.INSTANCE.world.getForegroundTileMap());
    }

    private void renderTiles(SpriteBatch batch, Tile[][] tileMap) {
        final EntityPlayer entityPlayer = LD37.INSTANCE.entityPlayer;

        final World world = entityPlayer.world;

        final float countWidth = 16.0f;
        final float countHeight = 16.0f;
        final float scaleWidth = Gdx.graphics.getWidth() / countWidth;
        final float scaleHeight = Gdx.graphics.getHeight() / countHeight;

        final float x = entityPlayer.x;
        final float y = entityPlayer.y;

        final float xRem = (float) (x - Math.floor(x));
        final float yRem = (float) (y - Math.floor(y));

        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        batch.begin();
        for (int j = -1; j < countHeight + 1; j++) {
            int yy = (int) (j + y);

            for (int i = -1; i < countWidth + 1; i++) {
                int xx = (int) (i + x);
                if (xx >= 0 && xx < tileMap.length && yy >= 0 && yy < tileMap[xx].length) {
                    batch.draw(tileMap[xx][yy].texture, (i + xRem) * scaleWidth, (j + yRem) * scaleHeight, scaleWidth, scaleHeight);
                }
            }
        }
        batch.end();
    }
}
