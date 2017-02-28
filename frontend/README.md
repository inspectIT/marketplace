# Requisition
Make sure (Node)[https://nodejs.org/en/], (npm)[https://nodejs.org/en/] and (angular-cli)[https://cli.angular.io/] are installed. 
You can check this by typing ```node -v```, ```npm --v``` or ```ng --v``` in command prompt or in your unix shell.

# Project Structure
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

## Hints
* Pay attention, when modifying carousel-component and dashboard-component, because of usage of @Input() annotation, which may lead to spaghetti code.
* There are different model classes, that looks same (SearchResult, Dashboard, Overview). These are dummy classes for now. Every component need its own model class. They will probably change in future.


## Tools 
1. Bootstrap V3.XX
2. jQuery latest
3. valor-software - ng2-file-upload


## TODO
* dynamic load of items, when reaching the end of a page
* create custom carousel component
* refactor model classes