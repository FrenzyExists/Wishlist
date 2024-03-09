package com.pikachu.codepathmail

import java.text.DateFormat
import java.util.Calendar


class EmailFetcher {
    companion object {
        private val senders = listOf(
            "Dahlia Cline",
            "Kevin Miranda",
            "Kaya Austin",
            "Laila Calderon",
            "Marquise Rhodes",
            "Fletcher Patel",
            "Luz Barron",
            "Kamren Dudley",
            "Jairo Foster",
            "Lilah Sandoval",
            "Ansley Blake",
            "Slade Sawyer",
            "Jaelyn Holmes",
            "Phoenix Bright",
            "Ernesto Gould"
        )
        private val titles = listOf(
            "Welcome to Kotlin!",
            "Getting Started with Android",
            "Mastering Kotlin Coroutines",
            "Exploring Jetpack Compose",
            "Understanding MVVM Architecture",
            "Kotlin for Beginners",
            "Advanced Android Development",
            "Building Responsive UIs",
            "Efficient Networking in Android",
            "Creating Custom Views in Android",
            "Securing Android Applications",
            "Best Practices in Android Development",
            "Testing Strategies for Android Apps",
            "Exploring Room Database",
            "Handling User Authentication",
            "Android App Monetization",
            "Optimizing App Performance",
            "Deep Dive into Fragments",
            "Exploring Material Design Components",
            "Publishing Your First App",
            "Working with Notifications",
            "Debugging Techniques in Android",
            "Design Patterns in Android",
            "Improving Code Quality",
            "Adopting Clean Architecture",
            "Mastering RecyclerView",
            "Understanding Data Binding",
            "UI/UX Design Principles",
            "Advanced Kotlin Features",
            "Navigation Architecture Components"
        )
        private val summaries = listOf(
            "Welcome to the Android Kotlin Course! We're excited to have you join us and learn how to develop Android apps using Kotlin. Here are some tips to get started.",
            "In this course, you'll dive into the world of Android app development using Kotlin. Get ready to build amazing apps!",
            "Get hands-on experience with Kotlin as you create real-world Android applications. Let's start coding!",
            "Join us on a journey to master Android development with Kotlin. By the end of this course, you'll be building your own apps!",
            "Unlock the power of Kotlin for Android development. From basics to advanced topics, we've got you covered!",
            "Ready to become an Android Kotlin developer? This course will guide you through the process step by step.",
            "Welcome aboard the Kotlin train! Learn how to build modern and efficient Android apps with ease.",
            "Embark on a learning adventure with Kotlin as your companion. Get ready to create awesome Android apps!",
            "Let's kick off this course with a warm welcome! Get ready for an exciting journey into Android app development.",
            "Hello, Android Kotlin enthusiasts! Get ready to explore the latest features and best practices in Android development.",
            "Dive deep into Android app development with Kotlin. We'll cover everything from layouts to networking!",
            "Join us as we explore the world of Android development using the powerful Kotlin programming language.",
            "Become a pro at Android app development with Kotlin. This course will take you from beginner to expert!",
            "Ready to build your first Android app? This course will teach you everything you need to know!",
            "Welcome to the world of Android development! Let's build some amazing apps together using Kotlin.",
            "Get ready to unleash the full potential of Kotlin for Android app development. Let's get coding!",
            "Calling all Android enthusiasts! This course is your gateway to becoming a proficient Kotlin developer.",
            "Start your journey to Android app mastery with Kotlin. By the end of this course, you'll be a pro!",
            "Learn Kotlin the fun way - by building real Android apps! Get hands-on experience and valuable skills.",
            "Join us as we explore the exciting world of Android app development using Kotlin. Let the coding begin!",
            "Welcome to the Android Kotlin Course! Get ready to learn the ins and outs of Android app creation.",
            "Build beautiful, responsive Android apps with Kotlin. This course will teach you everything you need to know.",
            "Start your Android development journey on the right foot with Kotlin. Let's build something amazing!",
            "Get ready to dive into the world of Android app design and development using the versatile Kotlin language.",
            "Welcome to Kotlin for Android Developers! This course will equip you with the skills to create amazing apps.",
            "Join us as we uncover the secrets of Android app development with Kotlin. Let's create something awesome!",
            "Learn Kotlin and create awesome Android apps that users will love. Get started on your development journey!",
            "Welcome to the Android Kotlin Course! Get ready to code, design, and create your own Android apps.",
            "Join us as we explore the endless possibilities of Android app development with Kotlin. Let's innovate!",
            "Ready to take your Android skills to the next level? This course will show you how with Kotlin!"
        )

        private var date = DateFormat.getDateInstance()

        fun getEmails(): MutableList<Email> {
            val emails : MutableList<Email> = ArrayList()
            date.calendar.set(2024, Calendar.MARCH, 21)

            for (i in 0..9) {
                val email = Email(senders[i], titles[titles.indices.random()], summaries[titles.indices.random()], date)
                emails.add(email)
            }
            return emails
        }

        fun getNext5Emails(): MutableList<Email> {
            val newEmails : MutableList<Email> = ArrayList()
            date.calendar.set(2024, Calendar.MARCH, 21)

            for (i in 0..4) {
                val email = Email(senders[senders.indices.random()], titles[titles.indices.random()], summaries[titles.indices.random()], date)
                newEmails.add(email)
            }
            return newEmails
        }
    }
}