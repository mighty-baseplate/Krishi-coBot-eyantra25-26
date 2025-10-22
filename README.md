# 🎓 Secure Online Examination System

> **OOP Syllabus Project & Hackathon Demo**  
> A full-stack web application demonstrating university-level Object-Oriented Programming principles, design patterns, and Java enterprise technologies.

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

---

## 📋 Table of Contents

- [Features](#-features)
- [Architecture](#-architecture)
- [OOP Concepts Demonstrated](#-oop-concepts-demonstrated)
- [Design Patterns](#-design-patterns)
- [Technology Stack](#-technology-stack)
- [Project Structure](#-project-structure)
- [Setup & Installation](#-setup--installation)
- [Running the Application](#-running-the-application)
- [Demo Script](#-demo-script)
- [API Endpoints](#-api-endpoints)
- [Testing](#-testing)
- [Screenshots](#-screenshots)
- [Contributors](#-contributors)

---

## ✨ Features

### Student Features
- 🔐 **Secure Authentication** - JWT-based login with role-based access control
- 📊 **Interactive Dashboard** - View available exams with detailed information
- ⏱️ **Real-time Timer** - 30-minute countdown for each exam
- 📝 **Multiple Question Types** - MCQ, Coding, and Essay questions
- 🎯 **Auto-Grading** - Instant results for MCQ exams
- 📈 **Performance Analytics** - View scores, grades, and pass/fail status
- 📱 **Responsive Design** - Mobile-friendly Bootstrap 5 UI

### Admin Features
- 👨‍💼 **Admin Dashboard** - Complete system overview
- 📚 **Exam Management** - Create exams using Factory pattern
- ❓ **Question Bank** - Add questions to exams dynamically
- 📊 **Analytics Dashboard** - Charts and statistics using Chart.js
- 🔍 **Metadata Inspection** - View exam metadata via Reflection API
- 👥 **Student Management** - View all registered students

### Technical Highlights
- 🏭 **Factory Pattern** - Dynamic exam creation (MCQ/Coding/Essay)
- 🎯 **Strategy Pattern** - Pluggable evaluation strategies
- 🔄 **Concurrency** - Thread-safe synchronized exam submissions
- 🌊 **Streams API** - Advanced data filtering and aggregations
- 💾 **Serialization** - Object persistence to files
- 📁 **File I/O** - CSV export for exam results
- 🧪 **JUnit 5 Tests** - Comprehensive unit testing
- 🔒 **Spring Security** - Authentication and authorization
- 🗄️ **JPA/Hibernate** - Object-relational mapping
- 💻 **H2 Database** - In-memory database for portability

---

## 🏗️ Architecture

### System Architecture (ASCII UML)

```
┌─────────────────────────────────────────────────────────────┐
│                    Presentation Layer                       │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐        │
│  │  Thymeleaf  │  │  Bootstrap  │  │  Chart.js   │        │
│  │  Templates  │  │     CSS     │  │   Charts    │        │
│  └─────────────┘  └─────────────┘  └─────────────┘        │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                    Controller Layer                         │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐       │
│  │   Student    │ │    Admin     │ │     Auth     │       │
│  │  Controller  │ │  Controller  │ │  Controller  │       │
│  └──────────────┘ └──────────────┘ └──────────────┘       │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                     Service Layer (SOLID)                   │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐       │
│  │ IExamService │ │IStudentService│ │ExamServiceImpl│      │
│  │  Interface   │ │   Interface  │ │  (Concrete)  │       │
│  └──────────────┘ └──────────────┘ └──────────────┘       │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│               Design Patterns Layer                         │
│  ┌──────────────┐ ┌──────────────┐                         │
│  │ ExamFactory  │ │  Evaluation  │                         │
│  │   Pattern    │ │   Strategy   │                         │
│  │              │ │   Pattern    │                         │
│  └──────────────┘ └──────────────┘                         │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                   Domain/Entity Layer                       │
│         ┌────────────────────────┐                          │
│         │        Exam            │   (Abstract)             │
│         │  - id, title, type     │                          │
│         │  + evaluate()          │                          │
│         └────────────────────────┘                          │
│                  ↑        ↑        ↑                         │
│         ┌────────┴────┬───┴────┬───┴─────┐                 │
│    ┌────────┐   ┌─────────┐  ┌──────────┐                 │
│    │MCQExam │   │CodingExam│  │EssayExam │                 │
│    │        │   │         │  │          │                 │
│    └────────┘   └─────────┘  └──────────┘                 │
│                                                              │
│    ┌─────────┐         ┌──────────┐                        │
│    │ Student │◆────────│ Question │                        │
│    └─────────┘         └──────────┘                        │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                  Repository Layer (JPA)                     │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐       │
│  │   Student    │ │     Exam     │ │   Question   │       │
│  │  Repository  │ │  Repository  │ │  Repository  │       │
│  └──────────────┘ └──────────────┘ └──────────────┘       │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                    Database Layer                           │
│              ┌──────────────────────┐                       │
│              │   H2 In-Memory DB    │                       │
│              └──────────────────────┘                       │
└─────────────────────────────────────────────────────────────┘
```

---

## 🎯 OOP Concepts Demonstrated

### 1. **Encapsulation**
- **Private fields** with **public getters/setters** in all entity classes
- Example: `Student` class with private `id`, `name`, `scores`
- **Information hiding** - internal implementation details are hidden

```java
public class Student {
    private Long id;              // Encapsulated field
    private String name;
    private Map<Long, Integer> examScores = new HashMap<>();
    
    public Integer getScoreForExam(Long examId) {  // Controlled access
        return examScores.getOrDefault(examId, 0);
    }
}
```

### 2. **Inheritance**
- **Abstract base class** `Exam` with concrete subclasses
- `MCQExam`, `CodingExam`, `EssayExam` extend `Exam`
- **Code reuse** - common fields/methods in parent class

```java
public abstract class Exam {
    private Long id;
    private String title;
    public abstract int evaluate(String[] answers);  // Template method
}

public class MCQExam extends Exam {
    @Override
    public int evaluate(String[] answers) {
        // MCQ-specific implementation
    }
}
```

### 3. **Polymorphism**
- **Method overriding** - Each exam type implements `evaluate()` differently
- **Runtime polymorphism** - Exam reference can point to any subclass
- **Interface polymorphism** - `EvaluationStrategy` interface

```java
Exam exam1 = new MCQExam("Test", 1, 10);      // Polymorphic reference
Exam exam2 = new CodingExam("Challenge", 1, 5);
exam1.evaluate(answers);  // Calls MCQExam's implementation
exam2.evaluate(answers);  // Calls CodingExam's implementation
```

### 4. **Abstraction**
- **Abstract classes** - `Exam` with abstract `evaluate()` method
- **Interfaces** - `IExamService`, `IStudentService`, `EvaluationStrategy`
- **Hiding complexity** - Service interfaces hide implementation details

```java
public interface IExamService {
    Exam createExam(ExamType type, String title, int sections, int qPerSection);
    int submitExam(Long examId, Student student, String[] answers);
}
```

### 5. **Static Members**
- **Static counters** - `Exam.getTotalExams()`, `Student.getTotalStudents()`
- **Static methods** - Factory methods in `ExamFactory`
- **Shared state** across all instances

```java
public class Exam {
    private static int examCounter = 0;  // Shared across all exams
    
    public Exam(...) {
        examCounter++;  // Increments for each exam created
    }
    
    public static int getTotalExams() {
        return examCounter;
    }
}
```

---

## 🎨 Design Patterns

### 1. **Factory Pattern** ✅
**Purpose:** Create exam objects without specifying exact class

```java
public class ExamFactory {
    public Exam createExam(ExamType type, String title, int sections, int qPerSection) {
        switch (type) {
            case MCQ:
                return new MCQExam(title, sections, qPerSection);
            case CODING:
                return new CodingExam(title, sections, qPerSection);
            case ESSAY:
                return new EssayExam(title, sections, qPerSection);
        }
    }
}
```

**Usage in Application:**
- Admin creates exam → Factory decides which subclass to instantiate
- Demonstrates **Open/Closed Principle** - easy to add new exam types

### 2. **Strategy Pattern** ✅
**Purpose:** Interchangeable evaluation algorithms

```java
public interface EvaluationStrategy {
    int evaluate(String[] answers, String[] correctAnswers);
}

public class MCQStrategy implements EvaluationStrategy {
    public int evaluate(String[] answers, String[] correctAnswers) {
        // MCQ auto-grading logic
    }
}

public class ManualStrategy implements EvaluationStrategy {
    public int evaluate(String[] answers, String[] correctAnswers) {
        return 0;  // Requires manual grading
    }
}
```

**Usage in Application:**
- MCQExam uses `MCQStrategy` (auto-grading)
- CodingExam/EssayExam use `ManualStrategy`
- Strategies can be swapped at runtime

### 3. **Repository Pattern** ✅
**Purpose:** Abstract data access layer

```java
@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByType(ExamType type);
}
```

### 4. **Template Method Pattern** ✅
**Purpose:** Define skeleton in base class, override in subclasses

```java
public abstract class Exam {
    public abstract int evaluate(String[] answers);  // Template method
    
    public synchronized void submit(Student s, String[] answers) {
        int score = evaluate(answers);  // Calls overridden method
        studentScores.put(s.getId(), score);
    }
}
```

---

## 🛠️ Technology Stack

### Backend
- **Java 17+** - Modern Java features
- **Spring Boot 3.2.0** - Enterprise application framework
- **Spring Web** - RESTful APIs
- **Spring Data JPA** - Database abstraction
- **Spring Security** - Authentication & authorization
- **Hibernate** - ORM implementation
- **H2 Database** - In-memory database
- **Lombok** - Boilerplate reduction
- **JJWT 0.12.3** - JWT token generation

### Frontend
- **Thymeleaf** - Server-side templating
- **Bootstrap 5.3.0** - Responsive CSS framework
- **Bootstrap Icons** - Icon library
- **Chart.js 4.4.0** - Data visualization

### Testing
- **JUnit 5** - Unit testing
- **Mockito** - Mocking framework
- **Spring Boot Test** - Integration testing

### Build Tool
- **Maven** - Dependency management

---

## 📁 Project Structure

```
online-exam-system/
├── pom.xml                                 # Maven dependencies
├── README.md                               # This file
│
├── src/main/java/com/examsystem/
│   ├── OnlineExamSystemApplication.java   # Main Spring Boot class
│   │
│   ├── config/                             # Configuration classes
│   │   ├── SecurityConfig.java            # Spring Security config
│   │   ├── WebConfig.java                 # Web MVC config
│   │   └── DataInitializer.java           # Demo data loader
│   │
│   ├── controller/                         # REST & MVC Controllers
│   │   ├── AuthController.java            # Login/Register
│   │   ├── StudentController.java         # Student operations
│   │   ├── AdminController.java           # Admin operations
│   │   └── DashboardController.java       # Route based on role
│   │
│   ├── service/                            # Business logic (SOLID)
│   │   ├── IExamService.java              # Exam service interface
│   │   ├── ExamServiceImpl.java           # Exam service implementation
│   │   ├── IStudentService.java           # Student service interface
│   │   └── StudentServiceImpl.java        # Student service implementation
│   │
│   ├── repository/                         # JPA Repositories
│   │   ├── StudentRepository.java
│   │   ├── ExamRepository.java
│   │   └── QuestionRepository.java
│   │
│   ├── entity/                             # Domain entities (OOP)
│   │   ├── Student.java                   # Student entity
│   │   ├── Question.java                  # Question entity
│   │   ├── Exam.java                      # Abstract exam (Inheritance)
│   │   ├── MCQExam.java                   # MCQ exam subclass
│   │   ├── CodingExam.java                # Coding exam subclass
│   │   └── EssayExam.java                 # Essay exam subclass
│   │
│   ├── model/                              # DTOs & Enums
│   │   ├── ExamType.java                  # Enum: MCQ, CODING, ESSAY
│   │   ├── Role.java                      # Enum: STUDENT, ADMIN
│   │   ├── LoginRequest.java
│   │   └── ExamSubmissionRequest.java
│   │
│   ├── exception/                          # Custom exceptions
│   │   ├── InvalidAnswerException.java
│   │   └── ExamNotFoundException.java
│   │
│   ├── strategy/                           # Strategy pattern
│   │   ├── EvaluationStrategy.java        # Strategy interface
│   │   ├── MCQStrategy.java               # MCQ evaluation
│   │   └── ManualStrategy.java            # Manual grading
│   │
│   ├── factory/                            # Factory pattern
│   │   └── ExamFactory.java               # Exam creation factory
│   │
│   ├── security/                           # Security components
│   │   ├── JwtUtil.java                   # JWT token utilities
│   │   └── CustomUserDetailsService.java  # User loading
│   │
│   └── utils/                              # Utility classes
│       ├── SerializationUtil.java         # Object serialization
│       └── FileIOUtil.java                # CSV file operations
│
├── src/main/resources/
│   ├── application.yml                     # App configuration
│   │
│   ├── templates/                          # Thymeleaf templates
│   │   ├── login.html                     # Login page
│   │   ├── register.html                  # Registration page
│   │   │
│   │   ├── student/                       # Student views
│   │   │   ├── dashboard.html
│   │   │   ├── exam.html
│   │   │   └── results.html
│   │   │
│   │   └── admin/                         # Admin views
│   │       ├── dashboard.html
│   │       ├── analytics.html
│   │       ├── create-exam.html
│   │       └── add-questions.html
│   │
│   └── static/                             # Static resources
│       ├── css/
│       ├── js/
│       └── images/
│
└── src/test/java/com/examsystem/          # JUnit tests
    ├── ExamServiceTest.java               # Service layer tests
    ├── StrategyPatternTest.java           # Strategy tests
    ├── StreamsAPITest.java                # Streams API tests
    └── ConcurrencyTest.java               # Concurrency tests
```

---

## 🚀 Setup & Installation

### Prerequisites
- **Java 17 or higher** ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.8+** ([Download](https://maven.apache.org/download.cgi))
- **Git** (optional, for cloning)

### Installation Steps

1. **Clone the repository** (or download ZIP)
   ```bash
   git clone https://github.com/yourusername/online-exam-system.git
   cd online-exam-system
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run tests** (optional but recommended)
   ```bash
   mvn test
   ```

---

## ▶️ Running the Application

### Method 1: Maven Spring Boot Plugin (Recommended for Development)
```bash
mvn spring-boot:run
```

### Method 2: JAR Execution (For Deployment/Demo)
```bash
mvn clean package
java -jar target/online-exam-system-1.0.0.jar
```

### Method 3: IDE (IntelliJ IDEA / Eclipse)
1. Import as Maven project
2. Run `OnlineExamSystemApplication.java` main method

### Access the Application
- **Application URL:** http://localhost:8080
- **H2 Console:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:examdb`
  - Username: `admin`
  - Password: `admin`

### Default Login Credentials

| Role    | Username  | Password  |
|---------|-----------|-----------|
| Admin   | admin     | admin123  |
| Student | student1  | pass123   |
| Student | student2  | pass123   |
| Student | student3  | pass123   |
| Student | student4  | pass123   |
| Student | student5  | pass123   |

---

## 🎬 Demo Script (For Hackathon Presentation)

### Part 1: Introduction (2 minutes)
1. **Start application**: `mvn spring-boot:run`
2. **Show splash screen** in console:
   ```
   ╔════════════════════════════════════════════════════════╗
   ║  Secure Online Examination System Started Successfully!║
   ╚════════════════════════════════════════════════════════╝
   ```
3. **Navigate to** http://localhost:8080
4. **Highlight features:**
   - OOP principles (Encapsulation, Inheritance, Polymorphism, Abstraction)
   - Design Patterns (Factory, Strategy)
   - Modern tech stack (Spring Boot, JPA, Security, Thymeleaf)

### Part 2: Student Flow (3 minutes)
1. **Login as Student** → `student1` / `pass123`
2. **Dashboard Tour:**
   - Show stats cards (Available Exams, Last Score)
   - Explain exam types (MCQ auto-graded, Coding/Essay manual)
3. **Take MCQ Exam:**
   - Click "Start Exam" on "Java OOP Fundamentals"
   - **Highlight timer** (30 minutes countdown)
   - Answer questions (use radio buttons)
   - Click "Submit Exam"
4. **View Results:**
   - Show score circle, percentage, pass/fail status
   - Grade calculation (A+, A, B, C, D, F)
   - Progress bar visualization

### Part 3: Admin Features (3 minutes)
1. **Logout** and **login as Admin** → `admin` / `admin123`
2. **Admin Dashboard:**
   - Show system statistics
   - All exams table
   - Registered students table
3. **Create New Exam (Factory Pattern Demo):**
   - Click "Create Exam"
   - Select type: **MCQ** (explain Factory will create `MCQExam` instance)
   - Fill title: "Advanced Java Concepts"
   - Set sections: 2, Questions: 5
   - Click "Create Exam & Add Questions"
4. **Add Questions:**
   - Add sample MCQ questions with options
   - Show how questions are persisted
5. **Analytics Dashboard:**
   - Click "Analytics" in sidebar
   - Select an exam from dropdown
   - **Show Streams API in action:**
     - Total Students (count)
     - Passed Students (filter)
     - Average Score (mapToInt + average)
   - **Chart.js visualizations:**
     - Pass/Fail Pie Chart
     - Performance Bar Chart

### Part 4: OOP & Design Patterns Demo (3 minutes)
1. **Factory Pattern:**
   - Show console logs: `[FACTORY] Created MCQExam with auto-grading strategy`
   - Explain polymorphism: Same `createExam()` returns different types
2. **Strategy Pattern:**
   - Show MCQ auto-grading vs Manual grading
   - Explain interchangeable strategies
3. **Reflection API:**
   - Click "Metadata" button on an exam
   - Show console output with class fields/methods
4. **Concurrency:**
   - Open multiple browser tabs
   - Submit same exam concurrently as different students
   - Check console: `[CONCURRENT] Student X submitted exam. Score: Y`
   - Verify no race conditions (all submissions recorded)
5. **Streams API:**
   - Go to H2 Console (http://localhost:8080/h2-console)
   - Query: `SELECT * FROM STUDENTS`
   - Back to Analytics → Show filtering/grouping in action

### Part 5: Code Walkthrough (2 minutes)
1. **Show Project Structure** in IDE
2. **Highlight key files:**
   - `Exam.java` (Abstract class with inheritance)
   - `ExamFactory.java` (Factory pattern)
   - `EvaluationStrategy.java` (Strategy interface)
   - `ExamServiceImpl.java` (Streams API usage)
   - JUnit tests (`ExamServiceTest.java`)
3. **Run tests:** `mvn test` → Show passing tests

### Part 6: Q&A Preparation (1 minute)
**Anticipated Questions:**
- **Q:** How does Factory Pattern work?
  - **A:** Based on `ExamType` enum, factory instantiates appropriate subclass
- **Q:** How is concurrency handled?
  - **A:** Synchronized `submit()` method + `ConcurrentHashMap`
- **Q:** How do you test Streams API?
  - **A:** Show `StreamsAPITest.java` with filter/map/collect tests
- **Q:** How is security implemented?
  - **A:** Spring Security with JWT tokens, password encryption

---

## 🔌 API Endpoints

### Authentication
| Method | Endpoint           | Description          |
|--------|--------------------|----------------------|
| POST   | `/login`           | User login           |
| POST   | `/register`        | New student signup   |
| POST   | `/api/auth/login`  | JWT token generation |

### Student APIs
| Method | Endpoint                         | Description              |
|--------|----------------------------------|--------------------------|
| GET    | `/student/dashboard`             | Student dashboard        |
| GET    | `/student/exam/{examId}`         | Start exam               |
| POST   | `/student/submit`                | Submit exam answers      |
| GET    | `/student/api/exam/{id}/questions` | Get exam questions JSON |
| POST   | `/student/api/submit`            | Submit exam (API)        |

### Admin APIs
| Method | Endpoint                          | Description                |
|--------|-----------------------------------|----------------------------|
| GET    | `/admin/dashboard`                | Admin dashboard            |
| GET    | `/admin/analytics`                | Analytics page             |
| GET    | `/admin/api/analytics/{examId}`   | Get exam analytics (JSON)  |
| GET    | `/admin/api/students/grouped`     | Students grouped by type   |
| GET    | `/admin/exam/create`              | Create exam form           |
| POST   | `/admin/exam/create`              | Submit new exam            |
| GET    | `/admin/exam/{id}/questions`      | Add questions page         |
| POST   | `/admin/exam/{id}/question`       | Add question to exam       |
| GET    | `/admin/exam/{id}/metadata`       | Show exam metadata (Reflection) |

---

## 🧪 Testing

### Run All Tests
```bash
mvn test
```

### Test Coverage
- ✅ **ExamServiceTest** - Service layer testing with mocking
- ✅ **StrategyPatternTest** - MCQStrategy and ManualStrategy tests
- ✅ **StreamsAPITest** - Java Streams API operations
- ✅ **ConcurrencyTest** - Thread-safe concurrent submissions

### Test Highlights

**1. Factory Pattern Test**
```java
@Test
void testCreateMCQExam() {
    Exam exam = examService.createExam(ExamType.MCQ, "Test", 1, 10);
    assertTrue(exam instanceof MCQExam);
}
```

**2. Grading Test**
```java
@Test
void testMCQGrading_AllCorrect() {
    String[] answers = {"A", "A", "A"};
    int score = exam.evaluate(answers);
    assertEquals(100, score);
}
```

**3. Streams Test**
```java
@Test
void testFilterPassedStudents() {
    List<Student> passed = students.stream()
        .filter(s -> s.getScoreForExam(1L) >= 50)
        .collect(Collectors.toList());
    assertEquals(3, passed.size());
}
```

**4. Concurrency Test**
```java
@Test
void testConcurrentSubmission() {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    // Submit 10 exams concurrently
    // Verify all 10 submissions recorded correctly
}
```

---

## 📸 Screenshots

### 1. Login Page
*Beautiful gradient login with demo credentials*

### 2. Student Dashboard
*Cards showing available exams with auto-grading badges*

### 3. Exam Taking
*Real-time timer, MCQ radio buttons, responsive design*

### 4. Results Page
*Score circle, grade, percentage, pass/fail status*

### 5. Admin Dashboard
*System overview with stats cards and tables*

### 6. Analytics Dashboard
*Chart.js visualizations showing pass/fail distribution*

### 7. Create Exam (Factory Pattern)
*Form demonstrating Factory pattern for exam creation*

---

## 🎓 Learning Outcomes

This project demonstrates mastery of:

1. **OOP Principles:**
   - ✅ Encapsulation (private fields, public methods)
   - ✅ Inheritance (Exam hierarchy)
   - ✅ Polymorphism (method overriding, interfaces)
   - ✅ Abstraction (abstract classes, interfaces)

2. **SOLID Principles:**
   - ✅ **S**ingle Responsibility - Each class has one purpose
   - ✅ **O**pen/Closed - Factory allows extension without modification
   - ✅ **L**iskov Substitution - Exam subclasses interchangeable
   - ✅ **I**nterface Segregation - Focused service interfaces
   - ✅ **D**ependency Inversion - Depends on abstractions (interfaces)

3. **Design Patterns:**
   - ✅ Factory Pattern
   - ✅ Strategy Pattern
   - ✅ Repository Pattern
   - ✅ Template Method Pattern

4. **Java Features:**
   - ✅ Streams API (filter, map, collect, groupBy, average)
   - ✅ Collections (HashMap, List, ConcurrentHashMap)
   - ✅ Concurrency (synchronized methods, ExecutorService)
   - ✅ Reflection (getDeclaredFields, getMethods)
   - ✅ Serialization (ObjectOutputStream, Serializable)
   - ✅ Exception Handling (custom exceptions)
   - ✅ File I/O (BufferedWriter, CSV export)

5. **Enterprise Technologies:**
   - ✅ Spring Boot framework
   - ✅ Spring Data JPA
   - ✅ Spring Security
   - ✅ Thymeleaf templating
   - ✅ JWT authentication
   - ✅ RESTful APIs

6. **Testing:**
   - ✅ JUnit 5
   - ✅ Mockito
   - ✅ Unit testing
   - ✅ Concurrency testing

---

## 🔧 Configuration

### application.yml
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:examdb
    username: admin
    password: admin
  jpa:
    hibernate:
      ddl-auto: create-drop
  h2:
    console:
      enabled: true

server:
  port: 8080

jwt:
  secret: MySecretKeyForOOPExamSystemDemoHackathon2024
  expiration: 86400000
```

---

## 🤝 Contributors

- **Your Name** - Project Developer
- **University Name** - OOP Syllabus Project
- **Date** - October 2024

---

## 📝 License

This project is created for educational purposes as part of an OOP syllabus project and hackathon demonstration.

---

## 🙏 Acknowledgments

- **Spring Boot Team** - Amazing framework
- **Bootstrap** - Beautiful UI components
- **Chart.js** - Data visualization
- **H2 Database** - In-memory database
- **Java Community** - For excellent documentation

---

## 📞 Support

For questions or issues:
- **Email:** your.email@university.edu
- **GitHub Issues:** [Create an issue](https://github.com/yourusername/online-exam-system/issues)

---

## 🎯 Future Enhancements

- [ ] Add video proctoring
- [ ] Implement email notifications
- [ ] Add question randomization
- [ ] Export results to PDF
- [ ] Multi-language support
- [ ] Mobile app (Android/iOS)
- [ ] Real-time collaborative exams
- [ ] AI-powered answer evaluation

---

<div align="center">

**⭐ Star this repository if you found it helpful!**

Made with ❤️ for OOP learning and hackathon demo

</div>
