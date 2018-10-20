package com.cpu;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = {"id", "version"})
public class AuditTrail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	private long version;
	private boolean active = true;
	private long dateCreated;


}
