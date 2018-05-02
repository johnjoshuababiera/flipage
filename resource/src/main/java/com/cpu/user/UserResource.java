package com.cpu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    
    @Autowired
    private UserService service;

    @PostMapping("/save")
    public User create(@RequestBody User user){
        return service.save(user);
    }

    @GetMapping(value="/findById")
    public User findStudent(@RequestParam long id){
        return service.findOne(id);
    }


    @GetMapping(value = "/remove")
    public void remove(@RequestParam long id) {
        service.remove(id);
    }

    @PostMapping(value="/hasDuplicate")
    public boolean hasDuplicate(@RequestBody User user) throws Exception {
        return service.hasDuplicate(user);
    }
}
