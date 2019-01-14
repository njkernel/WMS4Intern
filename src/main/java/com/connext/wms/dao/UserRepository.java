package com.connext.wms.dao;

import com.connext.wms.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User,Integer> {
    User findByUserName(String username);
}
