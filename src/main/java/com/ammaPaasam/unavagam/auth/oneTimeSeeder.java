//package com.ammaPaasam.unavagam.auth;
//
//import com.ammaPaasam.unavagam.entity.base.Roles;
//import com.ammaPaasam.unavagam.repository.RoleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class oneTimeSeeder implements CommandLineRunner {
//
//
//    private final RoleRepository roleRepository;
//    @Override
//    public void run(String... args) {
//
//        Roles role = new Roles();
//        role.setRoleName("ORPHANAGE");
//        role.setDescription("test");
//        role.setActive(true);
//        role.setDeleted(false);
//
//        roleRepository.save(role);
//
//        Roles role1 = new Roles();
//        role1.setRoleName("FOOD_DONOR");
//        role1.setDescription("test");
//        role1.setActive(true);
//        role1.setDeleted(false);
//
//        roleRepository.save(role1);
//
//    }
//}
