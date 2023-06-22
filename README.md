# ExpiryDB

ExpiryDB is a lightweight Android library that provides storage operations with expiry time for objects using the Paper library. It allows you to store and retrieve objects with an expiry time, automatically handling the expiration of stored objects.

## Features

- Save objects with expiry time in Book storage.
- Retrieve objects from storage and automatically check for expiry.
- Delete objects from storage.
- Destroy the entire storage.
- Retrieve all keys of objects stored in the storage.

## Installation

To use ExpiryDB in your Android project, follow these steps:

1. In `settings.gradle`, add maven `{ url 'https://jitpack.io' }`

**Groovy**
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven { url 'https://jitpack.io' }
    }
}
```
**Kotlin**
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven ("https://jitpack.io")
    }
}
```
> Note: If you add this in `build.gradle` (project level), it won't work. You must add it in the `settings.gradle` instead.

2. Add the ExpiryDB dependency to your app-level `build.gradle` file:

**Groovy**
```groovy
dependencies {
    ...
    implementation 'com.github.rdnasim:ExpiryDB:1.0.2'
}
```
**Kotlin**
```kotlin
dependencies {
    ...
    implementation ("com.github.rdnasim:ExpiryDB:1.0.2")
}
```

## Usage
1. Import the library package
```kotlin
import com.riadul.expirydb.ExpiryDB
```
2. Initialize ExpiryDB with your application context:
```kotlin
ExpiryDB.init(applicationContext)
```
3. Use the book() method to get an instance of ExpiryDBBook for performing storage operations:
```kotlin
val dbBook = ExpiryDB.book()
```
4. Save an object with an expiry time:
```kotlin
val key = "my_object"
val value = MyObject()
val expiryTimeMillis = 60000L // Expiry time in milliseconds

dbBook.write(key, value, expiryTimeMillis)
```
5. Retrieve an object from storage:
```kotlin
val retrievedValue = dbBook.read(key)
```
The retrieved value will be `null` if the object has expired or does not exist.

6. Delete an object from storage:
```kotlin
dbBook.delete(key)
```
7. Retrieve all keys of objects stored in the storage:
```kotlin
val keys = dbBook.getAllKeys()
```
## License
This project is licensed under the Apache License - see the [LICENSE](https://github.com/rdnasim/expirydb/blob/main/LICENSE) file for details.
