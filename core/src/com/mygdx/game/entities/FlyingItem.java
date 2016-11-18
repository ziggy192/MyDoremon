package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.main.Handler;

/**
 * Created by Nghia on 11/14/2016.
 */


public class FlyingItem extends Entity {

    private static final int SPEED = 5;

    private ItemAttributes itemAttributes;
    private Entity target;

    private Vector2 velocity;
    boolean alive;


    public FlyingItem() {
        alive = false;
    }


    public FlyingItem(Handler handler, Item itemTriggered, Entity target, boolean isAlive) {
        super(handler, itemTriggered.getX(), itemTriggered.getY(), itemTriggered.getWidth(), itemTriggered.getHeight());
        this.itemAttributes = itemTriggered.getItemAttributes();
        this.target = target;
        velocity = new Vector2(0, 0);

        alive = isAlive;
    }


    @Override
    public void tick() {
        if (isAlive()) {
            updateVelocity(target);
            this.move(velocity);
            updateBounds();
            if (this.bounds.overlaps(target.getBounds())) {
                setAlive(false);
                //done colliding
            }
        }

    }


    private void updateVelocity(Entity target) {
        //set velocity dir toward target with a constant speed
        velocity.set(target.getMiddleLocationVector()).sub(this.getMiddleLocationVector()).setLength(SPEED);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isAlive()) {
            batch.draw(itemAttributes.getItemTextureRegion(), x, y, getWidth(), getHeight());
        }

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public static FlyingItem instance = new FlyingItem();

    public static FlyingItem create(Handler handler, Item itemTriggered, Entity target) {
        return new FlyingItem(handler, itemTriggered, target, true);
    }

    public static void trigger(Handler handler, Item itemTriggered, Entity target) {
        instance = new FlyingItem(handler, itemTriggered, target, true);
    }

    public void scroll(float vy) {
        if (isAlive())
            y -= vy;
    }
}
