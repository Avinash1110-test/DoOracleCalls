package com.oracle.application.service.impl;

import com.oracle.application.exception.ServiceException;
import com.oracle.application.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

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
}
