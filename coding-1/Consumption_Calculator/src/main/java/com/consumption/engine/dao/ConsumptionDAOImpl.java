package com.consumption.engine.dao;


import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.consumption.engine.pojos.ConsumptionInfo;
import com.consumption.engine.pojos.ConsumptionReport;
import com.consumption.engine.pojos.PlaceInfo;
import com.consumption.engine.pojos.Villages;

@Component
public class ConsumptionDAOImpl implements ConsumptionDAO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional
	public void addCounter(PlaceInfo place) {
		 
		Session session = sessionFactory.getCurrentSession();
		logger.info("Proceeding to add into database, Place Information = " +place);
		logger.info("Place Information = " +place.getCounterid());
		logger.info("Place Information = " +place.getCountername());
		
			 session.save(place);
		
	}

	@Override
	public PlaceInfo findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		logger.info("Place Information = " +id);
		PlaceInfo placeInfo=(PlaceInfo) session.get(PlaceInfo.class,id);
		return placeInfo;
	}

	@Override
	public void addConsumption(ConsumptionInfo consumptionInfo) {
		Session session = sessionFactory.getCurrentSession();
		logger.info("Information of the amount consumption saved successfully= " +consumptionInfo);

		Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		consumptionInfo.setCreateDateTime(ts);

		session.save(consumptionInfo);
	}

	@Override
	public ConsumptionReport getAll(String duration) {
		Session session = sessionFactory.getCurrentSession();
		logger.info("Retreiving from the database");

		duration = duration.replaceAll("\\D+","");
		int durationInt = Integer.parseInt(duration);

		Timestamp timestampUpperLimit = Timestamp.valueOf(LocalDateTime.now());
		Timestamp timestampLowerLimit = Timestamp.valueOf(LocalDateTime.now().minusHours(durationInt));


		StringBuilder finalSQlFire = new StringBuilder();
		finalSQlFire
		.append("select sum(b.consumption) as consumption, a.name from placeinfo a  LEFT JOIN consumptioninfo b ")
		.append("ON  a.id=b.id where b.tmsmtp >")
		.append("\'")
		.append(timestampLowerLimit)
		.append("\'")
		.append(" AND ")
		.append("b.tmsmtp < \'")
		.append(timestampUpperLimit)
		.append("\'")
		.append("GROUP BY a.name");

		logger.info("Final SQL query " +finalSQlFire);

		Query query = session.createSQLQuery(finalSQlFire.toString()).setResultTransformer(Transformers.aliasToBean(Villages.class));

		List<Villages> villageList = query.list();
		ConsumptionReport consumptionReport = new ConsumptionReport();
		consumptionReport.setVillages(villageList);

		return consumptionReport;
	}


}
