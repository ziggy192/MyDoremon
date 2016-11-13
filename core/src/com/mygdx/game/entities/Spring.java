package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gfx.Assets;
import com.mygdx.game.main.Handler;


public class Spring extends Entity {

    public static final int DEFAULT_WIDTH = 80;
    public static final int DEFAULT_HEIGHT = 60;

//    private int state = 0;
    private boolean appear = false;


    private Platform attachedPlatform ;
    private Rectangle bounds;

//    public static Spring generate(Handler handler) {
//        return new Spring(handler, 0, 0);
//    }

    public Spring(Handler handler, Platform attachedPlatform) {
        super(handler, attachedPlatform.getX(), attachedPlatform.getY(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.attachedPlatform = attachedPlatform;
        bounds = new Rectangle(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        appear = false;
    }

    @Override
    public void tick() {
        System.out.println("Spring appear : " +(appear ? "true" : "false"));
        updatePosition();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (appear) {
            batch.draw(getCurrentFrame(),
                    (int) this.x, (int) this.y,
                    this.width, this.height);
        }
    }

    public void updatePosition(){
        setX(attachedPlatform.getX() + attachedPlatform.getWidth() / 2 - this.getWidth() / 2);
        setY(attachedPlatform.getY() - attachedPlatform.getHeight());
        updateBounds(x,y);
    }

    private void updateBounds(float x, float y) {
        bounds = new Rectangle(x, y,getWidth(), getHeight());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Platform getAttachedPlatform() {
        return attachedPlatform;
    }

    public TextureRegion getCurrentFrame() {
        return Assets.spring[
//                this.state
                1];
    }

    public void setAttachedPlatform(Platform attachedPlatform) {
        this.attachedPlatform = attachedPlatform;
    }

//    public int getState() {
//        return state;
//    }
//
//    public void setState(int state) {
//        this.state = state;
//    }

    public boolean isAppear() {
        return appear;
    }

    public void setAppear(boolean appear) {
        this.appear = appear;
    }

    public void dispose() {

    }

}
