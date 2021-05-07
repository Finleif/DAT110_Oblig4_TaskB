package no.hvl.dat110.ac.restservice;

import com.google.gson.Gson;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccessLog {

    protected ConcurrentHashMap<Integer, AccessEntry> log;
    // atomic integer used to obtain identifiers for each access entry
    private AtomicInteger cid;

    private static final Gson gson = new Gson();

    public AccessLog() {
        this.log = new ConcurrentHashMap<>();
        cid = new AtomicInteger(0);
    }

    public int add(String message) {

        int id = cid.addAndGet(1);


        log.put(id, new AccessEntry(id, message));

        return id;
    }

    public AccessEntry get(int id) {

        return log.get(id);

    }

    public void clear() {

        log.clear();

    }

    public String toJson() {

        return gson.toJson(log.values());
    }
}
