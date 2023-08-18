package org.ssglobal.training.codes.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	private String gender;
	private LocalDate birthdate;
	private String unit;
	private String street;
	private String village;
	private String barangay;
	private String city;
	private String region;
	private Integer zipCode;
	private String contact;
	private String email;
	private String password;
	private String filename;
	private String mimeType;
	private byte[] data;
	private Boolean status;
}
