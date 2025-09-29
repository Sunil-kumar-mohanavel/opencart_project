package UI;

import org.testng.annotations.Test;
import Base.BaseTest;

public class TestUI extends BaseTest {

    @Test
    public void openHomePage() {
        System.out.println("Browser opened successfully at: " + driver.getCurrentUrl());
    }
}
