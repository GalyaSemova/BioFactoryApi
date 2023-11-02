package com.dev.biostoreapi.service;

import com.dev.biostoreapi.model.entity.UserRoleEntity;
import com.dev.biostoreapi.model.enums.UserRoleEnum;

public interface UserRoleService {

    UserRoleEntity getUserRoleByEnumName(UserRoleEnum userRoleEnum);
}
