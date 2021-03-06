package org.managementsystem.dao.user;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.managementsystem.dao.IDao;
import org.managementsystem.HibernateUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collections;
import java.util.List;

public class UserDAOImpl implements IDao<User> {
	Session session = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getDataList() {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			List<User> userDataList = Collections.checkedList(criteria.list(),
					User.class);
			return userDataList;
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
	public void setData(User t) {
		User user = new User(t.getSectionNo(), t.getUserNo(), t.getUserMail(),
				t.getPassword());
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
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
	public void updateData(User t) {
		User user=new User(t.getSectionNo(),t.getUserNo(),t.getUserMail(),t.getPassword());
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(user);
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
	public void deleteData(User t) {
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

		}
	}

}
