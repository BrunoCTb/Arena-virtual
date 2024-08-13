CREATE TABLE users (

    id CHAR(36) PRIMARY KEY,
    username VARCHAR(30),
    first_name VARCHAR(100),
    last_name VARCHAR(250),
    email VARCHAR(250),
    password VARCHAR(250),
    birth DATE,
    image_url VARCHAR(250)

)