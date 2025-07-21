/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.artikel;


import Artikel.DokterData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DokterXMLDatabase {

    private static final String FILE_PATH = "datadokter.xml";

    public static void simpan(List<List<DokterData>> allData) {
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("DaftarKolom", List.class);
        xstream.alias("Dokter", DokterData.class);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            xstream.toXML(allData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<List<DokterData>> baca() {
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("DaftarKolom", List.class);
        xstream.alias("Dokter", DokterData.class);

        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(FILE_PATH)) {
            return (List<List<DokterData>>) xstream.fromXML(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

