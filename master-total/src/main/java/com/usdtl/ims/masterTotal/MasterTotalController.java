package com.usdtl.ims.masterTotal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MasterTotalController {
    private MasterTotalService service;
    @PostMapping("create")
    public MasterTotalEntity createItem(@RequestBody List<MasterTotalEntity> masterTotal) {
        return service.createItem(masterTotal);
    }

}
