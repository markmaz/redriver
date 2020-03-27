package com.redriver.project.services.redriver;

import com.redriver.project.entity.Student;
import com.redriver.project.exceptions.NotFoundException;
import com.redriver.project.repository.StudentRepository;
import com.redriver.project.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
public class RedRiverStudentServiceImpl implements StudentService {
    private StudentRepository repository;

    @Autowired
    public RedRiverStudentServiceImpl(StudentRepository repository){
        this.repository = repository;
    }


    /**
     * I don't like returning nulls or Optionals from a getByID method. Seems like you know the ID - very specific.
     * Probably coming from a list of entities that were returned - so it should be in the database. If it isn't then
     * something is really wrong and should be an exception - IMHO
     *
     * @param id
     * @return
     * @throws com.redriver.project.exceptions.NotFoundException
     */
    @Override
    public Student getStudentById(String id) throws NotFoundException {
        if(StringUtils.isEmpty(id)){
            throw new NotFoundException(id + " not found");
        }else{
            Optional<Student> student = this.repository.findById(id);

            if(student.isPresent()){
                return student.get();
            }else{
                throw new NotFoundException(id + " not found");
            }
        }
    }

    /**
     * Added the sorting to this as well along with pagination and optional parameters using the Example and ExampleMapper
     *
     * @param student
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @Override
    public List<Student> getStudents(Student student, int page, int size, String sort) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("firstName", exact()).withMatcher("lastName", exact());
        Example<Student> example = Example.of(student, matcher);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
        return this.repository.findAll(example, pageRequest).toList();
    }

    @Override
    public Student saveStudent(Student student) {
        return this.repository.save(student);
    }
}
