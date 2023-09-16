create database GrAs;

use GrAs;

create table if not exists supplier(
SUPP_ID INT primary key,
SUPP_NAME varchar(50) NOT NULL,
SUPP_CITY varchar(50) NOT NULL,
SUPP_PHONE varchar(50)NOT NULL);

create table if not exists customer(
CUS_ID INT primary key,
CUS_NAME VARCHAR(20)NOT NULL,
CUS_PHONE VARCHAR(10)NOT NULL,
CUS_CITY VARCHAR(30)NOT NULL,
CUS_GENDER CHAR primary key);

create table  if not exists category(
CAT_ID INT primary key,
CAT_NAME VARCHAR(20)
NOT NULL);

create table if not exists products(
PRO_ID INT primary key,
PRO_NAME VARCHAR(20)
NOT NULL
DEFAULT
"Dummy",
PRO_DESC VARCHAR(60),
CAT_ID INT NOT NULL,
PRIMARY KEY (PRO_ID),
FOREIGN KEY (CAT_ID) REFERENCES CATEGORY (CAT_ID)
);

create table if not exists supplier_pricing(
PRICING_ID INT primary key,
PRO_ID INT ,
SUPP_ID INT ,
SUPP_PRICE INT
DEFAULT 0,
PRIMARY KEY (PRICING_ID),
FOREIGN KEY (PRO_ID) REFERENCES PRODUCT (PRO_ID),
FOREIGN KEY (SUPP_ID) REFERENCES SUPPLIER(SUPP_ID)
);


create table if not exists orders(
ORD_ID INT primary key,
ORD_AMOUNT INT NOT NULL,
ORD_DATE DATE NOT NULL,
CUS_ID INT ,
PRICING_ID INT,
PRIMARY KEY (ORD_ID),
FOREIGN KEY (CUS_ID) REFERENCES CUSTOMER(CUS_ID),
FOREIGN KEY (PRICING_ID) REFERENCES SUPPLIER_PRICING(PRICING_ID)
);

create table if not exists rating(
RAT_ID INT primary key,
ORD_ID INT ,
RAT_RATSTARS INT NOT NULL,
PRIMARY KEY (RAT_ID),
FOREIGN KEY (ORD_ID) REFERENCES `order`(ORD_ID)
);


insert into supplier values(1 ,'Rajesh Retails ','Delhi ',1234567890),
(2,' Appario Ltd.',' Mumbai', 2589631470),
(3,' Knome products',' Banglore', 9785462315),
(4 ,'Bansal Retails',' Kochi', 8975463285),
(5,' Mittal Ltd.',' Lucknow', 7898456532);

insert into customer values(1,"AAKASH",'9999999999',"DELHI",'M'),
(2,"AMAN",'9785463215',"NOIDA",'M'),
(3,"NEHA",'9999999999',"MUMBAI",'F'),
(4,"MEGHA",'9994562399',"KOLKATA",'F'),
(5,"PULKIT",'7895999999',"LUCKNOW",'M');

insert into customer values( 1,"BOOKS"),
(2,"GAMES"),
(3,"GROCERIS"),
(4,"ELECTRONICS"),
(5,"CLOTHES");

insert into product values(1,"GTA V","Windows 7 and above with i5 processor and 8GB RAM",2),
(2,"TSHIRT","SIZE-L with Black, Blue and White variations",5),
(3,"ROG LAPTOP","Windows 10 with 15inch screen, i7 processor, 1TB SSD",4),
(4,"OATS","Highly Nutritious from Nestle",3),
(5,"HARRY POTTER","Best Collection of all time by J.K Rowling",1),
(6,"MILK","1L Toned MIlk",3),
(7,"Boat EarPhones","1.5Meter long Dolby Atmos",4),
(8,"Jeans","Stretchable Denim Jeans with various sizes and color",5),
(9,"Project IGI","compatible with windows 7 and above",2),
(10,"Hoodie","Black GUCCI for 13 yrs and above",5),
(11,"Rich Dad Poor Dad","Written by RObert Kiyosaki",1),
(12,"Train Your Brain","By Shireen Stephen",1);

insert into supplier_pricing values(1,1,2,1500),
(2,3,5,30000),
(3,5,1,3000),
(4,2,3,2500),
(5,4,1,1000),
(6,12,2,780),
(7,12,4,789),
(8,3,1,31000),
(9,1,5,1450),
(10,4,2,999),
(11,7,3,549),
(12,7,4,529),
(13,6,2,105),
(14,6,1,99),
(15,2,5,2999),
(16,5,2,2999);

insert into `ORDER` values (101,1500,"2021-10-06",2,1),
(102,1000,"2021-10-12",3,5),
(103,30000,"2021-09-16",5,2),
(104,1500,"2021-10-05",1,1),
(105,3000,"2021-08-16",4,3),
(106,1450,"2021-08-18",1,9),
(107,789,"2021-09-01",3,7),
(108,780,"2021-09-07",5,6),
(109,3000,"2021-09-10",5,3),
(110,2500,"2021-09-10",2,4),
(111,1000,"2021-09-15",4,5),
(112,789,"2021-09-16",4,7),
(113,31000,"2021-09-16",1,8),
(114,1000,"2021-09-16",3,5),
(115,3000,"2021-09-16",5,3),
(116,99,"2021-09-17",2,14);

INSERT INTO RATING VALUES(1,101,4),
(2,102,3),
(3,103,1),
(4,104,2),
(5,105,4),
(6,106,3),
(7,107,4),
(8,108,4),
(9,109,3),
(10,110,5),
(11,111,3),
(12,112,4),
(13,113,2),
(14,114,1),
(15,115,1),
(16,116,0);

select count(t2.cus_gender) as NoOfCustomers, t2.cus_gender from 
(select t1.cus_id, t1.cus_gender, t1.ord_amount, t1.cus_name from 
(select `order`.*, customer.cus_gender, customer.cus_name from `order` inner join customer where `order`.cus_id=customer.cus_id having `order`.ord_amount>=3000)
as t1  group by t1.cus_id) as t2 group by t2.cus_gender;

select product.pro_name, `order`.* from `order`, supplier_pricing, product 
where `order`.cus_id=2 and `order`.pricing_id=supplier_pricing.pricing_id and supplier_pricing.pro_id=product.pro_id;

select supplier.* from supplier where supplier.supp_id in 
(select supp_id from supplier_pricing group by supp_id having 
count(supp_id)>1) 
group by supplier.supp_id;

create view lowest_exp_prod as
select c.cat_id,c.cat_name,p.pro_name,min(sp.supp_price) as lowestprice
from category c
join
product p 
on c.cat_id=p.cat_id
join supplier_pricing sp
on p.pro_id=sp.pro_id
group by c.cat_id,c.cat_name,p.pro_name;

Select * from lowest_expensive_product;

select product.pro_id,product.pro_name from `order` 
inner join supplier_pricing on supplier_pricing.pricing_id=`order`.pricing_id 
inner join product on product.pro_id=supplier_pricing.pro_id 
where `order`.ord_date>"2021-10-05";

select customer.cus_name,customer.cus_gender from customer 
where customer.cus_name like 'A%' or customer.cus_name like '%A';

DELIMITER //
CREATE PROCEDURE CalculateSupplierRating()
BEGIN
    SELECT s.SUPP_ID, s.SUPP_NAME, AVG(r.RAT_RATSTARS) AS Rating,
    CASE
        WHEN AVG(r.RAT_RATSTARS) = 5 THEN 'Excellent Service'
        WHEN AVG(r.RAT_RATSTARS) > 4 THEN 'Good Service'
        WHEN AVG(r.RAT_RATSTARS) > 2 THEN 'Average Service'
        ELSE 'Poor Service'
    END AS Type_of_Service
    FROM supplier s
    LEFT JOIN supplier_pricing sp ON s.SUPP_ID = sp.SUPP_ID
    LEFT JOIN ordertable o ON sp.PRICING_ID = o.PRICING_ID
    LEFT JOIN rating r ON o.ORD_ID = r.ORD_ID
    GROUP BY s.SUPP_ID, s.SUPP_NAME;
END //
DELIMITER ;


CALL CalculateSupplierRating();
