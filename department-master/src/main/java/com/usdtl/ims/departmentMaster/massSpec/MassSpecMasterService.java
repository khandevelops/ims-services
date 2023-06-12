package com.usdtl.ims.departmentMaster.massSpec;

import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.response.DepartmentMasterResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class MassSpecMasterService {
    private MassSpecMasterRepository massSpecMasterRepository;
    private MasterDepartmentClient client;

    public Page<MassSpecMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return massSpecMasterRepository.findAll(pageRequest);
    }

}