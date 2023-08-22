package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Complaint;
import org.ssglobal.training.codes.repository.ComplaintRepository;
import org.ssglobal.training.codes.request.ComplaintRequest;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplaintService {
	private final ComplaintRepository complaintRepository;
	
	public List<Complaint> getAllComplaints() {
		return complaintRepository.getAllComplaints();
	}
	
	public Complaint getComplaintById(Long id) {
		return complaintRepository.getComplaintById(id);
	}
	
	public List<Complaint> getComplaintByFarmerId(Long id) {
		return complaintRepository.getComplaintByFarmerId(id);
	}
	
	@Transactional
	public Response addComplaint(ComplaintRequest complaint) {
		return complaintRepository.addComplaint(complaint);
	}
	
	@Transactional
	public Response updateComplaint(Long id, ComplaintRequest complaint) {
		return complaintRepository.updateComplaint(id, complaint);
	}
}
