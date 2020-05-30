A simple project to ply with a graphql.


http://localhost:8080/rest/books

raw body of post request for all books:
```
{
	allBooks {
		isn
		title
		authors
	}
}
```
raw body of post request for a particular book:
```
{
	book(id: "123") {
		title
		authors
		publisher
	}
}
```
a mix of all books, and a particular book

```
{
	allBooks {
		isn
	}
	
	book(id: "123") {
		title
		authors
		publisher
	}
}
```



