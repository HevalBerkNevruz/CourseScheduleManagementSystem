package org.managementsystem.controller.course;

import java.util.List;

import org.managementsystem.IDao;
import org.managementsystem.model.course.Course;

public class CourseController {
	private IDao<Course> dao;

	public CourseController(IDao<Course> dao) {
		this.dao = dao;
	}

	public IDao<Course> getDao() {
		return dao;
	}

	public void setDao(IDao<Course> dao) {
		this.dao = dao;
	}

	public List<Course> getCourseDataList() {
		return dao.getDataList();
	}

	public void setCourseData(Course course) {
		dao.setData(course);
	}

	public void courseUpdate(Course course) {
		dao.updateData(course);
	}
}
