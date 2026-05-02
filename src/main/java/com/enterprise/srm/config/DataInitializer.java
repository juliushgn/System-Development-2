package com.enterprise.srm.config;

import com.enterprise.srm.model.*;
import com.enterprise.srm.repository.ServiceRequestRepository;
import com.enterprise.srm.repository.UserRepository;
import com.enterprise.srm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ServiceRequestRepository requestRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            log.info("Database already populated — skipping initialization.");
            return;
        }

        log.info("Initializing demo data...");

        // Create users
        User alice = userService.createUser("Alice", "Meyer", "alice@company.com",
                "password123", Role.EMPLOYEE, "Marketing");
        User bob = userService.createUser("Bob", "Schmidt", "bob@company.com",
                "password123", Role.EMPLOYEE, "Sales");
        User carol = userService.createUser("Carol", "Wagner", "carol@company.com",
                "password123", Role.SERVICE_AGENT, "IT");
        User dave = userService.createUser("Dave", "Fischer", "dave@company.com",
                "password123", Role.SERVICE_AGENT, "Facilities");
        User eve = userService.createUser("Eve", "Braun", "eve@company.com",
                "password123", Role.MANAGER, "Operations");

        // Sample service requests
        createSampleRequest("Laptop won't start", "My laptop does not boot since this morning. It shows a black screen.", RequestCategory.IT_SUPPORT, Priority.HIGH, alice);
        createSampleRequest("Broken office chair", "The chair in room 204 has a broken wheel and is unsafe.", RequestCategory.FACILITY, Priority.MEDIUM, bob);
        createSampleRequest("Request employment certificate", "I need an official employment certificate for my bank.", RequestCategory.HR_DOCUMENT, Priority.LOW, alice);
        createSampleRequest("VPN access needed", "I need VPN access for remote work starting next Monday.", RequestCategory.ACCESS_PERMISSION, Priority.HIGH, bob);
        createSampleRequest("Printer out of paper", "The printer on floor 3 has been out of paper for 2 days.", RequestCategory.OFFICE_SUPPLIES, Priority.LOW, alice);

        log.info("Demo data initialized. Login credentials:");
        log.info("  Employee : alice@company.com / password123");
        log.info("  Employee : bob@company.com   / password123");
        log.info("  Agent    : carol@company.com / password123");
        log.info("  Agent    : dave@company.com  / password123");
        log.info("  Manager  : eve@company.com   / password123");
    }

    private void createSampleRequest(String title, String description,
                                     RequestCategory category, Priority priority, User user) {
        ServiceRequest r = new ServiceRequest();
        r.setTitle(title);
        r.setDescription(description);
        r.setCategory(category);
        r.setPriority(priority);
        r.setStatus(RequestStatus.NEW);
        r.setSubmittedBy(user);
        requestRepository.save(r);
    }
}
