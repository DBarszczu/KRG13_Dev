# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.8)

# Declares and names the project.

project("libraryjni")


add_library(arm
        STATIC IMPORTED
        )
set_target_properties(arm
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libarm.a)

add_library( # Sets the name of the library.
        libraryjni
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        es_rgil.cpp)

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

target_link_libraries( # Specifies the target library.
        libraryjni
        arm
        ${log-lib})

target_include_directories(libraryjni PRIVATE ${CMAKE_CURRENT_SOURCE_DIR})