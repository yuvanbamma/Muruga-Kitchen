package com.ammaPaasam.unavagam.auth;

import com.ammaPaasam.unavagam.entity.Cities;
import com.ammaPaasam.unavagam.entity.States;
import com.ammaPaasam.unavagam.entity.base.Roles;
import com.ammaPaasam.unavagam.repository.CityRepository;
import com.ammaPaasam.unavagam.repository.RoleRepository;
import com.ammaPaasam.unavagam.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Component
public class oneTimeSeeder implements CommandLineRunner {

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

    private final StateRepository stateRepository;
    private final CityRepository cityRepository;

    @Override
    public void run(String... args) {

        // ===== STATES =====
        States tamilNadu = saveState("Tamil Nadu");
        States kerala = saveState("Kerala");
        States karnataka = saveState("Karnataka");
        States andhra = saveState("Andhra Pradesh");

        // ===== CITIES =====

        // Tamil Nadu
        saveCity("Chennai", "600001", tamilNadu.getId());
        saveCity("Coimbatore", "641001", tamilNadu.getId());
        saveCity("Madurai", "625001", tamilNadu.getId());
        saveCity("Pollachi", "642001", tamilNadu.getId());

        // Kerala
        saveCity("Kochi", "682001", kerala.getId());
        saveCity("Trivandrum", "695001", kerala.getId());
        saveCity("Thrissur", "680001", kerala.getId());

        // Karnataka
        saveCity("Bengaluru", "560001", karnataka.getId());
        saveCity("Mysuru", "570001", karnataka.getId());

        // Andhra Pradesh
        saveCity("Visakhapatnam", "530001", andhra.getId());
        saveCity("Vijayawada", "520001", andhra.getId());
    }

    private States saveState(String name) {
        States state = new States();
        state.setStateName(name);
        return stateRepository.save(state);
    }

    private void saveCity(String name, String pinCode, UUID stateId) {
        Cities city = new Cities();
        city.setCityName(name);
        city.setPinCode(pinCode);
        city.setStateId(stateId);
        cityRepository.save(city);
    }

}
