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
	region VARCHAR(255),
	zip_code INT, 
	contact VARCHAR(20),
	email VARCHAR(80),
	image TEXT,
	username VARCHAR(80),
	password VARCHAR(255),
	role VARCHAR(50),
	status BOOLEAN
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
	status BOOLEAN
);

DROP TABLE IF EXISTS course;
CREATE TABLE course(
	course_id SERIAL,
	course_name VARCHAR(255),
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
	quantity INT,
	mass numeric(12, 2),
	price INT,
	image TEXT,
	post_date date,
	status boolean
);

drop table if exists offer;
CREATE TABLE offer(
	offer_id SERIAL,
	post_id INT,
	farmer_id INT,
	quantity INT,
	price INT,
	offer_date date
);

drop table if exists payment;
CREATE TABLE payment(
	payment_id SERIAL,
	order_id_ref INT,
	offer_id INT,
	payment_mode VARCHAR(20),
	payment_date date,
	status boolean
);

drop table if exists sold_crop;
CREATE TABLE sold_crop(
	sold_id SERIAL,
	offer_id INT,
	sold_crop_date date
);