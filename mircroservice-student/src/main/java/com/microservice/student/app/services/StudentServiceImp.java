package com.microservice.student.app.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.student.app.documents.Student;
import com.microservice.student.app.repositories.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImp implements IStudentService {

	@Autowired
	private StudentRepository _studentRepo;
	
	@Override
	public Flux<Student> getAll() {
		return _studentRepo.findAll();
	}

	@Override
	public Mono<Student> save(Student student) {
		return _studentRepo.save(student);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return _studentRepo.deleteById(id);
	}

	@Override
	public Mono<Student> getById(String id) {
		return _studentRepo.findById(id);
	}

	@Override
	public Flux<Student> getByFirstName(String firstName) {
		return _studentRepo.findByFirstName(firstName);
	}

	@Override
	public Flux<Student> getByBirthdayDate(LocalDate birthdayDate) {
		return _studentRepo.findByBirthdayDate(birthdayDate);
	}

	@Override
	public Flux<Student> getByBirthdayDateBetween(LocalDate date1, LocalDate date2) {
		return _studentRepo.findByBirthdayDateBetween(date1, date2);
	}

}
