package com.tensquare.test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class mongoDBTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient("192.168.175.128");
        MongoDatabase database = client.getDatabase("spitdb");

        MongoCollection<Document> spit = database.getCollection("spit");

        Map<String, Object> map = new HashMap<>();

        map.put("content", "草");
        map.put("userId", "9");
        map.put("visits", 123);
        map.put("publishtime", new Date());
        Document document = new Document(map);

        spit.insertOne(document);
        client.close();

    }
}

