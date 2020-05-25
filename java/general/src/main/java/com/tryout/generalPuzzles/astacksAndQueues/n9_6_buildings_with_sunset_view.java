package com.tryout.generalPuzzles.astacksAndQueues;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

// Design an algorithm that processes buildings in east-to-west order and returns the
//set of buildings which view the sunset. Each building is specified by its height.
public class n9_6_buildings_with_sunset_view {
    // Specifically, we use a stack to record buildings that have a view. Each time a
    //building b is processed, if it is taller than the building at the top of the stack, we pop
    //the stack until the top of the stack is taller than b—all the buildings thus removed lie
    //to the east of a taller building
    private static class BuildingWithHeight {
        public Integer id;
        public Integer height;

        public BuildingWithHeight(Integer id, Integer height) {
            this.id = id;
            this.height = height;
        }
    }

    public static Deque<BuildingWithHeight> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        int buildingldx = 0;
        Deque<BuildingWithHeight> buildingsWithSunset = new LinkedList<>();
        while (sequence.hasNext()) {
            Integer buildingHeight = sequence.next();
            while (!buildingsWithSunset.isEmpty()
                    && (Integer.compare(buildingHeight, buildingsWithSunset.getLast().height) >= 0)) {
                buildingsWithSunset.removeLast();
            }
            buildingsWithSunset.addLast(new BuildingWithHeight(buildingldx++, buildingHeight));
        }
        return buildingsWithSunset;
    }
}

