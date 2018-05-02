package com.cpu;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id", "version"})
@MappedSuperclass
public class AuditTrail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	private long version;
	private boolean active = true;
	private long createdBy;
	private long dateCreated;
	private Long modifiedBy;
	private Long dateModified;
	

}
