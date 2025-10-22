# 🚀 Quick Start Guide - Secure Online Exam System

## One-Command Setup

```bash
# Clone and run (for first time)
git clone <repository-url>
cd online-exam-system
mvn clean install && mvn spring-boot:run
```

## If Already Downloaded

```bash
# Just run
mvn spring-boot:run
```

## Access URLs

Once running, open these in your browser:

1. **Main Application**: http://localhost:8080
2. **H2 Database Console**: http://localhost:8080/h2-console
3. **Health Check**: http://localhost:8080/actuator/health

## Login Credentials

### Admin Account
- **Username**: `admin`
- **Password**: `admin123`
- **Access**: Full system control, create exams, view analytics

### Student Accounts
- **Username**: `student1` to `student5`
- **Password**: `pass123` (same for all)
- **Access**: Take exams, view results

## What You'll See On First Run

```
╔════════════════════════════════════════════════════════╗
║  Secure Online Examination System Started Successfully!║
║  Access: http://localhost:8080                         ║
║  H2 Console: http://localhost:8080/h2-console          ║
╚════════════════════════════════════════════════════════╝

[FACTORY] Created MCQExam with auto-grading strategy
[FACTORY] Created CodingExam with manual grading strategy
[FACTORY] Created EssayExam with manual grading strategy

✓ Created Admin: admin/admin123
✓ Created 5 Students: student1-5/pass123
✓ Created MCQ Exam with 10 questions
✓ Created Coding Exam with 3 questions
✓ Created Essay Exam with 3 questions
```

## Quick Tour (5 minutes)

### Step 1: Student Flow (2 min)
1. Go to http://localhost:8080
2. Login as `student1` / `pass123`
3. Click "Start Exam" on "Java OOP Fundamentals"
4. Answer questions (select any options)
5. Click "Submit Exam"
6. View your score and grade!

### Step 2: Admin Flow (2 min)
1. Logout → Login as `admin` / `admin123`
2. See system dashboard with all exams and students
3. Click "Create Exam" → Try creating a new exam
4. Click "Analytics" → See charts (select an exam from dropdown)

### Step 3: Database Inspection (1 min)
1. Go to http://localhost:8080/h2-console
2. Use these credentials:
   - JDBC URL: `jdbc:h2:mem:examdb`
   - Username: `admin`
   - Password: `admin`
3. Click "Connect"
4. Run query: `SELECT * FROM STUDENTS`

## Running Tests

```bash
# Run all JUnit tests
mvn test

# Run specific test class
mvn test -Dtest=ExamServiceTest

# Run with coverage report
mvn clean test jacoco:report
```

## Building JAR for Deployment

```bash
# Build executable JAR
mvn clean package

# Run the JAR
java -jar target/online-exam-system-1.0.0.jar
```

## Stopping the Application

- **In Terminal**: Press `Ctrl + C`
- **Maven Process**: Will stop gracefully
- **Database**: H2 in-memory data will be lost (resets on next run)

## Troubleshooting

### Port 8080 already in use?
```bash
# Option 1: Kill process on port 8080
# On Linux/Mac:
lsof -ti:8080 | xargs kill -9

# On Windows:
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Option 2: Change port in src/main/resources/application.yml
server.port: 8081
```

### Tests failing?
```bash
# Clean build
mvn clean test

# Skip tests and just run
mvn spring-boot:run -DskipTests
```

### Can't see demo data?
- Demo data is auto-loaded via `DataInitializer.java`
- Check console for initialization messages
- If missing, restart application

## System Requirements

- ✅ **Java 17 or higher** (required)
- ✅ **Maven 3.8+** (required)
- ✅ **8 GB RAM** (recommended)
- ✅ **Modern browser** (Chrome, Firefox, Safari, Edge)
- ✅ **Internet connection** (for downloading dependencies on first run)

## Verify Installation

```bash
# Check Java version
java -version
# Should show: java version "17" or higher

# Check Maven version
mvn -version
# Should show: Apache Maven 3.8.x or higher
```

## Directory Structure Overview

```
online-exam-system/
├── pom.xml                          # Maven dependencies
├── README.md                        # Full documentation
├── QUICK_START.md                   # This file
├── DEMO_SCRIPT.md                   # Hackathon demo guide
│
├── src/main/java/com/examsystem/
│   ├── OnlineExamSystemApplication.java  # Start here
│   ├── entity/                      # Exam, Student, Question
│   ├── service/                     # Business logic
│   ├── controller/                  # REST & web controllers
│   ├── factory/                     # Factory pattern
│   ├── strategy/                    # Strategy pattern
│   └── ...
│
├── src/main/resources/
│   ├── application.yml              # Configuration
│   └── templates/                   # HTML pages
│
└── src/test/java/                   # JUnit tests
```

## Next Steps

1. ✅ **Run the app** (you're here!)
2. 📖 **Read full README.md** for architecture details
3. 🎬 **Check DEMO_SCRIPT.md** for presentation guide
4. 🧪 **Run tests** to see OOP concepts in action
5. 🎓 **Explore code** to understand design patterns

## Key Features to Try

- 🏭 **Factory Pattern**: Create different exam types in Admin
- 🎯 **Strategy Pattern**: Compare MCQ auto-grading vs manual
- 🌊 **Streams API**: Check Analytics dashboard
- 🔄 **Concurrency**: Open multiple tabs, submit exams simultaneously
- 🔍 **Reflection**: Click "Metadata" button in Admin dashboard

## Getting Help

- 📖 **Full Documentation**: See README.md
- 🐛 **Issues**: Check console for error messages
- 💬 **Questions**: Review DEMO_SCRIPT.md Q&A section

---

**Ready to explore? Start the app and go to http://localhost:8080** 🚀
