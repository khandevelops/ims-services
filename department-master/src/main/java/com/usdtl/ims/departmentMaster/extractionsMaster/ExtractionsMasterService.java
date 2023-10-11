package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.departmentMaster.common.MasterEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExtractionsMasterService {
    private ExtractionsMasterRepository repository;
    public ExtractionsMasterEntity assignItem(MasterEntity masterItem) {
        return ExtractionsMasterEntity
                .builder()
                .masterItem(masterItem)
                .build();

    }
    public Double getTotal() {
        return repository.getGrandTotal();
    }

}