package no.hvl.dat110.ac.restservice;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccessLog {

    protected ConcurrentHashMap<Integer, AccessEntry> log;
    // atomic integer used to obtain identifiers for each access entry
    private AtomicInteger cid;

    public AccessLog() {
        this.log = new ConcurrentHashMap<Integer, AccessEntry>();
        cid = new AtomicInteger(0);
    }

    // TODO: add an access entry to the log for the provided message and return assigned id
    public int add(String message) {

        int id = 0;

        return id;
    }

    // TODO: retrieve a specific access entry from the log
    public AccessEntry get(int id) {

        return null;

    }

    // TODO: clear the access entry log
    public void clear() {

    }

    // TODO: return JSON representation of the access log
    public String toJson() {

        String json = null;

        return json;
    }
}
