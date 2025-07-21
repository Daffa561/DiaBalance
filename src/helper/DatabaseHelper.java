package helper;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Reminder;

public class DatabaseHelper {
    private static final String DB_PATH = "Database.xml";

    // Login
    public static int login(String username, String password) {
        try {
            Document doc = loadDocument();
            NodeList userList = doc.getElementsByTagName("user");
            for (int i = 0; i < userList.getLength(); i++) {
                Element user = (Element) userList.item(i);
                String xmlUsername = user.getElementsByTagName("username").item(0).getTextContent();
                String xmlPassword = user.getElementsByTagName("password").item(0).getTextContent();
                if (username.equals(xmlUsername) && password.equals(xmlPassword)) {
                    String id = user.getElementsByTagName("id").item(0).getTextContent();
                    return Integer.parseInt(id); // Login sukses, kembalikan id user
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Login gagal
    }


    // Register user baru
    public static void register(String username, String password, String email) {
        try {
            Document doc = loadDocument();

            Node usersNode = doc.getElementsByTagName("users").item(0);

            Element newUser = doc.createElement("user");

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(String.valueOf(getNextUserId(doc))));
            newUser.appendChild(id);

            Element usernameEl = doc.createElement("username");
            usernameEl.appendChild(doc.createTextNode(username));
            newUser.appendChild(usernameEl);

            Element passwordEl = doc.createElement("password");
            passwordEl.appendChild(doc.createTextNode(password));
            newUser.appendChild(passwordEl);

            Element emailEl = doc.createElement("email");
            emailEl.appendChild(doc.createTextNode(email));
            newUser.appendChild(emailEl);

            usersNode.appendChild(newUser);

            saveDocument(doc);
            System.out.println("✅ User terdaftar!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tambah monitoring
    public static void addMonitoring(int userId, String date, int glucoseLevel) {
        try {
            Document doc = loadDocument();

            Node monitoringNode = doc.getElementsByTagName("monitoring").item(0);

            Element record = doc.createElement("record");

            Element userIdEl = doc.createElement("userId");
            userIdEl.appendChild(doc.createTextNode(String.valueOf(userId)));
            record.appendChild(userIdEl);

            Element dateEl = doc.createElement("date");
            dateEl.appendChild(doc.createTextNode(date));
            record.appendChild(dateEl);

            Element glucoseEl = doc.createElement("glucoseLevel");
            glucoseEl.appendChild(doc.createTextNode(String.valueOf(glucoseLevel)));
            record.appendChild(glucoseEl);

            monitoringNode.appendChild(record);

            saveDocument(doc);
            System.out.println("✅ Data monitoring ditambahkan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addReminder(int userId, String judul, String tanggal, String deskripsi) {
    try {
        Document doc = loadDocument();
        Node remindersNode = doc.getElementsByTagName("reminders").item(0);

        Element reminder = doc.createElement("reminder");

        Element userIdEl = doc.createElement("userId");
        userIdEl.appendChild(doc.createTextNode(String.valueOf(userId)));
        reminder.appendChild(userIdEl);

        Element judulEl = doc.createElement("judul");
        judulEl.appendChild(doc.createTextNode(judul));
        reminder.appendChild(judulEl);

        Element tanggalEl = doc.createElement("tanggal");
        tanggalEl.appendChild(doc.createTextNode(tanggal));
        reminder.appendChild(tanggalEl);

        Element deskripsiEl = doc.createElement("deskripsi");
        deskripsiEl.appendChild(doc.createTextNode(deskripsi));
        reminder.appendChild(deskripsiEl);

        remindersNode.appendChild(reminder);

        saveDocument(doc);
        System.out.println("✅ Reminder ditambahkan!");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }


    public static List<Reminder> getRemindersByUserId(int userId) {
    List<Reminder> reminders = new ArrayList<>();
    try {
        Document doc = loadDocument();
        NodeList reminderNodes = doc.getElementsByTagName("reminder");

        for (int i = 0; i < reminderNodes.getLength(); i++) {
            Element reminder = (Element) reminderNodes.item(i);
            int uid = Integer.parseInt(reminder.getElementsByTagName("userId").item(0).getTextContent());
            if (uid == userId) {
                String judul = reminder.getElementsByTagName("judul").item(0).getTextContent();
                String deskripsi = reminder.getElementsByTagName("deskripsi").item(0).getTextContent();
                LocalDate tanggal = LocalDate.parse(reminder.getElementsByTagName("tanggal").item(0).getTextContent());
                reminders.add(new Reminder(judul, deskripsi, tanggal));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return reminders;
    }

    public static void removeUserReminder(int userId, String judul, LocalDate tanggal) {
    try {
        Document doc = loadDocument();
        NodeList users = doc.getElementsByTagName("user");

        for (int i = 0; i < users.getLength(); i++) {
            Element user = (Element) users.item(i);
            if (Integer.parseInt(user.getElementsByTagName("id").item(0).getTextContent()) == userId) {
                NodeList remindersNode = user.getElementsByTagName("reminder");
                for (int j = 0; j < remindersNode.getLength(); j++) {
                    Element reminder = (Element) remindersNode.item(j);
                    String judulDb = reminder.getElementsByTagName("judul").item(0).getTextContent();
                    String tanggalDb = reminder.getElementsByTagName("tanggal").item(0).getTextContent();

                    if (judulDb.equals(judul) && tanggalDb.equals(tanggal.toString())) {
                        user.getElementsByTagName("reminders").item(0).removeChild(reminder);
                        saveDocument(doc);
                        System.out.println("✅ Reminder dihapus dari database");
                        return;
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }


    
    



    public static void addRkrp(int userId, String date, int score, String details) {
        try {
            Document doc = loadDocument();

            Node rkrpNode = doc.getElementsByTagName("rkrp").item(0);

            Element entry = doc.createElement("entry");

            Element userIdEl = doc.createElement("userId");
            userIdEl.appendChild(doc.createTextNode(String.valueOf(userId)));
            entry.appendChild(userIdEl);

            Element dateEl = doc.createElement("date");
            dateEl.appendChild(doc.createTextNode(date));
            entry.appendChild(dateEl);

            Element scoreEl = doc.createElement("score");
            scoreEl.appendChild(doc.createTextNode(String.valueOf(score)));
            entry.appendChild(scoreEl);

            Element detailsEl = doc.createElement("details");
            detailsEl.appendChild(doc.createTextNode(details));
            entry.appendChild(detailsEl);

            rkrpNode.appendChild(entry);

            saveDocument(doc);
            System.out.println("✅ Data RKR-P ditambahkan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Helper method
    private static Document loadDocument() throws Exception {
        File file = new File(DB_PATH);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(file);
    }

    private static void saveDocument(Document doc) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(DB_PATH));
        transformer.transform(source, result);
    }

    private static int getNextUserId(Document doc) {
        NodeList userList = doc.getElementsByTagName("user");
        int maxId = 0;
        for (int i = 0; i < userList.getLength(); i++) {
            Element user = (Element) userList.item(i);
            int id = Integer.parseInt(user.getElementsByTagName("id").item(0).getTextContent());
            if (id > maxId) maxId = id;
        }
        return maxId + 1;
    }
}
