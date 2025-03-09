package main.controller;

import main.dao.TourDAO;
import main.model.Tour;
import main.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	TourService tourService;

	@RequestMapping("/")
	public String getHome() {
		return "home";
	}

	@RequestMapping("/showtour")
	public String showTour() {
		System.out.println("invoking showTour method");
		Tour tour = tourService.getById(1);
		return "home";
	}

	
}
