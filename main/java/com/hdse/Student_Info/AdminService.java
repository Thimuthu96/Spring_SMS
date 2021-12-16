package com.hdse.Student_Info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Autowired
	private ResultRepository resultRepository;
	@Autowired
	private UserRepository repo;

	
	public List<User> listAll(){
		return repo.findAll();
	}
	
	public List<Result> listAllResults(){
		return resultRepository.findAll();
	}
	
	public List<Result> listAllbyName(String fName,String sGrade,String subj,String month){
		return resultRepository.findByKeyword(fName,sGrade,subj,month);
	}
	
	public void save(Result result) {
		resultRepository.save(result);
	}
	
	public Result get(Long id) {
		return resultRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		resultRepository.deleteById(id);
	}
	

}
