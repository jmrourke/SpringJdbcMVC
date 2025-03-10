package main.controller;

import main.dao.TourDAO;
import main.model.Tour;
import main.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	String className = this.getClass().getSimpleName();

	@Autowired
	TourService tourService;

	@RequestMapping("/")
	public String getHome() {
		return "home";
	}

	@RequestMapping("/showtour")
	public String showTour() {
		System.out.println(className + " - invoking showTour method");
		Tour tour = tourService.getById(1);
		System.out.println(className + " - " + tour.toString());
		return "home";
	}

	@RequestMapping("/addtours")
	public String addtours() {
		System.out.println(className + " - invoking addtours method");
		boolean result = tourService.addTours();
		System.out.println(className + " - " + result);
		return "home";
	}


	
}
