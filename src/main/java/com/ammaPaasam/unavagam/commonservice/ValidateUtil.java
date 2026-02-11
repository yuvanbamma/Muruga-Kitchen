package com.ammaPaasam.unavagam.commonservice;

import com.ammaPaasam.unavagam.entity.base.Roles;
import com.ammaPaasam.unavagam.exception.ApiException;
import com.ammaPaasam.unavagam.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidateUtil {

    private final RoleRepository roleRepository;


    public UUID validateAndGetRoleUUId(String role) {
        Roles roles = roleRepository.findByRoleName(role);
        if (roles == null) {
            throw new ApiException("Role not found for this id", HttpStatus.NOT_FOUND);
        } else {
            return roles.getId();
        }
    }

    public String getRoleNameByUUID(UUID roleId){
        Roles role = roleRepository.findById(roleId).orElseThrow(() ->  new ApiException("Role not found for this id",HttpStatus.NOT_FOUND));
        return role.getRoleName();
    }
}
