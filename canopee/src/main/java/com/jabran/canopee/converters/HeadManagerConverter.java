package com.jabran.canopee.converters;

import com.jabran.canopee.entities.HeadManager;
import com.jabran.canopee.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class HeadManagerConverter implements Converter<String, HeadManager> {

    @Autowired
    ManagerRepository managerRepository;

    @Override
    public HeadManager convert(String source) {
        return managerRepository.findById(Integer.parseInt(source)).orElse(null);
    }
}
