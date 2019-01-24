package com.packtpublishing.tddjava.ch02friendships;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FriendshipsAssertJTest {

    Friendships friendships;

    @Before
    public void before() {
        friendships = new Friendships();
        friendships.makeFriends("Joe", "Audrey");
        friendships.makeFriends("Joe", "Peter");
        friendships.makeFriends("Joe", "Michael");
        friendships.makeFriends("Joe", "Britney");
        friendships.makeFriends("Joe", "Paul");
    }

    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void michaelHasOneFriend(){
    	assertThat(friendships.getFriendsList("Michael").contains("Joe") &&
    			friendships.getFriendsList("Michael").size() == 1);
    }
    
    @Test
    public void paulIsNotBrineyFriend(){
    	assertThat(!friendships.getFriendsList("Paul").contains("Britney"));
    }
    
    @Test
    public void joeHas5Friends() {
    	Assert.assertSame("Joe has 5 friends.", 5, friendships.getFriendsList("Joe").size());
    }
    
    @Test
    public void joeIsEveryOnesFriend(){
    	String personas[] = new String[]{"Audrey","Peter","Michael","Britney", "Paul"};
    	List<Object> joesFriends = Arrays.asList(personas);
    	Assert.assertTrue(friendships.getFriendsList("Joe")
    			.containsAll(joesFriends));
    }

}

