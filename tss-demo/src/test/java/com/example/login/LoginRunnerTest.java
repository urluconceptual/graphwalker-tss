package com.example.login;

import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.VertexCoverage;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.QuickRandomPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.java.test.TestBuilder;
import org.junit.Test;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginRunnerTest {

    private static final URI MODEL_PATH = Paths.get("src/test/resources/models/LoginModel.json").toUri();

    @Test
    public void runRandomEdgeCoverageTest() {
        new TestBuilder().addContext(
                        new LoginTest(),
                        Path.of(MODEL_PATH),
                        new RandomPath(new EdgeCoverage(100))
                )
                .execute(true);
    }

    @Test
    public void runRandomVertexCoverageTest() {
        new TestBuilder().addContext(
                        new LoginTest(),
                        Path.of(MODEL_PATH),
                        new RandomPath(new VertexCoverage(100))
                )
                .execute(true);
    }

    @Test
    public void runAStarToSessionTimedOut() {
        new TestBuilder().addContext(
                new LoginTest(),
                Path.of(MODEL_PATH),
                new AStarPath(new ReachedVertex("v_SessionTimedOut"))
        ).execute(true);
    }

    @Test
    public void runAStarToErrorLockedLogin() {
        new TestBuilder().addContext(
                new LoginTest(),
                Path.of(MODEL_PATH),
                new AStarPath(new ReachedVertex("v_ErrorLockedLogin"))
        ).execute(true);
    }


    @Test
    public void runQuickRandomEdgeCoverageTest() {
        new TestBuilder().addContext(
                        new LoginTest(),
                        Path.of(MODEL_PATH),
                        new QuickRandomPath(new EdgeCoverage(100))
                )
                .execute(true);
    }
}
