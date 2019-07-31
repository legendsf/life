package com.sf.jkt.k.testex.mvc;

import org.springframework.stereotype.Component;

@Component
public class ModelDao {
    public MModel getModel(Long id) {
        MModel model = new MModel();
        model.id = 1L;
        model.name = "afei";
        return model;
    }
}
