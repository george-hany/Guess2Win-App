Android Template
---
---

## Summary

This document provides an overview of the development of Modules and Applications .

## Supported OS versions

Minimum SDK version 21 (Android 4.4)
Target SDK version 29 

## Programming languages

Kotlin 

## Architecture

In this section, we demonstrate which used structure in Android Template is **The Modular Architecture**.

#### Overview 

Consider the following diagrams, that show how Android Template layers will interact with each others

![image](/uploads/aef7df308be28c3f8fc7c5cd27436037/Items.png)

## Platform Architecture

Android Template developed based on Modular Architecture

There are 3 main layers in platform:

![image](/uploads/178f94b5bcf8b5dbaa9d00ff7a464ddf/Items-5.png)

As see in above diagram:
### Application Layer:

This layer responsible to define the following configuration:
1. Environment (Development, Demo & Production) 
2. Target/Product flavors: the app must has specific variables.
   - Backend Application ID
   - Backend Organisation ID
   - Target/Product name
   - Target/Product bundle/application id

### Features Layer

This layer includes business features like (Login, Registration, Check-in, etc...)

#### Every feature includes:
1. UI: to describe how user will interact with feature
2. Business: to define feature business requirements

### Core Layer

This layer includes funcational modules like (Networking, Logging, Analytics & etc...) to facilitate on other platform layers (Application & Features)

## MVVM - Desgin Pattern 
Each feature/Module use MVVM(Model view view-model) Architecture as the below diagram:

![image](/uploads/e3d0075c4a363f1d5aba95c1ad4c3ae8/Items-6.png)

### Presentation Stage:
This layer comprises UI components and UI process components .

Being at this layer, the feature has to define the way the mobile app will present it in front of the end users

this layer includes all classes of type:
- Activity
- Fragment
- Custom view
- Adapters
- Dialogs


### Business Stage:
As the name suggests, the layer focuses on the business front. In simple language it focuses on the way business will be presented in front of the end users.

This includes workflows, business components and requirements such as filter, sort & mapping the UI data

This layer includes all classes of type "ViewModel"

### Data Stage:
At this third stage data related factors are kept in mind. This includes Data access components, data helpers/utilities, and service agents.

This layer responsible to provide the different data sources such as Network, SharedPref, Room, Files

To access this layer there is module called (Data-Module), This module built on Repository pattern to Save,Get & Cache any kind of data

##General notes:
- MVVM (Mode View-View Model) is the used pattern across all application modules
- (Core layer) is abstracted layer, it doesn't know anything about application features and business needs.
- (Core layer) can be moved from application to another.
- (Feature layer) modules can be used by Application layer or another module in same layer


# Developer

* Fork this repo to the new group created for the project.
* Import the project into android studio: File >> Open.
* open the project and install dependencies.


Now, your project is set up and you can go ahead to create modules for your features

# Setting up correct Environment
* Rename the application id in /app/build.gradle.

`    applicationId "com.example.src"    `

*  Refactor the existing classes packaging to match your application.
*  Put the Application ID and Organization ID in /app/build.gradle.

`    buildConfigField("String", "AppID", "\"Put AppID HERE\"")`

`    buildConfigField("String", "OrgID", "\"Put OrgID HERE\"")`
* Put the Base URL in /data/build.gradle.
  if you have more than base URL add another buildConficField with name of new URL.

`     buildConfigField("String", "BASE_URL", "\"Put Base Url HERE\"")   `
    
