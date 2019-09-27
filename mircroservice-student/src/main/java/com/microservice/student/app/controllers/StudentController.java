package com.microservice.student.app.controllers;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.student.app.documents.Student;
import com.microservice.student.app.services.StudentServiceImp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StudentController {

	@Autowired
	private StudentServiceImp _studentService;
	
	@GetMapping("/")
	public ResponseEntity<Flux<Student>> getAll(){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(_studentService.getAll());
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Mono<Student>> createStudent(@Valid @RequestBody Student student){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(_studentService.save(student));
	}
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Mono<Student>> updateStudent(@RequestBody Student student, @PathVariable String id){
		
		Mono<Student> studentDB = _studentService.getById(id).flatMap(st -> {
			st.setFirstName(student.getFirstName());
			st.setLastName(student.getLastName());
			st.setGender(student.getGender());
			st.setBirthdayDate(student.getBirthdayDate());
			st.setDocType(student.getDocType());
			st.setDocNumber(student.getDocNumber());
			
			return _studentService.save(st);
		});
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(studentDB);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteStudentById(@PathVariable String id){
		
		return _studentService.deleteById(id);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Mono<Student>> getStudentById(@RequestBody Student student, @PathVariable String id){
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(_studentService.getById(id));
	}
	
	@GetMapping("/name/{firstName}")
	public Mono<ResponseEntity<Flux<Student>>> getStudentByFirstName(@RequestBody Student student, @PathVariable String firstName){
		
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(_studentService.getByFirstName(firstName)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/date/{birthdayDate}")
	public Mono<ResponseEntity<Flux<Student>>> getStudentByBirthdayDate(@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate birthdayDate){
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(_studentService.getByBirthdayDate(birthdayDate)));
	}
	
	@GetMapping("/dates/{date1}/{date2}")
	public ResponseEntity<Flux<Student>> getByBirthdayDateBetween(@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate date1,
																  @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate date2){
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(_studentService.getByBirthdayDateBetween(date1, date2));
	}
	
}







