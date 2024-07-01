package com.springboot.springboot_jpa.utils;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

@Component
public class DynamicFilterProvider {

     public SimpleFilterProvider createFilterProvider(String[] fields) {
          SimpleFilterProvider filterProvider = new SimpleFilterProvider();
          filterProvider.addFilter("dynamicUserFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
          return filterProvider;
     }
}