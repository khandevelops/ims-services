package com.usdtl.ims.profileDetails.departmentNames;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.profileDetails.response.DeleteResponse;
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
        departmentNameEntity.setHasInventory(departmentName.getHasInventory());
        repository.save(departmentNameEntity);
        return departmentNameEntity;
    }

    public ResponseEntity<DeleteResponse> deleteDepartmentName(Integer id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new NotFoundException("Item associated with id: " + id + " not found");
        }
        repository.deleteById(id);
        DeleteResponse response = new DeleteResponse("SUCCESS", id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
