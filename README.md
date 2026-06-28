ANZBank Assignment – Android App

1- Project Overview

This is an Android app built with Kotlin ,Jetpack Compose, Coroutines, Hilt, and Retrofit/Gson.

It showcases:

- [ ] Display a list of users from a public API.

- [ ] Navigating to a user detail screen using Compose Navigation.

- [ ] Handling loading and error states.

- [ ] Clean Architecture with MVVM and Repository + ViewModel + Composable UI.

- [ ] Unit testing for ViewModels and Repository.

2- Technologies Used

- [ ] Dependency Library

- [ ] Kotlin Language

- [ ] Jetpack Compose for UI

- [ ] For Navigation - Compose Navigation + NavGraph

- [ ] Dependency Injection - Hilt

- [ ] Networking Retrofit + Gson

- [ ] Kotlin Coroutines for asynchronous task

- [ ] Theme Material3 (Theme.kt , Color.kt, Type.kt)

- [ ] Testing JUnit, Mockito, Coroutines Test , Espresso Test

3 - Project Structure - com.example.anzbankassignment

├─ MainApplication.kt -- Hilt Application class

├─ data

    │ ├─ UserDto.kt

    │ ├─ UserRepositoryImpl.kt

    │ └─ remote/UserApi.kt

├─ domain

    │ └─ UserRepository.kt
    │ └─ User.kt

├─ di

    │ └─ NetorkModule.kt      -  Hilt modules

├─ presentation

    │ ├─ MainActivity

    │ ├─ navigation

        │ │ └─ NavGraph.kt # Compose navigation graph

    │ ├─ theme

        │ │ ├─ Color.kt

        │ │ ├─ Theme.kt

        │ │ └─ Type.kt

    │ └─ users

        │ ├─ viewmodel

           │ │ ├─ UsersViewModel.kt

           │ │ └─ UserDetailViewModel.kt

    │ ├─ screens

           │ ├─ UsersScreen.kt

           │ └─ UserDetailScreen.kt

4-  Features

User List Screen

- [ ] Fetches users from API and displays them in a scrollable list.

- [ ] Shows loading indicator while fetching.

- [ ] Handles network errors .

User Detail Screen

- [ ] Displays user details including name, email, phone, company, and profile photo.

- [ ] Handles user not found and network exceptions.

Navigation

- [ ] Uses NavGraph.kt for navigation.

- [ ] Route example: user/{userId} passes userId as argument to detail screen.

Themes

- [ ] Color.kt → color palette

- [ ] Type.kt → typography styles

- [ ] Theme.kt → app-wide Material3 theme

Dependency Injection

- [ ] Hilt provides repository and API instances.

5 - Assumptions

- [ ] API endpoint: https://fake-json-api.mock.beeceptor.com/users

- [ ] All fields in User are Strings .

- [ ] Simple error handling for null users or network failures.

 6 - Demo Video And Screen Shot added in project

- [ ] Folder Path - https://github.com/priyabratam280-anz/ANZBank-Assignment/tree/master/ScreenShotAndVideo

- [ ] Quick Demo Video -  app_demo.webm

- [ ] Screen ScreenShots

- [ ] TestCase ScreenShot
- [ ] Espresso ScreenUiTest video - EspressoScreenUiTest.webm
