package com.microservice.student.app.repositories;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.student.app.documents.Student;

import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String>{

	Flux<Student> findByFirstName(String firstName);
	Flux<Student> findByBirthdayDate(LocalDate birthdayDate);
	Flux<Student> findByBirthdayDateBetween(LocalDate date1, LocalDate date2);
}
