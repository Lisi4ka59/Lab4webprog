package com.lisi4ka.lab4webdb.utils;

import com.lisi4ka.lab4webdb.db.Dot;
import com.lisi4ka.lab4webdb.db.DotRepository;
import com.lisi4ka.lab4webdb.db.RegUserRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.lisi4ka.lab4webdb.utils.Check.tokenMap;
import static com.lisi4ka.lab4webdb.utils.DateNow.dateTimeNow;
import static com.lisi4ka.lab4webdb.utils.ImageToByteArray.imageToByteArray;

public abstract class GraphicsService {
    public static byte[] drawGraphic(String inputX, String inputY, String inputR, String inputRandom, String token, DotRepository dotRepository, RegUserRepository regUserRepository) throws IOException {
        long scriptStartTime = System.nanoTime();

        long flightTime;

        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_BYTE_INDEXED);
        float x;
        float y;
        float radius;
        float random;
        Long userId;
        try {
            long parsedToken = Long.parseLong(token);
            String user = tokenMap.inverse().get(parsedToken);
            userId = regUserRepository.findByUsername(user).get().getId();
            x = Float.parseFloat(inputX);
            y = Float.parseFloat(inputY);
            radius = Float.parseFloat(inputR);
            random = Float.parseFloat(inputRandom);
        } catch (Exception ex) {
            ex.printStackTrace();
            File file = new File("classes/static/cover.jpg");
            image = ImageIO.read(file);
            return imageToByteArray(image, "jpeg");
        }

        int roundX = Math.round(x - 100);
        int roundY = -Math.round(y - 100);
        float X = (x - 100) * radius;
        float Y = -(y - 100) * radius;
        float R = radius * 74;

        int gr;
        int re;
        int bl;
        String[] result = DotValidator.result(X, Y, R, random);
        re = Integer.parseInt(result[0]);
        gr = Integer.parseInt(result[1]);
        bl = Integer.parseInt(result[2]);


        Graphics2D graphics = image.createGraphics();
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, 200, 200);
        graphics.setColor(new Color(51, 153, 255));
        graphics.fillRect(100, 27, 74, 73);
        graphics.fillArc(26, 26, 148, 148, 270, 90);
        graphics.fillPolygon(new int[]{63, 100, 100}, new int[]{100, 100, 136}, 3);
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(8, 99, 184, 2);
        graphics.fillRect(99, 8, 2, 184);


        graphics.fillRect(27, 96, 2, 8);
        graphics.fillRect(63, 96, 2, 8);
        graphics.fillRect(136, 96, 2, 8);
        graphics.fillRect(172, 96, 2, 8);
        graphics.fillRect(96, 27, 8, 2);
        graphics.fillRect(96, 63, 8, 2);
        graphics.fillRect(96, 136, 8, 2);
        graphics.fillRect(96, 172, 8, 2);


        graphics.fillPolygon(new int[]{188, 188, 196}, new int[]{96, 104, 100}, 3);
        graphics.fillPolygon(new int[]{96, 104, 100}, new int[]{12, 12, 4}, 3);


        graphics.drawString(String.valueOf(radius), 164, 92);
        graphics.drawString(String.valueOf(radius / 2), 164 - 36, 92);
        graphics.drawString(String.valueOf(-radius / 2), 164 - 109, 92);
        graphics.drawString(String.valueOf(-radius), 164 - 145, 92);


        graphics.drawString(String.valueOf(radius), 106, 32);
        graphics.drawString(String.valueOf(radius / 2), 106, 32 + 36);
        graphics.drawString(String.valueOf(-radius / 2), 106, 32 + 36 * 3 + 1);
        graphics.drawString(String.valueOf(-radius), 106, 32 + 36 * 3 + 1 + 36);

        graphics.drawString("X", 185, 117);
        graphics.drawString("Y", 107, 15);


        if (x != 1000) {
            graphics.setColor(new Color(re, gr, bl));
            graphics.fillOval(roundX + 100 - 3, -(roundY - 100 + 3), 5, 5);
        }


        try {

            Iterable<Dot> dots = dotRepository.findAllByUserId(userId);
            for (Dot dot:dots){
                graphics.setColor(new Color(dot.getRed(), dot.getGreen(), dot.getBlue()));
                graphics.fillOval(Math.round(((dot.getX() * 74 / radius) + 100)) - 2, -(-Math.round(-((dot.getY() * 74 / radius) - 100)) + 2), 5, 5);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ошибка при подключении к БД!");
        }

        if (x != 1000) {
            try {
                Dot dot = new Dot();
                dot.setX((x - 100) / 74 * radius);
                dot.setY((100 - y) / 74 * radius);
                dot.setR(radius);
                dot.setRed(re);
                dot.setGreen(gr);
                dot.setBlue(bl);
                dot.setResult(result[3]);
                dot.setDate(dateTimeNow());
                flightTime = System.nanoTime() - scriptStartTime;
                dot.setFlightTime(flightTime);
                dot.setUserId(userId);
                dotRepository.save(dot);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        graphics.dispose();
        return imageToByteArray(image, "jpeg");
    }
}
