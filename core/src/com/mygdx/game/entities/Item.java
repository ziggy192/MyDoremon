package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.main.Handler;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nghia on 11/12/2016.
 */

public class Item extends Entity {

    private static final int DEFAULT_WIDTH = 80;
    private static final int DEFAULT_HEIGHT = 80;


    private static final ItemAttributes[] attributes = new ItemAttributes[]{
            new ItemAttributes("food-01.png"),
            new ItemAttributes("Untitled-1-05.png"),
            new ItemAttributes("Untitled-1-15.png"),
            new ItemAttributes("Untitled-1-06.png")
    };
    public static final ArrayList<ItemAttributes> itemTypes = new ArrayList<ItemAttributes>(Arrays.asList(attributes));
    private static final int DEFAULT_TYPE = 0;

    private Rectangle bounds;
    private int type;
    private boolean alive;

    public Item(Handler handler, float x, float y, int type) {
        super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        if (type < itemTypes.size()) {
            this.type = type;
        } else {
            this.type = DEFAULT_TYPE;
        }

        bounds = new Rectangle(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        alive = false;
//        alive = true;
    }

    @Override
    public String toString() {
        return itemTypes.get(type).toString();
    }

    @Override
    public void tick() {
        updateBounds();
    }

    private void updateBounds() {
        bounds = new Rectangle(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public int getType() {
        return type;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (alive) {
            batch.draw(itemTypes.get(type).getItemTextureRegion(), x, y, width, height);
        }
    }

    public void getNewRandomType() {
        type = (int) (Math.random() * (itemTypes.size()));
    }

    public void updateWithPlatform(Platform platform) {

        setX(platform.getX() + (platform.getWidth() - DEFAULT_WIDTH) / 2);
        setY(platform.getY() - DEFAULT_HEIGHT);
        updateBounds();
    }


    public static Item createRandomItemForPlatfrom(Handler handler, Platform platform) {
        return new Item(handler, platform.getX() + (platform.getWidth() - DEFAULT_WIDTH) / 2
                , platform.getY() - DEFAULT_HEIGHT
                , (int) (Math.random() * (itemTypes.size())));
    }


}
