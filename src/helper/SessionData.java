package helper;

import model.Reminder;
import java.util.LinkedList;
import java.util.Queue;

public class SessionData {
    public static int currentUserId;
    public static String currentUsername;
    public static Queue<Reminder> reminderQueue = new LinkedList<>();
}

