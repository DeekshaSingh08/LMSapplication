package poc.Lmsapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.Lmsapplication.entities.AdminDetails;
import poc.Lmsapplication.exceptionhandling.AdminNotFoundException;
import poc.Lmsapplication.repositories.AdminRepository;

import java.util.List;

/**
 * @author deeksha.singh
 */
@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public List<AdminDetails> findAllAdmins() {
        return adminRepository.findAll();
    }

    public AdminDetails findAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public AdminDetails createAdmin(AdminDetails adminDetails) {
        return adminRepository.save(adminDetails);
    }

    public AdminDetails updateAdmin(AdminDetails adminDetails, long id) {
        if (!adminRepository.existsById(id)) {
            throw new AdminNotFoundException("Admin was not found !");
        } else {
            AdminDetails adminDetails1 = adminRepository.findById(id).orElse(adminDetails);
            adminDetails1.setEmailId(adminDetails.getEmailId());
            return adminRepository.save(adminDetails1);
        }

    }

    public String deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new AdminNotFoundException("Admin was not found !");
        } else {
            adminRepository.deleteById(id);
            return "Successfully deleted !";
        }


    }
}