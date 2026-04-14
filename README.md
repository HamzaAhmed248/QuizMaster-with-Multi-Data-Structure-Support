QuizMaster with Multi-Data Structure Support
An interactive Java console application designed to manage quizzes using two distinct data structures: Singly Linked Lists and Arrays. The project features a real-time countdown timer to simulate a high-pressure testing environment.

Features
Dual Data Structure Implementation: * Linked List: Efficiently manages dynamic student registration and question storage.
Array: Provides a fixed-memory alternative for performance comparison.
Multi-threaded Timer: A background thread monitors the time remaining. Once the 10-second limit is reached, the quiz interrupts automatically.
Student Validation: Includes logic to ensure every student has a unique ARID and prevents students from taking the quiz more than once.
Automatic Scoring: Calculates marks instantly (2 points per correct answer) and generates a leaderboard.

Hardware & Software
Language: Java (JDK 8+)
Concepts Used: Object-Oriented Programming (OOP), Multi-threading, Data Structures (Linked Lists).

Input Type,Logic
ARID,Verified using a do-while loop for uniqueness in the Linked List.
Timer,Thread.sleep(1000) loop running in a separate QuizTimer class.
Questions,Stored as Question objects with 4 options and a correct index.
