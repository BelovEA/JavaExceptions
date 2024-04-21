import java.io.*;
import java.util.Scanner;
//pull req
public class UserDataApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите: Фамилию, Имя, Отчество, дату рождения, номер телефона, пол" +
                "\nСделайте это в следующем формате: фамилия, имя, отчество(разделенные пробелом), дата рождения формата: dd.mm.yyyy, номер телефона - целое число, пол - символ латиницей f или m: ");
        String input = scanner.nextLine();

        try {
            validateAndSaveUserData(input);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validateAndSaveUserData(String input) throws IOException {
        String[] parts = input.split(" ");

        if (parts.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных. Требуется ввести 6 значений.");
        }

        String lastName = parts[0];
        String firstName = parts[1];
        String middleName = parts[2];
        String birthDate = parts[3];
        String phoneNumber = parts[4];
        String gender = parts[5];

        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Используйте формат dd.mm.yyyy.");
        }

        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Номер телефона должен содержать только цифры.");
        }

        if (!gender.matches("[fm]")) {
            throw new IllegalArgumentException("Пол должен быть указан как 'f' или 'm'.");
        }

        saveToFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);
    }

    private static void saveToFile(String lastName, String firstName, String middleName, String birthDate, String phoneNumber, String gender) throws IOException {
        File file = new File(lastName + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(String.format("%s %s %s %s %s %s\n", lastName, firstName, middleName, birthDate, phoneNumber, gender));
        } catch (IOException e) {
            System.out.println("Проблема с записью в файл.");
            throw e;
        }
    }
}
