package com.enterprise.srm.controller;

import com.enterprise.srm.model.*;
import com.enterprise.srm.service.ServiceRequestService;
import com.enterprise.srm.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employee")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class EmployeeController {

    private final ServiceRequestService requestService;
    private final UserService userService;

    @GetMapping("/requests")
    public String myRequests(Model model, Authentication auth,
                             @RequestParam(required = false) String search) {
        User currentUser = userService.findByEmail(auth.getName());
        var requests = (search != null && !search.isBlank())
                ? requestService.searchForEmployee(currentUser, search)
                : requestService.getRequestsForEmployee(currentUser);

        model.addAttribute("requests", requests);
        model.addAttribute("search", search);
        model.addAttribute("currentUser", currentUser);
        return "employee/requests";
    }

    @GetMapping("/requests/new")
    public String newRequestForm(Model model, Authentication auth) {
        User currentUser = userService.findByEmail(auth.getName());
        model.addAttribute("requestForm", new ServiceRequestForm());
        model.addAttribute("categories", RequestCategory.values());
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("currentUser", currentUser);
        return "employee/new-request";
    }

    @PostMapping("/requests/new")
    public String submitRequest(@Valid @ModelAttribute("requestForm") ServiceRequestForm form,
                                BindingResult result,
                                Model model,
                                Authentication auth,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            User currentUser = userService.findByEmail(auth.getName());
            model.addAttribute("categories", RequestCategory.values());
            model.addAttribute("priorities", Priority.values());
            model.addAttribute("currentUser", currentUser);
            return "employee/new-request";
        }
        User currentUser = userService.findByEmail(auth.getName());
        ServiceRequest created = requestService.createRequest(
                form.getTitle(), form.getDescription(),
                form.getCategory(), form.getPriority(), currentUser);

        redirectAttributes.addFlashAttribute("successMsg",
                "Request " + created.getRequestNumber() + " submitted successfully!");
        return "redirect:/employee/requests";
    }

    @GetMapping("/requests/{id}")
    public String viewRequest(@PathVariable Long id, Model model, Authentication auth) {
        User currentUser = userService.findByEmail(auth.getName());
        ServiceRequest request = requestService.findById(id);

        // Employees can only see their own requests
        if (!request.getSubmittedBy().getId().equals(currentUser.getId())
            && currentUser.getRole() == Role.EMPLOYEE) {
            return "redirect:/employee/requests?error=forbidden";
        }

        model.addAttribute("request", request);
        model.addAttribute("comments", requestService.getPublicComments(request));
        model.addAttribute("currentUser", currentUser);
        return "employee/request-detail";
    }

    // DTO for form binding
    public static class ServiceRequestForm {
        @jakarta.validation.constraints.NotBlank(message = "Title is required")
        @jakarta.validation.constraints.Size(min = 5, max = 200)
        private String title;

        @jakarta.validation.constraints.NotBlank(message = "Description is required")
        @jakarta.validation.constraints.Size(min = 10, max = 2000)
        private String description;

        @jakarta.validation.constraints.NotNull(message = "Please select a category")
        private RequestCategory category;

        @jakarta.validation.constraints.NotNull(message = "Please select a priority")
        private Priority priority;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public RequestCategory getCategory() { return category; }
        public void setCategory(RequestCategory category) { this.category = category; }
        public Priority getPriority() { return priority; }
        public void setPriority(Priority priority) { this.priority = priority; }
    }
}
