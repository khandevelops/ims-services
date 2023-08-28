package com.usdtl.ims.masterTotal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MasterTotalService {
    private MasterTotalRepository repository;
    public MasterTotalEntity createItem(List<MasterTotalEntity> masterTotal) {
        return (MasterTotalEntity) repository.saveAll(masterTotal);
    }
}
