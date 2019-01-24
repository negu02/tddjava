package com.packtpublishing.tddjava.ch02friendships;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;

public class FriendshipsHamcrestTest {

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
        assertThat(friendships.getFriendsList("Alex"), empty());
    }

    @Test
    public void joeHas5Friends() {
        assertThat(friendships.getFriendsList("Joe"), hasSize(5));
    }

    @Test
    public void joeIsFriendWithEveryone() {
        assertThat(
                friendships.getFriendsList("Joe"),
                containsInAnyOrder("Audrey", "Peter", "Michael", "Britney", "Paul")
        );
    }
}

