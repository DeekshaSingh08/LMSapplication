package poc.Lmsapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc.Lmsapplication.entities.AdminDetails;
import poc.Lmsapplication.services.AdminService;

import java.util.List;

/**
 * @author deeksha.singh
 */
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;


    @GetMapping("/getAllAdmins")
    public List<AdminDetails> getAllAdmins(){
        return adminService.findAllAdmins();
    }

    @GetMapping("/getAdminById/{Id}")
    public AdminDetails getAdminById(@PathVariable("id") Long id){
        return adminService.findAdminById(id);
    }

   @PostMapping("/createAdmin")
    public AdminDetails createAdmin(@RequestBody AdminDetails adminDetails){
        return adminService.createAdmin(adminDetails);
   }

    @PutMapping("/updateAdmin/{id}")
    public AdminDetails updateAdmin(@PathVariable("id") long id, @RequestBody AdminDetails adminDetails) {
        return adminService.updateAdmin(adminDetails, id);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Long id) {
        adminService.deleteAdmin(id);
        return new ResponseEntity<>("Admin is deleted successfully", HttpStatus.OK);
    }

}
