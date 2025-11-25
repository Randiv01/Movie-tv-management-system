# 🎬 MovieHub - Online Movie and TV Series Browsing System

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JSP](https://img.shields.io/badge/JSP-007396?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

**A comprehensive web-based platform for browsing, managing, and reviewing movies and TV series**

[Features](#-features) • [Installation](#-installation--setup) • [Architecture](#-architecture) • [Documentation](#-documentation)

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [Database Schema](#-database-schema)
- [Installation & Setup](#-installation--setup)
- [Project Structure](#-project-structure)
- [Key Components](#-key-components)
- [OOP Concepts Implemented](#-oop-concepts-implemented)
- [Usage Guide](#-usage-guide)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🎯 Overview

**MovieHub** is a full-featured web application designed for browsing, managing, and reviewing movies and TV series. Built using Java Enterprise Edition technologies, it provides a seamless experience for both regular users and administrators. The system supports user registration, content browsing, watchlist management, reviews, and comprehensive admin controls.

### Key Highlights

- **Dual User System**: Separate interfaces for regular users and administrators
- **Content Management**: Full CRUD operations for movies and TV series
- **User Engagement**: Watchlists, reviews, ratings, and user feedback
- **Security**: Password hashing (SHA-256), session management, and input validation
- **Modern UI**: Responsive design with intuitive navigation
- **OOP Principles**: Inheritance, polymorphism, encapsulation, and abstraction

---

## ✨ Features

### 👤 User Features

- **User Authentication**
  - User registration with profile image upload
  - Secure login with password hashing (SHA-256)
  - Password reset functionality
  - Session management

- **Content Browsing**
  - Browse movies and TV series with detailed information
  - Search and filter by genre, rating, year, and keywords
  - View movie/TV series details (cast, director, trailer, etc.)
  - Download links for available content

- **Watchlist Management**
  - Add movies and TV series to personal watchlist
  - Remove items from watchlist
  - Search within watchlist
  - Separate watchlists for movies and TV series

- **Reviews & Ratings**
  - Submit detailed reviews for movies and TV series
  - Include ratings, pros, cons, and recommendations
  - Edit and delete own reviews
  - View all reviews for a specific title

- **User Profile**
  - View and edit personal information
  - Update profile picture
  - View personal reviews and watchlists
  - Notification system

- **Contact & Support**
  - Contact form for user inquiries
  - View admin responses to messages
  - FAQ section

### 🔐 Admin Features

- **User Management**
  - View all registered users
  - Update user information
  - Delete user accounts
  - View user statistics

- **Content Management**
  - Add new movies and TV series
  - Update existing content
  - Delete movies and TV series
  - Manage content availability

- **Admin Management**
  - Add new admin accounts
  - Update admin information
  - Delete admin accounts
  - Admin type classification

- **Feedback Management**
  - View all user messages
  - Respond to user inquiries
  - Manage feedback with attachments
  - Update and delete feedback records

- **Analytics & Reports**
  - View platform statistics
  - Monitor user activity
  - Content performance metrics

---

## 🛠 Technology Stack

### Backend
- **Java SE** - Core programming language
- **Java Servlets** - Server-side request handling
- **JSP (JavaServer Pages)** - Dynamic web page generation
- **JDBC** - Database connectivity

### Frontend
- **HTML5** - Markup language
- **CSS3** - Styling and layout
- **JavaScript** - Client-side interactivity
- **Bootstrap Icons** - Icon library
- **Font Awesome** - Additional icons

### Database
- **MySQL** - Relational database management system
- **JDBC Driver** - MySQL Connector/J 5.1.48

### Server
- **Apache Tomcat** - Servlet container and web server

### Libraries & Dependencies
- **JSTL (JSP Standard Tag Library)** - JSP tag library
- **Jakarta Servlet API** - Servlet specification
- **JUnit 4** - Unit testing framework

---

## 🏗 Architecture

The project follows a **Model-View-Controller (MVC)** architectural pattern with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────┐
│                      Presentation Layer                  │
│  (JSP Pages - View)                                      │
│  - Home.jsp, Movies.jsp, UserProfile.jsp, etc.          │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────────┐
│                   Controller Layer                       │
│  (Servlets - Request Handling)                           │
│  - LoginServlet, ReviewInsertServlet, etc.              │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────────┐
│                    Service Layer                         │
│  (Business Logic & Data Access)                         │
│  - UserDBUtil, MovieDBUTIL, ReviewDBUtil, etc.          │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────────┐
│                     Model Layer                          │
│  (Data Models)                                           │
│  - User, Movie, TVSeries, Review, Watchlist, etc.       │
└──────────────────┬──────────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────────┐
│                   Database Layer                         │
│  (MySQL Database)                                         │
│  - online_mt_system                                      │
└─────────────────────────────────────────────────────────┘
```

### Design Patterns Used

1. **Singleton Pattern**: Database connection management (`DBConnect`)
2. **DAO Pattern**: Data Access Object interface (`UserDAO`)
3. **MVC Pattern**: Separation of Model, View, and Controller
4. **Factory Pattern**: Object creation in service layer

---

## 🗄 Database Schema

The system uses a MySQL database named `online_mt_system` with the following main tables:

### Core Tables

#### `user`
- `id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `fname` (VARCHAR)
- `lname` (VARCHAR)
- `gmail` (VARCHAR, UNIQUE)
- `password` (VARCHAR) - SHA-256 hashed
- `country` (VARCHAR)
- `gender` (VARCHAR)
- `dob` (DATE)
- `mobile` (VARCHAR)
- `profileimage` (VARCHAR)
- `nic` (VARCHAR)
- `address` (VARCHAR)

#### `admin`
- `aid` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `agmail` (VARCHAR, UNIQUE) - Must end with @my.moviehub.com
- `apassword` (VARCHAR) - SHA-256 hashed
- `afullname` (VARCHAR)
- `agender` (VARCHAR)
- `adob` (DATE)
- `amobile` (VARCHAR)
- `aaddress` (VARCHAR)
- `anic` (VARCHAR)
- `admintype` (VARCHAR)

#### `movie`
- `movieid` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `mname` (VARCHAR)
- `description` (TEXT)
- `genre` (VARCHAR)
- `release_date` (DATE)
- `duration` (VARCHAR)
- `language` (VARCHAR)
- `director` (VARCHAR)
- `cast` (TEXT)
- `rating` (VARCHAR)
- `image` (VARCHAR)
- `trailer_url` (VARCHAR)
- `availability` (VARCHAR)
- `download_link` (VARCHAR)

#### `tvseries`
- `tsid` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `title` (VARCHAR)
- `description` (TEXT)
- `genre` (VARCHAR)
- `language` (VARCHAR)
- `release_date` (DATE)
- `seasons` (INT)
- `episodes` (INT)
- `duration_per_episode` (VARCHAR)
- `rating` (DOUBLE)
- `poster_url` (VARCHAR)
- `trailer_url` (VARCHAR)
- `status` (VARCHAR)
- `cast` (TEXT)
- `creator` (VARCHAR)
- `download_link` (VARCHAR)

#### `review`
- `review_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `movie_id` (INT, FOREIGN KEY)
- `user_id` (INT, FOREIGN KEY)
- `movie_name` (VARCHAR)
- `username` (VARCHAR)
- `rating` (INT)
- `review_title` (VARCHAR)
- `comment` (TEXT)
- `pros` (TEXT)
- `cons` (TEXT)
- `recommend` (VARCHAR)
- `review_date` (DATETIME)

#### `tvsreview`
- `tv_review_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `tv_series_id` (INT, FOREIGN KEY)
- `user_id` (INT, FOREIGN KEY)
- `tv_series_name` (VARCHAR)
- `username` (VARCHAR)
- `season` (INT)
- `episode` (VARCHAR)
- `rating` (INT)
- `review_title` (VARCHAR)
- `comment` (TEXT)
- `pros` (TEXT)
- `cons` (TEXT)
- `recommend` (VARCHAR)
- `review_date` (DATE)
- `screenshot` (VARCHAR)

#### `watchlist`
- `watchlist_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `user_id` (INT, FOREIGN KEY)
- `movie_id` (INT, FOREIGN KEY)
- `movie_title` (VARCHAR)
- `genre` (VARCHAR)
- `rating` (VARCHAR)
- `director` (VARCHAR)
- `release_date` (DATE)
- `added_date` (TIMESTAMP)
- `movie_poster` (VARCHAR)

#### `TVSeriesWatchlist`
- `id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `user_id` (INT, FOREIGN KEY)
- `tv_series_id` (INT, FOREIGN KEY)
- `title` (VARCHAR)
- `genre` (VARCHAR)
- `rating` (DOUBLE)
- `creator` (VARCHAR)
- `release_date` (DATE)
- `poster_url` (VARCHAR)
- `added_on` (TIMESTAMP)

#### `contactus`
- `mid` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `cid` (INT)
- `cfname` (VARCHAR)
- `cgmail` (VARCHAR)
- `cmobile` (VARCHAR)
- `cmessage` (TEXT)
- `datetime` (DATETIME)

#### `feedback`
- `feedback_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
- `message_id` (INT, FOREIGN KEY)
- `user_id` (INT, FOREIGN KEY)
- `admin_id` (INT, FOREIGN KEY)
- `admin_name` (VARCHAR)
- `admin_email` (VARCHAR)
- `admin_mobile` (VARCHAR)
- `category` (VARCHAR)
- `language` (VARCHAR)
- `feedback_message` (TEXT)
- `feedback_datetime` (TIMESTAMP)
- `attachment_file` (VARCHAR)

---

## 🚀 Installation & Setup

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher
- **Apache Tomcat** 9.0 or higher
- **MySQL Server** 5.7 or higher
- **IDE** (Eclipse, IntelliJ IDEA, or NetBeans)
- **MySQL Connector/J** (included in `WEB-INF/lib`)

### Step-by-Step Installation

#### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/Online_movie_and_tv_series_browsing_system.git
cd Online_movie_and_tv_series_browsing_system
```

#### 2. Database Setup

1. Start MySQL server
2. Create the database:
   ```sql
   CREATE DATABASE online_mt_system;
   USE online_mt_system;
   ```

3. Create the required tables (you'll need to create SQL scripts based on the schema above, or import from existing database dump if available)

4. Update database credentials in `DBConnect.java`:
   ```java
   // File: src/main/java/controller/DBConnect.java
   private static final String url = "jdbc:mysql://localhost:3306/online_mt_system";
   private static final String username = "root";  // Your MySQL username
   private static final String password = "your_password";  // Your MySQL password
   ```

   Also update in `src/main/java/admincontrroller/DBConnect.java`

#### 3. Configure Tomcat Server

1. Download and install Apache Tomcat
2. Configure Tomcat in your IDE:
   - Set Tomcat installation directory
   - Configure server port (default: 8080)
   - Add the project as a web application

#### 4. Build and Deploy

1. **Using IDE (Eclipse/IntelliJ)**:
   - Import the project
   - Configure build path to include all JAR files in `WEB-INF/lib`
   - Clean and build the project
   - Deploy to Tomcat server
   - Start the server

2. **Using Command Line**:
   ```bash
   # Compile Java files
   javac -cp "WEB-INF/lib/*" src/main/java/**/*.java
   
   # Package as WAR (if using Maven/Gradle)
   # Or deploy directly to Tomcat webapps folder
   ```

#### 5. Access the Application

- Open your browser and navigate to:
  ```
  http://localhost:8080/Online_movie_and_tv_series_browsing_system/
  ```

#### 6. Initial Admin Account

Create an admin account in the database with email ending in `@my.moviehub.com`:

```sql
INSERT INTO admin (agmail, apassword, afullname, agender, adob, amobile, aaddress, anic, admintype)
VALUES ('admin@my.moviehub.com', SHA2('your_password', 256), 'Admin Name', 'Male', '1990-01-01', '1234567890', 'Address', 'NIC123', 'Super Admin');
```

**Note**: The system uses SHA-256 hashing for passwords. Make sure to hash passwords before inserting into the database.

---

## 📁 Project Structure

```
Online_movie_and_tv_series_browsing_system/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── admincontrroller/
│       │   │   └── DBConnect.java          # Admin database connection
│       │   │
│       │   ├── adminmodel/
│       │   │   ├── Admin.java               # Admin model class
│       │   │   └── Feedback.java            # Feedback model class
│       │   │
│       │   ├── adminservice/
│       │   │   ├── AdminDBUtil.java         # Admin data access operations
│       │   │   ├── FeedbackDBUtil.java      # Feedback management
│       │   │   ├── ManageMovieDBUTIL.java   # Movie management for admin
│       │   │   ├── ManageTVSeriesDBUTIL.java # TV Series management
│       │   │   ├── MessageDBUtil.java       # Message handling
│       │   │   └── TVSeriesDBUTIL.java       # TV Series data access
│       │   │
│       │   ├── adminservlet/
│       │   │   ├── adminlogoutservlet.java
│       │   │   ├── AlladminDeleteServlet.java
│       │   │   ├── AllAdminUpdateServlet.java
│       │   │   ├── AllmessageServlet.java
│       │   │   ├── AllUserDeleteServlet.java
│       │   │   ├── AlluserUpdateServlet.java
│       │   │   ├── DeleteFeedbackServlet.java
│       │   │   ├── DeleteMovieServlet.java
│       │   │   ├── DeleteTVSeriesServlet.java
│       │   │   ├── Displayadminservlet.java
│       │   │   ├── DisplayAllUserServlet.java
│       │   │   ├── DisplayMovieServlet.java
│       │   │   ├── DisplayTVSeriesServlet.java
│       │   │   ├── InsertadminServlet.java
│       │   │   ├── InsertFeedbackServlet.java
│       │   │   ├── ManageFeedbackServlet.java
│       │   │   ├── ManageMovieServlet.java
│       │   │   ├── MovieInsertServlet.java
│       │   │   ├── TVSeriesInsertServlet.java
│       │   │   ├── UpdateFeedbackServlet.java
│       │   │   ├── UpdateMovieServlet.java
│       │   │   └── UpdateTVSeriesServlet.java
│       │   │
│       │   ├── controller/
│       │   │   └── DBConnect.java           # Main database connection (Singleton)
│       │   │
│       │   ├── model/
│       │   │   ├── ContactUs.java           # Contact message model
│       │   │   ├── Media.java               # Abstract base class for media
│       │   │   ├── Movie.java                # Movie model (extends Media)
│       │   │   ├── Person.java               # Abstract base class for users
│       │   │   ├── Review.java               # Movie review model
│       │   │   ├── TVSeries.java             # TV Series model (extends Media)
│       │   │   ├── TVSeriesWatchlist.java    # TV Series watchlist model
│       │   │   ├── TVSReview.java            # TV Series review model
│       │   │   ├── User.java                 # User model (extends Person)
│       │   │   └── Watchlist.java            # Movie watchlist model
│       │   │
│       │   └── service/
│       │       ├── ContactUsDBUtil.java      # Contact form data access
│       │       ├── MovieDBUTIL.java          # Movie data operations
│       │       ├── PasswordUtil.java         # Password utility functions
│       │       ├── ReviewDBUtil.java         # Review data operations
│       │       ├── TVSRBDUtil.java            # TV Series review operations
│       │       ├── TVSriesDBUtil.java        # TV Series data operations
│       │       ├── UserDAO.java              # User DAO interface
│       │       ├── UserDBUtil.java           # User data operations
│       │       └── WatchListDBUtil.java      # Watchlist operations
│       │
│       └── webapp/
│           ├── CSS/                         # Stylesheets
│           ├── JS/                           # JavaScript files
│           ├── Images/                       # Image assets
│           ├── Movieimages/                  # Movie posters
│           ├── TVSeriesImages/                # TV Series posters
│           ├── ProfileImages/                 # User profile images
│           ├── MovieDFiles/                  # Movie download files
│           ├── TVSDFiles/                    # TV Series download files
│           ├── uploads/                      # Uploaded files
│           │
│           ├── *.jsp                         # JSP pages (views)
│           │   ├── Home.jsp                  # Home page
│           │   ├── Login.jsp                 # Login page
│           │   ├── Register.jsp              # Registration page
│           │   ├── Movies.jsp                # Movies listing
│           │   ├── Movieinfo.jsp             # Movie details
│           │   ├── tv_series.jsp             # TV Series listing
│           │   ├── tv_seriesinfo.jsp         # TV Series details
│           │   ├── UserProfile.jsp           # User profile
│           │   ├── MyWatchlist.jsp           # Movie watchlist
│           │   ├── MyTVSWatchlist.jsp        # TV Series watchlist
│           │   ├── Myreviews.jsp             # User reviews
│           │   ├── ContactUs.jsp             # Contact form
│           │   ├── FAQ.jsp                   # FAQ page
│           │   ├── AdminHome.jsp             # Admin dashboard
│           │   ├── ManageUsers.jsp           # User management
│           │   ├── ManageMovies.jsp          # Movie management
│           │   ├── ManageTVSeries.jsp        # TV Series management
│           │   ├── InsertMovie.jsp           # Add movie form
│           │   ├── InsertTVSeries.jsp        # Add TV Series form
│           │   ├── ManageFeedback.jsp        # Feedback management
│           │   └── ... (other JSP files)
│           │
│           └── WEB-INF/
│               ├── lib/                      # JAR dependencies
│               │   ├── mysql-connector-java-5.1.48.jar
│               │   ├── servlet-api.jar
│               │   ├── jstl-1.2.jar
│               │   └── ... (other JARs)
│               │
│               └── web.xml                   # Web application configuration
│
├── build/                                    # Compiled class files
└── README.md                                 # This file
```

---

## 🔑 Key Components

### 1. Database Connection (`DBConnect.java`)

Implements **Singleton Pattern** for efficient database connection management:

```java
public class DBConnect {
    private static Connection connection = null;
    
    public static synchronized Connection getConnection() {
        // Lazy initialization with thread safety
        if (connection == null || connection.isClosed()) {
            // Create connection
        }
        return connection;
    }
}
```

### 2. User Authentication

- **Password Hashing**: SHA-256 algorithm
- **Session Management**: HTTP sessions for user state
- **Role-Based Access**: Separate admin and user authentication

### 3. Model Classes

#### Inheritance Hierarchy

```
Person (Abstract)
├── User
└── Admin

Media (Abstract)
├── Movie
└── TVSeries
```

### 4. Service Layer

- **UserDBUtil**: Implements `UserDAO` interface (polymorphism)
- **MovieDBUTIL**: Movie CRUD operations
- **ReviewDBUtil**: Review management
- **WatchListDBUtil**: Watchlist operations

### 5. Servlet Layer

- **Request Handling**: Process HTTP requests
- **Session Management**: Maintain user sessions
- **Response Generation**: Redirect or forward to JSP pages

---

## 🎓 OOP Concepts Implemented

### 1. **Inheritance**

- `User` and `Admin` extend `Person` (abstract class)
- `Movie` and `TVSeries` extend `Media` (abstract class)
- Code reusability and hierarchical organization

### 2. **Polymorphism**

- `User` and `Admin` override `displayProfile()` method from `Person`
- `UserDBUtil` implements `UserDAO` interface
- Method overloading in constructors

### 3. **Encapsulation**

- Private fields with public getters
- Data hiding and controlled access
- Example: `User` class with private fields and getter methods

### 4. **Abstraction**

- `Person` and `Media` as abstract classes
- Abstract method `displayProfile()` in `Person`
- Interface `UserDAO` for data access abstraction

### 5. **Design Patterns**

- **Singleton**: Database connection management
- **DAO Pattern**: Data access abstraction
- **MVC**: Separation of concerns

---

## 📖 Usage Guide

### For Regular Users

1. **Registration**
   - Navigate to Register page
   - Fill in personal details
   - Upload profile picture (optional)
   - Submit registration

2. **Login**
   - Enter email and password
   - System validates credentials
   - Redirects to home page upon success

3. **Browsing Content**
   - Browse movies from Movies page
   - Browse TV series from TV Series page
   - Use search and filter options
   - Click on title to view details

4. **Watchlist**
   - Add movies/TV series to watchlist
   - View watchlist from profile
   - Remove items as needed

5. **Reviews**
   - Write reviews for movies/TV series
   - Include rating, pros, cons
   - Edit or delete your reviews

### For Administrators

1. **Admin Login**
   - Use email ending with `@my.moviehub.com`
   - Enter admin credentials
   - Access admin dashboard

2. **User Management**
   - View all users
   - Update user information
   - Delete user accounts

3. **Content Management**
   - Add new movies/TV series
   - Update existing content
   - Delete content
   - Manage availability

4. **Feedback Management**
   - View user messages
   - Respond to inquiries
   - Manage feedback records

---

## 🖼 Screenshots

> **Note**: Add screenshots of your application here. Include:
> - Home page
> - Login/Registration pages
> - Movie/TV Series browsing
> - User profile
> - Admin dashboard
> - Watchlist and reviews

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Contribution Guidelines

- Follow Java coding conventions
- Add comments for complex logic
- Update documentation as needed
- Test your changes thoroughly

---

## 📝 License

This project is developed as part of an academic course (SE_OOP_2025_S1_KND_WD_G15) at SLIIT (Sri Lanka Institute of Information Technology).

**Note**: This is an educational project. Please ensure compliance with copyright laws when using actual movie/TV series content.

---

## 👥 Authors

- **Group G15** - SE_OOP_2025_S1_KND_WD_G15
  - Initial development and implementation

---

## 🙏 Acknowledgments

- SLIIT (Sri Lanka Institute of Information Technology)
- Course instructors and teaching assistants
- Open-source community for libraries and tools

---

## 📧 Contact & Support

For questions, issues, or contributions:

- **Repository**: [GitHub Repository URL]
- **Issues**: [GitHub Issues Page]
- **Email**: [Your Contact Email]

---

## 🔮 Future Enhancements

Potential improvements for future versions:

- [ ] Real-time streaming capabilities
- [ ] Advanced recommendation system using ML
- [ ] Social features (friends, sharing)
- [ ] Mobile application (Android/iOS)
- [ ] Payment integration for premium content
- [ ] Advanced analytics dashboard
- [ ] Multi-language support
- [ ] RESTful API development
- [ ] Cloud deployment (AWS/Azure)
- [ ] Enhanced security features (2FA, OAuth)

---

<div align="center">

**Made with ❤️ by Group G15**

⭐ Star this repo if you find it helpful!

</div>

