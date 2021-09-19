package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        return mappingJacksonValue(someBean, "SomeBeanFilter", new String[]{"field1", "field2"});
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBean() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value11", "value22", "value33"));

        return mappingJacksonValue(someBeans, "SomeBeanFilter", new String[]{"field2", "field3"});
    }

    private MappingJacksonValue mappingJacksonValue(Object objectValue, String filterId, String[] fields) {

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter(filterId, filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(objectValue);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
