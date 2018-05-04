package com.solomatoff.chaptertrainee003.department;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OrganisationTest {
    @Test
    public void whenSortAscending() {
        String[] original = new String[] {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};

        DepartmentComparator departmentComparator = new DepartmentComparator();

        Organisation newStructure = new Organisation(original);
        String[] result = newStructure.sort(departmentComparator);

        String[] expected = new String[] {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};

        assertThat(result, is(expected));
    }
    @Test
    public void whenSortingInDecreasingOrder() {
        String[] original = new String[] {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};

        DepCompDescendingly departmentComparator = new DepCompDescendingly();

        Organisation newStructure = new Organisation(original);
        String[] result = newStructure.sort(departmentComparator);

        String[] expected = new String[] {"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};

        assertThat(result, is(expected));
    }
}
