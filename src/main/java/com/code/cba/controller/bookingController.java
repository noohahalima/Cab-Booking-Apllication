package com.code.cba.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.code.cba.entity.Cab;
import com.code.cba.entity.Driver;
import com.code.cba.entity.booking;
import com.code.cba.service.*;



@Controller
@RequestMapping("/booking")
public class bookingController {

	@Autowired
	bookingservice bookingservice;
	@Autowired
	Cabservice cabservice;
	@Autowired
	Driverservice driverservice;
	@RequestMapping("showform")
	public String showForm(Model model)
	{
		//create the object of the student class
		booking booking=new booking();
		//create add the student to the model 
		model.addAttribute("booking",booking);
		return "bookcab";
	}
	
	

@RequestMapping(value="bookingform",method=RequestMethod.POST)
public String driverForm(@ModelAttribute("booking") booking thebooking)
{

	System.out.println("Id"+thebooking.getBookingId());
	if(thebooking.getBookingId()>0)
	{
		bookingservice.updatebooking(thebooking);
	}
	else
	{
		bookingservice.insertbooking(thebooking);
	}

	
	return "redirect://booking/bookinglist";
}
@RequestMapping("list")
public ModelAndView list(Model model)
{
	
	List<booking> bookings=	bookingservice.viewbooking();
	List<Driver> drivers = driverservice.viewDrivers();
	Map<String,Object> map=new HashMap<String, Object>();
	map.put("drivers", drivers);
	map.put("bookings",bookings);
	model.addAttribute("bookings",bookings);
	
	return  new ModelAndView("bookinglist",map);
}

//requestmapping for edit
@RequestMapping("editbooking/{id}")
//{id} we can get this by using @pathvariable to get the value from the url
public ModelAndView editbooking(@PathVariable ("id") int bookingId)
{

	booking booking=bookingservice.getById(bookingId);
	
	Map<String, Object> model = new HashMap<String, Object>();
	model.put("booking", booking);

	return new ModelAndView("booking-form",model);
}





@RequestMapping("cablist")
public String cablist(Model model)
{
	
	List<Cab> Cabs=	cabservice.viewCab();
	
	model.addAttribute("Cabs",Cabs);
	//retunr the view
	return "bookcab";
}

@RequestMapping(value="save",method=RequestMethod.POST)
public String SaveBooking(HttpServletRequest request, @ModelAttribute("booking") booking booking  ,Model model)
{
	String cabid=request.getParameter("cabid");
	int id=Integer.parseInt(cabid);
	Cab cab=	cabservice.getById(id);
	System.out.println("cabid"+cabid);
		booking.setCab(cab);
                if(booking.getBookingId()>0)
                	bookingservice.updatebooking(booking);
                else
                	bookingservice.insertbooking(booking);
    			
    			return "bookingconfirmation";
    			//return "redirect://booking/confirmation";
       
}

@RequestMapping("bookcab/{id}")
public String cabbooking(@PathVariable("id") int id,Model model)
{
	booking booking=new booking();
	Cab cab=	cabservice.getById(id);
	System.out.println("Id"+cab.getCabId());
	//booking.getCab().setCabId(id);
	model.addAttribute("booking",booking);
	model.addAttribute("Cab", cab);
	return "booking-form";
}

@RequestMapping("deletebooking/{id}")
//{id} we can get this by using @pathvariable to get the value from the url
public String deletebooking(@PathVariable int id,Model model)
{
	System.out.println(id);
	
	bookingservice.deletebooking(id);
	return "redirect://booking/list";
}

@RequestMapping("confirmation")
public String confirmbooking(Model model)
{
	booking booking=new booking();

	model.addAttribute("booking",booking);
	return "bookingconfirmation";
}
}
