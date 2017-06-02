# Ipso Facto

#### _By Gloria Friesen_

### Congress Reference Mobile Application
Current Version: 1.1 - 06.02.2017

### Description

Mobile application built with Android allowing users to search by location to find their congressional legislators.

### Intended Users

Politically engaged citizens.

## Installation/Setup

* In the terminal, run: `$ git clone <https://github.com/GloriaFriesen/ipso-facto>`
* Open Android Studio and select 'Open an existing Android Studio project'
* Find project Ipso Facto on local machine
* In Android Studio, run app on emulator or connected Android device (Keyboard shortcut: Shift+F10)

## Objectives

#### Week 1:
- [x] 3 (minimum) activities
  - [x] MainActivity
  - [x] ContactActivity
  - [x] AboutActivity
  - [x] LegislatorListActivity
- [X] Display list of information
  - [X] Display hard coded list of legislators in LegislatorsActivity
- [X] Implement adapters
  - [X] ArrayAdapter to display list of legislators
- [X] Gather user input and pass to another activity
  - [X] EditText on MainActivity will pass user inputted state to LegislatorActivity
- [X] Validate forms
  - [X] Use autocomplete, user needs to selected from predetermined array of states
  - [X] Add if/else to prevent user from submitting search without selecting a state
- [x] Use ButterKnife to bind views
- [x] Implement View.OnClickListener
- [X] Include custom typeface

#### Week 2:
- [X] Implement OkHttp to retrieve data from a backend
  - [X] Use ProPublica API to retrieve legislator data
- [X] Create data model (Legislator.java)
    - [ ] Name
    - [ ] Party
    - [ ] Role
- [ ] Display list of information using a RecyclerView
    - [ ] Display list of legislators in state from API response
    - [ ] Include name, photo (if available), party, and house
    - [ ] Clickable, will send to detail view of that legislator
- [ ] Use fragments and PageAdapter to swipe through views
    - [ ] Display complete legislator information
    - [ ] Phone and email will be clickable to initiate implicit intent
- [ ] Incorporate implicit intent
    - [ ] Phone call
    - [ ] Email

#### Week 3:
- [ ] Firebase user authentication
- [ ] Save and retrieve data using Firebase
- [ ] Firebase-RecyclerAdapter to display from database
- [ ] Use SharedPreferences to save an important piece of data in your application
- [ ] Allow users to 'favorite' a legislator and retrieve list from database
- [ ] Use dialogs to inform users of login status
- [ ] Use SearchView to gather user input

#### Week 4:
- [ ] Include 2 (minimum) gesture listeners, animations, or utilize camera
- [ ] Implements alternate resource
- [ ] Publish to Google Play

## Known Bugs
* ProPublica API takes state abbreviation as query but I am currently getting the full state name from the user, will need to implement code to convert before sending API request. Currently, hard coded string is in Legislator Service.
* ProPublica API searches by chamber (house vs senate), currently hard coded to return senators only, need to add user input
* ProPublica searches for representatives by district, need to find that for the user

## Support and contact details
Questions? Concerns? Suggestions? Reach out to me via github: <https://github.com/GloriaFriesen>.

## Technologies Used
* _Android_
* _Java_
* _XML_
* _Gradle_

## License
This software is licensed under the MIT license.
Copyright (c) 2017 Gloria Friesen.
