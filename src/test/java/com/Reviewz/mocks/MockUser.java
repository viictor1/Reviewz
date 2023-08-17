package com.Reviewz.mocks;

import com.Reviewz.core.user.model.User;
import com.Reviewz.infra.dataprovider.schema.user.UserRole;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockUser {

    public UserSchema mockEntity(Integer number){
        UserSchema user = new UserSchema();
        user.setId(UUID.randomUUID());
        user.setName("Name Test " + number);
        user.setLogin("Login Test " + number);
        user.setPassword("Password Test " + number);
        user.setRole(UserRole.USER);
        return user;
    }

    public User mockDto(Integer number) throws Exception {
        User user = new User();
        user.setId(UUID.fromString(number.toString()));
        user.setName("Name Test " + number);
        user.setLogin("Login Test " + number);
        user.setPassword("Password Test " + number);
        user.setRole(UserRole.USER);
        return user;
    }

    public User mockDto() throws Exception {
        return mockDto(0);
    }

    public List<UserSchema> mockEntityList() {
        List<UserSchema> users = new ArrayList<UserSchema>();
        for (int i = 0; i < 14; i++) {
            users.add(mockEntity(i));
        }
        return users;
    }

    public List<User> mockDtoList() throws Exception {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            users.add(mockDto(i));
        }
        return users;
    }

}
