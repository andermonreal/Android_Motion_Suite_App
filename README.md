# Android_Motion_Suite_App
---

There are two folders, each containing a different application. It is recommended to use Android Studio to work with these applications. It is very important to note that both applications require Back4App credentials to function properly. These credentials must be entered in the res/values/strings.xml file where it says "MODIFY".

The "MaquinaTrajeDeMovimiento" app simulates the movement suit machine. In this app, users can input various values related to the state of the movement suit. This includes adjusting parameters and settings that reflect the operational status and conditions of the suit, providing a comprehensive simulation experience.

The "TrajeDeMovimiento" app simulates five sensors corresponding to the head, right hand, left hand, right foot, and left foot. Users can input the coordinates for each sensor, reflecting their positions in real-time. This allows for a detailed representation of the suit's movements and positions, making it possible to track and analyze each sensor's data accurately.

Both applications are synchronized, allowing each sensor simulation to be assigned to a specific suit. This integration ensures that the data from the "TrajeDeMovimiento" app is accurately reflected in the "MaquinaTrajeDeMovimiento" app. All simulation data is stored in a Back4App database, ensuring that all changes and states are recorded and can be accessed for analysis and further development.

