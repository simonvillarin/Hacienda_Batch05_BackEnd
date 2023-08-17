package org.ssglobal.training.codes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Schedule;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {
	private final SessionFactory sf;

	public List<Schedule> getAllSchedule() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Schedule", Schedule.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Schedule getScheduleById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(Schedule.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<Schedule> getScheduleByFarmerId(Integer id) {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Schedule WHERE farmerId = :farmerId", Schedule.class)
					.setParameter("farmerId", id).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Response addSchedule(Schedule schedule) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();

			Schedule sched = new Schedule();
			sched.setStatus(true);

			if (schedule.getFarmerId() != null) {
				sched.setFarmerId(schedule.getFarmerId());
			}

			if (schedule.getStartDate() != null) {
				sched.setStartDate(schedule.getStartDate());
			}
			
			if (schedule.getEndDate() != null) {
				sched.setEndDate(schedule.getEndDate());
			}

			session.persist(sched);

			session.getTransaction().commit();

			return Response.builder().status(201).message("Schedule successfully created")
					.timestamp(LocalDateTime.now()).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response updateSchedule(Long id, Schedule schedule) {
		try (Session session = sf.openSession()) {
			Schedule sched = session.get(Schedule.class, id);
			if (sched != null) {
				session.beginTransaction();
				
				if (schedule.getFarmerId() != null) {
					sched.setFarmerId(schedule.getFarmerId());
				}
				
				if (schedule.getStartDate() != null) {
					sched.setStartDate(sched.getStartDate());
				}
				
				if (schedule.getEndDate() != null) {
					sched.setEndDate(sched.getEndDate());
				}
				
				if (schedule.getStatus() != null) {
					sched.setStatus(schedule.getStatus());
				}
				
				session.getTransaction().commit();
				
				return Response.builder()
						.status(200)
						.message("Schedule successfully updated")
						.timestamp(LocalDateTime.now())
						.build();
			} else {
				return Response.builder()
						.status(404)
						.message("Schedule not found")
						.timestamp(LocalDateTime.now())
						.build();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
