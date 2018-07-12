package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(AbstractBaseEntity.SEQUENCE + 1,
                    "Fedor", "fedor@mail.ru", "pass1", Role.ROLE_ADMIN, Role.ROLE_USER),
            new User(AbstractBaseEntity.SEQUENCE + 2,
                    "Masha", "masha@mail.ru", "pass2", Role.ROLE_USER),
            new User(AbstractBaseEntity.SEQUENCE + 3,
                    "Glasha", "glasha@mail.ru", "pass3", Role.ROLE_USER)
    );
}
