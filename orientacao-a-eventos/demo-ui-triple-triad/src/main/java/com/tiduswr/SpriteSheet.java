package com.tiduswr;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
    private BufferedImage sheet;
    private BufferedImage[] sprites;
    private int spriteWidth;
    private int spriteHeight;
    private int rows, columns;

    public SpriteSheet(String path, int spriteWidth, int spriteHeight) throws IOException {
        sheet = ImageIO.read(getClass().getResourceAsStream(path));
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        columns = sheet.getWidth() / spriteWidth;
        rows = sheet.getHeight() / spriteHeight;
        int numSprites = columns * rows;
        sprites = new BufferedImage[numSprites];
    }

    public void loadSprites() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                sprites[y * columns + x] = sheet.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
            }
        }
    }

    public BufferedImage getSprite(int index) {
        if (index >= 0 && index < sprites.length) {
            return sprites[index];
        } else {
            return null;
        }
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }
}