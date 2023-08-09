package com.usdtl.ims.profileDetails.departmentNames;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentNameRepository extends JpaRepository<DepartmentNameEntity, Integer> {

}
