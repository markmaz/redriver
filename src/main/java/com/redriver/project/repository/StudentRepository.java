package com.redriver.project.repository;

import com.redriver.project.entity.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    @Override
    <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable);


    Optional<Student> findById(String id);

    @Override
    <S extends Student> S save(S s);
}
