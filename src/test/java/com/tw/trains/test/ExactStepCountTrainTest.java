package com.tw.trains.test;

import com.tw.trains.BootStarpProperty;
import com.tw.trains.TownContainer;
import com.tw.trains.model.Town;
import com.tw.trains.provider.ExactStepCountSearchProvider;
import com.tw.trains.util.RouteBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.MissingResourceException;

public class ExactStepCountTrainTest {

    private TownContainer container;


    @Before
    public void initDataFromProperty() throws IOException {
        BootStarpProperty property = new BootStarpProperty();
        property.init("townLine");
        container = property.getContainer();
    }

    @Test(expected = MissingResourceException.class)
    public void shouldThrowExceptionWhenReadNotExistProperties() throws IOException {
        BootStarpProperty property = new BootStarpProperty();
        property.init("bb");
    }

    @Test
    public void shouldReturnThreeCountExactStepWhenStartAAndEndCAndExactStepIsFour() {
        Town start = RouteBuilder.buildTownInfo("A");
        Town end = RouteBuilder.buildTownInfo("C");
        ExactStepCountSearchProvider maxStepProvider = new ExactStepCountSearchProvider(start, end, 4, container);
        Integer count = maxStepProvider.search();
        Assert.assertEquals(3, (long) count);
    }
}
