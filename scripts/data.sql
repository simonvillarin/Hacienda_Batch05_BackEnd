DROP DATABASE IF EXISTS farming;
CREATE DATABASE farming;

\c farming;

DROP TABLE IF EXISTS users; 
CREATE TABLE users(
	user_id SERIAL,
	first_name VARCHAR(80),
	middle_name VARCHAR(80),
	last_name VARCHAR(80),
	suffix VARCHAR(50),
	gender VARCHAR(50),
	birthdate DATE,
	unit VARCHAR(255),
	street VARCHAR(255),
	village VARCHAR(255),
	barangay VARCHAR(255),
	city VARCHAR(255),
	province VARCHAR(255),
	region VARCHAR(255),
	contact VARCHAR(20),
	email VARCHAR(80),
	image TEXT,
	id_type VARCHAR(80),
	id_front TEXT,
	id_back TEXT,
	selfie TEXT,
	username VARCHAR(80),
	password VARCHAR(255),
	role VARCHAR(50),
	status VARCHAR(80)
);

DROP TABLE IF EXISTS farmer;
CREATE TABLE farmer(
	farmer_id SERIAL,
	user_id INT
);

DROP TABLE IF EXISTS admin;
CREATE TABLE admin(
	admin_id SERIAL,
	user_id INT
);

DROP TABLE IF EXISTS supplier;
CREATE TABLE supplier(
	supplier_id SERIAL,
	user_id INT
);

DROP TABLE IF EXISTS farming_tips;
CREATE TABLE farming_tips(
	tip_id SERIAL,
	subject TEXT,
	tip TEXT,
	image TEXT,
	status BOOLEAN
);

DROP TABLE IF EXISTS complaint;
CREATE TABLE complaint(
	complaint_id SERIAL,
	farmer_id INT,
	complaint_type VARCHAR(80),
	complaint_details TEXT,
	date date,
	image TEXT,
	status BOOLEAN,
	is_deleted BOOLEAN
);

DROP TABLE IF EXISTS course;
CREATE TABLE course(
	course_id SERIAL,
	course_name VARCHAR(255),
	description TEXT,
	yt_link TEXT,
	status BOOLEAN
);

DROP TABLE IF EXISTS course_enrolled;
CREATE TABLE course_enrolled(
	enroll_id SERIAL,
	farmer_id INT,
	course_id INT,
	enroll_date date,
	status BOOLEAN
);

DROP TABLE IF EXISTS schedule;
CREATE TABLE schedule(
	sched_id SERIAL,
	farmer_id INT,
	start_date date,
	end_date date,
	status BOOLEAN
);

DROP TABLE IF EXISTS image;
CREATE TABLE image (
	image_id SERIAL,
    filename TEXT,
    mime_type VARCHAR(30),
    data bytea
);

drop table if exists advertisement;
CREATE TABLE advertisement(
	post_id SERIAL,
	supplier_id INT,
	name VARCHAR(80),
	category VARCHAR(80),
	description VARCHAR(80),
	measurement VARCHAR(80),
	value numeric(13, 3),
	price numeric(13, 3),
	image TEXT,
	post_date date,
	status boolean,
	is_offered boolean
);

drop table if exists offer;
CREATE TABLE offer(
	offer_id SERIAL,
	farmer_id INT,
	supplier_id INT,
	post_id INT,
	measurement VARCHAR(80),
	value numeric(13, 3),
	price numeric(13, 3),
	offer_date date,
	offer_time time,
	is_accepted boolean,
	is_viewed boolean,
	status boolean
);

drop table if exists transaction;
create table transaction(
	transaction_id SERIAL,
	supplier_id INT,
	farmer_id INT,
	offer_id INT,
	accept_date date,
	accept_time time,
	paid_date date,
	paid_time time,
	deliver_date date,
	deliver_time time,
	status boolean,
	is_viewed BOOLEAN
);

drop table if exists payment;
CREATE TABLE payment(
	payment_id SERIAL,
	order_id_ref VARCHAR(80),
	transaction_id INT,
	payment_mode VARCHAR(80),
	payment_date date,
	payment_time time,
	status boolean
);

drop table if exists payment_details;
CREATE TABLE payment(
	payment_details_id SERIAL,
	payment_id_ref VARCHAR(80),
	payment_account_id INT,
	payment_mode VARCHAR(80),
	account_number INT,
	account_name VARCHAR(80),
	status boolean
);

drop table if exists payment_account;
CREATE TABLE payment(
	payment_account_id SERIAL,
	payment_mode VARCHAR(80),
	account_number INT,
	account_name VARCHAR(80),
	status boolean
);


drop table if exists sold_crop;
CREATE TABLE sold_crop(
	sold_id SERIAL,
	offer_id INT,
	sold_crop_date date
);