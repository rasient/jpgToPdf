import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException, DocumentException {
        File rootImages = new File("./src/main/resources/images");
        File rootPdf = new File("./src/main/resources/pdf");
        String outputFile = "output.pdf";

        List<String> files = new ArrayList<String>();
        files.add("test1.jpg");
        files.add("test2.jpg");
        files.add("test3.jpg");

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(rootPdf, outputFile)));
        document.open();
        for (String f : files) {
            document.newPage();
            Image image = Image.getInstance(new File(rootImages, f).getAbsolutePath());
            image.setAbsolutePosition(0, 0);
            image.setBorderWidth(0);
            image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());

//            Centreren in x en y richting van de plaatjes
            float x = (PageSize.A4.getWidth() - image.getScaledWidth()) / 2;
            float y = (PageSize.A4.getHeight() - image.getScaledHeight()) / 2;
            image.setAbsolutePosition(x, y);
            document.add(image);
        }
        document.close();
    }
}
