package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Taz", "Enjoyer", "taz@mail.ru");
      User user2 = new User("Alexey", "Ivanov", "ivanov@gmail.com");
      User user3 = new User("Vasily", "Smirnov", "smirnov@yandex.ru");
      User user4 = new User("Petr", "Mikhailov", "roma@yahoo.com");

      Car car1 = new Car("Lada", 2108);
      Car car2 = new Car("Chrysler", 300);
      Car car3 = new Car("Pontiac", 6000);
      Car car4 = new Car("Lincoln Continental", 1970);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for(User user : userService.listUsers()) {
         System.out.println(user + " owns " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Lada", 2108));

      context.close();
   }
}
