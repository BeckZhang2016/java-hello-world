import com.beck.controller.UserController;
import com.beck.entities.User;
import com.beck.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
public class ServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void testMvc() throws Exception {
        int userId = 1;
        List<User> users = null;
        User user = new User();
        user.setId(1);
        users.add(user);
        Mockito.when(userService.findOneForName("1"));
        userService.findOneForName("1");
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
