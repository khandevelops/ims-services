package com.usdtl.inventory.masterDepartment;

import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MasterDepartmentController {
    MasterDepartmentService service;

}
