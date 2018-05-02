package com.cpu.user;

import com.cpu.AuditTrail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends AuditTrail {
    private String email;
    private String department;
    private String username;
    private String password;
    //1 admin, 2 user
    private int role;
}
