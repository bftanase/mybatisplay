
DROP TABLE IF EXISTS guestbook;

CREATE TABLE guestbook (
  id int primary key,
  name varchar(50),
  message varchar(200)
);

insert  into guestbook(id,name,message) values (1,'John Doe','Hello Everyone'),(2,'Anne Smith','Wass uuupp???');

