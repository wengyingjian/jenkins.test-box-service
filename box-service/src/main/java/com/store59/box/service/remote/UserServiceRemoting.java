package com.store59.box.service.remote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.store59.box.model.User;
import com.store59.box.remoting.UserService;
import com.store59.rpc.utils.server.annotation.RemoteService;
import com.store59.rpc.utils.server.annotation.ServiceType;

@RemoteService(serviceType = ServiceType.HESSIAN, serviceInterface = UserService.class, exportPath = "/user")
public class UserServiceRemoting implements UserService {

    @Autowired
    private UserService userService;

    @Override
    public List<User> getUsers(User user) {
        return userService.getUsers(user);
    }

}
