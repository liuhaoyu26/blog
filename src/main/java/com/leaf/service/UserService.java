package com.leaf.service;

import com.leaf.po.User;



public interface UserService {

	User checkUser(String username, String password);
}
