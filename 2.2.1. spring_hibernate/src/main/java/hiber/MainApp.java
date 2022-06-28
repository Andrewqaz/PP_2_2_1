package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      userService.add(new User("UserWithCar1", "LastnameWithCar1", "user1@mail.ru", new Car("model1")));
      userService.add(new User("UserWithCar2", "LastnameWithCar2", "user1@mail.ru", new Car("model2")));
      userService.add(new User("UserWithCar3", "LastnameWithCar3", "user1@mail.ru", new Car("model3")));
      userService.add(new User("UserWithCar4", "LastnameWithCar4", "user1@mail.ru", new Car("model4")));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      User model1 = userService.getByCarSeriesAndModel(1, "model1");
      System.out.println(model1);

      context.close();
   }
}
