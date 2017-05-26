# Ipso Facto

#### _By Gloria Friesen_

### Congress Reference Mobile Application
Current Version: 1.0 - 05.26.2017

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
- [ ] Display list of information
  - [ ] Display hard coded list of legislators in LegislatorsActivity
- [ ] Implement adapters
  - [ ] ArrayAdapter to display list of legislators
- [ ] Gather user input and pass to another activity
  - [ ] EditText on MainActivity will pass user inputted state to LegislatorActivity
- [ ] Validate forms
- [x] Use ButterKnife to bind views
- [x] Implement View.OnClickListener

#### Week 2:
- [ ] Implement OkHttp to retrieve data from a backend
  - [ ] Use ProPublica API to retrieve legislator data
- [ ] Create data model
- [ ] Display list of information using a RecyclerView
- [ ] Use fragments and PageAdapter to swipe through views
- [ ] Incorporate implicit intent

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
* Soft input keyboard opens when app initially runs

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
