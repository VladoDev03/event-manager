package com.example.event_manager.configuration;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class QRCodeGenerator {
    public static BufferedImage generateQRCodeImage(String data, String topText, String bottomText) throws WriterException, IOException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(data, BarcodeFormat.QR_CODE, 250,250);
        return modifiedQRCode(bitMatrix, topText, bottomText);
    }

    public static BufferedImage modifiedQRCode(BitMatrix bitMatrix, String topText, String bottomText) {
        int matrixWidth = bitMatrix.getWidth();
        int matrixHeight = bitMatrix.getHeight();

        BufferedImage image = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixHeight);
        graphics.setColor(Color.BLACK);

        //translating the bitMatrix to graphics
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixHeight; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        //getting the final width
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int topTextWidth = fontMetrics.stringWidth(topText);
        int bottomTextWidth = fontMetrics.stringWidth(bottomText);
        int finalWidth = Math.max(matrixWidth, Math.max(topTextWidth, bottomTextWidth)) + 1;
        int finalHeight = matrixHeight + fontMetrics.getHeight() + fontMetrics.getAscent() + 1;

        BufferedImage finalImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D finalGraphics = finalImage.createGraphics();
        finalGraphics.setColor(Color.WHITE);
        finalGraphics.fillRect(0, 0, finalWidth, finalHeight);
        finalGraphics.setColor(Color.BLACK);

        finalGraphics.drawImage(image, (finalWidth - matrixWidth) / 2, fontMetrics.getAscent() + 2, null);
        finalGraphics.drawString(topText, (finalWidth - topTextWidth) / 2, fontMetrics.getAscent() + 2);
        finalGraphics.drawString(bottomText, (finalWidth - bottomTextWidth) / 2, finalHeight - fontMetrics.getDescent() - 5);

        return finalImage;
    }

    public static String getBase64QRCode(String data, String topText, String bottomText) {
        try {
            BufferedImage qrCodeImage = generateQRCodeImage(data, topText, bottomText);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "png", baos);
            byte[] bytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
