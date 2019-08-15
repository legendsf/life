package com.sf.jkt.k.service.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
    @Autowired
    ModelDao modelDao;

    public MModel getModel(Long id) {
        return null;
    }


}
