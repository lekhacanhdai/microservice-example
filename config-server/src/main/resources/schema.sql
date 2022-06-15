DROP TABLE IF EXISTS properties;

CREATE TABLE properties(
    id INT PRIMARY KEY AUTO_INCREMENT,
    application varchar(50) not null,
    profile varchar(50) not null,
    label varchar(50) default null,
    key1 varchar(100) not null,
    value varchar(100) not null
)