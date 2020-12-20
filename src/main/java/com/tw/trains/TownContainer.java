package com.tw.trains;

import com.tw.trains.model.Town;
import com.tw.trains.model.TownRoute;

import java.util.*;

public class TownContainer {

    int[][] graphMatrix = null;
    private Set<Town> towns = new TreeSet<>();
    /**
     * 起始Town对应的路线list
     */
    private Map<Town, List<TownRoute>> townRouteMap = new HashMap<>();
    /**
     * 图中town对应的下标
     */
    private Map<Town, Integer> townForIdxMap = new HashMap<>();
    private Map<Integer, Town> idxForTownMap = new HashMap<>();

    public void addTownRouteItem(Town start, TownRoute edges) {
        towns.add(start);
        if (townRouteMap.get(start) == null) {
            List<TownRoute> list = new ArrayList<>();
            list.add(edges);
            townRouteMap.put(start, list);
        } else {
            townRouteMap.get(start).add(edges);
        }
    }


    public void graphToMatrix() {
        int townIdx = 0;
        int townSize = this.towns.size();
        graphMatrix = new int[townSize][townSize];
        for (Town town : towns) {
            townForIdxMap.put(town, townIdx);
            idxForTownMap.put(townIdx, town);
            townIdx++;
        }
        buildMatrix();

    }


    private void buildMatrix() {
        for (Town town : towns) {
            List<TownRoute> list = townRouteMap.get(town);
            if (list != null) {
                for (TownRoute edge : list) {
                    Integer startIdx = townForIdxMap.get(edge.getParent());
                    Integer endIdx = townForIdxMap.get(edge.getTarget());
                    if (endIdx != null) {
                        graphMatrix[startIdx][endIdx] = edge.getDistance();
                    }
                }
            }
        }
    }

    public Map<Town, Integer> getTownForIdxMap() {
        return townForIdxMap;
    }

    public Map<Integer, Town> getIdxForTownMap() {
        return idxForTownMap;
    }

    public int[][] getGraphMatrix() {
        return graphMatrix;
    }
}
