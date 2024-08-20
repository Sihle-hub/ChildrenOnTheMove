package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.RetailStudent;
import com.example.demo.domain.Student;
import com.example.demo.repository.RetailStudentRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class RetailStudentService {

	
	@Autowired
	private RetailStudentRepository repo;
	
	public List<RetailStudent> listAll(){
		
		  return repo.findAll();
	}
	
	public void save(RetailStudent msc) {
		
		  repo.save(msc);
	}
	
	
	public void delete(RetailStudent msc) {
		
		repo.delete(msc);
		
	}
	
	 public void update(String id , RetailStudent updatedStudent) {
	       
		 Optional<RetailStudent> student = repo.findById(id);
		 
		 if (student.isPresent()) {
			 
			 RetailStudent existingStudent = student.get();

			 existingStudent.setName(updatedStudent.getName());
			 existingStudent.setSurname(updatedStudent.getSurname());
			 existingStudent.setContact(updatedStudent.getContact());
			 existingStudent.setId(updatedStudent.getId());
			 existingStudent.setAddress(updatedStudent.getAddress());
	           
			 existingStudent.setTypeWelfare(updatedStudent.getTypeWelfare());
			 existingStudent.setMatricStatus(updatedStudent.getMatricStatus());
	            existingStudent.setWard(updatedStudent.getWard());
	            existingStudent.setQualification(updatedStudent.getQualification());

	            // Save the updated student object
	            repo.save(existingStudent);
	            
         }
	        
	 }
	 
}
