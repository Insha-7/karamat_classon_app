# Classon App

Classon is an Android app designed to help students manage their academic and extracurricular schedules efficiently. The app provides an integrated platform for class reminders, announcements, event tracking, and timetable management.

## Features

- **Sign In and Sign Up Pages:**  
  Secure and user-friendly authentication to register or log in to the app.

- **Swipable and Scrollable Academics Section:**  
  View your academic class schedule for each weekday in a tabbed, swipeable view. Each day’s timetable is scrollable for easy exploration.

- **Swipable and Scrollable Axios Section:**  
  Similar to academics, the Axios section presents extracurricular schedules in a swipeable, scrollable, day-wise view.

- **Expandable Cards for Announcements:**  
  Announcements (like assignments, exams, deadlines) are shown in expandable cards—tap any card to view more details.

- **Scrollable Cultural Events Section:**  
  Explore upcoming cultural events in a vertically scrollable list, each card displaying event details like date, time, venue, and type.

- **Bottom Navigation Menu:**  
  Quickly switch between Academics, Axios, Announcements, and Cultural Events using a modern bottom navigation bar.

- **Class & Assignment Notifications:**  
  Get timely notifications before classes and assignment deadlines using Android’s built-in notification system (Notification API).

- **Day-wise Timetable with Tabs:**  
  Both Academics and Axios sections use tab layouts for weekdays, letting users swipe between different days.

- **Material Design UI:**  
  The app uses Android Material Components for a clean and intuitive interface.

- **RecyclerView-based Lists:**  
  All lists (events, announcements, timetable) are implemented with RecyclerView for smooth scrolling and efficient memory usage.

- **Fragment-based Navigation:**  
  Each major section is implemented as a fragment, ensuring smooth navigation and modular code.

## Tech Stack

- **Kotlin** — Main programming language for app logic.
- **Android SDK** — Native Android app development.
- **ViewPager2 & RecyclerView** — For swiping between days and displaying lists.
- **Material Components** — For modern UI and navigation.
- **Android Notification System (Notification API)** — Uses Android’s notification API to remind users about classes and assignments.

## Screens/Fragments

- **Academics:**  
  Swipe through daily class schedules for the week.
- **Axios:**  
  Track extracurricular schedules just like academic ones.
- **Announcements:**  
  View assignments and exam notifications in expandable cards.
- **Cultural Events:**  
  Browse and get details about upcoming events.

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/Insha-7/karamat_classon_app.git
    ```
2. Open the project in Android Studio.
3. Build and run the app on your device or emulator.

## Permissions

- Notification permissions are required for class reminders.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

---
