package com.solomatoff.mvc.model.repository.user.specificationimplements;

import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.Objects;

public class UserSpecificationByIdRange implements SqlSpecification {
    private int minId;
    private int maxId;

    public UserSpecificationByIdRange(int minId, int maxId) {
        this.minId = minId;
        this.maxId = maxId;
    }

    @Override
    public String toSqlClauses() {
        return String.format("id between %s and %s", minId, maxId);
    }

    @Override
    public String toString() {
        return "UserSpecificationByIdRange{minId = " + minId + ", maxId = " + maxId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSpecificationByIdRange that = (UserSpecificationByIdRange) o;
        return minId == that.minId && maxId == that.maxId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minId, maxId);
    }
}
