# Weather App 🌤️

## Overview
This is a weather application built using **Jetpack Compose** for the UI and integrated with the Weatherbit API to fetch weather data. The app displays the current weather based on the user's location and allows users to search for weather information in other cities.

## Features
- **Current Location Weather**: Automatically fetches and displays weather data for the user's current location.
- **Search for City Weather**: Allows users to search for weather information in different cities.
- **State Management**: Implements ViewModel and LiveData to manage the UI state.

## Screenshots
![Screenshot_2024-08-26-14-08-58-63_ab1359306de43320f9557c797b1c4be5](https://github.com/user-attachments/assets/a4476c65-871d-451f-901e-9ee70539507f)
![1](https://github.com/user-attachments/assets/e11690d1-eb2a-4aff-8cfc-51835e05f032)
![IMG_20240826_140630](https://github.com/user-attachments/assets/df989303-c9cb-4ee5-973e-2e48790377d6)
![3](https://github.com/user-attachments/assets/20424c98-7b19-4600-a4bf-cf003150b176)
![2](https://github.com/user-attachments/assets/1a184346-9c5e-486f-a431-52b2347ce0e8)



## Technologies Used
- **Kotlin**: The programming language used for development.
- **Jetpack Compose**: For building the UI.
- **Retrofit**: For making network requests to the Weatherbit API.
- **ViewModel & LiveData**: For state management.
- **Weatherbit API**: The API used to fetch weather data.

## Installation
To run this project locally:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/weather-app.git
    cd weather-app
    ```

2. **Add your API Key**:
   - Open the `local.properties` file in your project root.
   - Add your Weatherbit API key:
     ```
     WEATHER_API_KEY=your_api_key_here
     ```

3. **Build and Run the project** in Android Studio.
