package tests;

import org.junit.jupiter.api.Test;
import pages.BasePage;
import pages.GetByRolePage;

public class GetByRoleTest extends BaseTest {

    void getByRoleTest(){
        GetByRolePage getByRole= new GetByRolePage(page);
        getByRole.regexGetByRole.click();
    }
}
