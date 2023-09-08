package org.ssglobal.training.codes.request;

import java.time.LocalDate;

import org.ssglobal.training.codes.enums.Role;

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
	private String province;
	private String region;
	private String contact;
	private String email;
	private String username;
	private String password;
	private String idType;
	private String idNumber;
	private String selfie;
	private String filename;
	private String mimeType;
	private byte[] data;
	private String filename1;
	private String mimeType1;
	private byte[] data1;
	private String filename2;
	private String mimeType2;
	private byte[] data2;
	private Role role;
	private String status;
}
