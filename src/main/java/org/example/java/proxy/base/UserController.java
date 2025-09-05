package org.example.java.proxy.base;

/**
 * @author lxc
 * @date 2025/7/21
 */
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean register(String username, String password) {
        return userService.register(username, password);
    }

}
