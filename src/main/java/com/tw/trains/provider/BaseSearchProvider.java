package com.tw.trains.provider;

import com.tw.trains.TownContainer;
import com.tw.trains.model.Town;

public abstract class BaseSearchProvider {

    private TownContainer container;

    public BaseSearchProvider(TownContainer container) {
        this.container = container;
    }

    public Object search() {
        return null;
    }

    protected Integer getIdxByTown(Town town) {
        return container.getTownForIdxMap().get(town);
    }

    protected int[][] getGraphMatrix() {
        return container.getGraphMatrix();
    }

}
