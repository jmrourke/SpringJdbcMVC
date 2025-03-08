package main.controller;

import main.dao.TourDAO;
import main.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	TourDAO tourDAO;

	@RequestMapping("/")
	public String getHome() {
		return "home";
	}

	@RequestMapping("/addTour")
	public String addTour() {
		System.out.println("invoking addTour method");
		Tour tour = tourDAO.getById(1);
		return "home";
	}

	
}
