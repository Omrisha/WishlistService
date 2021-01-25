# WishlistService

This service is a final project for Cloud Computing Course in Afeka College of Management.

## Team

* Omri Shapira - Team Leader - 203554720
* Gal Odentz - 205892250
* Alon Bukai - 305347007

## Service Description

A simple spring boot service to give a user a way to manage and create Wish list of products he wishes to buy.

The serivce is not reactive, and will use PostgreSQL as Database, and springboot web.

## Installation

* Running MongoDB local database from the following command: `docker run --name mongotest -p 27017:27017 -d mongo`
* `git clone https://github.com/Omrisha/WishlistService`
* Open project in IntelliJ (Open project menu or import from git menu)
* Open project in Eclipse (File -> Import -> Import existing Gradle Project menu)
* after opening let gradle built and make the project.
* Run (Eclipse -> Run as SpringBoot App, IntelliJ -> via Play button)
* Open http://localhost:{port you defined in application.properties}/swagger-ui.html in your browser.

## JSON Structure

* List:
  user - represent the user that the list belongs to by id (email).
  name - the list name
  id - unique id for the list
  products - list of products in the wishlist
* Product:
  id - unique id for the list
  name - product name
  rating - how users rate the product (can be acheived from Products Reviews Management Service).

## JSON Example

```
{
	“user”: {
		“email”: temp@e-commerce.com
	},
	“id”: 1,
	“name”: “My Awesome things”,
	“products”: [
		“product”: {
			“id”: 42,
			“name”: “Groot”,
			“ratings”: 42
		},
		“product”: {
			“id”: 526, 
			“name”: “Baby yoda”,
			“ratings”: 42
		}
	]
}
```

```
{
	“user”: {
		“email”: temp@e-commerce.com
	},
	“name”: “My Awesome things”
}
```

```
{
	“id”: 526, 
	“name”: “Baby yoda”,
  “ratings”: 42
}
```

## API Specification

- POST /wishlist
Get new wishlist details and creates it in the DB if not exist already.
If the user is not exist in UserManagementService the service will return status code 400.

- GET /wishlist/{email}/{wishlistName}
Get wishlist data by its name.
If not exist will return status code 404.

- PUT /wishlist/{email}/{wishlistName}
Add new product to wishlist by its name and specific user.
To get product rating we will inteface the Products Reviews Management Service.
If there's no rating the value will be -1.
If there's no wishlist with the same name for the user it will return status code 404.

- GET /wishlist?filterBy=productId&filterValue={productId}&sortBy={sortAttrEnum}&sortOrder={sortOrderEnum}&size={size}&page={page}
Get all wishlist for the user specified by the email and ordered.
Order details below.

- GET /wishlist?filterBy=customerEmail&filterValue={customerEmail}&&sortBy={sortAttrEnum}&sortOrder={sortOrderEnum}&size={size}&page={page}
Get all wishlist by specific order.
Order details below.

- DELETE /wishlist
delete all wishlists in the database

* sortAttrEnum can be:
  user - sort alphabetic by user email
  name - sort alpabetic by list name
* sortOrderEnum can be:
  ASC - Ascending order
  DESC - descending order
 
## API Interfacing:

* ShoppingCatalogService - https://github.com/Omrisha/ShoppingCatalogService
* UserManagementService - https://github.com/Omrisha/user-management-service + https://github.com/Omrisha/dummy-storage-service
* ProductCouponService
