package org.ssglobal.training.codes.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.ssglobal.training.codes.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "suffix")
	private String suffix;
	
	@Column(name = "gender")
	private String gender; 
	
	@Column(name = "birthdate")
	private LocalDate birthdate;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "village")
	private String village;
	
	@Column(name = "barangay")
	private String barangay;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "province")
	private String province;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "id_type")
	private String idType;
	
	@Column(name = "id_front")
	private String idFront;
	
	@Column(name = "id_back")
	private String idBack;
	
	@Column(name = "selfie")
	private String seflie;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "status")
	private String status;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

