CREATE TABLE category
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) not null,
    description VARCHAR(500),
    tax NUMERIC(6,2) not null
);
CREATE TABLE users
(
    id VARCHAR(100) PRIMARY KEY,
    display_name VARCHAR(100) not null,
    address VARCHAR(500) not null,
    phone VARCHAR(100)
);
CREATE TABLE items
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) not null,
    description VARCHAR(500) not null,
    price NUMERIC(16,2) not null,
    category_id INTEGER not null,
    FOREIGN KEY (category_id) REFERENCES category (id)
);
CREATE TABLE orders
(
    id SERIAL PRIMARY KEY,
    status VARCHAR(30) not null default 'open',
    user_id VARCHAR(100) not null,
    FOREIGN KEY (user_id) REFERENCES users (id) on delete CASCADE
);
CREATE TABLE item_in_order
(
    order_id INTEGER not null,
    item_id INTEGER not null,
    PRIMARY KEY(order_id,item_id),
    FOREIGN KEY(order_id) REFERENCES orders (id)  on delete CASCADE,
    FOREIGN KEY(item_id) REFERENCES items (id) on delete RESTRICT
);
CREATE TABLE roles (
	user_id VARCHAR(100) NOT NULL,
	role VARCHAR(50) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users (id) on delete CASCADE,
	PRIMARY KEY (user_id,role)
);


