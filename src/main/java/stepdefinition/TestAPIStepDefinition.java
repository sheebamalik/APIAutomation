package stepdefinition;

import com.api.service.TestAPIService;
import io.cucumber.java.en.When;

public class TestAPIStepDefinition
{
    @When("^User hit the sample get API$")
    public static void user_hit_sample_get_api()
    {
        TestAPIService.getAPI();
    }

    @When("^User hit the sample post API$")
    public static void user_hit_sample_post_api()
    {
        TestAPIService.postAPI();
    }

    @When("^User hit the sample delete API$")
    public static void user_hit_sample_delete_api()
    {
        TestAPIService.deleteAPI();
    }
}
