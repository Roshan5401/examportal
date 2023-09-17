package com.exam.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long roleId;
	private String roleName;

	//for one role many user,many user for single role
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
	@JsonIgnore
	private Set<UserRole> userRoles=new HashSet<>();
}
