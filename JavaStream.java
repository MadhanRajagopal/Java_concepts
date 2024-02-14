import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee {
    private String name;
    private String dept;

    private double salary;

    public Employee(String name, String dept, double salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "dept='" + dept + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class JavaStream {

    public static void main(String[] args) {

        /*Find sum  all even numbers */
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        long sum = numbers.stream().filter(num -> num % 2 == 0).mapToInt(Integer::intValue).sum();
        System.out.println("Total Sum of even nos :: " + sum);
        int maxValue = numbers.stream().mapToInt(Integer::intValue).max().getAsInt();
        int minValue = numbers.stream().mapToInt(Integer::intValue).min().getAsInt();
        System.out.println("MAX Value :: " + maxValue);
        System.out.println("MIN Value :: " + minValue);


        List<String> languages = List.of("Java", "Python", "JavaScript", "C", "C++");
        String programmingLanguage = String.join(",", languages);
        System.out.println(programmingLanguage);

        /* sorted() -> internally use Comparable interface, if not return ClassCastException*/
        List<String> sortedList = languages.stream().sorted().toList();
        System.out.println(sortedList);

        /*         Remove Duplicates*/
        List<String> cloudProviders = Stream.of("AWS", "Azure", "GCP", "AWS").distinct().toList();
        System.out.println(cloudProviders);

        /*Remove Null Values*/
        List<String> records = Stream.of(null, "AWS", "Azure", "GCP", null).filter(Objects::nonNull).toList();
        System.out.println(records);

        // second element value
        String value = Stream.of("A", "B", "C", "D").sorted(Comparator.reverseOrder()).skip(1).findFirst().get();


        /* Get Max Salary by Each Department */
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "HR", 50000.0));
        employees.add(new Employee("Bob", "IT", 60000.0));
        employees.add(new Employee("Charliee", "Finance", 55000.0));
        employees.add(new Employee("David", "IT", 70000.0));
        employees.add(new Employee("Eva", "HR", 45000.0));
        employees.add(new Employee("Frank", "Finance", 58000.0));

        Map<String, Optional<Employee>> getMaxSalaryByEachDept = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        getMaxSalaryByEachDept.forEach((key, val) -> System.out.println(key + "::" + val.get().getSalary()));

    }
}
