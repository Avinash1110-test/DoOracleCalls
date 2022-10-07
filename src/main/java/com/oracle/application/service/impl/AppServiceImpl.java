package com.oracle.application.service.impl;

import com.oracle.application.exception.ServiceException;
import com.oracle.application.model.UserResponseDTO;
import com.oracle.application.service.AppService;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    /* Call Stored Procedure from database */
    @Override
    public String getUserPrimaryAddress(String userId) {

        /* configure stored procedure with procedure name.
        * Note : If we have procedure in package then - package_name.procedure_name */
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("get_user_primary_address");

        /* register mode (IN/OUT/INOUT) and datatype of parameters by using parameter positions (i.e., first[1], second[2], third[3] etc,.).
        * here we have total three parameters - one is IN and two are OUT parameters (Please check stored procedure for more details) */
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);

        /* now set values for IN parameters */
        storedProcedureQuery.setParameter(1, userId);

        /* execute procedure */
        storedProcedureQuery.execute();

        /* after executing get values from OUT parameters */
        String output = (String) storedProcedureQuery.getOutputParameterValue(2);
        String errMsg = (String) storedProcedureQuery.getOutputParameterValue(3);

        /* if procedure returns any error then throwing an exception */
        if (errMsg != null) {
            logger.info("AppService - getUserPrimaryAddress called 'get_user_primary_address' stored procedure, procedure returned an error: {}", errMsg);
            throw new ServiceException(errMsg, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        logger.info("AppService - getUserPrimaryAddress is called successfully with input: {}", userId);
        return output;
    }

    //to fetch any one or all column, but only one row (i.e., select * from table where id = xxxx)
    @Override
    public Object fetchSingleRowFromSelectQuery(String query) {

        logger.info("AppService - fetchSingleRowFromSelectQuery is called with input: {}", query);
        Query query1 = entityManager.createNativeQuery(query);
        return query1.getSingleResult();
    }

    //to fetch any particular column (one or more rows & columns) data (i.e., select column1, column2 from table)
    @Override
    public String fetchDataFromSelectQuery(String query) {

        logger.info("AppService - fetchDataFromSelectQuery is called with input: {}", query);
        Query query1 = entityManager.createNativeQuery(query);
        return query1.getResultList().toString();
    }

    //to fetch complete table data, one row or more than one row (i.e., select * from table)
    @Override
    public List<UserResponseDTO> fetchAllColumnsDataFromSelectQuery(String query) {

        logger.info("AppService - fetchAllColumnsDataFromSelectQuery is called with input: {}", query);

        Query query1 = entityManager.createNativeQuery(query);
        //map query result to a non-entity pojo class
        query1.unwrap(SQLQuery.class)
                .addScalar("user_id", StringType.INSTANCE)
                .addScalar("first_name", StringType.INSTANCE)
                .addScalar("last_name", StringType.INSTANCE)
                .addScalar("dob", StringType.INSTANCE)
                .addScalar("doj", StringType.INSTANCE)
                .addScalar("email_address", StringType.INSTANCE)
                .addScalar("contact_no", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(UserResponseDTO.class));
        return query1.getResultList();
    }
}
