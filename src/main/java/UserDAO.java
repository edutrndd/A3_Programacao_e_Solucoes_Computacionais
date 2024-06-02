package main.java; 
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> users;

    public UserDAO() {
        users = new ArrayList<>();

        // Adicionando um usuário administrador padrão
        User adminUser = new User(1, "Admin", "admin", "000.000.000-00", 30, "Male", "Country", "admin123", true);
        users.add(adminUser);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }
}
