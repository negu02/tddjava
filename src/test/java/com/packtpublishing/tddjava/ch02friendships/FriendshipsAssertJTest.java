package com.packtpublishing.tddjava.ch02friendships;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(friendships.getFriendsList("Joe"))
                .hasSize(5)
                .containsOnly("Audrey", "Peter", "Michael", "Britney", "Paul");
    }

}

