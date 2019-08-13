package com.sf.jkt.k.mock.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
    @Autowired
    ModelDao modelDao;

    public MModel getModel(Long id) {
        return modelDao.getModel(id);
    }


}
