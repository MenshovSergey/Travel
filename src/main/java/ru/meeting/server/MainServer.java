package main.java.ru.meeting.server;


import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sergej on 16.11.15.
 */
public class MainServer {
    private static ConcurrentHashMap<Long, Long> sessionId = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Long, Long> userIdMap = new ConcurrentHashMap<>();

    public static long getSessionId(long userId) {
        if (userIdMap.containsKey(userId)) {
            return userIdMap.get(userId);
        } else {
            Random random = new Random();
            long curId = random.nextLong();
            while (sessionId.containsKey(curId)) {
                curId = random.nextLong();
            }
            sessionId.put(curId, userId);
            return curId;
        }

    }



}
