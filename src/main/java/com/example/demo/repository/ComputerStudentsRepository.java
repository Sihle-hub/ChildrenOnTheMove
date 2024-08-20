package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.ComputerStudent;



public interface ComputerStudentsRepository extends JpaRepository<ComputerStudent, String>{

}
