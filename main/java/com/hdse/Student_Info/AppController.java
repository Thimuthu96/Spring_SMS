package com.hdse.Student_Info;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private UserRepository repo;
	@Autowired
	private ResultRepository resultRepo;
	@Autowired
	private AdminService service;
	
	@GetMapping("")
	public String ViewHomePage() {
		return "index";
	}
	
	@GetMapping("/registration")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		/*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);*/
		repo.save(user);
		return "registration_success";
	}
	
	//temp
	@PostMapping("/student_dashboard")
	public String processlogin(User user) {
		return "student";
	}
        
        //temp
	@GetMapping("/dashboard")
	public String backToDashboard() {
		return "student";
	}
        
        //temp
        @GetMapping("/logout")
        public String logOut(){
        return "index";
        }
        
        //temp
	@GetMapping("/admin_panel")
	public String backToAdminDashboard() {
		return "admin";
	}
        
	
	//temp
	@GetMapping("/adminlogin")
	public String processAdminLogin() {
		return "admin_login";
	}
	
	//temp
	@PostMapping("/admin_dashboard")
	public String processAdminDashboard(Admin admin) {
		return "admin";
	}
	
	@RequestMapping("/student_list")
	public String viewStudentDetails(Model model) {
		List<User> listStudent = service.listAll();
		model.addAttribute("listStudent", listStudent);
		
		return "view_students";
	}
	
	//addnew result page
	@GetMapping("/add_new_results")
	public String addNewResults(Model model) {
		model.addAttribute("result", new Result());
		return "new_results";
	}
	
	//save result
	@PostMapping("/save_result")
	public String saveResults(Result result) {
		resultRepo.save(result);
		return "redirect:/update_results";
	}
	
	@RequestMapping("/update_results")
	public String viewUpdatePage(Model model) {
		List<Result> listResult = service.listAllResults();
		model.addAttribute("listResult", listResult);
		
		return "result_update";
	}
	
	//update results row
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditResultForm(@PathVariable(name = "id") Long id) {
		ModelAndView modelAndView = new ModelAndView("edit_result");
		Result result = service.get(id);
		modelAndView.addObject("result", result);
		return modelAndView;
	}
	
	//delete results row
	@RequestMapping("/delete/{id}")
	public String deleteResult(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/update_results";
	}
	
	//all results
	@RequestMapping("/my_results")
	public String viewResultPage(Model model) {
		List<Result> listResult = service.listAllResults();
		model.addAttribute("listResult", listResult);
		
		return "allresults";
	}
	
	//find all results by keyword
	@RequestMapping("/check_result")
	public String showMyResult(Model model, @Param("fName") String fName,@Param("sGrade") String sGrade,@Param("subj") String subj,@Param("month") String month) {
		List<Result> listResults = service.listAllbyName(fName,sGrade,subj,month);
		model.addAttribute("listResults", listResults);
		
		return "viewResult";
	}
	
}
