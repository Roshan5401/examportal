package com.exam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRole {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userRoleId;
	
	//user
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	//role
	@ManyToOne
	private Role role;
	
}
