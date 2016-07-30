package com.summer.whm.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.Sensitivity;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.SensitivityMapper;
import com.summer.whm.service.BaseService;

@Service
public class SensitivityService extends BaseService {

    @Autowired
    private SensitivityMapper sensitivityMapper;

    public PageModel<Sensitivity> list(int pageIndex, int pageSize) {
        PageModel<Sensitivity> page = new PageModel<Sensitivity>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.sensitivityMapper;
    }

}
