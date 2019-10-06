package com.corporation.postmerge;

import java.util.*;

public class PostMerge {

    public List<User> merge(List<User> srcUsers) {

        List<User> resUsers = new ArrayList<>();
        if (srcUsers.size() < 1) {
            return resUsers;
        }
        
        resUsers.add(srcUsers.get(0));
        srcUsers.remove(0);

        boolean isAddNewUserInResList;
        for (User keyUser : srcUsers) {

            isAddNewUserInResList = true;

            for (User userInResList : resUsers) {

                if (isSameUser(keyUser, userInResList)) {
                    userInResList.getMailAddresses().addAll(keyUser.getMailAddresses());
                    isAddNewUserInResList = false;
                    break;
                }
            }

            if (isAddNewUserInResList) {
                resUsers.add(keyUser);
            }
        }
        return resUsers;
    }

    public boolean isSameUser(User keyUser, User inSetUser) {
        Set<String> tmpSet = new HashSet<>(inSetUser.getMailAddresses());
        tmpSet.retainAll(keyUser.getMailAddresses());
        return !tmpSet.isEmpty();
    }

    public static void main(String[] args) {

        List<User> srcUsers = new ArrayList<>();
        srcUsers.add(new User("user0", new HashSet<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"))));
        srcUsers.add(new User("user1", new HashSet<>(Arrays.asList("foo@gmail.com", "ups@pisem.net"))));
        srcUsers.add(new User("user2", new HashSet<>(Arrays.asList("xyz@pisem.net", "vasya@pupkin.com"))));
        srcUsers.add(new User("user3", new HashSet<>(Arrays.asList("ups@pisem.net", "aaa@bbb.ru"))));
        srcUsers.add(new User("user4", new HashSet<>(Arrays.asList("xyz@pisem.net"))));

        System.out.println("Source user list: ");
        System.out.println(Arrays.toString(srcUsers.toArray()));

        PostMerge process = new PostMerge();
        List<User> resUsers = process.merge(srcUsers);

        System.out.println("Result user list: ");
        System.out.println(Arrays.toString(resUsers.toArray()));
    }
}
