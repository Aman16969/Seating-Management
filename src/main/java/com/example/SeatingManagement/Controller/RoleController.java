package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.Role;
import com.example.SeatingManagement.PayLoad.ApiResponse;
import com.example.SeatingManagement.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        Role newRole=this.roleService.createRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<Role>> getAllRole(){
        List<Role> roles=this.roleService.getAllRole();
        return new ResponseEntity<>(roles,HttpStatus.ACCEPTED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id){
        Role role=this.roleService.getRoleById(id);
        return new ResponseEntity<>(role,HttpStatus.OK);


    }
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role,@PathVariable Integer id){
        Role updatedRole=this.roleService.updateRole(role,id);
        return new ResponseEntity<>(updatedRole,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse> deleteRole(@PathVariable Integer id){
        this.roleService.deleteRole(id);
        return new ResponseEntity<>(new ApiResponse("Role was deleted succesfully",true),HttpStatus.ACCEPTED);
    }

}
