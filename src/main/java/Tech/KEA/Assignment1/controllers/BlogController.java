package Tech.KEA.Assignment1.controllers;
import Tech.KEA.Assignment1.repository.DatabaseConnectionMySQL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class BlogController{
    @GetMapping ("/")
    public String index() {
        try {
            DatabaseConnectionMySQL d = new DatabaseConnectionMySQL();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return "index";
    }
}