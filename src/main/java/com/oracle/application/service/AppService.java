package com.oracle.application.service;

import com.oracle.application.model.UserResponseDTO;

import java.util.List;

public interface AppService {

    String getUserPrimaryAddress(String userId);

    Object fetchSingleRowFromSelectQuery(String query);

    String fetchDataFromSelectQuery(String query);

    List<UserResponseDTO> fetchAllColumnsDataFromSelectQuery(String query);
}
