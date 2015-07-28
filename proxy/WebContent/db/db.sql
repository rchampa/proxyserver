create table users
(
    user_id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username varchar(20),
    password varchar(20)    
);

create table projects
(
    project_id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    project_name varchar(20),
    uri varchar(100),
    user_id INT(5),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table mockups
(
    mockup_id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    url_mock varchar(100),
    headers_mock varchar(100),
    method_mock varchar(8),
    data_mock varchar(1024),
    enable boolean,
    deleted boolean,
    project_id INT(5),
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);