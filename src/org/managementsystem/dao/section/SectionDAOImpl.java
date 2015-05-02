package org.managementsystem.dao.section;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.managementsystem.dao.IDao;
import org.managementsystem.HibernateUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collections;
import java.util.List;

public class SectionDAOImpl implements IDao<Section> {
	Session session = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Section> getDataList() {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Section.class);
			List<Section> sectionDataList = Collections.checkedList(
					criteria.list(), Section.class);
			return sectionDataList;
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Database Error",
							"Databasedeki Verilere Eri�ilemiyor"));
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public void setData(Section t) {
		Section section = new Section(t.getFacultyNo(), t.getSectionNo(),
				t.getSectionName());
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(section);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Database Error",
							"Veri Eklenirken Bir Sorun Olu�tu"));
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void updateData(Section t) {
		Section section=new Section(t.getFacultyNo(),t.getSectionNo(),t.getSectionName());
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(section);
			session.getTransaction().commit();
		}catch (HibernateException e){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Database Error",
							"Veri Güncellenirken Bir Sorun Oluştu"));
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteData(Section t) {
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(t);
			session.getTransaction().commit();
		}catch (HibernateException e){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Database Error",
							"Veri Silinirken Bir Sorun Oluştu"));
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}
}
