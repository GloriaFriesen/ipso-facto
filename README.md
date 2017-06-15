# Ipso Facto

#### _By Gloria Friesen_

### Congress Reference Mobile Application
Current Version: 1.3 - 06.16.2017

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
    - [X] Name
    - [X] Party
    - [X] Role
- [X] Display list of information using a RecyclerView
    - [X] Display list of legislators in state from API response
       - [X] Currently, able to display list of senators from user's inputted state
       - [X] Need to implement logic to take full state name from user input and convert to state abbreviation to plug into API
       - [X] Need to add functionality for user to choose search between senators and representatives
    - [X] Include name, party, and role
    - [X] Clickable, will send to detail view of that legislator
- [X] Use fragments and PageAdapter to swipe through views
    - [X] Display complete legislator information
        - [X] PageAdapter is in place, displays initial API response with name, party, and role
        - [X] Need to implement 2nd API call using legislator id to obtain more detailed information
    - [X] Phone and websites will be clickable to initiate implicit intent
- [X] Incorporate implicit intent
    - [X] Need to implement 2nd API call using legislator id to obtain more detailed information
    - [X] Phone call
    - [X] Times Website
    - [X] Gov website

#### Week 3:
- [X] Firebase user authentication
    - [X] Sign in screen
    - [X] Register screen
- [X] Save and retrieve data using Firebase
- [X] Firebase-RecyclerAdapter to display from database
    - [X] Allow users to 'favorite' a legislator
    - [X] Retrieve list from database
- [X] Use SharedPreferences to save an important piece of data in your application
- [X] Use progress dialog to inform users of login status

#### Week 4:
- [ ] Include 2 (minimum) gesture listeners, animations, or utilize camera
- [ ] Implements alternate resource
- [ ] Publish to Google Play

## Known Bugs
* SavedLegislatorList
    * Save Legislator button is at the bottom, not ideal
* Layout is far from ideal, need to spend time improving especially across different screen sizes
* Would like to display dialog fragment for search feature, in progress
    * If not, need to fix layout on MainActivity, it's currently a mess


## Support and contact details
Questions? Concerns? Suggestions? Reach out to me via github: <https://github.com/GloriaFriesen>.

## Technologies Used
* _Android_
* _Java_
* _XML_
* _Gradle_
* _Firebase_

## License
This software is licensed under the MIT license.
Copyright (c) 2017 Gloria Friesen.
