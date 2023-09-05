package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ssglobal.training.codes.model.Complaint;
import org.ssglobal.training.codes.model.Farmer;
import org.ssglobal.training.codes.model.Image;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.request.ComplaintRequest;
import org.ssglobal.training.codes.response.ComplaintResponse;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ComplaintRepository {
	private final SessionFactory sf;
	
	public List<ComplaintResponse> getAllComplaints() {
		try (Session session = sf.openSession()) {
			List<Complaint> complaints = session.createQuery("FROM Complaint", Complaint.class).list();
			
			List<ComplaintResponse> complaintResponses = new ArrayList<>();
			complaints.forEach(complaint -> {
				User farmer = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", complaint.getFarmerId())
						.uniqueResult();
				
				ComplaintResponse complaintResponse = ComplaintResponse.builder()
						.complaintId(complaint.getComplaintId())
						.farmer(farmer)
						.complaintType(complaint.getComplaintType())
						.complaintDetails(complaint.getComplaintDetails())
						.date(complaint.getDate())
						.image(complaint.getImage())
						.status(complaint.getStatus())
						.isDeleted(complaint.getIsDeleted())
						.build();
				complaintResponses.add(complaintResponse);
			});
			
			return complaintResponses;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Complaint getComplaintById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(Complaint.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<ComplaintResponse> getComplaintByFarmerId(Long id) {
		try (Session session = sf.openSession()) {
			List<Complaint> complaints = session.createQuery("FROM Complaint WHERE farmerId = :farmerId", Complaint.class)
					.setParameter("farmerId", id)
					.list();
			
			List<ComplaintResponse> complaintResponses = new ArrayList<>();
			complaints.forEach(complaint -> {
				User farmer = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", complaint.getFarmerId())
						.uniqueResult();
				
				ComplaintResponse complaintResponse = ComplaintResponse.builder()
						.complaintId(complaint.getComplaintId())
						.farmer(farmer)
						.complaintType(complaint.getComplaintType())
						.complaintDetails(complaint.getComplaintDetails())
						.date(complaint.getDate())
						.image(complaint.getImage())
						.status(complaint.getStatus())
						.isDeleted(complaint.getIsDeleted())
						.build();
				complaintResponses.add(complaintResponse);
			});
			
			return complaintResponses;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response addComplaint(ComplaintRequest complaint) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();

			Complaint comp = new Complaint();
			comp.setDate(LocalDate.now());
			comp.setStatus(false);
			comp.setIsDeleted(false);
			
			if (complaint.getFarmerId() != null) {
				Query<Farmer> query1 = session.createQuery("FROM Farmer WHERE userId = :userId", Farmer.class)
						.setParameter("userId", complaint.getFarmerId());
				Farmer farmer = query1.uniqueResult();
				if (farmer != null) {
					comp.setFarmerId(complaint.getFarmerId());
				} else {
					return Response.builder()
							.status(404)
							.message("Farmer Id does not exists")
							.timestamp(LocalDateTime.now())
							.build();
				}	
			}
			if (complaint.getComplaintType() != null && complaint.getComplaintType() != "") {
				comp.setComplaintType(complaint.getComplaintType());
			}
			if (complaint.getComplaintDetails() != null && complaint.getComplaintDetails() != "") {
				comp.setComplaintDetails(complaint.getComplaintDetails());
			}
			if (complaint.getFilename() != null && complaint.getFilename() != "") {
				Query<Image> query = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
						.setParameter("filename", complaint.getFilename());
				Image image = query.uniqueResult();
				if (image == null) {
					Image img = Image.builder()
							.filename(complaint.getFilename())
							.mimeType(complaint.getMimeType())
							.data(complaint.getData())
							.build();
					session.persist(img);
				}
				
				comp.setImage(createImageLink(complaint.getFilename()));
			}
			session.persist(comp);
			
			session.getTransaction().commit();
			
			return Response.builder()
					.status(201)
					.message("Complaint successfully created")
					.timestamp(LocalDateTime.now())
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response updateComplaint(Long id, ComplaintRequest complaint) {
		try (Session session = sf.openSession()) {
			Complaint comp = session.get(Complaint.class, id);
			if (comp != null) {
				session.beginTransaction();
				
				if (complaint.getComplaintType() != null && complaint.getComplaintType() != "") {
					comp.setComplaintType(complaint.getComplaintType());
				}
				if (complaint.getComplaintDetails() != null && complaint.getComplaintDetails() != "") {
					comp.setComplaintDetails(complaint.getComplaintDetails());
				}
				if (complaint.getFilename() != null && complaint.getFilename() != "") {
					Query<Image> query = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
							.setParameter("filename", complaint.getFilename());
					Image image = query.uniqueResult();
					if (image == null) {
						Image img = Image.builder()
								.filename(complaint.getFilename())
								.mimeType(complaint.getMimeType())
								.data(complaint.getData())
								.build();
						session.persist(img);
					}
					
					comp.setImage(createImageLink(complaint.getFilename()));
				}
				if (complaint.getStatus() != null) {
					comp.setStatus(complaint.getStatus());
				}
				if (complaint.getIsDeleted() != null) {
					comp.setIsDeleted(complaint.getIsDeleted());
				}
				
				session.getTransaction().commit();
				
				return Response.builder()
						.status(200)
						.message("Complaint successfully updated")
						.timestamp(LocalDateTime.now())
						.build();
			} else {
				return Response.builder()
						.status(404)
						.message("Complaint not found")
						.timestamp(LocalDateTime.now())
						.build();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response deleteComplaint(Long id) {
	    try (Session session = sf.openSession()) {
	        Complaint complaint = session.get(Complaint.class, id);
	        if (complaint != null) {
	            session.beginTransaction();
	            session.delete(complaint);
	            session.getTransaction().commit();

	            return Response.builder()
	                    .status(200)
	                    .message("Complaint successfully deleted")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        } else {
	            return Response.builder()
	                    .status(404)
	                    .message("Complaint not found")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }
	    } catch (HibernateException e) {
	        throw new RuntimeException("Error deleting complaint: " + e.getMessage(), e);
	    } catch (Exception e) {
	        throw new RuntimeException("Error deleting complaint: " + e.getMessage(), e);
	    }
	}

	
	private String createImageLink(String filename) {
		return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api/image/" + filename).toUriString();
	}
}
