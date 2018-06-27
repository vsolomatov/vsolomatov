package com.solomatoff.mvc.model.repository.musictype.specificationimplements;

import com.solomatoff.mvc.model.repository.musictype.specificationimplements.MusicTypeSpecificationByIdRange;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class MusicTypeSpecificationByIdRangeTest {

    @Test
    public void toSqlClauses() {
        MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange = new MusicTypeSpecificationByIdRange(1, 1);
        assertThat(musicTypeSpecificationByIdRange.toSqlClauses(), is("id between 1 and 1"));
    }

    @Test
    public void testToString() {
        MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange = new MusicTypeSpecificationByIdRange(1, 1);
        assertThat(musicTypeSpecificationByIdRange.toString(), is("MusicTypeSpecificationByIdRange{minId = 1, maxId = 1}"));
    }

    @Test
    public void equals() {
        MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange1 = new MusicTypeSpecificationByIdRange(1, 1);
        MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange2 = new MusicTypeSpecificationByIdRange(1, 1);
        assertThat(musicTypeSpecificationByIdRange1.equals(musicTypeSpecificationByIdRange2), is(true));

        MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange3 = new MusicTypeSpecificationByIdRange(2, 2);
        assertThat(musicTypeSpecificationByIdRange1.equals(musicTypeSpecificationByIdRange3), is(false));
    }

    @Test
    public void testHashCode() {
        MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange1 = new MusicTypeSpecificationByIdRange(1, 1);
        MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange2 = new MusicTypeSpecificationByIdRange(1, 1);
        assertThat(musicTypeSpecificationByIdRange1.hashCode(), is(musicTypeSpecificationByIdRange2.hashCode()));

        MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange3 = new MusicTypeSpecificationByIdRange(2, 2);
        assertThat(musicTypeSpecificationByIdRange1.hashCode(), not(musicTypeSpecificationByIdRange3.hashCode()));
    }
}