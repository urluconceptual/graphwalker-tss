package com.example.order;

import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.generator.RandomPath;
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
    public void runEdgeCoverageTest() {
        new TestBuilder().addContext(
                        new OrderTest().setNextElement(
                                new Edge().setName("e_CreateRequest").build()
                        ),
                        Path.of(MODEL_PATH),
                        new RandomPath(new EdgeCoverage(100))
                )
                .execute();
    }
    @Test
    public void runAStarToPickup() {
        new TestBuilder().addContext(
                new OrderTest().setNextElement(
                        new Edge().setName("e_CreateRequest").build()
                ),
                Path.of(MODEL_PATH),
                new AStarPath(new ReachedVertex("v_PickedUpByCustomer"))
        ).execute();
    }

}
