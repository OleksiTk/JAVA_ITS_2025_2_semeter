import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Клас Студент згідно з умовами: ім’я, прізвище, номер залікової книжки, середній бал
class Student {
    private String firstName;
    private String lastName;
    private String recordBookNumber;
    private double averageScore;

    public Student(String firstName, String lastName, String recordBookNumber, double averageScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.recordBookNumber = recordBookNumber;
        this.averageScore = averageScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Бал: " + averageScore + ")";
    }
}

// Клас Факультет згідно з умовами: назва, список студентів
class Faculty {
    private String name;
    private List<Student> students;

    public Faculty(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return name;
    }
}

// Клас Інститут згідно з умовами: назва, список факультетів
class Institute {
    private String name;
    private List<Faculty> faculties;

    public Institute(String name, List<Faculty> faculties) {
        this.name = name;
        this.faculties = faculties;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    // 1) Знайти загальну кількість студентів, що навчається в інституті
    public long getTotalStudentCount() {
        return faculties.stream()
                .mapToLong(faculty -> faculty.getStudents().size())
                .sum();
    }

    // 2) Знайти факультет, на якому навчається найбільша кількість студентів
    public Optional<Faculty> getLargestFaculty() {
        return faculties.stream()
                .max(Comparator.comparingInt(faculty -> faculty.getStudents().size()));
    }

    // 3) Скласти список студентів, у яких середній бал в діапазоні 95..100
    public List<Student> getTopStudents() {
        return faculties.stream()
                .flatMap(faculty -> faculty.getStudents().stream())
                .filter(student -> student.getAverageScore() >= 95.0 && student.getAverageScore() <= 100.0)
                .collect(Collectors.toList());
    }
}

// Головний клас для тестування
public class App {
    public static void main(String[] args) {
        // Створюємо студентів
        Student s1 = new Student("Олексій", "Іванов", "KB-11", 85.5);
        Student s2 = new Student("Марія", "Петренко", "KB-12", 96.0);
        Student s3 = new Student("Іван", "Сидоров", "FI-21", 99.5);
        Student s4 = new Student("Анна", "Коваленко", "FI-22", 92.0);
        Student s5 = new Student("Дмитро", "Ткаченко", "FI-23", 75.0);

        // Створюємо факультети з типізованими списками
        Faculty f1 = new Faculty("ФІОТ", Arrays.asList(s1, s2));
        Faculty f2 = new Faculty("ІПСА", Arrays.asList(s3, s4, s5));

        // Створюємо інститут
        Institute kpi = new Institute("КПІ", Arrays.asList(f1, f2));

        // Виконання задачі 1
        System.out.println("1) Загальна кількість студентів: " + kpi.getTotalStudentCount());

        // Виконання задачі 2
        kpi.getLargestFaculty().ifPresent(faculty -> 
            System.out.println("2) Найбільший факультет: " + faculty.getName() + " (Студентів: " + faculty.getStudents().size() + ")")
        );

        // Виконання задачі 3
        System.out.println("3) Студенти з балом 95-100:");
        kpi.getTopStudents().forEach(student -> System.out.println(" - " + student));
    }
}