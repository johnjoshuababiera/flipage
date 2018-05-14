package com.cpu.user;

import com.cpu.AuditTrail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends AuditTrail{
    private String username;
    private String idNumber;
    private String email;
    private String image;
    private String department;
    private String password;
    private boolean isAdmin;
}
