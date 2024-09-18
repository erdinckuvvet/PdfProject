/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erdinc_kuvvet.pdfproject;

/**
 *
 * @author erdinc.kuvvet
 */
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoToPdf {


    public static void createPdf(String dest, List<String> imagePaths, String brand, List<String> prices, List<String> sizes, int x_location, int y_location, int label_width) throws IOException {
        // PDF belgesini oluşturma
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        for (int i = 0; i < imagePaths.size(); i++) {
            // Sayfa ekleme
            if (i > 0) {
                pdf.addNewPage();
            }

            // Fotoğraf ekleme
            ImageData imageData = ImageDataFactory.create(imagePaths.get(i));
            Image image = new Image(imageData);
            image.setAutoScale(false);
            document.add(image);

            // Etiketleri ekleme
            Paragraph labels = new Paragraph()
                    .add(new Text("Brand: " + brand + "\n"))
                    .add(new Text("Price : " + prices.get(i) + "\n"))
                    .add(new Text("Size  : " + sizes.get(i) + "\n"));

            labels.setFixedPosition(i + 1, x_location, y_location, label_width); // Sağ üst köşeye yerleştirme (sayfa numarası, X, Y, genişlik)
            document.add(labels);
        }
        
        document.close();
    }
}
