package com.br.labspringboot3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.labspringboot3.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
