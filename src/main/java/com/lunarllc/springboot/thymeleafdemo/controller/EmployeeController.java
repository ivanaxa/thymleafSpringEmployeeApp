package com.lunarllc.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lunarllc.springboot.thymeleafdemo.entity.Employee;
import com.lunarllc.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	/* Used to test in memory employee code
	//load employee data
	private List<Employee> theEmployees;
	
	@PostConstruct
	private void loadData() {
		//create employee
		Employee empl = new Employee(1, "Emp FirstName", "Emp LastName", "test@gmail.com");
		Employee empl2 = new Employee(2, "Emp FirstName2", "Emp LastName2", "test2@gmail.com");
		Employee empl3 = new Employee(3, "Emp FirstName3", "Emp LastName3", "test3@gmail.com");
		Employee empl4 = new Employee(4, "Emp FirstName4", "Emp LastName4", "test4@gmail.com");
		Employee empl5 = new Employee(5, "Emp FirstName5", "Emp LastName5", "test5@gmail.com");
		Employee empl6 = new Employee(6, "Emp FirstName6", "Emp LastName6", "test6@gmail.com");
		
		//create the list
		theEmployees = new ArrayList<>();
		//add to the list
		theEmployees.add(empl);
		theEmployees.add(empl2);
		theEmployees.add(empl3);
		theEmployees.add(empl4);
		theEmployees.add(empl5);
		theEmployees.add(empl6);
		
	}
	*/
	
	private EmployeeService employeeService;
	
	//@Autowired is optional since only one constructor
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	//add mapping for "/list"
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		//get employees from database
		List<Employee> theEmployees = employeeService.findAll(); 
		//add to the spring model
		theModel.addAttribute("employees", theEmployees);
		//add theDate
		theModel.addAttribute("theDate", new java.util.Date());
		
		return "employees/list-employees";
		}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		//create model attribute to bind form data
		Employee theEmployee= new Employee();
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
		//return the template for this form
		//src/main/resources/templates/employees/employee-form.html
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
			Model theModel) {
		
		//get employee from service
		Employee theEmployee= employeeService.findById(theId);
		//set employee as a model attribtue to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		//send over to our form
		return "employees/employee-form";
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		//save the employee
		employeeService.save(theEmployee);
		
		//use redirect to prevent duplicate submission
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		//delete the employee
		employeeService.deleteById(theId);
		
		//use redirect to prevent duplicate submission
		return "redirect:/employees/list";
	}
	
	
	
}
	
