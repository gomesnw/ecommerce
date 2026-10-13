package dev.branchesandgomes.config.context;

import dev.branchesandgomes.model.UserEntity;

public class UserContext {
    private static ThreadLocal<UserEntity> user = new ThreadLocal<>();

    public static UserEntity getUser() {
        return user.get();
    }

    public static void setUser(UserEntity userEntity) {
        user.set(userEntity);
    }

    public static void cleanup() {
        user.remove();
    }
}
