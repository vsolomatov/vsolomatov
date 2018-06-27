package com.solomatoff.mvc.model.repository;

import com.solomatoff.mvc.entities.User;

public interface SqlSpecification {
    String toSqlClauses();
}
