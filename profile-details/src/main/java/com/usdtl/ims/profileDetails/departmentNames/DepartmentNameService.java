package com.usdtl.ims.profileDetails.departmentNames;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public DepartmentNameEntity updateDepartmentName(Integer id, DepartmentNameEntity departmentName) {
        DepartmentNameEntity departmentNameEntity = repository.findById(id).orElseThrow();
        departmentNameEntity.setName(departmentName.getName().toUpperCase());
        departmentNameEntity.setMapping(departmentName.getMapping().replaceAll(" ", "_").toUpperCase());
        repository.save(departmentNameEntity);
        return departmentNameEntity;
    }

    public ResponseEntity<String> deleteDepartmentName(Integer id) {
        repository.deleteById(id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.ACCEPTED);
    }

}
