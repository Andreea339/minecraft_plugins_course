package com.andreea.test;

import org.bukkit.entity.Player;
import org.bukkit.map.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Renderer extends MapRenderer {
    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {

        try {
            BufferedImage image = ImageIO.read(new URL("https://cuni.cz/newlayout/images/instagram.png"));
            mapCanvas.drawImage(0,0,image);

            //BufferedImage image = ImageIO.read(new URL("https://static.turbosquid.com/Preview/001323/510/3P/64.jpg"));
            //mapCanvas.drawImage(20,20, image);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //mapCanvas.drawText(50, 25, MinecraftFont.Font, "This is a treasure map");


//        mapCanvas.setPixel(10, 10, MapPalette.RED);
//        mapCanvas.setPixel(50, 0, MapPalette.LIGHT_BROWN);
//
//        for (int x = 25; x < 50; x++) {
//            for (int y = 25; y < 50; y++) {
//                mapCanvas.setPixel(x, y, MapPalette.RED);
//            }
//        }


    }
}
