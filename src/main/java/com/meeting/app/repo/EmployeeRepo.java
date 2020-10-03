package com.meeting.app.repo;

import com.meeting.app.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    Employee findByEmployeeName(@Param("name") String name);

}
