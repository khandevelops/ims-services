package com.usdtl.ims.profileDetails.departmentNames;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentNameService {
    private DepartmentNameRepository repository;
    public List<DepartmentNameEntity> getDepartmentNames() {
        return repository.findAll();
    }

    public DepartmentNameEntity createDepartmentName(DepartmentNameEntity departmentName) {
        DepartmentNameEntity departmentNameEntity = new DepartmentNameEntity();
        departmentNameEntity.setName(departmentName.getName().toUpperCase());
        departmentNameEntity.setMapping(departmentName.getMapping().toUpperCase());
        repository.save(departmentNameEntity);
        return departmentNameEntity;
    }
}
