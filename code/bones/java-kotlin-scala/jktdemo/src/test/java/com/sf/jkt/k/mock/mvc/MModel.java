package com.sf.jkt.k.mock.mvc;

public class MModel {
    public Long id;
    public String name;

    public MModel() {
    }

    public MModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MModel builder() {
        return new MModel();
    }
}
