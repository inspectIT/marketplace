# Marketplace - Frontend

## Getting Started
### Prerequisites
### Installing
Make sure (Node)[https://nodejs.org/en/], (npm)[https://nodejs.org/en/] and (angular-cli)[https://cli.angular.io/] are installed. 
You can check this by typing ```node -v```, ```npm --v``` or ```ng --v``` in command prompt or in your unix shell.


## Running the application/tests
### Project Structure
The Front-End contains four main Pages
* Dashboard - contains an overview of the promoted, recent and most downloaded items
* Details - where you find detailed description to an item, also you can leave a rating and a comment
* Error-Page - this will be shown, if there is a routing error
* Overview-Page - lists all items, that could be filtered regarding a certain category or predicate

Which rely heavily on following components:
* navigation component - used to navigate between the pages

Also following routes are defined
* "/" and "/dashboard" - navigates to the main Dashboard Overview 
* "/profile" - navigates to the corresponding profile page
* something that's not defined or written wrong will lead to error-page

## Deployment
### Hints
* Pay attention, when modifying carousel-component and dashboard-component, because of usage of @Input() annotation, which may lead to spaghetti code.
* There are different model classes, that looks same (SearchResult, Dashboard, Overview). These are dummy classes for now. Every component need its own model class. They will probably change in future.


### End to end tests

## Build with
1. Bootstrap V3.XX
2. jQuery latest
3. valor-software - ng2-file-upload

## Contributing

## Versioning

## Authors
* [Patrice Bouillet](https://github.com/pbouillet)
* [Nikita Kolytschew](https://github.com/nkolytschew)

## ToDo's
* dynamic load of items, when reaching the end of a page
* create custom carousel component
* refactor model classes

## Licence
This project is licensed under the Apache License Version 2.0 - see the [LICENSE](LICENSE) file for details.