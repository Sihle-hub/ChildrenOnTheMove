

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ComputerStudent;
import com.example.demo.domain.Student;
import com.example.demo.repository.ComputerStudentsRepository;


@Service
public class ComputerStudentsService {

	
	@Autowired
	private ComputerStudentsRepository repo;
	
	public List<ComputerStudent> listAll(){
		
		  return repo.findAll();
	}
	
	public void save(ComputerStudent msc) {
		
		  repo.save(msc);
	}
	
	
	public void delete(ComputerStudent msc) {
		
		repo.delete(msc);
		
	}
	
	 public void update(String id , Student updatedStudent) {
	       
		 Optional<ComputerStudent> student = repo.findById(id);
		 
		 if (student.isPresent()) {
			 
			 ComputerStudent existingStudent = student.get();

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


