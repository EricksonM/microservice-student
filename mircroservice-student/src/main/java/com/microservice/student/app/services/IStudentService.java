package com.microservice.student.app.services;

import java.time.LocalDate;

import com.microservice.student.app.documents.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IStudentService {

	public Flux<Student> getAll();
	public Mono<Student> save(Student student); //crear y modificar
	public Mono<Void> deleteById(String id);
	public Mono<Student> getById(String id);
	public Flux<Student> getByFirstName(String firstName);
	public Flux<Student> getByBirthdayDate(LocalDate birthdayDate);
	public Flux<Student> getByBirthdayDateBetween(LocalDate date1, LocalDate date2);
}
