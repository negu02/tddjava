package com.packtpublishing.tddjava.ch02friendships;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class FriendsCollection {
    private MongoCollection friends;

    @SuppressWarnings({ "deprecation", "resource" })
	public FriendsCollection() throws UnknownHostException {
        DB db = new MongoClient().getDB("friendships");
		friends = new Jongo(db).getCollection("friends");
    }

    public Person findByName(String name) {
        return friends.findOne("{_id: #}", name).as(Person.class);
    }

    public void save(Person p) {
        friends.save(p);
    }
}