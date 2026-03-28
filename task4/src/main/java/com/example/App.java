package com.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class App {
    public static void main(String[] args) {
        // Створення тестових даних
        List<Student> fiotStudents = new ArrayList<>();
        fiotStudents.add(new Student("Іван", "Іванов", "КВ-111", 96.5));
        fiotStudents.add(new Student("Петро", "Петренко", "КВ-112", 88.0));
        fiotStudents.add(new Student("Олена", "Коваленко", "КВ-113", 99.0));

        List<Student> fpmStudents = new ArrayList<>();
        fpmStudents.add(new Student("Марія", "Сидоренко", "КМ-211", 92.0));
        fpmStudents.add(new Student("Олег", "Ткаченко", "КМ-212", 95.0));

        Faculty fiot = new Faculty("ФІОТ", fiotStudents);
        Faculty fpm = new Faculty("ФПМ", fpmStudents);

        List<Faculty> allFaculties = new ArrayList<>();
        allFaculties.add(fiot);
        allFaculties.add(fpm);

        Institute kpi = new Institute("КПІ", allFaculties);

        // =================================================================================
        // Задача 1: Знайти загальну кількість студентів, що навчається в інституті
        // =================================================================================
        long totalStudents = kpi.getFaculties().stream()
                .flatMap(faculty -> faculty.getStudents().stream())
                .count();
        
        System.out.println("1) Загальна кількість студентів: " + totalStudents);

        // =================================================================================
        // Задача 2: Знайти факультет, на якому навчається найбільша кількість студентів
        // =================================================================================
        Faculty largestFaculty = kpi.getFaculties().stream()
                .max(Comparator.comparingInt(faculty -> faculty.getStudents().size()))
                .orElse(null); // Повертає null, якщо список факультетів порожній
                
        System.out.println("2) Факультет з найбільшою кількістю студентів: " + 
                (largestFaculty != null ? largestFaculty.getName() : "Не знайдено"));

        // =================================================================================
        // Задача 3: Скласти список студентів, у яких середній бал в діапазоні 95..100
        // =================================================================================
        List<Student> topStudents = kpi.getFaculties().stream()
                .flatMap(faculty -> faculty.getStudents().stream())
                .filter(student -> student.getAverageGrade() >= 95.0 && student.getAverageGrade() <= 100.0)
                .collect(Collectors.toList());

        System.out.println("3) Студенти з балом від 95 до 100:");
        topStudents.forEach(student -> System.out.println("   - " + student.toString()));
    }
}