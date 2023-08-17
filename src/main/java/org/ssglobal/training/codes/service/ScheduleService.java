package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Schedule;
import org.ssglobal.training.codes.repository.ScheduleRepository;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {
	private final ScheduleRepository scheduleRepository;
	
	public List<Schedule> getAllSchedule() {
		return scheduleRepository.getAllSchedule();
	}
	
	public Schedule getScheduleById(Long id) {
		return scheduleRepository.getScheduleById(id);
	}
	
	public List<Schedule> getScheduleByFarmerId(Integer id) {
		return scheduleRepository.getScheduleByFarmerId(id);
	}
	
	@Transactional
	public Response addSchedule(Schedule schedule) {
		return scheduleRepository.addSchedule(schedule);
	}

	@Transactional
	public Response updateSchedule(Long id, Schedule schedule) {
		return scheduleRepository.updateSchedule(id, schedule);
	}

}
