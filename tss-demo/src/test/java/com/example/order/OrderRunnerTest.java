package com.example.order;

import org.graphwalker.core.condition.*;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.QuickRandomPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.generator.ShortestAllPaths;
import org.graphwalker.core.model.Edge;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.java.test.TestBuilder;
import org.junit.Test;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OrderRunnerTest {

    private static final URI MODEL_PATH = Paths.get("src/test/resources/models/OrderModel.json").toUri();

    @Test
    public void runRandomEdgeCoverageTest() {
        new TestBuilder().addContext(
                        new OrderTest(),
                        Path.of(MODEL_PATH),
                        new RandomPath(new EdgeCoverage(100))
                )
                .execute();
    }

    @Test
    public void runRandomRequirementCoverageTest() {
        new TestBuilder().addContext(
                        new OrderTest(),
                        Path.of(MODEL_PATH),
                        new RandomPath(new RequirementCoverage(100))
                )
                .execute();
    }

    @Test
    public void runRandomVertexCoverageTest() {
        new TestBuilder().addContext(
                        new OrderTest(),
                        Path.of(MODEL_PATH),
                        new RandomPath(new VertexCoverage(100))
                )
                .execute();
    }

    @Test
    public void runAStarToPickup() {
        new TestBuilder().addContext(
                new OrderTest(),
                Path.of(MODEL_PATH),
                new AStarPath(new ReachedVertex("v_PickedUpByCustomer"))
        ).execute();
    }

    @Test
    public void runAStarToRejected() {
        new TestBuilder().addContext(
                new OrderTest(),
                Path.of(MODEL_PATH),
                new AStarPath(new ReachedVertex("v_Rejected"))
        ).execute();
    }


    @Test
    public void runQuickRandomEdgeCoverageTest() {
        new TestBuilder().addContext(
                        new OrderTest(),
                        Path.of(MODEL_PATH),
                        new QuickRandomPath(new EdgeCoverage(100))
                )
                .execute();
    }
}
