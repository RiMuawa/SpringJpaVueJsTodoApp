CREATE TABLE IF NOT EXISTS task (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    due_date DATE NOT NULL,
    code INT UNIQUE NOT NULL,
    status BOOLEAN NOT NULL
);
