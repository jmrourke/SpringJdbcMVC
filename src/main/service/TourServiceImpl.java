package main.service;

import main.dao.TourDAO;
import main.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TourServiceImpl implements TourService{

	String className = this.getClass().getSimpleName();

	@Autowired
	private TourDAO tourDAO;

	@Override
	public Tour getById(int id) {
		System.out.println(className + " - invoking service getById method");
		return tourDAO.getById(id);
	}

	@Override
	public boolean addTours() {
		System.out.println(className + " - invoking service addTours method");
		tourDAO.addTours();
		return false;
	}
}
