Feature: Inventory API
As a user of the Inventory API
I want to manage and query menu items
So that I can ensure the API works as expected

Scenario: Get all menu items
When I send a GET request to "/api/inventory"
Then the response status code should be 200
And the response should contain at least 9 items
And each item should have fields "id", "name", "price", "image"

# Scenario: Filter by id
# When I send a GET request to "/api/inventory/filter?id=3"
# Then the response status code should be 200
# And the response should contain an item with id "3", name "Baked Rolls x 8", price "$10", and image "roll.png"

Scenario: Add item for non existent id
When I send a POST request to "/api/inventory/add" with body
| id | name     | image        | price |
| 10 | Hawaiian | hawaiian.png | $14   |
Then the response status code should be 200

Scenario: Add item for existent id
When I send a POST request to "/api/inventory/add" with body
| id | name     | image        | price |
| 10 | Hawaiian | hawaiian.png | $14 |
Then the response status code should be 400

Scenario: Try to add item with missing information
When I send a POST request to "/api/inventory/add" with body
| name     | image        | price |
| Hawaiian | hawaiian.png | $14   |
Then the response status code should be 400
And the response should contain the message "Not all requirements are met"

Scenario: Validate recent added item is present in the inventory
When I send a GET request to "/api/inventory"
Then the response status code should be 200
And the response should contain an item with id "10", name "Hawaiian", price "$14", and image "hawaiian.png"

Scenario: Negative - Get inventory with invalid endpoint
When I send a GET request to "/api/inventory/invalid"
Then the response status code should be 404
And the response should contain the message "Not Found"

Scenario: Negative - Add item with missing all fields
When I send a POST request to "/api/inventory/add" with body
| name |
| |
Then the response status code should be 400
And the response should contain the message "Not all requirements are met"

