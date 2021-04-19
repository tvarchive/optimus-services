/*
 * Copyright (c) 2017.  TestVagrant Technologies
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.testvagrant.services.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.AlphaInterpolation;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImageResizer {

    private File imageToResize;
    private byte[] imageBytes;
    private int widthPx=303;
    private int heightPx=550;
    private double scalingFactor = 0.4d;
    private static List<String> imagesList = new ArrayList<>();

    public ImageResizer(File imageToResize) {
        this.imageToResize = imageToResize;
    }

    public ImageResizer(File imageToResize, int widthPx, int heightPx) {
        this.imageToResize = imageToResize;
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(imageToResize);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            this.imageBytes = baos.toByteArray();
        } catch (IOException e) {
            this.imageBytes = new byte[]{0};
        }
        this.widthPx = widthPx;
        this.heightPx = heightPx;
    }

    public ImageResizer(byte[] imageBytes, int widthPx, int heightPx) {
        this.imageBytes = imageBytes;
        this.widthPx = widthPx;
        this.heightPx = heightPx;
    }

    public ImageResizer(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public byte[] resizeImage() throws IOException {
        InputStream is = new ByteArrayInputStream(imageBytes);
        BufferedImage originalImage = ImageIO.read(is);
        BufferedImage resizeImage = Thumbnails.of(originalImage).scale(scalingFactor).scalingMode(ScalingMode.BICUBIC).alphaInterpolation(AlphaInterpolation.QUALITY).asBufferedImage();
        return getImageBytes(resizeImage);
    }


    public BufferedImage resizeImage(BufferedImage originalImage, int type){
        BufferedImage resizedImage = new BufferedImage(widthPx, heightPx, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, widthPx, heightPx, null);
        g.dispose();
        return resizedImage;
    }


    public static byte[] getImageBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( image, "png", baos );
        baos.flush();
        byte[] imageInBytes = baos.toByteArray();
        baos.close();
        return imageInBytes;
    }
}
