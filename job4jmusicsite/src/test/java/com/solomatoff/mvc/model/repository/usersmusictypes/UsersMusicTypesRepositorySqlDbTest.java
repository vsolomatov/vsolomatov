package com.solomatoff.mvc.model.repository.usersmusictypes;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.repository.SqlSpecification;
import com.solomatoff.mvc.model.repository.usersmusictypes.UsersMusicTypesRepository;
import com.solomatoff.mvc.model.repository.usersmusictypes.UsersMusicTypesRepositoryDb;
import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationAllUsersMusicTypes;
import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationByIdId;
import com.solomatoff.mvc.model.store.DbStore;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UsersMusicTypesRepositorySqlDbTest {
    private final UsersMusicTypesRepository usersMusicTypesRepository = new UsersMusicTypesRepositoryDb();
    private final static PersistentDAO DB_STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    private List<UsersMusicTypes> makeTestUsersMusicTypesAll() {
        return DB_STORE.findAllUsersMusicTypes(new UsersMusicTypes());
    }

    @Test
    public void addUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(3);
        usersMusicTypes.setMusicTypeId(1);
        usersMusicTypesRepository.addUsersMusicTypes(usersMusicTypes);

        SqlSpecification specification = new UsersMusicTypesSpecificationByIdId(3, 1);
        List result = usersMusicTypesRepository.query(specification);
        UsersMusicTypes usersMusicTypesResult = (UsersMusicTypes) result.get(0);
        assertThat(usersMusicTypesResult.getMusicTypeId(), is(1));
    }

    @Test
    public void removeUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(3);
        usersMusicTypes.setMusicTypeId(3);

        usersMusicTypesRepository.removeUsersMusicTypes(usersMusicTypes);

        SqlSpecification specification = new UsersMusicTypesSpecificationByIdId(3, 3);
        List result = usersMusicTypesRepository.query(specification);
        //System.out.println("result = " + result);

        assertEquals(result.size(), 0);
    }

    @Test
    public void updateUsersMusicTypes() {
        SqlSpecification specification = new UsersMusicTypesSpecificationByIdId(3, 3);
        List usersMusicTypeslist = usersMusicTypesRepository.query(specification);
        UsersMusicTypes usersMusicTypes = (UsersMusicTypes) usersMusicTypeslist.get(0);
        usersMusicTypes.setUserId(3);
        usersMusicTypes.setMusicTypeId(3);
        usersMusicTypesRepository.updateUsersMusicTypes(usersMusicTypes);

        List result = usersMusicTypesRepository.query(specification);
        UsersMusicTypes usersMusicTypesResult = (UsersMusicTypes) result.get(0);

        assertThat(usersMusicTypesResult.getMusicTypeId(), is(3));
    }

    @Test
    public void query() {
        SqlSpecification specification = new UsersMusicTypesSpecificationByIdId(1, 1);
        List result = usersMusicTypesRepository.query(specification);
        //System.out.println("result = " + result);
        UsersMusicTypes usersMusicTypesResult = (UsersMusicTypes) result.get(0);
        assertThat(usersMusicTypesResult.getMusicTypeId(), is(1));

        List<UsersMusicTypes> expected = makeTestUsersMusicTypesAll();
        //System.out.println("expected = " + expected);
        specification = new UsersMusicTypesSpecificationAllUsersMusicTypes();
        result = usersMusicTypesRepository.query(specification);
        assertThat(result, is(expected));
    }
}