package com.usdtl.ims.departmentMaster.massSpecMaster;

import com.usdtl.ims.clients.MasterDepartmentClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MassSpecMasterService {
    private MassSpecMasterRepository massSpecMasterRepository;
    private MasterDepartmentClient client;

    public Page<MassSpecMasterEntity> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return massSpecMasterRepository.findAll(pageRequest);
    }

}