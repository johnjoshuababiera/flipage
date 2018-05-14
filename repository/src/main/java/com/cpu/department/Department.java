package com.cpu.department;

import com.cpu.AuditTrail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Department extends AuditTrail {
}
