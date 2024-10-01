package entity;

import assets.player.Entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class Player extends Entity {
    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());
    GamePanel gamePanel;
    KeyHandler keyHandler;

    //constructor
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            up1 = loadImage("/assets/player/Blossom_up_1.png");
            up2 = loadImage("/assets/player/Blossom_up_2.png");
            down1 = loadImage("/assets/player/Blossom_down_1.png");
            down2 = loadImage("/assets/player/Blossom_down_2.png");
            left1 = loadImage("/assets/player/Blossom_left_1.png");
            left2 = loadImage("/assets/player/Blossom_left_2.png");
            right1 = loadImage("/assets/player/Blossom_right_1.png");
            right2 = loadImage("/assets/player/Blossom_right_2.png");

        } catch (IOException e) {
            LOGGER.severe("Error loading player images: " + e.getMessage());
        }
    }

    private BufferedImage loadImage(String path) throws IOException {
        BufferedImage image = null;
        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            if (inputStream != null) {
                image = ImageIO.read(inputStream);
            } else {
                throw new IOException("Resource not found: " + path);
            }
        }
        return image;
    }

    public void update() {
        if(keyHandler.upPressed) {
            direction = "up";
            y = y - speed;
        } else if (keyHandler.downPressed) {
            direction = "down";
            y = y + speed;
        }
        else if (keyHandler.leftPressed) {
            direction = "left";
            x = x - speed;
        } else if (keyHandler.rightPressed) {
            direction = "right";
            x = x + speed;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {
//        graphics2D.setColor(Color.white);
//        graphics2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}

