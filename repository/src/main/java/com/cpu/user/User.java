package com.cpu.user;

import com.cpu.AuditTrail;
import com.cpu.department.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Lob
    private String image;
    @OneToOne
    private Department department;
    private String password;
    private boolean admin=false;


    @Transient
    @JsonIgnore
    private Long deptId;
}
