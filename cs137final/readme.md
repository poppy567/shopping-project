
Group member:
Peiyuan Chen 00995617
Yingxue Zhang 00995516

# Website URL

http://centaurus-7.ics.uci.edu:5556/cs137final/list.jsp

# REQUIREMENTS

## Requirement 1

The product list page using JSP is http://centaurus-7.ics.uci.edu:5556/cs137final/list.jsp

## Requirement 2

### i. Method Type: GET (product)

### ii. Request URL

http://centaurus-7.ics.uci.edu:5556/jerseyrest/v1/api/shorts/3

### iii. Sample Response

...
{
"id": 3,
"name": "Gingham Track Short",
"price": 65,
"color": "Rose",
"description": "Gingham print track short from Champion that’s been designed in collaboration with HVN and is available only at UO. Made from a super soft gingham knit in a pull-on construction. Topped with a drawstring waistband. Finished with side-entry pockets + embroidered logo detailing.",
"pic1": "pics/pic3.jpeg",
"pic2": "pics/pic3-1.jpeg",
"pic3": "pics/pic3-2.jpeg"
}
...

### i. Method Type: GET (order)

### ii. Request URL

http://centaurus-7.ics.uci.edu:5556/jerseyrest/v1/api/orders/2

### iii. Sample Response

...
{
"id": 2,
"shortId": 2,
"size": 2,
"qty": 2,
"customerId": 2,
"address": "uci",
"shipMethod": "2days",
"city": "irvine",
"state": "ca",
"zip": "92613"
}
...

### i. Method Type: PUT (product)

### ii. Request URL

http://centaurus-7.ics.uci.edu:5556/jerseyrest/v1/api/shorts/11

### iii. Sample Request

...
{
"price": 41
}
...

### iv. Sample Response

...
{
"id": 11,
"name": "BDG Mom High-Rise Denim Short",
"price": 41,
"color": "Black",
"description": "Throwback high-rise denim mom short exclusive to UO by BDG. In soft medium-weight denim with a relaxed short-short silhouette finished with fixed cuffs, 5 pockets + a button-zipper fly.",
"pic1": "pics/pic11.jpeg",
"pic2": "pics/pic11-1.jpeg",
"pic3": "pics/pic11-2.jpeg"
}
...

### i. Method Type: PUT (order)

### ii. Request URL

http://centaurus-7.ics.uci.edu:5556/jerseyrest/v1/api/orders/5

### iii. Sample Request

...
{
"size": 30,
"qty": 4,
"customerId": 4,
"address": "updated ucsb",
"zip": "93105"
}
...

### iv. Sample Response

...
{
"id": 5,
"shortId": 5,
"size": 30,
"qty": 4,
"customerId": 4,
"address": "updated ucsb",
"shipMethod": "normal",
"city": "santa barbara",
"state": "ca",
"zip": "93105"
}
...

### i. Method Type: POST (product)

### ii. Request URL

http://centaurus-7.ics.uci.edu:5556/jerseyrest/v1/api/shorts

### iii. Sample Request

...
{
"id": 11,
"name": "BDG Mom High-Rise Denim Short",
"price": 49,
"color": "Black",
"description": "Throwback high-rise denim mom short exclusive to UO by BDG. In soft medium-weight denim with a relaxed short-short silhouette finished with fixed cuffs, 5 pockets + a button-zipper fly.",
"pic1": "pics/pic11.jpeg",
"pic2": "pics/pic11-1.jpeg",
"pic3": "pics/pic11-2.jpeg"
}
...

### iv. Sample Response

SHORT Added Successfully

### i. Method Type: POST (order)

### ii. Request URL

http://centaurus-7.ics.uci.edu:5556/jerseyrest/v1/api/orders

### iii. Sample Request

...
{
"id": 6,
"shortId": 6,
"size": 29,
"qty": 6,
"customerId": 1,
"address": "ucla",
"shipMethod": "2days",
"city": "los angeles",
"state": "ca",
"zip": "91001"
}
...

### iv. Sample Response

ORDER Added Successfully

### i. Method Type: DELETE (product)

### ii. Request URL

http://centaurus-7.ics.uci.edu:5556/jerseyrest/v1/api/shorts/12

### iv. Sample Response

SHORT Deleted Successfully


### i. Method Type: DELETE (order)

### ii. Request URL

http://centaurus-7.ics.uci.edu:5556/jerseyrest/v1/api/orders/2

### iv. Sample Response

ORDER Deleted Successfully
