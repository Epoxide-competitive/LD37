package xyz.epoxide.ld37;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageToBinary {


    public static void main(String[] args) {

        File backgroundImageFile = new File("background.png");
        File backgroundFile = new File("background.map");

        fileToImage(backgroundImageFile, backgroundFile);

        File foregroundImageFile = new File("foreground.png");
        File foregroundFile = new File("foreground.map");

        fileToImage(foregroundImageFile, foregroundFile);
    }

    public static void fileToImage(File imageFile, File mapFile) {
        try {
            BufferedImage img = ImageIO.read(imageFile);
            int width = img.getWidth();
            int height = img.getHeight();

            FileOutputStream fos = new FileOutputStream(mapFile);
            fos.write(toByteArray(width));
            fos.write(toByteArray(height));

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int color = img.getRGB(x, y);
                    fos.write(toByteArray(color));
                }
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] toByteArray(int value) {
        return new byte[]{
                (byte) (value >> 24),
                (byte) (value >> 16),
                (byte) (value >> 8),
                (byte) value};
    }

}

