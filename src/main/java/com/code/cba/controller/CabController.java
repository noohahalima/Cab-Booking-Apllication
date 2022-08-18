package com.code.cba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.code.cba.entity.Cab;
import com.code.cba.entity.Driver;
import com.code.cba.service.Driverservice;
import com.code.cba.service.*;



@Controller
@RequestMapping("/Cab")
public class CabController {
	
	@Autowired
	Cabservice Cabservice;
	
	@RequestMapping("showform")
	public String showForm(Model model)
	{
		//create the object of the cab class
		Cab cab=new Cab();
		//create add the cab to the model 
		model.addAttribute("Cab",cab);
		return "Cab-form";
	}
	
	

@RequestMapping(value="Cabform",method=RequestMethod.POST)
public String driverForm(@ModelAttribute("Cab") Cab theCab)
{
	
	System.out.println("Id"+theCab.getCabId());
	if(theCab.getCabId()>0)
	{
		Cabservice.updateCab(theCab);
	}
	else
	{
		Cabservice.insertCab(theCab);
	}

	
	return "redirect://Cab/Cablist";
}
@RequestMapping("list")
public String list(Model model)
{
	
	List<Cab> Cabs=	Cabservice.viewCab();
	
	model.addAttribute("Cabs",Cabs);
	//retunr the view
	return "Cablist";
}

//requestmapping for edit
@RequestMapping("editCab/{id}")
//{id} we can get this by using @pathvariable to get the value from the url
public ModelAndView editCab(@PathVariable ("id") int CabId)
{
	//we get the cab information from the service for the id
	Cab Cab=Cabservice.getById(CabId);
	//add the cab object to the model
	Map<String, Object> model = new HashMap<String, Object>();
	model.put("Cab", Cab);
	//retunr the view
	return new ModelAndView("Cab-form",model);
}



@RequestMapping("deleteCab/{id}")
//{id} we can get this by using @pathvariable to get the value from the url
public String deleteCab(@PathVariable int id,Model model)
{
	System.out.println(id);
	Cabservice.deleteCab(id);
	
	return "redirect://Cab/list";
}


//@RequestMapping(value="save",method=RequestMethod.POST)
//public String SaveDriver(HttpServletRequest request, @ModelAttribute("Cab") Cab Cab  ,Model model)
//{
//	
//		 
//                if(Cab.getCabId()>0)
//                	Cabservice.updateCab(Cab);
//                else
//                	Cabservice.insertCab(Cab);
//
//    			return "redirect://Cab/list";
//       
//}


@RequestMapping(value="save",method=RequestMethod.POST)
public ModelAndView userSave(@Valid  @ModelAttribute("Cab") Cab Cab,BindingResult result)
{
	
	if(result.hasErrors())
	{
		System.out.println("error");
		List<Cab>	cabs=Cabservice.viewCab();
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("Cablist",  cabs);
		//model.put("user", new User());
		return new ModelAndView("Cab-form",model);
	}
	else
	{
		if(Cab.getCabId()>0)
        	Cabservice.updateCab(Cab);
       else
       	Cabservice.insertCab(Cab);

	return new ModelAndView("redirect://Cab/list");
		
	}
}
}