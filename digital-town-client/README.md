# swagger-android-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-android-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-android-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-android-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.api.DefaultApi;

public class DefaultApiExample {

    public static void main(String[] args) {
        DefaultApi apiInstance = new DefaultApi();
        String authorization = "authorization_example"; // String | 
        try {
            apiInstance.getUser(authorization);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#getUser");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://api.digitaltown.com/sso*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**getUser**](docs/DefaultApi.md#getUser) | **GET** /users | 
*DefaultApi* | [**registerUser**](docs/DefaultApi.md#registerUser) | **POST** /users | 
*DefaultApi* | [**tokenAutorizationCode**](docs/DefaultApi.md#tokenAutorizationCode) | **POST** /token | 
*DefaultApi* | [**tokenRefreshCode**](docs/DefaultApi.md#tokenRefreshCode) | **POST** /token/refresh | 
*DefaultApi* | [**updateUser**](docs/DefaultApi.md#updateUser) | **PUT** /users | 
*DefaultApi* | [**usersClientsGet**](docs/DefaultApi.md#usersClientsGet) | **GET** /users/clients | 
*DefaultApi* | [**usersClientsPost**](docs/DefaultApi.md#usersClientsPost) | **POST** /users/clients | 
*DefaultApi* | [**usersClientsPut**](docs/DefaultApi.md#usersClientsPut) | **PUT** /users/clients | 


## Documentation for Models

 - [ClientCreate](docs/ClientCreate.md)
 - [ClientCreate1](docs/ClientCreate1.md)
 - [ClientUpdate](docs/ClientUpdate.md)
 - [ClientUpdate1](docs/ClientUpdate1.md)
 - [Token](docs/Token.md)
 - [Token1](docs/Token1.md)
 - [TokenRefresh](docs/TokenRefresh.md)
 - [TokenRefresh1](docs/TokenRefresh1.md)
 - [UserCreate](docs/UserCreate.md)
 - [UserCreate1](docs/UserCreate1.md)
 - [Users](docs/Users.md)
 - [UsersEdit](docs/UsersEdit.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



