package com.bulain.struts.controller;

import java.util.List;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.common.model.Person;
import com.bulain.common.pojo.PersonSearch;
import com.bulain.common.service.PersonService;

public class PersonAction extends PageSupportActionSupport {
    private static final long serialVersionUID = 4764266080241908757L;

    private Long id;

    private PersonSearch search;
    private Person person;
    private List<Person> listPerson;

    private transient PersonService personService;

    public String list() {
        search = (PersonSearch) getSearchFromSession(PersonSearch.class, search);
        page = getPageFromSession();

        listPerson = personService.page(search, page);

        putSearchToSession(PersonSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        person = new Person();
        return SUCCESS;
    }
    public String create() {
        personService.insert(person);
        return SUCCESS;
    }
    public String show() {
        person = personService.get(id);
        return SUCCESS;
    }
    public String edit() {
        person = personService.get(id);
        return SUCCESS;
    }
    public String update() {
        personService.update(person, false);
        return SUCCESS;
    }
    public String destroy() {
        personService.delete(id);
        return SUCCESS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonSearch getSearch() {
        return search;
    }

    public void setSearch(PersonSearch search) {
        this.search = search;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Person> getListPerson() {
        return listPerson;
    }

    public void setListPerson(List<Person> listPerson) {
        this.listPerson = listPerson;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
