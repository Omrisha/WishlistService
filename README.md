# WishlistService

This service is a final project for Cloud Computing Course in Afeka College of Management.

## Team

Omri Shapira - Team Leader - 203554720
Gal Odentz - 205892250
Alon Bukai - 305347007

## Service Description

A simple spring boot service to give a user a way to manage and create Wish list of products he wishes to buy.

The serivce is not reactive, and will use PostgreSQL as Database, and springboot web.

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

- GET /wishlist/{name}
Get wishlist data by its name.
If not exist will return status code 404.

- PUT /wishlist/{email}?name={name}
Add new product to wishlist by its name and specific user.
To get product rating we will inteface the Products Reviews Management Service.
If there's no rating the value will be -1.
If there's no wishlist with the same name for the user it will return status code 404.

- GET /wishlist?filterType=byUserEmail&filterValue={value}&sortBy={sortAttrEnum}&sortOrder={sortOrderEnum}&size={size}&page={page}
Get all wishlist for the user specified by the email and ordered.
Order details below.

- GET /wishlist?sortBy={sortAttrEnum}&sortOrder={sortOrderEnum}&size={size}&page={page}
Get all wishlist by specific order.
Order details below.

- DELETE /wishlist
delete all wishlists in the database

* sortAttrEnum can be:
  rating - sort by products rating
  user - sort alphabetic by user email
  name - sort alpabetic by list name
  product - sort alphabetic by product name
* sortOrderEnum can be:
  ASC - Ascending order
  DESC - descending order
 
## API Interfacing:

* ShoppingCatalogService
* UserManagementService 
* ProductCouponService
