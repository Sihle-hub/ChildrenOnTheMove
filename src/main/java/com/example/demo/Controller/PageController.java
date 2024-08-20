package com.example.demo.Controller;



import com.example.demo.domain.ComputerStudent;
import com.example.demo.domain.RetailStudent;
import com.example.demo.domain.Student;
import com.example.demo.repository.ComputerStudentsRepository;
import com.example.demo.repository.RetailStudentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ComputerStudentsService;
import com.example.demo.service.RetailStudentService;
import com.example.demo.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepo;
    
    @Autowired
    private RetailStudentService retailService;
    
    @Autowired
    private RetailStudentRepository retailRepo;
    
    @Autowired
    private ComputerStudentsService computerService;
    
    @Autowired
    private ComputerStudentsRepository computerRepo;
    

    @GetMapping("/loginAdmin")
    public String loginAdmin() {
        return "login.html";
    }

    @GetMapping("/admin")
    public String admin(HttpSession session) {
       
        if (session.getAttribute("loggedInUser") != null) {
            return "adminDashboard.html";
        } else {
          
            return "redirect:/loginAdmin";
        }
    }

    @PostMapping("/adminDashboard")
    public String adminDashboard(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 HttpSession session) {
        String userMamzo = "mamzo", userBoss = "boss";
        String passwordMamzo = "mamzo123", passwordBoss = "boss123";

       
        if ((username.equals(userMamzo) && password.equals(passwordMamzo)) ||
                (username.equals(userBoss) && password.equals(passwordBoss))) {
          
            session.setAttribute("loggedInUser", username);
            return "redirect:/admin";
        } else {
          
            return "redirect:/loginAdmin";
        }
    }

    @PostMapping("/register-student")
    public String registerStudent(@RequestBody Map<String, String> requestBody) {
    	
    	 String name = requestBody.get("name");
    	 String surname = requestBody.get("surname");
    	 String studentId = requestBody.get("id");
    	 String address = requestBody.get("address");
    	 String gender = requestBody.get("get_gender");
    	 String welfare = requestBody.get("welfare");
    	 String matric = requestBody.get("matric");
    	 String ward = requestBody.get("ward");
    	 int wardNo = Integer.parseInt(ward);
    	 String qualification = requestBody.get("qualification");
    	 String program = requestBody.get("program");
    	 String contacts = requestBody.get("contact");
    	

    	String[] initials = name.split(" ");
    	String initial = "";

    	for(int i = 0 ; i < initials.length ; i++) {
    		
    		initial += initials[i].substring(0,1);
    	}
    	
  
        Student student = new Student(studentId, name, surname, contacts, address, gender, welfare, matric, wardNo,
                qualification,program , initial);

        studentService.save(student);

        return "redirect:/admin";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@RequestParam("update-name") String name,
                                @RequestParam("update-surname") String surname,
                                @RequestParam("update-contacts") String contacts,
                                @RequestParam("update-identity-number-register") String id_number,
                                @RequestParam("oldId") String oldId,
                                @RequestParam("update-address") String address,
                                @RequestParam("update-type-welfare") String welfare,
                                @RequestParam("update-matric-status") String matric,
                                @RequestParam("update-ward-number") int ward,
                                @RequestParam("update-qualification") String qualification , @RequestParam("update-program") String program) {

    	String[] initials = name.split(" ");
    	String initial = "";

    	for(int i = 0 ; i < initials.length ; i++) {
    		
    		initial += initials[i].substring(0,1);
    		
    	}
    	
        Student student = new Student();
        
          student.setName(name);
          student.setId(id_number);
          student.setSurname(surname);
          student.setContact(contacts);
          student.setAddress(address);
          student.setTypeWelfare(welfare);
          student.setMatricStatus(matric);
          student.setWard(ward);
          student.setQualification(qualification);
          student.setProgram(program);
          student.setInitial(initial);
  
        studentService.update(oldId, student);

        return "redirect:/admin";
    }

    @GetMapping("/findStudent")
    @ResponseBody
    public List<Student> getAllUsers() {
        return studentRepo.findAll();
    }

    @PostMapping("/findUpdateStudent")
    @ResponseBody
    public List<Student> findUpdateStudent(@RequestBody Map<String, String> requestBody) {
      
        Student students =  new Student();
        
        List<Student> stud = new ArrayList<>();

        String name = requestBody.get("name");
        
        if(name.equals("Life")) {
        	
        	stud = studentRepo.findAll();
        }
        
        else {
        	
        	 if(name.equals("Computer")) {
             	
        		 List<ComputerStudent> cStudents = computerRepo.findAll();
        	    	
        	    	for(ComputerStudent i : cStudents) {
        	    		
        	    		students.setName(i.getName());
        	    		students .setSurname(i.getSurname());
        	    		students.setId(i.getId());
        	    		students.setAddress(i.getAddress());
        	    		students.setContact(i.getContact());
        	    		students.setGender(i.getGender());
        	    		students.setInitial(i.getInitial());
        	    		students.setMatricStatus(i.getMatricStatus());
        	    		students.setProgram(i.getProgram());
        	    		students.setQualification(i.getQualification());
        	    		students.setTypeWelfare(i.getTypeWelfare());
        	    		students.setWard(i.getWard());
        	    		
        	    		stud.add(students);

        	    	}
             }
        	 
        	 else {
        	 
        		 if(name.equals("Retail")) {
                  	
            		 List<RetailStudent> cStudents = retailRepo.findAll();
            	    	
            	    	for(RetailStudent i : cStudents) {
            	    		
            	    		students.setName(i.getName());
            	    		students .setSurname(i.getSurname());
            	    		students.setId(i.getId());
            	    		students.setAddress(i.getAddress());
            	    		students.setContact(i.getContact());
            	    		students.setGender(i.getGender());
            	    		students.setInitial(i.getInitial());
            	    		students.setMatricStatus(i.getMatricStatus());
            	    		students.setProgram(i.getProgram());
            	    		students.setQualification(i.getQualification());
            	    		students.setTypeWelfare(i.getTypeWelfare());
            	    		students.setWard(i.getWard());
            	    		
            	    		stud.add(students);

            	    	}
                 }
        		 
        	 }
        	
        }
        
        return stud;

    }
    
    @PostMapping("/findSearchStudent")
    @ResponseBody
    public List<Student> findSearchStudent(@RequestBody Map<String, String> requestBody) {
      
        Student students =  new Student();
        
        List<Student> stud = new ArrayList<>();

        String name = requestBody.get("name");
        
        if(name.equals("Life")) {
        	
        	stud = studentRepo.findAll();
        }
        
        else {
        	
        	 if(name.equals("Computer")) {
             	
        		 List<ComputerStudent> cStudents = computerRepo.findAll();
        	    	
        	    	for(ComputerStudent i : cStudents) {
        	    		
        	    		students.setName(i.getName());
        	    		students .setSurname(i.getSurname());
        	    		students.setId(i.getId());
        	    		students.setAddress(i.getAddress());
        	    		students.setContact(i.getContact());
        	    		students.setGender(i.getGender());
        	    		students.setInitial(i.getInitial());
        	    		students.setMatricStatus(i.getMatricStatus());
        	    		students.setProgram(i.getProgram());
        	    		students.setQualification(i.getQualification());
        	    		students.setTypeWelfare(i.getTypeWelfare());
        	    		students.setWard(i.getWard());
        	    		
        	    		stud.add(students);

        	    	}
             }
        	 
        	 else {
        	 
        		 if(name.equals("Retail")) {
                  	
            		 List<RetailStudent> cStudents = retailRepo.findAll();
            	    	
            	    	for(RetailStudent i : cStudents) {
            	    		
            	    		students.setName(i.getName());
            	    		students .setSurname(i.getSurname());
            	    		students.setId(i.getId());
            	    		students.setAddress(i.getAddress());
            	    		students.setContact(i.getContact());
            	    		students.setGender(i.getGender());
            	    		students.setInitial(i.getInitial());
            	    		students.setMatricStatus(i.getMatricStatus());
            	    		students.setProgram(i.getProgram());
            	    		students.setQualification(i.getQualification());
            	    		students.setTypeWelfare(i.getTypeWelfare());
            	    		students.setWard(i.getWard());
            	    		
            	    		stud.add(students);

            	    	}
                 }
        		 
        	 }
        	
        }
        
        return stud;

    }
    
    @PostMapping("/findDeleteStudent")
    @ResponseBody
    public List<Student> findDeleteStudent(@RequestBody Map<String, String> requestBody) {
      
        Student students =  new Student();
        
        List<Student> stud = new ArrayList<>();

        String name = requestBody.get("name");
        
        if(name.equals("Life")) {
        	
        	stud = studentRepo.findAll();
        }
        
        else {
        	
        	 if(name.equals("Computer")) {
             	
        		 List<ComputerStudent> cStudents = computerRepo.findAll();
        	    	
        	    	for(ComputerStudent i : cStudents) {
        	    		
        	    		students.setName(i.getName());
        	    		students .setSurname(i.getSurname());
        	    		students.setId(i.getId());
        	    		students.setAddress(i.getAddress());
        	    		students.setContact(i.getContact());
        	    		students.setGender(i.getGender());
        	    		students.setInitial(i.getInitial());
        	    		students.setMatricStatus(i.getMatricStatus());
        	    		students.setProgram(i.getProgram());
        	    		students.setQualification(i.getQualification());
        	    		students.setTypeWelfare(i.getTypeWelfare());
        	    		students.setWard(i.getWard());
        	    		
        	    		stud.add(students);

        	    	}
             }
        	 
        	 else {
        	 
        		 if(name.equals("Retail")) {
                  	
            		 List<RetailStudent> cStudents = retailRepo.findAll();
            	    	
            	    	for(RetailStudent i : cStudents) {
            	    		
            	    		students.setName(i.getName());
            	    		students .setSurname(i.getSurname());
            	    		students.setId(i.getId());
            	    		students.setAddress(i.getAddress());
            	    		students.setContact(i.getContact());
            	    		students.setGender(i.getGender());
            	    		students.setInitial(i.getInitial());
            	    		students.setMatricStatus(i.getMatricStatus());
            	    		students.setProgram(i.getProgram());
            	    		students.setQualification(i.getQualification());
            	    		students.setTypeWelfare(i.getTypeWelfare());
            	    		students.setWard(i.getWard());
            	    		
            	    		stud.add(students);

            	    	}
                 }
        		 
        	 }
        	
        }
        
        return stud;

    }

    @PostMapping("/delete-student")
    @ResponseBody
    public void deleteStudent(@RequestBody Map<String, String> requestBody) {
        String studentId = requestBody.get("id");
        String program = requestBody.get("iProgram");
        
        if(program.equals("Life")) {
        	
        	Student student = new Student();
            student.setId(studentId);
            studentRepo.delete(student);
            
            
        }
 
        else {
        	
        	if(program.equals("Computer")) {
        		
        		ComputerStudent student = new ComputerStudent();
        		student.setId(studentId);
        		computerRepo.delete(student);
        		
        	}
        	
        	else {
        		
        		if(program.equals("Retail")) {
        			
        		   RetailStudent student = new RetailStudent();
        		   student.setId(studentId);
        		   retailRepo.delete(student);
        			 
        		}
        	}
        	
        }
        
        
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
       
        session.invalidate();

        return "redirect:/loginAdmin";
    }
    

  
    @PostMapping("/register-retail")
    @ResponseBody
    public String registerRetailStudent(@RequestBody Map<String, String> requestBody) {
    	
    	
   	 String name = requestBody.get("name");
   	 String surname = requestBody.get("surname");
   	 String studentId = requestBody.get("id");
   	 String address = requestBody.get("address");
   	 String gender = requestBody.get("get_gender");
   	 String welfare = requestBody.get("welfare");
   	 String matric = requestBody.get("matric");
   	 String ward = requestBody.get("ward");
   	 int wardNo = Integer.parseInt(ward);
   	 String qualification = requestBody.get("qualification");
   	 String program = requestBody.get("program");
   	 String contacts = requestBody.get("contact");
    	
    	String[] initials = name.split(" ");
    	String initial = "";

    	for(int i = 0 ; i < initials.length ; i++) {
    		
    		initial += initials[i].substring(0,1);
    	}
    	
        RetailStudent student = new RetailStudent(studentId, name, surname, contacts, address, gender, welfare, matric, wardNo,
                qualification, "Retail Management" , initial);

        retailService.save(student);

        return "redirect:/admin";
    }
    
    
    @PostMapping("/register-computer")
    @ResponseBody
    public String registerComputerStudent(@RequestBody Map<String, String> requestBody) {
    	
    	
   	 String name = requestBody.get("name");
   	 String surname = requestBody.get("surname");
   	 String studentId = requestBody.get("id");
   	 String address = requestBody.get("address");
   	 String gender = requestBody.get("get_gender");
   	 String welfare = requestBody.get("welfare");
   	 String matric = requestBody.get("matric");
   	 String ward = requestBody.get("ward");
   	 int wardNo = Integer.parseInt(ward);
   	 String qualification = requestBody.get("qualification");
   	 String contacts = requestBody.get("contact");
    	
    	String[] initials = name.split(" ");
    	String initial = "";

    	for(int i = 0 ; i < initials.length ; i++) {
    		
    		initial += initials[i].substring(0,1);
    	}
    	
        ComputerStudent student = new ComputerStudent(studentId, name, surname, contacts, address, gender, welfare, matric, wardNo,
                qualification, "Computer Certificate" , initial);

        computerService.save(student);

        return "redirect:/admin";
    }
    
    @PostMapping("/dd")
    @ResponseBody
    public String registerPointStudent(@RequestBody Map<String, String> requestBody) {
    	
    	
   	 String name = requestBody.get("name");
   	 String surname = requestBody.get("surname");
   	 String studentId = requestBody.get("id");
   	 String address = requestBody.get("address");
   	 String gender = requestBody.get("get_gender");
   	 String welfare = requestBody.get("welfare");
   	 String matric = requestBody.get("matric");
   	 String ward = requestBody.get("ward");
   	 int wardNo = Integer.parseInt(ward);
   	 String qualification = requestBody.get("qualification");
   	 String contacts = requestBody.get("contact");
    	
    	String[] initials = name.split(" ");
    	String initial = "";

    	for(int i = 0 ; i < initials.length ; i++) {
    		
    		initial += initials[i].substring(0,1);
    	}
    	
        RetailStudent student = new RetailStudent(studentId, name, surname, contacts, address, gender, welfare, matric, wardNo,
                qualification, "Computer Certificate" , initial);

        retailService.save(student);

        return "redirect:/admin";
    }
    
    
    
    
    @GetMapping("/fetch_retail_students")
    @ResponseBody
    public List<RetailStudent> getAllRetailStudents() {
    	
        return retailRepo.findAll();
    }

    @GetMapping("/fetch_computer_students")
    @ResponseBody
    public List<ComputerStudent> getAllComputertudents() {
    	
        return computerRepo.findAll();
    }
    

}

