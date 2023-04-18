package com.example.SeatingManagement.Services;

import com.example.SeatingManagement.Entity.Role;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role){
        Role newRole=this.roleRepository.save(role);
        return newRole;
    }
    public List<Role> getAllRole(){
        List<Role> roles=this.roleRepository.findAll();
        return roles;
    }
    public Role getRoleById(Integer id){
        Role role=this.roleRepository.findById(id).orElseThrow(()->new ResourceNotFound("Role","role_id",""+id));
        return role;
    }
    public Role updateRole( Role role,Integer id){
        Role curRole=this.roleRepository.findById(id).orElseThrow(()->new ResourceNotFound("Role","role_id",""+id));
        curRole.setName(role.getName());
        return this.roleRepository.save(curRole);
    }
    public void deleteRole(Integer id){
        Role role=this.roleRepository.findById(id).orElseThrow(()->new ResourceNotFound("Role","role_id",""+id));
        this.roleRepository.delete(role);
    }
}
