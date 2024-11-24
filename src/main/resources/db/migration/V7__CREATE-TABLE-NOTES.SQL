CREATE TABLE IF NOT EXISTS notes (
   id SERIAL PRIMARY KEY,
   notes TEXT,
   class_records_id INT,
   FOREIGN KEY (class_records_id) REFERENCES class_records(id)
);