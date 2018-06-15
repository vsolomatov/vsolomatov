package com.solomatoff.chaptertrainee003.search;

import org.junit.Test;
import java.util.List;
import java.util.ListIterator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Ivan", "Ivanov", "247124", "Petrozavodsk")
        );
        phones.add(
                new Person("Vyacheslav", "Solomatov", "2050563", "Ekaterinburg")
        );

        List<Person> persons = phones.find("etr");

        /*for (Person person : persons) {
           //System.out.println(person.getSurname());
        }*/

        ListIterator<Person> myiterator = persons.listIterator();

        assertThat(myiterator.next().getSurname(), is("Arsentev"));

        assertThat(myiterator.next().getSurname(), is("Ivanov"));

        assertThat(myiterator.hasNext(), is(false));
    }
}