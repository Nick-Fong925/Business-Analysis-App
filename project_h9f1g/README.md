# My Personal Project - Business Analysis Application

## Business Analysis Application

My inspiration for the project is my father, a small business owner who owns a souvenir shop. Growing up, I noticed that he consistently worked long and arduous days, yet when he went home, he had additional work including: counting stock, calculating revenue, and making sure that all the items were accounted for. As someone without any experience with technology, he found programs incredibly complicated and intimidating, which meant that most of his work was done with pen and paper. Not only did this increase time, but making mistakes were very common.

The application will be similar to ones integrated into larger organizations, except it will be **catered towards individuals like my father**. The program itself, will be able to **calculate revenue and profit based on acquisition cost**, but also have additional features such as **calculating profit margins on specific items, and determine whether certain items are popular**, as well as being able to **create item specific entries to make things more visually clear**.



## User Stories
- As a user, I want to be able to add a specific item with purchase and sell information to a list
- As a user, I want to be able to visualize the revenue and profit of each specific item entered
- As a user, I want to be able to visualize the quantity and profit margin of each specific item entered
- As a user, I want to be able to keep track of overall profit of all items for each day entry

## Additional User Stories
- As a user, I want to be able to save my item entries, as well as the data visualization lists
- As a user, I want to be able to reload that state from file and resume, being able to add more items and make changes
 


## Phase 4: Task 2

Wed Nov 23 22:12:07 PST 2022
Hat has been added to the list of stocks

Wed Nov 23 22:12:10 PST 2022
Hoodie has been added to the list of stocks

Wed Nov 23 22:12:14 PST 2022
Shirt has been added to the list of stocks

Wed Nov 23 22:12:19 PST 2022
Keychain has been added to the list of stocks

Wed Nov 23 22:12:20 PST 2022
Quantity and Profit Margin Data has been Generated

Wed Nov 23 22:12:21 PST 2022
Profit and Revenue Data has been generated

## Phase 4: Task 3

- If given more time to complete my personal project I would improve my design by reducing duplication
I noticed that in my GUI there was a lot of duplicated code especially with the button functions and initialization of
JFrame objects. 


- Another improvement that I would make is to alter the way data is generated and saved within my stocklist and gui 
class. Currently, I create two different versions of data based on user inputs, one provides the profit margins and 
quantity sold per item, while the other provides revenue and profit per item. In my current project design,
I generated the data using multiple functions and thus has to save the data within json to be loaded. Looking back,
I could have utilized a for loop which would have simplified both the stocklist class and reduced the neccessary data 
needed to be saved.





