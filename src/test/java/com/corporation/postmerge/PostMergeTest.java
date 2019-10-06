package com.corporation.postmerge;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@Ignore
public class PostMergeTest {

    @Test
    public void whenSourceEmpty() {
        PostMerge process = new PostMerge();

        List<User> expect = new ArrayList<>();

        List<User> result = process.merge(new ArrayList<User>());
        assertThat(result, is(expect));
    }

    @Test
    public void whenSourceHasOneUser() {

        PostMerge process = new PostMerge();
        User user = new User("user0", new HashSet<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));

        List<User> expect = new ArrayList<>();
        expect.add(user);

        List<User> source = new ArrayList<>();
        source.add(user);
        List<User> result = process.merge(source);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSourceUsersHaveSamePost() {

        PostMerge process = new PostMerge();
        User user0 = new User("user0", new HashSet<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        User user1 = new User("user1", new HashSet<>(Arrays.asList("lol@mail.ru")));

        List<User> expect = new ArrayList<>();
        expect.add(user0);

        List<User> source = new ArrayList<>();
        source.add(user0);
        source.add(user1);
        List<User> result = process.merge(source);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSourceUsersHaveDifferentPost() {

        PostMerge process = new PostMerge();
        User user0 = new User("user0", new HashSet<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        User user1 = new User("user1", new HashSet<>(Arrays.asList("quiz@mail.ru")));

        List<User> expect = new ArrayList<>();
        expect.add(user0);
        expect.add(user1);

        List<User> source = new ArrayList<>();
        source.add(user0);
        source.add(user1);
        List<User> result = process.merge(source);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSourceUsersHaveSameAndDifferentPost() {

        PostMerge process = new PostMerge();
        User user0 = new User("user0", new HashSet<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        User user1 = new User("user1", new HashSet<>(Arrays.asList("lol@mail.ru", "gipgip@gmail.com")));
        User user2 = new User(
                "user0",
                new HashSet<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "gipgip@gmail.com"))
        );

        List<User> expect = new ArrayList<>();
        expect.add(user2);

        List<User> source = new ArrayList<>();
        source.add(user0);
        source.add(user1);
        List<User> result = process.merge(source);
        assertThat(result, is(expect));
    }

}