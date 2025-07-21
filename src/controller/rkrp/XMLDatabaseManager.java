package controller.rkrp;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLDatabaseManager {

    private static final String FILE_NAME = "database_formulir.xml";

    public static void tambahPengguna(String usia, String berat, String tinggi, String gender, String[] jawaban) {
        try {
            File xmlFile = new File(FILE_NAME);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            Element rootElement;
            if (xmlFile.exists()) {
                doc = dBuilder.parse(xmlFile);
                rootElement = doc.getDocumentElement();
            } else {
                doc = dBuilder.newDocument();
                rootElement = doc.createElement("database");
                doc.appendChild(rootElement);
            }

            // Hitung ID
            int id = rootElement.getElementsByTagName("pengguna").getLength() + 1;

            // Buat elemen pengguna
            Element pengguna = doc.createElement("pengguna");
            rootElement.appendChild(pengguna);

            pengguna.appendChild(buatElemen(doc, "id", String.valueOf(id)));
            pengguna.appendChild(buatElemen(doc, "usia", usia));
            pengguna.appendChild(buatElemen(doc, "berat", berat));
            pengguna.appendChild(buatElemen(doc, "tinggi", tinggi));
            pengguna.appendChild(buatElemen(doc, "jeniskelamin", gender));

            // Jawaban
            Element jawabanEl = doc.createElement("jawaban");
            pengguna.appendChild(jawabanEl);
            for (int i = 0; i < jawaban.length; i++) {
                jawabanEl.appendChild(buatElemen(doc, "pertanyaan" + (i + 1), jawaban[i]));
            }

            // Tulis ke file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

            System.out.println("Data pengguna berhasil ditambahkan ke XML.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Element buatElemen(Document doc, String nama, String isi) {
        Element elem = doc.createElement(nama);
        elem.appendChild(doc.createTextNode(isi));
        return elem;
    }
}





