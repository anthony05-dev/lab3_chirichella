CREATE DATABASE IF NOT EXISTS ClothingStore;
USE ClothingStore;

DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
                           id INT PRIMARY KEY,
                           firstName VARCHAR(50),
                           lastName VARCHAR(50),
                           points INT,
                           email VARCHAR(100)
);