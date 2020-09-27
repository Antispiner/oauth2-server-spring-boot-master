package com.internalproject.api.services;

import com.internalproject.api.domain.requests.UserCreateRequest;
import com.internalproject.api.model.entity.user.User;

public interface UserService {
    User create(UserCreateRequest request);
}
