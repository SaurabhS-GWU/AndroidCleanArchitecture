# AndroidCleanArchitecture
Android MVVM + Clean Architecture


## Tech Stack

- **Kotlin**: The programming language used for Android development.
- **MVVM**: The Model-View-ViewModel architecture pattern.
- **Clean Architecture**: A way to organize your code into layers to separate concerns.
- **Retrofit**: Used for networking and making API calls.
- **LiveData**: Observes and updates UI when data changes.
- **Kotlin Coroutines**: For managing background threads and asynchronous tasks.
- **Manual Dependency Injection**: Custom dependency injection to provide dependencies manually.


### Detailed Description of Each Folder:

- **`data/`**: Contains the data layer responsible for fetching data from the network or other sources.
  - **`model/`**: Data models used for mapping the network responses.
  - **`remote/`**: Contains the Retrofit API services that define how data is fetched from the network.
  - **`repository/`**: The implementation of the repository interface which abstracts data fetching logic, either from a network source or other sources.

- **`domain/`**: Contains business logic and the domain layer.
  - **`model/`**: Represents business-related data models that are decoupled from the actual data models.
  - **`repository/`**: Defines an interface for repositories to interact with data, which can be implemented in the `data` layer.
  - **`usecase/`**: Contains use cases that encapsulate specific business logic, such as fetching data or performing operations.

- **`presentation/`**: The UI layer that handles displaying data to the user and interacting with the ViewModel.
  - **`ui/`**: Contains fragments or activities that handle the UI.
  - **`viewmodel/`**: ViewModels provide data to the UI and handle data manipulation by calling use cases.

- **`di/`**: Contains the setup for manually providing dependencies to classes (like repositories, use cases, and view models).

- **`MainActivity.kt`**: The main activity that initializes and sets up the ViewModel for displaying the UI.

---

## Features

- Follows **Clean Architecture** with clear separation of concerns.
- Implements **MVVM** design pattern for managing UI-related data.
- Utilizes **Retrofit** for making network requests.
- **LiveData** for observing and updating UI automatically.
- **Kotlin Coroutines** for handling background tasks and async operations.
- **Manual Dependency Injection** to wire dependencies manually.

---

## Setup

To set up this project locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/clean-architecture-android.git

